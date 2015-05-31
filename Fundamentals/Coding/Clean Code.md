# Chapter 1: Clean Code

* code is the detailed specification of requirements

### The Total Cost of Owning a Mess

* "As the mess builds, the productivity of the team continues to decrease, asymptotically approaching zero."
* "We bemoan the schedules that were too tight to do things right."
* "You will _not_ make the deadline by making the mess. The mess will slow you down instantly, and force you to miss the deadline. The _only_ way to make the deadline--the only way to go fast--is to keep the code as clean as possible at all times."

### We are Authors

* "We are authors. And one thing about authors is that they have readers. Authors are responsible for communicating well with their readers. The net time you write a line of code, remember you are an author, writing for readers who will judge your effort."


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

* p.68

### Switch statements

