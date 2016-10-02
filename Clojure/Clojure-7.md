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

### Storing objects with `def`

### Creating and switching to namespaces

#### `refer`

#### `alias

### Real project organization

#### The relationship between file paths and namespace names

#### Requiring and using namespaces

#### The ns Macro

### To catch a burglar
