# Clojure for the Brave and True

#### `filter` and `some`

* __`filter`__ takes in a predicate function & tests true on all the elements of the sequence
  - if it's already sorted, `take-while` is more efficient

* __`some`__ tests whether a collection contains any values that test true for a predicate function
  - to return the statement that tests true, use `and`
    ```clojure
    (some #(and (> (:critter %) 3) %) food-journal)
    ; => {:month 3 :day 1 :human 4.2 :critter 3.3}
    ```

#### `sort`, `sort-by`, and `concat`

* `sort` in ascending order
* `sort-by` lets you apply a key function, eg. `count` to the sequence (check the docs)

* `concat` appends sequences to the end

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
