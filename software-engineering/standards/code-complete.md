# Code Complete

## Chapter 3: Prerequisites

- generally 20-30% of a project's schedule is dedicated to requirements, architecture, and upfront-planning
- Requirements checklist (p. 42)
- Architecture specification / top-level design
  - data design
- Architecture checklist (p. 54)
- Major construction practices (p. 69)

## Chapter 5: Design in construction

- __extensibility__: measurement of the ease in which you can enhance a system w/o affecting its underlying structure
- __high fan-in__: having a number of classes using a given class
  - good systems make use of utility classes at lower system levels
- __stratification__: making it possible to see a complete view of software at several levels of abstraction
- a program's structure of classes and their relations should be an acyclic graph
- identify areas likely to change
  - separate items likely to change
  - isolate items that seem likely to change
  - use enums for error codes/status variables
  - make program thread-safe

#### Popular design patterns

- __Abstract Factory__: creating sets of related objects by specifying the kind of set, but not the kinds of each specific object
- __Adapter__: converts the interface of a class to a different interface
- __Bridge__: builds an interface & implementation in such a way that either can vary w/o the other varying
- __Composite__: object contains additional objects of its own type, so that client code can interact w/ the top level object & not concern itself w/ all the detailed objects
- __Decorator__: attaches responsibilities to an object dynamically, w/o creating specific subclasses for each possible configuration of responsibilities
- __Facade__: provides a consistent interface to code that wouldn't otherwise have one
- __Factory Method__: instantiates classes derived from a specific base class w/o needing to keep track of the individual derived classes anywhere, except for the Factory Method
- __Iterator__: a server object that provides access to each element in a set sequentially
- __Observer__: keeps multiple objects in sync w/ each other by making an object responsible for notifying the set of related objects about changes to any member of the set
- __Singleton__: provides global access to a class that has one and only one instance
- __Strategy__: defines a set of algorithms/behaviors that're dynamically interchangeable with each other
- __Template Method__: defines the structure of an algorithm but leaves some of the detailed implementation to subclasses

#### Other heuristics

- enforce __preconditions__ & __postconditions__
- choose the time that you bind values to variables carefully
- Design practices checklist (p. 122)

## Chapter 6: Working classes

### Good class interfaces

- don't add public members that're inconsistent w/ the class's abstraction level
- consider both abstraction and cohesion together
- avoid putting private implementation details into a class's interface
  - eg. having a constructor w/ a 'name' parameter, and a private variable for name
    - it exposes that name is a String; instead, you could create an implementation class (eg. EmployeeImplementation) & have a pointer to the implementation

### Design and implementation issues

- be critical of classes containing more than 7 data members
- design & document for inheritance, or prohibit it (eg. in Java, declare a class `final`)
- be suspicious if a class only has 1 instance
- avoid deep inheritance trees (try 2-3 levels at most, but some suggest 7 +/- 2)
- if multiple classes share common data but not behavior, create a common object that those classes can contain

#### Constructors

- initialize all member data in constructors, if possible
- prefer deep copies to shallow copies until proven otherwise
- Class quality checklist (p. 194)

## Chapter 7: High-quality routines

### Valid reasons to create a routine

- reduce complexity
- introduce an immediate, understandable abstraction
- support subclassing by needing less new code to override a short routine
- hide sequences in which things happen; sequences don't depend on an ordering
- simplify complicated boolean tests
- improves performance since you can optimize the code in one place rather than everywhere
- small operations tend to turn into large one, as you check for edge cases

### Design at the routine level

- make sure routines have a single, complete job

### Good routine names

- describe everything the routine does, and if you end up with a silly/long name, it's a sign you need to rework
- avoid meaningless/vague/wishy-washy verbs (eg. 'handle', 'perform', 'output', 'process', 'dealWith')
  - unless 'handle' was used in error handling context
- to name a function, use a description of the return value (eg. currentColor)
- to name a procedure, use a strong verb followed by the object
- use opposites precisely, some common ones:
  - add/remove, increment/decrement, open/close, begin/end, insert/delete, show/hide
  - create/destroy, lock/unlock, source/target, first/last, min/max, start/stop
  - get/put, next/previous, up/down, get/set, old/new
- establish team-wide conventions for common operations

### How to use routine parameters

- put params in input-modify-output order
  - input-only first, input-and-output second, output-only third
- status/error variables last
- don't use params as working variables

### Macro routines and inline routines

- fully parenthesize macro expressions
- surround multiple-statement macros with curly braces
- name macros that expand to code like routines in case you need to replace them with routines
- High-quality routines checklist (p. 222)

## Chapter 8: Defensive programming

### Assertions

- use assertions for conditions that should never occur, & error handling for conditions you expect to occur

- for highly robust code, assert and then handle the code anyway

- avoid putting executable code into assertions

### Error-handling techniques

- return a neutral value (eg. 0, empty string, empty pointer) if it's known to be harmless
- continue until the next piece of valid data, if processing a stream of data
- return the same answer as the previous time, if it's processing a stream of harmless data (eg. next thermometer reading)
- substitute the closest legal value (eg. if you get -1 when it must be positive or 0, substitute with 0)
- return an error code (eg. throw an exception, return status as the function's return value, set the value of a status variable)
- call an error-processing routine/object
  - could be bad if an attacker has caused a buffer overrun attack
- display an error message
- shut down
- consumer apps tend to favor robustness over correctness; safety-critical apps tend to favor correctness over robustness

### Exceptions

- use exceptions to notify other parts of the program about errors that should not be ignored
- avoid throwing exceptions in constructors and destructors unless you catch them in the same place
- know the exceptions your library code throws
- standardize your project's use of exceptions

### Barricade your program to contain the damage caused by errors

- convert input data to the proper type at input time

### Offensive programming

- ways to check for edge cases
  - make sure _asserts_ abort the program
  - fill any memory allocated, so you can detect memory allocation errors
  - completely fill any files/streams allocated to flush out file-format errors
  - be sure the code in ease `case` statement's `default` or `else` clause fails hard, or is otherwise impossible to overlook
  - fill an object w/ junk data just before it's deleted
  - set up the program to email error log files to yourself to see what's occurring on prod

### Determining how much defensive programming to leave in production code

- leave in code that checks for important errors
- remove code checking for trivial errors (don't delete, just don't compile it)
- remove code resulting in hard crashes
- Defensive programming checklist (p. 248)

## Chapter 10: General issues in using variables

- initialize variables as they're declared, & close to where it's first used
- check input parameters for validity (eg. are they actually positive)
- use a memory-access checker to check against bad pointers

### Scope

- keep variables "live" for a short as possible ("live" = last statement in which it's referenced - first statement in which it's referenced)
  - reader needs to remember fewer variables
- break groups of related statements into separate routines
- use each variable for exactly one purpose
- avoid variables with hidden meanings (eg. the meaning changes based on the range of values it takes on; eg. when pageCount is +, it means the number of pages, if it's negative, it means an error code)

## Chapter 11: The power of variable names

### Naming specific types of data

- if you have several nested loops, assign longer names to the loop variables to improve readability (if applicable)
- if the loop is really long, you may need a longer name so that you can remember the use of the variable
- typical boolean names: done, error, found, success, ok
- abstract the entity the constant refers to, rather than the number (eg... MAX_DONUTS is better than DOZEN)

### Guidelines for language-specific conventions

- C conventions
  - character variables: `c`, `ch`
  - integer indexes: `i`, `j`
  - a number of something: `n`
  - a pointer: `p`,
  - a string: `s`,
  - preprocessor macros in ALL_CAPS
  - variable & routine names in all_lowercase
  - _ as a word separator
- C++ conventions, same as C, except for
  - camelCase
- Java conventions
  - integer indexes: `i`, `j`
  - constants in ALL_CAPS
- document extremely short names

## Chapter 12: Fundamental data types

### Numbers in general

- make type conversions obvious
- avoid mixed-type comparisons
- check for integer overflow, including in intermediate results
- for floats
  - avoid additions & subtractions on numbers that have greatly different magnitudes
  - avoid equality comparisons
  - anticipate rounding numbers
- for strings/characters:
  - decide on an internationalization/localization strategy early
  - in C: set the end of the string to `NULL`
  - in C: use `strncpy()` instead of `strcpy()` to avoid endless strings

### Enumerated types

- define the first & last entries of an enum for use as loop limits (value is a repeat of the actual first and last)
- reserve the first entry in the enum as invalid (usually `0`)

## Chapter 13: Unusual data types

### Structures

- use structures to simplify operations on blocks of data, and to simplify parameter lists
- use structures to reduce maintenance

### Pointers

- isolate pointer operations in routines or classes
- declare and define pointers at the same time
- delete pointers at the same scoping level as they were allocated
- check pointers before using them (eg. check that a data pointer is between start & end data)
- tag a field in a structure solely for the purpose of error checking, to ensure that the data hasn't been corrupted
- use extra pointer variables for clarity
- set pointers to null after deleting/freeing them, so you get an error if you try to use it

## Chapter 14: Organizing straight-line code

- organize code so that dependencies are obvious (eg. write an initializing routine)
- use routine params to make dependencies obvious (eg. all of the routines take the same param)
- document unclear dependencies with comments
- check for dependencies with assertions or error-handling code

## Chapter 15: Using conditionals

### `if` statements

- write the normal path through the code first, then write the unusual cases
- when you have an `if` test w/o an `else`, unless the reason is obvious, use comments to explain why the `else` clause isn't necessary (within the `else` block)

### `case` statements

- if cases are equally important, order them alphabetically or numerically
- put the normal case first
- put the most common case first
- only use the default clause to detect legitimate defaults (not the remaining case)
  - use it to detect errors

## Chapter 16: Controlling loops

- keep loop-housekeeping chores at either the beginning or end of the loop
- make each loop perform only one function
- avoid code that depends on the loop index's final value
- consider using safety counters to determine whether the loop has executed too many times

## Chapter 17: Unusual control structures

- use safety counters to prevent infinite recursion
- don't use recursion for factorials or Fibonacci numbers

## Chapter 18: Table-driven methods

- __table-driven method__: a scheme where you look up info in a table, rather than `if`/`switch` statements
- if the entries in a lookup table are large, it takes a lot less space to create an index array with a lot of wasted space than it does to take a lookup table w/ a lot of wasted space

## Chapter 19: General control issues

### Boolean expressions

- consider using decision tables to replace complicated conditions
- write boolean statements with positive tests, rather than negative ones
  - use DeMorgan's Theorems to create positive tests from negative ones
    - (!this || !that) == !(this && that)
- write numeric expressions in number-line order (ie. smaller values -> larger values)

### Null statements

- create a preprocessor `doNothing()` macro, or an inline function for null statements for emphasis
  - alternatively, consider whether the code would be clearer w/ a non-null loop body

### Taming dangerously deep nesting

- retest a part of the condition (splitting it into 2+ blocks)
- use a break block
- see if you can use `if-else` rather than just a nested `if`
- convert to a `switch` statement
- move part of the if-statement logic into its own routine

### Structured programming

- __structured programming__
  - a program should use only _single-entry, single-exit_ control constructs (only have one place it starts, & one place it could end)
- 3 components of structured programming:
  1. _sequence_: a set of statements executed in order
  2. _selection_: a group of statements are executed based on a condition
  3. _iteration_: a control structure

### Control structures and complexity

- a technique for counting complexity of a routine
  1. start w/ 1 for the straight path through the routine
  2. add 1 for each of the following keywords: `if`, `while`, `repeat`, `for`, `and`, `or`
  3. add 1 for each case in a `switch` statement
- rate your routine on this scale:
  - 0-5: probably fine
  - 6-10: try simplifying routine
  - 10+: break routine into 2 and call second part from the first routine

## Chapter 20: The software-quality landscape

- characteristics of software quality: correctness, usability, efficiency, reliability, integrity, adaptability, accuracy, robustness
- internal quality characteristics: maintainability, flexibility, portability, reusability, readability, testability, understandability

### Relative effectiveness of quality techniques

- by using practices in extreme programming, you can get a 74-97% defect-detection rate
  - informal design reviews (pair programming) [25-40%], informal code reviews (pair programming) [20-35%], personal desk-checking of code [20-60%], unit tests [15-50%], integration tests [25-40%], regression tests [15-30%]

## Chapter 21: Collaborative construction

### Pair programming

- don't force pair programming for the easy part (you could just collaborate on design & then program solo)
- rotate pairs & work assignments regularly (cross-pollination, learning different parts of the system)

### Formal inspections

- checklists to focus reviewers' attention on past problem areas
- inspection focuses on defect detection, not correction
- for rework (correcting defects), a person other than the author is assigned

### Other collaborative development practices

- code walk-through (by author of design/code), focused on technical issues
  - participants prepare ahead of time by reading the design/code & looking for errors
  - focus on error detection, not correction
  - 30-60 min

## Chapter 22: Developer testing

- _dirty tests_ vs _clean tests_:
  - _clean tests_: tests whether the code works
  - _dirty tests_: testing for all the ways the code could break
- use complexity of program to decide how many test cases to write
- test data flow by making tests for each of the states of data: defined, used, and killed
- things to test for when looking for bad data:
  - too little/no data
  - too much data
  - the wrong kind of data (invalid data)
  - the wrong size of data
  - uninitialized data
- things to look for in good data:
  - expected values
  - minimum & maximum normal configurations
  - compatibility with old data

### Test-support tools

- test data generators
- data recorder/logging (log significant errors)
- use system perturbers
  - memory filling
  - memory shaking (makes sure you haven't written code that depends on data being in an absolute, rather than relative, location)
  - selective memory failing (low memory conditions)
  - memory access checking (bounds checking) for pointer behavior

## Chapter 23: Debugging

### Finding a defect

- an error that doesn't occur predictably is usually an initialization error, timing issue, or dangling-pointer problem
- __stabilizing an error__: narrowing a test case to the simplest one that still produces the error
- set a maximum time for "quick and dirty" debugging, so you know whether it's a more complex issue than expected

### Fixing a defect

- understand the problem before you fix it, and also understand the program as well
- fix the problem, not the symptom
- make one change at a time
- add a unit test that exposes the defect

### Debugging tools

- set compiler's warning level to the highest, pickiest level possible
- treat warnings as errors

## Chapter 24: Refactoring

- small fixes (1-5 lines) are more likely to have errors than larger fixes, so don't treat them casually
- if a big refactor seems necessary, look at whether a redesign & reimplementation seems more appropriate
- times to check if you need to refactor
  - when you add a routine or class
  - when you fix a defect

## Chapter 25: Code-tuning strategies

### Performance overview

- efficiency can be thought of from these viewpoints:
  - _program requirements_: before you make high performance a requirement, make sure the users require that/wouldn't be fine with less; high performance can make systems more complex
  - program design
  - class and routine design
  - OS interactions
  - _code compilation_: some compilers are better at optimizing when turning high-level language into machine code
  - code tuning

### Code tuning

- reducing the number of lines in a high-level language doesn't necessarily improve the speed/size of the resulting machine code
- you shouldn't optimize as you go, since you don't know where the program's bottlenecks are
- system calls are often expensive, because it often needs a context switch

## Chapter 26: Code-tuning techniques

### Logic

- order tests by frequency
- compare performance of similar logic structures, some are faster than others depending on the language
  - eg. `if-else` is 82% faster in Java than a `switch` statement

### Other optimizations

- replace expensive operations w/ cheaper operations (eg. addition over multiplicatio, multiplication with exponentiation, etc.)
- use the fewest array dimensions possible
- if you know the result of an expression is always the same, replace it with a constant rather than computing it each time
- recoding parts of a program in a low-level language