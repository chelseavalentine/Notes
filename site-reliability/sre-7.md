# Site Reliability Engineering

Notes taken reading Google's [Site Reliability Engineering](https://landing.google.com/sre/) book.

## Ch 22: Addressing cascading failures

### Causes of cascading failures and designing to avoid them

* server overload, resource exhaustion

* insufficient CPU makes all requests slower, resulting in various secondary effects:
  - increased # of in-flight requests
    + more requests are handled concurrently, which usually triggers queueing
  - excessively long queue lengths, thread starvation
  - CPU or request starvation
    + internal server watchdogs checking server progress are also processed as part of the request queue
  - missed RPC deadlines, reduced CPU caching benefits

* memory exhaustion causes the following:
  - dying tasks (because the container manager evicts them for exceeding available resource limits, or there're application-specific crashes)
  - increased rate of garbage collection in Java -> increased CPU usage
  - reduced cache hit rates

* thread starvation can directly cause errors/lead to health check failures, since thread overhead could use too much RAM
  - in extreme cases, thread starvation can cause you to run out of process IDs

* file descriptors
  - running out of them can lead to the inability to initialize network connections, thus causing health checks to fail

* dependencies amongst resources can make it hard to pinpoint where things went wrong, and to erroneously think a certain part is at fault

* service unavailability (resource exhaustion -> servers crashing (e.g. b/c too much RAM allocated to a container) -> overload other servers)

### Preventing server overload

1. load test the server's capacity limits, & test the failure mode for overload

2. serve degraded results

3. instrument the server to reject requests when overloaded

4. instrument higher-level systems to reject requests, rather than overloading servers
  - rate limiting usually doesn't take overall service health into account, so it might not be able to stop a failure that's already started, and simple limitations are likely to leave capacity unused
  - can implement rate limiting in a number of places:
    + at the reverse proxies (by limiting the volume of requests by criteria such as IP address)
    + at the load balancers (by dropping requests when the service enters global overload)
    + at individual tasks (to prevent random fluctuations in load balancing from overwhelming the server)

5. perform capacity planning
  - also should be used to determine the load at which the service will fail

#### Queue management

* most thread-per-request servers use queues so that they can reject new requests when the queue is full
  - but queued requests consume memory & increase latency, because requests need to wait on a queue
  - for systems w/ steady traffic, a queue size ~50% of the thread pool size is ideal
  - systems with "bursty" traffic could benefit from a queue size = thread pool size
  
* another option is using queueless servers, relying on failover to other server tasks when the threads are full

#### Load shedding and graceful degradation

* __load shedding__: dropping a proportion of the load by dropping traffic as the server approaches overload conditions
  - to prevent the server from running out of RAM, failing health checks, serving with extremely high latency, or experiencing any of the overload symptoms
  - can be done via per-task throttling based on CPU/memory/queue length
  - changing the queue to LIFO could be good because a user who has been waiting for 10s is likely to have had refreshed their browser

* __graceful degradation__ reduces the amount of work needed to be performed
  - considerations to make when implementing this:
    + what metrics should you use to decide when to load shed or gracefully degrade requests?
    + what actions should be taken when the server is in degraded mode?
    + what layer should you implement this? every layer in the stack? at a high-level choke point?
  - keep the implementation simple, otherwise you might erroneously trigger degraded mode;
    + good to have a way to turn off complex degradation or tune parameters if needed

#### Retries

* always use randomized exponential backoff when scheduling retries
  - if they aren't randomly distributed over the retry window, there's a chance retry ripples will schedule at the same time, thus amplifying themselves

* limit retries per request; don't retry indefinitely

* consider a server-wide retry budget per minute, where you just fail requests when the budget is exceeded

* think about the service holistically and ensure multiple levels aren't unnecessarily retrying, otherwise each client retry attempt could potentially trigger [number of client attempts]^(amount of layers retrying)

#### Deadlines

* use RPC deadlines to determine how long a request can wait before it gives up, so the frontend's resources wont be consumed for too long
  - ensure that the server checks the deadline before attempting to perform any work on the request

* servers should employ deadline propagation & cancellation propagation

#### Bimodal latency

* __bimodal latency__ can be a cause of an outage, but you may not know if you only look at the mean latency
  - so also look at the distribution of latencies when you see a latency increase
  - is avoidable if you return requests, which don't complete, early with an error rather than waiting for the deadline

* having deadlines several orders of magnitude longer than the mean request latency usually is bad & can lead to thread exhaustion

* try limiting in-flight requests for a shared resource, or limit the number of threads a client can occupy, in order to prevent a client from dominating your system

### Slow startup and cold caching

* processes are often slower when starting b/c of either/both:
  1. required initialization
  2. runtime performance improvements in some languages, particularly Java (Just-In-Time compilation, hotspot optimization, & deferred class loading)
  - binaries are also less efficient when caches aren't filled

* things leading to a cold cache:
  - turning up a new cluster
  - returning a cluster to service after maintenance
  - restarts

* when caching has a significant effect on the service, consider:
  - overprovisioning the service
    + if a latency cache is employed, a service can sustain its expected load with an empty cache
    + if a capacity cache is employed, it can't sustain its expected load with an empty cache
  - employ general cascading failure prevention techniques
  - when adding load to a cluster, slowly increase the load, so you warm up the cache
    + can also ensure that all clusters carry a nominal load & caches are kept warm

### Triggering conditions for cascading failures

* things you can use to try to initiate the domino effect:
  - process death, process updates, new rollouts, organic growth
  - planned changes, drains or turndowns; request profile changes, resource limits

### Testing for cascading failures

* also check for cache correctness at high loads (subtle concurrency bugs)

* test your service's largest clients to see how they respond to failure

* test noncritical backends to ensure their unavailability doesn't interfere w/ critical components of your service
  - e.g. spelling suggestions shouldn't interfere with query results (which are way more critical)

### Immediate steps to address cascading failures

* increase resources; stop health check failures/deaths; restart servers; enter degraded modes; eliminate batch load; eliminate bad traffic
