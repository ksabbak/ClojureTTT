(ns clojure-tictactoe.core
  (:require [clojure-tictactoe.cli.output.instructions-printer :as instructions-printer]
            [clojure-tictactoe.cli.input.input-getter :as input-getter]
            [clojure-tictactoe.cli.output.board-printer :as board-printer]
            [clojure-tictactoe.game.game-play :as game-play]))

(defn -main
  [& args]
  (instructions-printer/print-game-intro)
  (input-getter/continue-to-game)
  (board-printer/print-board)
  (game-play/game-loop))
