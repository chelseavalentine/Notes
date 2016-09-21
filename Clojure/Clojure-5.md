# Clojure

#### Regular expressions

* format:
  ```clojure
  #"regular-expression"

  ;; eg.
  (re-find #"^left-" "left-eye")
  ; => "left-"

  (re-find #"^left-" "cleft-chin")
  ; => nil
  ```

#### `reduce`

* __`map`__ returns a list

* __`reduce`__ applies a function to a sequence (sequence specified by vector)
  - also it treats your map like a list of vectors

  ```clojure
  ;; sum with reduce
  (reduce + [1 2 3 4])
  ; => 10
  ```

* __`empty? []`__ tells you whether an array is empty

## Chapter 4: Core functions in depth

### Programming to abstractions

#### Treating lists, vectors, sets, and maps as sequences

#### `first`, `rest`, and `cons`

#### Abstraction through indirection

### Seq function examples

#### `map`

#### `reduce`

#### `take`, `drop`, `take-while`, and `drop-while`

#### `filter` and `some`

#### `sort` and `sort-by`

#### `concat`

### Lazy seqs

#### Demonstrating lazy seq efficiency

#### Infinite sequences

### The collection abstraction

#### `into`

#### `conj`

### Function functions

#### `apply`

#### `partial`

#### `complement`
