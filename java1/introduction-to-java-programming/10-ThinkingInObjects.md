#### 10 | Thinking in Objects

##### Immutable Objects and Classes

* immutable classes --> immutable objects
	* eg. `String` class
* contents of immutable objects are ... immutable
* immutable classes properties:
	* all data fields must be private & can't have any `set` methods as `public`

In order to create an immutable class, meet these criteria:

* all data fields must be private
* there can't be any mutator methods for data fields
	* mutator method: a method that changes a variable; known as `set`ter method

##### The Scope of Variables

* (talking about scope in terms of class, not loops)
* scope of instance & static variables: the entire class
	* this is the class regardless of where the variables are declared
* you can put a class's variables & methods in any order
	* exception: when a data field is initialized based on a reference to another data field
		* then the other data field needs to be declared first
		* it's good practice to declare data fields @ beginning of a class
	* can declare a class's variable many times, but can use the same variable name in a method in different nonnesting blocks
	* __hidden variable__: a local variable w/ the same name as a class' variable takes precendence, & the same-named class variable is _hidden_ (example below)

```Java
public class F {
	private int x = 0; //Instance variable
	private int y = 0;

	public F() {
	}

	public void p() {
		int x = 1; //Local variable
		System.out.println("x = " + x); //This x is x = 1, rather than x = 0;
		System.out.println("y = " + y); //This is y = 0 because y wasn't declared locally
	}
}
```


##### The `this` Reference

* `this` refers to the object itself; it can be used inside a constructor to invoke another constructor of the same class... yeah
	* usually, `this` reference is omitted; but you need the `this` reference to reference hidden data fields or invoke an overloaded constructor

eg: 

```Java
public class Circle {
	private double radius;

	...

	public double getArea() {
		return this.radius * this.radius * Math.PI;
	}

	public String toString() {
		return "radius: " + this.radius + "area: " + this.getArea();
	}
}
```


##### Using `this` to Reference Hidden Data Fields

* hidden data field: a data field name is used as a parameter in a `set` method --> the data field is hidden in the `set` method
	* in this case, you need to use `this` in order to access the data field

```Java
public class F {
	private int i = 5;
	private static double k = 0;

	public void setI(int i) {
		this.i = i;
	}

	public static void setK(double k) {
		F.k = k; //the value passed to the function is set as the value of k of this class, F
	}
}
```


##### Using `this` to Invoke a Constructor

* use `this` to invoke a constructor of the same class, like: 

```Java
public class Circle() {
	private double radius;

	public Circle(double radius) {
		this.radius = radius;
	}

	public Circle() {
		this(1.0);
	}
}
```


##### Class Abstraction and Encapsulation

* __class abstraction__: the separation of class implementation from the use of a class
* __class encapsulation__: hiding the details of a class' implementation

More about class abstraction:

* class's contract: the instructions the developer writes to tell the user how functions are used
* a class is known as an _abstract data type_ (ADT) b/c you can find & compute things of a class without knowing how they're actually computed


##### Object-Oriented Thinking

* in OOT, the focus is on designing methods; you use methods & data together in objects
* procedural paradigm: focus on designing methods; (data & methods are separate)
* OO paradigm: data & methods are coupled into objects


##### Object Composition

* objects can create another object
	* __composition__: the relationship between the two objects
	* composition is a case of the aggregation relationship
* __aggregation__ models _has-a_ relationships & shows ownership relationships between 2 objects
	* __aggregating object__: the ower object
		* may be owned by several other aggregating objects
		* if an object is owned by only 1 aggregating object, the relationship b/t them is called a __composition__
	* __aggregating class__: the aggregating object's class
		* aggregated class vs aggregating class
* __multiplicity__: a number/interval specifying how many of the class's objects are involved in the relationship; (* = unlimited)
	* each class involved in a relationship may specify a multiplicity


##### Class Design Guidelines

###### Cohesion

* classes describe a single entity & its class operations work toward a coherent purpose


###### Consistency

* place data declarations before the constructor
* place constructors before methods
* consistantly provide a public no-arg constructor to construct a default instance
	* you should document the reason for not including one if you decide not to include one
	* if no constructors are explicitly defined, a public default no-arg constructor w/ an empty body is assumed

###### Encapsulation

* __encapsulating data fields__: class should use `private` modifier to hide data from direct access by clients

###### Clarity

* design classes that allow the user to freely use it however they want


###### Instance vs. Static

* always reference static variables & methods from a class name (Not a reference variable) to improve readibility & avoid errors
* use `set` methods to change static data fields
* a constructor is always instance b/c it's used to create a specific instance
	* a static variable/method can be invoked from an instance method, but an instance variable/method can't be invoked from a static method


##### Processing Primitive Data Type Values as Objects

* can wrap primitive data types in their respective wrapper objects
	* eg `int` in `Integer`, `double` in `Double`, `char` in `Character`;
* wrapper classes don't have no-arg constructors
	* thus, once the objects are created, internal values can't be changed
* conversion methods, & other common methods are on p.395 


##### Automatic Conversion between Primitive Types and Wrapper Class Types

* primitive type values can be converted to object using a wrapper, & vice versa
* __boxing__: converting a primitive value to a wrapper object
* __unboxing__: converting a wrapper object to a primitive type
* __autoboxing__ & __autounboxing__: the Java compiler automatically boxes a primitive value that appears in a context requiring an object & unboxes an object that appears in a context requiring a primitive value


##### The `BigInteger` and `BigDecimal` Classes

* these are used to represent huge integers and decimal numbers... who would've known
* they are both immutable!!! Immutable. __Immutable__.
* can use `new BigInteger(String)` & `new BigDecimal(String)` to create instances of BigInteger & BigDecimal to use `add`, `subtract`, `multiple`, `divide`, & `remainder` methods on, and `compareTo`

