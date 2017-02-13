## Type Casting

* __type casting__: check instance type or treat it as a different superclass/subclass along its own class hierarchy, using `is` and `as` operators
    - eg. you can put many subclasses that extend from a base class in an array together, but they're all considered type baseclass until you downcast them by checking their type using `is`
        + eg. `if item is Movie`

Downcasting

* use `as?` or `as!` to downcast to the subclass, if you think an instance is a subclass behind the scenes
    - `as?` returns an optional value of the type you're downcasting to
    - `as!` downcasts & unwraps, and will cause a runtime error if you're wrong

Type Casting for Any and AnyObject

* `AnyObject` can represent an instance of any class type
    - for example, if you had an array like `[AnyObject]`, you could use `as!` to downcast each item, if you know all of the elements are the same type
    ```swift
    let someObjects: [AnyObject] = [ ... ]
    for movie in someObjects as! [Movie] { ... }
    ```

* `Any` can represent an instance of any type, including function types (eg. `[Any]()` for an array of anything)
    - must downcast each of the elements if you want to do anything w/ them except just print lol


## Nested Types

* __nested type__: nesting supporting enums, classes, & structures w/i the definition of  the type it supports
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

* __extensions__ add new functionality to an existing `class`, `struct`, `enum`, or `protocol` type––even if you don't have access to the source code (this is called _retroactive modeling_)!!
    - declared with `extension`
    - you cannot override existing functionality; only extend.
    - you can't add stored properties, or add property observers to existing properties
* abilities:
    - add computed instance properties & computed type properties
    - define instance methods & types
    - add new initializers
    - define subscripts
    - define & use new nested types
    - make an existing type conform to a protocol
        + all instances of the type will get the new functionality, even if created before the extension was defined
* you can add convenience initializers, but not designated initializers or deinitializers
```swift
extension SomeExistingType: SomeProtocol, AnotherProtocol {
    // implementation of protocol requirements goes here
}
```


## Protocols

* __protocol__: a blueprint of methods, properties, & other requirements for classes, structures, or enumerations to provide an actual implementation of
    - very similar to Java's interfaces
    - are listed along with the superclass in class declarations
    - can inherit protocols, so anything implementing the protocol must also implement the protocols it inherits
* property requirements
    - doesn't specify whether it's stored/computed, only the property name & type, and whether it's gettable, or gettable and settable
* method requirements
    - default values can't be specified
* mutating method requirements
    - you can just specify it as `mutating` in the protocol, no need to also write `mutating` in its implementation for classes (only `enum` & `struct`)
* initializer requirements
    - you can implement these requirements as either a designated or convenience initializer, but with either, you must mark it with `required`
        + you can leave out `required` if the class is `final`, since it can't be subclassed
        + if a subclass overrides a designated initializer from some superclass, & implements a matching initializer requirement from some protocol, you need to mark that overriding implementation with `required` _and_ `override`
    - can be failable
* optional protocol requirements
    - requirements that are optional to implement
    - declared with `optional` before it. Must have keyword `@objc` before `protocol` if you want to make one of its requirements optional
        + `@objc` protocols can only be adopted by classes inheriting from Objective-C classes, or other `@objc` classes. Can't be adopted by `struct` or `enum`
* protocols as types
    - use as a param or return type in a function, method, or initializer
    - use as type of a constant, variable, or property
    - use as type in an array, dictionary, etc.
* you can use `is` and `as` to check for protocol conformance, & to cast to a specific protocol

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

* __delegation__: a design pattern allowing a class/structure to delegate part of its responsibilities to an instance of another type
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

* if a type already conforms to all of the requirements of a protocol, but hasn't stated that it adopts that protocol, you can make it do so with an empty extension
```swift
extension Hamster: TextRepresentation {}
```

Class-Only Protocols

* limit the protocol to only being implemented by classes, by adding the `class` keyword to its inheritance list; must put `class` first in the list
    - usecase: when protocol assumes reference type
```swift
protocol SomeClassOnlyProtocol: class, SomeInheritedProtocol {
}
```

Protocol Composition

* require something to conform to multiple protocols at once using the form `protocol<Protocol1, Protocol2, ...>`

Protocol Extensions

* you can extend protocols to provide more properties/types/etc.
* you can extend protocols to provide default implementations to any method/property using that protocol
* you can add constraints to protocol extensions using the `where` clause
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

* type parameter: the placeholder type, written in angled brackets

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

* make sure that the type parameter has to inherit from a specific class, or conform to a protocol or protocol composition

```swift
func someFunction<T: SomeClass, U: SomeProtocol>(someT: T, someU: U) {
    //
}
```

Associated types

* __associated type__: a placeholder name to a type that's used as part of the protocol; it's essentially an alias
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

* can define requirements for associated types and type

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

* __access control__ allows you to restrict access to parts of your code from code in other source files & modues.
    - can hide implementation details & specify an interface to access the code
    - can specify access levels to individual types (ie. classes, structs, enums), as well as the properties, methods, initializers, & subscripts they contain
    - can restrict protocols, global constants, variables, functions to certain contexts
* __module__: a single unit of code distribution (eg. framework/app that can be imported by another module w/ `import`)
    - each build target (eg. app bundle, framework) is treated as a separate module
* __source file__: single Swift source code file w/i a module

Access levels

* _public access_: used w/i any source file from their defining module, or which imports it
    - declared with `public`
    - use case: the public interface to a framework (API - application programming interface)
    - it's properties, functions, etc. will be `internal`, not `public`, unlike the other access levels
* _internal access_: can only be used w/i their defining module
    - declared with `internal`
    - use case: an app/framework's internal structure
* _private access_: can only be used w/i it's defining source file
    - declared with `private`
    - use case: hiding implementation details of a functionality

Guiding principle of access levels
>No entity can be defined in terms of another entity that has a lower (ie. more restrictive) access level

* Default access level: _internal_
    - if you mark an import declaration for a product module w/ `@testable`, unit test targets can access any internal entity regardless of the explicit access level specified

* you can specify an explicit access level for a type when you define it
* if there're multiple access levels in the tuple, or function parameter/return types, the whole takes the access level of the most restrictive one
* `enum` & access levels:
    - each case gets the access level of the enum, and you can't specify access levels for individual cases
    - the types used w/i the enum must have an access level more liberal or equal to the `enum`
* nested types & access levels:
    - nested types w/i a private type are private
    - nested types w/i a public or internal type are internal (unless you explicitly declare them public)
* subclassing
    - subclasses can't be less restrictive than their superclasses
    - you can only override visible superclass members
* constants, variables, properties, and subscripts
    - can't be more public than its types
    - if you use a private type, you must mark whatever is using it as `private` too

Getters and setters

* they inherit the access level of whatever they belong to, but you can give it a lower access level to a setter than its getter
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

* can be given an access level less/equal to the type they initialize, unless they're `required`, obv.
* if you want a default initializer () in a `public` type to be public too, you must create the argument-less initializer yourself and make it public

Protocols

* its defined requirements have the same access level as the protocol was declared in its defining file, and cannot be changed
* you can have a type conform to a protocol with a lower access level than the type

Extensions

* extensions get the same access level as what it extends, but you can make an extension `private` for instance, to have everything that's added be `private`
    - within the extension, you can override the private and give particularm members a different access level
    - you can't do this if you're just extending to add protocol conformance, it just goes with the access level of what it extends

Generics

* access level is the minimum of the access level of the generic type/function itself and the access level of its type constaints

Type aliases

* type aliases are treated as distinct types, and a can have access levels lower or equal to the type it aliases


## Advanced Operators

* operators don't overflow by default; it's reported as an error; if you want to allow overflow, use `&` before the operator (eg. `&+`)
* you can make your own operators w/ custom precedence & associated values... wtf? talk about insane customizability
    - you can't overload `=` nor the ternary conditional operator

Operator Functions

* You can overload operators to determine how to add particular types, and these function definitions are defined globally
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

* mark its left input param w/ `inout` since it's modified directly

```swift
func += (inout left: Vector2D, right: Vector2D) {
    left = left + right
}
original += vectorToAdd
```

Equivalence operators

* have a way to determine whether your custom classes & structures are equal

```swift
func == (left: Vector2D, right: Vector2D) -> Bool {
    return (left.x == right.x) && (left.y == right.y)
}
func != (left: Vector2D, right: Vector2D) -> Bool {
    return !(left == right)
}
```

Custom operators

* are global. Must declare using the `operator` keyword & mark it with a `prefix`, `infix`, or `postfix` modifier
    - you can later give meaning to it through overloading
```swift
prefix operator +++ {}
```

Precedence and associaitivity for custom infix operators

* postfix operators are applied before the prefix operator if you're applying them both to the same operand
* `associativity`'s possible values:
    - `left` associate to the left if written to other left-associative operators of the same precedence
    - `right` associate to the right "..."
    - `none` (by default) can't be written next to other operators w/ the same precedence
* `precedence`
    - `100` by default
```swift
infix operator +- { associativity left precedence 140 }
```

[Prev page](Swift_3.md) • [Next Page](Swift_5.md)
