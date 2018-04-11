(ns clojure-tictactoe.game.game-play
  (:require [clojure-tictactoe.cli.output.board-printer :as board-printer]
            [clojure-tictactoe.cli.output.instructions-printer :as instructions-printer]
            [clojure-tictactoe.cli.input.input-getter :as input-getter]
            [clojure-tictactoe.cli.output.end-game-printer :as end-printer]
            [clojure-tictactoe.game.players :as players]
            [clojure-tictactoe.game.board :as board]
            [clojure-tictactoe.game.game-rules :as rules]
            ))

(defn player-move [move-function board marker]
  (board/mark-space (move-function board) marker board))

(defn continue-game [board markers turn]
  (let [new-board (player-move (players/choose-player-function turn) board (markers (players/current-player turn)))]

    (board-printer/print-board new-board)
    new-board))

(defn game-loop [board markers turn]
  (if-not (rules/game-over? board)
    (let [new-board (continue-game board markers turn)]

      (recur new-board markers (+ 1 turn)))
    (if-let [results (rules/assess-winner board)]
      (println (end-printer/game-won-message results))
      (println end-printer/game-tie-message))))

(defn initialize-game []
  (instructions-printer/print-game-intro)
  (input-getter/continue-to-game)
  (let [board (board/render-empty-board 9)
        markers (players/acquire-both-markers '("you" "the computer"))]
    (board-printer/print-board board)
    (game-loop board markers 0)))
