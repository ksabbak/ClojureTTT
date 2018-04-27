(ns clojure-tictactoe.game.game-play
  (:require [clojure-tictactoe.cli.output.board-printer :as board-printer]
            [clojure-tictactoe.cli.output.end-game-printer :as end-printer]
            [clojure-tictactoe.cli.output.messages :as m]
            [clojure-tictactoe.cli.input.input-getter :as input-getter]
            [clojure-tictactoe.game.board :as board]
            [clojure-tictactoe.game.game-rules :as rules]
            [clojure-tictactoe.game.players :as players]
            [clojure-tictactoe.cli.cli-controller :as cli]
            ))

(defn player-move [move-function board marker]
  (board/mark-space (move-function board marker) marker board))

(defn move [board game-type markers turn]
  (let [player-function (players/choose-player-function game-type turn)
        marker (markers (players/current-player turn))
        new-board (player-move player-function board marker)]
    new-board))

(defn game-loop [board game-type markers turn]
  (board-printer/print-board board)
  (if-not (rules/game-over? board)
    (let [new-board (move board game-type markers turn)]
      (board-printer/print-board new-board)
      (recur new-board game-type markers (inc turn)))
    (do
      (if-let [results (rules/assess-winner board)]
      (end-printer/end-game-printer (end-printer/game-won-message results))
      (end-printer/end-game-printer m/end-tie))
      (when (cli/restart?)
        (let [board-size (count board)
              fresh-board (board/render-empty-board board-size)
              turns-played (count (remove number? board))
              start-turn (- turn turns-played)]
          (recur fresh-board game-type markers start-turn))))))

(defn initialize-game []
  (cli/intro-game)
  (let [options (cli/game-options)
        game-type (:game-type options)
        board-size (:board-size options)
        board (board/render-empty-board board-size)
        turn (:turn options)
        markers (:markers options)]
    (game-loop board game-type markers turn)))
