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
  - put: `>!` or `>!!`
  - take: `<!` or `<!!`

* outside the go block, you need to use 2 exclamation points
  - put: `>!!`
  - take: `<!!`

#### `thread`

### `alts!!`

### Queues

### Process pipelines to escape callback hell

### Additional resources
