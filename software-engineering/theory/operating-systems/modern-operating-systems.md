# Modern Operating Systems

## Chapter 1: Introduction

- __operating system__: a layer of software to abstract away resource (network, I/O devices, RAM, etc.) management
  - runs in __kernel mode__ (aka _supervisor mode_), giving it permission to access to the hardware and everything else
  - _user mode_: what everything else runs in

### 1.1 What is an operating system?

- operating system functions:
  1. abstracting resources & providing them to the user
  2. managing hardware resources
- _architecture_: instruction set, memory organization, I/O, bus structure
- resource management:
  - __multiplexing__ (sharing) resources: many users sharing time or space of a resource

### 1.2 History of Operating Systems

- __multiprogramming__: allowing multiple jobs to use the memory by partitioning memory & sharing (avoids long idle time)
- __spooling__: loading a new job into the disk after a running job finished
- __timesharing__: programmers sharing a computer via an online terminal (variant of multiprogramming)

### 1.3 Computer hardware review

- registers visible to programmers:
  - __program counter__: contains next instruction's memory address
  - __stack pointer__: points to top of current stack in memory
  - __PSW__ (Program Status Word): contains condition code bits (set by comparison instructions, CPU priority, mode, etc.)
- main memory is divided into cache lines
- __virtual memory__ allows computers to run programs larger than physical memory by putting them on a disk & using main memory as a cache for the most heavily executed parts
  - requires mapping memory addresses on the fly to convert the address the program generated to the physical address in RAM where the word is located
    - the __MMU__ (Memory Management Unit) does this; it's a part of the CPU
- __device driver__: the software talking to the controller; gives controller commands & accepts responses
  - need a different driver for each OS
  - is put into the OS to be run in kernel mode; but can run outside of the kernal
- __busy waiting__: the operation where after I/O, the driver puts the data wheere its needed & then returns; then control is returned to the caller
  - the CPU continuously polls the device until it's done
- __interrupt__: completion is signalled this way; until an interrupt is received,  the CPU looks for other work to do
- __interrupt vector__: the part of memory storing the address of the interrupt handler for a device (device # may be used as index)
- __DMA (Direct Memory Access) chip__: controls flow of bits b/t memory & a controller w/o constant CPU intervention
  - CPU tells DMA chip how many bytes to transfer, relevant device & memory addresses, & the direction
  - DMA causes an interrupt after it's done
- __USB (Universal Serial Bus)__ attaches slow I/O devices (eg. keyboard, mouse) to the computer
- Booting the computer (p. 34)

### 1.4 The operating system zoo

- __embedded operating systems__: systems (eg. microwaves, TVs, cars, MP3s, etc.) where you can't install untrusted software; all the software is in ROM
- __hard real-time systems__ provide absolute guarantees htat a certain action will occur by a certain time (eg. military applications, factory stuff, etc.)
  - __soft real-time systems__ may miss an occasional deadline (eg. smartphones, audio systems, etc.)

### 1.5 Operating system concepts

- __process__: a program in execution
  - __address space__: a list of memory locations from 0 to [a maximum], which the process can read & write;
    - contains the program, its data, & its stack as well
  - its resources, outstanding alarms, & a list of related processes, & other info are also associated w/ each process
- __process table__: an OS table including an array of structures, one for each current process
  - a suspended process has a __core image__ (its address space) * its process table entry (includes the process's meta data)
- after a certain # of seconds, the OS sends the process an __alarm signal__, forcing it to save its meta-data & start running a signal-handling procedure
- __UID (User IDentification)__ is assigned to each person authorized to use the system, by the system admin; every process stores a UID, as well as a GID (Group IDentification)
- __working directory__ = current directory, as opposed to the absolute path
- __special files__ are provided to make I/O devices look like files, so you an use system calls as usual
  - __block special files__ model devices consisting of randomly addressable blocks (eg. disks)
  - __character special files__ model printers, modems, & other devices accepting/outputting a character stream
- __pipe__: a pseuofile connecting two processes (process A writes to process B)

> If a user puts an ampersand after a command, the shell does not wait for it to complete. Instead it just gives a prompt immediately.

woaaahhh eg. `npm install &`

### 1.6 System calls

- fork returns a __PID (Process IDentifier)__, allowing you to distinguish the child and parent processes
  - the parent executes a `waitpid` system call, waiting just until any of its children terminates, and receives the child process' exit status
- UNIX processes are divided into 3 segments: [1] the text segment (ie. the program code), [2] the data segment (ie. the variables), & [3] the stack segment

### 1.7 Operating system structure

- a __reincarnation server__'s job is to check whether other servers & drivers are functioning correctly, and to replace them if not
- __client-server model__ consists of servers, which each provide a service, and the clients, which use those services
- __exokernel__: a program handling the allocation of resources to virtual machines, & checking to see that no machine is using another's resources
  - the bottom layer of a machine, running in kernel mode

### 1.8 The World According to C

- convention: name every constant, except for `-1`, `0`, and `1`
- you can give macros parameters, such as in

```c
#define max(a, b) (a > b ? a : b)

// ...
i = max(j, k + 1)
```

- `#` lines speak to the compiler
- __C preprocessor__: first pass of the C compiler
- __linker__ combines all of the output `.o` files into one executable binary file; library functions are also included, interfunction references resolved, and machine addresses relocated as needed

## Chapter 2: Processes and Threads

- __process__: abstraction of a running program; an instance of an executing program
  - allows pseudo concurrent operations, essentially splitting one CPU into multiple virtual CPUs
  - process includes its program counter, registers, & variables, & therefore its own virtual CPU
  - are used to group things together, whereas threads are entities scheduled to be executed on the CPU

### 2.1 Processes

- __pseudo-parallelism__ is the contrast of __multiprocessor__ system's parallelism (multiple CPUs sharing the same physical memory)
- __multiprogramming__: rapidly switching between programs/processes
- these 4 principal events cause process creation:
  1. system initialization
  2. executing a process-creation system call by a running process
  3. a user request to create a new process
  4. initiation of a batch job
- __daemons__: background process to handle activity (eg. email, Web pages, printing, etc.)
- __`fork`__ creates an exact clone of the calling process
  - the parent & child processes have the same memory image, environment strings, and open files
  - they have distinct address spaces, the child's is a copy; no writable memory is shared
- ways to terminate a process:
  1. normal exit (voluntary)
  2. error exit (voluntary)
  3. fatal error (involuntary)
  4. killed by another process (involuntary)
- __process group__: a process, all of its children, and further descendants
  - signals are sent to all members of a process group assoc. with the keyboard, for instance, and each member can handle it their way
- an `init` process is in the boot image; it runs & tells the pc how many terminals there are, thereby forking a new process per terminal
- 3 process states:
  1. Running (using the CPU)
  2. Ready (runnable; temporarily stopped to let another process run)
  3. Blocked (unable to run until an external event happens, like I/O)
- 4 process transitions:
  1. process blocks for input
  2. scheduler picks another process
  3. scheduler picks this process
  4. input becomes available
- __process table__ a table (array of structures) the OS maintains, with one entry (__process control block__) per process
  - entry contains meta info about process state: program counter, stack pointer, memory allocation, status of open files, accounting & schedule info, and everything that needs to be saved when state switching
- __interrupt vector__: a location associated w/ each I/O class, containing the interrupt procedure's address
- __degree of multiprogramming__: CPU utilization = 1 - p^n, where p is the fraction of time a process is blocked, and _n_ is the number of processes

### 2.2 Threads

- __thread__: mini-processes within a process
  - allows parallel entities to share an address space & all of its data amongst itself, which is otherwise impossible
  - lighter weight than processes, faster and easier to create/destroy
  - better performance, esp. when doing a mix of substantial computing & I/O, since the activities can overlap
  - sometimes called light-weight processes
  - each thread has its own stack
- __cache__: a collection of data in memory, to eliminate trips to disk
- in a website example, __dispatcher__ thread reads request for work, then chooses an idle/blocked __worker thread__ to handle the request
- __finite-state machine__: a system design where each computation has a saved state, & there's a set of events that can occur to change the state
- process model is based on 2 concepts
  1. resource grouping
  2. execution
- states of a thread:
  - running
  - blocked
  - ready
  - terminated
- __Pthreads__: the threads package to write portable threaded programs
- __thread table__ a table to keep track of threads in processes, which each need their own private table, when threads are managed in user space
- user-level threads advantages & disadvantages
  - can have customized scheduling algorithm
  - the way blocking system calls are implemented
  - can run w/ existing operating systems
- kernel has one thread table to keep track of all threads in the system
  - greater cost to create/destroy threads because system calls are longer
    - -> recycling threads as a possible alleviator
- you can intertwine user & kernel threads; eg. putting multiple user threads on a kernel thread
  - user-level threads take turns using the kernel thread
- __scheduler activation__ mimics the functionality of kernel threads, but w/ better perf. & flexibility like the user space ones
  - avoids unnecessary transitions b/t user & kernel space
  - kernel assigns a # of virtual processors to each process, & lets (user-space) run-time system allocate threads to processes
- __upcall__: a notification causing the kernel to activate run-time system at a known starting address
  - occurs when thread is blocked & kernel lets run-time system know via params
- __pop-up thread__: arrival of a msg causes system to create thread to handle the msg
  - don't have history, etc. that needs to be restored, since they're new; thus quick creation
- threads can have private global variables

### 2.3 Interprocess communication (IPC)

- __race conditions__: when the result depends on which of the multiple processes run, since they're reading and writing common storage
- __mutual exclusion__: preventing more than 1 process from reading & writing shared data at once
  - __critical section__ / __critical region__: the shared memory
  - need 4 conditions to ensure this:
    1. no 2 processes may simultaneously be inside their critical regions
    2. no assumptions must be made about speeds or number of CPUs
    3. no processes running outside its critical region may block any process
    4. no process should have to wait forever to enter its critical region

#### Mutual exclusion with busy waiting

- __busy waiting__: continuously testing a variable until some value appears
  - to be avoided since it wastes CPU time; should ensure the wait time will be short
  - __spin lock__: a lock using busy waiting
- method: disabling interrupts
  - have each process disable all interrupts before entering its critical region, & re-enable them before leaving it
  - cons
    - bad to give this kind of power
    - if it's a multiprocessor system, disabling the interrupts only affects 1 CPU
- method: lock variables
  - when a process wants to enter the critical region, it checks for the lock to be 0
  - cons:
    - this is also prone to race conditions b/c the 2nd process could modify the lock right after the 1st process finished its 2nd check
- method: strict alternation
  - each process takes turns locking and unlocking the critical region until they're done
  - cons
    - depending on how fast the process finishes, both processes could be outside of the critical region w/ the lock being 0, but only one has the go-ahead
    - bad if one process is a lot slower than the other
- method: Peterson's solution
  - uses lock variables & warning variables
  - cons
    - processes put their number in for a turn, but that place can be overwritten, so a process may not have its intent acknowledged, and have to wait until the other is done
- the Test and Set Lock (TSL) instruction
  - reads contents of the memory word _lock_ into register `rx` then stores a nonzero value @ memory address _lock_
    - (locks the memory bus)
    - no other processor can access the memory word until the instruction is finished
- __priority inversion problem__: a process 1 starting busy waiting, when process 2 is in the critical region, but process 2 isn't scheduled while process 1 is busy waiting, so process 2 never leaves its critical region
  - -> process 2 loops forever
- `sleep` causes the caller to be blocked until another process wakes it up
- `wakeup` wakes up the specified process

#### Producer-consumer problem

- __producer-consumer problem__ / __bounded-buffer problem__: 2 processes sharing a fixed-size buffer; one (producer) puts info in, the other (consumer) takes it out
  - potential issues:
    - producer wants to add to buffer, but full; so producer sleeps until consumer has removed items
    - consumer wants to remove from buffer, but empty; so sleeps until producer has added items
  - cons:
    - -> race conditions since we have a variable keeping track of count in buffer
    - if a wakeup is sent to a process that isn't sleeping yet, the intent is lost
      - could be fixed with a __wakeup waiting bit__
- __semaphore__: an integer to count the # of wakeups
  - 2 operators: `down` (like `sleep`) & `up` (like `wakeup`)
  - checking/changing the value, & going to sleep, are all done as a single indivisible __atomic action__
  - can be used to solve the producer-consumer problem
  - 2 semaphores: mutex semaphore for mutual exclusion, full and empty semaphore for synchronization
    - __synchronization semaphore__ ensures certain event sequences don't occur
- __binary semaphores__: semaphores initialized to 1 & used by 2+ processes to ensure that only 1 of them can enter its critical region at the same time

#### Mutexes

- __mutex__: a shared variable w/ 2 states: unlocked or locked
  - if it's locked when a thread calls `mutex_lock`, it's blocked until the critical region's thread is done
    - w/ multiple blocked threads, one is chosen randomly
  - when a thread fails to acquire a lock, it calls `thread yield` to give up the CPU to another thread -> no busy waiting
- multiple processes sharing a common address space never have the efficiency of user-level threads since the kernel is deeply involved in their management
- fast user space mutex (__futex__) implements basic locking like a mutex, but avoids dropping into kernel unless really needed
  - switching to kernel & back is really expensive
  - 2 parts: kernel service (has a wait queue for blocked processes) & a user library
- mutexes in pthreads
  - pthreads have functions to synchronize threads
  - __condition variables__: a pthread synchronization mechanism allowing threads to block due to a condition not being met
    - have no memory, so if a signal is sent to a condition variable that no thread is waiting on, the signal is lost

#### Monitors

- __monitor__: higher-lvl synchronization primitive; a collection of procedures, variables, & data structures grouped in a module/package
  - processes can call the procedures in a monitor, but can't access its internal data structures from procedures declared outside the monitor
  - only one process can be active in a monitor at once
  - uses condition variables, `wait`, & `signal` to decide when to block a process/let another enter
  - they make parallel program less error-prone than using semaphores
  - cons:
    - are a high-lvl programming-language concept, & the compiler needs to recognize them

#### Message passing

- __message passing__: uses 2 primitives, `send` and `receive`, which're system calls;
  - cons:
    - messages can be lost by the network
      - preventable by ensuring receiver sends back an acknowledgment message upon receival, so sender can retransmit if necessary
    - acknowledgment could be lost, so sender receives acknowledgment twice
      - solvable by numbering them, so can check for duplicates
    - copying messages between processes is slower than a semaphore operation or entering a monitor
- __mailbox__: a place to buffer a specified # of messages, so you can prevent bombarding a process w/ messages, b/c a mailbox can get filled up
- __rendezvous__: a strategy where the receiver is blocked until a send happens
- __barrier__: dividing apps into phases, w/ a rule that processes cant proceed into the next phase until all are ready to proceed, using barriers, for synchronization

#### Avoiding locks: Read-Copy-Update (RCU)

- __RCU__ decouples the removal & reclamation phases of the update
  - has all processes read the same old value, or the same new value; need to wait for all to read the data structure
  - __read-side critical section__: the data structure that the readers all try to access
  - requires defining a __grace period__ that we'd know all threads to be out of the read-side critical section at least once

### 2.4 Scheduling

- __scheduler__s use __scheduling algorithms__
- __CPU-bound__ / __commute-bound__: processes spending most of their time computing
- __I/O-bound__: processes spending most of their time waiting for I/O
- __non-preemptive scheduling algorithm__ one which picks a process to run & lets it run until it blocks
- __preemptive scheduling algorithm__ one which lets a process run for a maximum of a fixed time, before selecting another
- categories of scheduling algorithms (due to different environments):
  - batch
    - used a lot in business (payroll inventory, interest calculation, & other periodic tasks)
    - non-preemptive algorithms are fine here; or preemptive ones w/ long wait times
  - iterative
  - real time
    - real-time constraints (eg. user needs to be serviced) -> preemptive is better

#### Scheduling algorithm goals

- for _all systems_:
  - fairness: give each process fair share of CPU
  - policy enforcement: ensure stated policy is carried out
  - balance: keep all parts of system busy
- for _batch systems_:
  - __throughput__: maximize jobs per hr
  - __turnaround time__: minimize time b/t submission & termination
  - CPU utilization: keep CPU busy all time
- for _interactive systems_:
  - __response time__: respond to requests quickly
  - __proportionality__: meet users' expectations
- for _real-time systems_:
  - meeting deadlines: avoid losing data
  - predictability: avoid quality degradation in multimedia systems

#### Scheduling in batch systems

- __first-come, first-served__
- __shortest job first__
- __shortest remaining time next__ (run time needs to be known in advance)

#### Scheduling in interactive systems

- __round-robin scheduling__: each process is assigned a time interval (its __quantum__) in which it's allowed to run
  - need to make the quantum large enough to justify the __process switch__ / __context switch__
- setting the quantum too short causes too many process switches and lowers the CPU efficiency
  - setting it too long may cause poor response to short interactive requests
  - around 20–50 msec is a reasonable compromise
- __priority scheduling__
  - can combine algorithms, eg. group processes into priority classes and use priority scheduling among the classes but round-robin scheduling within each class
- __multiple queues__ incrementing the amount of quanta a process gets each time it's given a turn to run (eg. 1, 2, 4, 8, 16, etc.)
- __shortest process next__
  - could make estimates based on past behavior
- __guaranteed scheduling__: make promises to users about how much CPU they'll get, eg n users get 1/n CPU power
- __lottery ticket scheduling__ (amongst users)
- __fair-share scheduling__ (amongst users)

#### Scheduling in real-time systems

- __hard real-time system__: one in which absolute deadlines must be met
- __soft real-time system__: one in which missing an occasional deadline is tolerable
- there're static & dynamic scheduling algorithms; the former is only good if you have perfect info of how long work will take

#### Policy vs. Mechanism

- often the scheduler doesn't make the best choice; the main process could have a better idea of which child processes are most important
  - solve by separating the __scheduling mechanism__ from the __scheduling policy__
    - scheduling algorithm is parameterized, but user processes can fill them in

#### Thread scheduling

- thread scheduler inside process; kernel isn't aware of it; round-robin & priority scheduling are most common here

### 2.5 Classical IPC problems

- __starvation__: programs run indefinitely but fail to make progress, often caused by __deadlock__

## Chapter 3: Memory management

### 3.1 No memory abstraction

- without abstraction, could have at most 1 program in memory, because otherwise another program could overwrite memory addresses another program was using and cause a crash
  - can get some parallelism by programming w/ multiple threads (since all threads in a process see the same memory image)
- __BIOS__ Basic Input Output System
- __swapping__: running multiple programs concurrently by saving the entire memory to disk, and then bringing in the next program
  - doesn't work b/c a program may jump to a part of memory which is where the next program is, and cause a crash, since both reference physical memory
- __static relocation__: modify the 2nd program on the fly as it loads into memory, by adding the memory address of the program to every instruction
  - slows down loading

### 3.2 A memory abstraction: address spaces (216)

- __address space__: the set of addresses a process can use to address memory

#### Base and limit registers

- __dynamic relocation__: mapping each process' address space onto a different part of physical memory
  - when process is run, base register gets physical address where program begins, & limit register gets the length ofthe program
  - CPU automatically adds the base value to the address, each time the process references memory
  - con: additions are slow due to carry-propagation time, unless using special addition circuits

#### Swapping

- __virtual memory__ allows programs to run even when only partially in memory memory
- __memory compaction__: moving all processes downward as far as possible, when swapping creates multiple holes in memory
  - isn't done b/c requires a lot of CPU time

##### Managing free memory

Two ways of keeping track of memory usage: [1] bitmaps, [2] free lists

##### Memory management with bitmaps

- memory is divided into allocation units.
  - the smaller the unit, the larger the bitmap
  - the bigger the unit, the greater chance that appreciable memory is wasted in the last unit of the process if the process size isn't an exact multiple of the allocation unit
- con of bitmaps: searching a bit map for a run of a given length is slow because the run may straddle word boundaries in the map

##### Memory management with linked lists

- maintaining a linked list of allocated and free memory segments
  - a segment either contains a process or is an empty hole b/t two processes
  - if the segment list is kept sorted by address, it's straightforward to update the list when a process is terminated or swapped out
- algorithms to allocate memory for a created process/swapped in process:
  - __first fit__: scan list of segments until find big enough hole
  - __next fit__: a variation of first fit where it keeps track of last-found suitable hole to search from where it left off
    - slightly worse performance than first fit
  - __best fit__: search entire list for smallest adequate hole
    - obv slower than first fit, but also results in more wasted memory than either b/c fills memory w/ tiny, useless holes
  - __worst fit__: looking for worst fit, so it leaves a large enough hole to be useful
    - also a crappy idea
  - __quick fit__: maintains separate lists for common sizes requested
    - when process terminates/is swapped out, looking for its neighbors to see if a merge is possible is expensive

### 3.3 Virtual memory

- __overlays__: splitting a program into little pieces, and just loading the overlay manager into memory upon start
  - programmer had to define the overlays
- __virtual memory__: each program has its own address space, broken into chunks (__pages__)
  - __page__: a contiguous range of addresses, mapped onto physical memory, or stored on disk
- __paging__: when a computer stores & receives data from a secondary storage for use in main memory
  - __virtual addresses__: the program-generated addresses, which form the __virtual address space__
- when virtual memory is used, virtual addresses go to a __MMU (Memory Management Unit)__ instead of directly to the memory bus
- __page frames__: the corresponding units in the physical memory that map the fixed-size units called pages, holding the virtual address space
- a __Present/absent bit__ keeps track of which pages are physically present in memory
- __page fault__: when the MMU causes the CPU to trap the operating system, when it notices that a page is unmapped
- __page table__: stores the number of the page frame corresponding to the virtual page, mapping virtual addresses onto physical addresses
  - __dirty bit__: a reflection of the page's state

Concerns with speeding up paging:

- Mapping from virtual -> physical address must be fast
- If the virtual address space is large, the page table will be large

A solution:

- use a __TLB (Translation Lookaside Buffer)__ or an __associative memory__ to map virtual -> physical addresses w/o going through the page table
- a __soft miss__: the page referenced isn't in the TLB, but is in memory; don't need disk I/O, but takes 10-20 machine instructions
  - __minor page fault__: when a page is in memory but not in the process's page table (perhaps because brought in by another process)
- a __hard miss__: page isn't in memory, and need to use disk I/O; easily 1 million times slower than a soft miss
  - __major page fault__
- __page table walk__: the act of looking up the mapping in the page table hierarchy
- __segmentation fault__: when a program accesses an invalid address, leading to the OS killing the program

For large memories, you can:

- use a __multilevel page table__, __page directory__, __inverted page tables__

## Chapter 6: Deadlocks

- __deadlock__: a set of processes is deadlocked if each process in the set is waiting for an event that only another process in the set can cause

### 6.1 Resources

- __resources__: the objects granted (eg. devices, data records, files, ...)
  - types:
    - __preemptable resource__: one that can be taken away from the process owning it w/o ill effects (eg. memory)
    - __nonpreemptable resource__: one that can't be taken away from the current owner w/o potentially causing failure
      - deadlocks usually involve this kind of resource

### 6.2 Introduction to deadlocks

- __resource deadlock__: each process waits for a resource possessed by another member of the set
  - 4 conditions that must be present to cause this:
    1. _Mutual exclusion condition_: each resource is either currently assign- ed to exactly one process or is available
    2. _Hold-and-wait condition_: processes currently holding resources that were granted earlier can request new resources
    3. _No-preemption condition_: resources previously granted cannot be forcibly taken away from a process; the process needs to release it
    4. _Circular wait condition_: circular list of 2+ processes, each waiting for the next member in the chain

### 6.4 Deadlock detection and recovery

- when there're multiple copies of a resource, you use a matrix-based algorithm instead of a list-based algorithm to detect deadlocks
  - __existing resource vector__, __available resource vector__, __current allocation matrix__, __request matrix__
- when to check for a deadlock?: every _k_ minutes, or if CPU utilization has dropped below a threshold

#### Recovery from deadlock

- through preemption:
  - temporarily take a resource from its owner & give it to another process (manual intervention, thus difficult or impossible)
- through rollback:
  - have processes checkpointed (store state data to a file for recreation) periodically
- through killing processes:
  - best to kill ones that can be rerun from the beginning w/o ill effects (eg. compilation)

### 6.5 Deadlock avoidance

- resource trajectories
  - model out/graph the processes' turns & their shared resource region
- safe & unsafe states
  - __safe state__: one where there's a scheduling order in which each process can be run to completion, even if they all request their maximum resources immediately
- __banker's algorithm__: check if the request leads to an unsafe state, and deny the request if so
  - in practice, processes don't know their maximum resource needs

### 6.6 Deadlock prevention

- attacking the mutual-exclusion condition
  - don't assign resource exclusively  to a process (eg. read-only data)
- attacking the wait-and-hold condition
  - prevent processes holding resources from waiting for more resources, by having processes request all resources before execution; all or nothing
    - issue: processes don't know what they need until they start running, & this is suboptimal resource utilization
- attacking the no-preemption condition
  - virtualize resource (if it's a virtualizable resource; records aren't, for instance)
- attacking the circular wait condition
  - entitle a process to at most 1 resource at once (unacceptable restriction)
  - number all resources, and processes request resources in numerical order
    - con: maybe impossible to find an ordering to suit everyone

### 6.7 Other issues

- __two-phase locking__: [1] processes tries to lock the records it needs, one at a time, [2] if (1) succeeds, it performs the updates & releases the locks
  - in (1), if a record is in use, it releases all locks and starts again
  - con: only works if the programmer arranged things s.t. the program can be stopped at any time during (1)
- __competition synchronization__: independent processes competing for the same resources
- __communication deadlock__: 2+ processes try to communicate by sending messages (& messages could get lost, w/ another process depending on that message)
  - prevented with timeouts
- __protocol__: communication rules
- __host__: a user computer
- __router__: a specialized communications computer, moving packets of data from source -> destination (has a packet buffer)
- __livelock__: the states of the processes involved in the livelock constantly change with regard to one another, none progressing
- __starvation__: the longest jobs not getting chosen due to shorter jobs getting priority, getting postponed indefinitely