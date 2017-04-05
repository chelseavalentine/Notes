# Site Reliability Engineering

Notes taken reading Google's [Site Reliability Engineering](https://landing.google.com/sre/) book.

## Ch 26: Data integrity: What you read is what you wrote

### Data integrity's strict requirements

* __scale__: a service's volume of users & the mixture of workloads the serve can handle before latency suffers or the service falls apart

* __velocity__: how fast a service can innovate to provide users w/ superior value at a reasonable cost

* backups vs. archives: backups can be loaded back into an app, whereas archives can't
  - archives safekeep data for auditing, discovery, & compliance needs
    + they take time to recover (e.g. a week)
  - backups are used in situations of disasters, intended to allow you to recover data w/i the uptime

* data is at risk until backed up, thus should consider periodic backups, or even continuous (streaming) approaches

#### Technical challenges cloud services introduce for backups

* recovered data won't necessarily be correct if the environment uses a mixture of transactional & non-transactional backup and restore solutions

* if services need to evolve w/o going down for maintenance, different versions of business logic may act on data in parallel

* when interacting services are independently versioned, incompatible versions of different services may interact momentarily, which could result in accidental data corruption/loss

-----

* APIs should have features/functionality addressing the following:
  - data locality and caching
  - local and global data distribution
  - strong and/or eventual consistency
  - data durability, backup, and recovery

### Google SRE objectives in maintaining integrity and availability

* it's important to recognize that replication and redundancy != recoverability
  - won't protect you from software bugs, defects in the system, user errors, etc.
    + database exports to a file, and other methods, may though

* need defense in depth (several types of defense):
  - 1st line of defense: _soft deletion_: user-visible trash
    + and then lazy deletion when it's time to delete it
  - 2nd line of defense: backups & related recovery methods
  - 3rd line of defense: regular data validation

* challenges that developing with the cloud poses:
  - referential integrity between datastores
  - schema changes
  - zero-downtime data migrations
  - evolving integration points with other services

* continuously test the recovery process as a part of your normal operations, and set alerts for when a recovery process fails

### General principles of SRE as applied to data integrity

* anything and everything can and will go wrong
