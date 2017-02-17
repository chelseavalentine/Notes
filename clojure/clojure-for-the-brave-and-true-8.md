# Clojure for the Brave and True

#### Macros

* can create macros to change how clojure reads things, eg. infix notation
  ```clojure
  (let [infix (read-string "(1 + 1)")]
    (list (second infix) (first infix) (last infix)))
  ; => (+ 1 1)
  ```

* the data structures returned by a function isn't evaluated, but if it's returned by a macro, it is
  * __macro expansion__: the process of determining the return value of a macro

* `macroexpand` shows you which data structure a macro returns before the data structure is evaluated
  ```clojure
  (macroexpand '(ignore-last-operand (+ 1 2 10)))
  ; => (+ 1 2)

  ```
Examples
```clojure
;; 1
(defmacro ignore-last-operand
  [function-call]
  (butlast function-call))

;; 2
(defmacro infix
  [infixed]
  (list (second infixed)
        (first infixed)
        (last infixed)))

(infix (1 + 2))
; => 3
```

#### Syntactic abstraction and the `->` macro

* threading/stabby macro: `->` lets you rewrite functions so you don't need to read it right-to-left; it also lets you omit parentheses
  ```clojure
  ;; before
  (defn read-resource
    "Read a resource into a string"
    [path]
    (read-string (slurp (clojure.java.io/resource path))))

  ;; after
  (defn read-resource
    [path]
    (-> path
        clojure.java.io/resource
        slurp
        read-string))
  ```

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
