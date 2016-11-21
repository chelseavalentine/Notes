# Modern Operating Systems

## Chapter 3: Memory management

### 3.1 No memory abstraction

* without abstraction, could have at most 1 program in memory, because otherwise another program could overwrite memory addresses another program was using and cause a crash
  - can get some parallelism by programming w/ multiple threads (since all threads in a process see the same memory image)

* __BIOS__ Basic Input Output System

* __swapping__: running multiple programs concurrently by saving the entire memory to disk, and then bringing in the next program
  - doesn't work b/c a program may jump to a part of memory which is where the next program is, and cause a crash, since both reference physical memory

* __static relocation__: modify the 2nd program on the fly as it loads into memory, by adding the memory address of the program to every instruction
  - slows down loading

### 3.2 A memory abstraction: address spaces (216)

* __address space__: the set of addresses a process can use to address memory

#### Base and limit registers

* __dynamic relocation__: mapping each process' address space onto a different part of physical memory
  - when process is run, base register gets physical address where program begins, & limit register gets the length ofthe program
  - CPU automatically adds the base value to the address, each time the process references memory
  - con: additions are slow due to carry-propagation time, unless using special addition circuits

#### Swapping

* __virtual memory__ allows programs to run even when only partially in memory memory

* __memory compaction__: moving all processes downward as far as possible, when swapping creates multiple holes in memory
  - isn't done b/c requires a lot of CPU time

##### Managing free memory

Two ways of keeping track of memory usage: [1] bitmaps, [2] free lists

##### Memory management with bitmaps

* memory is divided into allocation units.
  - the smaller the unit, the larger the bitmap
  - the bigger the unit, the greater chance that appreciable memory is wasted in the last unit of the process if the process size isn't an exact multiple of the allocation unit

* con of bitmaps: searching a bit map for a run of a given length is slow because the run may straddle word boundaries in the map

##### Memory management with linked lists

* maintaining a linked list of allocated and free memory segments
  - a segment either contains a process or is an empty hole b/t two processes
  - if the segment list is kept sorted by address, it's straightforward to update the list when a process is terminated or swapped out

* algorithms to allocate memory for a created process/swapped in process:
  - __first fit__: scan list of segments until find big enough hole
  - __next fit__: a variation of first fit where it keeps track of last-found suitable hole to search from where it left off
    + slightly worse performance than first fit
  - __best fit__: search entire list for smallest adequate hole
    + obv slower than first fit, but also results in more wasted memory than either b/c fills memory w/ tiny, useless holes
  - __worst fit__: looking for worst fit, so it leaves a large enough hole to be useful
    - also a crappy idea
  - __quick fit__: maintains separate lists for common sizes requested
    - when process terminates/is swapped out, looking for its neighbors to see if a merge is possible is expensive

### 3.3 Virtual memory

* __overlays__: splitting a program into little pieces, and just loading the overlay manager into memory upon start
  - programmer had to define the overlays

* __virtual memory__: each program has its own address space, broken into chunks (__pages__)
  - __page__: a contiguous range of addresses, mapped onto physical memory, or stored on disk

* __paging__: when a computer stores & receives data from a secondary storage for use in main memory
  - __virtual addresses__: the program-generated addresses, which form the __virtual address space__

* when virtual memory is used, virtual addresses go to a __MMU (Memory Management Unit)__ instead of directly to the memory bus

* __page frames__: the corresponding units in the physical memory that map the fixed-size units called pages, holding the virtual address space

* a __Present/absent bit__ keeps track of which pages are physically present in memory

* __page fault__: when the MMU causes the CPU to trap the operating system, when it notices that a page is unmapped

* __page table__: stores the number of the page frame corresponding to the virtual page, mapping virtual addresses onto physical addresses
  - __dirty bit__: a reflection of the page's state

Concerns with speeding up paging:

* Mapping from virtual -> physical address must be fast
* If the virtual address space is large, the page table will be large

A solution:

* use a __TLB (Translation Lookaside Buffer)__ or an __associative memory__ to map virtual -> physical addresses w/o going through the page table

* a __soft miss__: the page referenced isn't in the TLB, but is in memory; don't need disk I/O, but takes 10-20 machine instructions
  - __minor page fault__: when a page is in memory but not in the process's page table (perhaps because brought in by another process)

* a __hard miss__: page isn't in memory, and need to use disk I/O; easily 1 million times slower than a soft miss
  - __major page fault__

* __page table walk__: the act of looking up the mapping in the page table hierarchy

* __segmentation fault__: when a program accesses an invalid address, leading to the OS killing the program

For large memories, you can:

* use a __multilevel page table__, __page directory__, __inverted page tables__

### 3.4 Page replacement algorithms (240)

### 3.5 Design issues for paging systems (253)

### 3.6 Implementation issues (264)

### 3.7 Segmentation (271)

### 3.8 Research on memory management (283)

(284)
