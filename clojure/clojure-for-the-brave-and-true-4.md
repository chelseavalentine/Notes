# Clojure for the Brave and True

#### `let`

* __`let`__ binds names to values
  - also creates scope
  - you can reference existing bindings w/i the binding
  - `take [number]` determines how many are assigned
  - you can use rest params

  ```clojure
  (let [x 3]
    x)
  ; => 3

  ;; referencing
  (def x 0)
  (let [x (inc x)] x)
  ; => 1

  ;; using take
  (def dalmatian-list
    ["Pongo" "Perdita" "Puppy 1" "Puppy 2"])
  (let [dalmatians (take 2 dalmatian-list)]
    dalmatians)
  ; => ("Pongo" "Perdita")

  ;; using rest params
  (let [[pongo & dalmatians] dalmatian-list]
    [pongo dalmatians])
  ; => ["Pongo" ("Perdita" "Puppy 1" "Puppy 2")]
  ```

* significance of `let`
  - increased clarify by naming things
  - saves an evaluation, esp. good for expensive function calls or if the expression has side effects

* __`into`__ puts elements into a vector
  ```clojure
  (into [] (set [:a :a]))
  ; => [:a]
  ```

#### `loop`

* __`loop`__ format: `loop [iteration [number]]` starts the loop w/ an initial value
  - __`recur`__ lets you call the function from w/i itself

  ```clojure
  (loop [iteration 0]
    (println (str "Iteration " iteration))
    (if (> iteration 3)
      (println "Goodbye!")
      (recur (inc iteration))))
  ; => Iteration 0
  ; => Iteration 1
  ; => Iteration 2
  ; => Iteration 3
  ; => Iteration 4
  ; => Goodbye!
  ```

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
