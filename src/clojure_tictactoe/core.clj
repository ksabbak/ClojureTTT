(ns clojure-tictactoe.core
  (:require [clojure-tictactoe.tictactoe :as ttt]
            [clojure-tictactoe.cli.output.instructions-printer :as instructions-printer]
            [clojure-tictactoe.cli.input.input-getter :as input-getter]))

(defn -main
  [& args]
  (instructions-printer/print-game-intro)
  (input-getter/continue-to-game)
  (println (ttt/render-board (ttt/render-board-spaces)))
  (ttt/game-loop))
