# Chapter 1: Clean Code

* code is the detailed specification of requirements

### The Total Cost of Owning a Mess

* "As the mess builds, the productivity of the team continues to decrease, asymptotically approaching zero."
* "We bemoan the schedules that were too tight to do things right."
* "You will _not_ make the deadline by making the mess. The mess will slow you down instantly, and force you to miss the deadline. The _only_ way to make the deadline--the only way to go fast--is to keep the code as clean as possible at all times."

### We are Authors

* "We are authors. And one thing about authors is that they have readers. Authors are responsible for communicating well with their readers. The next time you write a line of code, remember you are an author, writing for readers who will judge your effort."

# Chapter 2: Meaningful Names

### Use Intention-Revealing Names

* good names answer these questions:
    - why it exists
    - what it does
    - how it's used

### Avoid disinformation

* Be careful with word choice, such as 'list,' when creating variable names; if it doesn't refer to a real list, then you shouldn't put 'list' in the name

### Make meaningful distinctions

* if you want to use the same name for two things, don't just slightly modify the name for the second name to satisfy the compiler
    - if names must be different, they should also mean something different

### Use pronounceable names

### Use searchable names

* single-letter names should only be used as local variables inside short methods
    - the length of a name should correspond to the size of its scope

### Avoid encodings

* eg. NameString, amountInt

### Avoid mental mapping

* don't make readers translate names into other names they already know

### Class names

* class names should have noun/noun phrase names.

### Method names

* methods should have verb/verb phrase names
* accessors, getters, & predicates should be named for their value & prefixed with `get`, `set`, and `is`

### Don't use puns

* In the sense that you shouldn't use one word to mean more than 1 thing
    - eg. using 'add' to append to a list and add two numbers together

### Use solution domain names and solution domain names

* Names that relate to programming concepts, and names that relate to the nature of the problem you're solving

### Add meaningful context

* If a name isn't meaningful enough, or could be mistaken for a different meaning of the word, then you can add a prefix (eg. addrState, rather than state)

# Chapter 3: Functions

* functions should be small; no more than 20 lines

### Blocks and indenting

* `if`, `else`, `while`, `for`, etc. statements should be kept to one line long (line prob = function call)
* shouldn't have more than 2 indent levels in a function

### Do one thing

* Functions should do one thing
    - If you can extract another function from a function, with a name that isn't a restatement of the function's implementation, then your function isn't small enough
    - thus, there shouldn't be sections of a function... that's not doing one thing

### One level of abstraction per function

* doing so makes it easier for the reader to not get confused as to whether statements are large concepts or small details

### Reading Code from Top to Bottom: _The Stepdown Rule_

* Code should look like a series of short 'TO' paragraphs

eg.
    To include the setups and teardowns, we include setups, then we include the tent, and then we include the teardowns.

    To include the setups, we include the suite setup if this is a suite, then we include the regular setup.

    To include the suite setup, we search the parent hierarchy for the...

    To search the parent ...

As you can see, the abstraction level is constant for each TO paragraph

### Switch statements

* `switch` statements are tolerable if they meet these conditions:
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

* Don't be afraid to make a name long; a long descriptive name > short enigmatic name
    - it's also better than a long descriptive comment
* You should try several names to see which name makes the code more readable

### Function Arguments

* the ideal number of arguments for a function is 0. Three or more should be avoided.
    - `includeSetupPage()` is easier to understand than `includeSetupPageInto(newPageContent)`
    - arguments are harder to test, because you need to test combinations of arguments as well
* output arguments are harder to understand than input arguments, because you're used to thinking about things going into a function, and values being returned

### Common Monadic Forms

* monadic function = function with 1 argument
* avoid functions that don't follow these forms, eg. `void includeSetupPageInto(StringBuffer pageText)`
    - the input should refer to what is going in, and the return value should be named after what the transformation will turn the input into

    ### Flag Arguments

* passing a boolean into a function = terrible practice; and it inherently means that the function does more than 1 thing
    - something if the flag is true, and something if it is false

    ### Dyadic functions

* dyadic function = function with 2 arguments
* times where dyadic functions are appropriate
    - things like functions to make Cartesian points
* problems with dyadic functions
    - there often isn't a natural ordering for the arguments, so you need to learn the order
        + `assertEquals(expected, actual)`

        ### Triads

* triad: function w/ 3 arguments
* don't do it

### Argument Objects

* if 2-3+ arguments are necessary, it could be that you need to wrap some arguments in an object
    - consider: `Circle makeCircle(Point center, double radius)` rather than `Circle makeCircle(double x, double y, double radius)`

    ### Verbs and Keywords

* monadic functions & their arguments should be a verb/noun pair:
    - `write(name)` or `writeField(name)` is good
* make reminders of argument orders in function names can be helpful
    - `assertExpectedEqualsActual(expected, actual)` is better than `assertEquals(...)`

    ### Have No Side Effects

* be wary of whether a function is doing things that you haven't described it as doing
    - (eg. opening access to edit the data for future functions, when you're only trying to do a simple compare value)

    ### Output Arguments

* If your function has to change the state of something, have it change the state of its owning object

### Command Query Separation

* functions should either do something (change state) or answer something (return information)

### Prefer Exceptions to Returning Error Codes

* If you use exceptions, rather than returned error codes, then the error processing code can be separated from the path code, and be simplified:

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

* You should extract the bodies of the try & catch blocks into functions of their own

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

* Returning error codes implies a class/enum w/ all the error codes defined
    - this means when you change the Error enum/class, you need to recompile and redeploy the classes that rely on them
        + use exceptions rather than error codes, because the new exceptions are derivatives of the exception class & can be added w/o forcing recompilation/redeployment

        ### Don't Repeat Yourself

        ### Structured Programming

* Dijkstra's rules of structured programming:
    - every function, & every block w/i a function, should have 1 entry & 1 exit;
        + so only 1 `return` statement, no `break`/`continue` statements in a loop, && no `goto` statements
* this doesn't really matter if your functions are small anyway; only thing that should be avoided is `goto` (b/c used in large functions)

### How Do You Write Functions Like This?

* create draft, then continuously test and refine it
