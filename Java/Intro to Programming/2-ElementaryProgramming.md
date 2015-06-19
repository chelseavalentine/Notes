#### Elementary Programming

##### Basics
Read user input
``` Java
import java.util.Scanner;

public class Scanner{
  public static void main (String[] args) {
    Scanner in = new Scanner (System.in); //This creates a new scanner called 'in'
    
    in.close(); //It's good practice to close off user input at the end of your program!
  }
}
```

When using the scanner that you created, 'in', there are some common methods that you can use:
* ```in.next()``` = reads a string that ends before a whitespace character
* ```in.nextLine()``` = reads a line of text (ends when the Enter key is pressed)
* ```in.nextDouble()``` = reads an number of the _double_ type
* ```in.nextInt()``` = reads an integer of the _int_ type
* ```in.nextFloat()``` = reads a number of the _float_ type
* ```in.nextLong()``` = reads an integer of the _long_ type
* ```in.nextShort()``` = reads an integer of the _short_ type
* ```in.nextByte()``` = reads an integer of the _byte_ type

##### Print statements
Print with a line break after the statement
``` Java
public class PrintBreak{
  public static void main (String[] args) {
    System.out.println("Wow this will have a line break after the statement.");
  }
}
```

Print without a line break after the statement
* You can also do the same thing by typing \n within the " " in your print statement

``` Java
public class PrintNoBreak{
  public static void main (String[] args) {
    System.out.print("Wow this will not have a line break after the statement,"
    + "but you could make some like \n\n\n");
  }
}
```

Print a formatted output
``` Java
public class PrintFormat{
  public static void main (String[] args) {
    System.out.printf("I don't really know how to do this properly yet, but once I do, I'll update this...");
  }
}
```

##### Variables
You must declare and initialize variables before you can use them!
``` Java
int variable1; //Declare one at a time
variable1 = 3; //Initialize it in a separate step
int yo = 4; //Declare a variable and initialize it in one single step.

int variable2, variable3, variable4; //Or do multiple in one go
variable2 = variable3 = variable4 = 0; //Initialize multiple variables, to have the same value, in one step.

int variable5 = 0, variable6 = 1; //Declare and initialize variables of the same type in one step.
```

There are multiple variable types at your disposal:
* ```int```
* ```double```
* ```byte```
* ```short```
* ```long```
* ```float```
* ```char```
* ```boolean```

Just know that these variable types vary in storage size. Depending on which variable you use, an amount of memory will be allocated.

Going from smallest to largest storage size: ```byte```, ```short```, ```int```, ```long```, ```float```, & ```double```

Note that ```=``` is the *assignment operator*.

##### Named constants
Let's say you want to keep a fixed number and use it throughout your program. How would you go about it? You'd create a named constant.

Its format works like this:
``` Java
public class NamedConstants{
  public static void main (String[] args) {
    final datatype CONSTANTNAME = value; //declare the constant value
    
    int useThatValue = CONSTANTNAME; //use the constant value
  }
}
```

##### Numeric operators
* ```+``` Addition
* ```-``` Subtraction
* ```*``` Multiplication
* ```/``` Division
* ```%``` Remainder

###### Exponent operations
Did you notice that there isn't a ```**``` for exponent operations, like there is in Python? If you want to represent an exponent, do the following:
``` Java
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
* ```+=``` = Addition assignment
* ```-=``` = Subtraction assignment
* ```*=``` = Multiplication assignment
* ```/=``` = Division assignment
* ```%=``` = Remainder assignment

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

* Floating-point values --> char
  * Procedure: floating-point becomes an integer, then a char.
```Java
char ch = (char)55.05; // 55 is assigned to ch
System.out.println(ch); // displays A
```

* char --> int
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
* ```\b``` = Backspace. Unicode: \u0008. Decimal value: 8.
* ```\t``` = Tab. Unciode: \u0009. Decimal value: 9.
* ```\n``` = Linefeed. Unicode: \u000A. Decimal value: 10.
* ```\f``` = Formfeed. Unicode: \u000C. Decimal value: 12.
* ```\r``` = Carriage Return. Unicode: \u000D. Decimal value: 13.
* ```\\``` = Backslash. Unicode: \u005C. Decimal value: 92.
* ```\"``` = Double Quote. Unicode \u0022. Decimal value: 34.

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
``` Java
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
```Java
double doubleValue = Double.parseDouble (123456)
```