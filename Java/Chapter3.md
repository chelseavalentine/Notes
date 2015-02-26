#### Selections

##### ```boolean``` data type
Two values: ```true``` or ```false```
```Java
boolean stayAwake = true;
```

###### Comparison operators
* ```<``` = less than
* ```<=``` = less than or equal to
* ```>``` = greater than
* ```>=``` = greater than or equal to
* ```==``` = equal to
* ```!=``` = not equal to

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

* ```Math.cos()```, ```Math.sin()```, ```Math.tan()```, ```Math.asin()```, ```Math.acos()```, ```Math.atan()```
* ```Math.toRadians()```, ```Math.toDegrees()```
* ```Math.exp()```, ```Math.log()```, ```Math.log10()```
* ```Math.pow()```, ```Math.sqrt()```
* ```Math.ceil()```, ```Math.floor()```, ```Math.round()```
* ```Math.min()```, ```Math.max()```
* ```Math.abs()```

##### Logical operators
Boolean operators:
* ```!``` = not (logical negation)
* ```&&``` = and (logical conjunction)
* ```||``` = or (logical disjunction)
* ```^``` = exclusive or (logical exclusion)
  * The exclusive or, ```^```, of 2 Boolean operands is true if & only if the two operands have different Boolean values.

##### ```switch``` Statements
If there're multiple cases that you can foresee (due to you giving options, or w/e), then you can use switch statements to make your life easier.

``` Java
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
``` Java
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
``` Java
int x = 50; 
int y = (x > 0) ? 1 : -1;
```

This is essentially saying, if x is greater than 0, then assign y to 1 (```y = 1```). Otherwise, assign y to -1 (```y = -1```).

The general format for conditional expressions is as follows:
```boolean-expression ? expression1 : expression2;```

###### Formatted print statements
Format specifiers:
* ```%b``` a boolean
* ```%c``` a character
* ```%d``` a decimal integer
* ```%f``` a floating-point number
* ```%e``` a number in standard scientific notation
* ```%s``` a string

So let's say you have a print statement, and you want to format it, because you happen to be using printf, you'd use:

``` Java
System.out.printf ("Hello, that will be $%10.2d for those %15s", cost, item); 
```

###### Justifying your formatted print statements
* ```%-10d``` will left-justify

##### Confirmation dialogs
``` Java
import JOptionPane.*;
...
int option = JOptionPane.showConfirmDialog (null, "[TEXT TO USER]");
```
