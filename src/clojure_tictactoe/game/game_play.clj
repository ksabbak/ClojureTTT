(ns clojure-tictactoe.game.game-play
  (:require [clojure-tictactoe.cli.output.board-printer :as board-printer]
            [clojure-tictactoe.cli.output.instructions-printer :as instructions-printer]
            [clojure-tictactoe.cli.input.input-getter :as input-getter]
            [clojure-tictactoe.game.players :as players]
            [clojure-tictactoe.game.board :as board]
            [clojure-tictactoe.game.game-rules :as rules]
            [clojure-tictactoe.cli.output.end-game-printer :as end-printer]
            ))

(defn player-move [move-function board]
  (loop []
    (if-let [new-board (board/mark-space (move-function) @players/player-atom board)]
      new-board
      (do (println "Sorry, that looks taken, try again")
          (recur)))))

(defn continue-game [board]
  (let [new-board (player-move input-getter/get-player-choice board)]
    (board-printer/print-board new-board)
    (players/player-swap)
    new-board))

(defn game-loop []
  (instructions-printer/print-game-intro)
  (input-getter/continue-to-game)
  (let [board (board/render-empty-board 9)]
    (board-printer/print-board board)
    (loop [board board]
      (if-not (rules/game-over? board)
        (let [new-board (continue-game board)]
              (recur new-board))
        (let [results (rules/assess-win board)]
              (if results
                (println (end-printer/game-won-message results))
                (println end-printer/game-tie-message)))))))

