#### Methods

##### Defining a Method
Syntax for method definition:
```Java
modifier returnValueType methodName (list of parameters with their types) {
  //Method body
}

//For instance:
public static void Example (int [] list){
  //Method body
}
```

* You can have methods with the same name as long as the arguments you feed into it have different types.
* Call a method by typing `method(argument1, argument2)`

##### `void` Method Example
* `void` methods don't return any values... it sends whatever you do within the function to the abyss once the function has terminated

##### Passing Parameters by Values
* Arguments must match the parameters in _order_, _number_, and _compatible type_.
* After you invoke a method w/ an argument, the values of the argument are passed to the method

##### Modularizing Code
* Use methods to modularize code to allow you to reuse stuff and manage stuff

##### Overloading Methods
* overloading methods lets you use the same name for methods, as long as their signatures are different (the parameters it takes in)

##### The Scope of Variables
* _scope of a variable_ the part of the program where the variable can be referenced
  * an example lies in `for` loops; any variables declared in there are local variables, because they only function within their loops

##### The `Math` Class
* `pow(a, b)` = a^b
* `sin([some value in radians])`, `cos([some value in radians])`, `tan([some value in radians])`, `toDegrees([some value in radians])`
* `toRadians([degrees])`, `asin([proportion])`, `acos([proportion])`, `atan([proportion])` 

##### Exponent Methods
* `exp([double x])` = returns e raised to the power of x
* `log([double x])` = returns the ln of x ( ln(x) = LOGe(X) )
* `log10([double x])` = returns the base 10 log of x ( LOG10(x) )
* `pow([double a], [double b])` = a^b
* `sqrt([double x])` = returns sqrt of x for x >= 0

##### The Rounding Methods
* `ceil([double x])` = rounds x to the nearest integer, returned as a double value
* `floor([double x])` = x is rounded down to nearest integer, returned as a double value
* `rint([double x])` = x rounded to nearest integer; if x is equally close to 2 integers, the even one is returned as a double 

##### The `min`, `max`, and `abs` Methods
* `min` & `max` return the minimum, or maximum, number of __two numbers__
 
##### The `random` Method
* `(Math.random() * scaleDatWithNumberHere)`

Example usage
```Java
(int)(Math.random() * 10) //Returns a random integer between 0 & 9
```
