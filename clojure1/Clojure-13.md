# Clojure

### Records

* __records__ are maplike data types, created with __`defrecord`__
  - they differ from maps since you specify fields for records (slots for the data)
  ```clojure
  (ns records)
  (defrecord Person [name age hobbies])
  ```

* how to create an instance of a record
  ```clojure
  ;; Way 1
  (Person. "Chelsea" 19 ["doing", "things"])
  ; => #records.Person{:name "Chelsea", :age 19, :hobbies ["doing", "things"]}

  ;; Way 2
  (-> Person "Chelsea" 19 ["things"])

  ;; Way 3
  (map->Person {:name "Chelsea" :age 19 :hobbies ["cool"]})
  ```

* you can look up record values in the same way you look up map values, and you can also use Java field access interop
  ```clojure
  (.name chelsea)
  ; => "Chelsea"

  (:name chelsea)
  ; => "Chelsea"

  (get chelsea :name)
  ; => "Chelsea"
  ```

* testing for equality ensures all fields are equal and that the comparands have the same type
  ```clojure
  (= chelsea {:name "Chelsea"})
  ; => false
  ```

* if you `dissoc` a field, the result's type will be a regular map, not a record

* accessing a map is slower than accessing record type
