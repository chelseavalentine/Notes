# Clojure

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
