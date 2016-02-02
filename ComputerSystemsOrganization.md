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

1. register
2. L1 cache (SRAM)
3. L2 cache (SRAM)
4. L3 cache (SRAM)
5. main memory (DRAM)
6. local secondary storage (local disks)
7. remote secondary storage (distributed file systems, Web servers)

### How the OS interfaces with Hardware

#### Processes

#### Threads

#### Virtual memory

#### Files

### Networking

### Key themes and concepts

#### Amdahl's Law

#### Thread-Level Concurrency

#### Instruction-Level Parallelism

#### Single-Instruction, Multiple-Data (SIMD) Parallelism

##### Importance of abstractions in computer systems

