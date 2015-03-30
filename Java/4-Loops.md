#### Loops

##### The ```while``` loop
Do something while the statement is true
``` Java
while (x == 10) {
  [Your code that will be executed while x is equal to 10];
}
```

##### The `do-while` loop
If you want to run the code once before checking the condition (to decide whether to continue), you can use this
``` Java
do {
  [this code];
} while (loop-continuation-condition);
```

##### The `for` loop
``` Java
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
* `break` will exit the loop that it's in
* `continue` will end the current iteration & go to the next one
##### Sentinel loop
These keep doing something until the value is changed to 0. (eg. you tell a user to type '0' when they're done.

##### Input and output redirections
* input redirection: uses data from another file 
  * type this into the command line ```java SentinelValue < input.txt```
* output redirection: sends the output to a file rather than displaying it on the console
  * type this into the command line ```java ClassName > output.txt```

##### Controlling a loop with a confirmation dialog
``` Java
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
