(ns clojure-tictactoe.game.game-play
  (:require [clojure-tictactoe.cli.output.board-printer :as board-printer]
            [clojure-tictactoe.cli.output.instructions-printer :as instructions-printer]
            [clojure-tictactoe.cli.input.input-getter :as input-getter]
            [clojure-tictactoe.cli.output.end-game-printer :as end-printer]
            [clojure-tictactoe.game.players :as players]
            [clojure-tictactoe.game.board :as board]
            [clojure-tictactoe.game.game-rules :as rules]
            ))

(def standard-board-size 9)
(def game-options ["Human vs Human" "Human vs. Computer"])

(defn player-move [move-function board marker]
  (board/mark-space (move-function board) marker board))

(defn continue-game [board game-type markers turn]
  (let [new-board (player-move
                    (players/choose-player-function
                      game-type turn)
                    board
                    (markers
                      (players/current-player turn)))]
    (board-printer/print-board new-board)
    new-board))

(defn game-loop [board game-type markers turn]
  (if-not (rules/game-over? board)
    (let [new-board (continue-game board game-type markers turn)]
      (recur new-board game-type markers (+ 1 turn)))
    (if-let [results (rules/assess-winner board)]
      (end-printer/end-game-printer (end-printer/game-won-message results))
      (end-printer/end-game-printer end-printer/game-tie-message))))

(defn initialize-game []
  (instructions-printer/print-game-intro)
  (input-getter/continue-to-game)
  (let [game-type (input-getter/get-game-type game-options)
        board (board/render-empty-board standard-board-size)
        markers (players/acquire-both-markers '("player 1" "player 2"))]
    (board-printer/print-board board)
    (game-loop board game-type markers 0)))
