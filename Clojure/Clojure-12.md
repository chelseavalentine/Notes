# Clojure

#### Creating and mutating objects

* `(new ClassName optional-args)` or `(ClassName. optional-args)` (used more often)
  ```clojure
  (new String)
  ; => ""

  (String.)
  ; => ""
  ```

* Clojure's __`doto`__ macro let's you execute multiple methods on the same object
  ```clojure
  (doto (java.util.Stack.)
    (.push "First pushed")
    (.push "Second pushed"))
  ; => ["First pushed" "Second pushed"]
  ```


#### Importing

* `import` lets you refer to imported classes w/o their namespace prefix

* import multiple classes at once
  ```clojure
  (import [package.name1 ClassName1 ClassName2]
          [package.name2 ClassName3 ClassName4])

  ;; more often done in the ns macro
  (ns chelsea.namespace
    (:import [java.util Date Stack]
             [java.net Proxy URI]))
  ```

#### The `System` Class

* `exit`, `getEnv`, `getProperty`
  - `getEnv` returns all of your system’s environment variables as a map

### Files and I/O

* commonly used: `clojure.java.io`, `java.io.File`, `java.io.Reader`, `java.io.BufferedReader`, `java.io.FileReader`, `java.io.BufferedWriter`, `java.io.FileWriter`
  ```clojure
  (let [file (java.io.File. "/")]
    (println (.exists file))
    (println (.canWrite file))
    (println (.getPath file)))
  ; => true
  ; => false
  ; => /
  ```

* Clojure's __`spit`__ writes to a resource, and __`slurp`__ reads from one
  ```clojure
  (spit "/tmp/todo-list"
  "- first thing
  - second thing")

  (slurp "/tmp/todo-list")

  ; => "- first thing
        - second thing"
  ```

* etc: `StringReader`, `StringWriter`

## Chapter 13: Creating and extending abstractions with multimethods, protocols, and records

* polymorphism: one name for one purpose with multiple implementations depending on type

### Multimethods

* __multimethods__ let you define a dispatching function, which produces dispatching values that're used to determine which method to use

```clojure
(defmulti full-moon-behavior (fn [were-creature] (:were-type were-creature)))
(defmethod full-moon-behavior :wolf
  [were-creature]
  (str (:name were-creature) " will howl and murder"))
(defmethod full-moon-behavior :simmons
  [were-creature]
  (str (:name were-creature) " will encourage people and sweat to the oldies"))

(full-moon-behavior {:were-type :wolf
                     :name "Rachel from next door"})
; => "Rachel from next door will howl and murder"

(full-moon-behavior {:name "Andy the baker"
                     :were-type :simmons})
; => "Andy the baker will encourage people and sweat to the oldies"

(defmethod full-moon-behavior nil
  [were-creature]
  (str (:name were-creature) " will stay at home and eat ice cream"))

(full-moon-behavior {:were-type nil
                     :name "Martin the nurse"})
; => "Martin the nurse will stay at home and eat ice cream"

(defmethod full-moon-behavior :default
  [were-creature]
  (str (:name were-creature) " will stay up all night fantasy footballing"))

(full-moon-behavior {:were-type :office-worker
                     :name "Jimmy from sales"})
; => "Jimmy from sales will stay up all night fantasy footballing"
```

### Protocols

* a __protocol__ is a collection of 1+ polymorphic operations
  - if you want two different protocols to include methods with the same name, you’ll need to put the protocols in different namespaces
  - cannot have rest arguments
  ```clojure
  (ns my-protocols)
  (defprotocol MyProtocol
    "My docstring"
    (methodSignature1 [x] "A docstring for the method")
    (methodSignature2 [x] [x y] "I take two"))
  ```

* you can extend types with __`extend-type`__, even all objects (`java.lang.Object`)!
  ```clojure
  (extend-type java.lang.String
    Wat
    exclaim [x] (str x "!"))

  (exclaim "lol")
  ; => "lol!"
  ```

* __`extend-protocol`__ lets you define protocol implementations for multiple types at once
  ```clojure
  (extend-protocol Wat
    java.lang.String
    (exclaim [x] (str x "!"))

    java.lang.Object
    (rando [x] "this is some rando thing that's returned")
    (feelings-about
      ([x] "ok")
      ([x y] (str "feeling great about" y))))
  ```
