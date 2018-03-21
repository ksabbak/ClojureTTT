(ns clojure-tictactoe.core
  (:require [clojure-tictactoe.tictactoe :as ttt]
            [clojure-tictactoe.cli.output.instructions-printer :as instructions-printer]
            [clojure-tictactoe.cli.input.input-getter :as input-getter]
            [clojure-tictactoe.cli.output.board-printer :as board-printer]))

(defn -main
  [& args]
  (instructions-printer/print-game-intro)
  (input-getter/continue-to-game)
  (println (board-printer/render-board (board-printer/render-board-spaces)))
  (ttt/game-loop))
