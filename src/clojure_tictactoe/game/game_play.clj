(ns clojure-tictactoe.game.game-play
(:require   [clojure-tictactoe.cli.output.board-printer :as board-printer]
            [clojure-tictactoe.cli.input.input-getter :as input-getter]
            [clojure-tictactoe.game.players :as players]
            ))

(defn game-loop
  ([]
  (let [player-choice (input-getter/get-player-choice)]
    (println (board-printer/render-board (board-printer/render-board-spaces player-choice)))
    (players/player-swap)
    (game-loop player-choice)))
  ([choices]
   (let [player-choice (input-getter/get-player-choice choices)]
     (println (board-printer/render-board (board-printer/render-board-spaces player-choice)))
     (players/player-swap)
     (recur player-choice))))
