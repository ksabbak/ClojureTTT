(ns clojure-tictactoe.core
  (:require [clojure-tictactoe.tictactoe :as ttt]))

(defn -main
  [& args]
  (ttt/intro-game)
  (ttt/continue-to-game)
  (println (ttt/board [0 1 2 3 4 5 6 7 8])))
