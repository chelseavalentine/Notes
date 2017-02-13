# Chapter 1: The Fundamentals of Programming

* primitives: `byte`, `short`, `int`, `long`, `float`, `double`, `char`, `boolean`


# Chapter 2: Beginning Java

### The Compiler and the JVM

Java compiler compiles the source code (checks for errors & converts to machine code). Java Virtual Machine (JVM) executes the code. The advantage of the JVM? You can run the Java code on any operating system, because it's a virtual machine.


### Objects

* _instance variables_: an object created from a class is an instance of it, and has its own copy of variables to describe the object's state 
* classes describe an object's state & behavior, but aren't objects themselves
    - objects are intdependent


#### Hiding our variables

`private` makes it so that other classes can't directly access variables


Side notes:

* You can use `String` even if a method asks for a `CharSequence` because __polymorphism__ (ability of an object to take many forms) applies.


#### Random

```java
import java.util.Random;

public class DiceMaker {
    public static void maid (String[] args) {
        Random r = new Random();
        int randomNumber = r.nextInt(6); // 0, 1, 2, 3, 4, 5
    }
}
```


#### ArrayList

```java
import java.util.ArrayList;

...
ArrayList names = new ArrayList();
names.add("name");
names.get(0);
names.size();

// limit arraylists to only hold one type
ArrayList<String> names = new ArrayList<String>();
...
```


#### Using ArrayLists with Primitives

Can't directly insert primitives into ArrayLists
```java
// can do this instead
ArrayList<Integer> numbers = new ArrayList<Integer>();
```



# Chapter 3: Designing Better Objects

### Interface

Interface: has abstract methods, without method bodies, that tell what the 'Human' category, or whatever else it could be, can do.
```java
public interface Human {
    public void eat();

    public void walk();

    public void sleep(int duration);
}
```

Extend the interface:

```java
public class King implements Human {
    public void eat() {
        System.out.println("The King eats.");
    }

    public void walk() {
        //
    }

    public void sleep(int duration) {
        //
    }

    public void rule() {
        //
    }
}
```

If you have an object implement an interface, it must implement every abstract method in the interface.


### Polymorphism

If you have a method like

```java
pubic void feed(Human h) {
    //
}
```

You can even pass in a King, or any other object of category 'Human', and it will work. Polymorphism allows you do utilize a commn method with multiple object types.


### Inheritance

Using inheritance over interface allows you to reuse code. Inheritance lets similar classes share methods & variables.

```java
public class Cat extends Animal {
    //
}
```


### Graphics

Use `javax.swing` package to create GUIs



# Chapter 4: Laying the Foundations

### Learning to build games

Three components of a game:

* Game Development Framework
    - game-independent classes that performs generic tasks like implementing a game screen & handling player input
* Game-specific classes
    - classes representing characters, power-ups, levels, etc.
* Resources
    - images & sound files

Game will have the following classes:

* Main classes
    - GameMain
        + starting point of the game; has the main method
    - Game
        + has the game loop, & methods to start & exit the game
    - Resources
        + allows you to quickly load images & sound files
* State classes
    - State
        + each state represents a screen in the game; this class is the blueprint fr other states (through inheritance)
    - LoadState
        + the initial state of the game, where resources are loaded
    - MenuState
        + welcome screen w/ navigation
* Utility classes
    - InputHandler
        + listens for user mouse & keyboard events, and dispatches the state classes to handle the events
