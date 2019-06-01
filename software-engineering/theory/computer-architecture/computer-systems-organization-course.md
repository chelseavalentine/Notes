# Computer Systems Organization course

Notes taken while reading _Computer Systems: A Programmer's Perspective (3rd ed)_.

## Chapter 1: Information is Bits + Context

- __1 byte__: 8 bits (bit = 0 or 1)
- __executable object file__, __executable__: a file containing a sequence of low-lvl machine instructions

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

- know which statements are more efficient, eg. if need a conditional statement, should we use `switch`, or `if-else`?
- understanding linktime errors (when linker can't resolve a reference), differences between static & global variables, etc.
- avoiding security holes, like buffer overflow

### Computer's hardware organization

- buses
  - __buses__: electrical channels carrying information bytes between components
    - designed to transfer **words** (fixed sizes of bytes… most common is 8 bytes (64 bits))
- input/output devices
  - keyboard, screen, mouse, disk drive
  - I/O devices are connected to the I/O bus via a controller/adapter
    - __controller__: chip sets in the device/on system's circuit board
    - __adapter__: card that plugs into a slot on the motherboard
- memory
  - **main memory**: a collection of dynamic random access memory (DRAM), which functions as a temporary storage device for programs & data
    - memory organized as a linear array of bytes
- processor
  - **central processing unit (CPU)**; **processor**: engine that executes instructions stored in main memory
  - **program counter (PC)**; **register**: the word-size storage device that points at the instruction for the CPU to execute
  - __instruction set architecture__: this defines the processor's (abstracted) instruction execution model
  - __artithmetic/logic unit (ALU)__ computes new data & address values
- __direct memory access (DMA)__: when data travels directly from disk to memory

##### Sample CPU operations

- _load_: copy a byte/word from main memory → register, overwriting
- _store_: copy a byte/word from a register to main memory, overwriting
- _operate_: copy contents of 2 registers to the ALU, perform artithmetic, store result in 1 register, overwiting
- _jump_: extract word from the instruction, copy it into the PC, overwriting

### Caching

- processors read from registers faster than from memory, & is cheaper to make processors, rather than memory, run faster
  - workaround: __caches; cache memories__: small storage devices (allowing for faster data transfer than large storage devices) to temporarily store data

### Storage device hierarchy

(From 1: smaller, faster, & costlier storage devices to 7: larger, slower & cheaper storage devices)

- **static random access memory (SRAM)**: system can get the effect of a large & fast memory by exploiting code & data locality

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

- __operating system__: the software layer between the application program (software) & the hardware (eg. processor, main memory, I/O devices)
  - purposes:
    1. protect the hardware from misuse by bad apps
    2. abstracts the methods to dealing w/ the low-lvl hardware devices, to make it easier for apps
  - abstractions:
    - *processes* (abstractions for the processor, main memory, & I/O devices), *virtual memory* (abstractions for the main memory & disk I/O devices), & *files* (abstractions for I/O devices)

#### Processes

- __process__: OS's abstraction for running a program
  - multiple processes are run concurrently
    - __concurrently__: instructions of one process are interleaved w/ another process's instructions, so the operations appear to be occurring at the same time/running in parallel
- __context switching__:
  - __context__: the state (all of the info that the process needs to run), including things like the current values of the PC (the register), the register file, & the main memory's contents
- __kernel__: the portion of the OS code that's always in memory; a collection of code & data structures the system uses to manage all of the processes
  - it deals w/ transitions from one process to another
  - (eg. when an app needs the system to do something, eg I/O, it executes a system call instruction, transferring control to the kernel)

#### Threads

- __thread__: an execution unit within a process; a process can have multiple threads
  - threads run in the context of the process & share the same code and global data as the process
  - easier to communicate between threads than between processes
  - multithreading can ofc make programs faster (when comptuer has multiple processors)

#### Virtual memory

- __virtual memory__: an abstraction that makes it seem like each process has exclusive use of memory
  - __virtual address space__: a uniform view of memory that a process has, an address to a location in main memory
    - each address has well-defined areas:
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

- __file__: a sequence of bytes
  - therefore, all I/O devices (eg. disks, keyboards, networks, displays, etc.) are files

### Key themes and concepts

#### Amdahl's law

- __Amdahl's law__: when we speed up one part of the system, the overall effect on the system depends on the significance of the part & how much it was sped up

#### Concurrency and Parallelism

- __parallelism__: use of concurrency to make systems run faster

##### Thread-level concurrency

- __time-sharing__: a single computer switches rapidly among its executed processes, allowing multiple users to interact w/ the system simultaneously, or a single user to run multiple tasks concurrently
- __uniprocessor system__: system w/ 1 processor core
- __multiprocessor system__: system w/ 2/+ processor cores, all under the control of one OS kernel
- __hyperthreading__; __simultaneous multithreading__: a technique enabling a single CPU to execute multiple control flows
  - needs multiple copies of the CPU hardware (program counters & register files) & single copies of other parts
  - decides to switch cycles on a cycle-by-cycle basis, rather than having a timer
    - allows for better usage of resources, b/c if a thread is waiting on something, the CPU can switch to a different thread

##### Instruction-level parallelism

- __instruction-level parallelism__: ability to execute multiple instructions at one time (eg. multiple per clock cycle)
- __pipelining__: actions needed for an instruction are partitioned into steps, the processor hardware has a series of stages, & then each stage performs a step
  - stages operate in parallel
- __superscalar processors__: processors that can sustain execution rates faster than 1 instruction per clock cycle
- __single-instruction multiple-data (SIMD) parallelism__: splitting a single instruction into multiple operations to be performed in parallel

##### Importance of abstractions in computer systems

- __Application Program Interface (API)__

## Chapter 2: Representing & Manipulating Information

- __unsigned__ encodings represent numbers `>= 0`
- __two's complement__ encodings represent __signed__ integers (numbers + or –)

### Information storage

- __bytes__ (8 bits) are the smallest addressable unit of memory
- __virtual memory__: a large array of bytes, with each given an address
  - __virtual address space__: the set of all possible addresses
- __program objects__: program data, instruction, & control information

#### Hexadecimal notation

- byte values range from 00000000₂ to 11111111₂, in base-10 notation, it's 0 to 255
- __hexadecimal numbers__: base-16 numbers that use digits `0-9` and characters `A-F`
  - each byte can take on values from 00₁₆ to FF₁₆
  - in C: hexadecimal numbers are denoted to machines by using `0x`, eg. `0x000000`
- hexadecimal → binary representation
  - eg. 0x163A4C becomes 00010111101001001100, because you convert each hexa-digit into its binary representation, and just append them together as if a string.

#### Data sizes

- __word size__: a computer's set size for pointer data (common word sizes are 64 bit & 32 bit)
  - the virtual address space's maximum size is `2^w - 1`, with a _w_-bit word size

| C declaration   |                  | Bytes    |          |
| --------------- | ---------------- | -------- | -------- |
| *Signed*        | *Unsigned*       | *32-bit* | *64-bit* |
| `[signed] char` | `unsigned char`  | 1        | 1        |
| `short`         | `unsigned short` | 2        | 2        |
| `int`           | `unsigned`       | 4        | 4        |
| `long`          | `unsigned long`  | 4        | 8        |
| `char *`        |                  | 4        | 8        |
| `float`         |                  | 4        | 4        |
| `double`        |                  | 8        | 8        |

The compiler won't always treat the data type as `signed`, if you want it to, then you must declare `signed [data type]`

Can declare in multiple ways (all of the following are identical)

```c
unsigned long
unsigned long int
long unsigned
long unsigned int
```

###### Declaring pointers in C

```c
// for data type T
T *p;
// eg.
char *p;
```

#### Addressing and byte ordering

- multi-byte objects are stored as a contiguous sequence of bytes in memory
- _little endian_: storing bytes from least to most significant
  - _big endian_: storing bytes from most to least significant
  - why does this even matter?: if you're sending data over from a machine w/ the reverse ordering
    - thus, in networking, the data is usually converted  to match the network standard
  - why does this even matter?: when reading machine-level programs

###### "Aliases" (`typedef`) in C

```c
typedef int *int_pointer
// allows you to declare a pointer to an int like this, which is semantically better
int_pointer ip;
```

- also you can cast pointers in C… or maybe it's only `void *` pointers… you'll probably figure it out

#### Representing Strings

- __string__: array of characters terminated by `null` (ideally, if the memory is really full or something, you can't count on there being a null at the end, it could be another in-use memory chunk)

#### Bit-level operations in C

- _masking operations_ w/ bit-level operations: mask selects a part of the bits in the word using a bit pattern (everything but the pattern becomes 0s)

#### Shift operations in C

- consider an operand _x_ that has a bit representation of x, sub …, w-1, w, w-2,.. and so on, doing `x << k` will do w-k-1, w-k, w-k-2,… and so on. `x >> k` will do w+k-1, w+k, w+k-2, and so on.
  - `x << 4` will add 4 0's to the end of the value, shifting the rest 4 to the left
    - 01100011 becomes 0011_0000_
  - `x >> 4` would add 4 0's to the beginning of the value, shifting the rest 4 to the right

### Integer representations

#### Integral data types

Typical ranges for C integral data types for 64-bit programs

| C data type      | Minimum          | Maximum          |
| ---------------- | ---------------- | ---------------- |
| `[signed] char`  | -128             | 127              |
| `unsigned char`  | 0                | 255              |
| `short`          | -32,768          | 32,767           |
| `unsigned short` | 0                | 65,535           |
| `int`            | ≈-2.15 million   | ≈2.15 million    |
| `unsigned`       | 0                | ≈4.30 million    |
| `long`           | ≈-9,223 trillion | ≈9.223 trillion  |
| `unsigned long`  | 0                | ≈18.446 trillion |

### Integer arithmetic

- Encodings
- Two's complement encodings

#### Unsigned addition

- overflows occur when the sum goes above the max of the data type
  - it's not a C error when overflows occur, but you probably want to check for them
  - there can be positive & negative overflow (p. 90)

##### Two's-complement multiplication

| Mode             |    x     |    y     |    x • y    | Truncated x • y |
| ---------------- | :------: | :------: | :---------: | :-------------: |
| Unsigned         | 5 [101]  | 3 [011]  | 15 [001111] |     7 [111]     |
| Two's complement | -3 [101] | 3 [011]  |     -9      |    -1 [111]     |
| Unsigned         | 4 [100]  | 7 [111]  | 28 [011100] |     4 [100]     |
| Two's complement | -4 [100] | -1 [111] | 4 [000100]  |    -4 [100]     |
| Unsigned         | 3 [001]  | 3 [011]  | 9 [001001]  |     1 [001]     |
| Two's complement | 3 [011]  | 3 [011]  | 9 [001001]  |     1 [001]     |

- Multiplying by constants is just shifting the bits over to the left (p. 101)

- Dividing by constants is just shifting bits over to the right (p. 104)

  - It's x/2^k rounded down in bit form

  - C expression that compules value x/2^k is

    ```c
     (x<0 ? x+(1<<k)-1 : x) >>k
    ```

### Floating point

- floating point representation of rational numbers is V = x • 2^y
- in base 10 numbers, you can consider the '.' as a binary point, where all digits left are positive & right ones are negative
- IEEE floating-point standard representation: V = (-1)^s • M • 2^E
  - sign *s* determines negative/positive,  for number 0, there's a special case
  - m is a fractional binary number between 1 and 2-e (greek)
  - exponent E weighs the value by a (possibly negative) power of 2
- sign, exponent, fractional field diagrams (p. 113), examples (p. 116)

##### Rounding

Illustration of rounding modes

| Mode              | $1.40 | $1.60 | $1.50 | $2.50 | $-1.50 |
| ----------------- | ------------- | ------------- | ------ |
| Round-to-even     | $1    | $2    | $2    | $2    | $-2    |
| Round-toward-zero | $1    | $1    | $1    | $2    | $-1    |
| Round-down        | $1    | $1    | $1    | $2    | $-2    |
| Round-up          | $2    | $2    | $2    | $3    | $-1    |

##### Floating-point operators

- there are defined ways to add & multiply (p. 123)
  - eg. add: Round(x + y), multiply: Round(x • y)
- how C handles rounding & overflows (p. 125)