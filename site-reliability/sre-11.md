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
