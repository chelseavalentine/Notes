# Site Reliability Engineering

Notes taken reading Google's [Site Reliability Engineering](https://landing.google.com/sre/) book.

## Ch 17: Testing for reliability

> If you haven't tried it, assume it's broken.

### Types of software testing

* hierarchy of traditional tests (base to top): unit tests -> integration tests -> system tests
  - __unit test__: smallest & simplest form of software testing, limited to a separable unit of software (e.g. class/function)
  - __integration test__: testing the assembled software components, using dependency injection or what have you (example tool: Dagger)
  - __system tests__: largest scale test, run on an undeployed system, testing the end-to-end functionality of a system
    + __smoke tests__: testing very simple but critical behavior, also known as sanity testing
      - purpose: can short-circuit additional & more expensive testing
    + __performance tests__ serve to ensure that the system doesn't get much slower, use much more memory, etc. without notice
    + __regression tests__: testing against a gallery of rogue bugs that've historically plagued the system
  - __production tests__ interact w/ a live production system, are similar to black-box monitoring, and are sometimes collectively called black-box testing
  - __configuration tests__ test whether each configuration file by examining production to see how a particular binary is actually configured, and reports discrepancies against its config file
    + can be really simple, like a file diff of expected contents & file on production
    + config tests become more complex when the configuration does any of the following:
      - implicitly incorporates defaults that're built into the binary, so tests need to be versioned
      - passes through a preprocessor (e.g. bash into command-line flags), so the tests need to follow expansion rules
      - specifies behavioral context for a shared time, so tests depend on that runtime's release schedule
  - __stress test__: testing and finding the limits on a web service, e.g.:
    + how full can the database get before writes start to fail?
    + how many QPS can be sent to an app. server before it becomes overloaded, and requests fail? 
  - __canary test__: upgrading a subset of servers to a new config version, and then leaving them in an incubation period ("baking the binary"), and then progressively propagating that release to the rest of the servers, if there aren't unexpected variances

### Creating a test-and-build environment

* starting to write tests for a codebase without them is super overwhelming, so need to prioritize what can best benefit from testing, and which tests are easiest to write while providing the most value
  - e.g. test business-critical, mission-critical stuff, components that're in great use

### Testing at scale

* many tools that test for disaster, and are designed to operate offline, do the following:
  - compute a checkpoint state that's equivalent to cleanly stopping the service
  - push the checkpoint state to be loadable by existing nondisaster validation tools
  - support the usual barrier tools, which trigger the clean start procedure

* writing config files in interpreted languages is risky
  - more important to check for performance

* should write probe tests, split into 3 sets of requests:
  - known bad requests
  - known good requests that can be replayed against production
  - known good requests that can't be replayed against production

## Ch 18: Software engineering in SRE

### Why is software engineering within SRE important?

### Auxon case study: project background and problem space

#### Traditional capacity planning

1. collect demand forecasts
  - how many resources are needed; when and where they're needed
  - prediction for several quarters to years
2. device & build allocation plans
  - how much supply and in what locations?
3. review & sign off on a plan
  - is the forecast reasonable? does the plan line up with budgetary, product-level, & technical considerations?
4. deploy & configure resources

This plan, like most plans, should be adequately revised as the variables change.

### Intent-based capacity planning

* specifies the requirements, not the implementation

* __Intent-based Capacity Planning__ approaches this issue by programatically encoding the dependencies and parameters (__intent__) of a service's needs, & use that encoding to autogenerate an allocation plan that details which resources go to which service, in which cluster
  - allows autogeneration of a new plan when parameters change

* __intent__: the rational for how a service owner wants to run their service

* information we need in order to capture a service's intent:
  - dependencies
  - performance metrics
  - prioritization

* __Auxon__: Google's implementation of an intent-based capacity planning and resource allocation solution

* don't focus on perfection & purity of a solution, especially if the bounds of the problem aren't well known; launch and iterate

## Ch 19: Load balancing at the front-end

### Power isn't the answer

* Google uses __traffic load balancing__
  - take into account need for high throughput (e.g. video uploading) vs. low latency (e.g. searching)
    + on the global level, they send things requiring high throughput to less-utilized, perhaps further away, datacenters
    + on the local level, they focus on optimal resource utilization & protecting a single server from overloading
    + exceptions: could route requests to slightly further away datacenter to keep caches warm, or even a different region to avoid network congestion

### Load balancing using DNS

* problems with just letting client randomly pick an IP address
  - doesn't know which one is closest
    + mitigated if use an anycast address for authoritative nameservers, since DNS queries flow to the nearest address
      - instead of having end users talk directly to an authoritative nameserver, a recursive DNS server lies between

* implications of the DNS middleman on traffic management:
  - recursive resolution of IP addresses
  - nondeterministic reply paths
  - additional caching complications

* considerations
  - does the closest datacenter have the capacity to serve nearby users? is it experiencing network problems?
  - authoritative nameservers can't flush resolvers' caches, so need to use a relatively low TTL, and any DNS changes need to wait for caches to invalidate

### Load balancing at the virtual IP address

* Virtual IP addresses (VIPs) are shared across many devices, but the end user sees it as a single, regular IP address
  - allows hiding the implementation details, so can schedule upgrades/change the machine pool size, etc. without user knowing

* __network load balancer__ is a part of the VIP implementation, and it receives packets and forwards them to one of the machines behind the VIP
  - from there, there're many algorithms you can use to decide which machine gets the packet; e.g. lead loaded, round-robin, etc.
  - for stateful protocols, the balancer needs to keep track of all connections sent through it so that subsequent packets are sent to the same backend
    + how? use parts of the packet to create a connection ID, and then use the connection ID to select a backend
    + however, a backend could fail while a session is ongoing, and all goes to shit; need better solution

* better solution: __consistent hashing__ provides a relatively stable mapping algorithm even when backends are added or removed, leading to minimal disruption
  - so they use simple connection tracking, but fall back to consistent hashing when the system is under pressure

* another solution: __Direct Server Response (DSR)__: backend can send replies directly to the original sender, because you modify information on the data link later (layer 2 in OSI networking model) by changing the destination MAC address of the forwarding packet
  - allows balancer to leave all information in upper layers intact, so the backend receives the original source & destination IP addresses
  - saves a lot of work in the typical case (when user requests are small and replies are large), because only a small fraction of the traffic needs to traverse the load balancer
  - great b/c doesn't require keeping state on the load balancer device
  - not great because using layer 2 for internal load balancing incurs _serious_ disadvantages when deployed at scale, since all machines need to reach each other at the data link layer
    + not an issue if the network can support this connectivity & the number of machines doesn't grow excessively

* Google uses packet encapsulation:
  - network load balancer puts forwarded packet into another IP packet w/ Generic Routing Encapsulation (GRE), uses the backend's address as the destination, the backend processes the inner packet as if it were delivered directly to its network interface
  - allows the network load balancer & backend to be geographically independent
  - con: inflated packet size
    + can cause the packet to exceed the available Maximum Transmission Unit (MTU) size, and thus require fragmentation
      - but once the packet reaches the datacenter, can avoid fragmentation by using a larger MTU w/i the datacenter, but that requires a network supporting large Protocol Data Units
