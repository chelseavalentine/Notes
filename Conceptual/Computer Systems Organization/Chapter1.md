# Computer Systems Organization

Notes taken while reading _Computer Systems: A Programmer's Perspective (3rd ed)_.



## Chapter 1: Information is Bits + Context

* __1 byte__: 8 bits (bit = 0 or 1)
* __executable object file__, __executable__: a file containing a sequence of low-lvl machine instructions

### Compilation system

1. _Preprocessing phase_: `hello.c` → `hello.i`
   
   all imports are made, creating a file with all necessary code in it (text file)
   
2. _Compilation phase_: `hello.i` → `hello.s`
   
   compiler converts source code into an assembly-language program (text file)
   
3. _Assembly phase_: `hello.s` → `hello.o`
   
   assembler converts hello.s into machine-language instructions (relocatable object program) (binary file)
   
4. _Linking phase_: `hello.o` → `hello`
   
   the linker handles merging of calling in functions provided by libraries, and creates an executable (binary file)

##### Using knowledge of the compilation system to your advantage

* know which statements are more efficient, eg. if need a conditional statement, should we use `switch`, or `if-else`?
* understanding linktime errors (when linker can't resolve a reference), differences between static & global variables, etc.
* avoiding security holes, like buffer overflow

### Computer's hardware organization

* buses
  * __buses__: electrical channels carrying information bytes between components
    * designed to transfer **words** (fixed sizes of bytes… most common is 8 bytes (64 bits))
* input/output devices
  * keyboard, screen, mouse, disk drive
  * I/O devices are connected to the I/O bus via a controller/adapter
    * __controller__: chip sets in the device/on system's circuit board
    * __adapter__: card that plugs into a slot on the motherboard
* memory
  * **main memory**: a collection of dynamic random access memory (DRAM), which functions as a temporary storage device for programs & data
    * memory organized as a linear array of bytes
* processor
  * **central processing unit (CPU)**; **processor**: engine that executes instructions stored in main memory
  * **program counter (PC)**; **register**: the word-size storage device that points at the instruction for the CPU to execute
  * __instruction set architecture__: this defines the processor's (abstracted) instruction execution model
  * __artithmetic/logic unit (ALU)__ computes new data & address values
* __direct memory access (DMA)__: when data travels directly from disk to memory

##### Sample CPU operations

* _load_: copy a byte/word from main memory → register, overwriting
* _store_: copy a byte/word from a register to main memory, overwriting
* _operate_: copy contents of 2 registers to the ALU, perform artithmetic, store result in 1 register, overwiting
* _jump_: extract word from the instruction, copy it into the PC, overwriting

### Caching

* processors read from registers faster than from memory, & is cheaper to make processors, rather than memory, run faster
  * workaround: __caches; cache memories__: small storage devices (allowing for faster data transfer than large storage devices) to temporarily store data

### Storage device hierarchy

(From 1: smaller, faster, & costlier storage devices to 7: larger, slower & cheaper storage devices)

* **static random access memory (SRAM)**: system can get the effect of a large & fast memory by exploiting code & data locality


1. register file
   
   *holds words removed from the cache memory*
   
2. L1 cache (SRAM) (located on the processor chip, accesses are almost as fast as the register)
   
   *holds cache lines from the L2 cache*
   
3. L2 cache (SRAM) (5x longer than L1, connected to the processor by a bus)
   
   *holds cache lines from the L3 cache*
   
4. L3 cache (SRAM)
   
   *holds cache lines from memory*


1. main memory (DRAM)
   
   *holds disk blocks from local disks*
   
2. local secondary storage (local disks)
   
   *holds files retrieved from disks on the remote network servers*
   
3. remote secondary storage (distributed file systems, Web servers)

### How the OS interfaces with Hardware

* __operating system__: the software layer between the application program (software) & the hardware (eg. processor, main memory, I/O devices)
  * purposes:
    1. protect the hardware from misuse by bad apps
    2. abstracts the methods to dealing w/ the low-lvl hardware devices, to make it easier for apps
  * abstractions:
    * *processes* (abstractions for the processor, main memory, & I/O devices), *virtual memory* (abstractions for the main memory & disk I/O devices), & *files* (abstractions for I/O devices)

#### Processes

* __process__: OS's abstraction for running a program
  * multiple processes are run concurrently
    * __concurrently__: instructions of one process are interleaved w/ another process's instructions, so the operations appear to be occurring at the same time/running in parallel
* __context switching__:
  * __context__: the state (all of the info that the process needs to run), including things like the current values of the PC (the register), the register file, & the main memory's contents
* __kernel__: the portion of the OS code that's always in memory; a collection of code & data structures the system uses to manage all of the processes
  * it tdeals w/ transitions from one process to another
  * (eg. when an app needs the system to do something, eg I/O, it executes a system call instruction, transferring control to the kernel)

#### Threads

* __thread__: an execution unit within a process; a process can have multiple threads
  * threads run in the context of the process & share the same code and global data as the process
  * easier to communicate between threads than between processes
  * multithreading can ofc make programs faster (when comptuer has multiple processors)

#### Virtual memory

* __virtual memory__: an abstraction that makes it seem like each process has exclusive use of memory
  
  * __virtual address space__: a uniform view of memory that a process has, an address to a location in main memory
    
    * each address has well-defined areas:
      
      1. _program code & data_
         
         initialized by the program's executable
         
      2. *heap*
         
         dynamically expands & contracts & run time due to library routines like `malloc` & `free`
         
      3. _shared libraries_
         
         eg. C standard library, math library, etc.
         
      4. _stack_
         
         compiler uses to implement function calls; dynamically expands & contracts w/ program's execution. Eg. imagine a recursive function adding function calls to the stack
         
      5. _kernel virtual memory_
         
         applications can invoke the kernel here; can't modify the kernel or handle any of its functions

#### Files

* __file__: a sequence of bytes
  * therefore, all I/O devices (eg. disks, keyboards, networks, displays, etc.) are files



### Key themes and concepts

#### Amdahl's law

* __Amdahl's law__: when we speed up one part of the system, the overall effect on the system depends on the significance of the part & how much it was sped up

#### Concurrency and Parallelism

* __parallelism__: use of concurrency to make systems run faster

##### Thread-level concurrency

* __time-sharing__: a single computer switches rapidly among its executed processes, allowing multiple users to interact w/ the system simultaneously, or a single user to run multiple tasks concurrently
* __uniprocessor system__: system w/ 1 processor core
* __multiprocessor system__: system w/ 2/+ processor cores, all under the control of one OS kernel
* __hyperthreading__; __simultaneous multithreading__: a technique enabling a single CPU to execute multiple control flows
  * needs multiple copies of the cPU hardware (program counters & register files) & single copies of other parts
  * decides to switch cycles on a cycle-by-cycle basis, rather than having a timer
    * allows for better usage of resources, b/c if a thread is waiting on something, the CPU can switch to a different thread

##### Instruction-level parallelism

* __instruction-level parallelism__: ability to execute multiple instructions at one time (eg. multiple per clock cycle)
* __pipelining__: actions needed for an instruction are partitioned into steps, the processor hardware has a series of stages, & then each stage performs a step
  * stages operate in parallel
* __superscalar processors__: processors that can sustain execution rates faster than 1 instruction per clock cycle
* __single-instruction multiple-data (SIMD) parallelism__: splitting a single instruction into multiple operations to be performed in parallel

##### Importance of abstractions in computer systems

* __Application Program Interface (API)__