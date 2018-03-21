(ns clojure-tictactoe.core
  (:require [clojure-tictactoe.tictactoe :as ttt]
            [clojure-tictactoe.cli.output.instructions-printer :as instructions-printer]))

(defn -main
  [& args]
  (instructions-printer/print-game-intro)
  (ttt/continue-to-game)
  (println (ttt/render-board (ttt/render-board-spaces)))
  (ttt/game-loop))
