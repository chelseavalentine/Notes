(ns playsync.core
  (:require [clojure.core.async
             :as a
             :refer [>! <! >!! <!! go chan buffer close! thread
                     alts! alts!! timeout]]))


(def echo-chan (chan))
(go (println (<! echo-chan)))
(>!! echo-chan "ketchup")

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "hmm"))
