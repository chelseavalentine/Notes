## The Basics

* `let` constants, `var` variables
* declare a specific type: `var str: String`
* you can't change a variable's type once declared
* you can put variables in strings like so: `print("Hello \(name)")

Numbers

* access number bounds, like so: `Int.min`, `Int.max`
* double > float b/c double has a 15-decimal-digit precision, whereas float has 6-decimal-digit precision
* you can format numbers to make them easier to read
    ```swift
    let paddedDouble = 000123.456
    let overOneMillion = 1_000_000.000_000_1
    ```
* use aliases for alternate names for any type: eg. `typealias SuperCoolIntWow = Int`

Tuples

* group multiple values into one compound, of the same of different types
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

* optionals are used if you want there to be the possible of having a value, or being `nil`. Add `?` to the end of the type name.
    ```swift
    var hello: Int? // is nil

    // this is of type 'Int?'
    let convertedNumber = Int("1234") // 1234
    let convertedNumber2 = Int("Hellooo") // nil
    ```

* You can null out a variable if it's an optional:
    ```swift
    var address: String? = "123 Abc Street"
    address = nil
    ```
* `nil` isn't a pointer to an object, it's an absense of a type. _Any_ optional can be set to `nil`, not just object types.

If statements & forced unwrapping

* If you're sure that an optional contains a value, you can force unwrap the value appending `!` to the variable name
    ```swift
    if convertedNumber != nil {
        print(convertedNumber!)
    }
    ```

Optional binding

* optional binding: if the optional has a value, make that value avalable as a temporary constant or variable
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

* If the program will definitely assign an optional a variable, you can use `!` appended to the type name in variable declarations to create an _implicitly unwrapped optional_
    * a runtime error will occur if it turns out that it doesn't have a value when you try to access it and it's `nil`
        *  of course, you can avoid this error by checking it against `nil` just as you would a regular optional

* see a normal optional's vs. an implicitly unwrapped optional's behavior here:
    ```swift
    let possibleString: String? = "An optional string"
    let forcedString: String = possibleString! // requires exclamation mark

    let assumedString: String! = "An implicitly unwrapped optional string."
    let implicitString: String = assumedString // no need for exclamation mark
    ```

Error handling

* purpose: determine cause of failure, & perhaps propagate the error to another part of the program
    * Swift propagates errors out of their current scope until finally caught by a `catch`
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

* interestingly, you can do this:
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

* essentially, checking that a boolean condition asserts to true during runtime (for debugging only)
    ```swift
    let age = -3
    assert(age >= 0, "Age can't be negative.")
    assert(age >= 0)
    ```


## Basic Operators

* you can perform remainder (`%`) calculations on floating numbers
* range operators: `a..<b`, `a...b`
* using tuple assignment decomposition:
    ```swift
    // x = 1 and y = 2 now
    let (x, y) = (1, 2)
    ```
* unary minus operator `-` allows you to negate any variable (eg. `-three`)
* unary plus operator `+` doesn't do anything, but can be used for code symmetry
* check whether the identity of objects are equal with `!==` and `===`

Nil coalescing operator (`a ?? b`)

* it only unwraps optional type's `a` if it has a value, or takes on default value `b` if `a` is `nil`
    - `b` has to match the type stored in `a`, of course

Range operators

* closed range operator `a...b` goes from `a` to `b`, inclusive on both ends
* half-open range operator `a..<b` goes from `a` inclusive to `b`, not inclusive.


## Strings and Characters

* Strings are __value types not objects__ in Swift. So it's passed by value.
* strings are mutable if assigned to a variable (`var`), but immutable if assigned to a consonant (`let`)
* String API (a glimpse): `.isEmpty`, `.character`, `.count`,

* character type: `let c: Character "c"`

* String from char array is possible!
```swift
let chars: [Character] = ["p", "a", "n", "d", "a"]
print(String(chars))
```

* you can do whatever you want within strings, by using string interpolation: `print("5 + 4 is \(5+4))`
* special characters: `\0` null character, `\\` backslash, `\t` tab, `\n` line feed, `\r` carriage return, `\"` double quote, `\'` single quote
    - also, a Unicode scalar `\u{n}` where n is a 1-8 hexadecimal number equal to a valid Unicode code point

Extended grapheme clusters (Woah! Super cool)

* extended grapheme clusters allow you to represent complex characters as a 1 character value. Really useful for Korean, for instance:

```swift
let predecomposed: Character = "\u{D55C}" // 한
let decomposed: Character = "\u{1112}\u{1161}\u{11AB}" // ㅎ, ㅏ, ㄴ

// predecomposed = decomposed = 한
```

Accessing & modifying a String

* `.Index` can be used to access the each of the characters
    - but remember that different characters take varying amounts of memory (due to extended grapheme clusters), so to determine where a character is, you have to iterate from the start
    - `.characters.indices` tells you the range the of the indices
* `.startIndex` the position of the first character, `.endIndex` the position after the last character in the String
    * if dealing with an empty string, the `startIndex` & `endIndex` are equal
* accessing a character index outside of bounds -> runtime error, eg.
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

* Inserting
```swift
var hi = "hello"
// insert a character
hi.insert("!", atIndex: hi.endIndex) // hi = "hello!"

// insert a string
hi.insertContentsOf(" What's up?".characters, at: hi.endIndex)
```

* Removing
```swift
var bye = "bye bye. bye."

// remove a character
bye.removeAtIndex(bye.endIndex.predecessor()) // bye = "bye bye. bye"

// remove a range of characters
let range = bye.endIndex.advancedBy(-5)..<bye.endIndex
bye.removeRange(range) // bye = "bye bye"
```

* Comparing strings: can use `!=` & `==`
* Prefix & suffixes: `.hasPrefix()`, `.hasSuffix()`
* Access a string's Unicode scalar representation by iterating over its `.unicodeScalars` property (returns the 21-bit value in a `UInt32` value)

## Collection Types

#### Arrays
* arrays: ordered collections of values
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

* array API: `.count`, `.append()`, `.isEmpty`, `.removeLast()`
* access an element as usual (eg. `array[0]`)
* target & replace a range of array elements, even if the replacement is of a different size:
    ```swift
    shoppingList[4...6] = ["Peppers", "Ice cream"]
    // replaced "Chocolate", "Cheese", and "Butter" with two items.
    ```
* insert before the specified index: `.insert([Item], atIndex: [Index])`
* remove an element by index: `.removeAtIndex([Index])`
* if you need to iterate over each item, and need the index too, use `.enumerate()`
    ```swift
    for (index, value) in shoppingList.enumerate() {
        print("Item \(index + 1): \(value)")
    }
    ```

#### Sets

* sets: unordered collections of unique values
    - the type must be hashable for you to store it in a set (must conform to the `Hashable` & `Equatable` protocols if custom type)
    - created with `Set<Type>()`
    - make set empty by equating it to `[]`
    ```swift
    // Create initialized set w/ 3 items
    var flavors: Set<String> = ["Mint", "Chocolate", "Mint", "Berry"]

    // alternatively written as:
    var flavors: Set = ["Mint", "Chocolate", "Mint", "Berry"]
    ```
* set API: `.insert()`, `.count`, `.isEmpty`, `.remove()` (returns item or `nil`), `.removeAll()`, `.contains()`, `.intersect([set])`, `.exclusiveOr([set])`, `.union([set])`, `.subtract([set])`, `.isSubsetOf()`, `.isSupersetOf()`, `.isStrictSubsetOf()`, `.isStrictSupersetOf()`, `.isDisjointWith()`

Iterating over a set

* Since there's no defined ordering, if you want to iterate over a set in a specific order, you need to use `.sort()`, which'll return the set's element as a sorted array
    ```swift
    for flavor in flavors.sort() {
        print("I like \(flavor)")
    }
    ```


#### Dictionaries

* dictionaries: unordered collections of key-value associations
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

* dictionary API: `.count`, `.isEmpty`, `.removeValueForKey([key])`, `.keys`, `.values`

Iterating over a dictionary:

```swift
// need both key & value?
for (partner1, partner2) in couples {
    print("\(partner1) and \(partner2) are a couple.")
}

// Create an array from keys or values?
let firstPartners = [String](couples.keys)
```

* Since there's no defined ordering, if you want to iterate over a dictionary in a specific order, you need to use `.sort()` on the keys or values


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
 * has no implicit fall-through; there's an implicit break at the end of each case.
 * must be exhaustive (Doesn't necessarily mean that a `default` is needed)

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

* You can test multiple values in one switch statement by using tuples
    - if you want to match any value, you can use wildcard `_`
* You can use _value binding_ to bind values to temporary constants/variables within the case's body
* `where` can be used to check for more conditions

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

* Control transfer statements: `continue`, `break`, `fallthrough`, `return`, `throw`

Yay! You can label statements in Swift!

```swift
labelName: while condition {
    while condition2 {
        if (badThing) break labelName
    }
}
```

* `guard` executes statements based on the boolean value of an expression; it always has an `else` clause (thereby making it different from `if` statements)

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

[Prev page](README.md) • [Next Page](Swift_2.md)
