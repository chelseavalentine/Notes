## Subscripts

* you can define subscripts if you want to access things w/ square braces
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

* methods, properties, subscripts are inherited
* can add property observers to inherited properties to get notified on change
* classes don't inherit from a universal base class
* subclass syntax: `class Subclass: Superclass`
* declare overriding definitions with `override`
    - can still call the superclass's definition with `super` (eg. `super.someMethod()` or `super.someProperty`, or `super[someIndex]`)
    - must state the name & type of any property you're overriding
    - you can make inherited read-only properties into read-write, but not vice-versa
    - prevent overrides by declaring with `final`

Overriding property observers

* use overriding to add property observers to an inherited property w/ `willSet` or `didSet`
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

* you cannot subclass a `final class`


## Initialization

* initializers declaraed with `init()`, and params if you choose.
    - swift automatically provides external param names
    - you can set a constant during initialization
    - structures & classes have a default `init()` initializer
    - you can call other initializers w/i an initializer (eg. `self.init`)

Class inheritance and initialization

* all of the class's stored properties need to be assigned an initial value in initialization. There are 2 ways of doing so:
    - __designated initializers__: using its super's initializers to initialize everything
        + must call a designated initializer from its immediate superclass (delegate up)
        + ensures all properties introduced by the class are initialized _before_ delegating up to its superclass
        ```swift
        init(parameters) {
            statements
        }
        ```
    - __convenience initializers__: using its other initializers to fully initialize everything
        + declared with keyword `convenience`
        + must call another initializer from the _same_ class (delegate across)
        + must ultimately call a designated initializer
        ```swift
        convenience init(parameters) {
            statements
        }
        ```

Initializer inheritance and overriding

* subclasses don't inherit superclass' initializers by default; onl
* if you write a subclass initializer that matches a superclass' designated initializer, you need to write `override` beforehand, even if what you're overriding it with is a convenience initializer
    - need to specify `override`, even if you're writing a custom `init()` initializer with no params

Automatic Initializer Inheritance

* if these conditions are met, superclass initializers are automatically inherited:
    - Subclass doesn't define any designated initializers
    - Subclass provides an implementation of _all_ of its superclass designated initializers (via inheriting them, or providing custom implementations of them)

Failable Initializers

* eg. when initialization params are invalid, absence of a resource, etc. you can make class/structure initialization fail
* declared by adding a question mark to the init name (ie. `init?()`)
    - cannot have a failable & nonfailable initializer w/ the same parameter types and names
* failable initializers create an optional value of the type it initializes, and it would `return nil` upon fail
* enumerations w/ raw values automatically get `init?(rawValue:)` initializers
* you can override a failable initializer w/ a nonfailable initializer, but not vice-versa

Propagation of Initialization Failure

* a failable class/struct/enum initializer can delegate across to another fialable initializer from the same class/struct/enum, or up to one of its superclass's failable initalizers

The `init!` Failable Initializer

* define a failable initializer that creates an implictly unwrapped optional instance of the appropriate type with `init!`
    - can delegate from `init?` to `init!` & vice-versa, and override with each other.
    - can delegate from `init` to `init!` but will trigger an assertion if `init!` fails

Required Initializers

* use `required` keyword before `init` if every class initializer must implement it
    - must also write the `required` keyword before subclass's overriding of a required initializer, you don't need to also specify `override`

Closures or functions in initialization

* `()` at the end of the closure means execute immediately, otherwise you're assigning the closure to the property

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


## Automatic Reference Counting


## Optional Chaining


## Error Handling



[Prev page](Swift_2.md) • [Next Page](Swift_4.md)
