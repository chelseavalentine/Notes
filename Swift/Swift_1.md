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

If statements 

## Strings and Characters

## Collection Types

## Control Flow


[Prev page](README.md) • [Next Page](Swift_2.md)
