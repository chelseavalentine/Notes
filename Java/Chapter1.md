#### Introduction to Computers, Programs, and Java

##### Basics
``` Java
public class ClassName{
  public static void main (String[] args) {
    [Main program goes here]
  }
}
```

* The program starts executing from the *main* method. There can be several methods.


##### Message dialog boxes
``` Java
import javax.swing.JOptionPane; //import javax.swing.*; works too

public class WelcomeInMessageDialogBox {
  public static void main (String[] args) {
    //Display 'TEXT' in a message dialog box
    JOptionPane.showMessageDialog ( null, "TEXT");
  }
}
```

* It's standard to use null as the first argument, and then your text as the second.


##### Avoid common errors
* If you don't make 7 in 7/21 into 7.0/21, Java will interpret it as an integer division and give you an incorrect result (if you were intending to do regular division).
