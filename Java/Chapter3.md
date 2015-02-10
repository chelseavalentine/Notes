#### Selections (122-147)

##### boolean data type
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


``` Java
public class ClassName{
  public static void main (String[] args) {
    [Main program goes here]
  }
}
```
