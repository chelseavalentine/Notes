# Clojure for the Brave and True

## Chapter 1: Building, Running, and the REPL

- __JAR files__: collections of Java bytecode.
- `lein repl` opens the Read-Eval-Print Loop (REPL)
- uses prefix notation, so operator comes first in an expression

## Crash course

- whitespace delimits operands, and commas are interpreted as whitespace

#### Forms

- __form__: valid code

  - Clojure evaluates every form to produce a value
  - eg. `1`, `["a" "vector" "of" "strings"]

- operation:

  ```clojure
  (operator operand1 operand2 ... operandn)
  ```

#### Control flow

- __`if`__

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

- __`do`__ allows you to wrap multiple forms in parentheses, rather than just 1

  ```clojure
  (if true
    (do (println "This first thing")
      "This second thing")
    (do (println "This first else thing")
      "This second else thing"))
  ```

- __`when`__: a combination of `if` & `do` w/o an `else branch

  - used when you want to do multiple things & always return `nil` when the condition is false

  ```clojure
  (when true
    (println "This is happening")
    "Okay...")
  ```

- check for `nil` with the `nil?` function

  ```clojure
  (nil? 1) ; => false
  ```

- __`=`__: equality operator

  ```clojure
  (= 1 1) ; => false
  ```

- __`and`__, __`or`__

  - `and` returns the last truthy value
  - `or` returns the last value if none exist, or the first truthy value

#### Naming values with `def`

- __`def`__ binds a name to a value

  - _bind_ because in Clojure, you rarely need to alter a name/value association

  ```clojure
  (def people-names
    ["Annie" "Bob" "Claire" "Derek" "Elaine"])
  
  people-names ; => ["Annie" "Bob" "Claire" "Derek" "Elaine"]
  ```

#### Data structures

- all data structures are immutable

- strings

  - only use double quotes for strings

  - has no string interpolation, so the only way you concatenate things is via the __`str`__ function

    ```clojure
    (def name "Chelsea")
    (str "she said, \"My name is " name ".\"")
    ```

#### Maps

- maps `{}`

  - 2 kinds of maps: hash maps & sorted maps

- declare keywords like so:

  ```clojure
  ; example 1
  
  {:key-name "Value"
   :key-name2 "Value2"}
  
  ; example 2
  {:name {:first "John" :last "Smith"} :age 2}
  
  ; example 3; associates string-key w/ plus function
  {"string-key" +}
  ```

- create a hashmap with keyword `hash-map`

  ```clojure
  (hash-map :a 1 :b 2) ; => {:a 1 :b 2}
  ```

- look up values with __`get`__

  - returns `nil` if you don't specify a default return value

  ```clojure
  (get {:a 0 :b 1} :b) ; => 1
  
  ; specifying a default return value
  (get {:a 0 :b 1} :c "lol wat") ; => "lol wat"
  ```

- look up values in nested maps with __`get-in`__

  ```clojure
  ; => "😀"
  (get-in {:a 0 :b {:c "😀"}} [:b :c])
  ```

- you can also use maps as a function w/ the key as its arg

  ```clojure
  ({:name "Chelsea"} :name) ; => "Chelsea"
  ```

#### Keywords

- keywords can be used as functions to look up a value in a data structure, such as the prev example w/ map

  ```clojure
  (:a {:a 1 :b 2 :c 3} "my default value")
  ; => 1
  ```

#### Vectors

- can be any type, & you can mix types

- can create vectors with `[]` or the `vector` function

  ```clojure
  (vector "I'm" "watching" "this" "show" 10/10)
  ```

- get the 0th element of a vector

  ```clojure
  (get [3 2 1] 0) ; => 3
  ```

- add more elements to the vector with `conj`

  ```clojure
  (conj [1 2 3] 4)
  ; => [1 2 3 4]
  ```

#### Lists

- lists: linear collection of values, but can't retrieve w/ `get`

  ```clojure
  ; list declaration
  '(1 2 3 4)
  ; => (1 2 3 4)
  
  (list (1 "two" {3 4}))
  ; => (1 "two" {3 4})
  ```

- use `nth` function to retrieve from list

  - slower than using `get` since the whole list is traversed to get to the _nth_ element

  ```clojure
  (nth '(:a :b :c) 0)
  ; => :a
  ```

- use `conj` to add to a list, but note that elements are _added to the beginning of the list_

  ```clojure
  (conj '(1 2 3) 4)
  ; => (4 1 2 3)
  ```

#### Sets

- collections of unique values w/ two types: hash sets & sorted sets

- declare a hash-set with `#` or `hash-set`

  ```clojure
  #{"hi" 20 "hi"}
  ; => #{ "hi" 20}
  
  (hash-set 1 1 2 2)
  ; => #{1 2}
  ```

- add to a hash-set

  ```clojure
  (conj #{:a :b} :c)
  ; => #{:a :b :c}
  ```

- check for membership with `contains?` which returns `true` or `false`, or with `get`, which returns `nil` if not a member

  ```clojure
  (contains? #{:a :b} :c)
  ; => false
  
  (get #{:a :b} :b)
  ; => true
  
  ; shorthand get
  (:c #{:a :b})
  ; => nil
  ```

- create a set from a vector using the `set` function

  ```clojure
  (set [3 3 3 4 4])
  ; => #{3 4}
  ```

#### Simplicity

- Clojure belief:

> It is better to have 100 functions operate on one data structure than 10 functions on 10 data structures.

#### Functions

```clojure
((or + -) 1 2 3)
; => 6

((and (= 1 1) +) 1 2 3)
; => 6
```

- if you get the error `<x> cannot be cast to clojure.lang.IFn` it means you're trying to use something as a function when it's not
- you can create high-order functions that allow function args

```clojure
(inc 1.1)
; => 2.1

(map inc [0 1 2 3])
; => (1 2 3 4)
```

- Clojure evaluates all function args recursively before passing them to a function

```clojure
(+ (inc 199) (/ 100 (- 7 2)))
(+ 200 (/ 100 (- 7 2))) ; evaluated "(inc 199)"
(+ 200 (/ 100 5)) ; evaluated (- 7 2)
(+ 200 20) ; evaluated (/ 100 5)
220 ; final evaluation
```

#### Function calls, macro calls, and special forms

- __special form__'s differentiating features
  - they don't always evaluate all of their operands
    - eg. an `if` statement would only operate the proper branch
  - you can't use them as arguments to functions
- macros evaluate operands differently from function calls, therefore can't be passed as arguments to functions

#### Defining functions

- parts of a function definition:

  - `defn`
  - name
  - a docstring describing the function (optional)
    - viewable w/ `doc` function in REPL, format: `(doc [name])`
  - parameters listed in brackets
  - function body

  ```clojure
  (defn wat
    "Says 'wat'"
    [name]
    (str "wat " name " wat"))
  
  (wat "Person")
  ; => "wat Person wat"
  ```

- __arity__: the number of parameters a function has ("[number]-arity function")

  ```clojure
  (defn no-params
    []
    "No params heree")
  
  (defn two-params
    [x y]
    (str "Two params yo: " x y))
  ```

- functions support __arity overloading__

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

- you can define a __rest parameter__ with `&`, which pipes all of the arguments into the array name defined after the `&`

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

- __destructuring__ allows you to bind names to values w/i a collection

- destructuring a vector

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

- you can destructure maps too by passing a map as a parameter

  - you can retain access to the original map by passing the `:as` keyword

  ```clojure
  (defn announce-treasure-location
    [{lat :lat lng :lng}]
    (println (str "The treasure is at lat: " lat " lng: " lng)))
  
  (announce-treasure-location {:lat 28.22 :lng 81.33})
  
  ; a simpler way to destructure that array uses keys
  (defn announce-treasure-location
    [{:keys [lat lng] :as treasure-location}]
    (println (str "The treasure is at lat: " lat " lng: " lng)))
  ```

- Clojure automatically returns the last form evaluated

  ```clojure
  (defn illustrative-function
    []
    (+ 1 304)
    30
    "joe")
  
  (illustrative-function)
  ; => "joe"
  ```

#### Anonymous functions

- functions don't need to have names; can declare one with `fn` or with `#` (made possible by reader macros)

  ```clojure
  (fn [param-list]
    function body)
  
  ; eg.
  ((fn [x] (* x 3)) 8)
  ; => 24
  
  ; high-order functions
  (def my-special-multiplier (fn [x] (* x 3)))
  (my-special-multiplier 12)
  ; => 36
  
  ; with #
  (#(* % 3) 8)
  ; => 24
  
  (map #(str "Hi, " %)
       ["Darth Vader" "Mr. Magoo"])
  ; => ("Hi, Darth Vader" "Hi, Mr. Magoo")
  ```

- the `%` indicates the argument passed to the function

  - `%` = `%1`, and you can indicate others with `%[number]`
  - `%&` works as a rest parameter

#### Returning functions

Function factory!

```clojure
(defn inc-maker
  "Create a custom incrementor"
  [inc-by]
  #(+ % inc-by))

(def inc3 (inc-maker 3))

(inc3 7)
; => 10
```

#### `let`

- __`let`__ binds names to values

  - also creates scope
  - you can reference existing bindings w/i the binding
  - `take [number]` determines how many are assigned
  - you can use rest params

  ```clojure
  (let [x 3]
    x)
  ; => 3
  
  ;; referencing
  (def x 0)
  (let [x (inc x)] x)
  ; => 1
  
  ;; using take
  (def dalmatian-list
    ["Pongo" "Perdita" "Puppy 1" "Puppy 2"])
  (let [dalmatians (take 2 dalmatian-list)]
    dalmatians)
  ; => ("Pongo" "Perdita")
  
  ;; using rest params
  (let [[pongo & dalmatians] dalmatian-list]
    [pongo dalmatians])
  ; => ["Pongo" ("Perdita" "Puppy 1" "Puppy 2")]
  ```

- significance of `let`

  - increased clarify by naming things
  - saves an evaluation, esp. good for expensive function calls or if the expression has side effects

- __`into`__ puts elements into a vector

  ```clojure
  (into [] (set [:a :a]))
  ; => [:a]
  ```

#### `loop`

- __`loop`__ format: `loop [iteration [number]]` starts the loop w/ an initial value

  - __`recur`__ lets you call the function from w/i itself

  ```clojure
  (loop [iteration 0]
    (println (str "Iteration " iteration))
    (if (> iteration 3)
      (println "Goodbye!")
      (recur (inc iteration))))
  ; => Iteration 0
  ; => Iteration 1
  ; => Iteration 2
  ; => Iteration 3
  ; => Iteration 4
  ; => Goodbye!
  ```

#### Regular expressions

- format:

  ```clojure
  #"regular-expression"
  
  ;; eg.
  (re-find #"^left-" "left-eye")
  ; => "left-"
  
  (re-find #"^left-" "cleft-chin")
  ; => nil
  ```

#### `reduce`

- __`map`__ returns a list

- __`reduce`__ applies a function to a sequence (sequence specified by vector)

  - also it treats your map like a list of vectors

  ```clojure
  ;; sum with reduce
  (reduce + [1 2 3 4])
  ; => 10
  ```

- __`empty? []`__ tells you whether an array is empty

## Chapter 4: Core functions in depth

#### Treating lists, vectors, sets, and maps as sequences

- __sequence__ / __seq__: a collection of elements organized in linear order

#### Abstraction through indirection

- __indirection__: a mechanism a language employs so that one name can have multiple related meanings
  - eg. `first` has multiple data-structure-specific meanings
  - different function bodies are used based on the type of the argument

### Seq function examples

#### `map`

- you can give it multiple collections, so that the first arg transforms the second, the second the third, and so on

  - but the mapping function needs to be able to take a number of args equal to the num of collections

  ```clojure
  (map str ["a" "b" "c"] ["A" "B" "C"])
  ```

- you can also pass map a collection of functions

  ```clojure
  (defn stats
    [numbers]
    (map #(% numbers) [sum count avg]))
  ```

#### `reduce`

- processes each element in a sequence to build a result

#### `take`, `drop`, `take-while`, and `drop-while`

- `take` & `drop` take a number & a seq; then they either crop values or drop values, & then return an array

- __`take-while`__ & __`drop-while`__ take a predicate function to decide when to stop taking/dropping

  - __predicate function__: function whose return value is evaluated for truth or falsity

    ```clojure
    (def food-journal
      [{:month 1 :day 1 :human 5.3 :critter 2.3}
       {:month 1 :day 2 :human 5.1 :critter 2.0}
       {:month 4 :day 1 :human 3.7 :critter 3.9}
       {:month 4 :day 2 :human 3.7 :critter 3.6}])
    
    (take-while #(< (:month %) 2) food-journal)
    ; => ({:month 1 :day 1 :human 5.3 :critter 2.3}
    ; =>  {:month 1 :day 2 :human 5.1 :critter 2.0})
    ```

#### `filter` and `some`

- __`filter`__ takes in a predicate function & tests true on all the elements of the sequence

  - if it's already sorted, `take-while` is more efficient

- __`some`__ tests whether a collection contains any values that test true for a predicate function

  - to return the statement that tests true, use `and`

    ```clojure
    (some #(and (> (:critter %) 3) %) food-journal)
    ; => {:month 3 :day 1 :human 4.2 :critter 3.3}
    ```

#### `sort`, `sort-by`, and `concat`

- `sort` in ascending order
- `sort-by` lets you apply a key function, eg. `count` to the sequence (check the docs)
- `concat` appends sequences to the end

### Lazy seqs

- __lazy seq__: a seq whose members aren't computed until you try to access them
  - map uses this

#### Infinite sequences

- can use `take`, along with `repeat` (for a value) and `repeatedly` (for a function) to create sequences

  ```clojure
  (concat (take 8 (repeat "na")) ["Batman!"])
  ; => ("na" "na" "na" "na" "na" "na" "na" "na" "Batmenz")
  
  (take 3 (repeatedly (fn [] (rand-int 10))))
  ; => (1 4 0)
  ```

### The collection abstraction

#### `into`

- __`into`__ lets you convert something into a data structure, eg. a sequence into a map

  ```clojure
  (map identity {:sunlight-reaction "Glitter!"})
  ; => ([:sunlight-reaction "Glitter!"])
  
  (into {} (map identity {:sunlight-reaction "Glitter!"}))
  ; => {:sunlight-reaction "Glitter!"}
  
  (into ["cherry"] '("pine" "spruce"))
  ; => ["cherry" "pine" "spruce"]
  ```

#### `conj`

- `into` merges values into the holder, whereas `conj` will also put the holder in, if that makes sense?

  ```clojure
  (conj [0] [1])
  ; => [0 [1]]
  
  (conj [0] 1)
  ; => [0 1]
  ```

### Function functions

#### `apply`

- `apply` explodes a seqable DS so it can be passed to a function expecting a rest parameter

  ```clojure
  (max 0 1 2)
  ; => 2
  
  (max [0 1 2])
  ; => [0 1 2]
  
  (apply max [0 1 2])
  ; => 2
  ```

#### `partial`

- `partial` takes a function & args, then returns a new function

  ```clojure
  (def add10 (partial + 10))
  (add10 3)
  ; => 13
  (add10 5)
  ; => 15
  
  (def add-missing-elements
    (partial conj ["water" "earth" "air"]))
  
  (add-missing-elements "unobtainium" "adamantium")
  ; => ["water" "earth" "air" "unobtainium" "adamantium"]
  ```

#### `complement`

- `complement` tests for falseness, rather than doing `not [condition]`

## Chapter 5: Functional programming

- decouples functions and data
- by programming to a small set of abstractions, you end up with more reusable, composable code

### Pure functions: What and why

- __pure function__:
  - has __referential transparency__: always returns same results given the same args
  - doesn't cause side-effects; can't change things outside of the function

### Living with immutable data structures

#### Recursion instead of for/while

- since immutable data structures, need to use recursion if you wanted to do a sum

  ```clojure
  (defn sum
    ([vals] (sum vals 0))
    ([vals accumulating-total]
      (if (empty? vals)
      accumulating-total
      (sum (rest vals) (+ (first vals) accumulating-total)))))
  ```

- `recur` is better for performance-wise for recursion, since clojure doesn't provide tail call optimization

  ```clojure
  (defn sum
    ([vals]
      (sum vals 0))
    ([vals accumulating-total]
      (if (empty? vals)
        accumulating-total
        (recur (rest vals) (+ (first vals) accumulating-total)))))
  ```

#### Function composition instead of attribute mutation

- __function composition__: return val of a function is passed as an arg to another

### Cool things to do with pure functions

#### `comp`

- `comp` allows you to create a new function from the composition of any number of functions

  - applies functions like a composition, so if `(comp f1 f2 ... fn)`, then `f1(f2(..(fn(args))))`

  ```clojure
  ((comp inc *) 2 3)
  ; => 7
  ```

- can use to retrieve nested values

  ```clojure
  (def character
    {:name "Smooches McCutes"
     :attributes {:intelligence 10
                  :dexterity 5}})
  (def c-int (comp :intelligence :attributes))
  
  (c-int character)
  ; => 10
  ```

- make compositions of functions that take more than 1 arg by wrapping it in an anonymous function

  ```clojure
  (def spell-slots-comp (comp int inc #(/ % 2) c-int))
  ```

#### `memoize`

- memoization stores the args passed to a function, & the return value of the function

  - significance: subsequent calls to the function w/ the same args can return the result immediately

  ```clojure
  (def memo-sleepy-identity (memoize sleepy-identity))
  (memo-sleepy-identity "Mr. Fantastico")
  ; => "Mr. Fantastico" after 1 second
  
  (memo-sleepy-identity "Mr. Fantastico")
  ; => "Mr. Fantastico" immediately
  ```

### Misc

- `assoc-in` returns a map w/ the given value @ the specified nesting

  ```clojure
  (assoc-in {} [:cookie :monster :vocals] "Finntroll")
  ; => {:cookie {:monster {:vocals "Finntroll"}}}
  ```

- `get-in` lets you look up values in nested maps

  ```clojure
  (get-in {:cookie {:monster {:vocals "Finntroll"}}} [:cookie :monster])
  ; => {:vocals "Finntroll"}
  ```

## Chapter 6: Organizing your project

### Your project as a library

- __namespaces__ contain maps b/t symbols & references to vars

  - current namespace: `*ns*`; gotten w/ `(ns-name *ns*)`
  - if you want to use the symbol & not the thing it refers to, you need to precede it with a quote (`'`)

  ```clojure
  (map inc [1 2])
  ; => (2 3)
  
  '(map inc [1 2])
  ; => (map inc [1 2])
  ```

### Storing objects with `def`

- `def`: primary tool in Clojure for storing objects

  - process is called _interning a var_

  ```clojure
  (def great-books ["East of Eden" "The Glass Bead Game"])
  ; => #'user/great-books
  ```

- get a namespace's interned-vars using `ns-interns`

  ```clojure
  (ns-interns *ns*)
  
  (get (ns-inters *ns*) 'my-defined-symbol')
  ; => #'user/my-defined-symbol
  ```

- get the full map of symbols to vars with `(ns-map *ns*)`

- get the symbol corresponding to the var with `deref` and `#'`

  ```clojure
  (def chelsea ["Chelsea is" "so cool"])
  
  (deref #'user/chelsea)
  ; => ["Chelsea is" "so cool"]
  ```

- _name collision_: rewritting a symbol with a new value;

### Creating and switching to namespaces

- `create-ns` creates a namespace

  ```clojure
  (create-ns 'chelsea.app)
  ; => #<Namespace chelsea.app>
  ```

- __`in-ns`__ creates a namespace if it doesn't exist & switches into it

  ```clojure
  (in-ns 'chelsea.app)
  ; => #<Namespace chelsea.app>
  ```

- access another namespace's symbols w/ a fully qualified name, eg. `chelsea/printName`

#### `refer`

- `refer` gives you control over how you refer to symbols in other namespaces by essentially merging in the symbols into the current namespace

  - use `:only`, `:exclude`, and `:rename` to control what's merged & how

  ```clojure
  (clojure.core/refer 'chelsea.app')
  
  (clojure.core/refer 'cheese.taxonomy :only ['bries])
  (clojure.core/refer 'cheese.taxonomy :exclude ['bries])
  (clojure.core/refer 'cheese.taxonomy :rename {'bries 'yummy-bries})
  ```

- `defn-` define a private function

  - still technically referable to outside of its namespace by using `@#'some/private-var`

#### `alias`

- `alias` lets you define shorter names

  ```clojure
  (clojure.core/alias 'taxonomy 'cheese.taxonomy)
  ```

### Real project organization

#### The relationship between file paths and namespace names

- __`ns`__: primary way to create & manage namespaces
- interpreting a namespace name
  - creating a directory w/ `lein` makes the source code's root `src`
  - dashes in namespace names correspond to underscores in the filesystem
  - the component preceding a `.` corresponds to a directory

#### Requiring and using namespaces

- __`require`__ takes a symbol designating a namespace, ensures it exists & is ready for use, and then evaluates it

  - you can alias a namespace at the same time
  - example file header

  ```clojure
  (ns the-divine-cheese-code.core)
  ;; Ensure that the SVG code is evaluated
  (require 'the-divine-cheese-code.visualization.svg)
  
  ; alternatively,
  (require '[the-divine-cheese-code.visualization.svg :as svg])
  
  ;; Refer the namespace so that you don't have to use the
  ;; fully qualified name to reference svg functions
  (refer 'the-divine-cheese-code.visualization.svg)
  ```

- __`use`__ combines the functionality of `require` and `refer`

  - you can also use `:only`, `:exclude`, `:as`, & `:rename` with it

  ```clojure
  (use 'the-divine-cheese-code.visualization.svg)
  
  ; alternatively
  (use '[the-divine-cheese-code.visualization.svg :as svg])
  
  ;; more examples
  (use '[the-divine-cheese-code.visualization.svg :as svg :only [points]])
  ```

#### The `ns` macro

- example where you can control what's referred from `clojure-core`, using `ns`. It takes the same options as `refer`

  ```clojure
  (ns the-divine-cheese-code.core
    (:refer-clojure :exclude [println]))
  ```

- 6 possible references within `ns`:

  1. `(:refer-clojure)`
  2. `(:require)` works like the `require` function, and also lets you refer names & symbols
  3. `(:use)`
  4. `(:import)`
  5. `(:load)`
  6. `(:gen-class)`

```clojure
;; requiring multiple things
(ns the-divine-cheese-code.core
  (:require [the-divine-cheese-code.visualization.svg :as svg]
            [clojure.java.browse :as browse]))

;; referring names
(ns the-divine-cheese-code.core
  (:require [the-divine-cheese-code.visualization.svg :refer [points]]))

;; referring all symbols
(ns the-divine-cheese-code.core
  (:require [the-divine-cheese-code.visualization.svg :refer :all]))
```

## Chapter 7: Reading, evaluation, and macros

### An overview of Clojure's evaluation model

- __evaluation model__: [1] read textual source code, producing the data structures; [2] evaluate: traverse the data structures & use function application or var lookup based on the type
  - this makes it a __homoiconic__ language since it has a relationship b/t source code, data, & evaluation
    - it represents abstract syntax trees (ASTs) using lists, and your program is just a tree

### The reader

#### Reading

- `read-string` takes a string as an arg, processes it w/ Clojure's reader, & returns a data structure

  ```clojure
  (read-string "(+ 1 2)")
  ; => (+ 1 2)
  
  (list? (read-string "(+ 1 2)"))
  ; => true
  
  (eval (read-string "(+ 1 2)"))
  ; => 3
  ```

#### Reader macros

- __reader macros__: sets of rules for transforming text into data structures
  - designated by macro characters, eg. `'` (quote), `#`, & `@` (deref)

### The evaluator

#### Things that evaluate to themselves

- `true`, `false`, `{}`, `:hello`, `()`

#### Symbols

- Clojure resolves a symbol by:

  1. Looking up whether the symbol names a special form. If it doesn’t...
  2. Looking up whether the symbol corresponds to a local binding. If it doesn’t...
  3. Trying to find a namespace mapping introduced by def. If it doesn’t...
  4. Throwing an exception

- __local binding__: any association b/t a symbol & a value that _wasn't_ created by `def` (eg. created by `let`)

  - the most recently defined binding takes precedence

  ```clojure
  (let [x 5]
    (+ x 3))
  ; => 8
  
  ;; most recent one takes precedence
  (let [x 5]
    (let [x 6]
      (+ x 3)))
  ; => 9
  ```

#### Lists

- if a list isn't empty, it's evaluated as a call to the first element in the list

##### Function calls

```clojure
(+ 1 2)
; => 3
```

##### Special forms

- instead of resolving symbols, they create associations b/t symbols & values,
- `def`, `let`, `loop`, `fn`, `do`, `recur`

#### Macros

- can create macros to change how clojure reads things, eg. infix notation

  ```clojure
  (let [infix (read-string "(1 + 1)")]
    (list (second infix) (first infix) (last infix)))
  ; => (+ 1 1)
  ```

- the data structures returned by a function isn't evaluated, but if it's returned by a macro, it is

  - __macro expansion__: the process of determining the return value of a macro

- `macroexpand` shows you which data structure a macro returns before the data structure is evaluated

  ```clojure
  (macroexpand '(ignore-last-operand (+ 1 2 10)))
  ; => (+ 1 2)
  
  ```

  Examples

```clojure
;; 1
(defmacro ignore-last-operand
  [function-call]
  (butlast function-call))

;; 2
(defmacro infix
  [infixed]
  (list (second infixed)
        (first infixed)
        (last infixed)))

(infix (1 + 2))
; => 3
```

#### Syntactic abstraction and the `->` macro

- threading/stabby macro: `->` lets you rewrite functions so you don't need to read it right-to-left; it also lets you omit parentheses

  ```clojure
  ;; before
  (defn read-resource
    "Read a resource into a string"
    [path]
    (read-string (slurp (clojure.java.io/resource path))))
  
  ;; after
  (defn read-resource
    [path]
    (-> path
        clojure.java.io/resource
        slurp
        read-string))
  ```

## Chapter 8: Writing macros

- macros allow you to derive a lot of the built-in functionality
- you can create multiple-arity macros

### Building lists for evaluation

#### Distinguishing symbols and values

- you often need to turn off evaluation when trying to get a macro to do what you want

  ```clojure
  ;; goal: a result like this
  (let [result expression]
    (println result)
    result)
  
  ;; this fails @ printing the expression and returning the expression's value;
  (defmacro my-print-whoopsie
   [expression]
   (list let [result expression]
         (list println result)
         result))
  
  ;; this succeeds
  (defmacro my-print
    [expression]
    (list 'let ['result expression]
          (list 'println 'result)
          'result))
  ```

#### Simple quoting

- quoting a symbol returns a symbol regardless of whether it has a value associated with it

#### Syntax quoting: ```

- differences between this and normal quoting

  - returns fully qualified symbols (with the namespace included); avoids name collisions

  - allows you to unquote forms using `~`

    ```clojure
    `(+ 1 ~(inc 1))
    ; => (clojure.core/+ 1 2)
    
    ;; otherwise
    `(+ 1 (inc 1))
    ; => (clojure.core/+ 1 (clojure.core/inc 1))
    ```

### Things to watch out for

#### Variable capture

- __variable capture__: when a macro introduces a binding that eclipses an existing binding

  - helps prevent accidentally capturing variables w/i macros; you can use `gensym` if you want `let` bindings in your macro
  - `gensym` creates a new unique symbol, you can do the same with a `#` after the symbol

  ```clojure
  (def message "Good job!")
  (defmacro with-mischief
    [& stuff-to-do]
    (concat (list 'let ['message "Oh, big deal!"])
            stuff-to-do))
  
  (with-mischief
    (println "Here's how I feel about that thing you did: " message))
  ; => Here's how I feel about that thing you did: Oh, big deal!
  
  ;;; with gensym
  (defmacro without-mischief
    [& stuff-to-do]
    (let [macro-message (gensym 'message)]
      `(let [~macro-message "Oh, big deal!"]
         ~@stuff-to-do
         (println "I still need to say: " ~macro-message))))
  
  (without-mischief
    (println "Here's how I feel about that thing you did: " message))
  ; => Here's how I feel about that thing you did:  Good job!
  ; => I still need to say:  Oh, big deal!
  ```

#### Double evaluation

- __double evaluation__: when a form passed to a macro as an arg gets evaluated more than once

  - occurs because of a duplicate statement in the macro expansion

  ```clojure
  ;; this sleeps twice
  (defmacro report
    [to-try]
    `(if ~to-try
       (println (quote ~to-try) "was successful:" ~to-try)
       (println (quote ~to-try) "was not successful:" ~to-try)))
  
  ;; Thread/sleep takes a number of milliseconds to sleep for
  (report (do (Thread/sleep 1000) (+ 1 1)))
  
  ;; because this is the macro expansion
  (if (do (Thread/sleep 1000) (+ 1 1))
    (println '(do (Thread/sleep 1000) (+ 1 1))
             "was successful:"
             (do (Thread/sleep 1000) (+ 1 1)))
  
    (println '(do (Thread/sleep 1000) (+ 1 1))
             "was not successful:"
             (do (Thread/sleep 1000) (+ 1 1))))
  
  ;; fix it by binding it to an auto-gensym'd symbol
  (defmacro report
    [to-try]
    `(let [result# ~to-try]
       (if result#
         (println (quote ~to-try) "was successful:" result#)
         (println (quote ~to-try) "was not successful:" result#))))
  ```

#### Macros all the way down

- when everything you're using is a macro, so values aren't what you'd expect

## Chapter 9: Concurrent and Parallel Programming

### Futures, delays, and promises

#### Futures

- __`future`__ is used to define a task & put it on another thread, w/o requiring the result immediately

  - is run once and its result is cached
  - `future` func returns a reference value you can use to request the result
    - you need to dereference it w/ `deref` or `@`
    - dereferencing is blocked until the future is done running

  ```clojure
  (future (Thread/sleep 4000)
          (println "I'll print after 4 seconds"))
  (println "I'll print immediately")
  
  ;; dereferencing
  (let [result (future (println "this prints once")
                       (+ 1 1))]
    (println "deref: " (deref result))
    (println "@: " @result))
  ; => "this prints once"
  ; => deref: 2
  ; => @: 2
  ```

- you can set a timeout on the `future` by passing a number of milliseconds

  ```clojure
  ;; wait at most 10ms, use timeout value 5 if it times out
  (deref (future (Thread/sleep 1000) 0) 10 5)
  ; => 5
  ```

- check whether a `future` is done running

  ```clojure
  (realized? (future (Thread/sleep 1000)))
  ; => false
  
  (let [f (future)]
    @f
    (realized? f))
  ; => true
  ```

#### Delays

- __`delay`__ let you define a task w/o having it execute or w/o requiring the result immediately

  - is also run once and its result is cached

  ```clojure
  (def my-delay
    (delay (let [message "This is my delay"]
             (println "First deref:" message)
             message)))
  ```

- use __`force`__ to evaluate a delay & dereference it

  ```clojure
  (force my-delay)
  ; => First deref: This is my delay
  ; => "This is my delay"
  
  @my-delay
  ; => "This is my delay"
  ```

#### Promises

- __`promise`__ let you express that you expect a result w/o having to define the task that'll produce it, or when the task should run

  - you can deliver a result to a promise using __`deliver`__
  - you get the result by dereferencing the promise
  - can only be written once
  - if you dereference the promise w/o receiving the value, the program blocks until you do

  ```clojure
  (def my-promise (promise))
  (deliver my-promise (+ 1 2))
  @my-promise
  ; => 3
  ```

## Chapter 10: Atoms, Refs, and Vars

### Atoms

- __reference types__ let you manage identities of objects

  - __`atom`__ allows you to name an identity & retrieve its state

  ```clojure
  (def person (atom {:name "Chelsea"
                     :age 19}))
  
  @person
  ; => {:name "Chelsea", :age 19}
  ```

- use __`swap!`__ to update the reference

  - it produces a new value, updates the atom to refer to the new value, and returns the new value
  - you can update many properties

  ```clojure
  (swap! person
         (fn [current-state]
           (merge-with + current-state {:age 20})))
  ; => {:name "Chelsea", :age 20}
  
  @person
  ; => {:name "Chelsea", :age 20}
  ```

- allows you to keep multiple states

  ```clojure
  (let [num (atom 1)
        s1 @num]
    (swap! num inc)
    (println "State 1:" s1)
    (println "Current state:" @num))
  ; => State 1: 1
  ; => Current state: 2
  ```

- you can use __`reset`__ to change an atom back

  ```clojure
  (reset! person {:name "Chelsea"
                  :age 19})
  ```

### Watches and validators

#### Watches

- __watch__ takes 4 args: [1] a key, [2] the reference being watched, [3] its previous state, [4] its new state

  - `add-watch`'s form: `(add-watch [ref key] [watch function])`

  ```clojure
  (defn aged-alert
    [key watched old-state new-state]
    (if (> (:age new-state) (:age old-state)))
      (do
        (println "Just aged a year.")))
  
  (add-watch person :age aged-alert)
  ```

#### Validators

- __validators__ allow you to specify what states are OK for a reference to have, attached during atom creation

  - invalid reference state will be thrown if you violate the validator
  - throw an exception for give a more descriptive error message

  ```clojure
  (defn age-validator
    [{:keys [age]}]
    (> -1))
  
  ;; with exception thrown
  (defn age-validator
    [{:keys [age]}]
    ( or (> -1))
      (throw (IllegalStateException. "You can't have a negative age."))
  
  (def person (atom {:name "Chelsea"
                     :age 19
                     :validator age-validator}))
  ```

- modify refs with __`alter`__, but must be done w/i a transaction

  - behavior:
    1. reach outside the transaction & read the ref's current state
    2. compare the  current state to the state the ref started w/ w/i the transaction
    3. if the 2 differ, make the transaction retry
    4. otherwise, commit the altered ref state

#### `commute`

- __`commute`__ updates the ref's state w/i a transaction, too, with slightly different behavior
  - doesn't retry; only use if you're sure your refs won't be in an invalid state
  - behavior:
    1. reach outside the transaction & read the ref's current state
    2. run the `commute` function again using the current state
    3. commit the result

### Vars

#### Dynamic binding

- used for resources that one or more functions target

- creation using `^:dynamic`:

  ```clojure
  (def ^:dynamic *my-dynamic-var* "its value")
  ```

- change the value temporarily with __`binding`__

  ```clojure
  (binding [*my-dynamic-var* "changed value"]
    *my-dynamic-var*)
  ; => "changed value"
  ```

- if you access a dynamically bound var from within a manually created thread, the var will evaluate to the original value

#### Altering the var root

- when you create a new var, the initial value that you supply is its __root__

- __`alter-var-root`__ allows you to permanently change the root value

  ```clojure
  (def power-source "hair")
  (alter-var-root #'power-source (fn [_] "7-eleven parking lot"))
  power-source
  ; => "7-eleven parking lot"
  ```

- use __`with-redefs`__  to temporarily alter the var's root, & it also appears in child threads

  ```clojure
  (with-redefs [*out* *out*]
          (doto (Thread. #(println "with redefs allows me to show up in the REPL"))
            .start
            .join))
  ```

### Stateless concurrency and parallelism with `pmap`

- __`pmap`__ gives you concurrency performance benefits! performs a `map` in parallel

  - sometimes it isn't worth it since there's overhead in creating and coordinating threads

  ```clojure
  ;; test how long it takes to map something
  (time (dorun (pmap clojure.string/lower-case some-list-of-names)))
  ```

- adjust the amount of things a thread works on by adjusting the grain size

  ```clojure
  ;; example macro
  (defn ppmap
    "Partitioned pmap, for grouping map ops together to make parallel
    overhead worthwhile"
    [grain-size f & colls]
    (apply concat
     (apply pmap
            (fn [& pgroups] (doall (apply map f pgroups)))
            (map (partial partition-all grain-size) colls))))
  (time (dorun (ppmap 1000 clojure.string/lower-case orc-name-abbrevs)))
  ; => "Elapsed time: 44.902 msecs"
  ```

## Chapter 11: Concurrent processes with `core.async`

### Getting started with processes

- __`chan`__ creates a channel
  - channels communicate messages; you can put messages on them & take them off
  - processes wait for put & take to complete before continuing to execute
- __`go`__ creates a new process
  - everything runs concurrently on a separate thread if put within the `go` expression
  - thread pool count: 2 + (num cores on your machine)
- __`<!`__ is the take function
- __`>!!`__ is the put function; returns `true` when successfully completes
  - the process blocks until another process takes the message
  - the processes act independently & don't know of each other

#### Buffering

- creating buffered channels means you can just drop off a message and go off to do whatever, rather than waiting on someone to take it

  ```clojure
  (def echo-buffer (chan 2))
  (>!! echo-buffer "ketchup")
  ; => true
  (>!! echo-buffer "ketchup")
  ; => true
  (>!! echo-buffer "ketchup")
  ; This blocks because the channel buffer is full
  ```

- a `sliding-buffer` is like a FIFO buffer

- a `dropping-buffer` discards values in a last-in, first-out way

#### Blocking and parking

- use `!!` w/ the put & take methods if it's _inside the go block_
  - put: `>!` (non-blocking) or `>!!`
  - take: `<!` (non-blocking) or `<!!`
- outside the go block, you need to use 2 exclamation points
  - put: `>!!` (blocking)
  - take: `<!!` (blocking)

#### `thread`

- you want to use blocking take & put when you use threads

- `thread` creates a thread and executes a process; it returns a channel with the return value of the process in it

  ```clojure
  (let [t (thread "chili")]
    (<!! t))
  ; => "chili"
  ```

- use threads, instead of a `go`-block for long-running tasks so you don't clog the thread pool

- use the blocking put & take variants w/ `thread`

- if there's nothing to take, you get a `nil`

### `alts!!`

- __`alts!!`__ lets you use the result of the first successful channel operation

  - takes a vector of channels as its argument, and does a blocking take on all of them simultaneously
  - returns a vector w/ the first element being the value taken, & the second being the channel it was taken from
  - you can give it a timeout channel, so it waits a specified number of seconds before timing out

  ```clojure
  (let [c1 (chan)]
    (upload "serious.jpg" c1)
    (let [[headshot channel] (alts!! [c1 (timeout 20)])]
      (if headshot
        (println "Sending headshot notification for" headshot)
        (println "Timed out!"))))
  ; => Timed out!
  ```

- use it for put operations by putting a vector inside the vector you pass in

  ```clojure
  (let [c1 (chan)
        c2 (chan)]
    (go (<! c2))
    (let [[value channel] (alts!! [c1 [c2 "put!"]])]
      (println value)
      (= channel c2)))
  ; => true
  ; => true
  ```

- __`alts!`__: the parking alternative (continues execution instead of just waiting on results) to use inside `go` blocks

### Process pipelines to escape callback hell

- you can make it so each unit of logic lives in its own isolated process, and all communication between units of logic occurs through explicitly defined input and output channels

  ```clojure
  (defn upper-caser
    [in]
    (let [out (chan)]
      (go (while true (>! out (clojure.string/upper-case (<! in)))))
      out))
  
  (defn reverser
    [in]
    (let [out (chan)]
      (go (while true (>! out (clojure.string/reverse (<! in)))))
      out))
  
  (defn printer
    [in]
    (go (while true (println (<! in)))))
  
  (def in-chan (chan))
  (def upper-caser-out (upper-caser in-chan))
  (def reverser-out (reverser upper-caser-out))
  (printer reverser-out)
  
  (>!! in-chan "redrum")
  ; => MURDER
  
  (>!! in-chan "repaid")
  ; => DIAPER
  ```

## Chapter 12: Working with the JVM

### The JVM

- __just-in-time compilation__: a running JVM executing bytecode by translating it to machine code on the fly
- Java Archive file (JAR file)

### Clojure App JARs

- the __`(:gen-class)`__ directive in the namespace declaration tells Clojure to generate a class for the namespace

### Java interop

#### Interop syntax

- call methods on an object with `(.methodName object optional-args go-here)`

  ```clojure
  (.toUpperCase "chelsea")
  ; => "CHELSEA"
  ```

#### Creating and mutating objects

- `(new ClassName optional-args)` or `(ClassName. optional-args)` (used more often)

  ```clojure
  (new String)
  ; => ""
  
  (String.)
  ; => ""
  ```

- Clojure's __`doto`__ macro let's you execute multiple methods on the same object

  ```clojure
  (doto (java.util.Stack.)
    (.push "First pushed")
    (.push "Second pushed"))
  ; => ["First pushed" "Second pushed"]
  ```

#### Importing

- `import` lets you refer to imported classes w/o their namespace prefix

- import multiple classes at once

  ```clojure
  (import [package.name1 ClassName1 ClassName2]
          [package.name2 ClassName3 ClassName4])
  
  ;; more often done in the ns macro
  (ns chelsea.namespace
    (:import [java.util Date Stack]
             [java.net Proxy URI]))
  ```

#### The `System` Class

- `exit`, `getEnv`, `getProperty`
  - `getEnv` returns all of your system’s environment variables as a map

### Files and I/O

- commonly used: `clojure.java.io`, `java.io.File`, `java.io.Reader`, `java.io.BufferedReader`, `java.io.FileReader`, `java.io.BufferedWriter`, `java.io.FileWriter`

  ```clojure
  (let [file (java.io.File. "/")]
    (println (.exists file))
    (println (.canWrite file))
    (println (.getPath file)))
  ; => true
  ; => false
  ; => /
  ```

- Clojure's __`spit`__ writes to a resource, and __`slurp`__ reads from one

  ```clojure
  (spit "/tmp/todo-list"
  "- first thing
  - second thing")
  
  (slurp "/tmp/todo-list")
  
  ; => "- first thing
        - second thing"
  ```

- etc: `StringReader`, `StringWriter`

## Chapter 13: Creating and extending abstractions with multimethods, protocols, and records

- polymorphism: one name for one purpose with multiple implementations depending on type

### Multimethods

- __multimethods__ let you define a dispatching function, which produces dispatching values that're used to determine which method to use

```clojure
(defmulti full-moon-behavior (fn [were-creature] (:were-type were-creature)))
(defmethod full-moon-behavior :wolf
  [were-creature]
  (str (:name were-creature) " will howl and murder"))
(defmethod full-moon-behavior :simmons
  [were-creature]
  (str (:name were-creature) " will encourage people and sweat to the oldies"))

(full-moon-behavior {:were-type :wolf
                     :name "Rachel from next door"})
; => "Rachel from next door will howl and murder"

(full-moon-behavior {:name "Andy the baker"
                     :were-type :simmons})
; => "Andy the baker will encourage people and sweat to the oldies"

(defmethod full-moon-behavior nil
  [were-creature]
  (str (:name were-creature) " will stay at home and eat ice cream"))

(full-moon-behavior {:were-type nil
                     :name "Martin the nurse"})
; => "Martin the nurse will stay at home and eat ice cream"

(defmethod full-moon-behavior :default
  [were-creature]
  (str (:name were-creature) " will stay up all night fantasy footballing"))

(full-moon-behavior {:were-type :office-worker
                     :name "Jimmy from sales"})
; => "Jimmy from sales will stay up all night fantasy footballing"
```

### Protocols

- a __protocol__ is a collection of 1+ polymorphic operations

  - if you want two different protocols to include methods with the same name, you’ll need to put the protocols in different namespaces
  - cannot have rest arguments

  ```clojure
  (ns my-protocols)
  (defprotocol MyProtocol
    "My docstring"
    (methodSignature1 [x] "A docstring for the method")
    (methodSignature2 [x] [x y] "I take two"))
  ```

- you can extend types with __`extend-type`__, even all objects (`java.lang.Object`)!

  ```clojure
  (extend-type java.lang.String
    Wat
    exclaim [x] (str x "!"))
  
  (exclaim "lol")
  ; => "lol!"
  ```

- __`extend-protocol`__ lets you define protocol implementations for multiple types at once

  ```clojure
  (extend-protocol Wat
    java.lang.String
    (exclaim [x] (str x "!"))
  
    java.lang.Object
    (rando [x] "this is some rando thing that's returned")
    (feelings-about
      ([x] "ok")
      ([x y] (str "feeling great about" y))))
  ```

### Records

- __records__ are maplike data types, created with __`defrecord`__

  - they differ from maps since you specify fields for records (slots for the data)

  ```clojure
  (ns records)
  (defrecord Person [name age hobbies])
  ```

- how to create an instance of a record

  ```clojure
  ;; Way 1
  (Person. "Chelsea" 19 ["doing", "things"])
  ; => #records.Person{:name "Chelsea", :age 19, :hobbies ["doing", "things"]}
  
  ;; Way 2
  (-> Person "Chelsea" 19 ["things"])
  
  ;; Way 3
  (map->Person {:name "Chelsea" :age 19 :hobbies ["cool"]})
  ```

- you can look up record values in the same way you look up map values, and you can also use Java field access interop

  ```clojure
  (.name chelsea)
  ; => "Chelsea"
  
  (:name chelsea)
  ; => "Chelsea"
  
  (get chelsea :name)
  ; => "Chelsea"
  ```

- testing for equality ensures all fields are equal and that the comparands have the same type

  ```clojure
  (= chelsea {:name "Chelsea"})
  ; => false
  ```

- if you `dissoc` a field, the result's type will be a regular map, not a record

- accessing a map is slower than accessing record type

