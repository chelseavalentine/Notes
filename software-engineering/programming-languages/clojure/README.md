# Clojure

### Table of Contents: Clojure Docs

* [Concurrency and Parallelism](clojure-docs-1.md)


### Table of Contents: Clojure for the Brave and True

Learning clojure from [Clojure for the Brave and True](http://www.braveclojure.com/).

__[Chapter 1: Building, running, and the REPL](clojure-for-the-brave-and-true.md#chapter-1-building-running-and-the-repl)__

__[Chapter 3: Crash course](clojure-for-the-brave-and-true.md#chapter-3-crash-course)__
  * [Control flow](clojure-for-the-brave-and-true.md#control-flow)
    - `if`, `do`, `when`, `nil?`, `=`, `and`, `or`
  * [Naming values with `def`](clojure-for-the-brave-and-true.md#naming-values-with-def)
  * [Data structures](clojure-for-the-brave-and-true.md#data-structures)
  * [Maps](clojure-for-the-brave-and-true.md#maps)
    - `hash-map`, `get`, `get-in`
  * [Keywords](clojure-for-the-brave-and-true.md#keywords)
  * [Vectors](clojure-for-the-brave-and-true.md#vectors)
    - `vector`
  * [Lists](clojure-for-the-brave-and-true.md#lists)
    - `list`, `nth`, `conj`
  * [Sets](clojure-for-the-brave-and-true.md#sets)
    - `#`, `hash-set`, `contains?`
  * [Simplicity](clojure-for-the-brave-and-true.md#simplicity)
  * [Functions](clojure-for-the-brave-and-true.md#functions)
  * [Function calls, macro calls, and special forms](clojure-for-the-brave-and-true.md#function-calls-macro-calls-and-special-forms)
  * [Defining functions](clojure-for-the-brave-and-true.md#defining-functions)
    - `defn`, `doc`, `&`, `:as`
  * [Anonymous functions](clojure-for-the-brave-and-true.md#anonymous functions)
    - `fn`, `#`, `%`, `%&`
  * [Returning functions](clojure-for-the-brave-and-true.md#returning-functions)
  * [`let`](clojure-for-the-brave-and-true.md#let)
    - `let`, `take`, `into`
  * [`loop`](clojure-for-the-brave-and-true.md#loop)
    - `loop`, `recur`
  * [Regular expressions](clojure-for-the-brave-and-true.md#regular-expressions)
    - `re-find`
  * [`reduce`](clojure-for-the-brave-and-true.md#reduce)
    - `reduce`, `map`

__[Chapter 4: Core functions in depth](clojure-for-the-brave-and-true.md#chapter-4-core-functions-in-depth)__
  * [Treating lists, vectors, sets, and maps as sequences](clojure-for-the-brave-and-true.md#treating-lists-vectors-sets-and-maps-as-sequences)
  * [Abstraction through indirection](clojure-for-the-brave-and-true.md#abstraction-through-indirection)
  * [`map`](clojure-for-the-brave-and-true.md#map)
  * [`reduce`](clojure-for-the-brave-and-true.md#reduce)
  * [`take`, `drop`, `take-while`, and `drop-while`](clojure-for-the-brave-and-true.md#take-drop-take-while-and-drop-while)
  * [`filter` and `some`](clojure-for-the-brave-and-true.md#)
  * [`sort`, `sort-by`, and `concat`](clojure-for-the-brave-and-true.md#)
  * [Lazy seqs](clojure-for-the-brave-and-true.md#)
  * [Infinite sequences](clojure-for-the-brave-and-true.md#)
  * [`into`](clojure-for-the-brave-and-true.md#)
  * [`conj`](clojure-for-the-brave-and-true.md#)
  * [`apply`](clojure-for-the-brave-and-true.md#)
  * [`partial`](clojure-for-the-brave-and-true.md#)
  * [`complement`](clojure-for-the-brave-and-true.md#)

__[Chapter 5: Functional programming](clojure-for-the-brave-and-true.md#chapter-5-functional-programming)__
  * [Pure functions: What and why](clojure-for-the-brave-and-true.md#pure-functions-what-and-why)
  * [Recursion instead of for/while](clojure-for-the-brave-and-true.md#recursion-instead-of-forwhile)
  * [Function composition instead of attribute mutation](clojure-for-the-brave-and-true.md#function-composition-instead-of-attribute-mutation)
  * [`comp`](clojure-for-the-brave-and-true.md#comp)
  * [`memoize`](clojure-for-the-brave-and-true.md#memoize)
  * [Misc](clojure-for-the-brave-and-true.md#misc)
    - `assoc-in`, `get-in`

__[Chapter 6: Organizing your project](clojure-for-the-brave-and-true.md#chapter-6-organizing-your-project)__
  * [Your project as a library](clojure-for-the-brave-and-true.md#your-project-as-a-library)
  * [Storing objects with `def`](clojure-for-the-brave-and-true.md#storing-objects-with-def)
    - `def`, `ns-interns`, `ns-map`, `deref`, `#'`
  * [Creating and switching to namespaces](clojure-for-the-brave-and-true.md#creating-and-switching-to-namespaces)
    - `create-ns`, `in-ns`
  * [`refer`](clojure-for-the-brave-and-true.md#refer)
    - `refer`, `:only`, `:exclude`, `:rename`, `defn-`
  * [`alias`](clojure-for-the-brave-and-true.md#alias)
  * [The relationship between file paths and namespace names](clojure-for-the-brave-and-true.md#the-relationship-between-file-paths-and-namespace-names)
    - `ns`
  * [Requiring and using namespaces](clojure-for-the-brave-and-true.md#requiring-and-using-namespaces)
    -   `require`, `use`, `refer`, `:only`, `:exclude`, `:as`, `:rename`
  * [The `ns` macro](clojure-for-the-brave-and-true.md#the-ns-macro)
    - `:refer-clojure`, `:require`, `:use`, `:import`, `:load`, `:gen-class`

__[Chapter 7: Reading, evaluation, and macros](clojure-for-the-brave-and-true.md#chapter-7-reading-evaluation-and-macros)__
  * [An overview of Clojure's evaluation module](clojure-for-the-brave-and-true.md#an-overview-of-clojures-evaluation-module)
  * [Reading](clojure-for-the-brave-and-true.md#reading)
  * [Reader macros](clojure-for-the-brave-and-true.md#reader-macros)
  * [Things that evaluate to themselves](clojure-for-the-brave-and-true.md#things-that-evaluate-to-themselves)
  * [Symbols](clojure-for-the-brave-and-true.md#symbols)
  * [Lists](clojure-for-the-brave-and-true.md#lists)
  * [Macros](clojure-for-the-brave-and-true.md#)
    - `defmacro`, `macroexpand`
  * [Syntactic abstraction and the `->` macro](clojure-for-the-brave-and-true.md#syntactic-abstraction-and-the---macro)

__[Chapter 8: Writing Macros](clojure-for-the-brave-and-true.md#chapter-8-writing-macros)__
  * [Distinguishing symbols and values](clojure-for-the-brave-and-true.md#distinguishing-symbols-and-values)
  * [Simple quoting](clojure-for-the-brave-and-true.md#simple-quoting)
  * [Syntax quoting](clojure-for-the-brave-and-true.md#syntax-quoting-)
    - ``` `~`
  * [Variable capture](clojure-for-the-brave-and-true.md#variable-capture)
    - `gensym`
  * [Double evaluation](clojure-for-the-brave-and-true.md#double-evaluation)
  * [Macros all the way down](clojure-for-the-brave-and-true.md#macros-all-the-way-down)

__[Chapter 9: The Sacred Art of Concurrent and Parallel Programming](clojure-for-the-brave-and-true.md#chapter-9-concurrent-and-parallel-programming)__
  * [Futures](clojure-for-the-brave-and-true.md#futures)
    - `future`, `deref`, `@`
  * [Delays](clojure-for-the-brave-and-true.md#delays)
    - `delay`, `force`
  * [Promises](clojure-for-the-brave-and-true.md#promises)
    - `promise`, `deliver`

__[Chapter 10: Atoms, refs, and vars](clojure-for-the-brave-and-true.md#chapter-10-atoms-refs-and-vars)__
  * [Atoms](clojure-for-the-brave-and-true.md#atoms)
    - `atom`, `swap!`, `reset`
  * [Watches](clojure-for-the-brave-and-true.md#watches)
    - `add-watch`
  * [Validators](clojure-for-the-brave-and-true.md#validators)
    - `alter`
  * [`commute`](clojure-for-the-brave-and-true.md#commute)
  * [Dynamic binding](clojure-for-the-brave-and-true.md#dynamic-binding)
    - `^:dynamic`, `binding`
  * [Altering the var root](clojure-for-the-brave-and-true.md#altering-the-var-root)
    - `alter-var-root`, `with-redefs`
  * [Stateless concurrency and parallelism with `pmap`](clojure-for-the-brave-and-true.md#stateless-concurrency-and-parallelism-with-pmap)

__[Chapter 11: Concurrent processes with `core.async`](clojure-for-the-brave-and-true.md#chapter-11-concurrent-processes-with-coreasync)__
  * [Getting started with processes](clojure-for-the-brave-and-true.md#getting-started-with-processes)
    - `chan`, `go`, `<!`, `>!!`
  * [Buffering](clojure-for-the-brave-and-true.md#buffering)
    - `sliding-buffer`, `dropping-buffer`
  * [Blocking and parking](clojure-for-the-brave-and-true.md#blocking-and-parking)
    - `!!`, `>!`, `>!!`, `<!`, `<!!`, `>!!`, `<!!`
  * [`thread`](clojure-for-the-brave-and-true.md#thread)
  * [`alts!!`](clojure-for-the-brave-and-true.md#alts)
    - `alts!!`, `alts!`
  * [Process pipelines to escape callback hell](clojure-for-the-brave-and-true.md#process-pipelines-to-escape-callback-hell)

__[Chapter 12: Working with the JVM](clojure-for-the-brave-and-true.md#chapter-12-working-with-the-jvm)__
  * [The JVM](clojure-for-the-brave-and-true.md#)
  * [Clojure App JARs](clojure-for-the-brave-and-true.md#clojure-for-the-brave-and-true-app-jars)
    - `:gen-class`
  * [Java Interop syntax](clojure-for-the-brave-and-true.md#interop-syntax)
  * [Creating and mutating objects](clojure-for-the-brave-and-true.md#creating-and-mutating-objects)
    - `new`, `doto`
  * [Importing](clojure-for-the-brave-and-true.md#importing)
  * [The `System` class](clojure-for-the-brave-and-true.md#the-system-class)
    - `exit`, `getEnv`, `getProperty`
  * [Files and I/O](clojure-for-the-brave-and-true.md#files-and-io)
    - `spit`, `slurp`

__[Chapter 13: Creating and Extending Abstractions with Multimethods, Protocols, and Records](clojure-for-the-brave-and-true.md#chapter-13-creating-and-extending-abstractions-with-multimethods-protocols-and-records)__
  * [Multimethods](clojure-for-the-brave-and-true.md#multimethods)
    - `defmulti`, `defmethod`
  * [Protocols](clojure-for-the-brave-and-true.md#protocols)
    - `defprotocol`, `extend-type`, `extend-protocol`
  * [Records](clojure-for-the-brave-and-true.md#records)
    - `defrecord`, `dissoc`
