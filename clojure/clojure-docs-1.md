# Clojure Docs

## Concurrency and parallelism

Notes taken on CDS' [concurrency and parallelism guide](http://clojure-doc.org/articles/language/concurrency_and_parallelism.html).

### Introduction and terminology

* __immutable data structures__: data structures that, when changed, produce new data structures (copies), possibly with optimizations such as internal structural sharing

* concurrency hazards:
  - __concurrency hazards__: conditions occurring in concurrent programs that prevent the program from being correct
  - race condition
  - deadlock
  - __livelock__: when 2+ threads are technically running computations, but not making any useful work
  - starvation

### Identity/value separation ("on State and Identity")

* in Clojure, values are immutable, and new values are produced upon attempts to modify a value (data structure), thus making them persistent data structures

* __identity__: a named entity that changes over time & references a value

### Clojure reference types

* classifications
  - __coordinated__: an operation depending on cooperation from other operations in order to produce correct results
    + eg. banking operation involving multiple accounts
  - __uncoordinated__: an operation that doesn't affect other operations in any way
    + eg. downloading 100 web pages
  - __synchronous__: when the caller's thread waits, blocks, or sleeps until it's given access to a given resource or context
  - __asynchronous__: operations that can be started/scheduled without blocking the caller's thread

* reference types' classifications:
  - __refs__ are coordinated & synchronous
  - __atoms__ are uncoordinated & synchronous
  - __agents__ are uncoordinated & asynchronous

#### Atoms

* __atoms (`atom`)__: references changing such that changes immediately become visible to all threads, & are guaranteed to be synchronized by the JVM (ie. atomically)
  - atoms are uncoordinated, synchronous identities

  - creating an atom

    ```clojure
    (def created-films (atom []))
    (let [another-atom (atom [])])
    ```

  - accessing an atom's value

    ```clojure
    ; accessing an atom's value: reader macro
    @created-films

    ; accessing an atom's value: dereferencing
    (deref created-films)
    ```

  - mutating an atom via `clojure.core/swap!`

    ```clojure
    (swap! created-films conj 290098)
    ;; => [290098]
    ```

    + differences: in clojure, we mutate atoms with a retriable function, rather than setting a value like in java

  - mutating an atom via `clojure.core/reset!`

    ```clojure
    (reset! created-films [101920])
    ;; => [101920]

    + it sets the atom to a specific value
    + should be used sparingly in implementation code, but makes sense to use between test executions

#### Agents

* __agents (`agent`)__: references that're updated asynchronously
  - ie. updates happen in a thread pool, later, at an unknown point in time
  - agents are identities providing uncoordinated, asynchronous updates
  - example use case: counting the 40x and 50x status code responses
  - best used for cases that don't require strict consistency for reads

  - creating an `agent`

    ```clojure
    (def errors-counter (agent 0))

    @errors-counter
    ;; => 0
    ```

  - mutating an agent with `clojure.core/send`

    ```clojure
    (send errors-counter inc)
    ;; => #<Agent@523a123: 0>

    @errors-counter
    ;; => 1
    ```

    + uses a fixed-size thread pool, so using blocking operations won't yield good throughput

  - mutating an agent with `clojure.core/send-off`
    + syntactically the same, but uses a growing thread pool, meaning that blocking operations aren't a problem for it, as long as the JVM has enough resources to create and run all the threads

##### Using custom executors with agents

* default thread pool size: `number of available CPU cores + 2`

* you can use `clojure.core/set-agent-send-executor!` to control what thread pool (executor) is used

  ```clojure
  (import java.util.concurrent.Executors)

  (set-agent-send-executor! (Executors/newFixedThreadPool 32))
  ;; clojure.core/send will now use a fixed size thread pool w/ 32 threads
  ```

* `clojure.core/send-via` lets you specify an executor to be used on a case-by-case basis

  ```clojure
  (import java.util.concurrent.Executors)

  (def custom-pool (Executors/newFixedThreadPool 32))

  (send-via custom-pool stream-agent inc)
  ```

##### Agents and error handling

* when the modification of an `agent` fails, the `agent` will be in an error state, and the operation will return `nil`

  ```clojure
  (send errors-counter / 0)
  ;; => nil
  ```

* `clojure.core/agent-error` allows you to access the exception that occurred with the state mutation attempt

  ```clojure
  (agent-error errors-counter)
  ;; => #<ArithmeticException java.lang.ArithmeticException: Divide by zero>
  ```

* use `clojure.core/restart-agent` to give it a new initial value

  ```clojure
  (restart-agent errors-counter 0)
  ```

* you can have it ignore errors, by using the `:error-mode :continue` option, along with an `:error-handler`


### Dereferencing

### Delays

### Futures

### Promises

### Watches and validators

### Using intrinsic locks (`synchronized`) in Clojure

### Reducers (Clojure 1.5+)

### `java.util.concurrent`

### Other approaches to concurrency

### Runtime parallelism
