# Chapter 4: Comments

* the proper use of comments is to compensate for failure to express yourself in code
    - their use isn't a cause for celebration
* Comments Do Not Make Up for Bad Code
* Explain Yourself in Code


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

* Mumbling
* Redundant Comments
* Misleading Comments
* Mandated Comments
* Journal Comments
* Don't Use a Comment When You Can Use a Function or a Variable
* Position Markers
* Closing Brace Comments
* Attributions and Bylines
* Commented-out Code
* Nonlocal Information
* Too Much Information
* Inobvious Connection
* Function Headers



# Chapter 5: Formatting


### Vertical Formatting

##### The Newspaper Metaphor

* topmost parts hold the high-level concepts & algorithms
* at the end, there should be low-level functions & details

##### Vertical Openness Between Concepts

* each line represents an expression/clause; & each group of lines repesents a complete thought
    - thoughts should be separated by a blank line

##### Vertical Density

Put code close together to imply close association

##### Vertical Distance

Concepts closely related should be kept vertically close to each other; and you should have them in the same file, too.

* variable declarations
    - as close to usage as possible
* instance variables
    - declared at the top of the class
    - used by many of the class's methods
* dependent functions
    - functions that call on another should be vertically close, w/ caller above the callee
* conceptual affinity
    - if you have a lot of functions that perform the same kind of use, then you should have less vertical distance between them
        + eg. a group of functions that assertTrue, but have different parameters


### Horizontal Formatting

* limit your line length to ~120 characters. Less is better.

##### Horizontal Openness and Density

* spaces around assignment operators imply that the two sides are different
* no space between a function name and its arguments implies that they're closely related
* use whitespace to accentuate the precedence of operators

```java
private static double determinant(double a, double b, double c) {
    return b*b - 4*a*c;
}
```


### Team Rules

A team of developers should agree on a single formatting style, & then every member should use that style.



# Chapter 6: Objects and Data Structures

* Hiding implementation is not just a matter of putting a layer of functions between the variables. Hiding implementation is about abstractions
  - Serious thought needs to be put into the best way to represent the data that an object contains. The worst option is to blithely add getters and setters.

* Objects hide their data behind abstractions and expose functions that operate on that data. Data structures expose their data and have no meaningful functions.

* the idea that everything is an object is a myth; sometimes you really do want simple data structures with procedures operating on them

* __The Law of Demeter__: modules shouldn't know the inner workings of the objects it manipulates, which means (in the context of C): a method _f_ of class _C_ should only call methods of these
  - _C_
  - an object created by _f_
  - an object passed as an arg to _f_
  - an object held in instance variable of _C_

* therefore, by this law, the following is a violation:
  ```c
  final String outputDir = ctxt.getOptions().getScratchDir().getAbsolutePath();
  ```

* instead of chaining methods, separate them into separate lines, so you can name & write the type for their outputs
