# Clojure for the Brave and True

## Chapter 10: Atoms, Refs, and Vars

### Atoms

* __reference types__ let you manage identities of objects
  - __`atom`__ allows you to name an identity & retrieve its state
  ```clojure
  (def person (atom {:name "Chelsea"
                     :age 19}))

  @person
  ; => {:name "Chelsea", :age 19}
  ```

* use __`swap!`__ to update the reference
  - it produces a new value, updates the atom to refer to the new value, and returns the new value
  - you can update many properties
  ```clojure
  (swap! person
         (fn [current-state]
           (merge-with + current-state {:age 20})))
  ; => {:name "Chelsea", :age 20}

  @person
  ; => {:name "Chelsea", :age 20}
  ```

* allows you to keep multiple states
  ```clojure
  (let [num (atom 1)
        s1 @num]
    (swap! num inc)
    (println "State 1:" s1)
    (println "Current state:" @num))
  ; => State 1: 1
  ; => Current state: 2
  ```

* you can use __`reset`__ to change an atom back
  ```clojure
  (reset! person {:name "Chelsea"
                  :age 19})
  ```

### Watches and validators

#### Watches

* __watch__ takes 4 args: [1] a key, [2] the reference being watched, [3] its previous state, [4] its new state
  - `add-watch`'s form: `(add-watch [ref key] [watch function])`

  ```clojure
  (defn aged-alert
    [key watched old-state new-state]
    (if (> (:age new-state) (:age old-state)))
      (do
        (println "Just aged a year.")))

  (add-watch person :age aged-alert)
  ```

#### Validators

* __validators__ allow you to specify what states are OK for a reference to have, attached during atom creation
  - invalid reference state will be thrown if you violate the validator
  - throw an exception for give a more descriptive error message
  ```clojure
  (defn age-validator
    [{:keys [age]}]
    (> -1))

  ;; with exception thrown
  (defn age-validator
    [{:keys [age]}]
    ( or (> -1))
      (throw (IllegalStateException. "You can't have a negative age."))

  (def person (atom {:name "Chelsea"
                     :age 19
                     :validator age-validator}))
  ```

* modify refs with __`alter`__, but must be done w/i a transaction
  - behavior:
    1. reach outside the transaction & read the ref's current state
    2. compare the  current state to the state the ref started w/ w/i the transaction
    3. if the 2 differ, make the transaction retry
    4. otherwise, commit the altered ref state


#### `commute`

* __`commute`__ updates the ref's state w/i a transaction, too, with slightly different behavior
  - doesn't retry; only use if you're sure your refs won't be in an invalid state
  - behavior:
    1. reach outside the transaction & read the ref's current state
    2. run the `commute` function again using the current state
    3. commit the result

### Vars

#### Dynamic binding

* used for resources that one or more functions target
* creation using `^:dynamic`:
  ```clojure
  (def ^:dynamic *my-dynamic-var* "its value")
  ```

* change the value temporarily with __`binding`__
  ```clojure
  (binding [*my-dynamic-var* "changed value"]
    *my-dynamic-var*)
  ; => "changed value"
  ```

* if you access a dynamically bound var from within a manually created thread, the var will evaluate to the original value

#### Altering the var root

* when you create a new var, the initial value that you supply is its __root__

* __`alter-var-root`__ allows you to permanently change the root value
  ```clojure
  (def power-source "hair")
  (alter-var-root #'power-source (fn [_] "7-eleven parking lot"))
  power-source
  ; => "7-eleven parking lot"
  ```

* use __`with-redefs`__  to temporarily alter the var's root, & it also appears in child threads
  ```clojure
  (with-redefs [*out* *out*]
          (doto (Thread. #(println "with redefs allows me to show up in the REPL"))
            .start
            .join))
  ```

### Stateless concurrency and parallelism with `pmap`

* __`pmap`__ gives you concurrency performance benefits! performs a `map` in parallel
  - sometimes it isn't worth it since there's overhead in creating and coordinating threads
  ```clojure
  ;; test how long it takes to map something
  (time (dorun (pmap clojure.string/lower-case some-list-of-names)))
  ```

* adjust the amount of things a thread works on by adjusting the grain size
  ```clojure
  ;; example macro
  (defn ppmap
    "Partitioned pmap, for grouping map ops together to make parallel
    overhead worthwhile"
    [grain-size f & colls]
    (apply concat
     (apply pmap
            (fn [& pgroups] (doall (apply map f pgroups)))
            (map (partial partition-all grain-size) colls))))
  (time (dorun (ppmap 1000 clojure.string/lower-case orc-name-abbrevs)))
  ; => "Elapsed time: 44.902 msecs"
  ```
