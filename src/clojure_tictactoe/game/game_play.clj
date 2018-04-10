(ns clojure-tictactoe.game.game-play
  (:require [clojure-tictactoe.cli.output.board-printer :as board-printer]
            [clojure-tictactoe.cli.output.instructions-printer :as instructions-printer]
            [clojure-tictactoe.cli.input.input-getter :as input-getter]
            [clojure-tictactoe.cli.output.end-game-printer :as end-printer]
            [clojure-tictactoe.game.players :as players]
            [clojure-tictactoe.game.board :as board]
            [clojure-tictactoe.game.game-rules :as rules]
            ))

(defn player-move [move-function board]
  (board/mark-space (move-function board) @players/player-atom board))

(defn continue-game [board]
  (let [new-board (player-move (players/choose-player-function @players/player-atom) board)]
    (board-printer/print-board new-board)
    (players/player-swap)
    new-board))

(defn game-loop [board]
  (loop [board board]
    (if-not (rules/game-over? board)
      (let [new-board (continue-game board)]
        (recur new-board))
      (if-let [results (rules/assess-winner board)]
        (println (end-printer/game-won-message results))
        (println end-printer/game-tie-message)))))

(defn initialize-game []
  (instructions-printer/print-game-intro)
  (input-getter/continue-to-game)
  (let [board (board/render-empty-board 9)]
    (board-printer/print-board board)
    (game-loop board)))
