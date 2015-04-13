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
```Java
```

##### Object Composition
```Java
```

##### Class Design Guidelines
```Java
```

###### Cohesion

###### Consistency

###### Encapsulation

###### Clarity

###### Completeness

###### Instance vs. Static

##### Processing Primitive Data Type Values as Objects
```Java
```

##### Automatic Conversion between Primitive Types and Wrapper Class Types
```Java
```

##### Automatic Conversion between Primitive Types and Wrapper Class Types
```Java
```

##### The `BigInteger` and `BigDecimal` Classes
```Java
```
