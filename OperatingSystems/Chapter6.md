# Modern Operating Systems

## Chapter 6: Deadlocks

* __deadlock__: a set of processes is deadlocked if each process in the set is waiting for an event that only another process in the set can cause

### 6.1 Resources

* __resources__: the objects granted (eg. devices, data records, files, ...)
  - types:
    * __preemptable resource__: one that can be taken away from the process owning it w/o ill effects (eg. memory)
    * __nonpreemptable resource__: one that can't be taken away from the current owner w/o potentially causing failure
      - deadlocks usually involve this kind of resource


### 6.2 Introduction to deadlocks

* __resource deadlock__: each process waits for a resource possessed by another member of the set
  - 4 conditions that must be present to cause this:
    1. _Mutual exclusion condition_: each resource is either currently assign- ed to exactly one process or is available
    2. _Hold-and-wait condition_: processes currently holding resources that were granted earlier can request new resources
    3. _No-preemption condition_: resources previously granted cannot be forcibly taken away from a process; the process needs to release it
    4. _Circular wait condition_: circular list of 2+ processes, each waiting for the next member in the chain

### 6.4 Deadlock detection and recovery

* when there're multiple copies of a resource, you use a matrix-based algorithm instead of a list-based algorithm to detect deadlocks
  * __existing resource vector__, __available resource vector__, __current allocation matrix__, __request matrix__

* when to check for a deadlock?: every _k_ minutes, or if CPU utilization has dropped below a threshold

#### Recovery from deadlock

* through preemption:
  - temporarily take a resource from its owner & give it to another process (manual intervention, thus difficult or impossible)

* through rollback:
  - have processes checkpointed (store state data to a file for recreation) periodically

* through killing processes:
  - best to kill ones that can be rerun from the beginning w/o ill effects (eg. compilation)


### 6.5 Deadlock avoidance

* resource trajectories
  - model out/graph the processes' turns & their shared resource region

* safe & unsafe states
  - __safe state__: one where there's a scheduling order in which each process can be run to completion, even if they all request their maximum resources immediately

* __banker's algorithm__: check if the request leads to an unsafe state, and deny the request if so
  - in practice, processes don't know their maximum resource needs

### 6.6 Deadlock prevention

* attacking the mutual-exclusion condition
  - don't assign resource exclusively  to a process (eg. read-only data)

* attacking the wait-and-hold condition
  - prevent processes holding resources from waiting for more resources, by having processes request all resources before execution; all or nothing
    + issue: processes don't know what they need until they start running, & this is suboptimal resource utilization

* attacking the no-preemption condition
  - virtualize resource (if it's a virtualizable resource; records aren't, for instance)

* attacking the circular wait condition
  - entitle a process to at most 1 resource at once (unacceptable restriction)
  - number all resources, and processes request resources in numerical order
    + con: maybe impossible to find an ordering to suit everyone

### 6.7 Other issues

* __two-phase locking__: [1] processes tries to lock the records it needs, one at a time, [2] if (1) succeeds, it performs the updates & releases the locks
  - in (1), if a record is in use, it releases all locks and starts again
  - con: only works if the programmer arranged things s.t. the program can be stopped at any time during (1)

* __competition synchronization__: independent processes competing for the same resources

* __communication deadlock__: 2+ processes try to communicate by sending messages (& messages could get lost, w/ another process depending on that message)
  - prevented with timeouts

* __protocol__: communication rules

* __host__: a user computer

* __router__: a specialized communications computer, moving packets of data from source -> destination (has a packet buffer)

* __livelock__: the states of the processes involved in the livelock constantly change with regard to one another, none progressing

* __starvation__: the longest jobs not getting chosen due to shorter jobs getting priority, getting postponed indefinitely
