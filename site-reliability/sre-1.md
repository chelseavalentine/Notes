# Site Reliability Engineering

Notes taken reading Google's [Site Reliability Engineering](https://landing.google.com/sre/) book.

## Introduction

* UNIX system internals and networking (layer 1 to layer 3)
* SRE team responsibilities: availability, latency, performance, efficiency, change management, monitoring, emergency response, + capacity planning
  - change management:
    + implementing progressive rollouts; rolling back changes safely when problems arise; quickly & accurately detecting problems
  - capacity planning requires having:
    + an accurate organic demand forecast, extending beyond the lead time required for acquiring capacity
    + accurate incorporation of inorganic demand sources into the demand forecast
    + regular load testing of the system too correlate raw capacity (servers, disks, etc.) to service capacity
  - provisioning:
    + spinning up a new instance/location
    + making significant modifications to existing systems (e.g. config files, load balancers, networking), and validating that the new capacity performs and delivers correct results (super risky)
 
## Chapter 2: the production environment at Google

 * basic definitions
  - __machines__: a piece of hardware (e.g. a VM)
  - __server__: a piece of software that implements a service

 * describing a datacenter:
  - tens of machines placed in a _rack_, where _racks_ stand in a _row_, with 1+ rows forming a _cluster_, and a _datacenter_ building housing multiple clusters, with multiple datacenter buildings forming a campus

* __Borg__: Google's distributed cluster operating system, which manages and maintains jobs (includes restarting them, allocating requested resources to jobs, etc.)
  - Kubernetes = open source Container Cluster orchestration framework

* database-like services
  - __Bigtable__: NoSQL db system that can handle databases petabytes in size
    + sparse, distributed, persistent multidimensional sorted map that's indexed by row key, column key, and timestamp
    + each value in the map is an uninterpreted array of bytes
    + supports eventually consistent, cross-datacenter replication
  - __Spanner__: offers SQL-like and real consistency

* uses 3 levels of load balancing
  - geographic load balancing for DNS requests
  - at a user service level (e.g. YouTube, Google Maps, etc.)
  - at the Remote Procedure Call (RPC) level

* __Chubby__ lock service maintains locks via a filesystem-like API
  - uses Paxos protocol for asynchronous Consensus
  - selects which replica proceeds, in the instance that a service has multiple replicas of a job running for reliability, but only one replica can perform actual work

* __Borgmon__ scrapes metrics from monitored servers to...
  - set up alerting for acute problems
  - compare behavior before & after updates
  - examine how resource consumption behavior evolves over time
  
* RPC calls are preferred even when a call to a subroutine in the local program needs to be made
  - this way, can refactor the call into a different server if need more modularity, or when the server's codebase grows

## Chapter 3: embracing risk

### Managing risk

* since opportunity & costs a lot to increase reliability by a little, goal is to aim for an availability, instead of just endlessly trying to increase availability

### Measuring service risk

* you can measure availability by time, or by some other metric of success
  - e.g. for Google, it's number of successful requests

### Risk tolerance of services

* value of improved availability = [revenue gained] * [proposed increase in availability, as a decimal number]
  - the cost should be <= the value to be worth it

* with service error rates lower than typical ISP error rate, the service errors can get comparatively drowned out by internet connection issues

* latency depends on product
  - e.g. AdSense (contextual ads on 3rd party sites) latency can be a function of the 3rd party site's loading time

### Identifying the risk tolerance of infrastructure services

* can satisfy competing constraints (high availability vs. high throughput) by offering different levels of service

### Motivation for error budgets

* __canarying__: testing a new release on a small subset of a typical workload
  - concerns: how long to wait? how big should the canary be?

* as long as the measured uptime is above the SLO (and thus have an error budget remaining), can push new releases

## Chapter 4: Service Level Objectives

### Service level terminology

* __Service Level Indicator (SLI)__: quantitative measure of the service
  - e.g. request latency, error rate ([all requests received]/[system throughput]), availability, yield (fraction of well-formed requests that succeed)
    + __durability__: the likelihood that data will be retained over a long period of time

* __Service Level Objective (SLO)__: target value/range of values for a service
  - usually it's like SLI ≤ [target], or [lower bound] ≤ SLI ≤ [upper bound]

* sometimes you need to make sure that users aren't overly dependent on your service
  - e.g. Google's Chubby rarely ever went down, so people depended on it as if its availability would be a sure thing
    + Google's solution to that was to make sure that Chubby met, but didn't exceed its SLO

* __Service Level Agreement (SLA)__: explicit/implicit contract w/ users that includes consequences of meeting/missing the SLOs they contain
  - can be a financial consequence (e.g. rebate, penalty)

### Indicators in practice

* choosing too many indicators usually is at the cost of not being able to pay enough attention to the most important indicators
  - too few => system unexamined

* example indicators for types of services:
  - user-facing systems: availability, latency, & throughput
  - storage systems: latency, availability, & durability
  - big data systems: throughput, & __end-to-end latency__ (how long it takes the data to progress from ingestion to completion)

* all systems should care about __correctness__

* should measure metrics both on server- and client-side

* 99th/99.9th percentile = plausible worst-cast value; 50th percentile = typical case

#### Standardize indicators

* should standardize indicators so you don't need to include units each time, e.g.:
  - aggregation intervals: "averaged over 1 minute"
  - aggregation regions: "all the tasks in a cluster"
  - how frequently measurements are made: "every 10 secs"
  - which requests are included: "HTTP GETs from black-box monitoring jobs"
  - how the data is acquired: "through monitoring, measured at the server"
  - data-access latency: "time to last byte"

### Objectives in practice

* example objectives:
  - 99% of `Get` RPC calls will complete in less than 100ms
    + 99% (averaged over 1 minute) of `Get` RPC calls will complete in less than 100ms (measured across all backend servers)

* can make multiple SLO targets based on % and time, and for each class of workload

* things to keep in mind while choosing SLOs:
  - don't pick targets based on current performance
  - keep simple targets
  - avoid absolute targets (e.g. scaling infinitely lol)
  - have as few SLOs as possible
  - perfection can wait (start with a loose target to tighten, rather than an overly strict target that has to be relaxed because it's overzealous)
    + remember that SLOs can be used as a guide for product teams (don't want to exhaust them, but also don't want to create a lax product)

* don't overachieve
  - e.g. throttle requests, design system so it isn't faster under light loads, etc. to avoid over-dependence
