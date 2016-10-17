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

## Chapter 18: Table-driven methods

*  __table-driven method__: a scheme where you look up info in a table, rather than `if`/`switch` statements

* if the entries in a lookup table are large, it takes a lot less space to create an index array with a lot of wasted space than it does to take a lookup table w/ a lot of wasted space

## Chapter 19: General control issues

### Boolean expressions

* consider using decision tables to replace complicated conditions

* write boolean statements with positive tests, rather than negative ones
  - use DeMorgan's Theorems to create positive tests from negative ones
    + (!this || !that) == !(this && that)

* write numeric expressions in number-line order (ie. smaller values -> larger values)

### Null statements

* create a preprocessor `doNothing()` macro, or an inline function for null statements for emphasis
  - alternatively, consider whether the code would be clearer w/ a non-null loop body

### Taming dangerously deep nesting

* retest a part of the condition (splitting it into 2+ blocks)

* use a break block

* see if you can use `if-else` rather than just a nested `if`

* convert to a `switch` statement

* move part of the if-statement logic into its own routine

### Structured programming

* __structured programming__
  - a program should use only _single-entry, single-exit_ control constructs (only have one place it starts, & one place it could end)

* 3 components of structured programming:
  1. _sequence_: a set of statements executed in order
  2. _selection_: a group of statements are executed based on a condition
  3. _iteration_: a control structure

### Control structures and complexity

* a technique for counting complexity of a routine
  1. start w/ 1 for the straight path through the routine
  2. add 1 for each of the following keywords: `if`, `while`, `repeat`, `for`, `and`, `or`
  3. add 1 for each case in a `switch` statement

* rate your routine on this scale:
  - 0-5: probably fine
  - 6-10: try simplifying routine
  - 10+: break routine into 2 and call second part from the first routine

## Chapter 20: The software-quality landscape

* characteristics of software quality: correctness, usability, efficiency, reliability, integrity, adaptability, accuracy, robustness

* internal quality characteristics: maintainability, flexibility, portability, reusability, readability, testability, understandability

### Relative effectiveness of quality techniques

* by using practices in extreme programming, you can get a 74-97% defect-detection rate
  - informal design reviews (pair programming) [25-40%], informal code reviews (pair programming) [20-35%], personal desk-checking of code [20-60%], unit tests [15-50%], integration tests [25-40%], regression tests [15-30%]

## Chapter 21: Collaborative construction

### Pair programming

* don't force pair programming for the easy part (you could just collaborate on design & then program solo)

* rotate pairs & work assignments regularly (cross-pollination, learning different parts of the system)

### Formal inspections

* checklists to focus reviewers' attention on past problem areas

* inspection focuses on defect detection, not correction

* for rework (correcting defects), a person other than the author is assigned

### Other collaborative development practices

* code walk-through (by author of design/code), focused on technical issues
  - participants prepare ahead of time by reading the design/code & looking for errors
  - focus on error detection, not correction
  - 30-60 min

## Chapter 22: Developer testing

* _dirty tests_ vs _clean tests_:
  - _clean tests_: tests whether the code works
  - _dirty tests_: testing for all the ways the code could break

* use complexity of program to decide how many test cases to write

* test data flow by making tests for each of the states of data: defined, used, and killed

* things to test for when looking for bad data:
  - too little/no data
  - too much data
  - the wrong kind of data (invalid data)
  - the wrong size of data
  - uninitialized data

* things to look for in good data:
  - expected values
  - minimum & maximum normal configurations
  - compatibility with old data

### Test-support tools

* test data generators

* data recorder/logging (log significant errors)

* use system perturbers
  - memory filling
  - memory shaking (makes sure you haven't written code that depends on data being in an absolute, rather than relative, location)
  - selective memory failing (low memory conditions)
  - memory access checking (bounds checking) for pointer behavior

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
