# The Datacenter as a Computer

## Chapter 1: Introduction

* warehouse-scale computers (WSCs) have 1 program running on them (an Internet server, which may have more programs on it), consist of thousands of computing nodes, their network & storage subsystems, power distribution & conditioning equipment, & cooling systems; in a buildings
  - requirements of homogeneity, single-organization control, and enhanced focus on cost efficiency

* Network Attached Storage (NAS) deals exclusively with data management, and doesn't run compute jobs
  - provides high availability via replication / error correction capabilities
  - Solid State Drives (SSDs) can deliver I/O rates many orders of magnitude higher than disks, but are more expensive
  - different from DFS b/c it only stores, has redundancy and you can think of it as a single disk, whereas DFS uses a lot of commodity computers and uses their disks to be many parts of a whole

* go for rack-level networking locality to avoid using the uplinks to the cluster-level switch (~100x more expensive than commodity rack switches)
  - you get less bandwidth with off-rack disks via the shared rack uplinks, but much more disk storage

* thermal management allows CPUs to run closer to their maximum power envelope

## Chapter 2: Workloads and Software Infrastructure

* __platform-level software__: things expected to be in all individual servers to abstract the single machine's hardware to provide basic server-level services (the common firmware, kernel, OS distribution, & libraries)

* __cluster-level infrastructure__: collection of distributed systems software managing resources & providing services to clusters
  - eg, DFSs, schedulers & remote procedure call (RPC) libraries

* __application-level software__: software to implement a specific service
  - can be divided into online services & offline computations

* ways datacenters are different from the desktop/server model
  - _ample parallelism_: eg. coming from relatively independent records needing processing (eg. billions of Web pages, log lines, etc.), or thousands of requests
  - _workload churn_: easier to integrate changes and have shorter release cycles
  - _platform homogeneity_: small number of hardware & system software configurations deployed @ any time
    + simplifies cluster-lvl scheduling & load balancing, & reduces maintenance needs
  - _fault-free operation_: multiplicative effect of individual failure rates -> faults every few hrs

#### Performance and availability techniques

* __Reed-Solomon codes__: error correcting codes to allow recovery from data loss w/ less space overhead  than straight replication (advantage: availability & space savings)

* __sharding (partitioning)__: splitting huge data sets into smaller fragments and distributing them over a large number of machines (advntage: performance & availability)
  - microsharding helps load balancing & recovery

* __load balancing__: biasing the sharding policy to equalize work per server
  - requests are still not entirely predictable
  - microsharding makes this easier since small units of work can be changed to mitigate hotspots

* __health checking & watchdog timers__

* __integrity checks__: checking for data corruption

* __application-specific compression__: useful since decompression is order of magnitudes faster than a disk seek
  - better than a generic compression since you custom fit it to the data encoding & distribution of values

* __eventual consistency__ (of data)

* __centralized control__ is easier to implement than a DS w/ single master limits (advantage: performance)

* __canaries__: requests that can expose a system-crashing bug, as it is distributed to a large # of servers
  - prevent this w/ sending the request to 1-3 servers, iff that is successful, send it to the others

* __redundant execution and tail-tolerance__
  - why? parallel tasks completion can be held up by a small percentage of its subtasks

* virtualization is used to manage security & performance isolation of a customer's app
  - downside is performance, esp. w/ I/O-intensive workloads

#### Cluster-level infrastructure software

* resource management maps user tasks to hardware resources, enforces priorities & quotas, & has basic task management services

* some problems are common enough in large-scale services that targeted programming frameworks to simplify product development are created (eg. MapReduce, BigTable, Dynamo)
  - made to handle data partitioning, distribution, & fault tolerance

#### Application-level software

* hardware specialization sometimes isn't worth it b/c of the speed of workload churn, changing product requirements, & thus changing problem area

## Etc for Midterm

* __oversubscription__: when demand for servers exceeds the throughput the servers can serve
  - -> slower speeds as work/requests gets backed up
  - oversubscription factor: `amount_being_asked_for` / `amount_you_can_serve`
  - there isn't oversubscription w/i a rack b/c the bottleneck is the rack switch

* when designing system you need to expect that communications will fail or be slow
