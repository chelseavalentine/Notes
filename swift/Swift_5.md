## Language Reference

* arrow `->` means "can consist of"
* `|` denotes an alternative grammar production

#### Lexical structure

* keywords mentioned, which I haven't learned about yet:
    - `dynamicType`, `Self`, `rethrows`, `#column`, `#file`, `#line`, `#function`, `#available`, `#else`, `#elseif`, `#endif`, `#if`, `#selector`
* (notable) keywords reserved in particular contexts: `left`, `right`, `none`, `precedence`, `required`, `unowned`, `weak`, `willSet`


#### Types

* if a function type has more than 1 `->`, they're grouped from right to left
    - eg. `Int -> Int -> Int` would become `Int -> (Int -> Int)` (function takes an `Int`, and returns another function that takes an `Int` and returns and `Int`)
* create multidimensional arrays by nesting the square brackets: eg. `[[Int]]`, `[[[Int]]]`


#### Metatype Type

* metatype of a class/structure/enumeration type is the name of type is the name of the type followed by `.Type`. Protocol metatype is protocol followed by `.Protocol`
    - eg. `SomeClass.Type`, `SomeProtocol.Protocol`
* get the runtimetype of an instance with `.dynamicType` after the instance name
    - eg. `someInstance.dynamicType`
    - are the runtime & compile types the same? check with `instance.dynamicType === instance.self`

```swift
// Use the type
let metatype: AnotherSubclass.Type = AnotherSubclass.self
let anotherInstance = metatype.init(string: "some string")
```


## Expressions

#### Literal expressions

* ooo.. this is probably good for debugging: you can use these literals
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

* you can label statements with a statement label followed immediately by `:`, allowing you to be explicit about changing control flow in a loop/switch with `break` & `continue` statements
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

* top-level code: code available to everything in the module (default behavior, aka `internal`)

#### Import declaration

There're import kinds!!, this is the import structure:

```swift
import [import kind] [module].[symbol name]
```

* import kinds include: `typealias`, `struct`, `class`, `enum`, `protocol`, `var`, `func`


#### `inout` parameters

* it's unpredictable when the function using the `inout` parameter will return, so your changes to the original value is overwritten by the value of the copy
    - likewise, there's no copy-out @ end of closures or nested functions, so if a closure is called after the function returns, any changes the closure makes to the `inout` parameters don't get copied back to the original


#### Throwing functions & methods

* just changing whether a function throws or not does not override the superclass's function; you need to change the function type too

* a function/method can use `rethrow` to throw and error if and only if one if its function params throws an eror
    - cannot throw any errors of its own, thus can't contain a `throw` statement

```swift
func functionWithCallback(callback: () throws -> Int) rethrows {
    try callback()
}
```


## Attributes

* __attributes__ provide more info about a declaration/type
```swift
@[attribute name]
// or
@[attribute name]([attribute arguments])
```

Declaration attributes:

* `autoclosure`: delays evaluation of expression by wrapping it in an argument-less closure
    - also implies `noescape`, unless passed optional argument `escaping`
* `available`: indicate that something only is intended for a certain platform/operating system variation; takes args with the OS/platform, or arg of `*` to indicate all platforms
    - alternatively, you can say `unavailable` for ones it isn't available on
* `introduced=[version number]`: say when the declaration was first introduced

More:
* `noescape`, `nonobjc`, `noreturn`, `NSApplicationMain`, `NSCopying`, `NSManaged`, `testable`, `UIApplicationMain`, `warn_unused_result`

Type attributes (only appliable to types):
* `convention`: indicate its calling conventions, `noreturn`


## Patterns

* __pattern__: the structure of a single value/composite value
    - eg. `_` is the wildcard pattern

[Prev page](Swift_4.md)
