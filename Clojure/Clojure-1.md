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

#### Vectors

* can be any type, & you can mix types

* can create vectors with `[]` or the `vector` function
  ```clojure
  (vector "I'm" "watching" "this" "show" 10/10)
  ```

* get the 0th element of a vector
  ```clojure
  (get [3 2 1] 0) ; => 3

* add more elements to the vector with `conj`
  ```clojure
  (conj [1 2 3] 4)
  ; => [1 2 3 4]
  ```

#### Lists

* lists: linear collection of values, but can't retrieve w/ `get`
  ```clojure
  ; list declaration
  '(1 2 3 4)
  ; => (1 2 3 4)

  (list (1 "two" {3 4}))
  ; => (1 "two" {3 4})
  ```

* use `nth` function to retrieve from list
  - slower than using `get` since the whole list is traversed to get to the _nth_ element

  ```clojure
  (nth '(:a :b :c) 0)
  ; => :a
  ```

* use `conj` to add to a list, but note that elements are _added to the beginning of the list_
  ```clojure
  (conj '(1 2 3) 4)
  ; => (4 1 2 3)
  ```

#### Sets

* collections of unique values w/ two types: hash sets & sorted sets

* declare a hash-set with `#` or `hash-set`
  ```clojure
  #{"hi" 20 "hi"}
  ; => #{ "hi" 20}

  (hash-set 1 1 2 2)
  ; => #{1 2}
  ```

* add to a hash-set
  ```clojure
  (conj #{:a :b} :c)
  ; => #{:a :b :c}
  ```

* check for membership with `contains?` which returns `true` or `false`, or with `get`, which returns `nil` if not a member
  ```clojure
  (contains? #{:a :b} :c)
  ; => false

  (get #{:a :b} :b)
  ; => true

  ; shorthand get
  (:c #{:a :b})
  ; => nil
  ```

* create a set from a vector using the `set` function
  ```clojure
  (set [3 3 3 4 4])
  ; => #{3 4}
  ```

#### Simplicity

* Clojure belief:
> It is better to have 100 functions operate on one data structure than 10 functions on 10 data structures.

#### Functions

```clojure
((or + -) 1 2 3)
; => 6

((and (= 1 1) +) 1 2 3)
; => 6
```

* if you get the error `<x> cannot be cast to clojure.lang.IFn` it means you're trying to use something as a function when it's not

* you can create high-order functions that allow function args

```clojure
(inc 1.1)
; => 2.1

(map inc [0 1 2 3])
; => (1 2 3 4)
```

* Clojure evaluates all function args recursively before passing them to a function

```clojure
(+ (inc 199) (/ 100 (- 7 2)))
(+ 200 (/ 100 (- 7 2))) ; evaluated "(inc 199)"
(+ 200 (/ 100 5)) ; evaluated (- 7 2)
(+ 200 20) ; evaluated (/ 100 5)
220 ; final evaluation
```

#### Function calls, macro calls, and special forms

* __special form__'s differentiating features
  - they don't always evaluate all of their operands
    - eg. an `if` statement would only operate the proper branch
  - you can't use them as arguments to functions

* macros evaluate operands differently from function calls, therefore can't be passed as arguments to functions

#### Defining functions

* parts of a function definition:
  - `defn`
  - name
  - a docstring describing the function (optional)
    + viewable w/ `doc` function in REPL, format: `(doc [name])`
  - parameters listed in brackets
  - function body

  ```clojure
  ➊ (defn wat
  ➋   "Says 'wat'"
  ➌   [name]
  ➍   (str "wat " name " wat "
    "wat"))

  (wat "Person")
  ; => "wat Person wat"
  ```

* __arity__: the number of parameters a function has ("[number]-arity function")
  ```clojure
  (defn no-params
    []
    "No params heree")

  (defn two-params
    [x y]
    (str "Two params yo: " x y))
  ```

* functions support __arity overloading__
  ```clojure
  (defn multi-arity
    ;; 3-arity arguments and body
    ([first-arg second-arg third-arg]
       (do-things first-arg second-arg third-arg))
    ;; 2-arity arguments and body
    ([first-arg second-arg]
       (do-things first-arg second-arg))
    ;; 1-arity arguments and body
    ([first-arg]
       (do-things first-arg)))
  ```

* you can define a __rest parameter__ with `&`, which pipes all of the arguments into the array name defined after the `&`
  ```clojure
  (defn codger-communication
    [whippersnapper]
    (str "Get off my lawn, " whippersnapper "!!!"))

  (defn codger
    [& whippersnappers]
    (map codger-communication whippersnappers))

  (codger "Anne" "Bill" "Chris")
  ; => ("Get off my lawn, Anne!!!"
        "Get off my lawn, Bill!!!"
        "Get off my lawn, Chris!!!")
  ```

* __destructuring__ allows you to bind names to values w/i a collection

* destructuring a vector
  ```clojure
  ;; Return the first element of a collection
  (defn print-choices
    [[first-thing second-choice & unimportant-choices]]
    (println (str "Your first choice is: " first-thing))
    (println (str "Your second choice is: " second-choice))
    (println (str "Unimportant: " (clojure.string/join ", " unimportant-choices))))

  (my-first ["oven" "bike" "war-axe" "okay" "err" "crap"])
  ; => Your first choice is: oven
  ; => Your second choice is: bike
  ; => Unimportant: war-axe, okay, err, crap
  ```

* you can destructure maps too by passing a map as a parameter
  ```clojure
  (defn announce-treasure-location
    [{lat :lat lng :lng}]
    (println (str "The treasure is at lat: " lat " lng: " lng)))

  (announce-treasure-location {:lat 28.22 :lng 81.33})

  ; a simpler way to destructure that array uses keys
  (defn announce-treasure-location
    [{:keys [lat lng]}]
    (println (str "The treasure is at lat: " lat " lng: " lng)))
  ```

#### Anonymous functions

#### Returning functions

#### `let`

#### `loop`

#### Regular expressions

#### Symmetrizer
