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

[Prev page](Swift_4.md)
