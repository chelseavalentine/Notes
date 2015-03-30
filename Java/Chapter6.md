#### Single-Dimensional Arrays

##### Array Basics
* an array's size is fixed once it's created
* access arrays via its index `array[0]`

##### Declaring Array Variables
```Java
//Basic declaration is like this
elementType[] arrayVariableName;

//For instance
double[] myList;
```

##### Creating Arrays
* declaration of array variables don't allocate any memory space for the array; only a storage allocation
* but once an array has been declared, you can do this:
```Java
arrayVariableName = new elementType[arraySize];
```

* You can declare a variable & give it a size at the same time
```Java
//Way 1
elementType[] arrayRefVar = new elementType[arraySize];

//Way 2
elementType arrayRefVar[] = new elementType[arraySize];

//eg.
double[] myList = new double[10];
```
##### Array Size and Default Values
* get access to the size of an array variable by doing `arrayVariable.length`

##### Array initializers
* You can create an array with its values set, but remember that you can't extend the size:
```Java
elementType[] arrayVariable = {value0, value1, value2, ..., valuek};
```

##### Processing Arrays
* Since you know the size of an array by using `.length`, you should use `for` loops to process them:

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
* Doing `list1 = list2` will copy the reference values from list1 to list2, it won't actually copy the contents of the array
* Ways to actually copy arrays
  * Loop through the array, copying elements one by one
  * Use the static `arraycopy` method in the `System` class
    ```Java
    System.arraycopy(sourceArray, src_position, targetArray, tar_position, length);
    
    //example
    System.arraycopy(sourceArray, 0, targetArray, 0, sourceArray.length);
    ```
##### Passing Arrays to Methods
* arrays are passed by sharing to methods, so you can see a change in the array outside of the method
* it's called __pass-by-sharing__: the array in the method is the same as the array being passed; the reference value is passed to the method, not just the value

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
* elements of the list must be ordered already for binary search to work
* this is how it works... if this even makes sense
  * If the key is less than the middle element, you continue to search for the key in only the first half of the array
  * If the key is equal to the middle element, the search ends with a match
  * If the key is greater than the middle element, you need to continue to search for the key only in the second half of the array

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
* Searches the entire list for the smallest element, then swaps it with the 1st element in the list;
* Then searches through the remainder of the list, and does the same thing
* Repeat ^^^ 

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
* Sort a list by repeatedly inserting a new element into a sorted sublist until the whoe list is sorted
* What the hell does this description even mean... look this up later, please, future self.

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
