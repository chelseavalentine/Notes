# Site Reliability Engineering

Notes taken reading Google's [Site Reliability Engineering](https://landing.google.com/sre/) book.

## Ch 9: Simplicity

* __exploratory coding__: writing code w/ an intended expiration date, allowing you to be more liberal with test coverage & release management since it's never shipped

* every line of code changed/added creates the potential for a bug/defect introduction

* it's poor practice to create/put a "util" or "misc" binary into production

## Part III: Practices

* service reliability hierarchy (most basic -> most advanced):
  - monitoring, incident response, postmortem/root cause analysis, testing + release procedures, capacity planning, development, product

## Ch 10: Practical alerting from time-series data

### Time-series monitoring outside of Google

* Borgmon uses white-box testing by requiring a common data exposition format, which enables mass data collection with low overheads
  - data is used for creating alerts & rendering charts
  - can collect from other Borgmon, allowing a hierarchy of information about the service (e.g. cluster-level, global-level, etc)

### Exporting variables (varz)

* each of the major languages @ Google has a matching exported variable interface implementation, which automatically registers w/ the HTTP server built into every Google binary

### Collection of exported data

* Borgmon instances are configured with a list of targets by using one of many name resolution methods
  - target list can be dynamic, so using service discovery allows the monitoring to scale w/o maintenance

* varz isn't similar to __Simple Network Management Protocol (SNMP)__, which is designed to have minimal transport requirements and continue working when most other network applications fail
  - Borgmon allows engineers to write smarter alerting rules by using collection failure itself as a signal

### Storage in the [time-series arena](https://landing.google.com/sre/book/chapters/practical-alerting.html#fig_structure_of_timeseries)

* data points take on the form `(timestamp, value)`, stored in chronological lists called __time-series__, each labelled uniquely, with a label of the form `name=value`
  - the variable expression for the name of a time-series (labelset) is a bunch of key-value pairs, e.g.:
    `{var=[variable name], job=[job], service=[service], zone=[location]}`
* when the time-series arena (table holding all of the collected time-series) gets full, the oldest entries are evicted
  - periodically, in-memory state is archived to an external Time-Series Database (TSDB), so Borgman could query TSDB for older data

### Rule evaluation

* _Borgmon rules_ just consist of simple algebraic expressions computing time-series from other time-series

### Alerting

* Borgmon is connected to a centrally-run service, the Alertmanager, whose responsibilities include:
  - inhibiting certain alerts when others are active
  - deduplicating alerts from multiple Borgmon that have the same labelsets
  - fan-in or fan-out alerts based on their labelsets when multiple alerts with similar labelsets fire

### Sharding the monitoring topology

* time-series data is streamed between Borgmon, as opposed to using the text-based varz format, which saves CPU time and network bytes

* typical deployment: 2+ global Borgmon for top-level aggregation, & 1 Borgmon per datacenter to monitor all the jobs running at that location

### Black-box monitoring

* shortcomings of Borgmon, a white-box monitoring system, include:
  - not being able to see what users see, e.g. failure because of a DNS error
    + this issue is covered via Prober, which runs protocol checks against a target, and can integrate with both Borgmon and the Alertmanager

### Maintaining the configuration

* Borgmon provides a way to build extensive unit & regression tests by synthesizing time-series data, so you can see whether rules behave as intended

## Ch 11: Being on-call

### Feeling safe

* most important on-call resources:
  - clear escalation paths
  - well-defined incident-management procedures
  - a blameless postmortem culture

## Ch 12: Effective troubleshooting

### Theory

* __hypothetico-deductive method__ (? lol): given a set of observations about a system, and a theoretical basis for understanding system behavior, we iteratively hypothesize potential causes for the failure and try to test these hypotheses

* troubleshooting process: [1] problem report -> [2] triage -> [3] examine -> [4] diagnose -> [5] test/treat -> [6] cure ([diagram here](https://landing.google.com/sre/book/chapters/effective-troubleshooting.html#xref_troubleshooting_process))

* common troubleshooting pitfalls:
  - looking at irrelevant symptoms or misunderstanding the meaning of system metrics
  - misunderstanding how to change the system, its inputs, or its environment, so as to safely and effectively test hypotheses
  - coming up with wildly improbable theories about what's wrong, or latching onto causes of past problems
  - hunting down spurious correlations that are actually coincidences, or are correlated with shared causes

### In practice

* problem report: every problem starts with an automated report/someone saying there's a problem
  - effective reports tell you: [1] the expected behavior, [2] the actual behavior, and [3] how to reproduce the behavior

* first step in your course of action should be to make the system work as well as it can under the circumstances
  - e.g. diverting traffic to working clusters, preventing cascading failure, or disabling subsystems to lighten the load

* need to be able to examine every component in a system in order to understand if it's behaving correctly
  - monitoring and logging are invaluable for this
    + text logs are helpful for reactive, real-time debugging, while storing logs in a structured binary format allows you to conduct retrospective analysis with much more information
    + great to have multiple verbosity levels, with ways to increase these levels in real-time, so you can examine any/all operations in detail without having to restart the process

* you can look at connections _between_ components (the data flowing between them) to determine whether a given component is working properly
  - for small-enough systems, you can examine the correctness from end to beginning
  - for large systems, it's probably better to split the system in half and examine communication paths between components on one side and the other and repeatedly determining which half is working properly

* ask "what", "where", and "why" the malfunctioning system is do whatever it's doing

* figure out when last the system was working

* finally, test and treat your hypotheses, keeping in mind that:
  - ideal tests have mutually exclusive alternatives, so that it can rule out a group of hypotheses
  - perform tests in decreasing order of likelihood, considering the possible risks to the system from the test
  - an experiment may provide misleading results due to confounding factors
  - active tests may have side effects that change future test results
    + e.g. using more CPUs to make operations faster, but encounter more data races; turning on verbose logging, but can't tell whether system is getting slower because of logging
  - some tests may not be definitive, only suggestive

### Negative results are magic

* experiments with negative results are conclusive

* tools and methods can outlive the experiment and inform future work

### Making troubleshooting easier

* build observability – with both white-box metrics and structured logs – into each component from the ground up

* design systems with well-understood and observable interfaces between components
