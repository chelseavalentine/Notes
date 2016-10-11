# Clojure

### Real project organization

#### The relationship between file paths and namespace names

* __`ns`__: primary way to create & manage namespaces

* interpreting a namespace name
  - creating a directory w/ `lein` makes the source code's root `src`
  - dashes in namespace names correspond to underscores in the filesystem
  - the component preceding a `.` corresponds to a directory

#### Requiring and using namespaces

* __`require`__ takes a symbol designating a namespace, ensures it exists & is ready for use, and then evaluates it
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

* __`use`__ combines the functionality of `require` and `refer`
  - you can also use `:only`, `:exclude`, `:as`, & `:rename` with it
  ```clojure
  (use 'the-divine-cheese-code.visualization.svg)

  ; alternatively
  (use '[the-divine-cheese-code.visualization.svg :as svg])

  ;; more examples
  (use '[the-divine-cheese-code.visualization.svg :as svg :only [points]])
  ```

#### The `ns` macro

* example where you can control what's referred from `clojure-core`, using `ns`. It takes the same options as `refer`
  ```clojure
  (ns the-divine-cheese-code.core
    (:refer-clojure :exclude [println]))
  ```

* 6 possible references within `ns`:
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

* __evaluation model__: [1] read textual source code, producing the data structures; [2] evaluate: traverse the data structures & use function application or var lookup based on the type
  - this makes it a __homoiconic__ language since it has a relationship b/t source code, data, & evaluation
    - it represents abstract syntax trees (ASTs) using lists, and your program is just a tree

### The reader

#### Reading

* `read-string` takes a string as an arg, processes it w/ Clojure's reader, & returns a data structure
  ```clojure
  (read-string "(+ 1 2)")
  ; => (+ 1 2)

  (list? (read-string "(+ 1 2)"))
  ; => true

  (eval (read-string "(+ 1 2)"))
  ; => 3
  ```

#### Reader macros

* __reader macros__: sets of rules for transforming text into data structures
  - designated by macro characters, eg. `'` (quote), `#`, & `@` (deref)

### The evaluator

#### Things that evaluate to themselves

* `true`, `false`, `{}`, `:hello`, `()`

#### Symbols

* Clojure resolves a symbol by:
  1. Looking up whether the symbol names a special form. If it doesn’t...
  2. Looking up whether the symbol corresponds to a local binding. If it doesn’t...
  3. Trying to find a namespace mapping introduced by def. If it doesn’t...
  4. Throwing an exception

* __local binding__: any association b/t a symbol & a value that _wasn't_ created by `def` (eg. created by `let`)
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

* if a list isn't empty, it's evaluated as a call to the first element in the list

##### Function calls

```clojure
(+ 1 2)
; => 3
```

##### Special forms

* instead of resolving symbols, they create associations b/t symbols & values,
* `def`, `let`, `loop`, `fn`, `do`, `recur`
