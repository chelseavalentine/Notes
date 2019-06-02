# Site Reliability Engineering

Notes taken reading Google's [Site Reliability Engineering](https://landing.google.com/sre/) book.

## Introduction

- UNIX system internals and networking (layer 1 to layer 3)
- SRE team responsibilities: availability, latency, performance, efficiency, change management, monitoring, emergency response, + capacity planning
  - change management:
    - implementing progressive rollouts; rolling back changes safely when problems arise; quickly & accurately detecting problems
  - capacity planning requires having:
    - an accurate organic demand forecast, extending beyond the lead time required for acquiring capacity
    - accurate incorporation of inorganic demand sources into the demand forecast
    - regular load testing of the system too correlate raw capacity (servers, disks, etc.) to service capacity
  - provisioning:
    - spinning up a new instance/location
    - making significant modifications to existing systems (e.g. config files, load balancers, networking), and validating that the new capacity performs and delivers correct results (super risky)

## Chapter 2: the production environment at Google

- basic definitions

- __machines__: a piece of hardware (e.g. a VM)
- __server__: a piece of software that implements a service

- describing a datacenter:

- tens of machines placed in a _rack_, where _racks_ stand in a _row_, with 1+ rows forming a _cluster_, and a _datacenter_ building housing multiple clusters, with multiple datacenter buildings forming a campus

- __Borg__: Google's distributed cluster operating system, which manages and maintains jobs (includes restarting them, allocating requested resources to jobs, etc.)
  - Kubernetes = open source Container Cluster orchestration framework
- database-like services
  - __Bigtable__: NoSQL db system that can handle databases petabytes in size
    - sparse, distributed, persistent multidimensional sorted map that's indexed by row key, column key, and timestamp
    - each value in the map is an uninterpreted array of bytes
    - supports eventually consistent, cross-datacenter replication
  - __Spanner__: offers SQL-like and real consistency
- uses 3 levels of load balancing
  - geographic load balancing for DNS requests
  - at a user service level (e.g. YouTube, Google Maps, etc.)
  - at the Remote Procedure Call (RPC) level
- __Chubby__ lock service maintains locks via a filesystem-like API
  - uses Paxos protocol for asynchronous Consensus
  - selects which replica proceeds, in the instance that a service has multiple replicas of a job running for reliability, but only one replica can perform actual work
- __Borgmon__ scrapes metrics from monitored servers to...
  - set up alerting for acute problems
  - compare behavior before & after updates
  - examine how resource consumption behavior evolves over time
- RPC calls are preferred even when a call to a subroutine in the local program needs to be made
  - this way, can refactor the call into a different server if need more modularity, or when the server's codebase grows

## Chapter 3: embracing risk

### Managing risk

- since opportunity & costs a lot to increase reliability by a little, goal is to aim for an availability, instead of just endlessly trying to increase availability

### Measuring service risk

- you can measure availability by time, or by some other metric of success
  - e.g. for Google, it's number of successful requests

### Risk tolerance of services

- value of improved availability = [revenue gained] * [proposed increase in availability, as a decimal number]
  - the cost should be <= the value to be worth it
- with service error rates lower than typical ISP error rate, the service errors can get comparatively drowned out by internet connection issues
- latency depends on product
  - e.g. AdSense (contextual ads on 3rd party sites) latency can be a function of the 3rd party site's loading time

### Identifying the risk tolerance of infrastructure services

- can satisfy competing constraints (high availability vs. high throughput) by offering different levels of service

### Motivation for error budgets

- __canarying__: testing a new release on a small subset of a typical workload
  - concerns: how long to wait? how big should the canary be?
- as long as the measured uptime is above the SLO (and thus have an error budget remaining), can push new releases

## Chapter 4: Service Level Objectives

### Service level terminology

- __Service Level Indicator (SLI)__: quantitative measure of the service
  - e.g. request latency, error rate ([all requests received]/[system throughput]), availability, yield (fraction of well-formed requests that succeed)
    - __durability__: the likelihood that data will be retained over a long period of time
- __Service Level Objective (SLO)__: target value/range of values for a service
  - usually it's like SLI ≤ [target], or [lower bound] ≤ SLI ≤ [upper bound]
- sometimes you need to make sure that users aren't overly dependent on your service
  - e.g. Google's Chubby rarely ever went down, so people depended on it as if its availability would be a sure thing
    - Google's solution to that was to make sure that Chubby met, but didn't exceed its SLO
- __Service Level Agreement (SLA)__: explicit/implicit contract w/ users that includes consequences of meeting/missing the SLOs they contain
  - can be a financial consequence (e.g. rebate, penalty)

### Indicators in practice

- choosing too many indicators usually is at the cost of not being able to pay enough attention to the most important indicators
  - too few => system unexamined
- example indicators for types of services:
  - user-facing systems: availability, latency, & throughput
  - storage systems: latency, availability, & durability
  - big data systems: throughput, & __end-to-end latency__ (how long it takes the data to progress from ingestion to completion)
- all systems should care about __correctness__
- should measure metrics both on server- and client-side
- 99th/99.9th percentile = plausible worst-cast value; 50th percentile = typical case

#### Standardize indicators

- should standardize indicators so you don't need to include units each time, e.g.:
  - aggregation intervals: "averaged over 1 minute"
  - aggregation regions: "all the tasks in a cluster"
  - how frequently measurements are made: "every 10 secs"
  - which requests are included: "HTTP GETs from black-box monitoring jobs"
  - how the data is acquired: "through monitoring, measured at the server"
  - data-access latency: "time to last byte"

### Objectives in practice

- example objectives:
  - 99% of `Get` RPC calls will complete in less than 100ms
    - 99% (averaged over 1 minute) of `Get` RPC calls will complete in less than 100ms (measured across all backend servers)
- can make multiple SLO targets based on % and time, and for each class of workload
- things to keep in mind while choosing SLOs:
  - don't pick targets based on current performance
  - keep simple targets
  - avoid absolute targets (e.g. scaling infinitely lol)
  - have as few SLOs as possible
  - perfection can wait (start with a loose target to tighten, rather than an overly strict target that has to be relaxed because it's overzealous)
    - remember that SLOs can be used as a guide for product teams (don't want to exhaust them, but also don't want to create a lax product)
- don't overachieve
  - e.g. throttle requests, design system so it isn't faster under light loads, etc. to avoid over-dependence

## Ch 5: Eliminating Toil

### Toil defined

- __toil__: work tied to running a production service, which tends to be manual, repetitive, automatable
  - is interrupt-driven and reactive, rather than strategy-driven and proactive
  - O(N) with service growth

### What qualifies as engineering?

- software engineering:
  - writing/modifying code, design & documentation work
    - e.g. automation scripts, creating tools/frameworks, adding service features for scalability and reliability, modifying infrastructure code to make it more robust
- systems engineering:
  - configuring production systems, modifying configurations, or documenting systems in a way that produces lasting improvements from a one-time effort
    - e.g. monitoring setup & updates, load balancing configuration, server configuration, tuning of OS parameters, load balancer setup, & consulting on architecture, design, and productionization for developer teams
- __overhead__: administrative work not directly tied to running a service (e.g. hiring, HR paperwork, team/company meetings, etc.)

## Ch 6: Monitoring distributed systems

- definitions
  - __white-box monitoring__: monitoring based on metrics exposed by system internals (includes logs, interfaces, or HTTP handler emitting internal statistics)
  - __black-box monitoring__: testing externally visible behavior as a user would see it
- why monitor?
  - analyzing long-term trends
  - comparing over time or experiment groups (trying out new technologies) 
  - conducting ad hoc retrospective analysis (i.e. debugging)
    - e.g. trying to see why latency increased
- monitoring systems should address the what and why of things broken
- black-box vs. white-box monitoring
  - white-box monitoring allows you to detect imminent problems, whereas black-box problems are occurring

### Four golden signals to monitor

- __latency__: time it takes to service a request
  - distinguish between latency for successful requests & for failed requests
- __traffic__: amount of demand being placed on the system, measured in a high-level system-specific metric
- __errors__
- __saturation__: utilization of resources
  - includes predictions of impending saturation (e.g. database will fill in X hours)
  - e.g. measuring latency over small windows (e.g. 1 min), can signal saturation early
- the frequency of monitoring depends on the availability you're trying to reach
- remove data collection, aggregation, and alerting configuration if it's rarely used; same for signals that aren't used for anything

### Tying these principles together

- does the rule detect an otherwise undetected condition that's urgent, actionable, and actively or imminently user visible?
- every page response should require intelligence to react
- pages should be about a novel problem/event that hasn't been seen before

## Ch 7: The evolution of automation at Google

- important concerns to resolve when configuring clusters
  - are all of the service's dependencies available and correctly configured?
  - were all configurations and packages consistent with other deployments?
- aspects of automation processes:
  - competence (their accuracy)
  - latency (how quickly all steps are executed once initiated)
  - relevance (portion of real-world process covered by automation)
- Borg turned cluster management into something where you could make API calls to a central coordinator
  - gave additional efficiency, flexibility (e.g. machines can schedule batch __and__ user-facing tasks), & reliability

## Ch 8: Release engineering

### The role of a release engineer

- understanding of source code management, compilers, build configuration languages, automated build tools, package managers, & installers
- release engineers & SREs work together to develop strategies for canarying changes, pushing out new releases w/o service interruption, & rolling back features demonstrating problems

### Philosophy

#### Self-service model

- develop best practices & tools s.t. product teams can be self-sufficient/control + run their own release processes

#### High velocity

- frequent releases means less changes between versions, and can test/troubleshoot easier

#### Hermetic builds

- __hermetic build__: a build insensitive to the libraries and other software installed on the build machine
  - builds depend on known versions of build tools (e.g. compilers) and dependencies (e.g. libraries)

### Continuous build and deployment

- __Rapid__: Google's automated release system, which uses several Google technologies to provide a scalable, hermetic, and reliable release system
  - building: Google uses __Blaze__ (since it supports building binaries from many languages
  - branches: the main branch of the source code tree (mainline) isn't used, they branch off it and cherry pick the changes for a release, so they know the exact contents of each release
  - testing: a continuous test system runs unit tests upon each change submission
  - packaging: uses Midas Package Manager (MPM) to distribute software to Google production machines

### Configuration management

- methods of managing the distribution of config files
  - using the mainline
    - allows you to decouple the binary releases and configuration changes
    - but can lead to a skew between the checked-in version and the running version, since you need to update jobs to pickup config changes
  - including config files and binaries in the same MPM package
    - works when there're few configuration files, or the project change with each release cycle
    - limits flexibility by tightly coupling binary & config files
    - simplifies deployment since only need to install one package
  - packaging config files into MPM "configuration packages"
    - allows changing each config and binary package independently
  - reading config files from an external store
    - good for projects with config files that need to change frequently or dynamically (while the binary is running)
    - stored in Chubby, Bigtable, or their source-based filesystem

## Ch 9: Simplicity

- __exploratory coding__: writing code w/ an intended expiration date, allowing you to be more liberal with test coverage & release management since it's never shipped
- every line of code changed/added creates the potential for a bug/defect introduction
- it's poor practice to create/put a "util" or "misc" binary into production

## Part III: Practices

- service reliability hierarchy (most basic -> most advanced):
  - monitoring, incident response, postmortem/root cause analysis, testing + release procedures, capacity planning, development, product

## Ch 10: Practical alerting from time-series data

### Time-series monitoring outside of Google

- Borgmon uses white-box testing by requiring a common data exposition format, which enables mass data collection with low overheads
  - data is used for creating alerts & rendering charts
  - can collect from other Borgmon, allowing a hierarchy of information about the service (e.g. cluster-level, global-level, etc)

### Exporting variables (varz)

- each of the major languages @ Google has a matching exported variable interface implementation, which automatically registers w/ the HTTP server built into every Google binary

### Collection of exported data

- Borgmon instances are configured with a list of targets by using one of many name resolution methods
  - target list can be dynamic, so using service discovery allows the monitoring to scale w/o maintenance
- varz isn't similar to __Simple Network Management Protocol (SNMP)__, which is designed to have minimal transport requirements and continue working when most other network applications fail
  - Borgmon allows engineers to write smarter alerting rules by using collection failure itself as a signal

### Storage in the [time-series arena](https://landing.google.com/sre/book/chapters/practical-alerting.html#fig_structure_of_timeseries)

- data points take on the form `(timestamp, value)`, stored in chronological lists called __time-series__, each labelled uniquely, with a label of the form `name=value`
  - the variable expression for the name of a time-series (labelset) is a bunch of key-value pairs, e.g.:
    `{var=[variable name], job=[job], service=[service], zone=[location]}`
- when the time-series arena (table holding all of the collected time-series) gets full, the oldest entries are evicted
  - periodically, in-memory state is archived to an external Time-Series Database (TSDB), so Borgman could query TSDB for older data

### Rule evaluation

- _Borgmon rules_ just consist of simple algebraic expressions computing time-series from other time-series

### Alerting

- Borgmon is connected to a centrally-run service, the Alertmanager, whose responsibilities include:
  - inhibiting certain alerts when others are active
  - deduplicating alerts from multiple Borgmon that have the same labelsets
  - fan-in or fan-out alerts based on their labelsets when multiple alerts with similar labelsets fire

### Sharding the monitoring topology

- time-series data is streamed between Borgmon, as opposed to using the text-based varz format, which saves CPU time and network bytes
- typical deployment: 2+ global Borgmon for top-level aggregation, & 1 Borgmon per datacenter to monitor all the jobs running at that location

### Black-box monitoring

- shortcomings of Borgmon, a white-box monitoring system, include:
  - not being able to see what users see, e.g. failure because of a DNS error
    - this issue is covered via Prober, which runs protocol checks against a target, and can integrate with both Borgmon and the Alertmanager

### Maintaining the configuration

- Borgmon provides a way to build extensive unit & regression tests by synthesizing time-series data, so you can see whether rules behave as intended

## Ch 11: Being on-call

### Feeling safe

- most important on-call resources:
  - clear escalation paths
  - well-defined incident-management procedures
  - a blameless postmortem culture

## Ch 12: Effective troubleshooting

### Theory

- __hypothetico-deductive method__ (? lol): given a set of observations about a system, and a theoretical basis for understanding system behavior, we iteratively hypothesize potential causes for the failure and try to test these hypotheses
- troubleshooting process: [1] problem report -> [2] triage -> [3] examine -> [4] diagnose -> [5] test/treat -> [6] cure ([diagram here](https://landing.google.com/sre/book/chapters/effective-troubleshooting.html#xref_troubleshooting_process))
- common troubleshooting pitfalls:
  - looking at irrelevant symptoms or misunderstanding the meaning of system metrics
  - misunderstanding how to change the system, its inputs, or its environment, so as to safely and effectively test hypotheses
  - coming up with wildly improbable theories about what's wrong, or latching onto causes of past problems
  - hunting down spurious correlations that are actually coincidences, or are correlated with shared causes

### In practice

- problem report: every problem starts with an automated report/someone saying there's a problem
  - effective reports tell you: [1] the expected behavior, [2] the actual behavior, and [3] how to reproduce the behavior
- first step in your course of action should be to make the system work as well as it can under the circumstances
  - e.g. diverting traffic to working clusters, preventing cascading failure, or disabling subsystems to lighten the load
- need to be able to examine every component in a system in order to understand if it's behaving correctly
  - monitoring and logging are invaluable for this
    - text logs are helpful for reactive, real-time debugging, while storing logs in a structured binary format allows you to conduct retrospective analysis with much more information
    - great to have multiple verbosity levels, with ways to increase these levels in real-time, so you can examine any/all operations in detail without having to restart the process
- you can look at connections _between_ components (the data flowing between them) to determine whether a given component is working properly
  - for small-enough systems, you can examine the correctness from end to beginning
  - for large systems, it's probably better to split the system in half and examine communication paths between components on one side and the other and repeatedly determining which half is working properly
- ask "what", "where", and "why" the malfunctioning system is do whatever it's doing
- figure out when last the system was working
- finally, test and treat your hypotheses, keeping in mind that:
  - ideal tests have mutually exclusive alternatives, so that it can rule out a group of hypotheses
  - perform tests in decreasing order of likelihood, considering the possible risks to the system from the test
  - an experiment may provide misleading results due to confounding factors
  - active tests may have side effects that change future test results
    - e.g. using more CPUs to make operations faster, but encounter more data races; turning on verbose logging, but can't tell whether system is getting slower because of logging
  - some tests may not be definitive, only suggestive

### Negative results are magic

- experiments with negative results are conclusive
- tools and methods can outlive the experiment and inform future work

### Making troubleshooting easier

- build observability – with both white-box metrics and structured logs – into each component from the ground up
- design systems with well-understood and observable interfaces between components

## Ch 13: Emergency response

### Test-induced emergency

- Google has scheduled disaster & emergency testing where SREs break the system, and observe how they fail, in order to make changes to improve reliability and prevent future failures from recurring
  - can help identify weaknesses or hidden dependencies

### Change-induced emergency

- emergencies induced by changes to configurations which result in unexpected & undesired behavior

### Process-induced emergency

- emergencies induced by processes (e.g. automation)

### Learn from the past. Don't repeat it.

- keep a history of outages
- ask the big, sometimes improbable, "what if..." questions
- encourage proactive testing

## Ch 14: Managing incidents

### Elements of incident management process

- everyone involved in the incident needs to have their own exclusive role, thereby separating responsibilities
  - need more staff and to re-delegate if any one person is overloaded
  - roles include:
    - incident commander assigns responsibilities according to need & priority; holds all positions that they haven't delegated; if appropriate, has ability to remove roadblocks preventing Ops from working most effectively
    - ops lead works with the commander by applying ops tools; the ops team is the only group modifying the system during the incident
    - communicator issues periodic updates to the incident response teams & stakeholders (usually via emails); may also keep the incident document accurate & updated
    - planner supports Ops by dealing w/ long-term issues, e.g. filing bugs, ordering food, arranging handoffs, and tracking how the system has diverged so it can be reverted upon incident resolution
- interested parties need to know where to interact w/ the commander (e.g. "War Room")
- commander needs to keep a living incident document; should be editable by several people
- need to perform a clear, live handoff of the commander role at the end of the current commander's  workday, and change should be communicated to incident party

### When to declare an incident

- do you need to involve a second team in fixing the problem?
- is the outage visible to customers?
- is the issue unsolved even after an hour's concentrated analysis?

## Ch 15: Postmortem culture - Learning from failure

### Google's postmortem philosophy

- writing a postmortem is a learning opportunity for the entire company
- postmortem triggers:
  - user-visible downtime or degradation beyond a certain threshold
  - data loss of any kind
  - on-call engineer intervention (release rollback, rerouting of traffic, etc.)
  - a resolution time above some threshold
  - a monitoring failure (which usually implies manual incident discovery)
  - a stakeholder requesting a postmortem for an event
- postmortems should:
  - identify contributing causes 
  - not indict any individuals or teams for bad/inappropriate behavior
  - calls out where and how services can be improved
  - not identify who the end-user(s) is/are

### Collaborate and share knowledge

- tools for sharing postmortems should include the following features:
  - real-time collaboration
  - an open commenting/annotation system
  - email notifications
- review criteria in publishing a postmortem:
  - was the key incident data collected for posterity?
  - are the impact assessments complete?
  - was the root cause sufficiently deep?
  - is the action plan appropriate and are resulting bug fixes at appropriate priority?
  - did we share the outcome with relevant stakeholders?

## Ch 16: Tracking outages

- all alert notifications for SRE share a central replicated system tracking whether a human has acknowledged receipt of a notification
  - system escalates to next destination if it hasn't been acknowledged after a configured interval
- "_Outalator"_ is Google's outage tracker, which passively receives all alerts sent by monitoring systems, and allows them to annotate, group, and analyze the data
  - can aggregate multiple alerts into a single incident
  - can tag notifications, e.g. tag something as a false-positive alert, test event, mistargeted email; tag its cause, action, etc.

## Ch 17: Testing for reliability

> If you haven't tried it, assume it's broken.

### Types of software testing

- hierarchy of traditional tests (base to top): unit tests -> integration tests -> system tests
  - __unit test__: smallest & simplest form of software testing, limited to a separable unit of software (e.g. class/function)
  - __integration test__: testing the assembled software components, using dependency injection or what have you (example tool: Dagger)
  - __system tests__: largest scale test, run on an undeployed system, testing the end-to-end functionality of a system
    - __smoke tests__: testing very simple but critical behavior, also known as sanity testing
      - purpose: can short-circuit additional & more expensive testing
    - __performance tests__ serve to ensure that the system doesn't get much slower, use much more memory, etc. without notice
    - __regression tests__: testing against a gallery of rogue bugs that've historically plagued the system
  - __production tests__ interact w/ a live production system, are similar to black-box monitoring, and are sometimes collectively called black-box testing
  - __configuration tests__ test whether each configuration file by examining production to see how a particular binary is actually configured, and reports discrepancies against its config file
    - can be really simple, like a file diff of expected contents & file on production
    - config tests become more complex when the configuration does any of the following:
      - implicitly incorporates defaults that're built into the binary, so tests need to be versioned
      - passes through a preprocessor (e.g. bash into command-line flags), so the tests need to follow expansion rules
      - specifies behavioral context for a shared time, so tests depend on that runtime's release schedule
  - __stress test__: testing and finding the limits on a web service, e.g.:
    - how full can the database get before writes start to fail?
    - how many QPS can be sent to an app. server before it becomes overloaded, and requests fail? 
  - __canary test__: upgrading a subset of servers to a new config version, and then leaving them in an incubation period ("baking the binary"), and then progressively propagating that release to the rest of the servers, if there aren't unexpected variances

### Creating a test-and-build environment

- starting to write tests for a codebase without them is super overwhelming, so need to prioritize what can best benefit from testing, and which tests are easiest to write while providing the most value
  - e.g. test business-critical, mission-critical stuff, components that're in great use

### Testing at scale

- many tools that test for disaster, and are designed to operate offline, do the following:
  - compute a checkpoint state that's equivalent to cleanly stopping the service
  - push the checkpoint state to be loadable by existing nondisaster validation tools
  - support the usual barrier tools, which trigger the clean start procedure
- writing config files in interpreted languages is risky
  - more important to check for performance
- should write probe tests, split into 3 sets of requests:
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

- specifies the requirements, not the implementation
- __Intent-based Capacity Planning__ approaches this issue by programatically encoding the dependencies and parameters (__intent__) of a service's needs, & use that encoding to autogenerate an allocation plan that details which resources go to which service, in which cluster
  - allows autogeneration of a new plan when parameters change
- __intent__: the rational for how a service owner wants to run their service
- information we need in order to capture a service's intent:
  - dependencies
  - performance metrics
  - prioritization
- __Auxon__: Google's implementation of an intent-based capacity planning and resource allocation solution
- don't focus on perfection & purity of a solution, especially if the bounds of the problem aren't well known; launch and iterate

## Ch 19: Load balancing at the front-end

### Power isn't the answer

- Google uses __traffic load balancing__
  - take into account need for high throughput (e.g. video uploading) vs. low latency (e.g. searching)
    - on the global level, they send things requiring high throughput to less-utilized, perhaps further away, datacenters
    - on the local level, they focus on optimal resource utilization & protecting a single server from overloading
    - exceptions: could route requests to slightly further away datacenter to keep caches warm, or even a different region to avoid network congestion

### Load balancing using DNS

- problems with just letting client randomly pick an IP address
  - doesn't know which one is closest
    - mitigated if use an anycast address for authoritative nameservers, since DNS queries flow to the nearest address
      - instead of having end users talk directly to an authoritative nameserver, a recursive DNS server lies between
- implications of the DNS middleman on traffic management:
  - recursive resolution of IP addresses
  - nondeterministic reply paths
  - additional caching complications
- considerations
  - does the closest datacenter have the capacity to serve nearby users? is it experiencing network problems?
  - authoritative nameservers can't flush resolvers' caches, so need to use a relatively low TTL, and any DNS changes need to wait for caches to invalidate

### Load balancing at the virtual IP address

- Virtual IP addresses (VIPs) are shared across many devices, but the end user sees it as a single, regular IP address
  - allows hiding the implementation details, so can schedule upgrades/change the machine pool size, etc. without user knowing
- __network load balancer__ is a part of the VIP implementation, and it receives packets and forwards them to one of the machines behind the VIP
  - from there, there're many algorithms you can use to decide which machine gets the packet; e.g. lead loaded, round-robin, etc.
  - for stateful protocols, the balancer needs to keep track of all connections sent through it so that subsequent packets are sent to the same backend
    - how? use parts of the packet to create a connection ID, and then use the connection ID to select a backend
    - however, a backend could fail while a session is ongoing, and all goes to shit; need better solution
- better solution: __consistent hashing__ provides a relatively stable mapping algorithm even when backends are added or removed, leading to minimal disruption
  - so they use simple connection tracking, but fall back to consistent hashing when the system is under pressure
- another solution: __Direct Server Response (DSR)__: backend can send replies directly to the original sender, because you modify information on the data link later (layer 2 in OSI networking model) by changing the destination MAC address of the forwarding packet
  - allows balancer to leave all information in upper layers intact, so the backend receives the original source & destination IP addresses
  - saves a lot of work in the typical case (when user requests are small and replies are large), because only a small fraction of the traffic needs to traverse the load balancer
  - great b/c doesn't require keeping state on the load balancer device
  - not great because using layer 2 for internal load balancing incurs _serious_ disadvantages when deployed at scale, since all machines need to reach each other at the data link layer
    - not an issue if the network can support this connectivity & the number of machines doesn't grow excessively
- Google uses packet encapsulation:
  - network load balancer puts forwarded packet into another IP packet w/ Generic Routing Encapsulation (GRE), uses the backend's address as the destination, the backend processes the inner packet as if it were delivered directly to its network interface
  - allows the network load balancer & backend to be geographically independent
  - con: inflated packet size
    - can cause the packet to exceed the available Maximum Transmission Unit (MTU) size, and thus require fragmentation
      - but once the packet reaches the datacenter, can avoid fragmentation by using a larger MTU w/i the datacenter, but that requires a network supporting large Protocol Data Units

## Ch 20: Load balancing in the datacenter

### Identifying bad tasks: Flow control and lame ducks

- simple flow control: track active requests per backend, and limit active requests to 100
  - but this only protects backend tasks against extreme forms of overload, and it can become overloaded before 100
- lame duck state approach uses 3 states a backend task can be in:
  - healthy: backend task has initialized correctly & is processing requests
  - refusing connections: backend task is unresponsive
  - lame duck: the backend task is listening on its port & can serve, but is explicitly asking clients to stop sending requests
    - it broadcasts this state to all active clients, and inactive clients also find out via periodic health checks
    - simplifies clean shutdown since it doesn't need to return an error to incoming requests
    - any ongoing request started before this state is entered are executed normally

### Limiting the connections pool with subsetting

- __subsetting__: limiting the pool of potential backend tasks w/ which a client task interacts
  - each client in Google's RPC system maintains a pool of long-lived connections to its backends that it uses to send new requests
    - saves the resource & latency costs associated with establishing & tearing down connections
    - if a connection remains idle for a while, Google's RPC implementation switches it to a cheap "inactive" mode
      - cheaper since every connection requires some memory & CPU due to health checks at both ends
  - subset size is important, and varies based on the service's typical behavior
    - use larger subset size if:
      - number of clients is signif. smaller than # backends
      - there're frequent load imbalances w/i the client jobs (ie. one client sends many more tasks than others, or a client occasionally has a fan-out request)
    - need to handle resizes in the number of clients/backends w/ minimal connection churn, and without knowing the numbers in advance

### Load balancing policies

- __load balancing policies__: mechanisms used by client tasks to select which backend task in its subset receives a client request
- __Simple Round Robin__
  - can result in up to 2x in CPU consumption from the least to the most loaded task, which is really wasteful b/c:
    - small subsetting
      - all of its clients may not issue requests @ the same rate
    - varying query costs
      - the most expensive requests can consume 1000x+ more CPU than the cheapest requests
    - machine diversity
    - unpredictable performance factors
      - antagonistic neighbors: competition for shared resources (e.g. space in memory caches, bandwitch, etc.)
      - task restarts: tasks require signif. more resources for a few mins when they restart
- __Lead-loaded Round Robin__:
  - keep track of active # of requests in each backend task & use RR amongst the set of tasks w/ a minimal number of active requests
    - con: if a task is seriously unhealthy, it might start serving 100% errors, w/ very low latency
      - it's faster to return an "i'm unhealthy" error than it is to process a request
      - the unhealthy task is __sinkholing__ traffic because clients may sent a large amount of traffic to this task–thinking it's available.
        - avoidable by counting recent errors as if they were active requests
    - in practice, performs as badly as RR because:
      - active # of requests in each client doesn't include requests from other clients to the same backends
      - count of active # of requests may not be a good proxy for a backend's capabilities
- __Weighted Round Robin__:
  - each client keeps a capability score for each backend, and requests are distributed in a Round Robin fashion based on capabilities
  - capability score is adjusted based on the backend's current number of successful requests and at what utilization cost, with failures incurring a penalty

## Ch 21: Handling overload

- ways to gracefully handle overload:
  - serve degraded responses (responses that aren't as accurate/contain less data than normal responses, but are easier to compute)
    - use a local copy of results that may not be up-to-date but is cheaper to use

### The pitfalls of "Queries per Second"

- QPS constantly changes based on updates to client, end-user traffic, etc. and it isn't good to model load balancing over something that's ever changing
- better metric: measuring capacity directly in available resources to model a datacenter's capacity, which works better because:
  - in platforms w/ garbage collection, memory pressure naturally translates into increased CPU consumption
  - in other platforms, it's possible to provision remaining resources in such a way that they're very unlikely to run out before CPU runs out

### Per-customer limits

- i.e. provision resources by product (Gmail, Calendar, Android, etc.)

### Client-side throttling

- when a customer is out of quota, it's cheaper for a backend task to reject its requests quickly, and return a "customer is out of quota" error, than it is to serve a correct response
  - depends on how expensive the request is; if it's a simple lookup, it might be as expensive to reject it
  - client-side throttling addresses this problem
- __adaptive throttling__: each client tasks keeps track of requests & accepts for the last 2 minutes of its history
  - requests: number of requests attempted by the application layer
  - accepts: number of requests the backend accepts

### Criticality

- a request to a backend is associated with one of these 4 __criticality__ values:
  - `CRITICAL_PLUS`: will result in serious user-visible impact if they fail
  - `CRITICAL`: default value for requests; will result in less-severe user-visible impact
    - services are expected to provision enough capacity for all expected `CRITICAL` and `CRITICAL_PLUS` traffic
  - `SHEDDABLE_PLUS`: where partial unavailability is expected; default for batch jobs
  - `SHEDDABLE`: where frequent partial unavailability and occasional full unavailability is expected

### Utilization signals

- Google uses utilization signals based on the state local to the task
  - most useful signal: __executor load average__, which is based on the "load" in the process
    - count active (running + waiting) threads in the process

### Handling overload errors

- if a large subset of backend tasks in the datacenter are overloaded, requests shouldn't be retried, & errors should bubble up to the caller
- per-request retry budget of up to 3 attempts
- per-client retry budget is a ratio of requests to retries, and makes it so Google only retries if the ratio is under 10%

### Load from connections

- requiring periodic health checks on a very large number of clients that issue a very low rate of requests can be more expensive than actually serving the requests
  - thus should include recalculating the frequency of health checks in your algorithm

## Ch 22: Addressing cascading failures

### Causes of cascading failures and designing to avoid them

- server overload, resource exhaustion
- insufficient CPU makes all requests slower, resulting in various secondary effects:
  - increased # of in-flight requests
    - more requests are handled concurrently, which usually triggers queueing
  - excessively long queue lengths, thread starvation
  - CPU or request starvation
    - internal server watchdogs checking server progress are also processed as part of the request queue
  - missed RPC deadlines, reduced CPU caching benefits
- memory exhaustion causes the following:
  - dying tasks (because the container manager evicts them for exceeding available resource limits, or there're application-specific crashes)
  - increased rate of garbage collection in Java -> increased CPU usage
  - reduced cache hit rates
- thread starvation can directly cause errors/lead to health check failures, since thread overhead could use too much RAM
  - in extreme cases, thread starvation can cause you to run out of process IDs
- file descriptors
  - running out of them can lead to the inability to initialize network connections, thus causing health checks to fail
- dependencies amongst resources can make it hard to pinpoint where things went wrong, and to erroneously think a certain part is at fault
- service unavailability (resource exhaustion -> servers crashing (e.g. b/c too much RAM allocated to a container) -> overload other servers)

### Preventing server overload

1. load test the server's capacity limits, & test the failure mode for overload
2. serve degraded results
3. instrument the server to reject requests when overloaded
4. instrument higher-level systems to reject requests, rather than overloading servers
   - rate limiting usually doesn't take overall service health into account, so it might not be able to stop a failure that's already started, and simple limitations are likely to leave capacity unused
   - can implement rate limiting in a number of places:
     - at the reverse proxies (by limiting the volume of requests by criteria such as IP address)
     - at the load balancers (by dropping requests when the service enters global overload)
     - at individual tasks (to prevent random fluctuations in load balancing from overwhelming the server)
5. perform capacity planning
   - also should be used to determine the load at which the service will fail

#### Queue management

- most thread-per-request servers use queues so that they can reject new requests when the queue is full
  - but queued requests consume memory & increase latency, because requests need to wait on a queue
  - for systems w/ steady traffic, a queue size ~50% of the thread pool size is ideal
  - systems with "bursty" traffic could benefit from a queue size = thread pool size
- another option is using queueless servers, relying on failover to other server tasks when the threads are full

#### Load shedding and graceful degradation

- __load shedding__: dropping a proportion of the load by dropping traffic as the server approaches overload conditions
  - to prevent the server from running out of RAM, failing health checks, serving with extremely high latency, or experiencing any of the overload symptoms
  - can be done via per-task throttling based on CPU/memory/queue length
  - changing the queue to LIFO could be good because a user who has been waiting for 10s is likely to have had refreshed their browser
- __graceful degradation__ reduces the amount of work needed to be performed
  - considerations to make when implementing this:
    - what metrics should you use to decide when to load shed or gracefully degrade requests?
    - what actions should be taken when the server is in degraded mode?
    - what layer should you implement this? every layer in the stack? at a high-level choke point?
  - keep the implementation simple, otherwise you might erroneously trigger degraded mode;
    - good to have a way to turn off complex degradation or tune parameters if needed

#### Retries

- always use randomized exponential backoff when scheduling retries
  - if they aren't randomly distributed over the retry window, there's a chance retry ripples will schedule at the same time, thus amplifying themselves
- limit retries per request; don't retry indefinitely
- consider a server-wide retry budget per minute, where you just fail requests when the budget is exceeded
- think about the service holistically and ensure multiple levels aren't unnecessarily retrying, otherwise each client retry attempt could potentially trigger [number of client attempts]^(amount of layers retrying)

#### Deadlines

- use RPC deadlines to determine how long a request can wait before it gives up, so the frontend's resources wont be consumed for too long
  - ensure that the server checks the deadline before attempting to perform any work on the request
- servers should employ deadline propagation & cancellation propagation

#### Bimodal latency

- __bimodal latency__ can be a cause of an outage, but you may not know if you only look at the mean latency
  - so also look at the distribution of latencies when you see a latency increase
  - is avoidable if you return requests, which don't complete, early with an error rather than waiting for the deadline
- having deadlines several orders of magnitude longer than the mean request latency usually is bad & can lead to thread exhaustion
- try limiting in-flight requests for a shared resource, or limit the number of threads a client can occupy, in order to prevent a client from dominating your system

### Slow startup and cold caching

- processes are often slower when starting b/c of either/both:

  1. required initialization
  2. runtime performance improvements in some languages, particularly Java (Just-In-Time compilation, hotspot optimization, & deferred class loading)

  - binaries are also less efficient when caches aren't filled

- things leading to a cold cache:

  - turning up a new cluster
  - returning a cluster to service after maintenance
  - restarts

- when caching has a significant effect on the service, consider:

  - overprovisioning the service
    - if a latency cache is employed, a service can sustain its expected load with an empty cache
    - if a capacity cache is employed, it can't sustain its expected load with an empty cache
  - employ general cascading failure prevention techniques
  - when adding load to a cluster, slowly increase the load, so you warm up the cache
    - can also ensure that all clusters carry a nominal load & caches are kept warm

### Triggering conditions for cascading failures

- things you can use to try to initiate the domino effect:
  - process death, process updates, new rollouts, organic growth
  - planned changes, drains or turndowns; request profile changes, resource limits

### Testing for cascading failures

- also check for cache correctness at high loads (subtle concurrency bugs)
- test your service's largest clients to see how they respond to failure
- test noncritical backends to ensure their unavailability doesn't interfere w/ critical components of your service
  - e.g. spelling suggestions shouldn't interfere with query results (which are way more critical)

### Immediate steps to address cascading failures

- increase resources; stop health check failures/deaths; restart servers; enter degraded modes; eliminate batch load; eliminate bad traffic

## Ch 23: Managing critical state: Distributed consensus for reliability

- __distributed consensus__: an agreement among a group of processes
  - use distributed consensus systems that've been formally proven and tested thoroughly

### CAP Theorem

- __CAP Theorem__: a distributed system can't simultaneously have all 3 of the following:
  1. consistency (consistent views of the data at each node)
  2. availability (availability of the data at each node)
  3. tolerance (tolerance to network partitions)
- there are distributed datastore technologies supporting BASE semantics
  - __BASE (Basically Available, Soft state, and Eventual consistency)__ semantics are useful for large volumes of data & transactions that would be much more costly, & perhaps infeasible, with datastores supporting ACID semantics
  - rely on multimaster replications, committing writes to different processes concurrently, with a mechanism to resolve conflicts (e.g. latest timestamp wins, a.k.a. __eventual consistency__)
    - issues w/ eventual consistency b/c there's inevitable __clock drift__ in distributed systems, or network partitioning

### Motivating the use of consensus: Distributed systems coordination failure

- network partitions could be the result of:
  - a very slow network
  - some, but not all, messages being dropped
  - throttling occurring in one direction, but not the other direction
- many distributed systems problems turn out to be because of different versions of distributed consensus, including master election, group membership, etc.

### How distributed consensus works

- distributed software systems should use __asynchronous distributed consensus__, since it has potentially unbounded delays in message passing
  - it's impossible to solve the asynchronous distributed consensus in bounded time (FLP impossibility result) due to an unreliable network
  - use synchronous consensus for real-time systems, w/ dedicated hardware ensuring messages will be passed w/ timing guarantees
  - can either by __crash-fail__ (nodes never return to the system), or __crash-recover__
    - crash-recover is more useful since most problems are due to slow network, restarts, etc.
- __Byzantine failure__: a process passes incorrect messages due to a bug or malicious activity, are comparatively costly to handle, & are encountered less often

#### Paxos overview: An example protocol

- __Paxos__ operates as a sequence of uniquely numbered proposals which may be accepted by a majority of the system's processes
  - imposes strict ordering on all system operations
- 1st phase: proposer sends a sequence # no acceptors, who agree to accept the proposal iff it hasn't seen a proposal w/ a higher sequence number
  - if a proposer receives agreement from a majority, it can commit the proposal via a commit message w/ a value
  - acceptors need to write a journal to persistent storage whenever they accept a proposal, so they can honor their guarantees after restarting
- Paxos' limitation: any given node may not have a complete view of the set of values that've been agreed to

### System architecture patterns for distributed consensus

- distributed consensus algorithms become useful as you add higher-level system components (e.g. datastores, configuration stores, queues, locking, & leader election services) to provide the functionality they don't address

#### Reliable replicated state machines (RSMs)

- a __replicated state machine (RSM)__: a system that executes the same set of operations, in the same order, on several processes
  - used to build config storages, locking, & leader election, because several papers show that any deterministic program can be implemented as a highly available replicated service by being implemented as an RSM
  - operations are ordered globally via a consensus algorithm
  - RSMs may need to synchronize state from peers, because every member of a consensus group doesn't belong to each of the other consensus groups
    - can use a __sliding-window protocol__ to reconcile state b/t peer processes in an RSM
- can have highly available processing using leader election
  - appropriate where service leader's work can be performed by 1 processes, or is sharded
  - replicate a service, & then use leader election to ensure only 1 leader is working at any point in time
- a __barrier__, to block processes from continuing until all have reached it, can be implemented as an RSM s.t. there isn't a single point of failure, as there would be if implemented by a single coordinator process
- can also create distributed locks by implementing them as RSMs

#### Reliable distributed queuing and messaging

- instead of removing from queues, a __lease system__ is more appropriate, because we need to ensure everything on the queue is processed
  - lease system: use renewable leases w/ timeouts instead of indefinite locks, in case the process dies
  - downside of queueing-based systems: queue dies? system can't operate! Thus, implement the queue as an RSM to minimize risk
- __atomic broadcast__ allows messages to be received reliably & in the same order by all participants
- __queuing-as-work-distribution__ pattern uses a queue as a load balancing device, & can be considered point-to-point messaging
- publish-subscribe systems can be used to implement coherent distributed caches

### Distributed consensus performance

- it isn't true that consensus algorithms are too slow + costly for systems requiring high throughput & low latency
- workload can also vary due to consistency semantics required for read operations

#### Multi-Paxos protocol: Detailed message flow

- the __Multi-Paxos__ protocol uses a __strong leader process__, meaning that unless a leader hasn't been elected yet, or some failure occurs, it only requires one round trip from the proposer to a quorum of acceptors to reach consensus
- need to have the right timeouts & backoff strategies to ensure all processes don't attempt to become the leader @ the same time (-> dueling proposers -> no proposals accepted -> livelock) in the absence of a leader
- to guarantee that a read is up-to-date, do one of the following:
  - perform a read-only consensus operation
  - read data from replica guaranteed to be most up-to-date (the leader can provide this guarantee)
  - use quorum leases (some replicas are granted a lease on all/part of a data in the system, allowing strongly consistent local reads at the cost of some write performance)
    - lease is for a specific (usually brief) period of time, since any operation changing the state of that data needs to be acknowledged by all replicas in the read quorum
    - if any of the replicas becomes unavailable, data can't be modified until the lease expires

#### Distributed consensus performance and network latency

- it isn't practical for clients to keep persistent connections to consensus clusters open, because TCP/IP connections consume resources (e.g. file descriptors, keepalive traffic) 
  - solution: use a pool of regional proxies, which hold persistent TCP/IP connections, in order to avoid the setup overhead over long distances
    - proxies also help encapsulate sharding, load balancing strategies, & discovery of cluster members and leaders
- using a stable leader could allow read optimizations, but has the following drawbacks:
  - operations to change state must be sent to leader, & there's increased latency for clients located farther from the leader
  - leader process's outgoing network bandwidth is the system bottleneck
  - if leader's machine has performance problems, the throughput of the entire system is reduced
- batching -> increased system throughput, but replicas are idle as they await replies to their messages
  - solution? pipeline so you can allow multiple proposals to be in-flight at once, so using a sliding-window approach instead

### Deploying distributed consensus-based systems

- can use majority quorums, i.e. `2f + 1` replicas can tolerate `f` failures, or if need Byzantine fault tolerance, use `3f + 1` replicas to tolerate `f` failures
- if a consensus system uses enough replicas that it can't form a quorum, the system is unrecoverable since the durable logs of at least 1 of the missing replicas can't be accessed
- decision for number of replicas is a trade-off between: the need for reliability, frequency of planned maintenance affecting the system, risk, performance, and cost

#### Location of replicas

- a __failure domain__: the set of components of a system that can become unavailable as a result of a single failure, e.g.:
  - a physical machine
  - a rack in a datacenter served by 1 power supply
  - several racks in a datacenter served by 1 piece of networking equipment
  - a fiber optic cable cut can render a datacenter unavailable
  - a set of datacenters in a geographic area can be affected by a single natural disaster

#### Capacity and load balancing

- adding more replicas to a majority quorum system can potentially decrease system availability, since there's a percentage of available replicas needed for the system to stay alive

#### Quorum composition

- spread replicas as evenly as possible to try to create even lag, and to distribute traffic as evenly as possible

### Monitoring distributed consensus systems

- things to monitor:
  - members running in each consensus group, and their state (healthy?)
  - persistently lagging replicas
  - whether a leader exists
  - number of leader changes
  - consensus transaction number (are we making progress?)
  - number of proposals seen; number of proposals agreed upon
  - throughput & latency
  - additionally to troubleshoot performance issues:
    - latency distributions for proposal acceptance
    - distributions of network latencies observed b/t diff parts of the system
    - number of time acceptors spend on durable logging
    - the overall bytes accepted per second in the system

## Ch 24: Distributed periodic scheduling with Cron

### Cron

- cron's failure domain is essentially 1 machine, so if that machine isn't running, the cron scheduler won't run and jobs won't launch
  - note that failure to launch is acceptable for some jobs, but not for others
- the only state needing to persist across `crond` restarts is the crontab configuration
  - cron doesn't care about what it has launched; it launches jobs & forgets about them :'( 
- system prefers skipping launches over launching a job multiple times, and it's up to the cron job owners to monitor their jobs, since it's harder to recover from multiple launches where it's unacceptable

### Cron at large scale

- decouple processes from machines in order to increase reliability; thus, specify service requirements and the datacenter it should run in
- need to mitigate data loss (local machine data) & excessive time requirements
  - can do this via distributed filesystem, e.g. GFS, to persist local state of the machine, and identify jobs that failed to launch due to rescheduling
- need to know the quantity of resources each job, and the cron system itself, needs
  - because cron job may be delayed if datacenter doesn't have enough resources
- deploying cron w/i a single datacenter allows low latency & it to share the same fate as the datacenter scheduler

### Building cron at Google

- tracking state of cron job:

  - store data externally in a generally available distributed storage
  - usage a system that stores a small volume of state as part of the cron service itself
    - Google chose this b/c data stored about cron jobs is super small, and data size doesn't justify an expensive distributed filesystem write w/ high latency
    - base services should have few dependencies

- Google deploys replicas of the cron service & uses the Paxos distributed consensus algorithm

  - the leader is also the cron service leader

- every cron job has 2 synchronization points:

  1. when we're about to perform the launch
  2. when we've finished the launch

  - need to keep track of both to prevent launch miss or duplicate launches
    - one solution: provide naming of jobs ahead of time, and then attach state to those names so you can look them up; can launch the jobs w/ names where a lookup of their state fails

- instead of logging everything, log the most recent snapshot of jobs

## Ch 25: Data processing pipelines

- __data pipeline__: a program that reads in data, transforms it in some desired way, and then outputs new data
- __multiphase pipelines__: pipelines that, given the scale & processing complexity inherent to Big Data, run off a chained series of programs, with the output of one program being the input to the next
  - __depth__ of a pipeline: the number of programs chained together

### Challenges with the periodic pipeline pattern

- periodic pipelines are stable if sufficient workers and CPU capacity, and number of chained jobs and relative throughput between jobs are uniform
  - but it takes fine tuning of worker sizing, periodicity, chunking technique, etc. to get to this state
  - organic growth & change can stress the system & cause problems (e.g. jobs exceeding their deadlines, resource exhaustion, etc)
  - they're typically run as lower-priority batch jobs

### Troubles caused by uneven work distribution

- "hanging chunk" problem can result when resources are assigned due to difference b/t machines in a cluster, or overallocation to a job
  - typical user code waits for all computation to complete before moving onto the next pipeline stage
  - may not be ideal to just restart the job, because pipeline implementations don't include checkpointing, so all work on the chunks is restarted from the beginning

#### Monitoring problems in periodic pipelines

- need to have real--time info on runtime performance so you can provide operational support, (including emergency response)
  - in practice, standard monitoring models report metrics collected during execution upon completion
  - continuous pipelines' tasks are constantly running & their telemetry (process of recording & transmitting the readings of an instrument) is routinely designed s.t. real-time metrics are available
- __Moiré load pattern__: 2+ pipelines running simultaneously occasionally overlap, causing them to both consume a shared resource

### Introduction to Google Workflow

- when designing a system for a proposed data pipeline, scope out the following from the business side: demand for design modifications, expected additional resources, & expected latency requirements
- Google Workflow makes continuous processing available at scale for transactional data pipelines, and ensures correctness w/ exactly-one semantics

### Stages of execution in Workflow

- the task master holds a collection of pointers to uniquely-named data, & each work unit has a uniquely held lease, which thereby allows a correctness guarantee
  - Workflow also versions all the tasks, in case they update or the task lease changes
- Workflow embeds a server token (a unique identifier for each Task Master) in each task's metadata to prevent a rogue or incorrectly-configured Task Master from corrupting the pipeline

### Ensuring business continuity

- the Task Master stores journals on Spanner, using it as a globally available, globally consistent, but low-throughput filesystem
  - Chubby is used to elect the writer

## Ch 26: Data integrity: What you read is what you wrote

### Data integrity's strict requirements

- __scale__: a service's volume of users & the mixture of workloads the serve can handle before latency suffers or the service falls apart
- __velocity__: how fast a service can innovate to provide users w/ superior value at a reasonable cost
- backups vs. archives: backups can be loaded back into an app, whereas archives can't
  - archives safekeep data for auditing, discovery, & compliance needs
    - they take time to recover (e.g. a week)
  - backups are used in situations of disasters, intended to allow you to recover data w/i the uptime
- data is at risk until backed up, thus should consider periodic backups, or even continuous (streaming) approaches

#### Technical challenges cloud services introduce for backups

- recovered data won't necessarily be correct if the environment uses a mixture of transactional & non-transactional backup and restore solutions
- if services need to evolve w/o going down for maintenance, different versions of business logic may act on data in parallel
- when interacting services are independently versioned, incompatible versions of different services may interact momentarily, which could result in accidental data corruption/loss

------

- APIs should have features/functionality addressing the following:
  - data locality and caching
  - local and global data distribution
  - strong and/or eventual consistency
  - data durability, backup, and recovery

### Google SRE objectives in maintaining integrity and availability

- it's important to recognize that replication and redundancy ≠ recoverability
  - won't protect you from software bugs, defects in the system, user errors, etc.
    - database exports to a file, and other methods, may though
- need defense in depth (several types of defense):
  - 1st line of defense: _soft deletion_: user-visible trash
    - and then lazy deletion when it's time to delete it
  - 2nd line of defense: backups & related recovery methods
  - 3rd line of defense: regular data validation
- challenges that developing with the cloud poses:
  - referential integrity between datastores
  - schema changes
  - zero-downtime data migrations
  - evolving integration points with other services
- continuously test the recovery process as a part of your normal operations, and set alerts for when a recovery process fails

### General principles of SRE as applied to data integrity

- anything and everything can and will go wrong

## Ch 27: Reliable product launches at scale

### Setting up a launch process

- questions on your launch checklist need to meet the following criteria:
  - is a substantial question, as proven by a previous launch disaster
  - is concrete, practical, and reasonable for developers to accomplish

### Developing a launch checklist

- question categories on a launch checklist:
  - architecture & dependencies
  - integration
  - capacity planning
  - failure modes
  - client behavior
  - processes and automation
  - development process
  - external dependencies
  - rollout planning

### Selected techniques for reliable launches

- gradual & staged rollouts
  - canaries: first stage of a rollout, which includes observing how the new version performs on a subset of the machines
  - gradual rollouts example: invite system for a 
- feature flag frameworks

## Ch 29: Dealing with interrupts

### Managing operational load

- __operational load__: the work that must be done to maintain the system in a functional state
  - falls into 3 general categories: pages, tickets, & ongoing operational activities
    - __pages__: production alerts, triggered in response to production emergencies
    - __tickets__: customer requests that require you to take an action
    - __ongoing operational responsibilities__/toil: team-owned code, flag rollouts, responses to ad hoc, time-sensitive questions from customers

## Ch 31: Communication and collaboration in SRE

### Communications: Production meetings

- on the agenda of production meetings (meetings about the service)
  - metrics
  - upcoming production changes
  - outages
  - paging events (pages about problems that can be postmortem-worthy, but often aren't)
  - nonpaging events
    - an issue that probably should've paged, but didn't
    - an issue that isn't pageable, but requires attention
    - an issue that isn't pageable and doesn't require attention
  - prior action items

## Ch 32: The evolving SRE engagement model

### The PRR model

- __Production Readiness Review (PRR)__: a process identifying the reliability needs of a service based on its specific details
- __"dark launch" setup__: a setup in which requests from a live production service are additionally sent to a new service
  - responses are "dark" since they aren't shown to users, but allow the team to gain operational insight & see issues prior to real launch
- a production platform w/ common service structure, conventions, & software infrastructure means that SREs can focus on platform-related bugs, and the product team can work on application-related bugs, achieving shared responsibility