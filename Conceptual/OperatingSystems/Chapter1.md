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
