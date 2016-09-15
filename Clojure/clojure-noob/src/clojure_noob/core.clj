; Declares the namespace
(ns clojure-noob.core
  (:gen-class))

(defn hello
 []
 (println "what's up?")
 (println (+ 1 2 3 4)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!")
  (hello))
