# Nagios Core

Learning about Nagios Core by reading _Building a Monitoring Infrastructure with Nagios_.


### Introduction

* purpose:
  - helps decrease downtime (2 main ways: redundancy & monitoring systems)
    + got to uphold those SLAs (Service Level Agreements)
  - helps reveal chronic interoperability issues
  - gives engineers detailed capacity planning information
  - shows where bandwich bottlenecks are
  - provides management to critical systems

* monitoring system: periodically connects to a Web server to make sure it responds, & sends notifications to admins if not
  - need to be done well, otherwise could create backdoors into otherwise secure infrastructure, leech time & resources from servers, & congest network with health checks
    + in bad cases: sensitive info could be being transmitted in clear text b/t hosts & the monitoring system

* __Nagios__ is open source software that interfaces well w/ other open source tools (isn't monolithic software solving all your problems)
  - better than other monolithic commercial solutions, because they try to accomplish too much by trying to monitor everything, and aren't like Nagios in the sense that it can monitor specific things exactly as you want it
    + often those commercial solutions have contextual limitations due to having architectures that're hard to integrate without a lot of reimplementation
  - highly modular due to single-purpose monitoring _plugins_
  - community-drievn support for devices & services
  - does one thing but does it well

### Chapter 1: Best Practices (pp. 1-11)

* important distinction b/t good & bad monitoring systems: amount of impact they have on network environment, when it comes to resource & bandwith utilization, & security

#### A procedural approach to systems monitoring

* need to distinguish which critical systems for monitoring initiatives to be successful (if everything is important, nothing is)

* management needs to be involved w/ planning monitoring systems, because they can help identify critical systems
  - those who work with the things being monitored should also be involved to get a solution that works for everyone

#### Processing and overhead

* centralized execution is preferable b/c the monitored hosts have less of a resource burden
  - but sometimes impossible, and remote processing is preferable
    + like in the instance of 10,000s of hosts, where centralized execution is too much for 1 monitoring server
    + w/ remote processing, clients report back their results to the monitoring system

* several Nagios servers can be combined to form 1 distributed monitoring system
  - enables centralized exceution in large environments, or geographically disperse environments

##### Bandwith considerations

* plugins generate some IP traffic, so need to position the monitoring system well w/i the network topology to minimize traffic routing, the amount of traffic, and its dependency on other devices
  - manipulate traffic via polling intervals & plugin redundancy (2+ plugins monitoring the same service)

* Nagios has _hostgroups_ for hosts that're on slow links or are sensitive to resource utilization, so slowness doesn't trigger an a false alarm

##### Network location and dependencies

* implementation goals:
  1. Maintain existing security measures
  2. Minimize impact on network
  3. Minimize # of dependencies b/t the monitoring system and the most critical systems

##### Security

* monitoring systems usually need remote execution rights -> easy to introduce backdoors & vulnerabilities, which may be overlooked by penetration testers & auditing tools

* benefit w/ Nagios is that the popular remote execution plugins use industry-standard OpenSSL libraries, which're peer-reviewed by smart ppl/specialistics
  - helps avoid issue of not implementing encryption protocols correctly, which commercial-monitoring apps sometimes fail at

##### Silence is golden

* monitoring systems use static thresholds to determine the state of a service
  - a rookie mistake: not figuring out what the normal operating parameters on a server are (eg. a server may routinely run CPU-intense batch jobs in a timeframe), thus creating a false alarm
  - run monitoring systesm for a few weeks to collect data to create good configs, prior to setting up notifications

* Nagios has two threshold levels (warning & critical), & many escalation & polling options
  - thus should architect the system w/ a layered approach

##### Watching ports versus watching applications

* __End to End (E2E) monitoring__: makes use of monitored services the way a user would (eg. checking ability to login to a website on a server)
  - can lighten the load b/c can often replace many plugins (eg. a plugin that only checks that the port the website is on is responding)
  - can report on more complex problems (eg. a permissions problem)
  - good @ catching failures in unexpected places
  - drawbacks:
    + you know there's a problem, but it doesn't know exactly where (sometimes it can be in a different system altogether)

### Chapter 2: Theory of Operations (pp. 13-39)

* need to modify monolithic software when you want to change/add to what you monitor (sometimes impossible due to licenses)

### (skim) Chapter 3: Installing Nagios (pp. 39-51)

### Chapter 4: Configuring Nagios (pp. 51-75)

### (skim) Chapter 5: Bootstrapping the configs (pp. 75-85)

### Chapter 6: Watching (pp. 85-131)

### Chapter 7: Visualization (pp. 131-173)

### (skim) Chapter 8: Nagios Event Broker Interface (pp. 171-193)
