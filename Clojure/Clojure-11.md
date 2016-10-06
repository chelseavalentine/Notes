# Clojure

## Chapter 11: Concurrent processes with `core.async`

### Getting started with processes

* __`chan`__ creates a channel
  - channels communicate messages; you can put messages on them & take them off
  - processes wait for put & take to complete before continuing to execute

* __`go`__ creates a new process
  - everything runs concurrently on a separate thread if put within the `go` expression
  - thread pool count: 2 + (num cores on your machine)

* __`<!`__ is the take function

* __`>!!`__ is the put function; returns `true` when successfully completes
  - the process blocks until another process takes the message
  - the processes act independently & don't know of each other

#### Buffering

* creating buffered channels means you can just drop off a message and go off to do whatever, rather than waiting on someone to take it
  ```clojure
  (def echo-buffer (chan 2))
  (>!! echo-buffer "ketchup")
  ; => true
  (>!! echo-buffer "ketchup")
  ; => true
  (>!! echo-buffer "ketchup")
  ; This blocks because the channel buffer is full
  ```

* a `sliding-buffer` is like a FIFO buffer

* a `dropping-buffer` discards values in a last-in, first-out way

#### Blocking and parking

* use `!!` w/ the put & take methods if it's _inside the go block_
  - put: `>!` (non-blocking) or `>!!`
  - take: `<!` (non-blocking) or `<!!`

* outside the go block, you need to use 2 exclamation points
  - put: `>!!` (blocking)
  - take: `<!!` (blocking)

#### `thread`

* you want to use blocking take & put when you use threads

* `thread` creates a thread and executes a process; it returns a channel with the return value of the process in it
  ```clojure
  (let [t (thread "chili")]
    (<!! t))
  ; => "chili"
  ```

* use threads, instead of a `go`-block for long-running tasks so you don't clog the thread pool

* use the blocking put & take variants w/ `thread`

* if there's nothing to take, you get a `nil`

### `alts!!`

* __`alts!!`__ lets you use the result of the first successful channel operation
  - takes a vector of channels as its argument, and does a blocking take on all of them simultaneously
  - returns a vector w/ the first element being the value taken, & the second being the channel it was taken from
  - you can give it a timeout channel, so it waits a specified number of seconds before timing out

  ```clojure
  (let [c1 (chan)]
    (upload "serious.jpg" c1)
    (let [[headshot channel] (alts!! [c1 (timeout 20)])]
      (if headshot
        (println "Sending headshot notification for" headshot)
        (println "Timed out!"))))
  ; => Timed out!
  ```

* use it for put operations by putting a vector inside the vector you pass in
  ```clojure
  (let [c1 (chan)
        c2 (chan)]
    (go (<! c2))
    (let [[value channel] (alts!! [c1 [c2 "put!"]])]
      (println value)
      (= channel c2)))
  ; => true
  ; => true
  ```

* __`alts!`__: the parking alternative (continues execution instead of just waiting on results) to use inside `go` blocks

### Process pipelines to escape callback hell

* you can make it so each unit of logic lives in its own isolated process, and all communication between units of logic occurs through explicitly defined input and output channels

  ```clojure
  (defn upper-caser
    [in]
    (let [out (chan)]
      (go (while true (>! out (clojure.string/upper-case (<! in)))))
      out))

  (defn reverser
    [in]
    (let [out (chan)]
      (go (while true (>! out (clojure.string/reverse (<! in)))))
      out))

  (defn printer
    [in]
    (go (while true (println (<! in)))))

  (def in-chan (chan))
  (def upper-caser-out (upper-caser in-chan))
  (def reverser-out (reverser upper-caser-out))
  (printer reverser-out)

  (>!! in-chan "redrum")
  ; => MURDER

  (>!! in-chan "repaid")
  ; => DIAPER
  ```
