(ns clojure-tictactoe.core
  (:require [clojure-tictactoe.tictactoe :as ttt]))

(defn -main
  [& args]
  (ttt/print-game-intro)
  (ttt/continue-to-game)
  (println (ttt/board (ttt/make-board-filler)))
  (ttt/game-loop))
