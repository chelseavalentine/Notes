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
