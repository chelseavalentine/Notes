# Learning C++

Notes taken while reading __Jumping into C++__ by Alex Allain.



## Pointers

+ __pointer__: variable holding an address to a memory store (not necessarily of a diff. variable)
  + knowing this address allows you to hold onto memory from the OS
  + can refer to the memory address or the variable that stores the memory address

### Memory's structure

* __stack__: variables declared in functions that're currently being used
* __heap__ (aka. __free store__): requestable unallocated memory
* __owner__: the code responsible for a memory chunk
  * best practice: document whether function takes ownership of the memory @ a pointer's address
    * esp. important b/c memory can be returned to OS if you don't keep track of whether it's being used by another part of the program
* __memory leak__: result of not freeing memory when out of use
* __invalid memory__: memory that hasn't been allocated, and thus cannot be accessed (causes crashes)
  * can occur when you don't init pointers (—> data corruption or crashes)
