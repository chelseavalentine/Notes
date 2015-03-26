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
```Java

//Find smallest potion

//Smallest = shortest potion
public static Potion find smallest (Potion[] listOfPotions) {
  Potion temp = listOfPotions[0];
  for (int i = 1; i < listOfPotions.length; i++){
    //Compare length of temp to length of list [i]
    if (temp.toString().length() > listOfPotions[i].toString().length)
      temp = listOfPotions[i];
  }
  return temp;
}
```

``` Java
//Create an array of potion objects
Potions [] listOfPotions = new Potion [10]; //This creates an array, but no Potion objects

for (int i = 0; i < listOfPotions.length; i++){
  listOfPotions[0] = new Potion (); //This fills the array w/ 10 Potion objects
}
```

purpose of `static`: when you specify static, it turns it into a class method, or data field, so it can be used without an object. Usually used for things that need to be shared between multiple objects in the class

if no `static` is present, then the method/data field is called an __instance__; 
