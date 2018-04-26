(ns clojure-tictactoe.game.game-play
  (:require [clojure-tictactoe.cli.output.board-printer :as board-printer]
            [clojure-tictactoe.cli.output.end-game-printer :as end-printer]
            [clojure-tictactoe.cli.output.instructions-printer :as instructions-printer]
            [clojure-tictactoe.cli.input.input-getter :as input-getter]
            [clojure-tictactoe.cli.input.input-translator :as input-translator]
            [clojure-tictactoe.game.board :as board]
            [clojure-tictactoe.game.game-rules :as rules]
            [clojure-tictactoe.game.players :as players]
            ))

(defn player-move [move-function board marker]
  (board/mark-space (move-function board marker) marker board))

(defn continue-game [board game-type markers turn]
  (let [player-function (players/choose-player-function game-type turn)
        marker (markers (players/current-player turn))
        new-board (player-move player-function board marker)]
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
  (let [standard-board-size 9
        game-options ["Human vs Human" "Human vs. Computer" "Computer vs. Human"]
        board-options ["3x3" "4x4"]
        players '("player 1" "player 2")
        game-type (input-getter/get-option-choice game-options instructions-printer/game-choice-message)
        board-choice (input-getter/get-option-choice board-options instructions-printer/board-size-message)
        board (board/render-empty-board (input-translator/get-board-size board-choice))
        turn (input-translator/get-first-turn game-type)
        markers (input-getter/acquire-both-markers players)]
    (board-printer/print-board board)
    (game-loop board game-type markers turn)))
