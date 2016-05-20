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


## Strings and Characters

## Collection Types

## Control Flow


[Prev page](README.md) • [Next Page](Swift_2.md)
