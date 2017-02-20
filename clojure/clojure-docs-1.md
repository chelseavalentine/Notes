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

#### Refs

* __refs (`ref`)__ ensure that multiple identities can be modified concurrently w/i a transaction
  - this means:
    + either all refs are modified or none are
    + there're no race conditions between involved refs
    + no possibility of deadlines between involved refs

  - refs are backed by Clojure's implementation of __STM (software transactional memory)__
    + a concurrency control method controlling access to shared storage, as an alternative to lock-based synchronization
    + uses multiversion concurrency control (MVCC) by taking a snapshot of the ref, making the changes in isolation of that, and then applying the result
    + detection of an update on the ref leads to a forced transaction retry

  - instantiating a ref

    ```clojure
    (def account-a (ref 0))
    ;; => #'user/account-a

    @account-a
    ;; => 0
    ```

* `clojure.core/dosync` starts a transaction, performs all modifications and commits changes
  - if a concurrently-running transaction modifies a ref within the current transaction before it commits, the current transaction is retried

##### `alter`

* `clojure.core/alter` is used to modify refs

  - its args: [1] ref, [2] function that takes the old value and returns a new value of the ref, and [3] any number of optional args to pass to the function

An example:

```clojure
(def account-a (ref 1000))
(def account-b (ref 1000))

(dosync
  (alter account-a + 100)
  (alter account-b - 100))

@account-a
;; => 1100
@account-b
;; => 900
```

##### `commute`

* you can use `clojure.core/commute` for operations whose order can be changed without affecting the result
  - it does the same as `alter`, but doesn't retry because you  can do it in any order

  - `commute` doesn't cause transaction conflicts

  ```clojure
  (dosync
    (commute account-a + 100)
    (commute account-b - 100))
  ```

##### Limitations of Refs

* __idempotent__: operations that when given the same inputs will produce the same results
  - may cause side effects (eg. updating a ref/atom), but may only produce the side effect once
  - pure functions, on the other hand, produce no side effects

* since transactions are retriable, you should structure your code into pure and idempotent code

* `clojure.core/io!` raises an exception if it's run when there's an STM transaction running
  - Clojure doesn't prevent you from doing I/O in transactions, it's a matter of programmer discipline

#### Vars

* __vars (`def`)__ are defined with `def`, and functions defined with `defn` are stored in vars
  - vars that only have root bindings (the default) have the same value regardless of the thread

  ```clojure
  (def url "http://en.wikipedia.org/wiki/Chelsea")

  (.start (Thread. (fn []
                     (println (format "url is %s" url)))))
  (.start (Thread. (fn []
                     (println (format "url is %s" url)))))
  ```

##### Dynamic scoping and thread-local bindings

* you can temporarily change a var's value, using `:dynamic` in its declaration, and then with `clojure.core/binding`
  - convention: using `*` around dynamic var names
  - `binding` only changes the var's current value within the same thread that it was originally defined in (making it __thread-local__)

  - usage:

    ```clojure
    (def ^:dynamic *url* "http://en.wikipedia.org/wiki/Chelsea")

    (binding [*url* "http://en.wikipedia.org/wiki/New+York"]
      (println (format "*url* is now %s" *url*)))
    ```

* you can alter a var's root binding with `clojure.core/alter-var-root
  - args: [1] a var, [2] a function that takes the old var value, and returns a new one

  ```clojure
  *url*
  ;; => "http://en.wikipedia.org/wiki/Chelsea"

  (.start (Thread. (fn []
                      (alter-var-root (var user/*url*) (fn [_] "http://en.wikipedia.org/wiki/New+York"))))))

  *url*
  ;; => "http://en.wikipedia.org/wiki/New+York"
  ```

* you can alter a var's root binding to a specific known value, using `clojure.core/constantly`


### Dereferencing

* `@` and `clojure.core/deref` allow you to dereference Clojure references

### Delays

* __delay__: a data structure that's evaluated the first time it's dereferenced
  - subsequent dereferencing uses the cached value
  - instantiated with `clojure.core/delay`

    ```clojure
    (def d (delay (System/currentTimeMillis)))

    @d
    ;; => some valuue

    @d
    ;; => same value
    ```

* you can use `clojure.core/realized?` to check whether a delay has been realized or is still pending


### Futures

* Clojure __future__s evaluate a piece of code in another thread
  - instantiated with `clojure.core/future`
  - it returns immediately, thereby not blocking the current thread
  - need to dereference the future to get its result

  ```clojure
  (def ft (future (+ 1 2)))

  @ft
  ;; => 3
  ```

* you can specify a timeout upon dereferencing, in case it takes a long time or gets blocked forever

  ```clojure
  (def ft (future (Thread/sleep 10000) :completed))
  (deref ft 2000 :timed-out)
  ;; => :timed-out
  ```


### Promises

* __promises__ are realized by calling `clojure.core/deliver` on a promise, along with a value
  - can be dereferenced with a timeout & cache the realized value
  - also supported by `clojure.core/realized?`

  ```clojure
  ; have no body
  (def p (promise))

  (deliver p {:result 42})
  (realized? p)
  ;; => true
  ```

### `java.util.concurrent`

#### Executors (Thread Pools)

* essentially standardizes, invocating, scheduling, execution, and control of asynchronous tasks
  - essentially you  create a thread pool and feed it a function to work on

  ```clojure
  (import '[java.util.concurrent Execturos ExecutorService Callable])

  (let [^ExecutorService pool (Executors/newFixedThreadPool 16)
        ^Callable clbl        (cast Callable (fn []
                                                (reduce + (range 0 10000))))]
    (.submit pool clbl))
  ```

* use `j.u.c.Future#get` to dereference the java Futures
  ```clojure
  (import '[java.util.concurrent Executors ExecutorService Callable])

  (let [^ExecutorService pool (Executors/newFixedThreadPool 16)
        ^Callable clbl        (cast Callable (fn []
                                               (reduce + (range 0 10000))))
        task                  (.submit pool clbl)]
    (.get task))
  ```

#### Countdown latches

* __countdown latch__ is a thread synchronization data structure that handles a group of concurrent workflows (eg. block current thread until N other threads are done with their work)

  - instantiation
    ```clojure
    (import java.util.concurrent.CountDownLatch)

    (CountDownLatch. n)
    ```

  - execution of `CountdownLatch#await` blocks the calling thread until the counter gets to 0
  - `CountdownLatch#countDown` invocations decrease the counter by 1

  - example using `await` and `countDown`
    ```clojure
    (let [cnt   (atom [])
          n     5
          latch (java.util.concurrent.CountDownLatch. n)]
      (doseq [i (range 0 n)]
        (.start (Thread. (fn []
                           (swap! cnt conj i)
                           (.countDown latch)))))
      (.await latch)
      @cnt)
    ;; note the ordering: starting N threads in parallel leads to
    ;; non-deterministic thread interleaving
    ;; ⇒ [0 1 2 4 3]
    ```
