# Clojure

#### Defining functions

* parts of a function definition:
  - `defn`
  - name
  - a docstring describing the function (optional)
    + viewable w/ `doc` function in REPL, format: `(doc [name])`
  - parameters listed in brackets
  - function body

  ```clojure
  ➊ (defn wat
  ➋   "Says 'wat'"
  ➌   [name]
  ➍   (str "wat " name " wat "
    "wat"))

  (wat "Person")
  ; => "wat Person wat"
  ```

* __arity__: the number of parameters a function has ("[number]-arity function")
  ```clojure
  (defn no-params
    []
    "No params heree")

  (defn two-params
    [x y]
    (str "Two params yo: " x y))
  ```

* functions support __arity overloading__
  ```clojure
  (defn multi-arity
    ;; 3-arity arguments and body
    ([first-arg second-arg third-arg]
       (do-things first-arg second-arg third-arg))
    ;; 2-arity arguments and body
    ([first-arg second-arg]
       (do-things first-arg second-arg))
    ;; 1-arity arguments and body
    ([first-arg]
       (do-things first-arg)))
  ```

* you can define a __rest parameter__ with `&`, which pipes all of the arguments into the array name defined after the `&`
  ```clojure
  (defn codger-communication
    [whippersnapper]
    (str "Get off my lawn, " whippersnapper "!!!"))

  (defn codger
    [& whippersnappers]
    (map codger-communication whippersnappers))

  (codger "Anne" "Bill" "Chris")
  ; => ("Get off my lawn, Anne!!!"
        "Get off my lawn, Bill!!!"
        "Get off my lawn, Chris!!!")
  ```

* __destructuring__ allows you to bind names to values w/i a collection

* destructuring a vector
  ```clojure
  ;; Return the first element of a collection
  (defn print-choices
    [[first-thing second-choice & unimportant-choices]]
    (println (str "Your first choice is: " first-thing))
    (println (str "Your second choice is: " second-choice))
    (println (str "Unimportant: " (clojure.string/join ", " unimportant-choices))))

  (my-first ["oven" "bike" "war-axe" "okay" "err" "crap"])
  ; => Your first choice is: oven
  ; => Your second choice is: bike
  ; => Unimportant: war-axe, okay, err, crap
  ```

* you can destructure maps too by passing a map as a parameter
  - you can retain access to the original map by passing the `:as` keyword

  ```clojure
  (defn announce-treasure-location
    [{lat :lat lng :lng}]
    (println (str "The treasure is at lat: " lat " lng: " lng)))

  (announce-treasure-location {:lat 28.22 :lng 81.33})

  ; a simpler way to destructure that array uses keys
  (defn announce-treasure-location
    [{:keys [lat lng] :as treasure-location}]
    (println (str "The treasure is at lat: " lat " lng: " lng)))
  ```

* Clojure automatically returns the last form evaluated
  ```clojure
  (defn illustrative-function
    []
    (+ 1 304)
    30
    "joe")

  (illustrative-function)
  ; => "joe"
  ```
