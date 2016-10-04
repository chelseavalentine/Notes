# Clojure

## Chapter 8: Writing macros

* macros allow you to derive a lot of the built-in functionality
* you can create multiple-arity macros


### Building lists for evaluation

#### Distinguishing symbols and values

* you often need to turn off evaluation when trying to get a macro to do what you want
  ```clojure
  ;; goal: a result like this
  (let [result expression]
    (println result)
    result)

  ;; this fails @ printing the expression and returning the expression's value;
  (defmacro my-print-whoopsie
   [expression]
   (list let [result expression]
         (list println result)
         result))

  ;; this succeeds
  (defmacro my-print
    [expression]
    (list 'let ['result expression]
          (list 'println 'result)
          'result))
  ```

#### Simple quoting

* quoting a symbol returns a symbol regardless of whether it has a value associated with it

#### Syntax quoting: ```

* differences between this and normal quoting
  - returns fully qualified symbols (with the namespace included); avoids name collisions
  - allows you to unquote forms using `~`
    ```clojure
    `(+ 1 ~(inc 1))
    ; => (clojure.core/+ 1 2)

    ;; otherwise
    `(+ 1 (inc 1))
    ; => (clojure.core/+ 1 (clojure.core/inc 1))
    ```

### Things to watch out for

#### Variable capture

* __variable capture__: when a macro introduces a binding that eclipses an existing binding
  - helps prevent accidentally capturing variables w/i macros; you can use `gensym` if you want `let` bindings in your macro
  - `gensym` creates a new unique symbol, you can do the same with a `#` after the symbol
  ```clojure
  (def message "Good job!")
  (defmacro with-mischief
    [& stuff-to-do]
    (concat (list 'let ['message "Oh, big deal!"])
            stuff-to-do))

  (with-mischief
    (println "Here's how I feel about that thing you did: " message))
  ; => Here's how I feel about that thing you did: Oh, big deal!

  ;;; with gensym
  (defmacro without-mischief
    [& stuff-to-do]
    (let [macro-message (gensym 'message)]
      `(let [~macro-message "Oh, big deal!"]
         ~@stuff-to-do
         (println "I still need to say: " ~macro-message))))

  (without-mischief
    (println "Here's how I feel about that thing you did: " message))
  ; => Here's how I feel about that thing you did:  Good job!
  ; => I still need to say:  Oh, big deal!
  ```

#### Double evaluation

* __double evaluation__: when a form passed to a macro as an arg gets evaluated more than once
  - occurs because of a duplicate statement in the macro expansion
  ```clojure
  ;; this sleeps twice
  (defmacro report
    [to-try]
    `(if ~to-try
       (println (quote ~to-try) "was successful:" ~to-try)
       (println (quote ~to-try) "was not successful:" ~to-try)))

  ;; Thread/sleep takes a number of milliseconds to sleep for
  (report (do (Thread/sleep 1000) (+ 1 1)))

  ;; because this is the macro expansion
  (if (do (Thread/sleep 1000) (+ 1 1))
    (println '(do (Thread/sleep 1000) (+ 1 1))
             "was successful:"
             (do (Thread/sleep 1000) (+ 1 1)))

    (println '(do (Thread/sleep 1000) (+ 1 1))
             "was not successful:"
             (do (Thread/sleep 1000) (+ 1 1))))

  ;; fix it by binding it to an auto-gensym'd symbol
  (defmacro report
    [to-try]
    `(let [result# ~to-try]
       (if result#
         (println (quote ~to-try) "was successful:" result#)
         (println (quote ~to-try) "was not successful:" result#))))
  ```

#### Macros all the way down

* when everything you're using is a macro, so values aren't what you'd expect
