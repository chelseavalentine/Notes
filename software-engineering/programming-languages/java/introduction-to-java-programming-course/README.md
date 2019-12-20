# Introduction to Java Programming

## Key things

So sometimes I forget how to run a java program...

### Running programs

First compile it.

`javac ClassName.java`

Then run it.

`java ClassName`

## Introduction to Computers, Programs, and Java

##### Basics

```Java
public class ClassName{
  public static void main (String[] args) {
    [Main program goes here]
  }
}
```

- The program starts executing from the *main* method. There can be several methods.

##### Message dialog boxes

```Java
import javax.swing.JOptionPane; //import javax.swing.*; works too

public class WelcomeInMessageDialogBox {
  public static void main (String[] args) {
    //Display 'TEXT' in a message dialog box
    JOptionPane.showMessageDialog ( null, "TEXT");
  }
}
```

- It's standard to use null as the first argument, and then your text as the second.

##### Avoid common errors

- If you don't make 7 in 7/21 into 7.0/21, Java will interpret it as an integer division and give you an incorrect result (if you were intending to do regular division).

## 2 | Elementary Programming

##### Basics

Read user input

```Java
import java.util.Scanner;

public class Scanner{
  public static void main (String[] args) {
    Scanner in = new Scanner (System.in); //This creates a new scanner called 'in'
    
    in.close(); //It's good practice to close off user input at the end of your program!
  }
}
```

When using the scanner that you created, 'in', there are some common methods that you can use:

- ```in.next()``` = reads a string that ends before a whitespace character
- ```in.nextLine()``` = reads a line of text (ends when the Enter key is pressed)
- ```in.nextDouble()``` = reads an number of the _double_ type
- ```in.nextInt()``` = reads an integer of the _int_ type
- ```in.nextFloat()``` = reads a number of the _float_ type
- ```in.nextLong()``` = reads an integer of the _long_ type
- ```in.nextShort()``` = reads an integer of the _short_ type
- ```in.nextByte()``` = reads an integer of the _byte_ type

##### Print statements

Print with a line break after the statement

```Java
public class PrintBreak{
  public static void main (String[] args) {
    System.out.println("Wow this will have a line break after the statement.");
  }
}
```

Print without a line break after the statement

- You can also do the same thing by typing \n within the " " in your print statement

```Java
public class PrintNoBreak{
  public static void main (String[] args) {
    System.out.print("Wow this will not have a line break after the statement,"
    + "but you could make some like \n\n\n");
  }
}
```

Print a formatted output

```Java
public class PrintFormat{
  public static void main (String[] args) {
    System.out.printf("I don't really know how to do this properly yet, but once I do, I'll update this...");
  }
}
```

##### Variables

You must declare and initialize variables before you can use them!

```Java
int variable1; //Declare one at a time
variable1 = 3; //Initialize it in a separate step
int yo = 4; //Declare a variable and initialize it in one single step.

int variable2, variable3, variable4; //Or do multiple in one go
variable2 = variable3 = variable4 = 0; //Initialize multiple variables, to have the same value, in one step.

int variable5 = 0, variable6 = 1; //Declare and initialize variables of the same type in one step.
```

There are multiple variable types at your disposal:

- ```int```
- ```double```
- ```byte```
- ```short```
- ```long```
- ```float```
- ```char```
- ```boolean```

Just know that these variable types vary in storage size. Depending on which variable you use, an amount of memory will be allocated.

Going from smallest to largest storage size: ```byte```, ```short```, ```int```, ```long```, ```float```, & ```double```

Note that ```=``` is the *assignment operator*.

##### Named constants

Let's say you want to keep a fixed number and use it throughout your program. How would you go about it? You'd create a named constant.

Its format works like this:

```Java
public class NamedConstants{
  public static void main (String[] args) {
    final datatype CONSTANTNAME = value; //declare the constant value
    
    int useThatValue = CONSTANTNAME; //use the constant value
  }
}
```

##### Numeric operators

- ```+``` Addition
- ```-``` Subtraction
- ```*``` Multiplication
- ```/``` Division
- ```%``` Remainder

###### Exponent operations

Did you notice that there isn't a ```**``` for exponent operations, like there is in Python? If you want to represent an exponent, do the following:

```Java
public class Exponent{
  public static void main (String[] args) {
    double exponent = Math.pow(2, 3); //Raise 2 to the 3rd power to get 8.0
    int exponent2 = Math.pow(2.5, 2); //Square 2.5 to get 6.25
  }
}
```

##### Numeric literals

A *literal* is a constant value that appears directly in the program.

eg. ```int soLiteral = 3;```, where 3 is the literal

###### Integer Literals

If your integer literal is super long, use the *long* type. How? Put ```L``` at the end of the super long number.

eg. ```int reallyLiteral = 4182491781212L;```

###### Floating-point literals

Weirdly enough, floating-point literals are declared as doubles, not floats. Make a number a float or double by adding ```F``` or ```D``` at the end of the number.

eg. ```System.out.println( 1.0F / 3.0F);``` gives you 0.333333334 whereas you'd get 0.3333333333 without the F

##### Scientific notation

Write ```1E2``` to get 1 x 10^2

##### Augmented assignment operators

- ```+=``` = Addition assignment
- ```-=``` = Subtraction assignment
- ```*=``` = Multiplication assignment
- ```/=``` = Division assignment
- ```%=``` = Remainder assignment

###### Increment and decrement operators

```Java
int increment = 0;
increment++; //Use increment's value, then add 1
++increment; //Add 1 to increment's value, then use it

int decrement = 0;
decrement--; //Use decrement's value, then subtract 1
--decrement;  //Subtract 1 from decrement's value, then use it
```

##### Numeric type conversions

Convert floating-point numbers to integers, and vice versa, using explicit casting

```Java
System.out.println( (int) 1.7 );

System.out.println( (double) 1 / 2 ); // if you don't do this, the answer will display as 0
```

##### Character data type and operations

A ```character``` data type represents a single character.

```Java
char letter = 'A';
char number = '1';
```

##### Casting between char and numeric types

You can convert characters into any numeric type & vice versa, but you need to keep in mind that only the lower 16 data bits are used when an integer is casted into a character.

```Java
char ch = (char)0XAB0041; // lower 16 bits hexcode is 0041, so that is assigned to ch
System.out.println(ch); // displays A
```

- Floating-point values --> char
  - Procedure: floating-point becomes an integer, then a char.

```Java
char ch = (char)55.05; // 55 is assigned to ch
System.out.println(ch); // displays A
```

- char --> int

```Java
int i = (int)'B';
System.out.println(i); //this prints out B's Unicode
```

You can use implicit casting (```type variable = '[letter]'```) if the result of the casting fits into the range of the type.
For example, in ```byte b = 'a';```, a's unicode is 97 & that fits w/i a byte. That wouldn't be the case if you were doing ```byte b = '\uFFF4'```, though. It'd be too big.

To force an assignment, cast the type like this: ```byte b = (byte) '\uFFF4';```

###### Escape characters

To get out of how your code would regularly be interpreted, you can use escape characters.

```Java
System.out.println("Escape your speech \"to say something\"!"); \ \" together represent one character
```

Other escape characters work as follows:

- ```\b``` = Backspace. Unicode: \u0008. Decimal value: 8.
- ```\t``` = Tab. Unciode: \u0009. Decimal value: 9.
- ```\n``` = Linefeed. Unicode: \u000A. Decimal value: 10.
- ```\f``` = Formfeed. Unicode: \u000C. Decimal value: 12.
- ```\r``` = Carriage Return. Unicode: \u000D. Decimal value: 13.
- ```\\``` = Backslash. Unicode: \u005C. Decimal value: 92.
- ```\"``` = Double Quote. Unicode \u0022. Decimal value: 34.

##### The String type

*String* = sequence of characters.

You can concatenate strings with ```+``` and ```+=```.

*Whitespace characters* include ```' '```, ```\```, ```\f```, ```\r```, or ```\n```.

Caution:

```Java
System.out.println("Food " + 1 + 2); //produces: Food 12
System.out.println("Food " + (1 + 2)); //produces: Food 3
```

##### Getting input from input dialogs

If you prefer a graphical dialog box instead of one in the console, you can do this:

```Java
import JOptionPane.*;

public class GraphicalInput{
  public static void main (String[] args) {
    String answer = JOptionPane.showInputDialog(x); //Where x is the string that creates the message
  }
}
```

##### Converting strings to numbers

Convert a string to an int

```Java
int intValue = Integer.parseInt (123456);
```

Convert a string into a double

```java
double doubleValue = Double.parseDouble (123456)
```

## 3 | Selections

##### ```boolean``` data type

Two values: ```true``` or ```false```

```Java
boolean stayAwake = true;
```

###### Comparison operators

- ```<``` = less than
- ```<=``` = less than or equal to
- ```>``` = greater than
- ```>=``` = greater than or equal to
- ```==``` = equal to
- ```!=``` = not equal to

##### If statements

```Java
//You could just have a one-way if statement
if (CONDITION == IS MET) {
  [execute this code];
}

//Or you could do a two-way if statement like this (if-else)
if (CONDITION == IS MET) {
  [execute that code];
}
else{
  [execute that code];
}

//And you could even do a multi-way if-else statement like this
if (CONDITION == IS MET){
  [execute those code];
}

else if (CONDITION2 == IS MET){
  [execute these code];
}

else {
  [execute them code];
}
```

###### Getting rid of redundancies 

```Java
//You don't need to do this
if (variable == true) {
  [some code];
}

//When you could do this
if (variable){
  [some code];
}
```

##### Generating random numbers

```Math.random();``` returns a "random" number between 0.0 & 1.0, excluding 1.0.

What if the number you want is 1.0 or bigger?

```Java
//For instance, this will generate a random number from 0 to 9
int var1 = (int)(Math.random() * 10); 

//This is the same as
int var2 = Math.floor( Math.random() * 10 );
```

Using the random method in conjunction with other Math methods is great.

- ```Math.cos()```, ```Math.sin()```, ```Math.tan()```, ```Math.asin()```, ```Math.acos()```, ```Math.atan()```
- ```Math.toRadians()```, ```Math.toDegrees()```
- ```Math.exp()```, ```Math.log()```, ```Math.log10()```
- ```Math.pow()```, ```Math.sqrt()```
- ```Math.ceil()```, ```Math.floor()```, ```Math.round()```
- ```Math.min()```, ```Math.max()```
- ```Math.abs()```

##### Logical operators

Boolean operators:

- ```!``` = not (logical negation)
- ```&&``` = and (logical conjunction)
- ```||``` = or (logical disjunction)
- ```^``` = exclusive or (logical exclusion)
  - The exclusive or, ```^```, of 2 Boolean operands is true if & only if the two operands have different Boolean values.

##### ```switch``` Statements

If there're multiple cases that you can foresee (due to you giving options, or w/e), then you can use switch statements to make your life easier.

```Java
public class Switchie{
  public static void main (String[] args) {
    switch (expression) {
      case answer1: [Do this]; break;
      case answer2: [Do this]; break
    }
  }
}
```

So for instance, you want the user to pick 1 of 3, and depending on which card they pick, your program does something. Part of your program would look like this:

```Java
int card = 0;
[get user input]

switch (card){
  case 11: System.out.println("You chose card 11."); break;
  case 13: System.out.println("You chose card 13."); break;
  case 17: System.out.println("You chose card 17."); break;
}
```

##### Conditional expressions

Conditional expressions are weird as hell. Let's say you want to assign a number to ```y``` depending on the value of ```x```. You type this statement:

```Java
int x = 50; 
int y = (x > 0) ? 1 : -1;
```

This is essentially saying, if x is greater than 0, then assign y to 1 (```y = 1```). Otherwise, assign y to -1 (```y = -1```).

The general format for conditional expressions is as follows:
```boolean-expression ? expression1 : expression2;```

###### Formatted print statements

Format specifiers:

- ```%b``` a boolean
- ```%c``` a character
- ```%d``` a decimal integer
- ```%f``` a floating-point number
- ```%e``` a number in standard scientific notation
- ```%s``` a string

So let's say you have a print statement, and you want to format it, because you happen to be using printf, you'd use:

```Java
System.out.printf ("Hello, that will be $%10.2d for those %15s", cost, item); 
```

###### Justifying your formatted print statements

- ```%-10d``` will left-justify

##### Confirmation dialogs

```Java
import JOptionPane.*;
...
int option = JOptionPane.showConfirmDialog (null, "[TEXT TO USER]");
```

## 4 | Loops

##### The ```while``` loop

Do something while the statement is true

```Java
while (x == 10) {
  [Your code that will be executed while x is equal to 10];
}
```

##### The `do-while` loop

If you want to run the code once before checking the condition (to decide whether to continue), you can use this

```Java
do {
  [this code];
} while (loop-continuation-condition);
```

##### The `for` loop

```Java
for (int i = initialValue; i < endValue; i++){
  [Code to be executed];
}
```

If there's only one statement in the loop body, you can omit the braces `{}`

The following are equivalent:

```Java
for ( ; ; ) {
  [Code to be executed];
}

for ( ; true; ){
  [Code to be executed];
}

//The best one to use
while (true){
  [Code to be executed];
}
```

##### The `break` and `continue` keywords

- `break` will exit the loop that it's in
- `continue` will end the current iteration & go to the next one

##### Sentinel loop

These keep doing something until the value is changed to 0. (eg. you tell a user to type '0' when they're done.

##### Input and output redirections

- input redirection: uses data from another file 
  - type this into the command line ```java SentinelValue < input.txt```
- output redirection: sends the output to a file rather than displaying it on the console
  - type this into the command line ```java ClassName > output.txt```

##### Controlling a loop with a confirmation dialog

```Java
import JOptionPane.*;

public class ContinueConfirmation{
  public static void main (String[] args) {
    int option = JOptionPane.YES_OPTION;
    while (option == JOptionPane.YES_OPTION){
      //do this
      option = JOptionPane.showConfirmationDialog (null, "Continue?")
    }
  }
}
```

## 5 | Methods

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

- You can have methods with the same name as long as the arguments you feed into it have different types.
- Call a method by typing `method(argument1, argument2)`

##### `void` Method Example

- `void` methods don't return any values... it sends whatever you do within the function to the abyss once the function has terminated

##### Passing Parameters by Values

- Arguments must match the parameters in _order_, _number_, and _compatible type_.
- After you invoke a method w/ an argument, the values of the argument are passed to the method

##### Modularizing Code

- Use methods to modularize code to allow you to reuse stuff and manage stuff

##### Overloading Methods

- overloading methods lets you use the same name for methods, as long as their signatures are different (the parameters it takes in)

##### The Scope of Variables

- _scope of a variable_ the part of the program where the variable can be referenced
  - an example lies in `for` loops; any variables declared in there are local variables, because they only function within their loops

##### The `Math` Class

- `pow(a, b)` = a^b
- `sin([some value in radians])`, `cos([some value in radians])`, `tan([some value in radians])`, `toDegrees([some value in radians])`
- `toRadians([degrees])`, `asin([proportion])`, `acos([proportion])`, `atan([proportion])` 

##### Exponent Methods

- `exp([double x])` = returns e raised to the power of x
- `log([double x])` = returns the ln of x ( ln(x) = LOGe(X) )
- `log10([double x])` = returns the base 10 log of x ( LOG10(x) )
- `pow([double a], [double b])` = a^b
- `sqrt([double x])` = returns sqrt of x for x >= 0

##### The Rounding Methods

- `ceil([double x])` = rounds x to the nearest integer, returned as a double value
- `floor([double x])` = x is rounded down to nearest integer, returned as a double value
- `rint([double x])` = x rounded to nearest integer; if x is equally close to 2 integers, the even one is returned as a double 

##### The `min`, `max`, and `abs` Methods

- `min` & `max` return the minimum, or maximum, number of __two numbers__

##### The `random` Method

- `(Math.random() * scaleDatWithNumberHere)`

Example usage

```Java
(int)(Math.random() * 10) //Returns a random integer between 0 & 9
```

## 6 | Single-Dimensional Arrays

##### Array Basics

- an array's size is fixed once it's created
- access arrays via its index `array[0]`

##### Declaring Array Variables

```Java
//Basic declaration is like this
elementType[] arrayVariableName;

//For instance
double[] myList;
```

##### Creating Arrays

- declaration of array variables don't allocate any memory space for the array; only a storage allocation
- but once an array has been declared, you can do this:

```Java
arrayVariableName = new elementType[arraySize];
```

- You can declare a variable & give it a size at the same time

```Java
//Way 1
elementType[] arrayRefVar = new elementType[arraySize];

//Way 2
elementType arrayRefVar[] = new elementType[arraySize];

//eg.
double[] myList = new double[10];
```

##### Array Size and Default Values

- get access to the size of an array variable by doing `arrayVariable.length`

##### Array initializers

- You can create an array with its values set, but remember that you can't extend the size:

```Java
elementType[] arrayVariable = {value0, value1, value2, ..., valuek};
```

##### Processing Arrays

- Since you know the size of an array by using `.length`, you should use `for` loops to process them:

```Java
for (int i = 0; i < list.length; i++){
  //Do this
}
```

##### for-each Loops

you can iterate through an array by doing this, for example:

```Java
for (double u: myList) {
  System.out.println(u); //This prints all of the stuffs in the array
}
```

##### Copying Arrays

- Doing `list1 = list2` will copy the reference values from list1 to list2, it won't actually copy the contents of the array

- Ways to actually copy arrays

  - Loop through the array, copying elements one by one

  - Use the static `arraycopy` method in the `System` class

    ```Java
    System.arraycopy(sourceArray, src_position, targetArray, tar_position, length);
    
    //example
    System.arraycopy(sourceArray, 0, targetArray, 0, sourceArray.length);
    ```

##### Passing Arrays to Methods

- arrays are passed by sharing to methods, so you can see a change in the array outside of the method
- it's called __pass-by-sharing__: the array in the method is the same as the array being passed; the reference value is passed to the method, not just the value

##### Returning an Array from a Method

```Java
public static int[] reverse (int[] list) {
  int[] result = new int[list.length];
  
  for (int i = 0; j = result.length - 1; i < list.lngth; i++, j--) {
    result[j] = list[i];
  }
  
  return result;
}
```

##### Variable-Length Argument Lists

You can treat multiple variables as an array????

```Java
public class VarArgsDemo {
  public static void main(String[] args) {
    printMax(34, 3, 3, 2, 56.5);
    printMax(new double[]{1, 2, 3});
  }
  
  public static void printMax(double... numbers) {
    if (numbers.length == 0) {
      System.out.println("No argument passed");
    }
    
    return;
  }
  
  double result = numbers[0];
  
  for (int i = 1; i < numbers.length; i++)
    if (numbers[i] > result)
      result = numbers[i];
      
  System.out.println("The max value is " + result);
}
```

##### Searching Arrays

###### The Linear Search Approach

elements are compared to a key; if a match is found, the index of the element is returned; otherwise, -1 is returned

```Java
public class LinearSearch {
  /** Method for finding a key in the list */
  public static int linearSearch(int[] list, int key) {
    for (int i = 0; i < list.length; i++) {
      if (key == list[i])
        return i;
    }
    
    return -1;
  }
}
```

###### The Binary Search Approach

- elements of the list must be ordered already for binary search to work
- this is how it works... if this even makes sense
  - If the key is less than the middle element, you continue to search for the key in only the first half of the array
  - If the key is equal to the middle element, the search ends with a match
  - If the key is greater than the middle element, you need to continue to search for the key only in the second half of the array

Version 1

```Java
public static int binarySearch(int[] list, int key) {
  int low = 0;
  int high = list.length - 1;
  
  int mid = (low + high) / 2;
  if (key < list[mid])
    high = mid - 1;
  else if (key == list[mid])
    return mid;
  else
    low = mid + 1;
}

```

Version 2

```Java
public static int binarySearch(int[] list, int key){
  int low = 0;
  int high = list.length - 1;
  
  while (high >= low) {
    int mid = (low + high) / 2;
    if (key < list[mid])
      high = mid - 1;
    else if (key == list[mid])
      return mid;
    else
      low = mid + 1;
  }
  
  return -1; //Not found :(
}

```

##### Sorting Arrays

###### Selection Sort

- Searches the entire list for the smallest element, then swaps it with the 1st element in the list;
- Then searches through the remainder of the list, and does the same thing
- Repeat ^^^ 

```Java
public class SelectionSort {
  /** the method for sorting the numbers */
  public static void selectionSort (double[] list) {
    for (int i = 0; i < list.length - 1; i++) {
      //Find the minimum in the list [i ... list.length-1]
      double currentMin = list[i];
      int currentMinIndex = i;
      
      for (int j = i + 1; j < list.length; j++) {
        if (currentMin > list[j]) {
          currentMin = list[j];
          currentMinIndex = j;
        }
      }
      
      //Swap list[i] with list[currentMinIndex] if necessary
      if (currentMinIndex != i) {
        list[currentMinIndex] = list[i];
        list[i] = currentMin;;
      }
    }
  }
}
```

###### Insertion Sort

- Sort a list by repeatedly inserting a new element into a sorted sublist until the whoe list is sorted
- What the hell does this description even mean... look this up later, please, future self.

```Java
public class InsertionSort {
  //The method for sorting numbers
  public static void insertionSort (double[] list) {
    for (int i = 1; i < list.length; i++) {
      //Insert list[i] into a sorted sublist list[0 ... i-1], so that list[0 ... i] is sorted
      double currentElement = list[i];
      int k;
      for (k = i - 1; k >= 0 && list[k] > currentElement; k--) {
        list[k + 1] = list[k];
      }
      
      //Insert the current element into list[k + 1]
      list[k + 1] = currentElement;
    }
  }
}
```

##### The Arrays Class

Compare lists
`java.util.Arrays.equals(list1, list2)` tells you whether two arrays have the same content

Fill a list

```Java
java.util.Arrays.fill(list1, 5); //Fill the whole array with 5's
java.util.Arrays.fill(list2, 1, 3, 8); //Fill a part of the array with 8's
```

## 8 | Objects and Classes

##### Definining Classes for Objects

- _object_ a real-life entity that can be distinctly identified
  - objects have properties/attributes, which are represented by data fields w/ their current values
    - eg. circle has attribute for radius
  - behavior/actions of object is defined by methods
    - eg. you can use getArea() for circle objects
- objects of the same type have the same class, & there can be many instances of a class
  - creating an instance = _instantiation_
- variables define data fields & methods to define actions
- a class has methods called _constructors_
  - constructors are designed to perform initializing actions (eg. initializing the data fields)
- you can put 2 classes in 1 file, but 1 class can be a public class

```Java
public class TestSimpleCircle {
}

class SimpleCircle {
}
```

##### Constructing Objects Using Constructors

`constructors`:

- must have the same name as the class itself
- they don't return a type, not even void
- are invoked using the `new` operator when an object is created (they initialize objects)

##### Accessing Objects via Reference Variables

- object's data & methods can be accessed through the `.` operator via the object's reference variable

###### Reference Variables and Reference Types

- object's _reference variables_  reference the object; syntax:

```Java
ClassName objectRefVar;
```

- _reference type_: a class

```Java
Circle myCircle = new Circle(); //myCircle is declared to be of Circle type, then an object is created & referenced to myCircle;
```

###### Accessing an Object's Data and Methods

- the `.` dot operator that invokes methods is also called the _object member access operator_
- `objectRefVar.dataField` references data field in object
- `objectRefVar.method(arguments)`invokes method

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

- When you create a variable, the value is different based on type
- _primitive type:_ the value is of the primitive type
- _reference type:_ the value is a reference to the object's memory location

##### The `Random` Class

- when you make a Random object, you need to specify a seed/use the default seed
- seed: a number used to initialize a pseudorandom number generator

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

- static variables shhared by all objects of the class
- static methods can't access instance members of the class

Declaring static variables methods, and constants:

```Java
static int numberOfObjects;

static innt getNumberObjects() {
 return numberOfObjects;
}

final static double PI = 3.14;
```

##### Visiblity Modifiers

- `public` modifier allows the classes/methods/data fields to be accessed from other classes
- If you don't use a modifier, the default is that: classes, methods, and data fields are accessible by any class in the same package (called _package-private_ or _package-access_)
- can always access data fields & methods from inside the class

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

- making data fields private protects data & makes the class easy to maintain
- _data field encapsulation_: declaring data fields private to prevent direct modifications

Methods have naming conventions to explain their purpose

- `get` methods

```Java
public returnType getPropertyName()
```

- `boolean` methods

```Java
public boolean isPropertyName()
```

- `set` methods

```Java
public void setPropertyName (dataType propertyValue)
```

##### Passing Objects to Methods

- passing an object to a method is to pass the reference of the object

##### Array of Objects

- arrays can hold objects as well as primitive type values
- eg. `Circle[] circleArray = new Circle[10];`
- you need to use a for-loop to initialize the array
- each element of the array references an object

## 9 | Strings

##### Constructing  a String

- Create a string from a string literal w/ this syntax:

```Java
//Way 1
String newString = new String (stringLiteral);

//Way 2
String message = new String ("Welcome to Java");

//Way 3
String message = "Welcome to Java";

//Way 4
char[] charArray = {'W', 'e', 'l', 'c', 'o', 'm', 'e', ' ', 't', 'o', ' ', 'J', 'a', 'v', 'a'};
String message = new String(charArray);
```

##### Immutable strings and interned strings

- Once a string is created, you can't change it.
- Interned string is when Java creates a unique instance for string literals w/ the same character sequence to improve efficiency & save memory

```Java
String s1 = "Welcome to Java"; //This is an interned string object for "Welcome to Java"
String s2 = "Welcome to Java"; //This is an interned string object for "Welcome to Java"; s1 = s2
```

##### String comparisons

- `==` only checks whether two strings refer to the same object, it doesn't tell you if the content is the same
  - You use the `equals` method to check whether they have the same content

```Java
if (string1.equals(string2))
  System.out.println("string1 and string2 have the same contents");
else
  System.out.println("string1 and string2 are not equal");
```

- `compareTo` is the way to compare two strings lexicographically (depends on Unicode)
  - You use this rather than comparison operators `>`, `<`, etc.
  - this will return a value depending on the relation of string1 to string2
    - returns `0` if s1 is equal to s2
    - returns a value less than `0` s1 is lexicographically less than s2
    - returns a value greater than `0` if s1 is lexicographically more than s2

```Java
String s1 = "abc";
String s2 = "abg";
System.out.println(s1.compareTo(s2)); //returns -4
```

It returns -4 because the first 2 characters are the same, but when c & g are compared, c is 4 less than g, which creates -4.

More `String` class methods:

- `equalsIgnoreCase`
- `compareIgnoreCase`
- `regionMatches` (compares portions of 2 strings for equality
- `equalsIgnoreCase`
- `str.startsWith(prefix)`
- `str.endsWith(suffix)`

##### Getting string length and characters, and combining strings

- get length of the string: `message.length()`
- `s.charAt(index)` retreieve a particular character in string `s`
- get a substring from a string `message.substring([start index], [index it goes up to, but not including])'`

Concatenate two strings

```Java
String s3 = s1.concat(s2); //one way
String s3 = s1 + s2; //another way
```

##### Converting, replacing, and splitting strings

Any changes you make to a string just look like changes, but in reality, you get a new string derived from the original

- `message.toLowerCase()`
- `message.toUpperCase()`
- `message.trim()` (eg. `"\t Good Night \n"` is trimmed down to `Good Night`)
- `message.replace("this with", "that")`
- `message.replaceFirst("first instance of this letter with", "that")`
- `message.replace('a', 'b')`
- `message.split("#")` split at instances of # or whatever other string

##### Matching, replacing, and splitting by patterns

- matches: `message.matches("Some string here");
  - matches is more powerful than `.equals()` b/c it can check whether strings follow a pattern.

```Java
//Check whether there is the string beginning with "Java" followed by 0+ characters; if there is, it evaluates to true
"Java is fun".matches("Java.*");
"Java is cool".matches("Java.*");
"Java is powerful".matches("Java.*");

//Check whether it follows this format
"440-02-4534".matches("\\d{3}-\\d{2}-d{4}"} //evaluates to true
```

- replace all: `message.replaceAll("replace this", "with this");`
- split: `"Java,C?C%, C++".split("[.,:;?]");` This will split it into the array tokens Java, C, C#, C++
- find the index of a character: `"Hey".indexOf('H')` returns 0.
- find the index of a character from a certain index: `message.indexOf("string", [index]);`

##### Conversion between strings and arrays

To convert a string to an array:

```Java
char[] chars = "Java".toCharArray(); //Each character in the string is a new element of the array.
```

Copy particular parts of the string and convert that into an array, where each character is an element of the array:

```Java
message.getChars( [(int) beginHere], [(int) endHere], [char[] dst], [(int) dstBegin] )
```

- `char[] dst` indicates which array you want to put the elements of the substring into
- `dstBegin` indicates which indices which indices of the destination array that you want to replace/add the new substring to

##### Converting characters and numeric values to strings

```String.valueOf([some number or characters]);``` will return an array of characters converted into strings
eg. ```String.valueOf(5.44)``` --> '5', '.', '4', '4' in an array

##### Formatting strings

The syntax to format strings is ```String.format(format, item1, item2, ..., itemk)```, which is similar to the format for print statements.

##### The `Character` class

- Create a `Character` object from a `char` value. (eg. `Character character = new Character('a');`)

Different `Character` methods:

- `variable.isDigit(char)`
- `variable.isLetter(char)`
- `variable.isLetterOrDigit(char)`
- `variable.isLowerCase(char)`
- `variable.isUpperCase(char)`
- `variable.toLowerCase(char)`
- `variable.toUpperCase(char)`
- `variable.countLetters(char)` (count the occurrences of each letter in a string)

##### The `StringBuilder` and `StringBuffer` classes

- `StringBuilder` and `StringBuffer` can be used wherever strings are used, the only difference is that they're more flexible than `String`s.
- can add new content into the `StringBuilder` and `StringBuffer` objects (so they are changeable)
- Difference between `StringBuilder` and `StringBuffer`:
- `StringBuilder`s are synchronized, meaning you can only use one task to execute the methods
- But it's more efficient if you only need a single task
- `StringBuffer` can be accessed by multiple tasks concurrently
- Both the `StringBuilder` and `StringBuffer` have the same methods & constructors;  

##### Modifying strings in the `StringBuilder`

- `StringBuilder stringBuilder = new StringBuilder();`
- `stringBuilder.append("some text");`
- `stringBuilder.insert(11, "some text");` (inserts the text in index 11)
- `stringBuilder.delete(indexStart, indexEnd);` (delete things in that range)
- `stringBuilder.reverse()`
- `stringBuilder.replace(indexStart, indexEnd, "Words");` (replace the characters in that range)
- `stringBuilder.setCharAt(0, 'w');` (changes a character at the index specified)

##### The `toString`, `capacity`, `length`, `setLength`, and `charAt` methods

- `stringBuilder.capacity();` (returns the # of characters the string builder can store w/o having to increase in size)
- `stringBuilder.length();`
- `stringBuilder.setLength(newLength);` (sets the length of the string builder; string will be truncated if it's less than the current length) (if it's greater, there'll be null characters added)
- `stringBuilder.charAt(index);` (tells you what character is at a certain index
- `stringBuilder.trimToSize();` (This will reduce the capacity to the actual size, getting rid of all of the parts that aren't being used)
- `new StringBuilder (initialCapacity)` creates a StringBuilder with a specified initial capacity

## 10 | Thinking in Objects

##### Immutable Objects and Classes

- immutable classes --> immutable objects
  - eg. `String` class
- contents of immutable objects are ... immutable
- immutable classes properties:
  - all data fields must be private & can't have any `set` methods as `public`

In order to create an immutable class, meet these criteria:

- all data fields must be private
- there can't be any mutator methods for data fields
  - mutator method: a method that changes a variable; known as `set`ter method

##### The Scope of Variables

- (talking about scope in terms of class, not loops)
- scope of instance & static variables: the entire class
  - this is the class regardless of where the variables are declared
- you can put a class's variables & methods in any order
  - exception: when a data field is initialized based on a reference to another data field
    - then the other data field needs to be declared first
    - it's good practice to declare data fields @ beginning of a class
  - can declare a class's variable many times, but can use the same variable name in a method in different nonnesting blocks
  - __hidden variable__: a local variable w/ the same name as a class' variable takes precendence, & the same-named class variable is _hidden_ (example below)

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

- `this` refers to the object itself; it can be used inside a constructor to invoke another constructor of the same class... yeah
  - usually, `this` reference is omitted; but you need the `this` reference to reference hidden data fields or invoke an overloaded constructor

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

- hidden data field: a data field name is used as a parameter in a `set` method --> the data field is hidden in the `set` method
  - in this case, you need to use `this` in order to access the data field

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

- use `this` to invoke a constructor of the same class, like: 

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

- __class abstraction__: the separation of class implementation from the use of a class
- __class encapsulation__: hiding the details of a class' implementation

More about class abstraction:

- class's contract: the instructions the developer writes to tell the user how functions are used
- a class is known as an _abstract data type_ (ADT) b/c you can find & compute things of a class without knowing how they're actually computed

##### Object-Oriented Thinking

- in OOT, the focus is on designing methods; you use methods & data together in objects
- procedural paradigm: focus on designing methods; (data & methods are separate)
- OO paradigm: data & methods are coupled into objects

##### Object Composition

- objects can create another object
  - __composition__: the relationship between the two objects
  - composition is a case of the aggregation relationship
- __aggregation__ models _has-a_ relationships & shows ownership relationships between 2 objects
  - __aggregating object__: the ower object
    - may be owned by several other aggregating objects
    - if an object is owned by only 1 aggregating object, the relationship b/t them is called a __composition__
  - __aggregating class__: the aggregating object's class
    - aggregated class vs aggregating class
- __multiplicity__: a number/interval specifying how many of the class's objects are involved in the relationship; (* = unlimited)
  - each class involved in a relationship may specify a multiplicity

##### Class Design Guidelines

###### Cohesion

- classes describe a single entity & its class operations work toward a coherent purpose

###### Consistency

- place data declarations before the constructor
- place constructors before methods
- consistantly provide a public no-arg constructor to construct a default instance
  - you should document the reason for not including one if you decide not to include one
  - if no constructors are explicitly defined, a public default no-arg constructor w/ an empty body is assumed

###### Encapsulation

- __encapsulating data fields__: class should use `private` modifier to hide data from direct access by clients

###### Clarity

- design classes that allow the user to freely use it however they want

###### Instance vs. Static

- always reference static variables & methods from a class name (Not a reference variable) to improve readibility & avoid errors
- use `set` methods to change static data fields
- a constructor is always instance b/c it's used to create a specific instance
  - a static variable/method can be invoked from an instance method, but an instance variable/method can't be invoked from a static method

##### Processing Primitive Data Type Values as Objects

- can wrap primitive data types in their respective wrapper objects
  - eg `int` in `Integer`, `double` in `Double`, `char` in `Character`;
- wrapper classes don't have no-arg constructors
  - thus, once the objects are created, internal values can't be changed
- conversion methods, & other common methods are on p.395 

##### Automatic Conversion between Primitive Types and Wrapper Class Types

- primitive type values can be converted to object using a wrapper, & vice versa
- __boxing__: converting a primitive value to a wrapper object
- __unboxing__: converting a wrapper object to a primitive type
- __autoboxing__ & __autounboxing__: the Java compiler automatically boxes a primitive value that appears in a context requiring an object & unboxes an object that appears in a context requiring a primitive value

##### The `BigInteger` and `BigDecimal` Classes

- these are used to represent huge integers and decimal numbers... who would've known
- they are both immutable!!! Immutable. __Immutable__.
- can use `new BigInteger(String)` & `new BigDecimal(String)` to create instances of BigInteger & BigDecimal to use `add`, `subtract`, `multiple`, `divide`, & `remainder` methods on, and `compareTo`

## 11 | Inheritance and Polymorphism

#### Introduction

- __inheritance__: defining new classes from existing classes

##### Superclasses and Subclasses

```Java

```

##### Using the `super` Keyword

```Java

```

###### Calling Superclass Constructors

```Java

```

###### Constructor Chaining

###### Calling Superclass Methods

##### Overriding Methods

```Java

```

##### Overriding vs. Overloading

```Java

```

##### The `Object` Class and Its `toString()` Method

```Java

```

##### Polymorphism

```Java

```

##### Dynamic Binding

```Java

```

##### Casting Objects and the `instanceOf` Operator

```Java

```

##### The `Object`'s `equals` Method

```Java

```

##### The `ArrayList` Class

```Java

```

##### The `protected` Data and Methods

```Java

```

##### Preventing Extending and Overriding

```Java

```

## 15 | Abstract Classes & Iterfaces

##### Introduction

```Java

```

##### Abstract Classes

```Java

```

###### Why Abstract Methods?

```Java

```

###### Points about Abstract Classes

```Java

```

##### Interfaces

```Java

```

##### The `Comparable` Interface

```Java

```

##### The `Cloneable` Interface

```Java

```

##### Interfaces vs. Abstract Classes

```Java

```

