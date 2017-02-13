# Modern Operating Systems

## Chapter 1: Introduction

* __operating system__: a layer of software to abstract away resource (network, I/O devices, RAM, etc.) management
  * runs in __kernel mode__ (aka _supervisor mode_), giving it permission to access to the hardware and everything else
  * _user mode_: what everything else runs in

### 1.1 What is an operating system?

* operating system functions:
  1. abstracting resources & providing them to the user
  2. managing hardware resources
* _architecture_: instruction set, memory organization, I/O, bus structure
* resource management:
  * __multiplexing__ (sharing) resources: many users sharing time or space of a resource

### 1.2 History of Operating Systems
* __multiprogramming__: allowing multiple jobs to use the memory by partitioning memory & sharing (avoids long idle time)
* __spooling__: loading a new job into the disk after a running job finished
* __timesharing__: programmers sharing a computer via an online terminal (variant of multiprogramming)

### 1.3 Computer hardware review

* registers visible to programmers:
  - __program counter__: contains next instruction's memory address
  - __stack pointer__: points to top of current stack in memory
  - __PSW__ (Program Status Word): contains condition code bits (set by comparison instructions, CPU priority, mode, etc.)

* main memory is divided into cache lines

* __virtual memory__ allows computers to run programs larger than physical memory by putting them on a disk & using main memory as a cache for the most heavily executed parts
  - requires mapping memory addresses on the fly to convert the address the program generated to the physical address in RAM where the word is located
    - the __MMU__ (Memory Management Unit) does this; it's a part of the CPU

* __device driver__: the software talking to the controller; gives controller commands & accepts responses
  - need a different driver for each OS
  - is put into the OS to be run in kernel mode; but can run outside of the kernal

* __busy waiting__: the operation where after I/O, the driver puts the data wheere its needed & then returns; then control is returned to the caller
  - the CPU continuously polls the device until it's done

* __interrupt__: completion is signalled this way; until an interrupt is received,  the CPU looks for other work to do

* __interrupt vector__: the part of memory storing the address of the interrupt handler for a device (device # may be used as index)

* __DMA (Direct Memory Access) chip__: controls flow of bits b/t memory & a controller w/o constant CPU intervention
  - CPU tells DMA chip how many bytes to transfer, relevant device & memory addresses, & the direction
  - DMA causes an interrupt after it's done

* __USB (Universal Serial Bus)__ attaches slow I/O devices (eg. keyboard, mouse) to the computer

* Booting the computer (p. 34)

### 1.4 The operating system zoo

* __embedded operating systems__: systems (eg. microwaves, TVs, cars, MP3s, etc.) where you can't install untrusted software; all the software is in ROM

* __hard real-time systems__ provide absolute guarantees htat a certain action will occur by a certain time (eg. military applications, factory stuff, etc.)
  * __soft real-time systems__ may miss an occasional deadline (eg. smartphones, audio systems, etc.)

### 1.5 Operating system concepts

* __process__: a program in execution
  * __address space__: a list of memory locations from 0 to [a maximum], which the process can read & write;
    - contains the program, its data, & its stack as well
  * its resources, outstanding alarms, & a list of related processes, & other info are also associated w/ each process

* __process table__: an OS table including an array of structures, one for each current process
  * a suspended process has a __core image__ (its address space) * its process table entry (includes the process's meta data)

* after a certain # of seconds, the OS sends the process an __alarm signal__, forcing it to save its meta-data & start running a signal-handling procedure

* __UID (User IDentification)__ is assigned to each person authorized to use the system, by the system admin; every process stores a UID, as well as a GID (Group IDentification)

* __working directory__ = current directory, as opposed to the absolute path

* __special files__ are provided to make I/O devices look like files, so you an use system calls as usual
  - __block special files__ model devices consisting of randomly addressable blocks (eg. disks)
  - __character special files__ model printers, modems, & other devices accepting/outputting a character stream

* __pipe__: a pseuofile connecting two processes (process A writes to process B)

> If a user puts an ampersand after a command, the shell does not wait for it to complete. Instead it just gives a prompt immediately.

woaaahhh eg. `npm install &`

### 1.6 System calls

* fork returns a __PID (Process IDentifier)__, allowing you to distinguish the child and parent processes
  - the parent executes a `waitpid` system call, waiting just until any of its children terminates, and receives the child process' exit status

* UNIX processes are divided into 3 segments: [1] the text segment (ie. the program code), [2] the data segment (ie. the variables), & [3] the stack segment

### 1.7 Operating system structure

* a __reincarnation server__'s job is to check whether other servers & drivers are functioning correctly, and to replace them if not

* __client-server model__ consists of servers, which each provide a service, and the clients, which use those services

* __exokernel__: a program handling the allocation of resources to virtual machines, & checking to see that no machine is using another's resources
  - the bottom layer of a machine, running in kernel mode

### 1.8 The World According to C

* convention: name every constant, except for `-1`, `0`, and `1`

* you can give macros parameters, such as in

```c
#define max(a, b) (a > b ? a : b)

// ...
i = max(j, k + 1)
```

* `#` lines speak to the compiler

* __C preprocessor__: first pass of the C compiler

* __linker__ combines all of the output `.o` files into one executable binary file; library functions are also included, interfunction references resolved, and machine addresses relocated as needed
