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


## Access Control


## Advanced Operators



[Prev page](Swift_3.md) • [Next Page](Swift_5.md)
