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

### Pure functions: What and why

#### Pure functions are referentially transparent

#### Pure functions have no side effects

### Living with immutable data structures

#### Recursion instead of for/while

#### Function composition instead of attribute mutation

### Cool things to do with pure functions

#### `comp`

#### `memoize`

### Peg thing ?? wtf ??

#### Playing

#### Code organization

#### Creating the board

#### Moving pegs

#### Rendering and printing the board

#### Player interaction
