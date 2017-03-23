# Site Reliability Engineering

Notes taken reading Google's [Site Reliability Engineering](https://landing.google.com/sre/) book.

## Ch 23: Managing critical state: Distributed consensus for reliability

* __distributed consensus__: an agreement among a group of processes
  - use distributed consensus systems that've been formally proven and tested thoroughly

### CAP Theorem

* __CAP Theorem__: a distributed system can't simultaneously have all 3 of the following:
  1. consistency (consistent views of the data at each node)
  2. availability (availability of the data at each node)
  3. tolerance (tolerance to network partitions)

* there are distributed datastore technologies supporting BASE semantics
  - __BASE (Basically Available, Soft state, and Eventual consistency)__ semantics are useful for large volumes of data & transactions that would be much more costly, & perhaps infeasible, with datastores supporting ACID semantics
  - rely on multimaster replications, committing writes to different processes concurrently, with a mechanism to resolve conflicts (e.g. latest timestamp wins, a.k.a. __eventual consistency__)
    + issues w/ eventual consistency b/c there's inevitable __clock drift__ in distributed systems, or network partitioning

### Motivating the use of consensus: Distributed systems coordination failure

* network partitions could be the result of:
  - a very slow network
  - some, but not all, messages being dropped
  - throttling occurring in one direction, but not the other direction

* many distributed systems problems turn out to be because of different versions of distributed consensus, including master election, group membership, etc.

### How distributed consensus works

* distributed software systems should use __asynchronous distributed consensus__, since it has potentially unbounded delays in message passing
  - it's impossible to solve the asynchronous distributed consensus in bounded time (FLP impossibility result) due to an unreliable network
  - use synchronous consensus for real-time systems, w/ dedicated hardware ensuring messages will be passed w/ timing guarantees
  - can either by __crash-fail__ (nodes never return to the system), or __crash-recover__
    + crash-recover is more useful since most problems are due to slow network, restarts, etc.

* __Byzantine failure__: a process passes incorrect messages due to a bug or malicious activity, are comparatively costly to handle, & are encountered less often

#### Paxos overview: An example protocol

* __Paxos__ operates as a sequence of uniquely numbered proposals which may be accepted by a majority of the system's processes
  - imposes strict ordering on all system operations

* 1st phase: proposer sends a sequence # no acceptors, who agree to accept the proposal iff it hasn't seen a proposal w/ a higher sequence number
  - if a proposer receives agreement from a majority, it can commit the proposal via a commit message w/ a value
  - acceptors need to write a journal to persistent storage whenever they accept a proposal, so they can honor their guarantees after restarting

* Paxos' limitation: any given node may not have a complete view of the set of values that've been agreed to

### System architecture patterns for distributed consensus

* distributed consensus algorithms become useful as you add higher-level system components (e.g. datastores, configuration stores, queues, locking, & leader election services) to provide the functionality they don't address

#### Reliable replicated state machines (RSMs)

* a __replicated state machine (RSM)__: a system that executes the same set of operations, in the same order, on several processes
  - used to build config storages, locking, & leader election, because several papers show that any deterministic program can be implemented as a highly available replicated service by being implemented as an RSM
  - operations are ordered globally via a consensus algorithm
  - RSMs may need to synchronize state from peers, because every member of a consensus group doesn't belong to each of the other consensus groups
    + can use a __sliding-window protocol__ to reconcile state b/t peer processes in an RSM

* can have highly available processing using leader election
  - appropriate where service leader's work can be performed by 1 processes, or is sharded
  - replicate a service, & then use leader election to ensure only 1 leader is working at any point in time

* a __barrier__, to block processes from continuing until all have reached it, can be implemented as an RSM s.t. there isn't a single point of failure, as there would be if implemented by a single coordinator process

* can also create distributed locks by implementing them as RSMs

#### Reliable distributed queuing and messaging

* instead of removing from queues, a __lease system__ is more appropriate, because we need to ensure everything on the queue is processed
  - lease system: use renewable leases w/ timeouts instead of indefinite locks, in case the process dies
  - downside of queueing-based systems: queue dies? system can't operate! Thus, implement the queue as an RSM to minimize risk

* __atomic broadcast__ allows messages to be received reliably & in the same order by all participants

* __queuing-as-work-distribution__ pattern uses a queue as a load balancing device, & can be considered point-to-point messaging

* publish-subscribe systems can be used to implement coherent distributed caches

### Distributed consensus performance

* it isn't true that consensus algorithms are too slow + costly for systems requiring high throughput & low latency

* workload can also vary due to consistency semantics required for read operations

#### Multi-Paxos protocol: Detailed message flow

* the __Multi-Paxos__ protocol uses a __strong leader process__, meaning that unless a leader hasn't been elected yet, or some failure occurs, it only requires one round trip from the proposer to a quorum of acceptors to reach consensus

* need to have the right timeouts & backoff strategies to ensure all processes don't attempt to become the leader @ the same time (-> dueling proposers -> no proposals accepted -> livelock) in the absence of a leader

* to guarantee that a read is up-to-date, do one of the following:
  - perform a read-only consensus operation
  - read data from replica guaranteed to be most up-to-date (the leader can provide this guarantee)
  - use quorum leases (some replicas are granted a lease on all/part of a data in the system, allowing strongly consistent local reads at the cost of some write performance)
    + lease is for a specific (usually brief) period of time, since any operation changing the state of that data needs to be acknowledged by all replicas in the read quorum
    + if any of the replicas becomes unavailable, data can't be modified until the lease expires

#### Distributed consensus performance and network latency

* it isn't practical for clients to keep persistent connections to consensus clusters open, because TCP/IP connections consume resources (e.g. file descriptors, keepalive traffic) 
  - solution: use a pool of regional proxies, which hold persistent TCP/IP connections, in order to avoid the setup overhead over long distances
    + proxies also help encapsulate sharding, load balancing strategies, & discovery of cluster members and leaders

* using a stable leader could allow read optimizations, but has the following drawbacks:
  - operations to change state must be sent to leader, & there's increased latency for clients located farther from the leader
  - leader process's outgoing network bandwidth is the system bottleneck
  - if leader's machine has performance problems, the throughput of the entire system is reduced

* batching -> increased system throughput, but replicas are idle as they await replies to their messages
  - solution? pipeline so you can allow multiple proposals to be in-flight at once, so using a sliding-window approach instead

### Deploying distributed consensus-based systems

* can use majority quorums, i.e. `2f + 1` replicas can tolerate `f` failures, or if need Byzantine fault tolerance, use `3f + 1` replicas to tolerate `f` failures

* if a consensus system uses enough replicas that it can't form a quorum, the system is unrecoverable since the durable logs of at least 1 of the missing replicas can't be accessed

* decision for number of replicas is a trade-off between: the need for reliability, frequency of planned maintenance affecting the system, risk, performance, and cost

#### Location of replicas

* a __failure domain__: the set of components of a system that can become unavailable as a result of a single failure, e.g.:
  - a physical machine
  - a rack in a datacenter served by 1 power supply
  - several racks in a datacenter served by 1 piece of networking equipment
  - a fiber optic cable cut can render a datacenter unavailable
  - a set of datacenters in a geographic area can be affected by a single natural disaster

#### Capacity and load balancing

* adding more replicas to a majority quorum system can potentially decrease system availability, since there's a percentage of available replicas needed for the system to stay alive

#### Quorum composition

* spread replicas as evenly as possible to try to create even lag, and to distribute traffic as evenly as possible

### Monitoring distributed consensus systems

* things to monitor:
  - # members running in each consensus group, and their state (healthy?)
  - persistently lagging replicas
  - whether a leader exists
  - number of leader changes
  - consensus transaction number (are we making progress?)
  - number of proposals seen; number of proposals agreed upon
  - throughput & latency
  - additionally to troubleshoot performance issues:
    + latency distributions for proposal acceptance
    + distributions of network latencies observed b/t diff parts of the system
    + number of time acceptors spend on durable logging
    + the overall bytes accepted per second in the system
