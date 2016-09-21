# Clojure

#### Anonymous functions

* functions don't need to have names; can declare one with `fn` or with `#` (made possible by reader macros)
  ```clojure
  (fn [param-list]
    function body)

  ; eg.
  ((fn [x] (* x 3)) 8)
  ; => 24

  ; high-order functions
  (def my-special-multiplier (fn [x] (* x 3)))
  (my-special-multiplier 12)
  ; => 36

  ; with #
  (#(* % 3) 8)
  ; => 24

  (map #(str "Hi, " %)
       ["Darth Vader" "Mr. Magoo"])
  ; => ("Hi, Darth Vader" "Hi, Mr. Magoo")
  ```

* the `%` indicates the argument passed to the function
  - `%` = `%1`, and you can indicate others with `%[number]`
  - `%&` works as a rest parameter

#### Returning functions

Function factory!

```clojure
(defn inc-maker
  "Create a custom incrementor"
  [inc-by]
  #(+ % inc-by))

(def inc3 (inc-maker 3))

(inc3 7)
; => 10
```

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
