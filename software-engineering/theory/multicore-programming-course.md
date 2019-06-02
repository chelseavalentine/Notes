# Multicore Programming course

Notes from taking NYU's Multicore Programming course.

# Week 1

### Lecture 1: The Multicore Revolution

* why we have multicore processors: CPU instructions are divisible
  * fetch -> decode -> execute -> memory -> commit
  * plus, there are multiple clocks per instruction (load, add, conditional branch)
* pipelining instructions gives you temporal parallelism, and the programmer doesn't need to do anything
  * but sometimes there're dependencies between instructions (e.g. `if` statement determines what's done) & unnecessary work is done in that pipeline
    * improvable via:
      * branch prediction (for conditional branch outcomes)
      * hazard detection (if instruction `n` needs `n + 1`, freeze the pipeline until it's ready)
      * forwarding logic (reduce the cost of hazard detection)
* can improve pipelines via duplicating functionality w/ redundant units to handle instructions
  * **instruction-level parallelism:** using redundant units to handle more instructions as they become ready/need to be executed
* there's a clock speed limit, therefore need to take advantage of multiple cores for performance
* **Moore's Law:** there's a limit to power density, therefore expect smaller gains
* therefore, get modern performance via hyperthreading, multiple cores, wider & deeper caches

#### Challenges of parallel programming/execution

* communication cost
* synchronization cost
* not all problems are amenable to parallelization
* hard to think in parallel
* much harder to debug

## Week 2

### Lecture 2: Parallelism, Concurrency, and Performance

#### Parallelism and concurrency

* **concurrency:** 2 or more tasks making progress in the _same timeframe_
  * implementable on a single processing unit
  * conceptually more general than parallelism
* **parallelism:** 2 or more tasks executing at _literally the same time_
  * requires hardware with multiple processing units

#### Amdahl's Law

[Speed achievable with _P_ processors] = 1 / [ F + ( (1 - F) / P ) ], where F = fraction of sequential work

* having faster cores makes more sense than having many cores
* it's not easy to find the sequential portion, _F_
* **Gustafson's Law:** computations involving arbitrarily large datasets can be efficiently parallelized
* limitations of both Amdahl's and Gustafson's laws:
  * they don't take into account synchronization, communication, OS, etc.
  * the load may not be balanced among cores

#### DAG model for multithreading

**Work Law**

* **Work Law:** Tp >= (T1 / P)
  * where:
    * work = T1 = total time spent on all instructions
    * Tp = fastest possible execution time on P processors
* **Span Law:** Tp >= T∞
  * where span = longest path of dependence in the DAG = T∞

therefore, **parallelism:** ( Work / Span ) = ( T1 / T∞ )

#### Types of Parallelism/Granularities

* **Instruction-level parallelism:**
  * pipelining capabilities of the CPU are responsible for parallelizing individual execution
  * is **superscalar** (several instrucftions are loaded at once, as far as possible, and are executed simultaneously)
  * out-of-order, speculative execution
* **Basic block parallelism:**
  * compiler or CPU may find ways to run multiple blocks in parallel
* **Loop parallelism:**
  * the compiler detects parallel opportunities in multiple independent iterations of the same loop, and parallelizes accordingly
* **Task/Thread parallelism:**
  * the OS or user-level library provides support for running tasks in parallel – most frequently via threading
    * requires support for atomic operations for locking
  * multicore
  * hyperthreading (aka **SMT, Simultaneous Multithreading**)
* **Process-level parallelism:**
  * the OS must support running and scheduling multiple processes in parallel, regardless of the number of cores available
  * requires a multiprocessor system and multiple cores
  * hyperthreading

#### Flynn Classification

* classifies computer architectures by their instruction and data parallelism

|                   | **Single instruction** | **Multiple instruction** |
| ----------------- | ---------------------- | ------------------------ |
| **Single data**   | SISD                   | MISD                     |
| **Multiple data** | SIMD                   | MIMD                     |

* MISD processor architectures are unusual since they run the same stream of instructions over the same data in multiple cores/execution units
  * usually done to check the correctness of the results
  * used in mission-critical systems (e.g. spacecrafts, missiles, nuclear power plants, etc.)

#### Programming models and performance

##### PRAM (Parallel Random Access Machine) model

* has shared memory & synchronous MIMD
* no communication cost & infinite memory
* this model is *unrealistic AF*, therefore it makes for inaccurate performance predictions

##### LogP model

* uses distributed memory
* no specification of interconnection network between memories
* is based on latency of communication, overhead in message transmission, bandwidth limitations, and processing power
* con: doesn't take contention into account, therefore it can underestimate communication time

##### BSP (Bulk Synchronous Parallel) model

* consists of:
  * processor-memory pairs
  * communication network deliverying messages point-to-point
  * barrier synchronization for all/a subset of the processes
* BSP programs are composed of supersets, which're composed of: [1] computation -> [2] communication -> [3] synchronization
* pros:
  * predicatable
* cons:
  * not good if locality is important
  * no distinguishing between sending 1 message of length _M_ or _M_ messages of length 1

## Week 3

### Chip Multiprocessor Architecture: Chapter 1.1 – 1.2

* **Chip Multiprocessor (CMP):** (aka multicore processor) a group of uniprocessors integrated onto 1 processor chip, so they act as a team made up of cores
  * separate processors are visible to programmers as entities, therefore we can now parallel program!

### Lecture 3: Understanding Hardware

* **coprocessors:** optional chip supplementing the CPU
  * it catches specific instructions in the instruction stream
  * e.g. GPUs, audio codecs, etc.

#### Multicore hardware

* 2 main data characteristics:
  * temporal locality (used recently)
  * spatial locality (used nearby)
* there's a processor-memory performance gap growing by 50% per year
* cache coherence is needed in multicore processors to ensure consistency
  * **snooping cache coherence:** cores share a bus, and any signals transmitted on the bus can be seen by all cores
  * **directory-based cache coherence:**:
    * uses a data structure called a "directory" to store statuses of each cache line
    * on updates, the directory is consulted, and then the cache controllers of the cores have that line in their caches invalidated
* tools you can have as a programmer to achieve better performance:
  * the number of threads you spawn at any given time
  * thread granularity
  * user thread scheduling
  * localities
  * Throughput?
  * latency?
  * Bandwidth-delay product
  * best performance for a configuration vs. scalability

#### Performance and hardware

* if you need to use a linked data structure, preallocate contiguous memory blocks for its elements and serve future insertions from this pool
* can you reuse notes from your linked list?

#### Diet threads

* **process:** has a unique private address space and its own execution stack
  * is a kernel-level unit of execution
  * communicates through: shared memory or pipes
* **thread:** has a shared address-space with thread-local stacks and has its own execution stack
  * is a kernel- or user-level unit of execution
  * communicates through: mutices, condition variables, semaphores, pipes, or shared memory
* **mutex ("mutual exclusion"):** locks used for exclusive access to any shared resource(s)
  * programmer defines the protected resource and is responsible for locking and unlocking access to the resource
* **condition variables:** gives synchronization based on data values and is always used with a mutex
  * replaces these steps: [1] lock mutex, [2] check value of data, [3] unlock
* **semaphore:** a counting mutex that atomically increases or decreases

## Week 4

### A Primer on Memory Consistency and Cache Coherence: Chapter 2

* coherence invariants:
  * **Single-Writer, Multiple-Read (SWMR) invariant:**
    * for any memory location A, at any given (logical) time, there exists only a single core that may write to A (and also read it), OR some number of cores that may only read A
  * **Data-Value invariant:**
    * memory location value at the start of an epoch is the same as the value of the memory location at the end of its last read-write epoch

### Lecture 4: Parallel Programming and `pthreads`

#### Programming for multicore systems

* Cobegin/coend marks portions where several "threads" of execution are allowed
* coroutines execute part of a task; is used for concurrency (and possibly parallelism)
* Fork/join: any task can start another task
  * parent and child execute concucrrently (and possibly in parallel)
  * parent may wait for the child to finish

#### Threads

* hardware "contexts": a thread's current state includes
  * PC
  * stack
  * registers/flags
  * memory map

##### `pthreads` concepts

* pthreads need explicit synchronization (via pthread_create, _join, _detach, _exit etc.)

## Week 5

### Lecture 5: Threads and `pthread`s, pt 2

#### Peterson's Algorithm

Peterson's algorithm is about mutual exclusion by using busy-waiting.

```c++
int victim;
bool flag[2] = {false, false};

void lock(void) {
  int me = my_tid % 2;
  int other = 1 - me;
  
  flag[me] = true;
  victim = me;
  
  while (flag[other] && victim == me) {
    // no-op
  }
}

void unlock(void) {
  int me = my_tid % 2;
  
  flag[me] = false;
}
```

* assumes:
  * number of threads is known a priori 
  * program execution is strictly sequential and each instruction is atomic
  * need to use `int` instead of `bool` w/ more than 2 threads

#### Semaphores

* **semaphore:** a synchronization primitive enabling waiting without busy waiting
  * `down()` checks that the value is > 0, decrements it, and allows the thread to continue
    * otherwise the thread is suspended until the counter is > 0
  * `up()` automatically increments the counter
* considerations:
  * incurs a system call each time you sleep instead of busy wait
    * avoidable if you use shared memory to store the state of the lock and ask the OS to sleep until the shared memory value changes, in the contended case

#### Condition variables

* lets thread wait for a given predicate to change and uses a mutex to protect the predicate state
  * `wait()` atomically suspends the thread and unlocks the associated mutex
  * `signal()` if there's 1+ suspended thread, it dequeues it and resumes execution atomically
  * `broadcast()` resumes execution for all suspended threads so that they can contend

### Homework 2

* L2 and L3 caches are still good b/c they're faster than main memory and disk, and are larger than the L1 cache
* `join()`ing a thread makes the current thread block until that thread terminates or `exit()`s

## Week 6

### Lecture 6: Coordinating resources

#### Coordinating resources

* **Reader-Writer Problem:** multiple threads want to read or write to a resource
  * Ideal: reader lock shared w/ other resources, and a writer lock allowing an exclusive writer. Locks work together for resource consistency.
  * `flock(file_handle, mode)` allows file locking between processes and threads
  * best implementation includes counts of the active readers, active writers, pending readers, and pending writers
    * maintaining fairness w/ counts can cause contention
* can use a barrier to synchronize a group of threads at a point
  * use mutex or semaphore to count arrivals and hold threads until `count = num_threads`
  * `pthread` has a barrier

## Week 7

### Lecture 7: Synchronized structures, pt 1

#### Lock-based concurrent queue

* there're scoped locks that are automatically unlocked when destroyed
* `enqueue()` and `dequeue()` should be protected under the same lock

#### Lock-free concurrent queue

* uses a sentinel (dummy node that's always first in the list, at the head)
  * allows enqueue and dequeue to touch distinct portions of the state

##### Compare-and-swap

* CAS (address, expected_value, desired_value)
  * atomically checks if the address has the expected value and sets the value if so and then returns true; otherwise returns false

#### The ABA problem

* referencing a given node, but the item at that address changes but looks the same because the memory is reused for a new node
* pointers are spatially unique, but not temporally unique
* solutions:
  * additional pointer info (version our 128-bit points w/ 64-bits for the target address and 64-bits for the counter)
  * free-list tracking
    * track "freed" pointers in a list with an associated counter, and increment on reuse



## Final review

### Synchronized structures, pt 2

#### Concurrent ordered list

* **deletion hazard:** how can we do a concurrent insertion and deletion and ensure that an insertion can be done when it's being inserted after a node being deleted?
  * **deletion mark:** combine the node's `next` pointer and a deletion mark (bit)
    * why? if the deletion is set, a CAS operation against the original pointer (which has its lower bits unchanged) will fail

#### Concurrent hash tables

* **lock striping:** single-bucket lock granularity
  * no normal operation on the hash table affects all buckets (resizing does)
  * even lookups need locking
  * w/ lock striping, need to rehash all members b/c change to # of nuckets mandates that; have must grab all locks to resize
* better hash table w/ our lock-free lists: hash keys with modulo (`%`) & reverse its bits so they stay in order after resizes; buckets just include `n + 1` bits per bucket with each resize
  * combine buckets into a single ordered list, w/ references to starts of each bucket in an arary
  * you can have concurrency during resize if you add an additional layer of indirection from the old bucket array to the new one
* **recursive split ordering:** a new ordering on keys that keeps items in a given bucket adjacent in the list throughout the splitting process

### Multicore correctness

#### Bugs

* types: rare conditions
* deadlocks
* atomicity violations
* ordering violations
* group coordination violations
* timing dependencies
* inconsistent lock ordering can lead to deadlocks, therefore lock and unlock locks in reverse order

#### Detecting and reproducing bugs

* **static analysis:** inspect during compilation
* **dynamic analysis:** inspect during runtime
  * catches more than static analysis, because shared variables (e.g. pointers) aren't always static
    * also: anything dealing with non-deterministic input (I/O) can't be captured with static analysis
* **"happens-before" graphs:** display and analyze code by delimiting blocks of code by synchronization primitives (e.g. locks) when comparing thread interleavings
* **locksets:** a record for the locks used with each variable (should be using the same locks with each variable access)
* scheduler-based analysis framework is used to catch Heisenbugs
* reduce the number of preemptions in analyzed code (granularity of code blocks being tested) to tame state explosion, since analysis tries to follow possible paths

### Multicore performance evaluation

#### Performance metrics

* Response time = latency
* Throughput = average execution rate
* elapsed time isn't a good metric for comparison purposes
  * elapsed time consists of: I/O, CPU (User CPU time and System CPU time), disk, and memory time
* machine performance: performance = 1 / execution_time
  * often, we use cycles instead of seconds when measuring execution time
* Clock rate (frequency) = cycles/second
  * 1 Hz. = 1 cycle / sec
* ET = IC x CPI x CT
  * seconds/program = cycles/program * seconds/cycle
  * ET = seconds/program, IC * CPI = cycles/program, CT = seconds/cycle
  * ET = execution time, IC = instruction count, CPI = cycles per instruction, CT = cycle time
* if you have a 4Ghz processor, cycle time is ( 1 / (4 x 10^9) )
* a floating-point-intesive program may have higher CPI
* Millions of Instructions Per Second (MIPS) is higher for programs with simple instructions

##### For multithreaded programs

* IPC isn't consistent because timing variations lead to different excecution times
  * the order that threads enter critical sections may vary
  * different interrupt timing leads to different scheduling decisions
* total number of instructions executed may vary across runs
  * effect increases with the number of cores because system-level code accounts for a significant fraction of the total execution time
* program doesn't run in a vacuum, so the normalized progress a program takes is:
  * Normalized Progress = T^SP / T^MP
    * T^SP = time when running in isolation
    * T^MP = time when running with other programs
* harmonic mean (hmean) is derived by executing all benchmarks for the same amount of instructions
* arithmetic meaning is derived from executing all benchmarks for the same amount of cycles

##### Benchmarks

* benchmarks allow you to:
  * explore architectural designs
  * identify bottlenecks
  * compare different systems
  * conduct performance predictions

##### Bottlenecks in multithreaded applications

* **bottleneck:** any code segment for which threads contend (i.e. wait)
  * examples: Ambdhal's serial portions, critical sections, barriers, pipeline stages
    * different states of a loop iteration may execute on different threads, and the slowest stage makes others wait
* the limiting bottlenecks change over the time of a program running

##### Good performance metrics?

* execution time matters: system, CPU, I/O, and memory time
* IPC (or CPI) isn't a good measure for multithreaded applications
* for parallel applications, system throughput and average normalized trunaround time are good measures

### Homework 3

#### Linearizability

* **linearizability** means that the immediate state is never visible outside of the code performing a particular option
  * linearizable sets of operations can be put in a total order without overlapping instructions
  * e.g. lock-free queues work suth that every operation sees newly-pushed and newly-popped elements as either (1) not yet in the queue, (2) completely in the queue, or (3) already removed from the queue
    * CAS allows this becaues pointers are atomically changed if no simultaneous operation has modified its state at the same time

#### Pros and cons of CAS

* `[+]` may have better progress because there's always at least one thread progressing
* `[-]` harder to reason about because you can't linearize multiple statements like you can with a mutex
* `[-]` ABA problem

### Heterogeneous multicore

#### Reasons for heterogeneous multicore

* CPU designs should balance single-thread performance w/ throughput (multicore performance)
* is more efficient
  * matches application to core(s) with capabilities
  * can provide better area-efficient coverage of workload demand
  * more efficient, meaning less power consumption
* matching application type and cores:
  * traditional multicore (latency-optimized cores) is good for applications w/ fewer, sophisticated cores
  * large # of throughput-optimized cores are good for applications w/ more, simpler threads (e.g. GPUs)

#### Issues in heterogeneous multicore processors

* scalability
* scheduling
* task-switching cost
* task-migration cost
* coherence
* core-switching decisions
* memory model (shared memory)

#### Programming heterogeneous multicore CPUs

* programmers typically think about algorithms, correctness, and thread partitioning
* programmers neglect asymmetry (unexpected/unpredictable workload behavior)
  * do so by evaluating threads' work partitioning and automatically schedule asymmetric threads
* can bind threads to cores if necessary, and avoid wasted waiting caused by load imbalance

##### Load imbalance sources

* Single-processor performance is poor because memory inefficiencies lead to optimizing for caching
* if a thread's lifetime is too short, then there's too much parallelism overhead
* different amounts of work per core
* different resources per processor

##### Solutions to load imbalance

* centralized/distributed task queues
* work stealing and work pushing
* offline scheduling and self-scheduling

### Transactional memory

* **transactions** allow queues to execute as if it were the only computation accessing the database
  * are serializable because query results are indistinguishable from serial execution
  * are ACID compliant (Aotmicity, Consistency, Isolation, Durability)
    * **durability:** once a transaction commits, its results are permanent

#### Uses for transactional memory

* programmers
* compiler designers when implementing high-level language features
* general parallel programming
  * error recovery
  * real-time programming
  * multitasking

#### Relaxed vs. atomic transactions

* **relaxed transactions:**
  * other code can't see intermediate results from a relaxed transaction
  * any operation is allowed
  * can't be cancelled
  * doesn't see other code's intermediate results
  * acts as if all relaxed transactions use a single mutex (serialization)
* **atomic transactions:**
  * other code can't see intermediate results
  * doesn't see intermediate results of other code
  * cancelleable
  * has limited subset of transaction-safe operations

##### Conflict resolution: optimistic vs. pessimistic concurrency control

* Transactional Memory (TM) system detects and resolves conflicts by aborting, delaying or repeating 1 or more transactions
* **pessimistic concurrency control:** upon conflict detection, it allows 1 thread to continue and delays other threads' transactions
* **optimistic concurrency control:** upon a conflict detection, the TM system resolves it by reexecuting 1 or both transactions
* you can have combos of optimistic and pessimistic reads and writes

##### Version management

* **eager version management:** transactions directly modify memory, but it maintains an undo log of overwritten data
  * requires pessimistic concurrency control
* **lazy version management:** transactions record redo logs of writes to apply & updates are delayed until the transaction commits
  * works with optimistic concurrency control

##### Software Transactional Memory (STM)

* compiler instruments code with transaction prolog, epilog, & read/write functions
* runtime tracks memory accesses, detects conflicts, and commits/aborts execution
* components: transaction descriptor (per transaction), undo log, redo log, readset, writeset

##### Hardware Transactional Memory (HTM)

* 3 flavors: full hardware TM, software, TM and hardware TM
* identifies memory locations for transactional accesses and manages readsets & writesets of transactions
* coherence messages trigger conflict detection and nearly all HTMs use eager conflict detection

####  STM advantages over HTM

- software is more flexible over hardware
- integrates easier with existing systems & language features (e.g. garbage collection)
- fewer intrinsic limitations imposed by fixed-sized hardware structures (e.g. caches)

#### HTM advantages over STM

- executes applications w/ lower overhead
- is less reliant on compiler optimizations to provide performance
- has better power & energy profiles
- all memory accesses in a transaction are implicitly transactional
- strong isolation without changing non-transactional memory accesses
- well-suited to lower-level languages without dynamic compilation, garbage collection, etc.

#### Transactions: conclusions and benefits

- trades bandwidth for simplicity and latency tolerance
- transactions elimitate locks b/c they're inherently atomic
  - therefore catches most parallel programming errors
- shared memory coherence & consistency are simplified