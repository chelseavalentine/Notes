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

Note that ```=``` is the *assignment operator*.

``` Java
public class ClassName{
  public static void main (String[] args) {
    [Main program goes here]
  }
}
```
