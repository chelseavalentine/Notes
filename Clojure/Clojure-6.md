# Clojure


#### Function composition instead of attribute mutation

* __function composition__: return val of a function is passed as an arg to another

### Cool things to do with pure functions

#### `comp`

* `comp` allows you to create a new function from the composition of any number of functions
  - applies functions like a composition, so if `(comp f1 f2 ... fn)`, then `f1(f2(..(fn(args))))`
  ```clojure
  ((comp inc *) 2 3)
  ; => 7
  ```

* can use to retrieve nested values
  ```clojure
  (def character
    {:name "Smooches McCutes"
     :attributes {:intelligence 10
                  :dexterity 5}})
  (def c-int (comp :intelligence :attributes))

  (c-int character)
  ; => 10
  ```

* make compositions of functions that take more than 1 arg by wrapping it in an anonymous function
  ```clojure
  (def spell-slots-comp (comp int inc #(/ % 2) c-int))
  ```

#### `memoize`

* memoization stores the args passed to a function, & the return value of the function
  - significance: subsequent calls to the function w/ the same args can return the result immediately

  ```clojure
  (def memo-sleepy-identity (memoize sleepy-identity))
  (memo-sleepy-identity "Mr. Fantastico")
  ; => "Mr. Fantastico" after 1 second

  (memo-sleepy-identity "Mr. Fantastico")
  ; => "Mr. Fantastico" immediately
  ```

### Misc

* `assoc-in` returns a map w/ the given value @ the specified nesting
  ```clojure
  (assoc-in {} [:cookie :monster :vocals] "Finntroll")
  ; => {:cookie {:monster {:vocals "Finntroll"}}}
  ```

* `get-in` lets you look up values in nested maps
  ```clojure
  (get-in {:cookie {:monster {:vocals "Finntroll"}}} [:cookie :monster])
  ; => {:vocals "Finntroll"}
  ```

## Chapter 6: Organizing your project

### Your project as a library

* __namespaces__ contain maps b/t symbols & references to vars
  - current namespace: `*ns*`; gotten w/ `(ns-name *ns*)`
  - if you want to use the symbol & not the thing it refers to, you need to precede it with a quote (`'`)
  ```clojure
  (map inc [1 2])
  ; => (2 3)

  '(map inc [1 2])
  ; => (map inc [1 2])
  ```

### Storing objects with `def`

* `def`: primary tool in Clojure for storing objects
  - process is called _interning a var_
  ```clojure
  (def great-books ["East of Eden" "The Glass Bead Game"])
  ; => #'user/great-books
  ```

* get a namespace's interned-vars using `ns-interns`
  ```clojure
  (ns-interns *ns*)

  (get (ns-inters *ns*) 'my-defined-symbol')
  ; => #'user/my-defined-symbol
  ```

* get the full map of symbols to vars with `(ns-map *ns*)`

* get the symbol corresponding to the var with `deref` and `#'`
  ```clojure
  (def chelsea ["Chelsea is" "so cool"])

  (deref #'user/chelsea)
  ; => ["Chelsea is" "so cool"]
  ```

* _name collision_: rewritting a symbol with a new value;

### Creating and switching to namespaces

* `create-ns` creates a namespace
  ```clojure
  (create-ns 'chelsea.app)
  ; => #<Namespace chelsea.app>
  ```

* __`in-ns`__ creates a namespace if it doesn't exist & switches into it
  ```clojure
  (in-ns 'chelsea.app)
  ; => #<Namespace chelsea.app>
  ```

* access another namespace's symbols w/ a fully qualified name, eg. `chelsea/printName`

#### `refer`

* `refer` gives you control over how you refer to symbols in other namespaces by essentially merging in the symbols into the current namespace
  - use `:only`, `:exclude`, and `:rename` to control what's merged & how
  ```clojure
  (clojure.core/refer 'chelsea.app')

  (clojure.core/refer 'cheese.taxonomy :only ['bries])
  (clojure.core/refer 'cheese.taxonomy :exclude ['bries])
  (clojure.core/refer 'cheese.taxonomy :rename {'bries 'yummy-bries})
  ```

* `defn-` define a private function
  - still technically referable to outside of its namespace by using `@#'some/private-var`

#### `alias`

* `alias` lets you define shorter names
  ```clojure
  (clojure.core/alias 'taxonomy 'cheese.taxonomy)
  ```
