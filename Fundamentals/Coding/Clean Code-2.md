# Chapter 4: Comments

* the proper use of comments is to compensate for failure to express yourself in code
    - their use isn't a cause for celebration

###### Comments Do Not Make Up for Bad Code

###### Explain Yourself in Code


## Good Comments

##### Legal Comments
Reference to licenses, authorship, etc. statements

###### Informative Comments
eg. ones explaining RegExps

###### Explanation of Intent

###### Clarification
Making things more readable (eg. `a.compareTo(b) == -1`)... but be sure that it's correct!

###### Warning of Consequences

###### TODO Comments
This isn't an excuse to leave in bad code

###### Amplification


## Bad Comments

###### Mumbling
Commenting just because.

###### Redundant Comments

###### Misleading Comments

###### Mandated Comments

###### Journal Comments

###### Don't Use a Comment When You Can Use a Function or a Variable

###### Position Markers

###### Closing Brace Comments

###### Attributions and Bylines

###### Commented-out Code

###### Nonlocal Information

###### Too Much Information

###### Inobvious Connection

###### Function Headers



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
