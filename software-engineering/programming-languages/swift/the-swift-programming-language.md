# The Swift Programming Language (Swift 2.2)

These notes are all based on Apple Inc. “The Swift Programming Language (Swift 2.2).” iBooks. https://itun.es/us/jEUH0.l

## A Swift Tour

- __structures__ are always passed by value, whereas __classes__ are passed by reference

- You can use functions in enum values:

  ```swift
  enum TaskActions {
      case Create(String)
      case Delete(Int)
      case Edit(Int, String)
  }
  
  // Thereby allowing you to do:
  let thisAction = TaskActions.Create("Study for the final.")
  
  // This is kind of weird, but you can also do
  switch thisAction {
  case let .Create(description):
      print("The task is to: \(description).")
  case let .Edit(id, description):
      print("We're going to edit task \(id) with the new task: \(description)")
  default:
      print("Unrecognized or currently unsupported action.")
  }
  ```

- `protocol` seems like an abstract class?

  ```swift
  // Classes can extend protocols
  class SimpleClass: ExampleProtocol {
      var simpleDescription: String = "A Simple Class that seems to be extending ExampleProtocol"
      var anotherProperty: Double = 4.20
      func adjust() {
          simpleDescription = "this function has different implementations"
      }
  }
  
  var mySimpleClass = SimpleClass()
  print("Before: \(mySimpleClass.simpleDescription)")
  
  mySimpleClass.adjust()
  print("After: \(mySimpleClass.simpleDescription)")
  ```

```
// Structures can extend protocols, too
struct SimpleStructure: ExampleProtocol {
    var simpleDescription: String = "A simple structure"

    /// mutating functions allow you to modify the structure from
    /// within the method, or even replace self entirely
    mutating func adjust() {
        simpleDescription = "Weird.. this function allows a mutating func"
    }
}

var mySimpleStructure = SimpleStructure()
print("Before: \(mySimpleStructure.simpleDescription)")

mySimpleStructure.adjust()
print("After: \(mySimpleStructure.simpleDescription)")
​```
```

- use extensions to add functionaltity to a default type (eg. new methods or properties)

  - extensions can be useful in many cases, eg. adding protocol conformance to a type

  ```swift
  extension Int: ExampleProtocol {
      var simpleDescription: String {
          return "The number \(self)"
      }
  
      mutating func adjust() {
          self += 10
      }
  }
  
  // Now you can use protocols on numbers
  print(10123.simpleDescription);
  ```

- the compiler treats variables/objects as having the class it was instantiated with; so even if it changes at run time, you can't access methods/properties the class implements as extra, if that class w/ extra features wasn't the initializing type

- Error handling

  ```swift
      enum UserErrors: ErrorType {
      case EmptyString
      case NoPermission
  }
  
  func addNewTask(task: String) throws -> String {
      if task == "" {
          throw UserErrors.EmptyString
      }
  
      return "Task added!"
  }
  
  func doUserActions() {
      do {
          /// some risky/secret task that the user doesn't have permission for
          /// If it fails, it'll go to the catch block
          try addNewTask("Hello world")
      } catch UserErrors.NoPermission {
          print("Sorry, you don't have permission to do this.")
      }catch let userError as UserErrors {
          print("You made a mistake, yo: \(userError)")
      } catch {
          print(error)
      }
  }
  ```

- Suppress errors and accept `nil`:

  - if it doesn't really matter whether the function executes properly, and you want to just discard the error on fail and accept nil, use `try?`

  ```swift
  let userSuccess = try? addNewTask("Hello world.")
  ```

- `defer` can be used to put setup & cleanup code nexxt to each other, and many other things, since it executes before the function returns

  ```swift
  func hello() {
      var goodbye = false;
      defer {
          goodbye = true;
      }
      // do some stuff here
  }
  ```

- generics: write the name inside angled brackets to make a generic function or type

  ```swift
  func duplicateItem<Item>(item: Item, numberOfTimes: Int) -> [Item] {
      var result = [Item]()
  
      for _ in 0..<numberOfTimes {
          result.append(item)
      }
  
      return result;
  }
  
  print(duplicateItem(5, numberOfTimes: 3))
  
  /// Generic enumerations
  enum OptionalValue<Wrapped> {
      case None
      case Some(Wrapped)
  }
  
  var possibleInteger: OptionalValue<Int> = .None
  possibleInteger = .Some(100)
  
  ```

- You can fine tune which generics can be used, or in general what requirements variables must have

  - eg. which protocol must it implement? do 2 types have to be the same? does it need a particular superclass?

  ```swift
  func anyCommonElements <T: SequenceType, U: SequenceType
      /// Writing <T: Equatable> is the same as writing <T where T: Equatable>
      where T.Generator.Element: Equatable,
      T.Generator.Element == U.Generator.Element> (lhs: T, _ rhs: U) -> Bool {
  
      // Do some stuff here lol
      // eg.
      for lhsItem in lhs {
          for rhsItem in rhs {
              if lhsItem == rhsItem {
                  return true;
              }
          }
      }
      //
      return false;
  }
  
  anyCommonElements([1, 2, 3], [3])
  ```

## Syntax

#### Type declarations

You usually don't need to declare type, but you can:

```swift
let implicitDouble = 70.0
let explicitDouble: Double = 70
```

Anything declared with `let` is immutable… let's say you want to put a number in a string:

```swift
let noun = "I'm "
let age = 18
let message = noun + String(age) + " for now"
// alternatively
let message2 = "I'm \(age) for now"
```



- __Optionals__: used when you have a variable whose value is optional (either the value, or `nil`

  ```swift
  let optionalNum: Int? = 10
  ```

  You can later unwrap these to get the type from the optional, if you know the value isn't `nil`. One way is with the __force unwrap operator__ `!`, like so:

  ```swift
  let num: Int = optionalNum!
  ```

  Here, we're unwrapping myString to see whether it's an integer. If it is, it'll assign possibleInt to an integer, otherwise it'll be `nil`.

  ```swift
  var myString = "7"
  var possibleInt = Int(myString)
  ```

- __implicitly unwrapped optionals__: (you'll rarely use) are indicated with a `!`. Its value is mandatory, but you don't need to unwrap it every time you want to access it

  ```swift
  var implicityUnwrappedInt: Int!
  ```

  

#### Arrays

You can either initialize them right away:

```swift
var array1 = ["This", "is", "written", "in", "Swift"]

/* accessed like: */
print(array1[0])
```

Or make an empty array, with a type declaration:

```swift
let array2 = [String]()
```



#### Control Flow

- optional binding to check an optional's value

  ```swift
  var optional: String? = "Name"
  var greeting = "Hello."
  if let message = optional {
    greeting = "Hello, \(name)"
  }
  ```

- `for-in` (like for-each)

  ```swift
  let array = [1, 20, 31, 42, 50]
  for num in array {
    if num...
  }
  ```

- `switch` statements

  Notice how you don't need to break out of each case. Cases don't waterfall.

  Also you must make exhaustive switch statements, hence the need for the `default` case

  ```swift
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



- `for` loops

  - `…`: closed range operator, includes both values
  - `..<`: half-open range operator, includes bottom bound up to, but not including the upper bound

  ```swift
  for i in 0..<4 {
    //
  }
  // equivalent number of iterations
  for _ in 0..3 {
    // wildcard _ is used when we don't care to use the
    // temporary value, we just need iterations
  }
  ```

  

#### Functions and methods

- `func`: declares a function
  - parameters are formatted like `name: Type`
  - the return type is declared like `-> [type]` after the parameter list
- when you call a function, the first argument isn't written with its name, but every subsequent argument is
- __method__: a function that's defined to a specific type

```swift
func function(prop1: String, prop2: Int) -> String {
	return "hehe \(prop1), uhh... \(prop2)."
}

// let's execute our function
function("it's 7:44am", prop2: 3)
```



#### Classes and Initializers

Instead of constructors, we have initializers. So it looks like:

**Initializers:**

```swift
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

```swift
let instance = MyClass(property: 10)
```

**Inheritance:**

Just like Java, Swift has single inheritance.

A subclass will inherit all of the superclass' behavior. We can override methods, but we must mark it with `override`

## The Basics

- `let` constants, `var` variables
- declare a specific type: `var str: String`
- you can't change a variable's type once declared
- you can put variables in strings like so: `print("Hello \(name)")

Numbers

- access number bounds, like so: `Int.min`, `Int.max`

- double > float b/c double has a 15-decimal-digit precision, whereas float has 6-decimal-digit precision

- you can format numbers to make them easier to read

  ```swift
  let paddedDouble = 000123.456
  let overOneMillion = 1_000_000.000_000_1
  ```

- use aliases for alternate names for any type: eg. `typealias SuperCoolIntWow = Int`

Tuples

- group multiple values into one compound, of the same of different types

  ```swift
  let http404Error = (404, "Not found")
  
  // Decomposing a tuple's contents into separate consonants/variables
  let (statusCode, statusMessage) = http404Error
  print ("the status message is: \(statusMessage)")
  
  // Or ignore parts you don't need
  let (thisStatusCode, _) = http404Error
  
  // Access parts of a tuple:
  print(http404Error.1); //prints status message
  
  let http200Status = (statusCode:200, description: "OK")
  print(http200Status.statusCode) // prints 200
  ```

Optionals

- optionals are used if you want there to be the possible of having a value, or being `nil`. Add `?` to the end of the type name.

  ```swift
  var hello: Int? // is nil
  
  // this is of type 'Int?'
  let convertedNumber = Int("1234") // 1234
  let convertedNumber2 = Int("Hellooo") // nil
  ```

- You can null out a variable if it's an optional:

  ```swift
  var address: String? = "123 Abc Street"
  address = nil
  ```

- `nil` isn't a pointer to an object, it's an absense of a type. _Any_ optional can be set to `nil`, not just object types.

If statements & forced unwrapping

- If you're sure that an optional contains a value, you can force unwrap the value appending `!` to the variable name

  ```swift
  if convertedNumber != nil {
      print(convertedNumber!)
  }
  ```

Optional binding

- optional binding: if the optional has a value, make that value avalable as a temporary constant or variable

  ```swift
  if let actualNumber = Int(possibleNumber) {
      print("\(possibleNumber) has an integer value of \(actualNumber)")
  } else {
      print("Couldn't convert \(possibleNumber) into an integer.")
  }
  
  // Slightly more advanced if-statement w/ this concept:
  if let num1 = Int("4"), num2 = Int("42") where num1 < num2 {
      print("\(num1) < \(num2)")
  }
  ```

Implicitly unwrapped optional

- If the program will definitely assign an optional a variable, you can use `!` appended to the type name in variable declarations to create an _implicitly unwrapped optional_

  - a runtime error will occur if it turns out that it doesn't have a value when you try to access it and it's `nil`
    - of course, you can avoid this error by checking it against `nil` just as you would a regular optional

- see a normal optional's vs. an implicitly unwrapped optional's behavior here:

  ```swift
  let possibleString: String? = "An optional string"
  let forcedString: String = possibleString! // requires exclamation mark
  
  let assumedString: String! = "An implicitly unwrapped optional string."
  let implicitString: String = assumedString // no need for exclamation mark
  ```

Error handling

- purpose: determine cause of failure, & perhaps propagate the error to another part of the program

  - Swift propagates errors out of their current scope until finally caught by a `catch`

  ```swift
  func throwingFunction() throws {
      // func may or may not throw an error
  }
  
  ...
  do {
      try throwingFunction()
      // no error thrown if continues to here
  } catch {
      // error was thrown
  }
  ```

- interestingly, you can do this:

  ```swift
  func makeASandwich() throws {
      // ...
  }
  
  do {
      try makeASandwich()
  } catch Error.MissingIngredients(let ingredients) {
      buyGroceries(ingredients)
  }
  ```

Assertions

- essentially, checking that a boolean condition asserts to true during runtime (for debugging only)

  ```swift
  let age = -3
  assert(age >= 0, "Age can't be negative.")
  assert(age >= 0)
  ```

## Basic Operators

- you can perform remainder (`%`) calculations on floating numbers

- range operators: `a..<b`, `a...b`

- using tuple assignment decomposition:

  ```swift
  // x = 1 and y = 2 now
  let (x, y) = (1, 2)
  ```

- unary minus operator `-` allows you to negate any variable (eg. `-three`)

- unary plus operator `+` doesn't do anything, but can be used for code symmetry

- check whether the identity of objects are equal with `!==` and `===`

Nil coalescing operator (`a ?? b`)

- it only unwraps optional type's `a` if it has a value, or takes on default value `b` if `a` is `nil`
  - `b` has to match the type stored in `a`, of course

Range operators

- closed range operator `a...b` goes from `a` to `b`, inclusive on both ends
- half-open range operator `a..<b` goes from `a` inclusive to `b`, not inclusive.

## Strings and Characters

- Strings are __value types not objects__ in Swift. So it's passed by value.
- strings are mutable if assigned to a variable (`var`), but immutable if assigned to a consonant (`let`)
- String API (a glimpse): `.isEmpty`, `.character`, `.count`,
- character type: `let c: Character "c"`
- String from char array is possible!

```swift
let chars: [Character] = ["p", "a", "n", "d", "a"]
print(String(chars))
```

- you can do whatever you want within strings, by using string interpolation: `print("5 + 4 is \(5+4))`
- special characters: `\0` null character, `\\` backslash, `\t` tab, `\n` line feed, `\r` carriage return, `\"` double quote, `\'` single quote
  - also, a Unicode scalar `\u{n}` where n is a 1-8 hexadecimal number equal to a valid Unicode code point

Extended grapheme clusters (Woah! Super cool)

- extended grapheme clusters allow you to represent complex characters as a 1 character value. Really useful for Korean, for instance:

```swift
let predecomposed: Character = "\u{D55C}" // 한
let decomposed: Character = "\u{1112}\u{1161}\u{11AB}" // ㅎ, ㅏ, ㄴ

// predecomposed = decomposed = 한
```

Accessing & modifying a String

- `.Index` can be used to access the each of the characters

  - but remember that different characters take varying amounts of memory (due to extended grapheme clusters), so to determine where a character is, you have to iterate from the start
  - `.characters.indices` tells you the range the of the indices

- `.startIndex` the position of the first character, `.endIndex` the position after the last character in the String

  - if dealing with an empty string, the `startIndex` & `endIndex` are equal

- accessing a character index outside of bounds -> runtime error, eg.

  ```swift
  // you can do
  let greeting = "Hello"
  greeting[greeting.startIndex] // G
  greeting[greeting.endIndex.predecessor()] // o
  print(greeting[greeting.startIndex.advancedBy(2)]) // l
  
  // runtime errors :(
  greeting[greeting.endIndex] // error
  greeting.endIndex.successor() // error
  ```

Modifying a string

- Inserting

```swift
var hi = "hello"
// insert a character
hi.insert("!", atIndex: hi.endIndex) // hi = "hello!"

// insert a string
hi.insertContentsOf(" What's up?".characters, at: hi.endIndex)
```

- Removing

```swift
var bye = "bye bye. bye."

// remove a character
bye.removeAtIndex(bye.endIndex.predecessor()) // bye = "bye bye. bye"

// remove a range of characters
let range = bye.endIndex.advancedBy(-5)..<bye.endIndex
bye.removeRange(range) // bye = "bye bye"
```

- Comparing strings: can use `!=` & `==`
- Prefix & suffixes: `.hasPrefix()`, `.hasSuffix()`
- Access a string's Unicode scalar representation by iterating over its `.unicodeScalars` property (returns the 21-bit value in a `UInt32` value)

## Collection Types

#### Arrays

- arrays: ordered collections of values

  - either declared as `Array<Type>` or `[Type]` (preferred)
  - empty array initializer: `[Int]()`

  ```swift
  // Create an array w/ a specific amount & a default value:
  var threeDoubles = [Double](count: 3, repeatedValue: 0.0)
  // [0.0, 0.0, 0.0]
  
  // can be written like this
  var secondDoubles: [Double] = [2.0, 2.0]
  // or like this
  var secondDoubles = [2.0, 2.0]
  var manyDoubles = threeDoubles + secondDoubles // [0.0, 0.0, 0.0, 2.0, 2.0]
  ```

Accessing & modifying arrays

- array API: `.count`, `.append()`, `.isEmpty`, `.removeLast()`

- access an element as usual (eg. `array[0]`)

- target & replace a range of array elements, even if the replacement is of a different size:

  ```swift
  shoppingList[4...6] = ["Peppers", "Ice cream"]
  // replaced "Chocolate", "Cheese", and "Butter" with two items.
  ```

- insert before the specified index: `.insert([Item], atIndex: [Index])`

- remove an element by index: `.removeAtIndex([Index])`

- if you need to iterate over each item, and need the index too, use `.enumerate()`

  ```swift
  for (index, value) in shoppingList.enumerate() {
      print("Item \(index + 1): \(value)")
  }
  ```

#### Sets

- sets: unordered collections of unique values

  - the type must be hashable for you to store it in a set (must conform to the `Hashable` & `Equatable` protocols if custom type)
  - created with `Set<Type>()`
  - make set empty by equating it to `[]`

  ```swift
  // Create initialized set w/ 3 items
  var flavors: Set<String> = ["Mint", "Chocolate", "Mint", "Berry"]
  
  // alternatively written as:
  var flavors: Set = ["Mint", "Chocolate", "Mint", "Berry"]
  ```

- set API: `.insert()`, `.count`, `.isEmpty`, `.remove()` (returns item or `nil`), `.removeAll()`, `.contains()`, `.intersect([set])`, `.exclusiveOr([set])`, `.union([set])`, `.subtract([set])`, `.isSubsetOf()`, `.isSupersetOf()`, `.isStrictSubsetOf()`, `.isStrictSupersetOf()`, `.isDisjointWith()`

Iterating over a set

- Since there's no defined ordering, if you want to iterate over a set in a specific order, you need to use `.sort()`, which'll return the set's element as a sorted array

  ```swift
  for flavor in flavors.sort() {
      print("I like \(flavor)")
  }
  ```

#### Dictionaries

- dictionaries: unordered collections of key-value associations

  - created with `Dictionary<KeyType, ValueType>` or `[KeyType: ValueType]` (preferred)
  - empty dictionary initializer example: `[Int: String]()`
  - or make dictionary empty `[:]` (if context has given us the types already)

  ```swift
  var couples: [String: String] = ["Mickey": "Minnie", "Romeo": "Juliet"]
  
  // or
  var couples = ["Mickey": "Minnie", "Romeo": "Juliet"]
  couples["Barack"] = "Michele"
  
  // Update the value b/c typo
  couples["Barack"] = "Michelle"
  // or
  couples.updateValue("Michelle", forKey: "Barack") // nil returned if doesn't exist already
  
  couples["Mickey"] = nil // removed that key-value pair
  ```

- dictionary API: `.count`, `.isEmpty`, `.removeValueForKey([key])`, `.keys`, `.values`

Iterating over a dictionary:

```swift
// need both key & value?
for (partner1, partner2) in couples {
    print("\(partner1) and \(partner2) are a couple.")
}

// Create an array from keys or values?
let firstPartners = [String](couples.keys)
```

- Since there's no defined ordering, if you want to iterate over a dictionary in a specific order, you need to use `.sort()` on the keys or values

## Control Flow

`for-in` loop

```swift
for character in "Panda 🐼".characters {
    print(character)
}

for _ in 1...5000 {
    print("Hi")
}
```

`while` loop

```swift
while condition {
    // statements
}
```

`repeat-while` loop

```swift
repeat {
    // statements
} while condition
```

`if`, `else if`, `else` statements

`switch` statement

- has no implicit fall-through; there's an implicit break at the end of each case.
- must be exhaustive (Doesn't necessarily mean that a `default` is needed)

```swift
switch some value to consider {
case value1:
    // response to value 1
case value2,
     1..<5:
    // response to value 2 or the rnage
default:
    // otherwise do something else
    break
}
```

- You can test multiple values in one switch statement by using tuples

  - if you want to match any value, you can use wildcard `_`

- You can use _value binding_ to bind values to temporary constants/variables within the case's body

- `where` can be used to check for more conditions

  ```swift
  let point = (1, 3)
  
  switch point {
  case (0, 0):
      print("origin")
  case (let y, 0):
      print("x-axis, at y-coordinate \(y)")
      fallthrough
  case (0, _) where _ % 2 == 0:
      print("y-axis")
  case (-2...2, -2..2):
      print("is inside the box")
  // No default needed because this is exhaustive
  case let (x, y):
      print ("is somewhere...")
  }
  ```

- Control transfer statements: `continue`, `break`, `fallthrough`, `return`, `throw`

Yay! You can label statements in Swift!

```swift
labelName: while condition {
    while condition2 {
        if (badThing) break labelName
    }
}
```

- `guard` executes statements based on the boolean value of an expression; it always has an `else` clause (thereby making it different from `if` statements)

```swift
func helloWorld(person: [String: String]) {
    guard let name = person["name"] else {
        print("How can you not have a name?")
        return
    }
}
```

#### Checking API availability

```swift
if #available(iOS9.1, OSX 10.10, *) {
    // use ios 9.1 APIs on iOS, and use OS X v10.10 APIs on OS X
    // * is required & specifies that on any other platform, the body of the if statement will use the minimum deployment target specified by your target
} else {
    print("You're out of luck!")
}
```

## Functions

- every function has a type (consists of the function's parameter types & return type)

- you can have functions w/ the same name as long as their overall signatures are diffferent

- you can write functions within functions to encapsulate the functionality

- functions without `-> [some type]` return `Void`, which is essentially an empty tuple

- return multiple values by putting them in a tuple & specifying the return type as a tuple

  - optional tuple return type: eg. `String?`, `(Int?, Int?)`, `(Int, Int)?` (whole tuple is optional)
  - an example way to deal w/ a function returning an optional value:

  ```swift
  if let bounds = minMax([8, -6, 2]) {
      print("min is \(bounds.min) and max is \(bounds.max)")
  }
  ```

- functionss have _external_ and _internal_ parameter names. If you specify an external parameter name, you must use it whenever you call that function

  - the interal parameter name is used within the function, not the external one
  - you can ommit the external one by specifying it as `_`, then you can make a call and not have to specify the name for that parameter, just as you can do  for the first

  ```swift
  func sayHello(to person: String, and anotherPerson: String) -> String {
      return "Hello \(person) and \(anotherPerson)."
  }
  
  print(sayHello(to: "Jack", and: "Jill"))
  ```

- you can specify default values for parameters

  - convention: place parameters with default values at the end of the parameter list

  ```swift
  func someFunction(parameterWithDefault: Int = 12) {
      //
  }
  
  someFunction(6)
  someFunction() // both are ok
  ```

- __variadic parameters__: accepts 0 or more values of a specified type, denoted by `...` appended to the end of a parameter's type name (eg. `numbers: Double..`)

  - values passed to a variadic parameter are made available w/i the function's body as an array of the declared type
  - _you can use at most one variadic parameter in a function_

- __in-out parameters__: mutable parameters (by default, parameters are constants), defined by writing `inout` at the start of the parameter definition

  - whatever happens in the function body will replace the original value
  - must prepend `&` to the variable name in the function call

  ```swift
  func swapTwoInts(inout a: Int, inout _ b: Int) {
      let tempA = a
      a = b
      b = tempA
  }
  
  var int1 = 3
  var int2 = 108
  swapTwoInts(&int1, &int2)
  ```

- function types: The previous function's function type would be `(Int, Int) -> Void` for instance

  - you can specify a constant/variable to be of a function type & assign a function to that variable
  - then you can use the variable as if you were using whatever function it was assigned
  - you can even use function types as parameters, or the return type

  ```swift
  var swapFunction: (Int, Int) -> Int = swapTwoInts
  var swapFunction = swapTwoInts // alternatively
  
  var anotherInt1 = 2
  var anotherInt2 = 012031
  
  swapFunction(anotherInt1, anotherInt2)
  ```

An example in which you'd return a function:

```swift
func chooseStepFunction(backwards: Bool) -> (Int) -> Int {
    return backwards ? stepBackward: stepForward
}

var currentValue = 3
let moveNearerToZero = chooseStepFunction(currentValue > 0)
```

Nested functions example

```swift
func chooseStepFunction(backwards: Bool) -> (Int) -> Int {
    func stepForward(input: Int) -> Int { return input + 1 }
    func stepBackward(input: Int) -> Int { return input - 1 }

    return backwards ? stepBackward : stepForward
}
```

## Closures

- __closure__: a self-contained block of functionality, which captures & stores references to any constants/variables from the context in which they're defined

- types of closures:

  1. global functions = named closures, which don't capture values
  2. nested functions = named closures, can capture values from enclosing function
  3. closure expressioned = unnamed, can capture values from surrounding context

- closure optimizations:

  - infers parameters & return types from context
  - implicit returns if single-expression
  - shorthand arg names
  - trailing closure syntax

- closure expressions: allow you to write inline closures

  - you can use constant/variable/variadic/`inout` params, but can't specify default values
  - `in` introduces the start of the closure's body
  - syntax:

  ```swift
  { (parameters) - > return type in
      statements
  }
  ```

These `backwards` functions all do the same things, in simpler ways

- If you want to omit the arguments, use `$0`, `$1`, ... for the arguments

```swift
// Way 1
func backwards(s1: String, _ s2: String) -> Bool {
    return s1 > s2
}
var reversed = names.sort(backwards)

// Way 2
var reversed = names.sort( { (s1: String, s2: String) -> Bool in return s1 > s2 } )

// Way 3: infer all things
var reversed = names.sort( { s1, s2 in return s1 > s2 } )

// Way 4: even infer the return
var reversed = names.sort( { s1, s2 in s1 > s2 } )

// Way 5: forget even having to list the args, just use them!
var reversed = names.sort( { $0 > $1 } )

// Way 6: Just use the string's operator function lol
var reversed = names.sort(>)
```

- __trailing closure__: a closure expression written outside & after the parentheses of the function call it supports
  - good for long closures

```swift
func someFunctionThatTakesAClosure(closure: () -> Void {
    // function body goes here
})

// You can opt to use it WITHOUT a trailing closure
someFunctionThatTakesAClosure({
    // closure's body here
})
// or WITH a trailing closure
someFunctionThatTakesAClosure() {
    // trailing closure body here,
}
```

Using it for our previous reverse variable

```swift
var reversed = names.sort { $0 > $1 }

// eg 2:
let strings = numbers.map {
    (number) -> String in
    var number = number
    var output = ""
    while number > 0 {
        output = digitNames[number % 10]! + output
        number /= 10
    }

    return output
}
```

- capturing values

  - closures can refer to & modify values of constants/variables w/i the body, even if the original scope that defined them no longer exist

- closures = reference type; so when you're assigning a function/closure to a constant/variable, you're keeping track of the reference to it, not the value

- nonescaping closures

  - a closure escapes a function when the closure is passed as an argument to the function, but is called after the function returns
  - write `@noescape` before the parameter name to indicate that the closure is not allowed to escape
    - allows you to refer to `self` implicitly w/i the closure
  - it's used when the closure has no use after it's usage in the function
  - _I have no clue what this even means... hopefully I'll figure it out in application?_

  ```swift
  class SomeClass {
      var x = 10
      func doSomething() {
          someFunctionWithEscapingClosure { self.x = 100 }
          someFunctionWithNonescapingClosure { x = 200 }
      }
  }
  
  let instance = someClass()
  instance.doSomething() // now instance.x is 200
  completionHandlers.first?() // someFunctionWithEscapingClosure is in completion handlers
  // now instance.x is 100
  ```

- Autoclosures: a closure automatically created to wrap an expresion passed as an arg to a function; it returns the value of the expression wrapped inside

  - allows you to delay evaluation; code inside isn't run until closure is called

  ```swift
  var customers = ["Chris", "Alex", "John", "Jane"] // 5 customers
  let customerProvider = { customers.removeAtIndex(0) } // still 5 customers; type: () -> String
  print ("Now serving \(customerProvider())!") // now 4 customers
  ```

```
// Autoclosures as a parameter, to do the same thing
func serveCustomer(@autoclosure customerProvider: () -> String) {
    print("Now serving \(customerProvider())!")
}

serveCustomer(customers.removeAtIndex(0))
​```
```

`@autoclosure` denotes an autoclosure that _cannot escape_. You can use `@autoclosure(escaping)` to have an autoclosure that can escape

## Enumerations

- enumeration (`enum`): a group of related values specified as `case`s, which can have any type for each case

```swift
enum Seasons {
    case Spring
    case Summer
    case Autumn
    case Winter
}
// or
enum Seasons {
    case Spring, Summer, Autumn, Winter
}
```

- once a value is declared as an enum type, you can change it by using `.[another case here]`

```swift
var season = Seasons.Summer
switch season {
case .Spring, .Fall:
    print("Mellow seasons are great.")
case .Summer:
    print("Too hot!")
case .Winter:
    print("Too cold!")
}
```

- you can store _associated values_ along with each enum case, to change what type it can take. They're accessible if you extract each value as a `let`/`var`

```swift
enum Barcode {
    case UPCA(Int, Int, Int, Int)
    case QRCode(String)
}
var skirtBarcode = Barcode.UPCA(8, 85909, 51226, 3)

switch skirtBarcode {
case .UPCA(let numberSystem, let manufacturer, let product, let check):
    print("UPC-A: \(numberSystem), \(manufacturer), \(product), \(check).")
case .QRCode(let productCode):
    print("QR code: \(productCode).")
}

// alternatively
switch skirtBarcode {
case let .UPCA(numberSystem, manufacturer, product, check):
    print("UPC-A: \(numberSystem), \(manufacturer), \(product), \(check).")
case let .QRCode(productCode):
    print("QR code: \(productCode).")
}


```

Raw values

- you can assign `enum` cases unique raw values (strings, characters, integer/floating point), all of the same type

```swift
enum Titles: String {
    case Lvl1 = "Noob"
    case Lvl2 = "Beginner"
    case Lvl3 = "Pro"
}

```

Implicitly-assigned raw values

- you can have Swift automatically set the values for you. Each case is one more than the last. If the first case doesn't have a value, it's set to `0`
- for strings, it will turn the name of the case into a String for it's `rawValue`
- initializing from a raw value: you can identify a case from it's raw value

```swift
enum Planet: Int {
    case Mercury = 1, Venus, Earth
}
print(Planet.Earth.rawValue) // 3
print(Planets(rawValue: 2).rawValue) // Can't do this, because it isn't unwrapped; if you want to unwrap it, do Planets(rawValue: 2)!.rawValue
print(Planets(rawValue: 20412)) // nil

enum Titles: String {
    case Noob, Beginner, Pro
}
print(Titles.Noob.rawValue) // "Noob"

```

Recursive enumerations

- __recursive enumeration__: an enumeration that has another instance of the enumeration as an associated value for 1/+ cases

  - indicated by writing `indirect` before the case, or the `enum` to make the whole `enum` a recursive

  ```swift
  // Recursive case
  enum ArithmeticExpression {
      case Number(Int)
      indirect case Addition(ArithmeticExpression, ArithmeticExpression)
      indirect case Multiplication(ArithmeticExpression, ArithmeticExpression)
  }
  
  // Recursive enum
  indirect enum ArithmeticExpression {
      case Number(Int)
      case Addition(ArithmeticExpression, ArithmeticExpression)
      case Multiplication(ArithmeticExpression, ArithmeticExpression)
  }
  
  // Example using this recursive enum:
  let five = ArithmeticExpression.Number(5)
  let four = ArithmeticExpression.Number(4)
  let sum = ArithmeticExpression.Addition(five, four)
  let product = ArithmeticExpression.Multiplication(sum, ArithmeticExpression.Number(2))
  
  // Another example
  func evaluate(expression: ArithmeticExpression) -> Int {
      switch expression {
      case let .Number(value):
          return value
      case let .Addition(left, right):
          return evaluate(left) + evaluate(right)
      case let .Multiplication(left, right):
          return evaluate(left) * evaluate(right)
      }
  }
  
  print(evaluate(product))
  
  ```

## Classes and Structures

- things __classes__ have that __structures__ don't
  - subclasses inherit the characteristics of its superclass
  - typecasting allows you to check & interpret the typeo f the class at instance
  - it has a deinitializer
  - due to reference counting, you can have more than 1 reference to a class instance
    - structures are value types (like `enum`s), and therefore passed by value, whereas classes are passed by reference
  - do two instances refer to the same instance behind the scene? Check with `!==` and `===`

```swift
struct Resolution {
    var width = 0
    var height = 0
}

class Video {
    var resolution = Resolution()
    var interlaced = false
    var name: String?
}

```

- things __structures__ have that __classes__ don't

  - an automatically-generated memberwise initializer

  ```swift
  let vga = Resolution(width: 640, height: 480)
  
  ```

  -

- you can modify a class/struct instance's properties even if you make it a constant, because the whole is still the same

- check whether 2 instances are equal in value with `==`

When to use structures vs. classes:

- the struct's primary purpose is to encapsulate a few relatively simple data values
- it's reasonable to expect the values to be copied rather than referenced
- any properties in the struct are value types
- the struct doesn't need to inherit properties/behavior from another eixsting type
- `String`, `Array`, `Dictionary` are examples of structures (`NSString`, `NSArry`, `NSDictionary` are classes)

## Properties

- `struct`, `class`, `enum` can have computed properties, which are properties associated with an instance
- only `class` & `struct` can have stored properties, which are properties associated with an instance
- __type properties__: properties that are associated w/ a type itself
- you can have property observers to watch changes

Stored properties

- __stored properties__ example:

  ```swift
  struct FixedLengthRange {
      var firstValue: Int
      let length: Int
  }
  
  ```

- __lazy stored property__: a property whose initial value isn't calculated until the first time it's used. Must be a variable

  - write `lazy` bfore the property declaration

```swift
// Takes a non-trivial amount of time to initialize. Imports data from an external file.
class DataImporter {
    var fileName = "data.txt"
}

class DataManager {
    lazy var importer = DataImporter()
    var data = [String]()
}

let manager = DataManager()
manager.data.append("Data 1")
manager.data.append("Data 2")
// importer instance hasn't been created for this manager instance yet
print(manager.importer.fileName) // now the importer is created & prints "data.txt"

```

- _caution:_ `lazy` properties may be initialized multiple times if it's accessed by multiple threads simultaneously

Computed properties

- __computed properties__: provide a getter & an optional setter to retrieve and set other properties

```swift
struct Rect {
    var origin = Point()
    var size = Size()
    var center: Point {
        get {
            let centerX = origin.x + (size.width / 2)
            let centerY = origin.y + (size.height / 2)
            return Point(x: centerX, y: centerY)
        }
        set (newCenter) {
            origin.x = newCenter.x - (size.width / 2)
            origin.y = newCenter.y - (size.height / 2)
        }

        // Shorthand for setter
        set {
            origin.x = newValue.x - (size.width / 2)
            origin.y = newValue.y - (size.height / 2)
        }
    }
    var square = Rect(origin: Point(x: 0.0, y: 0.0), size: Size(width: 10.0, height: 10.0))
    square.center = Point(x: 15.0, y: 15.0) // using the setter
}

```

- __read-only computed properties__: a computed property with a getter but no setter (no need to write get, you can just write `get`'s body in the `{ }`)

```swift
struct Cuboid {
    // ...
    var volume: Double {
        return width * height * depth
    }
}

```

Property observers

- __property observers__ are called whenever a property value is set, even if the new value is the same as the old.

  - can be used for all properties except `lazy` stored properties
  - you can add a property observer to an inherited property by overriding the property w/i the subclass
  - two types of observers: `willSet` (called before value is stored), `didSet` (called after value is stored)
    - if you don't declare a parameter for the value, a default paramter name of `newValue` is made available for `willSet`, or `oldValue` for `didSet`

  ```swift
  class StepCounter {
      var totalSteps: Int = 0 {
          willSet(newTotalSteps) {
              print("About to set totalSteps to \(newTotalSteps)")
          }
          didSet {
              if totalSteps > oldValue {
                  print("Added \(totalSteps - oldValue) steps")
              }
          }
      }
  }
  
  ```

Global and local variables

- global variables: variables defined outside of functions, methods, closures, or type contexts

Type properties

- instance properties: properties belonging to an instance of a particular type. A new set with every instance

- type properties: they're universal to all instances of that type; like static methods/variables.

  - must have a default value, since there's no initializer for a type to give it a value once an instance is created
  - by default, stored type properties are lazily initialized upon first access
  - type properties are queried at set on the type, not the instance of the type
  - declared with `static`, except if it's a computed type property, then you can use `class` to allow subclasses to override the implementation

  ```swift
  struct MyStructure {
      static var name = "Name"
  }
  
  enum MyEnum {
      static var yo = "Yo"
  }
  
  class MyClass {
      class var overrideableProp: Int {
          return 107
      }
  }
  
  ```

## Methods

- __method__: function associated with a particular type

Instance methods

- __instance method__: method belonging to an instance; same syntax as a function

  - has implicit access to all other instance methods & properties o fthat type
  - can only be called on a specific instance of the type it belongs to
  - here, `increment`, `incrementBy`, and `reset` are all instance methods

  ```swift
  class Counter {
      var count = 0
      func increment() {
          self.count += 1 // count += 1 works too
      }
      func incrementBy(amount: Int) {
          count += amount
      }
      func reset() {
          count = 0
      }
  }
  ```

- instance method parameters

  - given the first param name a local param name by default, and all of the others get both local & external param names. Of course, you can override.

Mutating methods

- declare a method with `mutating` if you want it to modify the properties of your `struct`/`enum`; classes do so by default

  - it's pretty powerful! you can assign a completely new instance to the `self` property, thereby replacing that instance from within

  ```swift
  enum TriStateSwitch {
      case Off, Low, High
      mutating func next() {
          switch self {
          case Off:
              self = Low
          case Low:
              self = High
          case High:
              self = Off
          }
      }
  }
  var ovenLight = TriStateSwitch.Low
  ovenLight.next() // now it's .High
  ```

Type methods

- essentially static methods that're denoted by `static` or `class`, which you call on a type, not an instance

## Subscripts

- you can define subscripts if you want to access things w/ square braces

  - written w/ the `subscript` keyword
  - __subscript overloading__: the subscript used (if many are implemented) will be determined based on type mathes
  - specify 1/+ params & a return type, like an instance method
  - are read or write only, or both
  - cannot use `inout` params or ddefault param values

  ```swift
  subscript(index: Int) -> Int {
      get {
          // return an appropriate subscript value here
      }
      set (newValue) {
          // perform a setting action
      }
  }
  
  // or make it readonly
  subscript(index: Int) -> Int {
      // return appropriate subscript value here
  }
  // declared like this, for instance
  let threeTimesTable = TimesTable(multiplier: 3)
  print("6 x 3 = \(threeTimesTable(6)") // 18
  ```

## Inheritance

- methods, properties, subscripts are inherited
- can add property observers to inherited properties to get notified on change
- classes don't inherit from a universal base class
- subclass syntax: `class Subclass: Superclass`
- declare overriding definitions with `override`
  - can still call the superclass's definition with `super` (eg. `super.someMethod()` or `super.someProperty`, or `super[someIndex]`)
  - must state the name & type of any property you're overriding
  - you can make inherited read-only properties into read-write, but not vice-versa
  - prevent overrides by declaring with `final`

Overriding property observers

- use overriding to add property observers to an inherited property w/ `willSet` or `didSet`

  - cannot provide both an overriding setter & a property observer

  ```swift
  class AutomaticCar: Car {
      override var currentSpeed:  Double {
          didSet {
              gear = Int(currentSpeed / 10.0) + 1
          }
      }
  }
  ```

- you cannot subclass a `final class`

## Initialization

- initializers declaraed with `init()`, and params if you choose.
  - swift automatically provides external param names
  - you can set a constant during initialization
  - structures & classes have a default `init()` initializer
  - you can call other initializers w/i an initializer (eg. `self.init`)

Class inheritance and initialization

- all of the class's stored properties need to be assigned an initial value in initialization. There are 2 ways of doing so:

  - __designated initializers__: using its super's initializers to initialize everything

    - must call a designated initializer from its immediate superclass (delegate up)
    - ensures all properties introduced by the class are initialized _before_ delegating up to its superclass

    ```swift
    init(parameters) {
        statements
    }
    ```

  - __convenience initializers__: using its other initializers to fully initialize everything

    - declared with keyword `convenience`
    - must call another initializer from the _same_ class (delegate across)
    - must ultimately call a designated initializer

    ```swift
    convenience init(parameters) {
        statements
    }
    ```

Initializer inheritance and overriding

- subclasses don't inherit superclass' initializers by default; onl
- if you write a subclass initializer that matches a superclass' designated initializer, you need to write `override` beforehand, even if what you're overriding it with is a convenience initializer
  - need to specify `override`, even if you're writing a custom `init()` initializer with no params

Automatic Initializer Inheritance

- if these conditions are met, superclass initializers are automatically inherited:
  - Subclass doesn't define any designated initializers
  - Subclass provides an implementation of _all_ of its superclass designated initializers (via inheriting them, or providing custom implementations of them)

Failable Initializers

- eg. when initialization params are invalid, absence of a resource, etc. you can make class/structure initialization fail
- declared by adding a question mark to the init name (ie. `init?()`)
  - cannot have a failable & nonfailable initializer w/ the same parameter types and names
- failable initializers create an optional value of the type it initializes, and it would `return nil` upon fail
- enumerations w/ raw values automatically get `init?(rawValue:)` initializers
- you can override a failable initializer w/ a nonfailable initializer, but not vice-versa

Propagation of Initialization Failure

- a failable class/struct/enum initializer can delegate across to another fialable initializer from the same class/struct/enum, or up to one of its superclass's failable initalizers

The `init!` Failable Initializer

- define a failable initializer that creates an implictly unwrapped optional instance of the appropriate type with `init!`
  - can delegate from `init?` to `init!` & vice-versa, and override with each other.
  - can delegate from `init` to `init!` but will trigger an assertion if `init!` fails

Required Initializers

- use `required` keyword before `init` if every class initializer must implement it
  - must also write the `required` keyword before subclass's overriding of a required initializer, you don't need to also specify `override`

Closures or functions in initialization

- `()` at the end of the closure means execute immediately, otherwise you're assigning the closure to the property

```swift
class MyClass {
    let myProperty: MyType = {
        // create a default value for myProperty here;
        // someValue must be of the same type as MyType
        return someValue
    }()
}
```

## Deinitialization

- __deinitializers__ are automatically called right before a class instance is deallocated
  - subclass inherit their superclass's deinitializer, which is called after a subclass's deinitializer, if there is one

```swift
deinit {
    // perform deinitialization
}
```

## Automatic Reference Counting (ARC)

- ARC tracks & manages the app's memory usage. If you choose, you can enable ARC to manage the app's memory better
  - reference counting _only applies to classes_
  - it makes a _strong reference_ to instances that're being referred to, which means that it's memory won't be deallocated
    - resolve strong references by defining some relationships b/t classes as week or unowned
- if obj1 refers to obj2, & obj2 refers to obj1, even if you make both of them `nil`, the memory won't be deallocated!! so you need to resolve their strong reference
- __weak reference__: a reference that doesn't keep a strong hold on the instance it refers to, allowing ARC to deallocate its memory
  - declared with `weak`
  - use case: when it's possible for that reference to have a missing value at some point in its lifetime
  - must be declared as variables
- __unowned reference__: similar to a weak reference, but the expectation is that the instance it refers to will always have a value
  - declared with `unowned` before the property/variable declaration
  - use case: a reference that _always_ has a value
  - eg. a credit card always has a customer, so you'd define `customer` as `unowned` to prevent a strong reference cycle

Unowned References and Implicitly Unwrapped Optional Properties

- if _both_ properties should have a value, and neither should be `nil`, you can combine an unowned property on one class, w/ an implicitly unwrapped optional property on the other

```swift
class Country {
    let name: String
    var capitalCity: City!
    init(name: String, capitalName: String) {
        self.name = name
        self.capitalCity = City(name: capitalName, country: self)
    }
}
class City {
    let name: String
    unowned let country: Country
    init(name: String, country: Country) {
        self.name = name
        self.country = country
    }
}
```

Strong Reference Cycles for Closures

- can create a strong reference if you assign a closure to a property of an instance and the body of the closure captures the instance (just has to access `self.someProperty` or `self.someMethod`)

  - a __closure capture list__ defines the rules to use when capturing 1/+ reference types w/i a closure's body
    - placed before the closure's parameter list & return type (if provided), or at the very start of the closure

  ```swift
  lazy var someClosure(Int, String) -> String = {
      [unowned self, weak delegate = self.delegate!] (index: Int, stringToProcess: String) -> String in
      // closure body here
  }
  lazy var someClosure() -> String = {
      [unowned self, weak delegate = self.delegate!] in
      // closure body here
  }
  ```

- define a capture as unowned if the closure & instance it captures will always refer to each other, & will always be deallocated at the same time

- define a capture as weak when it may become `nil`; check for its existence within the closure

## Optional Chaining

- __optional chaining__: process of querying/calling properties/methods/subscripts on an optional that may be `nil`

  - declared by putting a `?` after what you want to access, or after the method's parentheses
  - you can use `?` to access an optional property, and it will still be optional
  - multiple queries can be made, and if the original optional is `nil`, all of the methods/properties/subscripts you call will just return `nil`

  ```swift
  if let roomCount = Person().residence?.numberOfRooms {
      print("This person's residence has \(roomCount) room(s).")
  } else {
      print("Unable to retrieve the number of rooms") // prints this b/c residence is nil
  }
  ```

## Error Handling

- create enums that extend `ErrorType` for your own error types (`enum CustomError: ErrorType`)
- `throw` an error (`throw customError.case(optional, params)`)

Propagating Errors Using Throwing Functions

- __throwing function__: a function that throws an error w/i it's scope, thereby propagating it to the scope in which the function is called

  - syntax: `func canThrowErrors() throws -> String`
  - an alternative is to handle the error within the function, with `do`, `try`, and `catch`

  ```swift
  do {
      try [expression]
      [statements]
  } catch [pattern 1] {
      [statements]
  } catch [pattern 2] where [condition] {
      [statements]
  }
  ```

- You don't need to handle every error in `catch` blocks, but if you don't, the error will keep propagating to surrounding scopes, until it's eventually caught, or crashes your app

- handle an error by converting it to an optional value with `try?`

```swift
let x = try? someThrowingFunction()
```

- _disable error propagation_ with `try!` if you know that an error won't be thrown (eg. accessing a resource packaged with the app); if it is in fact thrown, you'll get a runtime error

Specifying Cleanup Actions

- don't forget `defer` blocks, which you can place anywhere, but will be called right before the code execution leaves the current block of code
  - cannot include code that'd transfer control out of the `defer` block, like `break` or `return`, or by throwing an error
  - deferred actions are operated in the _reverse order_ of how the defer blocks are specified

## Type Casting

- __type casting__: check instance type or treat it as a different superclass/subclass along its own class hierarchy, using `is` and `as` operators
  - eg. you can put many subclasses that extend from a base class in an array together, but they're all considered type baseclass until you downcast them by checking their type using `is`
    - eg. `if item is Movie`

Downcasting

- use `as?` or `as!` to downcast to the subclass, if you think an instance is a subclass behind the scenes
  - `as?` returns an optional value of the type you're downcasting to
  - `as!` downcasts & unwraps, and will cause a runtime error if you're wrong

Type Casting for Any and AnyObject

- `AnyObject` can represent an instance of any class type

  - for example, if you had an array like `[AnyObject]`, you could use `as!` to downcast each item, if you know all of the elements are the same type

  ```swift
  let someObjects: [AnyObject] = [ ... ]
  for movie in someObjects as! [Movie] { ... }
  ```

- `Any` can represent an instance of any type, including function types (eg. `[Any]()` for an array of anything)

  - must downcast each of the elements if you want to do anything w/ them except just print lol

## Nested Types

- __nested type__: nesting supporting enums, classes, & structures w/i the definition of  the type it supports
  - You'd access the `Suit` enum in the example with `Card.Suit` outside of its context

```swift
struct Card {
    enum Suit: Character {
        case Spades = "♠", Hearts = "♡", Diamonds = "♢", Clubs = "♣”
    }
    enum Rank: Int {
        case Two = 2, Three, ...
    }
    let rank: Rank, suit: Suit
    var description = "the card is \(rank.rawValue) of suit \(suit.rawValue)"
}
let aceOfSpaces = Card(rank: .Ace, suit: .Spades)
```

## Extensions

- __extensions__ add new functionality to an existing `class`, `struct`, `enum`, or `protocol` type––even if you don't have access to the source code (this is called _retroactive modeling_)!!
  - declared with `extension`
  - you cannot override existing functionality; only extend.
  - you can't add stored properties, or add property observers to existing properties
- abilities:
  - add computed instance properties & computed type properties
  - define instance methods & types
  - add new initializers
  - define subscripts
  - define & use new nested types
  - make an existing type conform to a protocol
    - all instances of the type will get the new functionality, even if created before the extension was defined
- you can add convenience initializers, but not designated initializers or deinitializers

```swift
extension SomeExistingType: SomeProtocol, AnotherProtocol {
    // implementation of protocol requirements goes here
}
```

## Protocols

- __protocol__: a blueprint of methods, properties, & other requirements for classes, structures, or enumerations to provide an actual implementation of

  - very similar to Java's interfaces
  - are listed along with the superclass in class declarations
  - can inherit protocols, so anything implementing the protocol must also implement the protocols it inherits

- property requirements

  - doesn't specify whether it's stored/computed, only the property name & type, and whether it's gettable, or gettable and settable

- method requirements

  - default values can't be specified

- mutating method requirements

  - you can just specify it as `mutating` in the protocol, no need to also write `mutating` in its implementation for classes (only `enum` & `struct`)

- initializer requirements

  - you can implement these requirements as either a designated or convenience initializer, but with either, you must mark it with `required`
    - you can leave out `required` if the class is `final`, since it can't be subclassed
    - if a subclass overrides a designated initializer from some superclass, & implements a matching initializer requirement from some protocol, you need to mark that overriding implementation with `required` _and_ `override`
  - can be failable

- optional protocol requirements

  - requirements that are optional to implement
  - declared with `optional` before it. Must have keyword `@objc` before `protocol` if you want to make one of its requirements optional
    - `@objc` protocols can only be adopted by classes inheriting from Objective-C classes, or other `@objc` classes. Can't be adopted by `struct` or `enum`

- protocols as types

  - use as a param or return type in a function, method, or initializer
  - use as type of a constant, variable, or property
  - use as type in an array, dictionary, etc.

- you can use `is` and `as` to check for protocol conformance, & to cast to a specific protocol

  ```swift
  protocol ProtocolOfHell {
      init(someParameter: Int)
      var mustBeSettable: Int { get set }
      var doesNotNeedToBeSettable: Int { get }
      static var someTypeProperty: Int { get set }
      func random() -> Double
      static func someTypeMethod()
      mutating func toggle()
  }
  
  class SomeClass: ProtocolOfHell {
      // implement all things
  }
  
  @objc protocol ObjCProtocol {
      optional var hello: Int { get }
  }
  class Something: NSObject, ObjCProtocol {
      let hello = 5
  }
  ```

Delegation

- __delegation__: a design pattern allowing a class/structure to delegate part of its responsibilities to an instance of another type
  - define a protocol that implements those responsibilities, and have another type implement it, then use that type

```swift
protocol DiceGame {
    func play()
}
protocol DiceGameDelegate {
    func gameDidStart(game: DiceGame)
    // ..
}
class MyGame: DiceGame {
    var delegate = DiceGameDelegate?
    func play() {
        delegate?.gameDidStart(self)
        delegate?.gameDidEnd(self)
    }
}
```

- if a type already conforms to all of the requirements of a protocol, but hasn't stated that it adopts that protocol, you can make it do so with an empty extension

```swift
extension Hamster: TextRepresentation {}
```

Class-Only Protocols

- limit the protocol to only being implemented by classes, by adding the `class` keyword to its inheritance list; must put `class` first in the list
  - usecase: when protocol assumes reference type

```swift
protocol SomeClassOnlyProtocol: class, SomeInheritedProtocol {
}
```

Protocol Composition

- require something to conform to multiple protocols at once using the form `protocol<Protocol1, Protocol2, ...>`

Protocol Extensions

- you can extend protocols to provide more properties/types/etc.
- you can extend protocols to provide default implementations to any method/property using that protocol
- you can add constraints to protocol extensions using the `where` clause
  - conforming types must satisfy these constraints before the properties & methods of the extension are available to it

```swift
// Can apply only to collections whose elements conform to the TextRepresentable protocol
extension SomeExistingProtocol where Generator.Element: TextRepresentable {
    func moreStuff() -> String {
        return "hello" + " world"
    }
}
```

## Generics

Generic functions

- type parameter: the placeholder type, written in angled brackets

```swift
func functionName<T>(inout a: T, inout _ b: T) {
    // Do something
}
var string = "hey"
functionName(&hey, &hey)
```

Generic types

```swift
struct Stack<Element> {
    //
}
var stack = Stack<String>()

// Extending a generic type
extension Stack {
    var top: Element {
        return items.isEmpty ? nil : items[items.count - 1]
    }
}
```

Type constraints

- make sure that the type parameter has to inherit from a specific class, or conform to a protocol or protocol composition

```swift
func someFunction<T: SomeClass, U: SomeProtocol>(someT: T, someU: U) {
    //
}
```

Associated types

- __associated type__: a placeholder name to a type that's used as part of the protocol; it's essentially an alias
  - declared with `associatedtype` keyword

```swift
protocol Container {
    associatedtype ItemType
    mutating func append(item: ItemType)
    var count: Int {get}
    subscript(i: Int) -> ItemType {get}
}
```

Where clauses

- can define requirements for associated types and type

```swift
func someExample<
    C1: Container, C2: FancyContainer
    where C1.ItemType == C2.ItemType, C1.ItemType: Equatable>
    (someContainer: C1, _ anotherContainer: C2) -> Bool {

    // do both containers have the same amount of items?
    if someContainer.count != anotherContainer.count {
        return true
    }
    if someContainer[0] == anotherContainer[0] {
        return true
    }
    return false
}
```

## Access Control

- __access control__ allows you to restrict access to parts of your code from code in other source files & modues.
  - can hide implementation details & specify an interface to access the code
  - can specify access levels to individual types (ie. classes, structs, enums), as well as the properties, methods, initializers, & subscripts they contain
  - can restrict protocols, global constants, variables, functions to certain contexts
- __module__: a single unit of code distribution (eg. framework/app that can be imported by another module w/ `import`)
  - each build target (eg. app bundle, framework) is treated as a separate module
- __source file__: single Swift source code file w/i a module

Access levels

- _public access_: used w/i any source file from their defining module, or which imports it
  - declared with `public`
  - use case: the public interface to a framework (API - application programming interface)
  - it's properties, functions, etc. will be `internal`, not `public`, unlike the other access levels
- _internal access_: can only be used w/i their defining module
  - declared with `internal`
  - use case: an app/framework's internal structure
- _private access_: can only be used w/i it's defining source file
  - declared with `private`
  - use case: hiding implementation details of a functionality

Guiding principle of access levels

> No entity can be defined in terms of another entity that has a lower (ie. more restrictive) access level

- Default access level: _internal_
  - if you mark an import declaration for a product module w/ `@testable`, unit test targets can access any internal entity regardless of the explicit access level specified
- you can specify an explicit access level for a type when you define it
- if there're multiple access levels in the tuple, or function parameter/return types, the whole takes the access level of the most restrictive one
- `enum` & access levels:
  - each case gets the access level of the enum, and you can't specify access levels for individual cases
  - the types used w/i the enum must have an access level more liberal or equal to the `enum`
- nested types & access levels:
  - nested types w/i a private type are private
  - nested types w/i a public or internal type are internal (unless you explicitly declare them public)
- subclassing
  - subclasses can't be less restrictive than their superclasses
  - you can only override visible superclass members
- constants, variables, properties, and subscripts
  - can't be more public than its types
  - if you use a private type, you must mark whatever is using it as `private` too

Getters and setters

- they inherit the access level of whatever they belong to, but you can give it a lower access level to a setter than its getter
  - declared with `private(set)` or `internal(set)` before `var` or `subscript`

```swift
struct TrackedString {
    private(set) var numberOfEdits = 0 // only settable w/i the same source file
    var value: String = "" {
        didSet {
            numberOfEdits += 1
        }
    }
}
```

Initializers

- can be given an access level less/equal to the type they initialize, unless they're `required`, obv.
- if you want a default initializer () in a `public` type to be public too, you must create the argument-less initializer yourself and make it public

Protocols

- its defined requirements have the same access level as the protocol was declared in its defining file, and cannot be changed
- you can have a type conform to a protocol with a lower access level than the type

Extensions

- extensions get the same access level as what it extends, but you can make an extension `private` for instance, to have everything that's added be `private`
  - within the extension, you can override the private and give particularm members a different access level
  - you can't do this if you're just extending to add protocol conformance, it just goes with the access level of what it extends

Generics

- access level is the minimum of the access level of the generic type/function itself and the access level of its type constaints

Type aliases

- type aliases are treated as distinct types, and a can have access levels lower or equal to the type it aliases

## Advanced Operators

- operators don't overflow by default; it's reported as an error; if you want to allow overflow, use `&` before the operator (eg. `&+`)
- you can make your own operators w/ custom precedence & associated values... wtf? talk about insane customizability
  - you can't overload `=` nor the ternary conditional operator

Operator Functions

- You can overload operators to determine how to add particular types, and these function definitions are defined globally

```swift
struct2D {
    var x = 0.0, y = 0.0
}
func + (left: Vector2D, right: Vector2D) -> Vector2D {
    return Vector2D(x: left.x + right.x, y: left.y + right.y)
}
```

Prefix and postfix operators

```swift
prefix func - (vector: Vector2D) -> Vector2D {
    return Vector2D(x: -vector.x, y: -vector.y)
}
```

Compound assignment operators

- mark its left input param w/ `inout` since it's modified directly

```swift
func += (inout left: Vector2D, right: Vector2D) {
    left = left + right
}
original += vectorToAdd
```

Equivalence operators

- have a way to determine whether your custom classes & structures are equal

```swift
func == (left: Vector2D, right: Vector2D) -> Bool {
    return (left.x == right.x) && (left.y == right.y)
}
func != (left: Vector2D, right: Vector2D) -> Bool {
    return !(left == right)
}
```

Custom operators

- are global. Must declare using the `operator` keyword & mark it with a `prefix`, `infix`, or `postfix` modifier
  - you can later give meaning to it through overloading

```swift
prefix operator +++ {}
```

Precedence and associaitivity for custom infix operators

- postfix operators are applied before the prefix operator if you're applying them both to the same operand
- `associativity`'s possible values:
  - `left` associate to the left if written to other left-associative operators of the same precedence
  - `right` associate to the right "..."
  - `none` (by default) can't be written next to other operators w/ the same precedence
- `precedence`
  - `100` by default

```swift
infix operator +- { associativity left precedence 140 }
```

## Language Reference

- arrow `->` means "can consist of"
- `|` denotes an alternative grammar production

#### Lexical structure

- keywords mentioned, which I haven't learned about yet:
  - `dynamicType`, `Self`, `rethrows`, `#column`, `#file`, `#line`, `#function`, `#available`, `#else`, `#elseif`, `#endif`, `#if`, `#selector`
- (notable) keywords reserved in particular contexts: `left`, `right`, `none`, `precedence`, `required`, `unowned`, `weak`, `willSet`

#### Types

- if a function type has more than 1 `->`, they're grouped from right to left
  - eg. `Int -> Int -> Int` would become `Int -> (Int -> Int)` (function takes an `Int`, and returns another function that takes an `Int` and returns and `Int`)
- create multidimensional arrays by nesting the square brackets: eg. `[[Int]]`, `[[[Int]]]`

#### Metatype Type

- metatype of a class/structure/enumeration type is the name of type is the name of the type followed by `.Type`. Protocol metatype is protocol followed by `.Protocol`
  - eg. `SomeClass.Type`, `SomeProtocol.Protocol`
- get the runtimetype of an instance with `.dynamicType` after the instance name
  - eg. `someInstance.dynamicType`
  - are the runtime & compile types the same? check with `instance.dynamicType === instance.self`

```swift
// Use the type
let metatype: AnotherSubclass.Type = AnotherSubclass.self
let anotherInstance = metatype.init(string: "some string")
```

## Expressions

#### Literal expressions

- ooo.. this is probably good for debugging: you can use these literals
  - `#file` (returns String of name of file it appears)
  - `#line` (Int, line number it appears)
  - `#column` (Int, column number it appears)
  - `#function` (String, name of declaration it appears)

```swift
func myFunctionName(string: String = #function) {
    print(string)
}
myFunctionName() // prints "myFunctionName()"
```

## Statements

- you can label statements with a statement label followed immediately by `:`, allowing you to be explicit about changing control flow in a loop/switch with `break` & `continue` statements
  - must be unique names

#### Build configuration statements

Conditionally compile code based on build configuration

```swift
#if [build configuration here without brackets]
statements
#elseif ...
statements
#else ...
statements
#endif
```

Get the build configuration using `os()`, `arch()`, or `swift()`... eh there's more to this.

#### Availability condition

Check availability of APIs at runtime, based on platforms arguments

```swift
if #available([platform name] [version], [...], *) {
    // statements to execute if APIs are available
} else {
    // fallback
}
```

## Declarations

- top-level code: code available to everything in the module (default behavior, aka `internal`)

#### Import declaration

There're import kinds!!, this is the import structure:

```swift
import [import kind] [module].[symbol name]
```

- import kinds include: `typealias`, `struct`, `class`, `enum`, `protocol`, `var`, `func`

#### `inout` parameters

- it's unpredictable when the function using the `inout` parameter will return, so your changes to the original value is overwritten by the value of the copy
  - likewise, there's no copy-out @ end of closures or nested functions, so if a closure is called after the function returns, any changes the closure makes to the `inout` parameters don't get copied back to the original

#### Throwing functions & methods

- just changing whether a function throws or not does not override the superclass's function; you need to change the function type too
- a function/method can use `rethrow` to throw and error if and only if one if its function params throws an eror
  - cannot throw any errors of its own, thus can't contain a `throw` statement

```swift
func functionWithCallback(callback: () throws -> Int) rethrows {
    try callback()
}
```

## Attributes

- __attributes__ provide more info about a declaration/type

```swift
@[attribute name]
// or
@[attribute name]([attribute arguments])
```

Declaration attributes:

- `autoclosure`: delays evaluation of expression by wrapping it in an argument-less closure
  - also implies `noescape`, unless passed optional argument `escaping`
- `available`: indicate that something only is intended for a certain platform/operating system variation; takes args with the OS/platform, or arg of `*` to indicate all platforms
  - alternatively, you can say `unavailable` for ones it isn't available on
- `introduced=[version number]`: say when the declaration was first introduced

More:

- `noescape`, `nonobjc`, `noreturn`, `NSApplicationMain`, `NSCopying`, `NSManaged`, `testable`, `UIApplicationMain`, `warn_unused_result`

Type attributes (only appliable to types):

- `convention`: indicate its calling conventions, `noreturn`

## Patterns

- __pattern__: the structure of a single value/composite value
  - eg. `_` is the wildcard pattern

