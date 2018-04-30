(ns clojure-tictactoe.game.game-play
  (:require [clojure-tictactoe.cli.cli-controller :as cli]
            [clojure-tictactoe.game.board :as board]
            [clojure-tictactoe.game.game-rules :as rules]
            [clojure-tictactoe.game.players :as players]
            [clojure-tictactoe.game.computer-opponent :as ai]))

(defn get-player-functions [players]
  (println players)
  (if (some #(= :computer %) players)
    [cli/get-player-move ai/get-move]
    [cli/get-player-move cli/get-player-move]))

(defn player-move [move-function board marker]
  (board/mark-space (move-function board marker) marker board))

(defn move [board player-turns markers turn]
  (let [current-player (players/current-player turn)
        player-function (player-turns current-player)
        marker (markers current-player)
        new-board (player-move player-function board marker)]
    new-board))

(defn game-loop [board player-turns markers turn]
  (cli/print-board board)
  (if-not (rules/game-over? board)
    (let [new-board (move board player-turns markers turn)]
      (recur new-board player-turns markers (inc turn)))
    (do
      (if-let [results (rules/assess-winner board)]
        (cli/win results)
        (cli/tie))
      (when (cli/restart?)
        (let [board-size (count board)
              fresh-board (board/render-empty-board board-size)
              turns-played (count (remove number? board))
              start-turn (- turn turns-played)]
          (recur fresh-board player-turns markers start-turn))))))

(defn initialize-game []
  (cli/intro-game)
  (let [options (cli/game-options)
        players (:players options)
        board-size (:board-size options)
        board (board/render-empty-board board-size)
        turn (:turn options)
        markers (:markers options)
        player-turns (get-player-functions players)]
    (game-loop board player-turns markers turn)))
