# Site Reliability Engineering

Notes taken reading Google's [Site Reliability Engineering](https://landing.google.com/sre/) book.

## Ch 20: Load balancing in the datacenter

### Identifying bad tasks: Flow control and lame ducks

* simple flow control: track active requests per backend, and limit active requests to 100
  - but this only protects backend tasks against extreme forms of overload, and it can become overloaded before 100

* lame duck state approach uses 3 states a backend task can be in:
  - healthy: backend task has initialized correctly & is processing requests
  - refusing connections: backend task is unresponsive
  - lame duck: the backend task is listening on its port & can serve, but is explicitly asking clients to stop sending requests
    * it broadcasts this state to all active clients, and inactive clients also find out via periodic health checks
    * simplifies clean shutdown since it doesn't need to return an error to incoming requests
    * any ongoing request started before this state is entered are executed normally

### Limiting the connections pool with subsetting

* __subsetting__: limiting the pool of potential backend tasks w/ which a client task interacts
  - each client in Google's RPC system maintains a pool of long-lived connections to its backends that it uses to send new requests
    + saves the resource & latency costs associated with establishing & tearing down connections
    + if a connection remains idle for a while, Google's RPC implementation switches it to a cheap "inactive" mode
      - cheaper since every connection requires some memory & CPU due to health checks at both ends
  - subset size is important, and varies based on the service's typical behavior
    + use larger subset size if:
      - number of clients is signif. smaller than # backends
      - there're frequent load imbalances w/i the client jobs (ie. one client sends many more tasks than others, or a client occasionally has a fan-out request)
    + need to handle resizes in the number of clients/backends w/ minimal connection churn, and without knowing the numbers in advance

### Load balancing policies

* __load balancing policies__: mechanisms used by client tasks to select which backend task in its subset receives a client request

* __Simple Round Robin__
  - can result in up to 2x in CPU consumption from the least to the most loaded task, which is really wasteful b/c:
    + small subsetting
      - all of its clients may not issue requests @ the same rate
    + varying query costs
      - the most expensive requests can consume 1000x+ more CPU than the cheapest requests
    + machine diversity
    + unpredictable performance factors
      - antagonistic neighbors: competition for shared resources (e.g. space in memory caches, bandwitch, etc.)
      - task restarts: tasks require signif. more resources for a few mins when they restart

* __Lead-loaded Round Robin__:
  - keep track of active # of requests in each backend task & use RR amongst the set of tasks w/ a minimal number of active requests
    + con: if a task is seriously unhealthy, it might start serving 100% errors, w/ very low latency
      - it's faster to return an "i'm unhealthy" error than it is to process a request
      - the unhealthy task is __sinkholing__ traffic because clients may sent a large amount of traffic to this task–thinking it's available.
        + avoidable by counting recent errors as if they were active requests
    + in practice, performs as badly as RR because:
      - active # of requests in each client doesn't include requests from other clients to the same backends
      - count of active # of requests may not be a good proxy for a backend's capabilities

* __Weighted Round Robin__:
  - each client keeps a capability score for each backend, and requests are distributed in a Round Robin fashion based on capabilities
  - capability score is adjusted based on the backend's current number of successful requests and at what utilization cost, with failures incurring a penalty

## Ch 21: Handling overload

* ways to gracefully handle overload:
  - serve degraded responses (responses that aren't as accurate/contain less data than normal responses, but are easier to compute)
    + use a local copy of results that may not be up-to-date but is cheaper to use

### The pitfalls of "Queries per Second"

* QPS constantly changes based on updates to client, end-user traffic, etc. and it isn't good to model load balancing over something that's ever changing

* better metric: measuring capacity directly in available resources to model a datacenter's capacity, which works better because:
  - in platforms w/ garbage collection, memory pressure naturally translates into increased CPU consumption
  - in other platforms, it's possible to provision remaining resources in such a way that they're very unlikely to run out before CPU runs out

### Per-customer limits

* i.e. provision resources by product (Gmail, Calendar, Android, etc.)

### Client-side throttling

* when a customer is out of quota, it's cheaper for a backend task to reject its requests quickly, and return a "customer is out of quota" error, than it is to serve a correct response
  - depends on how expensive the request is; if it's a simple lookup, it might be as expensive to reject it
  - client-side throttling addresses this problem

* __adaptive throttling__: each client tasks keeps track of requests & accepts for the last 2 minutes of its history
  - requests: number of requests attempted by the application layer
  - accepts: number of requests the backend accepts

### Criticality

* a request to a backend is associated with one of these 4 __criticality__ values:
  - `CRITICAL_PLUS`: will result in serious user-visible impact if they fail
  - `CRITICAL`: default value for requests; will result in less-severe user-visible impact
    + services are expected to provision enough capacity for all expected `CRITICAL` and `CRITICAL_PLUS` traffic
  - `SHEDDABLE_PLUS`: where partial unavailability is expected; default for batch jobs
  - `SHEDDABLE`: where frequent partial unavailability and occasional full unavailability is expected

### Utilization signals

* Google uses utilization signals based on the state local to the task
  - most useful signal: __executor load average__, which is based on the "load" in the process
    + count active (running + waiting) threads in the process

### Handling overload errors

* if a large subset of backend tasks in the datacenter are overloaded, requests shouldn't be retried, & errors should bubble up to the caller

* per-request retry budget of up to 3 attempts

* per-client retry budget is a ratio of requests to retries, and makes it so Google only retries if the ratio is under 10%

### Load from connections

* requiring periodic health checks on a very large number of clients that issue a very low rate of requests can be more expensive than actually serving the requests
  - thus should include recalculating the frequency of health checks in your algorithm
