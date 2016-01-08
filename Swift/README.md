# Swift

Time to learn Swift!

## Syntax

#### Type declarations

You usually don't need to declare type, but you can:

``` swift
let implicitDouble = 70.0
let explicitDouble: Double = 70
```

Anything declared with `let` is immutable… let's say you want to put a number in a string:

``` swift
let noun = "I'm "
let age = 18
let message = noun + String(age) + " for now"
// alternatively
let message2 = "I'm \(age) for now"
```



* __Optionals__: used when you have a variable whose value is optional (either the value, or `nil`
  
  ``` swift
  let optionalNum: Int? = 10
  ```
  
  You can later unwrap these to get the type from the optional, if you know the value isn't `nil`. One way is with the __force unwrap operator__ `!`, like so:
  
  ``` swift
  let num: Int = optionalNum!
  ```
  
  Here, we're unwrapping myString to see whether it's an integer. If it is, it'll assign possibleInt to an integer, otherwise it'll be `nil`.
  
  ``` swift
  var myString = "7"
  var possibleInt = Int(myString)
  ```


* __implicitly unwrapped optionals__: (you'll rarely use) are indicated with a `!`. Its value is mandatory, but you don't need to unwrap it every time you want to access it
  
  ``` swift
  var implicityUnwrappedInt: Int!
  ```
  
  ​

#### Arrays

You can either initialize them right away:

``` swift
var array1 = ["This", "is", "written", "in", "Swift"]

/* accessed like: */
print(array1[0])
```

Or make an empty array, with a type declaration:

``` swift
let array2 = [String]()
```



#### Control Flow

* `if`, `else if`, `else`
  
  ``` swift
  if variable == 0 {
    //
  } else if variable > 2 {
    //
  } else {
    //
  }
  ```



* optional binding to check an optional's value
  
  ``` swift
  var optional: String? = "Name"
  var greeting = "Hello."
  if let message = optional {
    greeting = "Hello, \(name)"
  }
  ```



* `for-in` (like for-each)
  
  ``` swift
  let array = [1, 20, 31, 42, 50]
  for num in array {
    if num...
  }
  ```



* `switch` statements
  
  Notice how you don't need to break out of each case. Cases don't waterfall.
  
  Also you must make exhaustive switch statements, hence the need for the `default` case
  
  ``` swift
  let variable = "hi"
  switch variable {
  case "h":
  	//
  case "", "e":
  	//
  case let x where x.hasSuffix("h"):
  	//
  default:
  	//
  }
  ```



* `for` loops
  
  * `…`: closed range operator, includes both values
  * `..<`: half-open range operator, includes bottom bound up to, but not including the upper bound
  
  ``` swift
  for i in 0..<4 {
    //
  }
  // equivalent number of iterations
  for _ in 0..3 {
    // wildcard _ is used when we don't care to use the
    // temporary value, we just need iterations
  }
  ```
  
  ​


* `while` loops
  
  //TODO: read about while loops



#### Functions and methods

* `func`: declares a function
  * parameters are formatted like `name: Type`
  * the return type is declared like `-> [type]` after the parameter list
* when you call a function, the first argument isn't written with its name, but every subsequent argument is
* __method__: a function that's defined to a specific type

``` swift
func function(prop1: String, prop2: Int) -> String {
	return "hehe \(prop1), uhh... \(prop2)."  
}

// let's execute our function
function("it's 7:44am", prop2: 3)
```



#### Classes and Initializers

**Classes:**

``` swift
class MyClass {
  var property1 = 0;
  
  func method1() -> void {
  	print("Hi")
  }
}
```

**Objects:**

We can create instances of the class with the following syntax:

``` swift
var myClass = MyClass()
myClass.property1 = 120
var msg = myClass.method1()
```

Instead of constructors, we have initializers. So it looks like:

**Initializers:**

``` swift
class MyClass {
  // properties...
  var property: Int
  
  init(property: Int) {
  	self.property = property
  }
  
  // methods...
}
```

And we can initialize it like so:

``` swift
let instance = MyClass(property: 10)
```



**Inheritance:**

Just like Java, Swift has single inheritance.

We can create subclasses like this:

``` swift
class Baby: Human {
  var age: Int
  var name: String
  var numLimbs: Int
  
  init(age: Int, name: String) {
  	self.age = age
    self.name = name
    super.init(numLimbs: numLimbs)
  }
}
```

A subclass will inherit all of the superclass' behavior. We can override methods, but we must mark it with `override`





#### Enumerations and Structures



#### Protocols



## Libraries

#### Swift and Cocoa Touch

