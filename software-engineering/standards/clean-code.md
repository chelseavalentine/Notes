# Chapter 1: Clean Code

- code is the detailed specification of requirements

### The Total Cost of Owning a Mess

- "As the mess builds, the productivity of the team continues to decrease, asymptotically approaching zero."
- "We bemoan the schedules that were too tight to do things right."
- "You will _not_ make the deadline by making the mess. The mess will slow you down instantly, and force you to miss the deadline. The _only_ way to make the deadline--the only way to go fast--is to keep the code as clean as possible at all times."

### We are Authors

- "We are authors. And one thing about authors is that they have readers. Authors are responsible for communicating well with their readers. The next time you write a line of code, remember you are an author, writing for readers who will judge your effort."

# Chapter 2: Meaningful Names

### Use Intention-Revealing Names

- good names answer these questions:
  - why it exists
  - what it does
  - how it's used

### Avoid disinformation

- Be careful with word choice, such as 'list,' when creating variable names; if it doesn't refer to a real list, then you shouldn't put 'list' in the name

### Make meaningful distinctions

- if you want to use the same name for two things, don't just slightly modify the name for the second name to satisfy the compiler
  - if names must be different, they should also mean something different

### Use pronounceable names

### Use searchable names

- single-letter names should only be used as local variables inside short methods
  - the length of a name should correspond to the size of its scope

### Avoid encodings

- eg. NameString, amountInt

### Avoid mental mapping

- don't make readers translate names into other names they already know

### Class names

- class names should have noun/noun phrase names.

### Method names

- methods should have verb/verb phrase names
- accessors, getters, & predicates should be named for their value & prefixed with `get`, `set`, and `is`

### Don't use puns

- In the sense that you shouldn't use one word to mean more than 1 thing
  - eg. using 'add' to append to a list and add two numbers together

### Use solution domain names and solution domain names

- Names that relate to programming concepts, and names that relate to the nature of the problem you're solving

### Add meaningful context

- If a name isn't meaningful enough, or could be mistaken for a different meaning of the word, then you can add a prefix (eg. addrState, rather than state)

# Chapter 3: Functions

- functions should be small; no more than 20 lines

### Blocks and indenting

- `if`, `else`, `while`, `for`, etc. statements should be kept to one line long (line prob = function call)
- shouldn't have more than 2 indent levels in a function

### Do one thing

- Functions should do one thing
  - If you can extract another function from a function, with a name that isn't a restatement of the function's implementation, then your function isn't small enough
  - thus, there shouldn't be sections of a function... that's not doing one thing

### One level of abstraction per function

- doing so makes it easier for the reader to not get confused as to whether statements are large concepts or small details

### Reading Code from Top to Bottom: _The Stepdown Rule_

- Code should look like a series of short 'TO' paragraphs

eg.
    To include the setups and teardowns, we include setups, then we include the tent, and then we include the teardowns.

```
To include the setups, we include the suite setup if this is a suite, then we include the regular setup.

To include the suite setup, we search the parent hierarchy for the...

To search the parent ...
```

As you can see, the abstraction level is constant for each TO paragraph

### Switch statements

- `switch` statements are tolerable if they meet these conditions:
  - appear only once
  - are used to create polymorphic objects
  - are hidden behind an inheritance relationship so the system can't see them

Switch statements aren't great because they're hard to keep small, and they don't scale well.

```java
public abstract class Employee {
    public abstract boolean isPayDay();
    public abstract Money calculatePay();
    public abstract void deliverPay(Money pay);
}

public interface EmployeeFactory {
    public Employee makeEmployee(EmployeeRecord r) throws InvalidEmployeeType;
}

public class EmployeeFactoryImpl implements EmployeeFactory {
    public Employee makeEmployee(EmployeeRecord r) throws InvalidEmployeeType {
        switch (r.type) {
            case COMISSIONED:
                return new CommissionedEmployee(r);
            case HOURLY:
                return new HourlyEmployee(r);
            case SALARIED:
                return new SalariedEmployee(r);
            default:
                throw new InvalidEmployeeType(r.type);
        }
    }
}
```

### Use Descriptive Names

- Don't be afraid to make a name long; a long descriptive name > short enigmatic name
  - it's also better than a long descriptive comment
- You should try several names to see which name makes the code more readable

### Function Arguments

- the ideal number of arguments for a function is 0. Three or more should be avoided.
  - `includeSetupPage()` is easier to understand than `includeSetupPageInto(newPageContent)`
  - arguments are harder to test, because you need to test combinations of arguments as well
- output arguments are harder to understand than input arguments, because you're used to thinking about things going into a function, and values being returned

### Common Monadic Forms

- monadic function = function with 1 argument

- avoid functions that don't follow these forms, eg. `void includeSetupPageInto(StringBuffer pageText)`

  - the input should refer to what is going in, and the return value should be named after what the transformation will turn the input into

  ### Flag Arguments

- passing a boolean into a function = terrible practice; and it inherently means that the function does more than 1 thing

  - something if the flag is true, and something if it is false

  ### Dyadic functions

- dyadic function = function with 2 arguments

- times where dyadic functions are appropriate

  - things like functions to make Cartesian points

- problems with dyadic functions

  - there often isn't a natural ordering for the arguments, so you need to learn the order

    - `assertEquals(expected, actual)`

    ### Triads

- triad: function w/ 3 arguments

- don't do it

### Argument Objects

- if 2-3+ arguments are necessary, it could be that you need to wrap some arguments in an object

  - consider: `Circle makeCircle(Point center, double radius)` rather than `Circle makeCircle(double x, double y, double radius)`

  ### Verbs and Keywords

- monadic functions & their arguments should be a verb/noun pair:

  - `write(name)` or `writeField(name)` is good

- make reminders of argument orders in function names can be helpful

  - `assertExpectedEqualsActual(expected, actual)` is better than `assertEquals(...)`

  ### Have No Side Effects

- be wary of whether a function is doing things that you haven't described it as doing

  - (eg. opening access to edit the data for future functions, when you're only trying to do a simple compare value)

  ### Output Arguments

- If your function has to change the state of something, have it change the state of its owning object

### Command Query Separation

- functions should either do something (change state) or answer something (return information)

### Prefer Exceptions to Returning Error Codes

- If you use exceptions, rather than returned error codes, then the error processing code can be separated from the path code, and be simplified:

```java
try {
    deletePage(page);
    registry.deleteReference(page.name);
    configKeys.deleteKey(page.name.makeKey());
}
catch (Exception e) {
    logger.log(e.getMessage());
}
```

##### Extract Try/Catch Blocks

- You should extract the bodies of the try & catch blocks into functions of their own

```java
public void delete(Page page) {
    try {
        deletePageAndAllReferences(page);
    }
    catch (Exception e) {
        logError(e);
    }
}
```

A function that handles errors has satisfied its 'one thing' (ie. it should do nothing else)

### The `Error.java` Dependency Magnet

- Returning error codes implies a class/enum w/ all the error codes defined

  - this means when you change the Error enum/class, you need to recompile and redeploy the classes that rely on them

    - use exceptions rather than error codes, because the new exceptions are derivatives of the exception class & can be added w/o forcing recompilation/redeployment

    ### Don't Repeat Yourself

    ### Structured Programming

- Dijkstra's rules of structured programming:

  - every function, & every block w/i a function, should have 1 entry & 1 exit;
    - so only 1 `return` statement, no `break`/`continue` statements in a loop, && no `goto` statements

- this doesn't really matter if your functions are small anyway; only thing that should be avoided is `goto` (b/c used in large functions)

### How Do You Write Functions Like This?

- create draft, then continuously test and refine it

# Chapter 4: Comments

- the proper use of comments is to compensate for failure to express yourself in code
  - their use isn't a cause for celebration
- Comments Do Not Make Up for Bad Code
- Explain Yourself in Code

## Good Comments

##### Legal Comments

Reference to licenses, authorship, etc. statements

###### Informative Comments

eg. ones explaining RegExps

###### Explanation of Intent

###### Clarification

Making things more readable (eg. `a.compareTo(b) == -1`)... but make sure that it's correct!

###### Warning of Consequences

###### TODO Comments

This isn't an excuse to leave in bad code

###### Amplification

## Bad Comments

- Mumbling
- Redundant Comments
- Misleading Comments
- Mandated Comments
- Journal Comments
- Don't Use a Comment When You Can Use a Function or a Variable
- Position Markers
- Closing Brace Comments
- Attributions and Bylines
- Commented-out Code
- Nonlocal Information
- Too Much Information
- Inobvious Connection
- Function Headers

# Chapter 5: Formatting

### Vertical Formatting

##### The Newspaper Metaphor

- topmost parts hold the high-level concepts & algorithms
- at the end, there should be low-level functions & details

##### Vertical Openness Between Concepts

- each line represents an expression/clause; & each group of lines repesents a complete thought
  - thoughts should be separated by a blank line

##### Vertical Density

Put code close together to imply close association

##### Vertical Distance

Concepts closely related should be kept vertically close to each other; and you should have them in the same file, too.

- variable declarations
  - as close to usage as possible
- instance variables
  - declared at the top of the class
  - used by many of the class's methods
- dependent functions
  - functions that call on another should be vertically close, w/ caller above the callee
- conceptual affinity
  - if you have a lot of functions that perform the same kind of use, then you should have less vertical distance between them
    - eg. a group of functions that assertTrue, but have different parameters

### Horizontal Formatting

- limit your line length to ~120 characters. Less is better.

##### Horizontal Openness and Density

- spaces around assignment operators imply that the two sides are different
- no space between a function name and its arguments implies that they're closely related
- use whitespace to accentuate the precedence of operators

```java
private static double determinant(double a, double b, double c) {
    return b*b - 4*a*c;
}
```

### Team Rules

A team of developers should agree on a single formatting style, & then every member should use that style.

# Chapter 6: Objects and Data Structures

- Hiding implementation is not just a matter of putting a layer of functions between the variables. Hiding implementation is about abstractions

  - Serious thought needs to be put into the best way to represent the data that an object contains. The worst option is to blithely add getters and setters.

- Objects hide their data behind abstractions and expose functions that operate on that data. Data structures expose their data and have no meaningful functions.

- the idea that everything is an object is a myth; sometimes you really do want simple data structures with procedures operating on them

- __The Law of Demeter__: modules shouldn't know the inner workings of the objects it manipulates, which means (in the context of C): a method _f_ of class _C_ should only call methods of these

  - _C_
  - an object created by _f_
  - an object passed as an arg to _f_
  - an object held in instance variable of _C_

- therefore, by this law, the following is a violation:

  ```c
  final String outputDir = ctxt.getOptions().getScratchDir().getAbsolutePath();
  ```

- instead of chaining methods, separate them into separate lines, so you can name & write the type for their outputs

- __data transfer object__ (DTO): an object with public variables and no functions

- usage: communicing w/ databases, parsing messages from sockets, etc. to convert raw db data into objects in the app
- __Active record__: a DTO with the addition of navigational methods like `save` and `find`

- objects expose behavior and hide data
- data structures expose data and don't have any significant behavior

# Chapter 7: Error Handling

- use exceptions rather than return codes (so caller doesn't need to check return call/error flag)
- create informative error messages and pass them along with your exceptions. Mention the operation that failed and the type of failure

### Wrapping third-party APIs

- if many types of exceptions need to be caught (often resulting in duplicate ways of handling), consider defining an exception class in terms of the caller's needs
  - the wrapper class will return a common exception type, so you only need to catch one exception instead of several

- advantages:
  - minimizing dependencies on the API
  - can mock third-party calls in testing
  - arent tied to the API's design choices

- __special case object__: you can create a class/configure an object so that it handles special cases for you, instead of dealing w/ exceptional behavior

### Don't return null

- throw an exception or return a special case object instead; otherwise a missing null check => disaster
- don't pass null either; susceptible to runtime errors

# Chapter 8: Boundaries

- encapsulate boundary interfaces (eg. `Map`) inside a class; avoid returning it form/accepting it as an argument to public APIs
- encapsulate APIs in classes as well, so you don't need to worry about the peculiarities of a class each time you want to use it
- write learning tests (tests that verify that the third-party packages work as intended)
  - they'll also help us figure out when the functionality changes
  - helps us avoid thinking that upgrading version is risky, since we have tests to prove that the newer version works
- you can write classes for code that doesn't exist yet (eg. if you know an API will provide x functionality, you can write the class that will help you interface with that API)

# Chapter 9: Unit Tests

### Three laws of TDD

1. You may not write production code until you've written a failing unit test
2. You may not write more of a unit test than is sufficient to fail, & not compiling is failing
3. You may not write more production code than is sufficient to pass the currently failing test.

- having no tests is <= having no tests;

  - need to modify tests as production code changes
  - test code is just as important as production code

  - must be as simple, succinct, & expressive, but doesn't need to be as efficient

- one assert per test

- can eliminate/reduce duplication by using the _template method_ pattern, where you create `given`/`when` formatted functions (eg. `whenRequestIsIssued`)

- single concept per test

- tests should be:

  - independent
  - repeatable in any environment (eg. prod, dev, local)
    - if your tests aren’t repeatable in any environment, then you’ll always have an excuse for why they fail."
  - self-validating (return a boolean)

# Chapter 10: Classes

- classes begin w/ a list of variables, ordered as such:
  - [1] public static constants, [2] private static variables, [3] private instance variables
  - functions: [1] public functions, [1.5] the related private functions]
- sometimes variables need to be made `protected`, rather than `private` so tests can access them
- classes need to be small (measured in _responsibilities_); class name describes its responsibilities
  - should be able to describe its functionality in <= 25 words, w/o using the words "if", "and", "or", or "but"
- __Single Responsibility Principle  (SRP)__: a class/module should have only 1 reason to change;
- cohesion means that there are as few instance variables as possible, and that instance variables (w/i reasonableness) are shared maximally amongst the methods
- Dependency Inversion Principle (DIP): a design principle saying that classes should depend upon abstractions, not concrete details

# Chapter 11: Systems

- avoid lazy initialization (checking whether a variable is null, and then initializing it if it is)
- separate construction/setup from use by moving construction to `main` or modules called by it, and then design the rest of the system to assume they all exist
- use __dependency injection (DI)__ because an object shouldn't have responsibility for instantiating dependencies itself, it should invert the control (__Inversion of Control (IoC)__) to an authoritative mechanism
- you can also have class provide setter methods or constructor arguments to inject dependencies
- separation of concerns helps us out when we need to refactor the system to scale it out

# Chapter 12: Emergence

- 4 rules than facilitate the emergence of a good "simple" design:

1. runs all the tests
2. contains no duplication
3. expresses the programmer's intent
4. minimizes the number of classes and methods

# Chapter 13: Concurrency

- concurrency = decoupling strategy, since it allows us to decouple _what_ gets done from _when_ it gets done
  - can improve an application's throughput & structure
  - can improve performance if there's a lot of wait time that can be shared b/t multiple threads/processes

> concurrency bugs aren’t usually repeatable, so they are often ignored as one-offs instead of the true defects they are

### Concurrency defense principles

Principles to defend a system from concurrent code problems

- Single Responsibility Principle (SRP); additionally, keep concurrency-related code separate from other code
- use the `synchronized` keyword to protect a critical section of code using a shared object, but limit these situations
  - but if there's more than 1 synchronized method on a shared class, your system is probably written incorrectly
- instead of sharing data, copy objects & treat them as read-only; you could always merge the threads of copies later into a single one
- attempt to partition data into independent subsets that can be operated on by independent threads, possibly in different processors
- use thread-safe collections, classes, & other libraries available to you (more examples in Java: executor framework for unrelated tasks, nonblocking solutions, etc.)
  - more eg. `ReentrantLock`, `Semaphore`, `CountDownLatch`
- for the few times when you use more than 1 method on a shared object, use one of these methods to make the code correct:
  1. __Client-based locking__: client locks the server before calling the first method
  2. __Server-based locking__: W/i the server, create a method to lock the server, call all the methods, and then unlock; then the client calls the new method
  3. __Adapted server__: create an intermediary to perform the locking; this is an example where the original server can't be changed
- keep synchronized sections as small as possible
- work on the art of writing correct shutdown code (to prevent deadlocks, etc.)
- write tests w/ the potential to expose problems in threaded code, & run them w/ different programmatic & system configurations
- treat spurious failures as candidate threading issues
- get your non-threaded code working first

### Testing threaded code

- make it pluggable, so you can run it in several configurations
- make it tunable, for the sake of configurations as well
- run with more threads than processors
  - encourages the system to task swap, potentially exposing a missing critical section, or deadlock-inducing code
- run on different platforms
- instrument your code to try & force failures
  - eg. by adding calls to methods like `Object.wait()`, `Object.sleep()`, `Object.yield()`, and `Object.priority()`
  - you can do this with an automated test framework (eg. CGLIB, ASM, or an Aspect-Oriented Framework)
  - use the jiggling strategy to ferret out errors

# Chapter 14: Successive Refinement

- essentially, the first working version of your code is still a work in progress; you need to refine it, perhaps even a few times

# Chapter 15: JUnit Intervals

- essentially, JUnit is great and makes for extremely readable tests

# Chapter 17: Smells and Heuristics

### Comments

- inappropriate information (which is better held elsewhere, eg. change histories, meta-data, etc.)
- obsolete or redundant comments

### Environment

- build requires more than one step
- tests require more than 1 step

### Functions

- output & flag arguments
- dead functions (which are never called)

### General

- incorrect behavior at boundaries

- overridden safety features (eg. turning off warnings)

- coding at the wrong level of abstraction (eg. base class shouldn't know super specific implementation details)

- using accessors & mutators of some other object to manipulate the data within that object ("feature envy")

- misplaced responsibility (putting code in the wrong place)

- dependent modules should explicitly ask the module it depends on for all the info it depends on

- polymorphism is better than if/else or switch/case statements

- encapsulate conditionals

- avoid negative conditionals

- encapsulate boundary conditions

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

- functions should descend only 1 level of abstraction

- keep configurable data at high levels

- don't let a model know too much of the navigation map of the system (eg. `a.getB().getC()` is excessive in excess)

### Java

- avoid long import lists with wildcards (using 2 or more classes from a package -> import whole package)
- don't inherit constants; use a static import instead
- use enums over constants

### Names

- choose names at an appropriate level of abstraction

### Tests

- don't skip trivial tests