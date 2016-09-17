# Chapter 9: Unit Tests

### Three laws of TDD

1. You may not write production code until you've written a failing unit test
2. You may not write more of a unit test than is sufficient to fail, & not compiling is failing
3. You may not write more production code than is sufficient to pass the currently failing test.

* having no tests is <= having no tests;
  - need to modify tests as production code changes
  - test code is just as important as production code
   * must be as simple, succinct, & expressive, but doesn't need to be as efficient

* one assert per test

* can eliminate/reduce duplication by using the _template method_ pattern, where you create `given`/`when` formatted functions (eg. `whenRequestIsIssued`)

* single concept per test

* tests should be:
  - independent
  - repeatable in any environment (eg. prod, dev, local)
    * if your tests aren’t repeatable in any environment, then you’ll always have an excuse for why they fail."
  - self-validating (return a boolean)

# Chapter 10: Classes

* classes begin w/ a list of variables, ordered as such:
  - [1] public static constants, [2] private static variables, [3] private instance variables
  - functions: [1] public functions, [1.5] the related private functions]

* sometimes variables need to be made `protected`, rather than `private` so tests can access them

* classes need to be small (measured in _responsibilities_); class name describes its responsibilities
  - should be able to describe its functionality in <= 25 words, w/o using the words "if", "and", "or", or "but"

* __Single Responsibility Principle  (SRP)__: a class/module should have only 1 reason to change;

* cohesion means that there are as few instance variables as possible, and that instance variables (w/i reasonableness) are shared maximally amongst the methods

* Dependency Inversion Principle (DIP): a design principle saying that classes should depend upon abstractions, not concrete details

# Chapter 11: Systems

* avoid lazy initialization (checking whether a variable is null, and then initializing it if it is)

* separate construction/setup from use by moving construction to `main` or modules called by it, and then design the rest of the system to assume they all exist

* use __dependency injection (DI)__ because an object shouldn't have responsibility for instantiating dependencies itself, it should invert the control (__Inversion of Control (IoC)__) to an authoritative mechanism

* you can also have class provide setter methods or constructor arguments to inject dependencies

* separation of concerns helps us out when we need to refactor the system to scale it out

# Chapter 12: Emergence

* 4 rules than facilitate the emergence of a good "simple" design:

1. runs all the tests
2. contains no duplication
3. expresses the programmer's intent
4. minimizes the number of classes and methods

# Chapter 13: Concurrency

* concurrency = decoupling strategy, since it allows us to decouple _what_ gets done from _when_ it gets done
  - can improve an application's throughput & structure
  - can improve performance if there's a lot of wait time that can be shared b/t multiple threads/processes

> concurrency bugs aren’t usually repeatable, so they are often ignored as one-offs instead of the true defects they are

### Concurrency defense principles

Principles to defend a system from concurrent code problems

* Single Responsibility Principle (SRP); additionally, keep concurrency-related code separate from other code

* use the `synchronized` keyword to protect a critical section of code using a shared object, but limit these situations
  - but if there's more than 1 synchronized method on a shared class, your system is probably written incorrectly

* instead of sharing data, copy objects & treat them as read-only; you could always merge the threads of copies later into a single one

* attempt to partition data into independent subsets that can be operated on by independent threads, possibly in different processors

* use thread-safe collections, classes, & other libraries available to you (more examples in Java: executor framework for unrelated tasks, nonblocking solutions, etc.)
  - more eg. `ReentrantLock`, `Semaphore`, `CountDownLatch`

* for the few times when you use more than 1 method on a shared object, use one of these methods to make the code correct:
  1. __Client-based locking__: client locks the server before calling the first method
  2. __Server-based locking__: W/i the server, create a method to lock the server, call all the methods, and then unlock; then the client calls the new method
  3. __Adapted server__: create an intermediary to perform the locking; this is an example where the original server can't be changed

* keep synchronized sections as small as possible

* work on the art of writing correct shutdown code (to prevent deadlocks, etc.)

* write tests w/ the potential to expose problems in threaded code, & run them w/ different programmatic & system configurations

* treat spurious failures as candidate threading issues

* get your non-threaded code working first

### Testing threaded code

* make it pluggable, so you can run it in several configurations

* make it tunable, for the sake of configurations as well

* run with more threads than processors
  - encourages the system to task swap, potentially exposing a missing critical section, or deadlock-inducing code

* run on different platforms

* instrument your code to try & force failures
  - eg. by adding calls to methods like `Object.wait()`, `Object.sleep()`, `Object.yield()`, and `Object.priority()`
  - you can do this with an automated test framework (eg. CGLIB, ASM, or an Aspect-Oriented Framework)
  - use the jiggling strategy to ferret out errors

# Chapter 14: Successive Refinement

* essentially, the first working version of your code is still a work in progress; you need to refine it, perhaps even a few times

# Chapter 15: JUnit Intervals

* essentially, JUnit is great and makes for extremely readable tests

# Chapter 17: Smells and Heuristics (316)

### Comments

* inappropriate information (which is better held elsewhere, eg. change histories, meta-data, etc.)

* obsolete or redundant comments

### Environment

* build requires more than one step

* tests require more than 1 step

### Functions

* output & flag arguments

* dead functions (which are never called)

### General

* incorrect behavior at boundaries

* overridden safety features (eg. turning off warnings)

* coding at the wrong level of abstraction (eg. base class shouldn't know super specific implementation details)

* using accessors & mutators of some other object to manipulate the data within that object ("feature envy")

* misplaced responsibility (putting code in the wrong place)

* dependent modules should explicitly ask the module it depends on for all the info it depends on

* polymorphism is better than if/else or switch/case statements

* encapsulate conditionals

* avoid negative conditionals

* encapsulate boundary conditions
  ```java
  if (level + 1 < tags.length) {
    // ...
  }

  // is worse than

  int nextLevel = level + 1;

  if (nextLevel < tags.length) {
    // ...
  }
  ```

* functions should descend only 1 level of abstraction

* keep configurable data at high levels

* don't let a model know too much of the navigation map of the system (eg. `a.getB().getC()` is excessive in excess)

### Java

* avoid long import lists with wildcards (using 2 or more classes from a package -> import whole package)

* don't inherit constants; use a static import instead

* use enums over constants

### Names

* choose names at an appropriate level of abstraction

### Tests

* don't skip trivial tests
