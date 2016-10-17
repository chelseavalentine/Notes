# Code Complete

## Chapter 13: Unusual data types

### Structures

* use structures to simplify operations on blocks of data, and to simplify parameter lists

* use structures to reduce maintenance

### Pointers

* isolate pointer operations in routines or classes

* declare and define pointers at the same time

* delete pointers at the same scoping level as they were allocated

* check pointers before using them (eg. check that a data pointer is between start & end data)

* tag a field in a structure solely for the purpose of error checking, to ensure that the data hasn't been corrupted

* use extra pointer variables for clarity

* set pointers to null after deleting/freeing them, so you get an error if you try to use it

## Chapter 14: Organizing straight-line code

* organize code so that dependencies are obvious (eg. write an initializing routine)

* use routine params to make dependencies obvious (eg. all of the routines take the same param)

* document unclear dependencies with comments

* check for dependencies with assertions or error-handling code

## Chapter 15: Using conditionals

### `if` statements

* write the normal path through the code first, then write the unusual cases

* when you have an `if` test w/o an `else`, unless the reason is obvious, use comments to explain why the `else` clause isn't necessary (within the `else` block)

### `case` statements

* if cases are equally important, order them alphabetically or numerically

* put the normal case first

* put the most common case first

* only use the default clause to detect legitimate defaults (not the remaining case)
  - use it to detect errors

## Chapter 16: Controlling loops

* keep loop-housekeeping chores at either the beginning or end of the loop

* make each loop perform only one function

* avoid code that depends on the loop index's final value

* consider using safety counters to determine whether the loop has executed too many times

## Chapter 17: Unusual control structures

* use safety counters to prevent infinite recursion

* don't use recursion for factorials or Fibonacci numbers

## Chapter 18: Table-driven methods (p. 448)

*  __table-driven method__: a scheme where you look up info in a table, rather than `if`/`switch` statements

* if the entries in a lookup table are large, it takes a lot less space to create an index array with a lot of wasted space than it does to take a lookup table w/ a lot of wasted space

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
