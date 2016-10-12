# Code Complete

## Chapter 8: Defensive programming

### Assertions

* use assertions for conditions that should never occur, & error handling for conditions you expect to occur
 - for highly robust code, assert and then handle the code anyway

* avoid putting executable code into assertions

### Error-handling techniques

* return a neutral value (eg. 0, empty string, empty pointer) if it's known to be harmless

* continue until the next piece of valid data, if processing a stream of data

* return the same answer as the previous time, if it's processing a stream of harmless data (eg. next thermometer reading)

* substitute the closest legal value (eg. if you get -1 when it must be positive or 0, substitute with 0)

* return an error code (eg. throw an exception, return status as the function's return value, set the value of a status variable)

* call an error-processing routine/object
  - could be bad if an attacker has caused a buffer overrun attack

* display an error message

* shut down

* consumer apps tend to favor robustness over correctness; safety-critical apps tend to favor correctness over robustness

### Exceptions

* use exceptions to notify other parts of the program about errors that should not be ignored

* avoid throwing exceptions in constructors and destructors unless you catch them in the same place

* know the exceptions your library code throws

* standardize your project's use of exceptions

### Barricade your program to contain the damage caused by errors

* convert input data to the proper type at input time

### Offensive programming

* ways to check for edge cases
  - make sure _asserts_ abort the program
  - fill any memory allocated, so you can detect memory allocation errors
  - completely fill any files/streams allocated to flush out file-format errors
  - be sure the code in ease `case` statement's `default` or `else` clause fails hard, or is otherwise impossible to overlook
  - fill an object w/ junk data just before it's deleted
  - set up the program to email error log files to yourself to see what's occurring on prod

### Determining how much defensive programming to leave in production code

* leave in code that checks for important errors

* remove code checking for trivial errors (don't delete, just don't compile it)

* remove code resulting in hard crashes

* Defensive programming checklist (p. 248)


## Chapter 9: The pseudocode programming process (p. 252)

## Chapter 10: General issues in using variables (p. 274)

## Chapter 11: The power of variable names (p. 296)

## Chapter 12: Fundamental data types (p. 328)

## Chapter 13: Unusual data types (p. 356)

## Chapter 14: Organizing straight-line code (p. 384)

## Chapter 15: Using conditionals (p. 392)

## Chapter 16: Controlling loops (p. 404)

## Chapter 17: Unusual control structures (p. 428)

## Chapter 18: Table-driven methods (p. 448)

## Chapter 19: General control issues (p. 468)

## Chapter 20: The software-quality landscape (p. 500)

## Chapter 21: Collaborative construction (p. 516)

## Chapter 22: Developer testing (p. 536)

## Chapter 23: Debugging (p. 572)

## Chapter 24: Refactoring (p. 600)

## Chapter 25: Code-tuning strategies (p. 624)

## Chapter 26: Code-tuning techniques (p. 646)

## Chapter 27: How program size affects (p. 686)

## Chapter 28: Managing construction (p. 698)

## Chapter 29: Integration (p. 726)

## Chapter 30: Programming tools (p. 746)

## Chapter 31: Layout and style (p. 766)

## Chapter 32: Self-documenting code (p. 814)

## Chapter 33: Personal character (p. 856)

## Chapter 34: Themes in software craftsmanship (p. 874)

## Chapter 35: Where to find more information (p. 892)

(p. 899)
