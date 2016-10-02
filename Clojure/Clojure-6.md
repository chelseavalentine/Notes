# Clojure

### Lazy seqs

* __lazy seq__: a seq whose members aren't computed until you try to access them
  - map uses this

#### Infinite sequences

* can use `take`, along with `repeat` (for a value) and `repeatedly` (for a function) to create sequences
  ```clojure
  (concat (take 8 (repeat "na")) ["Batman!"])
  ; => ("na" "na" "na" "na" "na" "na" "na" "na" "Batmenz")

  (take 3 (repeatedly (fn [] (rand-int 10))))
  ; => (1 4 0)
  ```

### The collection abstraction

#### `into`

* __`into`__ lets you convert something into a data structure, eg. a sequence into a map
  ```clojure
  (map identity {:sunlight-reaction "Glitter!"})
  ; => ([:sunlight-reaction "Glitter!"])

  (into {} (map identity {:sunlight-reaction "Glitter!"}))
  ; => {:sunlight-reaction "Glitter!"}

  (into ["cherry"] '("pine" "spruce"))
  ; => ["cherry" "pine" "spruce"]
  ```

#### `conj`

* `into` merges values into the holder, whereas `conj` will also put the holder in, if that makes sense?
  ```clojure
  (conj [0] [1])
  ; => [0 [1]]

  (conj [0] 1)
  ; => [0 1]
  ```

### Function functions

#### `apply`

* `apply` explodes a seqable DS so it can be passed to a function expecting a rest parameter
  ```clojure
  (max 0 1 2)
  ; => 2

  (max [0 1 2])
  ; => [0 1 2]

  (apply max [0 1 2])
  ; => 2
  ```

#### `partial`

* `partial` takes a function & args, then returns a new function
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

* `complement` tests for falseness, rather than doing `not [condition]`

## Chapter 5: Functional programming

* decouples functions and data
* by programming to a small set of abstractions, you end up with more reusable, composable code

### Pure functions: What and why

* __pure function__:
  - has __referential transparency__: always returns same results given the same args
  - doesn't cause side-effects; can't change things outside of the function

### Living with immutable data structures

#### Recursion instead of for/while

* since immutable data structures, need to use recursion if you wanted to do a sum
  ```clojure
  (defn sum
    ([vals] (sum vals 0))
    ([vals accumulating-total]
      (if (empty? vals)
      accumulating-total
      (sum (rest vals) (+ (first vals) accumulating-total)))))
  ```

* `recur` is better for performance-wise for recursion, since clojure doesn't provide tail call optimization
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
