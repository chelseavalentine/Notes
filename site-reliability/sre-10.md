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

## Ch 27: Reliable product launches at scale

### Setting up a launch process

* questions on your launch checklist need to meet the following criteria:
  - is a substantial question, as proven by a previous launch disaster
  - is concrete, practical, and reasonable for developers to accomplish

### Developing a launch checklist

* question categories on a launch checklist:
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

* gradual & staged rollouts
  - canaries: first stage of a rollout, which includes observing how the new version performs on a subset of the machines
  - gradual rollouts example: invite system for a 
* feature flag frameworks

## Ch 29: Dealing with interrupts

### Managing operational load

* __operational load__: the work that must be done to maintain the system in a functional state
  - falls into 3 general categories: pages, tickets, & ongoing operational activities
    + __pages__: production alerts, triggered in response to production emergencies
    + __tickets__: customer requests that require you to take an action
    + __ongoing operational responsibilities__/toil: team-owned code, flag rollouts, responses to ad hoc, time-sensitive questions from customers
