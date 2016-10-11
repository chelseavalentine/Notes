# Clojure

Learning clojure from [Clojure for the Brave and True](http://www.braveclojure.com/).


#### Table of Contents

__[Chapter 1: Building, running, and the REPL](Clojure-1.md#chapter-1-building-running-and-the-repl)__

__[Chapter 3: Crash course](Clojure-1.md#chapter-3-crash-course)__
  * [Control flow](Clojure-1.md#control-flow)
    - `if`, `do`, `when`, `nil?`, `=`, `and`, `or`
  * [Naming values with `def`](Clojure-1.md#naming-values-with-def)
  * [Data structures](Clojure-1.md#data-structures)
  * [Maps](Clojure-1.md#maps)
    - `hash-map`, `get`, `get-in`
  * [Keywords](Clojure-1.md#keywords)
  * [Vectors](Clojure-2.md#vectors)
    - `vector`
  * [Lists](Clojure-2.md#lists)
    - `list`, `nth`, `conj`
  * [Sets](Clojure-2.md#sets)
    - `#`, `hash-set`, `contains?`
  * [Simplicity](Clojure-2.md#simplicity)
  * [Functions](Clojure-2.md#functions)
  * [Function calls, macro calls, and special forms](Clojure-2.md#function-calls-macro-calls-and-special-forms)
  * [Defining functions](Clojure-3.md#defining-functions)
    - `defn`, `doc`, `&`, `:as`
  * [Anonymous functions](Clojure-3.md#anonymous functions)
    - `fn`, `#`, `%`, `%&`
  * [Returning functions](Clojure-3.md#returning-functions)
  * [`let`](Clojure-4.md#let)
    - `let`, `take`, `into`
  * [`loop`](Clojure-4.md#loop)
    - `loop`, `recur`
  * [Regular expressions](Clojure-4.md#regular-expressions)
    - `re-find`
  * [`reduce`](Clojure-4.md#reduce)
    - `reduce`, `map`

__[Chapter 4: Core functions in depth](Clojure-4.md#chapter-4-core-functions-in-depth)__
  * [Treating lists, vectors, sets, and maps as sequences](Clojure-4.md#treating-lists-vectors-sets-and-maps-as-sequences)
  * [Abstraction through indirection](Clojure-4.md#abstraction-through-indirection)
  * [`map`](Clojure-4.md#map)
  * [`reduce`](Clojure-4.md#reduce)
  * [`take`, `drop`, `take-while`, and `drop-while`](Clojure-4.md#take-drop-take-while-and-drop-while)
  * [`filter` and `some`](Clojure-5.md#)
  * [`sort`, `sort-by`, and `concat`](Clojure-5.md#)
  * [Lazy seqs](Clojure-5.md#)
  * [Infinite sequences](Clojure-5.md#)
  * [`into`](Clojure-5.md#)
  * [`conj`](Clojure-5.md#)
  * [`apply`](Clojure-5.md#)
  * [`partial`](Clojure-5.md#)
  * [`complement`](Clojure-5.md#)

__[Chapter 5: Functional programming](Clojure-5.md#chapter-5-functional-programming)__
  * [Pure functions: What and why](Clojure-5.md#pure-functions-what-and-why)
  * [Recursion instead of for/while](Clojure-5.md#recursion-instead-of-forwhile)
  * [Function composition instead of attribute mutation](Clojure-6.md#function-composition-instead-of-attribute-mutation)
  * [`comp`](Clojure-6.md#comp)
  * [`memoize`](Clojure-6.md#memoize)
  * [Misc](Clojure-6.md#misc)
    - `assoc-in`, `get-in`

__[Chapter 6: Organizing your project](Clojure-6.md#chapter-6-organizing-your-project)__
  * [Your project as a library](Clojure-6.md#your-project-as-a-library)
  * [Storing objects with `def`](Clojure-6.md#storing-objects-with-def)
    - `def`, `ns-interns`, `ns-map`, `deref`, `#'`
  * [Creating and switching to namespaces](Clojure-6.md#creating-and-switching-to-namespaces)
    - `create-ns`, `in-ns`
  * [`refer`](Clojure-6.md#refer)
    - `refer`, `:only`, `:exclude`, `:rename`, `defn-`
  * [`alias`](Clojure-6.md#alias)
  * [The relationship between file paths and namespace names](Clojure-7.md#the-relationship-between-file-paths-and-namespace-names)
    - `ns`
  * [Requiring and using namespaces](Clojure-7.md#requiring-and-using-namespaces)
    -   `require`, `use`, `refer`, `:only`, `:exclude`, `:as`, `:rename`
  * [The `ns` macro](Clojure-7.md#the-ns-macro)
    - `:refer-clojure`, `:require`, `:use`, `:import`, `:load`, `:gen-class`

__[Chapter 7: Reading, evaluation, and macros](Clojure-7.md#chapter-7-reading-evaluation-and-macros)__
  * [An overview of Clojure's evaluation module](Clojure-7.md#an-overview-of-clojures-evaluation-module)
  * [Reading](Clojure-7.md#reading)
  * [Reader macros](Clojure-7.md#reader-macros)
  * [Things that evaluate to themselves](Clojure-7.md#things-that-evaluate-to-themselves)
  * [Symbols](Clojure-7.md#symbols)
  * [Lists](Clojure-7.md#lists)
  * [Macros](Clojure-8.md#)
    - `defmacro`, `macroexpand`
  * [Syntactic abstraction and the `->` macro](Clojure-8.md#syntactic-abstraction-and-the---macro)

__[Chapter 8: Writing Macros](Clojure-8.md#chapter-8-writing-macros)__
  * [Distinguishing symbols and values](Clojure-8.md#distinguishing-symbols-and-values)
  * [Simple quoting](Clojure-8.md#simple-quoting)
  * [Syntax quoting](Clojure-8.md#syntax-quoting-)
    - ``` `~`
  * [Variable capture](Clojure-8.md#variable-capture)
    - `gensym`
  * [Double evaluation](Clojure-9.md#double-evaluation)
  * [Macros all the way down](Clojure-9.md#macros-all-the-way-down)

__[Chapter 9: The Sacred Art of Concurrent and Parallel Programming](Clojure-9.md#chapter-9-concurrent-and-parallel-programming)__
  * [Futures](Clojure-9.md#futures)
    - `future`, `deref`, `@`
  * [Delays](Clojure-9.md#delays)
    - `delay`, `force`
  * [Promises](Clojure-9.md#promises)
    - `promise`, `deliver`

__[Chapter 10: Atoms, refs, and vars](Clojure-10.md#chapter-10-atoms-refs-and-vars)__
  * [Atoms](Clojure-10.md#atoms)
    - `atom`, `swap!`, `reset`
  * [Watches](Clojure-10.md#watches)
    - `add-watch`
  * [Validators](Clojure-10.md#validators)
    - `alter`
  * [`commute`](Clojure-10.md#commute)
  * [Dynamic binding](Clojure-10.md#dynamic-binding)
    - `^:dynamic`, `binding`
  * [Altering the var root](Clojure-10.md#altering-the-var-root)
    - `alter-var-root`, `with-redefs`
  * [Stateless concurrency and parallelism with `pmap`](Clojure-10.md#stateless-concurrency-and-parallelism-with-pmap)

__[Chapter 11: Concurrent processes with `core.async`](Clojure-11.md#chapter-11-concurrent-processes-with-coreasync)__
  * [Getting started with processes](Clojure-11.md#getting-started-with-processes)
    - `chan`, `go`, `<!`, `>!!`
  * [Buffering](Clojure-11.md#buffering)
    - `sliding-buffer`, `dropping-buffer`
  * [Blocking and parking](Clojure-11.md#blocking-and-parking)
    - `!!`, `>!`, `>!!`, `<!`, `<!!`, `>!!`, `<!!`
  * [`thread`](Clojure-11.md#thread)
  * [`alts!!`](Clojure-11.md#alts)
    - `alts!!`, `alts!`
  * [Process pipelines to escape callback hell](Clojure-11.md#process-pipelines-to-escape-callback-hell)

__[Chapter 12: Working with the JVM](Clojure-11.md#chapter-12-working-with-the-jvm)__
  * [The JVM](Clojure-11.md#)
  * [Clojure App JARs](Clojure-11.md#clojure-app-jars)
    - `:gen-class`
  * [Java Interop syntax](Clojure-11.md#interop-syntax)
  * [Creating and mutating objects](Clojure-12.md#creating-and-mutating-objects)
    - `new`, `doto`
  * [Importing](Clojure-12.md#importing)
  * [The `System` class](Clojure-12.md#the-system-class)
    - `exit`, `getEnv`, `getProperty`
  * [Files and I/O](Clojure-12.md#files-and-io)
    - `spit`, `slurp`

__[Chapter 13: Creating and Extending Abstractions with Multimethods, Protocols, and Records](Clojure-12.md#chapter-13-creating-and-extending-abstractions-with-multimethods-protocols-and-records)__
  * [Multimethods](Clojure-12.md#multimethods)
    - `defmulti`, `defmethod`
  * [Protocols](Clojure-12.md#protocols)
    - `defprotocol`, `extend-type`, `extend-protocol`
  * [Records](Clojure-13.md#records)
    - `defrecord`, `dissoc`
