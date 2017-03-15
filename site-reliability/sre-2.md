# Site Reliability Engineering

Notes taken reading Google's [Site Reliability Engineering](https://landing.google.com/sre/) book.

## Ch 5: Eliminating Toil

### Toil defined

* __toil__: work tied to running a production service, which tends to be manual, repetitive, automatable
  - is interrupt-driven and reactive, rather than strategy-driven and proactive
  - O(N) with service growth

### What qualifies as engineering?

* software engineering:
  - writing/modifying code, design & documentation work
    + e.g. automation scripts, creating tools/frameworks, adding service features for scalability and reliability, modifying infrastructure code to make it more robust

* systems engineering:
  - configuring production systems, modifying configurations, or documenting systems in a way that produces lasting improvements from a one-time effort
    + e.g. monitoring setup & updates, load balancing configuration, server configuration, tuning of OS parameters, load balancer setup, & consulting on architecture, design, and productionization for developer teams
 
 * __overhead__: administrative work not directly tied to running a service (e.g. hiring, HR paperwork, team/company meetings, etc.)

## Ch 6: Monitoring distributed systems

* definitions
  - __white-box monitoring__: monitoring based on metrics exposed by system internals (includes logs, interfaces, or HTTP handler emitting internal statistics)
  - __black-box monitoring__: testing externally visible behavior as a user would see it

* why monitor?
  - analyzing long-term trends
  - comparing over time or experiment groups (trying out new technologies) 
  - conducting ad hoc retrospective analysis (i.e. debugging)
    + e.g. trying to see why latency increased

* monitoring systems should address the what and why of things broken

* black-box vs. white-box monitoring
  - white-box monitoring allows you to detect imminent problems, whereas black-box problems are occurring

### Four golden signals to monitor

* __latency__: time it takes to service a request
  - distinguish between latency for successful requests & for failed requests

* __traffic__: amount of demand being placed on the system, measured in a high-level system-specific metric

* __errors__

* __saturation__: utilization of resources
  - includes predictions of impending saturation (e.g. database will fill in X hours)
  - e.g. measuring latency over small windows (e.g. 1 min), can signal saturation early

* the frequency of monitoring depends on the availability you're trying to reach

* remove data collection, aggregation, and alerting configuration if it's rarely used; same for signals that aren't used for anything

### Tying these principles together

* does the rule detect an otherwise undetected condition that's urgent, actionable, and actively or imminently user visible?

* every page response should require intelligence to react

* pages should be about a novel problem/event that hasn't been seen before

## Ch 7: The evolution of automation at Google

* important concerns to resolve when configuring clusters
  - are all of the service's dependencies available and correctly configured?
  - were all configurations and packages consistent with other deployments?

* aspects of automation processes:
  - competence (their accuracy)
  - latency (how quickly all steps are executed once initiated)
  - relevance (portion of real-world process covered by automation)

* Borg turned cluster management into something where you could make API calls to a central coordinator
  - gave additional efficiency, flexibility (e.g. machines can schedule batch __and__ user-facing tasks), & reliability

## Ch 8: Release engineering

### The role of a release engineer

* understanding of source code management, compilers, build configuration languages, automated build tools, package managers, & installers

* release engineers & SREs work together to develop strategies for canarying changes, pushing out new releases w/o service interruption, & rolling back features demonstrating problems

### Philosophy

#### Self-service model

* develop best practices & tools s.t. product teams can be self-sufficient/control + run their own release processes

#### High velocity

* frequent releases means less changes between versions, and can test/troubleshoot easier

#### Hermetic builds

* __hermetic build__: a build insensitive to the libraries and other software installed on the build machine
  - builds depend on known versions of build tools (e.g. compilers) and dependencies (e.g. libraries)

### Continuous build and deployment

* __Rapid__: Google's automated release system, which uses several Google technologies to provide a scalable, hermetic, and reliable release system
  - building: Google uses __Blaze__ (since it supports building binaries from many languages
  - branches: the main branch of the source code tree (mainline) isn't used, they branch off it and cherry pick the changes for a release, so they know the exact contents of each release
  - testing: a continuous test system runs unit tests upon each change submission
  - packaging: uses Midas Package Manager (MPM) to distribute software to Google production machines

### Configuration management

* methods of managing the distribution of config files
  - using the mainline
    + allows you to decouple the binary releases and configuration changes
    + but can lead to a skew between the checked-in version and the running version, since you need to update jobs to pickup config changes
  - including config files and binaries in the same MPM package
    + works when there're few configuration files, or the project change with each release cycle
    + limits flexibility by tightly coupling binary & config files
    + simplifies deployment since only need to install one package
  - packaging config files into MPM "configuration packages"
    + allows changing each config and binary package independently
  - reading config files from an external store
    + good for projects with config files that need to change frequently or dynamically (while the binary is running)
    + stored in Chubby, Bigtable, or their source-based filesystem
