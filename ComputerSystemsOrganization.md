# Computer Systems Organization 

Notes taken while reading _Computer Systems: A Programmer's Perspective (3rd ed)_.



## Chapter 1: Information is Bits + Context

* __1 byte__: 8 bits (bit = 0 or 1)
* __executable object file__, __executable__: a file containing a sequence of low-lvl machine instructions



#### Compilation system

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



#### Computer's hardware organization

* buses
* input/output devices
* memory
  * main memory
  * random access memory
* processor
  * central processing unit
  * program counter