These notes are all based on Apple's Swift 2.2 book.

#### Table of Contents
1. [A Swift Tour](#a-swift-tour)
2. [The Basics](Swift_1.md#the-basics)
3. [Strings and Characters](Swift_1.md#strings-and-characters)
4. [Collection Types](Swift_1.md#collection-types)
5. [Control Flow](Swift_1.md#control-flow)
6. [Functions](Swift_2.md#functions)
7. [Closures](Swift_2.md#closures)
8. [Enumerations](Swift_2.md#enumerations)
9. [Classes and Structures](Swift_2.md#classes-and-structures)
10. [Properties](Swift_2.md#properties)
11. [Methods](Swift_2.md#methods)
12. [Subscripts](Swift_3.md#subscripts)
13. [Inheritance](Swift_3.md#inheritance)
14. [Initialization](Swift_3.md#initialization)
15. [Deinitialization](Swift_3.md#deinitialization)
16. [Automatic Reference Counting](Swift_3.md#automatic-reference-counting)
17. [Optional Chaining](Swift_3.md#optional-chaining)
18. [Error Handling](Swift_3.md#error-handling)
19. [Type Casting](Swift_4.md#type-casting)
20. [Nested Types](Swift_4.md#nested-types)
21. [Extensions](Swift_4.md#extensions)
22. [Protocols](Swift_4.md#protocols)
23. [Generics](Swift_4.md#generics)
24. [Access Control](Swift_4.md#access-control)
25. [Advanced Operators](Swift_4.md#advanced-operators)

## A Swift Tour

* __structures__ are always passed by value, whereas __classes__ are passed by reference
* You can use functions in enum values: 
    ```swift
    enum TaskActions {
        case Create(String)
        case Delete(Int)
        case Edit(Int, String)
    }
  
    // Thereby allowing you to do:
    let thisAction = TaskActions.Create("Study for the final.")

    // This is kind of weird, but you can also do
    switch thisAction {
    case let .Create(description):
        print("The task is to: \(description).")
    case let .Edit(id, description):
        print("We're going to edit task \(id) with the new task: \(description)")
    default:
        print("Unrecognized or currently unsupported action.")
    }
    ```
* `protocol` seems like an abstract class?
    ```swift
    // Classes can extend protocols
    class SimpleClass: ExampleProtocol {
        var simpleDescription: String = "A Simple Class that seems to be extending ExampleProtocol"
        var anotherProperty: Double = 4.20
        func adjust() {
            simpleDescription = "this function has different implementations"
        }
    }
    
    var mySimpleClass = SimpleClass()
    print("Before: \(mySimpleClass.simpleDescription)")
    
    mySimpleClass.adjust()
    print("After: \(mySimpleClass.simpleDescription)")
    
    
    // Structures can extend protocols, too
    struct SimpleStructure: ExampleProtocol {
        var simpleDescription: String = "A simple structure"
    
        /// mutating functions allow you to modify the structure from
        /// within the method, or even replace self entirely
        mutating func adjust() {
            simpleDescription = "Weird.. this function allows a mutating func"
        }
    }
    
    var mySimpleStructure = SimpleStructure()
    print("Before: \(mySimpleStructure.simpleDescription)")
    
    mySimpleStructure.adjust()
    print("After: \(mySimpleStructure.simpleDescription)")
    ```
* use extensions to add functionaltity to a default type (eg. new methods or properties)
    * extensions can be useful in many cases, eg. adding protocol conformance to a type
    ```swift
    extension Int: ExampleProtocol {
        var simpleDescription: String {
            return "The number \(self)"
        }
    
        mutating func adjust() {
            self += 10
        }
    }
    
    // Now you can use protocols on numbers
    print(10123.simpleDescription);
    ```
* the compiler treats variables/objects as having the class it was instantiated with; so even if it changes at run time, you can't access methods/properties the class implements as extra, if that class w/ extra features wasn't the initializing type
* Error handling
    ```swift
        enum UserErrors: ErrorType {
        case EmptyString
        case NoPermission
    }
    
    func addNewTask(task: String) throws -> String {
        if task == "" {
            throw UserErrors.EmptyString
        }
    
        return "Task added!"
    }
    
    func doUserActions() {
        do {
            /// some risky/secret task that the user doesn't have permission for
            /// If it fails, it'll go to the catch block
            try addNewTask("Hello world")
        } catch UserErrors.NoPermission {
            print("Sorry, you don't have permission to do this.")
        }catch let userError as UserErrors {
            print("You made a mistake, yo: \(userError)")
        } catch {
            print(error)
        }
    }
    ```
* Suppress errors and accept `nil`:
    * if it doesn't really matter whether the function executes properly, and you want to just discard the error on fail and accept nil, use `try?`
    ```swift
    let userSuccess = try? addNewTask("Hello world.")
    ```
* `defer` can be used to put setup & cleanup code nexxt to each other, and many other things, since it executes before the function returns
    ```swift
    func hello() {
        var goodbye = false;
        defer {
            goodbye = true;
        }
        // do some stuff here
    }
    ```
*  generics: write the name inside angled brackets to make a generic function or type
    ```swift
    func duplicateItem<Item>(item: Item, numberOfTimes: Int) -> [Item] {
        var result = [Item]()
    
        for _ in 0..<numberOfTimes {
            result.append(item)
        }
    
        return result;
    }
    
    print(duplicateItem(5, numberOfTimes: 3))
    
    /// Generic enumerations
    enum OptionalValue<Wrapped> {
        case None
        case Some(Wrapped)
    }
    
    var possibleInteger: OptionalValue<Int> = .None
    possibleInteger = .Some(100)

    ```
*  You can fine tune which generics can be used, or in general what requirements variables must have
    * eg. which protocol must it implement? do 2 types have to be the same? does it need a particular superclass?
    ```swift
    func anyCommonElements <T: SequenceType, U: SequenceType
        /// Writing <T: Equatable> is the same as writing <T where T: Equatable>
        where T.Generator.Element: Equatable,
        T.Generator.Element == U.Generator.Element> (lhs: T, _ rhs: U) -> Bool {
    
        // Do some stuff here lol
        // eg.
        for lhsItem in lhs {
            for rhsItem in rhs {
                if lhsItem == rhsItem {
                    return true;
                }
            }
        }
        //
        return false;
    }
    
    anyCommonElements([1, 2, 3], [3])
    ```


[Next page ->](Swift_1.md)
