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

## Chapter 6: Working classes

### Good class interfaces

* don't add public members that're inconsistent w/ the class's abstraction level

* consider both abstraction and cohesion together

* avoid putting private implementation details into a class's interface
  - eg. having a constructor w/ a 'name' parameter, and a private variable for name
    - it exposes that name is a String; instead, you could create an implementation class (eg. EmployeeImplementation) & have a pointer to the implementation

### Design and implementation issues

* be critical of classes containing more than 7 data members

* design & document for inheritance, or prohibit it (eg. in Java, declare a class `final`)

* be suspicious if a class only has 1 instance

* avoid deep inheritance trees (try 2-3 levels at most, but some suggest 7 +/- 2)

* if multiple classes share common data but not behavior, create a common object that those classes can contain

#### Constructors

* initialize all member data in constructors, if possible

* prefer deep copies to shallow copies until proven otherwise

* Class quality checklist (p. 194)

## Chapter 7: High-quality routines

### Valid reasons to create a routine

* reduce complexity
* introduce an immediate, understandable abstraction
* support subclassing by needing less new code to override a short routine
* hide sequences in which things happen; sequences don't depend on an ordering
* simplify complicated boolean tests
* improves performance since you can optimize the code in one place rather than everywhere
* small operations tend to turn into large one, as you check for edge cases

### Design at the routine level

* make sure routines have a single, complete job

### Good routine names

* describe everything the routine does, and if you end up with a silly/long name, it's a sign you need to rework

* avoid meaningless/vague/wishy-washy verbs (eg. 'handle', 'perform', 'output', 'process', 'dealWith')
  - unless 'handle' was used in error handling context

* to name a function, use a description of the return value (eg. currentColor)

* to name a procedure, use a strong verb followed by the object

* use opposites precisely, some common ones:
  - add/remove, increment/decrement, open/close, begin/end, insert/delete, show/hide
  - create/destroy, lock/unlock, source/target, first/last, min/max, start/stop
  - get/put, next/previous, up/down, get/set, old/new

* establish team-wide conventions for common operations

### How to use routine parameters

* put params in input-modify-output order
  - input-only first, input-and-output second, output-only third

* status/error variables last

* don't use params as working variables

### Macro routines and inline routines

* fully parenthesize macro expressions
* surround multiple-statement macros with curly braces
* name macros that expand to code like routines in case you need to replace them with routines

* High-quality routines checklist (p. 222)

## Chapter 8: Defensive programming (p. 224)

## Chapter 9: The pseudocode programming process (p. 252)
