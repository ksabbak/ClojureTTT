(ns clojure-tictactoe.core
  (:require [clojure-tictactoe.tictactoe :as ttt]))

(defn -main
  [& args]
  (ttt/print-game-intro)
  (ttt/continue-to-game)
  (println (ttt/render-board (ttt/render-board-spaces)))
  (ttt/game-loop))
