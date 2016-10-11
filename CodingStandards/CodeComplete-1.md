# Code Complete

## Chapter 3: Prerequisites

* generally 20-30% of a project's schedule is dedicated to requirements, architecture, and upfront-planning

* Requirements checklist (p. 42)

* Architecture specification / top-level design
  - data design

* Architecture checklist (p. 54)

* Major construction practices (p. 69)

## Chapter 5: Design in construction

* __extensibility__: measurement of the ease in which you can enhance a system w/o affecting its underlying structure

* __high fan-in__: having a number of classes using a given class
  - good systems make use of utility classes at lower system levels

* __stratification__: making it possible to see a complete view of software at several levels of abstraction

* a program's structure of classes and their relations should be an acyclic graph

* identify areas likely to change
  - separate items likely to change
  - isolate items that seem likely to change
  - use enums for error codes/status variables
  - make program thread-safe

#### Popular design patterns

* __Abstract Factory__: creating sets of related objects by specifying the kind of set, but not the kinds of each specific object

* __Adapter__: converts the interface of a class to a different interface

* __Bridge__: builds an interface & implementation in such a way that either can vary w/o the other varying

* __Composite__: object contains additional objects of its own type, so that client code can interact w/ the top level object & not concern itself w/ all the detailed objects

* __Decorator__: attaches responsibilities to an object dynamically, w/o creating specific subclasses for each possible configuration of responsibilities

* __Facade__: provides a consistent interface to code that wouldn't otherwise have one

* __Factory Method__: instantiates classes derived from a specific base class w/o needing to keep track of the individual derived classes anywhere, except for the Factory Method

* __Iterator__: a server object that provides access to each element in a set sequentially

* __Observer__: keeps multiple objects in sync w/ each other by making an object responsible for notifying the set of related objects about changes to any member of the set

* __Singleton__: provides global access to a class that has one and only one instance

* __Strategy__: defines a set of algorithms/behaviors that're dynamically interchangeable with each other

* __Template Method__: defines the structure of an algorithm but leaves some of the detailed implementation to subclasses

#### Other heuristics

* enforce __preconditions__ & __postconditions__

* choose the time that you bind values to variables carefully

* Design practices checklist (p. 122)

## Chapter 6: Working classes (p. 162)

## Chapter 7: High-quality routines (p. 198)

## Chapter 8: Defensive programming (p. 224)

## Chapter 9: The pseudocode programming process (p. 252)
