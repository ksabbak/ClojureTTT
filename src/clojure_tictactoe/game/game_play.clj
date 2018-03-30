(ns clojure-tictactoe.game.game-play
  (:require   [clojure-tictactoe.cli.output.board-printer :as board-printer]
            [clojure-tictactoe.cli.input.input-getter :as input-getter]
            [clojure-tictactoe.game.players :as players]
            [clojure-tictactoe.game.board :as board]
            [clojure-tictactoe.game.game-rules :as rules]
            ))

(defn player-move
  [move-function board]
  (loop []
    (if-let [new-board (board/mark-space (move-function) @players/player-atom board)]
      new-board
      (do (println "Sorry, that looks taken, try again") 
          (recur)))))

(defn game-loop
  ([]
   (game-loop (board/render-empty-board)))
  ([board]
   (when-not (rules/game-over? board)
     (let [new-board (player-move input-getter/get-player-choice board)]
       (board-printer/print-board new-board)
       (players/player-swap)
       (recur new-board)))))

