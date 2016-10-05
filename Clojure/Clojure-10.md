# Clojure

## Chapter 9: Concurrent and Parallel Programming

### Futures, delays, and promises

#### Futures

* __`future`__ is used to define a task & put it on another thread, w/o requiring the result immediately
  - is run once and its result is cached
  - `future` func returns a reference value you can use to request the result
    - you need to dereference it w/ `deref` or `@`
    - dereferencing is blocked until the future is done running
  ```clojure
  (future (Thread/sleep 4000)
          (println "I'll print after 4 seconds"))
  (println "I'll print immediately")

  ;; dereferencing
  (let [result (future (println "this prints once")
                       (+ 1 1))]
    (println "deref: " (deref result))
    (println "@: " @result))
  ; => "this prints once"
  ; => deref: 2
  ; => @: 2
  ```

* you can set a timeout on the `future` by passing a number of milliseconds
  ```clojure
  ;; wait at most 10ms, use timeout value 5 if it times out
  (deref (future (Thread/sleep 1000) 0) 10 5)
  ; => 5
  ```

* check whether a `future` is done running
  ```clojure
  (realized? (future (Thread/sleep 1000)))
  ; => false

  (let [f (future)]
    @f
    (realized? f))
  ; => true
  ```

#### Delays

* __`delay`__ let you define a task w/o having it execute or w/o requiring the result immediately
  - is also run once and its result is cached
  ```clojure
  (def my-delay
    (delay (let [message "This is my delay"]
             (println "First deref:" message)
             message)))
  ```

* use __`force`__ to evaluate a delay & derefence it
  ```clojure
  (force my-delay)
  ; => First deref: This is my delay
  ; => "This is my delay"

  @my-delay
  ; => "This is my delay"
  ```

#### Promises

* __`promise`__ let you express that you expect a result w/o having to define the task that'll produce it, or when the task should run
  - you can deliver a result to a promise using __`deliver`__
  - you get the result by dereferencing the promise
  - can only be written once
  - if you derefence the promise w/o receiving the value, the program blocks until you do
  ```clojure
  (def my-promise (promise))
  (deliver my-promise (+ 1 2))
  @my-promise
  ; => 3
  ```

## Chapter 10: Atoms, Refs, and Vars

### Object-orientation

### Clojure

### Atoms

### Watches and validators

#### Watches

#### Validators

#### `commute`

### Vars

#### Dynamic binding

#### Altering the var root

### Stateless concurrency and parallelism with `pmap`
