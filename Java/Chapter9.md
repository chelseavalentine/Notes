#### Strings (378-389)

##### Constructing  a String
* Create a string from a string literal w/ this syntax:

``` Java
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
* Once a string is created, you can't change it.
* Interned string is when Java creates a unique instance for string literals w/ the same character sequence to improve efficiency & save memory

``` Java
String s1 = "Welcome to Java"; //This is an interned string object for "Welcome to Java"
String s2 = "Welcome to Java"; //This is an interned string object for "Welcome to Java"; s1 = s2
```

##### String comparisons
* `==` only checks whether two strings refer to the same object, it doesn't tell you if the content is the same
  * You use the `equals` method to check whether they have the same content
``` Java
if (string1.equals(string2))
  System.out.println("stirng1 and string2 have the same contents");
else
  System.out.println("string1 and string2 are not equal");
```

* `compare to` is the way to compare two strings lexicographically (depends on Unicode)
  * You use this rather than comparison operators `>`, `<`, etc.
  * this will return a value depending on the relation of string1 to string2
    * returns `0` if s1 is equal to s2
    * returns a value less than `0` s1 is lexicographically less than s2
    * returns a value greater than `0` if s1 is lexicographically more than s2

``` Java
String s1 = "abc";
String s2 = "abg";
System.out.println(s1.compareTo(s2)); //returns -4
```

It returns -4 because the first 2 characters are the same, but when c & g are compared, c is 4 less than g, which creates -4.

More `String` class methods:
* `equalsIgnoreCase`
* `compareIgnoreCase`
* `regionMatches` (compares portions of 2 strings for equality
* `equalsIgnoreCase`
* `str.startsWith(prefix)`
* `str.endsWith(suffix)`

##### Getting string length and characters, and combining strings
* get length of the string: `message.length()`
* `s.charAt(index)` retreieve a particular character in string `s`
* get a substring from a string `message.substring([start index], [index it goes up to, but not including])'`
* 

Concatenate two strings
``` Java
String s3 = s1.concat(s2); //one way
String s3 = s1 + s2; //another way
```

##### Converting, replacing, and splitting strings
Any changes you make to a string just look like changes, but in reality, you get a new string derived from the original
* `message.toLowerCase()`
* `message.toUpperCase()`
* `message.trim()` (eg. `"\t Good Night \n"` is trimmed down to `Good Night`)
* `message.replace("this with", "that")`
* `message.replaceFirst("first instance of this letter with", "that")`
* `message.replace('a', 'b')`
* `message.split("#")` split at instances of # or whatever other string

##### Matching, replacing, and splitting by patterns
* matches: `message.matches("Some string here");
  * matches is more powerful than `.equals()` b/c it can check whether strings follow a pattern.

``` Java
//Check whether there is the string beginning with "Java" followed by 0+ characters; if there is, it evaluates to true
"Java is fun".matches("Java.*");
"Java is cool".matches("Java.*");
"Java is powerful".matches("Java.*");

//Check whether it follows this format
"440-02-4534".matches("\\d{3}-\\d{2}-d{4}"} //evaluates to true
```

* replace all: `message.replaceAll("replace this", "with this");`
* split: `"Java,C?C%, C++".split("[.,:;?]");` This will split it into the array tokens Java, C, C#, C++
* find the index of a character: `"Hey".indexOf('H')` returns 0.
* find the index of a character from a certain index: `message.indexOf("string", [index]);`

##### Conversion between strings and arrays

To convert a string to an array:
``` Java
char[] chars = "Java".toCharArray(); //Each character in the string is a new element of the array.
```

Copy particular parts of the string and convert that into an array, where each character is an element of the array:
``` Java
message.getChars( [(int) beginHere], [(int) endHere], [char[] dst], [(int) dstBegin] )
```
* `char[] dst` indicates which array you want to put the elements of the substring into
* `dstBegin` indicates which indices which indices of the destination array that you want to replace/add the new substring to

##### Converting characters and numeric values to strings
```String.valueOf([some number or characters]);``` will return an array of characters converted into strings
eg. ```String.valueOf(5.44)``` --> '5', '.', '4', '4' in an array
##### Formatting strings
The syntax to format strings is ```String.format(format, item1, item2, ..., itemk)```, which is similar to the format for print statements.

##### The `Character` class
* Create a `Character` object from a `char` value. (eg. `Character character = new Character('a');`)

Different `Character` methods:
* `variable.isDigit(char)`
* `variable.isDigit(char)`
* `variable.isDigit(char)`
* `variable.isDigit(char)`
* `variable.isDigit(char)`
* `variable.isDigit(char)`

```
public class ClassName{
  public static void main (String[] args) {
    [Main program goes here]
  }
}
```
