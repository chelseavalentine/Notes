# Clojure

## Introduction

### Chapter 1: Building, Running, and the REPL

* __JAR files__: collections of Java bytecode.

* `lein repl` opens the Read-Eval-Print Loop (REPL)

* uses prefix notation, so operator comes first in an expression

### Chapter 3: Do things: A Clojure crash course

* whitespace delimits operands, and commas are interpreted as whitespace

#### Forms

* __form__: valid code
  - Clojure evaluates every form to produce a value
  - eg. `1`, `["a" "vector" "of" "strings"]

* operation:
  ```clojure
  (operator operand1 operand2 ... operandn)
  ```

#### Control flow

* __`if`__
  ```clojure
  (if boolean-form
    then-form
    [else-form])

  ; eg.
  (if true
    "This is true"
    "This is false"
  )
  ```

* __`do`__ allows you to wrap multiple forms in parentheses, rather than just 1
  ```clojure
  (if true
    (do (println "This first thing")
      "This second thing")
    (do (println "This first else thing")
      "This second else thing"))
  ```

* __`when`__: a combination of `if` & `do` w/o an `else branch
  - used when you want to do multiple things & always return `nil` when the condition is false
  ```clojure
  (when true
    (println "This is happening")
    "Okay...")
  ```

* check for `nil` with the `nil?` function
  ```clojure
  (nil? 1) ; => false
  ```

* __`=`__: equality operator
  ```clojure
  (= 1 1) ; => false
  ```

* __`and`__, __`or`__
  - `and` returns the last truthy value
  - `or` returns the last value if none exist, or the first truthy value

#### Naming values with `def`

* __`def`__ binds a name to a value
  - _bind_ because in Clojure, you rarely need to alter a name/value association
  ```clojure
  (def people-names
    ["Annie" "Bob" "Claire" "Derek" "Elaine"])

  people-names ; => ["Annie" "Bob" "Claire" "Derek" "Elaine"]
  ```

#### Data structures

* all data structures are immutable

* strings
  - only use double quotes for strings
  - has no string interpolation, so the only way you concatenate things is via the __`str`__ function
    ```clojure
    (def name "Chelsea")
    (str "she said, \"My name is " name ".\"")
    ```

#### Maps

* maps `{}`
  - 2 kinds of maps: hash maps & sorted maps

#### Keywords

#### Vectors

#### Lists

#### Sets

#### Simplicity

#### Functions

#### Function calls, macro calls, and special forms

#### Defining functions

#### Anonymous functions

#### Returning functions

#### `let`

#### `loop`

#### Regular expressions

#### Symmetrizer
