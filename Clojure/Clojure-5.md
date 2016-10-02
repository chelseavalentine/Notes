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

#### Treating lists, vectors, sets, and maps as sequences

* __sequence__ / __seq__: a collection of elements organized in linear order

#### Abstraction through indirection

* __indirection__: a mechanism a language employs so that one name can have multiple related meanings
  - eg. `first` has multiple data-structure-specific meanings
  - different function bodies are used based on the type of the argument


### Seq function examples

#### `map`

* you can give it multiple collections, so that the first arg transforms the second, the second the third, and so on
  - but the mapping function needs to be able to take a number of args equal to the num of collections

  ```clojure
  (map str ["a" "b" "c"] ["A" "B" "C"])
  ```

* you can also pass map a collection of functions
  ```clojure
  (defn stats
    [numbers]
    (map #(% numbers) [sum count avg]))
  ```

#### `reduce`

* processes each element in a sequence to build a result

#### `take`, `drop`, `take-while`, and `drop-while`

* `take` & `drop` take a number & a seq; then they either crop values or drop values, & then return an array

* __`take-while`__ & __`drop-while`__ take a predicate function to decide when to stop taking/dropping
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
