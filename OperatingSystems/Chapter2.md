# Modern Operating Systems

## Chapter 2: Processes and Threads

* __process__: abstraction of a running program; an instance of an executing program
  - allows pseudo concurrent operations, essentially splitting one CPU into multiple virtual CPUs
  - process includes its program counter, registers, & variables, & therefore its own virtual CPU
  - are used to group things together, whereas threads are entities scheduled to be executed on the CPU

### 2.1 Processes

* __pseudo-parallelism__ is the contrast of __multiprocessor__ system's parallelism (multiple CPUs sharing the same physical memory)

* __multiprogramming__: rapidly switching between programs/processes

* these 4 principal events cause process creation:
  1. system initialization
  2. executing a process-creation system call by a running process
  3. a user request to create a new process
  4. initiation of a batch job

* __daemons__: background process to handle activity (eg. email, Web pages, printing, etc.)

* __`fork`__ creates an exact clone of the calling process
  - the parent & child processes have the same memory image, environment strings, and open files
  - they have distinct address spaces, the child's is a copy; no writable memory is shared

* ways to terminate a process:
  1. normal exit (voluntary)
  2. error exit (voluntary)
  3. fatal error (involuntary)
  4. killed by another process (involuntary)

* __process group__: a process, all of its children, and further descendants
  - signals are sent to all members of a process group assoc. with the keyboard, for instance, and each member can handle it their way

* an `init` process is in the boot image; it runs & tells the pc how many terminals there are, thereby forking a new process per terminal

* 3 process states:
  1. Running (using the CPU)
  2. Ready (runnable; temporarily stopped to let another process run)
  3. Blocked (unable to run until an external event happens, like I/O)

* 4 process transitions:
  1. process blocks for input
  2. scheduler picks another process
  3. scheduler picks this process
  4. input becomes available

* __process table__ a table (array of structures) the OS maintains, with one entry (__process control block__) per process
  - entry contains meta info about process state: program counter, stack pointer, memory allocation, status of open files, accounting & schedule info, and everything that needs to be saved when state switching

* __interrupt vector__: a location associated w/ each I/O class, containing the interrupt procedure's address

* __degree of multiprogramming__: CPU utilization = 1 - p^n, where p is the fraction of time a process is blocked, and _n_ is the number of processes

### 2.2 Threads

* __thread__: mini-processes within a process
  - allows parallel entities to share an address space & all of its data amongst itself, which is otherwise impossible
  - lighter weight than processes, faster and easier to create/destroy
  - better performance, esp. when doing a mix of substantial computing & I/O, since the activities can overlap
  - sometimes called light-weight processes
  - each thread has its own stack

* __cache__: a collection of data in memory, to eliminate trips to disk

* in a website example, __dispatcher__ thread reads request for work, then chooses an idle/blocked __worker thread__ to handle the request

* __finite-state machine__: a system design where each computation has a saved state, & there's a set of events that can occur to change the state

* process model is based on 2 concepts
  1. resource grouping
  2. execution

* states of a thread:
  - running
  - blocked
  - ready
  - terminated

* __Pthreads__: the threads package to write portable threaded programs

* __thread table__ a table to keep track of threads in processes, which each need their own private table, when threads are managed in user space

* user-level threads advantages & disadvantages
  - can have customized scheduling algorithm
  - the way blocking system calls are implemented
  - can run w/ existing operating systems

* kernel has one thread table to keep track of all threads in the system
  - greater cost to create/destroy threads because system calls are longer
    + -> recycling threads as a possible alleviator

* you can intertwine user & kernel threads; eg. putting multiple user threads on a kernel thread
  - user-level threads take turns using the kernel thread

* __scheduler activation__ mimics the functionality of kernel threads, but w/ better perf. & flexiility like the user space ones
  - avoids unnecessary transitions b/t user & kernel space
  - kernel assigns a # of virtual processors to each process, & lets (user-space) run-time system allocate threads to processes

* __upcall__: a notification causing the kernel to activate run-time system at a known starting address
  - occurs when thread is blocked & kernel lets run-time system know via params

* __pop-up thread__: arrival of a msg causes system to create thread to handle the msg
  - don't have history, etc. that needs to be restored, since they're new; thus quick creation

* threads can have private global variables

### 2.3 Interprocess communication (IPC)

* __race conditions__: when the result depends on which of the multiple processes run, since they're reading and writing common storage

* __mutual exclusion__: preventing more than 1 process from reading & writing shared data at once
  - __critical section__ / __critical region__: the shared memory
  - need 4 conditions to ensure this:
    1. no 2 processes may simultaneously be inside their critical regions
    2. no assumptions must be made about speeds or number of CPUs
    3.  no processes running outside its critical region may block any process
    4. no process should have to wait forever to enter its critical region

#### Mutual exclusion with busy waiting

* __busy waiting__: continuously testing a variable until some value appears
  - to be avoided since it wastes CPU time; should ensure the wait time will be short
  - __spin lock__: a lock using busy waiting

* method: disabling interrupts
  - have each process disable all interrupts before entering its critical region, & re-enable them before leaving it
  - cons
    + bad to give this kind of power
    + if it's a multiprocessor system, disabling the interrupts only affects 1 CPU

* method: lock variables
  - when a process wants to enter the critical region, it checks for the lock to be 0
  - cons:
    + this is also prone to race conditions b/c the 2nd process could modify the lock right after the 1st process finished its 2nd check

* method: strict alternation
  - each process takes turns locking and unlocking the critical region until they're done
  - cons
    + depending on how fast the process finishes, both processes could be outside of the critical region w/ the lock being 0, but only one has the go-ahead
    + bad if one process is a lot slower than the other

* method: Peterson's solution
  - uses lock variables & warning variables
  - cons
    + processes put their number in for a turn, but that place can be overwritten, so a process may not have its intent acknowledged, and have to wait until the other is done

* the Test and Set Lock (TSL) instruction
  - reads contents of the memory word _lock_ into register `rx` then stores a nonzero value @ memory address _lock_
    + (locks the memory bus)
    + no other processor can access the memory word until the instruction is finished

* __priority inversion problem__: a process 1 starting busy waiting, when process 2 is in the critical region, but process 2 isn't scheduled while process 1 is busy waiting, so process 2 never leaves its critical region
  - -> process 2 loops forever

* `sleep` causes the caller to be blocked until another process wakes it up

* `wakeup` wakes up the specified process

#### Producer-consumer problem

* __producer-consumer problem__ / __bounded-buffer problem__: 2 processes sharing a fixed-size buffer; one (producer) puts info in, the other (consumer) takes it out
  - potential issues:
    + producer wants to add to buffer, but full; so producer sleeps until consumer has removed items
    + consumer wants to remove from buffer, but empty; so sleeps until producer has added items
  - cons:
    + -> race conditions since we have a variable keeping track of count in buffer
    + if a wakeup is sent to a process that isn't sleeping yet, the intent is lost
      - could be fixed with a __wakeup waiting bit__

* __semaphore__: an integer to count the # of wakeups
  - 2 operators: `down` (like `sleep`) & `up` (like `wakeup`)
  - checking/changing the value, & going to sleep, are all done as a single indivisible __atomic action__
  - can be used to solve the producer-consumer problem
  - 2 semaphores: mutex semaphore for mutual exclusion, full and empty semaphore for synchronization
    + __synchronization semaphore__ ensures certain event sequences don't occur

* __binary semaphores__: semaphores initialized to 1 & used by 2+ processes to ensure that only 1 of them can enter its critical region at the same time

#### Mutexes

* __mutex__: a shared variable w/ 2 states: unlocked or locked
  - if it's locked when a thread calls `mutex_lock`, it's blocked until the critical region's thread is done
    + w/ multiple blocked threads, one is chosen randomly
  - when a thread fails to acquire a lock, it calls `thread yield` to give up the CPU to another thread -> no busy waiting

* multiple processes sharing a common address space never have the efficiency of user-level threads since the kernel is deeply involved in their management

* fast user space mutex (__futex__) implements basic locking like a mutex, but avoids dropping into kernel unless really needed
  - switching to kernel & back is really expensive
  - 2 parts: kernel service (has a wait queue for blocked processes) & a user library

* mutexes in pthreads
  - pthreads have functions to synchronize threads
  - __condition variables__: a pthread synchronization mechanism allowing threads to block due to a condition not being met
    + have no memory, so if a signal is sent to a condition variable that no thread is waiting on, the signal is lost

#### Monitors

* __monitor__: higher-lvl synchronization primitive; a collection of procedures, variables, & data structures grouped in a module/package
  - processes can call the procedures in a monitor, but can't access its internal data structures from procedures declared outside the monitor
  - only one process can be active in a monitor at once
  - uses condition variables, `wait`, & `signal` to decide when to block a process/let another enter
  - they make parallel program less error-prone than using semaphores
  - cons:
    + are a high-lvl programming-language concept, & the compiler needs to recognize them

#### Message passing

* __message passing__: uses 2 primitives, `send` and `receive`, which're system calls;
  - cons:
    + messages can be lost by the network
      - preventable by ensuring receiver sends back an acknowledgment message upon receival, so sender can retransmit if necessary
    + acknowledgment could be lost, so sender receives acknowledgment twice
      - solvable by numbering them, so can check for duplicates
    + copying messages between processes is slower than a semaphore operation or entering a monitor

* __mailbox__: a place to buffer a specified # of messages, so you can prevent bombarding a process w/ messages, b/c a mailbox can get filled up

* __rendezvous__: a strategy where the receiver is blocked until a send happens

* __barrier__: dividing apps into phases, w/ a rule that processes cant proceed into the next phase until all are ready to proceed, using barriers, for synchronization

#### Avoiding locks: Read-Copy-Update (RCU)

* __RCU__ decouples the removal & reclamation phases of the update
  - has all processes read the same old value, or the same new value; need to wait for all to read the data structure
  - __read-side critical section__: the data structure that the readers all try to access
  - requires defining a __grace period__ that we'd know all threads to be out of the read-side critical section at least once

### 2.4 Scheduling

* __scheduler__s use __scheduling algorithms__

* __CPU-bound__ / __commute-bound__: processes spending most of their time computing

* __I/O-bound__: processes spending most of their time waiting for I/O

* __non-preemptive scheduling algorithm__ one which picks a process to run & lets it run until it blocks

* __preemptive scheduling algorithm__ one which lets a process run for a maximum of a fixed time, before selecting another

* categories of scheduling algorithms (due to different environments):
  - batch
    + used a lot in business (payroll inventory, interest calculation, & other periodic tasks)
    + non-preemptive algorithms are fine here; or preemptive ones w/ long wait times
  - iterative
  - real time
    + real-time constraints (eg. user needs to be serviced) -> preemptive is better

#### Scheduling algorithm goals

* for _all systems_:
  - fairness: give each process fair share of CPU
  - policy enforcement: ensure stated policy is carried out
  - balance: keep all parts of system busy

* for _batch systems_:
  - __throughput__: maximize jobs per hr
  - __turnaround time__: minimize time b/t submission & termination
  - CPU utilization: keep CPU busy all time

* for _interactive systems_:
  - __response time__: respond to requests quickly
  - __proportionality__: meet users' expectations

* for _real-time systems_:
  - meeting deadlines: avoid losing data
  - predictability: avoid quality degradation in multimedia systems

#### Scheduling in batch systems

* __first-come, first-served__

* __shortest job first__

* __shortest remaining time next__ (run time needs to be known in advance)

#### Scheduling in interactive systems

* __round-robin scheduling__: each process is assigned a time interval (its __quantum__) in which it's allowed to run
  - need to make the quantum large enough to justify the __process switch__ / __context switch__

* setting the quantum too short causes too many process switches and lowers the CPU efficiency
  - setting it too long may cause poor response to short interactive requests
  - around 20–50 msec is a reasonable compromise

* __priority scheduling__
  - can combine algorithms, eg. group processes into priority classes and use priority scheduling among the classes but round-robin scheduling within each class

* __multiple queues__ incrementing the amount of quanta a process gets each time it's given a turn to run (eg. 1, 2, 4, 8, 16, etc.)

* __shortest process next__
  - could make estimates based on past behavior

* __guaranteed scheduling__: make promises to users about how much CPU they'll get, eg n users get 1/n CPU power

* __lottery ticket scheduling__ (amongst users)

* __fair-share scheduling__ (amongst users)

#### Scheduling in real-time systems

* __hard real-time system__: one in which absolute deadlines must be met

* __soft real-time system__: one in which missing an occasional deadline is tolerable

* there're static & dynamic scheduling algorithms; the former is only good if you have perfect info of how long work will take

#### Policy vs. Mechanism

* often the scheduler doesn't make the best choice; the main process could have a better idea of which child processes are most important
  - solve by separating the __scheduling mechanism__ from the __scheduling policy__
    + scheduling algorithm is parameterized, but user processes can fill them in

#### Thread scheduling

* thread scheduler inside process; kernel isn't aware of it; round-robin & priority scheduling are most common here

### 2.5 Classical IPC problems

* __starvation__: programs run indefinitely but fail to make progress, often caused by __deadlock__
