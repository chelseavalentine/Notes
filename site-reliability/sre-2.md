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

