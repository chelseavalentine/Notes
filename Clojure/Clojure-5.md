# Clojure

#### Regular expressions

* format:
  ```clojure
  #"regular-expression"

  ;; eg.
  (re-find #"^left-" "left-eye")
  ; => "left-"

  (re-find #"^left-" "cleft-chin")
  ; => nil
  ```

#### `reduce`

* __`map`__ returns a list

* __`reduce`__ applies a function to a sequence (sequence specified by vector)
  - also it treats your map like a list of vectors

  ```clojure
  ;; sum with reduce
  (reduce + [1 2 3 4])
  ; => 10
  ```

* __`empty? []`__ tells you whether an array is empty
