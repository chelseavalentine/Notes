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

* _in-out parameters_: mutable parameters (by default, parameters are constants), defined by writing `inout` at the start of the parameter definition
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

## Classes and Structures

## Properties

## Methods


[Prev page](Swift_1.md) • [Next Page](Swift_3.md)
