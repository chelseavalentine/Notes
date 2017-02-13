## Functions

* every function has a type (consists of the function's parameter types & return type)
* you can have functions w/ the same name as long as their overall signatures are diffferent
* you can write functions within functions to encapsulate the functionality
* functions without `-> [some type]` return `Void`, which is essentially an empty tuple
* return multiple values by putting them in a tuple & specifying the return type as a tuple
    - optional tuple return type: eg. `String?`, `(Int?, Int?)`, `(Int, Int)?` (whole tuple is optional)
    - an example way to deal w/ a function returning an optional value:
    ```swift
    if let bounds = minMax([8, -6, 2]) {
        print("min is \(bounds.min) and max is \(bounds.max)")
    }
    ```

* functionss have _external_ and _internal_ parameter names. If you specify an external parameter name, you must use it whenever you call that function
    - the interal parameter name is used within the function, not the external one
    - you can ommit the external one by specifying it as `_`, then you can make a call and not have to specify the name for that parameter, just as you can do  for the first
    ```swift
    func sayHello(to person: String, and anotherPerson: String) -> String {
        return "Hello \(person) and \(anotherPerson)."
    }

    print(sayHello(to: "Jack", and: "Jill"))
    ```

* you can specify default values for parameters
    - convention: place parameters with default values at the end of the parameter list
    ```swift
    func someFunction(parameterWithDefault: Int = 12) {
        //
    }

    someFunction(6)
    someFunction() // both are ok
    ```

* __variadic parameters__: accepts 0 or more values of a specified type, denoted by `...` appended to the end of a parameter's type name (eg. `numbers: Double..`)
    - values passed to a variadic parameter are made available w/i the function's body as an array of the declared type
    - _you can use at most one variadic parameter in a function_

* __in-out parameters__: mutable parameters (by default, parameters are constants), defined by writing `inout` at the start of the parameter definition
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

* function types: The previous function's function type would be `(Int, Int) -> Void` for instance
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

* __closure__: a self-contained block of functionality, which captures & stores references to any constants/variables from the context in which they're defined
* types of closures:
    1. global functions = named closures, which don't capture values
    2. nested functions = named closures, can capture values from enclosing function
    3. closure expressioned = unnamed, can capture values from surrounding context
* closure optimizations:
    - infers parameters & return types from context
    - implicit returns if single-expression
    - shorthand arg names
    - trailing closure syntax
* closure expressions: allow you to write inline closures
    - you can use constant/variable/variadic/`inout` params, but can't specify default values
    - `in` introduces the start of the closure's body
    - syntax:
    ```swift
    { (parameters) - > return type in
        statements
    }
    ```

These `backwards` functions all do the same things, in simpler ways
* If you want to omit the arguments, use `$0`, `$1`, ... for the arguments
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

* __trailing closure__: a closure expression written outside & after the parentheses of the function call it supports
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

* capturing values
    - closures can refer to & modify values of constants/variables w/i the body, even if the original scope that defined them no longer exist
* closures = reference type; so when you're assigning a function/closure to a constant/variable, you're keeping track of the reference to it, not the value

* nonescaping closures
    * a closure escapes a function when the closure is passed as an argument to the function, but is called after the function returns
    * write `@noescape` before the parameter name to indicate that the closure is not allowed to escape
        - allows you to refer to `self` implicitly w/i the closure
    * it's used when the closure has no use after it's usage in the function
    * _I have no clue what this even means... hopefully I'll figure it out in application?_
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

* Autoclosures: a closure automatically created to wrap an expresion passed as an arg to a function; it returns the value of the expression wrapped inside
    - allows you to delay evaluation; code inside isn't run until closure is called
    ```swift
    var customers = ["Chris", "Alex", "John", "Jane"] // 5 customers
    let customerProvider = { customers.removeAtIndex(0) } // still 5 customers; type: () -> String
    print ("Now serving \(customerProvider())!") // now 4 customers


    // Autoclosures as a parameter, to do the same thing
    func serveCustomer(@autoclosure customerProvider: () -> String) {
        print("Now serving \(customerProvider())!")
    }

    serveCustomer(customers.removeAtIndex(0))
    ```

`@autoclosure` denotes an autoclosure that _cannot escape_. You can use `@autoclosure(escaping)` to have an autoclosure that can escape


## Enumerations

* enumeration (`enum`): a group of related values specified as `case`s, which can have any type for each case
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

* once a value is declared as an enum type, you can change it by using `.[another case here]`
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

*  you can store _associated values_ along with each enum case, to change what type it can take. They're accessible if you extract each value as a `let`/`var`
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

* you can assign `enum` cases unique raw values (strings, characters, integer/floating point), all of the same type
```swift
enum Titles: String {
    case Lvl1 = "Noob"
    case Lvl2 = "Beginner"
    case Lvl3 = "Pro"
}
```

Implicitly-assigned raw values

* you can have Swift automatically set the values for you. Each case is one more than the last. If the first case doesn't have a value, it's set to `0`
* for strings, it will turn the name of the case into a String for it's `rawValue`
* initializing from a raw value: you can identify a case from it's raw value
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

* __recursive enumeration__: an enumeration that has another instance of the enumeration as an associated value for 1/+ cases
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

* things __classes__ have that __structures__ don't
    - subclasses inherit the characteristics of its superclass
    - typecasting allows you to check & interpret the typeo f the class at instance
    - it has a deinitializer
    - due to reference counting, you can have more than 1 reference to a class instance
        + structures are value types (like `enum`s), and therefore passed by value, whereas classes are passed by reference
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

* things __structures__ have that __classes__ don't
    - an automatically-generated memberwise initializer
    ```swift
    let vga = Resolution(width: 640, height: 480)
    ```
    -

* you can modify a class/struct instance's properties even if you make it a constant, because the whole is still the same
* check whether 2 instances are equal in value with `==`

When to use structures vs. classes:
* the struct's primary purpose is to encapsulate a few relatively simple data values
* it's reasonable to expect the values to be copied rather than referenced
* any properties in the struct are value types
* the struct doesn't need to inherit properties/behavior from another eixsting type

* `String`, `Array`, `Dictionary` are examples of structures (`NSString`, `NSArry`, `NSDictionary` are classes)


## Properties

* `struct`, `class`, `enum` can have computed properties, which are properties associated with an instance
* only `class` & `struct` can have stored properties, which are properties associated with an instance
* __type properties__: properties that are associated w/ a type itself
* you can have property observers to watch changes

Stored properties

* __stored properties__ example:
    ```swift
    struct FixedLengthRange {
        var firstValue: Int
        let length: Int
    }
    ```

* __lazy stored property__: a property whose initial value isn't calculated until the first time it's used. Must be a variable
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

* _caution:_ `lazy` properties may be initialized multiple times if it's accessed by multiple threads simultaneously

Computed properties

* __computed properties__: provide a getter & an optional setter to retrieve and set other properties
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

* __read-only computed properties__: a computed property with a getter but no setter (no need to write get, you can just write `get`'s body in the `{ }`)
```swift
struct Cuboid {
    // ...
    var volume: Double {
        return width * height * depth
    }
}
```

Property observers

* __property observers__ are called whenever a property value is set, even if the new value is the same as the old.
    - can be used for all properties except `lazy` stored properties
    - you can add a property observer to an inherited property by overriding the property w/i the subclass
    - two types of observers: `willSet` (called before value is stored), `didSet` (called after value is stored)
      * if you don't declare a parameter for the value, a default paramter name of `newValue` is made available for `willSet`, or `oldValue` for `didSet`
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

* global variables: variables defined outside of functions, methods, closures, or type contexts

Type properties

* instance properties: properties belonging to an instance of a particular type. A new set with every instance
* type properties: they're universal to all instances of that type; like static methods/variables.
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

* __method__: function associated with a particular type

Instance methods

* __instance method__: method belonging to an instance; same syntax as a function
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

* instance method parameters
    - given the first param name a local param name by default, and all of the others get both local & external param names. Of course, you can override.

Mutating methods

* declare a method with `mutating` if you want it to modify the properties of your `struct`/`enum`; classes do so by default
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

* essentially static methods that're denoted by `static` or `class`, which you call on a type, not an instance


[Prev page](Swift_1.md) • [Next Page](Swift_3.md)
