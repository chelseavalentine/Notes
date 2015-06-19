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

### Constructors
