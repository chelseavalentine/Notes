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

# Chapter 13: Concurrency (208)

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
