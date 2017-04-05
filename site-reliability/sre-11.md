# Site Reliability Engineering

Notes taken reading Google's [Site Reliability Engineering](https://landing.google.com/sre/) book.

## Ch 31: Communication and collaboration in SRE

### Communications: Production meetings

* on the agenda of production meetings (meetings about the service)
  - metrics
  - upcoming production changes
  - outages
  - paging events (pages about problems that can be postmortem-worthy, but often aren't)
  - nonpaging events
    + an issue that probably should've paged, but didn't
    + an issue that isn't pageable, but requires attention
    + an issue that isn't pageable and doesn't require attention
  - prior action items

## Ch 32: The evolving SRE engagement model

### The PRR model

* __Production Readiness Review (PRR)__: a process identifying the reliability needs of a service based on its specific details

* __"dark launch" setup__: a setup in which requests from a live production service are additionally sent to a new service
  - responses are "dark" since they aren't shown to users, but allow the team to gain operational insight & see issues prior to real launch

* a production platform w/ common service structure, conventions, & software infrastructure means that SREs can focus on platform-related bugs, and the product team can work on application-related bugs, achieving shared responsibility

## Ch 32: Lessons learned from other industries
