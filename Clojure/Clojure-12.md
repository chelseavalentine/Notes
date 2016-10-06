# Clojure

## Chapter 12: Working with the JVM

### The JVM

* __just-in-time compilation__: a running JVM executing bytecode by translating it to machine code on the fly

### JAR files

* Java Archive file (JAR file)

### Clojure App JARs

* the __`(:gen-class)`__ directive in the namespace declaration tells Clojure to generate a class for the namespace

### Java interop

#### Interop syntax

* call methods on an object with `(.methodName object optional-args go-here)`
  ```clojure
  (.toUpperCase "chelsea")
  ; => "CHELSEA"
  ```

#### Creating and mutating objects

* `(new ClassName optional-args)` or `(ClassName. optional-args)` (used more often)
  ```clojure
  (new String)
  ; => ""

  (String.)
  ; => ""
  ```

* Clojure's __`doto`__ macro let's you execute multiple methods on the same object
  ```clojure
  (doto (java.util.Stack.)
    (.push "First pushed")
    (.push "Second pushed"))
  ; => ["First pushed" "Second pushed"]
  ```


#### Importing

* `import` lets you refer to imported classes w/o their namespace prefix

* import multiple classes at once
  ```clojure
  (import [package.name1 ClassName1 ClassName2]
          [package.name2 ClassName3 ClassName4])

  ;; more often done in the ns macro
  (ns chelsea.namespace
    (:import [java.util Date Stack]
             [java.net Proxy URI]))
  ```

#### The `System` Class

* `exit`, `getEnv`, `getProperty`
  - `getEnv` returns all of your system’s environment variables as a map

### Files and I/O

* commonly used: `clojure.java.io`, `java.io.File`, `java.io.Reader`, `java.io.BufferedReader`, `java.io.FileReader`, `java.io.BufferedWriter`, `java.io.FileWriter`
  ```clojure
  (let [file (java.io.File. "/")]
    (println (.exists file))
    (println (.canWrite file))
    (println (.getPath file)))
  ; => true
  ; => false
  ; => /
  ```

* Clojure's __`spit`__ writes to a resource, and __`slurp`__ reads from one
  ```clojure
  (spit "/tmp/todo-list"
  "- first thing
  - second thing")

  (slurp "/tmp/todo-list")

  ; => "- first thing
        - second thing"
  ```

* etc: `StringReader`, `StringWriter`
