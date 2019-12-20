# Object-Oriented Programming course

Notes from taking NYU's Object-Oriented Programming course.

## Week 1

* an instance method can't override or hide a static method, and vice-versa

## Week 2

### The JVM

* executes byte code w/ a combo of an interpreter and just-in-time complication to assembly
* Java is dynamically linked (reduces resource consumption and is more modular)
  * loads shared libraries as needed at runtime

### CPP compilation

* `#`: directives for the preprocessor, where `#pragma` is compiler-specific

## Week 3

### Design pattern: Iterator Pattern

An object provides a standard way to examine all elements of a collection.

### Design pattern: Chain of Responsibility (CoR)

* consists of command objects and processing objects, where each processing object contains logic defining the type of command objects it can handle
* processing pipelines can be made, so you can pass data through phases

### The Expression Problem

* given that you need to develop node types in an AST, and also their operations depending on these types (e.g. expression evaluation, and pretty printing)
  * you can easily develop one (the types, or the operations), but not both

### Design pattern: Visitor Pattern

* used so you can extend operations instead of reimplementing
* visitors implement operators for node classes
  * each node type implements an `accept()` method so they can be visited
* need double dispatching: dispatch calls to `visit()` method based on:
  * dynamic type of the visitor object
  * dynamic type of the node passed to the `visit()` method

## Week 4

### Java inheritance

* `Object` class implements:
  * `String toString()`
  * `int hashCode()`
  * `boolean equals(Object obj)`
  * `Class<?> getClass()`

### Static vs. dynamic typing

* **dynamic type:** the actual type, determined by the object to which a variable points to at runtime

### Virtual method dispatch

* `virtual` declared methods are called based on the dynamic type of the receiver object
  * non-`virtual` methods are called based on static type
  * all Java methods are `virtual` except for static and private methods
    * this makes them overridable
  * virtual methods looks for the most specific implementation of the method first (i.e. the furthest down the inheritance chain)

### Virtual method tables (vtables)

* use `vptr`s for directing method to dynamic-dispatch (virtual method)
* vtables are created by copying entries from its superclass's vtable and changing the pointers of overridden methods to point to the new implementations
* add slots for any new methods that aren't `private` or `static`

## Week 5

* pros & cons of inheritance:
  * `[+]` substitution principal
  * `[+]` easy to modify reused code
  * `[-]` exposes subclass to superclass's implementation, therefore breaking encapsulation
* pros & cons of composition:
  * `[+]` blackbox reuse since the details of contained objects aren't visible
  * `[-]` the resulting system tends to have more objects

## Week 6

### Arrays in Java and C++

* C++ arrays can be stack- or heap-allocated, whereas in Java, they're only objects on the heap
* in C, if the array has static storage duration (e.g. global variable, static class member), then the elements are 0-initialized, otherwise they may contain arbitrary values

## Week 7

* **parametric polymorphism** allows you to handle values identically without depending on their types
  * use generics, and then instantiate with the type of choice
* C++ templates
  * can be instantiated, unlike Java generics
  * support generic arrays
  * can parametrize other values as well as types
* compiler generates a copy of the code for each instantiated template

## Week 8

### Explicit memory management in C++

* Rule of 3: if you define one of these, you should write definitions for all of them in your class: [1] destructor, [2] copy constructor, [3] assignment operator

## Week 9

### Automatic memory management with smart pointers

* can declare classes/templates as friends so that you can access the friend's private fields

  * e.g.

    ```c++
    template<typename U>
    friend class Ptr;
    ```

### Traits

* **traits** are template classes whose sole responsibility is to carry info used by another object or algorithm, to determine its "policy"/"implementation details"
  * allows you to avoid dynamic dispatch overhead that you'd get with inheritance

### Dynamic casts in our translator

* get object's class type and call `isInstance` to check if it's a valid cast

## Week 10

### Java's reflection mechanism (`java.lang.reflect`)

* reflection lets the program get information about its own types at runtime, and use that information to manipulate objects
* cons of reflection:
  * performance overhead b/c reflection involves types the JVM can't dynamically resolve, so it can't provide all its optimizations
  * requires a runtime permission which isn't allowed in most security contexts (e.g. mobile app, applet) because you can modify access-level modifiers
* you can use it to get the Class object of a classname string (qualified name)

## Week 11

These are all structural design patterns until the facade pattern, and then they're behavioral patterns.

### Design pattern: Delegation Pattern

An object within the class gets functionality delegated to it (composition as a pattern).

* can use an interface to have multiple classes implement a method, and then swap out classes (delegate) instances

### Design pattern: Decorator Pattern

A decorator object has the same interface as the object it decorates, and keeps the object that it accepts in its constructor or as a private member variable.

### Design pattern: Adapter Pattern

Helps 2 incompatible interfaces work together.

### Design pattern: Facade Pattern

Uses wrapping, delegation, and composition to decouple components and provide a simplified interface to complex systems.

### Design pattern: Command Pattern

An object is used to encapsulate all information needed to perform an action or trigger an event at a later time.

* has 4 associated objects: [1] command, [2] receiver, [3] invoker, and [4] client

### Design pattern: Strategy Pattern

Enables an algorithm's behavior to be selected at runtime by designing a family of interchangeable algorithms.

### Design pattern: Singleton Pattern

Ensures that only 1 instance is created. Is a "creational pattern" (deals with object creational mechanisms).

## Overall final review

* can call static methods on an instance, so if a static method fits better for the parameters, then the call isn't dynamically dispatched