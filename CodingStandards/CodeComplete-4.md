# Code Complete

## Chapter 23: Debugging

### Finding a defect

* an error that doesn't occur predictably is usually an initialization error, timing issue, or dangling-pointer problem

* __stabilizing an error__: narrowing a test case to the simplest one that still produces the error

* set a maximum time for "quick and dirty" debugging, so you know whether it's a more complex issue than expected

### Fixing a defect

* understand the problem before you fix it, and also understand the program as well

* fix the problem, not the symptom

* make one change at a time

* add a unit test that exposes the defect

### Debugging tools

* set compiler's warning level to the highest, pickiest level possible

* treat warnings as errors

## Chapter 24: Refactoring

* small fixes (1-5 lines) are more likely to have errors than larger fixes, so don't treat them casually

* if a big refactor seems necessary, look at whether a redesign & reimplementation seems more appropriate

* times to check if you need to refactor
  - when you add a routine or class
  - when you fix a defect

## Chapter 25: Code-tuning strategies

### Performance overview

* efficiency can be thought of from these viewpoints:
  - _program requirements_: before you make high performance a requirement, make sure the users require that/wouldn't be fine with less; high performance can make systems more complex
  - program design
  - class and routine design
  - OS interactions
  - _code compilation_: some compilers are better at optimizing when turning high-level language into machine code
  - code tuning

### Code tuning

* reducing the number of lines in a high-level language doesn't necessarily improve the speed/size of the resulting machine code

* you shouldn't optimize as you go, since you don't know where the program's bottlenecks are

* system calls are often expensive, because it often needs a context switch

## Chapter 26: Code-tuning techniques

### Logic

* order tests by frequency

* compare performance of similar logic structures, some are faster than others depending on the language
  - eg. `if-else` is 82% faster in Java than a `switch` statement

### Other optimizations

* replace expensive operations w/ cheaper operations (eg. addition over multiplicatio, multiplication with exponentiation, etc.)

* use the fewest array dimensions possible

* if you know the result of an expression is always the same, replace it with a constant rather than computing it each time

* recoding parts of a program in a low-level language

## Chapter 28: Managing construction (p. 698)

### Encourage good coding

*


### Configuration management

*


### Estimating a construction schedule

*


### Measurement

*


### Treating programmers as people

*


###   Managing your manager

*



## Chapter 29: Integration (p. 726)

## Chapter 30: Programming tools (p. 746)

## Chapter 31: Layout and style (p. 766)

## Chapter 32: Self-documenting code (p. 814)

## Chapter 33: Personal character (p. 856)

## Chapter 34: Themes in software craftsmanship (p. 874)

## Chapter 35: Where to find more information (p. 892)

(p. 899)
