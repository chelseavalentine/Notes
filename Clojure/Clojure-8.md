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

#### The ns Macro

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

### An overview of Clojure's evaluaion model

### The reader

#### Reading

#### Reader macros

### The evaluator

#### These things evaluate to themselves

#### Symbols

#### Lists

##### Function calls

##### Special forms

#### Macros

#### Syntactic abstraction and the `->` macro
