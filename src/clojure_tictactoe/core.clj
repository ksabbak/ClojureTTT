(ns clojure-tictactoe.core
  (:require [clojure-tictactoe.tictactoe :as ttt]))

(defn -main
  [& args]
  (println (ttt/welcome))
  (println (ttt/instructions)))
