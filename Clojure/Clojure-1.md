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

* declare keywords like so:
  ```clojure
  ; example 1

  {:key-name "Value"
   :key-name2 "Value2"}

  ; example 2
  {:name {:first "John" :last "Smith"} :age 2}

  ; example 3; associates string-key w/ plus function
  {"string-key" +}
  ```

* create a hashmap with keyword `hash-map`
  ```clojure
  (hash-map :a 1 :b 2) ; => {:a 1 :b 2}
  ```

* look up values with __`get`__
  - returns `nil` if you don't specify a default return value

  ```clojure
  (get {:a 0 :b 1} :b) ; => 1

  ; specifying a default return value
  (get {:a 0 :b 1} :c "lol wat") ; => "lol wat"
  ```

* look up values in nested maps with __`get-in`__
  ```clojure
  ; => "😀"
  (get-in {:a 0 :b {:c "😀"}} [:b :c])
  ```

* you can also use maps as a function w/ the key as its arg
  ```clojure
  ({:name "Chelsea"} :name) ; => "Chelsea"
  ```

#### Keywords

* keywords can be used as functions to look up a value in a data structure, such as the prev example w/ map
  ```clojure
  (:a {:a 1 :b 2 :c 3} "my default value")
  ; => 1
  ```
