# Site Reliability Engineering

Notes taken reading Google's [Site Reliability Engineering](https://landing.google.com/sre/) book.

## Ch 24: Distributed periodic scheduling with Cron

### Cron

* cron's failure domain is essentially 1 machine, so if that machine isn't running, the cron scheduler won't run and jobs won't launch
  - note that failure to launch is acceptable for some jobs, but not for others

* the only state needing to persist across `crond` restarts is the crontab configuration
  - cron doesn't care about what it has launched; it launches jobs & forgets about them :'( 

* system prefers skipping launches over launching a job multiple times, and it's up to the cron job owners to monitor their jobs, since it's harder to recover from multiple launches where it's unacceptable

### Cron at large scale

* decouple processes from machines in order to increase reliability; thus, specify service requirements and the datacenter it should run in

* need to mitigate data loss (local machine data) & excessive time requirements
  - can do this via distributed filesystem, e.g. GFS, to persist local state of the machine, and identify jobs that failed to launch due to rescheduling

* need to know the quantity of resources each job, and the cron system itself, needs
  - because cron job may be delayed if datacenter doesn't have enough resources

* deploying cron w/i a single datacenter allows low latency & it to share the same fate as the datacenter scheduler

### Building cron at Google

* tracking state of cron job:
  - store data externally in a generally available distributed storage
  - usage a system that stores a small volume of state as part of the cron service itself
    + Google chose this b/c data stored about cron jobs is super small, and data size doesn't justify an expensive distributed filesystem write w/ high latency
    + base services should have few dependencies

* Google deploys replicas of the cron service & uses the Paxos distributed consensus algorithm
  - the leader is also the cron service leader

* every cron job has 2 synchronization points:
  1. when we're about to perform the launch
  2. when we've finished the launch
  * need to keep track of both to prevent launch miss or duplicate launches
    - one solution: provide naming of jobs ahead of time, and then attach state to those names so you can look them up; can launch the jobs w/ names where a lookup of their state fails

 * instead of logging everything, log the most recent snapshot of jobs

## Ch 25: Data processing pipelines

* __data pipeline__: a program that reads in data, transforms it in some desired way, and then outputs new data

* __multiphase pipelines__: pipelines that, given the scale & processing complexity inherent to Big Data, run off a chained series of programs, with the output of one program being the input to the next
  - __depth__ of a pipeline: the number of programs chained together

### Challenges with the periodic pipeline pattern

* periodic pipelines are stable if sufficient workers and CPU capacity, and number of chained jobs and relative throughput between jobs are uniform
  - but it takes fine tuning of worker sizing, periodicity, chunking technique, etc. to get to this state
  - organic growth & change can stress the system & cause problems (e.g. jobs exceeding their deadlines, resource exhaustion, etc)
  - they're typically run as lower-priority batch jobs

### Troubles caused by uneven work distribution

* "hanging chunk" problem can result when resources are assigned due to difference b/t machines in a cluster, or overallocation to a job
  - typical user code waits for all computation to complete before moving onto the next pipeline stage
  - may not be ideal to just restart the job, because pipeline implementations don't include checkpointing, so all work on the chunks is restarted from the beginning

#### Monitoring problems in periodic pipelines

* need to have real--time info on runtime performance so you can provide operational support, (including emergency response)
  - in practice, standard monitoring models report metrics collected during execution upon completion
  - continuous pipelines' tasks are constantly running & their telemetry (process of recording & transmitting the readings of an instrument) is routinely designed s.t. real-time metrics are available

* __Moiré load pattern__: 2+ pipelines running simultaneously occasionally overlap, causing them to both consume a shared resource

### Introduction to Google Workflow

* when designing a system for a proposed data pipeline, scope out the following from the business side: demand for design modifications, expected additional resources, & expected latency requirements

* Google Workflow makes continuous processing available at scale for transactional data pipelines, and ensures correctness w/ exactly-one semantics

### Stages of execution in Workflow

* the task master holds a collection of pointers to uniquely-named data, & each work unit has a uniquely held lease, which thereby allows a correctness guarantee
  - Workflow also versions all the tasks, in case they update or the task lease changes

* Workflow embeds a server token (a unique identifier for each Task Master) in each task's metadata to prevent a rogue or incorrectly-configured Task Master from corrupting the pipeline

### Ensuring business continuity

* the Task Master stores journals on Spanner, using it as a globally available, globally consistent, but low-throughput filesystem
  - Chubby is used to elect the writer
