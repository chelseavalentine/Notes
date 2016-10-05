#### Objects and Classes

##### Definining Classes for Objects
* _object_ a real-life entity that can be distinctly identified
  * objects have properties/attributes, which are represented by data fields w/ their current values
    * eg. circle has attribute for radius
  * behavior/actions of object is defined by methods
    * eg. you can use getArea() for circle objects
* objects of the same type have the same class, & there can be many instances of a class
  * creating an instance = _instantiation_
* variables define data fields & methods to define actions
* a class has methods called _constructors_
  * constructors are designed to perform initializing actions (eg. initializing the data fields)
* you can put 2 classes in 1 file, but 1 class can be a public class

```Java
public class TestSimpleCircle {
}

class SimpleCircle {
}
```

##### Constructing Objects Using Constructors
`constructors`:
* must have the same name as the class itself
* they don't return a type, not even void
* are invoked using the `new` operator when an object is created (they initialize objects)

##### Accessing Objects via Reference Variables
* object's data & methods can be accessed through the `.` operator via the object's reference variable

###### Reference Variables and Reference Types
* object's _reference variables_  reference the object; syntax:
```Java
ClassName objectRefVar;
```
* _reference type_: a class
```Java
Circle myCircle = new Circle(); //myCircle is declared to be of Circle type, then an object is created & referenced to myCircle;
```

###### Accessing an Object's Data and Methods
* the `.` dot operator that invokes methods is also called the _object member access operator_
 * `objectRefVar.dataField` references data field in object
 * `objectRefVar.method(arguments)`invokes method

###### Reference Data Fields & the `null` Value
uninitialized data fields hold the value `null`
```Java
class Student {
 String name; //default value null
 int age; //default value 0
 boolean isScienceMajor; //default value false
 char gender; //gender has default value '\u0000'
}
```

###### Differences between Variables of Primitive Types and Reference Types
* When you create a variable, the value is different based on type
 * _primitive type:_ the value is of the primitive type
 * _reference type:_ the value is a reference to the object's memory location

##### The `Random` Class
* when you make a Random object, you need to specify a seed/use the default seed
 * seed: a number used to initialize a pseudorandom number generator
```Java
Random random1 = new Random(3);
System.out.print("From random1: ");
for (int i = 0; i < 10; i++)
 System.out.print(random1.nextInt(1000) + " ");
 
Random random2 = new Random(3);
System.out.print("From random2: ");
for (int i = 0; i < 10; i++)
 System.out.print(random1.nextInt(1000) + " ");
```

Both random1 & random2 have the same sequence of random `int` values


##### Static Variables, Constants, and Methods
* static variables shhared by all objects of the class
* static methods can't access instance members of the class

Declaring static variables methods, and constants:
```Java
static int numberOfObjects;

static innt getNumberObjects() {
 return numberOfObjects;
}

final static double PI = 3.14;
```

##### Visiblity Modifiers
* `public` modifier allows the classes/methods/data fields to be accessed from other classes
* If you don't use a modifier, the default is that: classes, methods, and data fields are accessible by any class in the same package (called _package-private_ or _package-access_)
* can always access data fields & methods from inside the class
```Java
public class C {
 private boolean x;
 
 public static void main(String[] args) {
  C c new C();
  System.out.println(c.x);
  System.out.println(c.convert());
 }
 
 private int convert() {
  return x ? 1 : -1;
 }
}
```

##### Data Field Encapsulation
* making data fields private protects data & makes the class easy to maintain
* _data field encapsulation_: declaring data fields private to prevent direct modifications

Methods have naming conventions to explain their purpose
* `get` methods
```Java
public returnType getPropertyName()
```
* `boolean` methods
```Java
public boolean isPropertyName()
```
* `set` methods
```Java
public void setPropertyName (dataType propertyValue)
```

##### Passing Objects to Methods
* passing an object to a method is to pass the reference of the object

##### Array of Objects
* arrays can hold objects as well as primitive type values
 * eg. `Circle[] circleArray = new Circle[10];`
 * you need to use a for-loop to initialize the array
 * each element of the array references an object
