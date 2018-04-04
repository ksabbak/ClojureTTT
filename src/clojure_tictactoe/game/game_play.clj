(ns clojure-tictactoe.game.game-play
  (:require   [clojure-tictactoe.cli.output.board-printer :as board-printer]
            [clojure-tictactoe.cli.input.input-getter :as input-getter]
            [clojure-tictactoe.game.players :as players]
            [clojure-tictactoe.game.board :as board]
            [clojure-tictactoe.game.game-rules :as rules]
            [clojure-tictactoe.cli.output.end-game-printer :as end-printer]
            ))

(defn player-move
  [move-function board]
  (loop []
    (if-let [new-board (board/mark-space (move-function) @players/player-atom board)]
      new-board
      (do (println "Sorry, that looks taken, try again") 
          (recur)))))

(defn continue-game
  [board]
    (let [new-board (player-move input-getter/get-player-choice board)]
       (board-printer/print-board new-board)
       (players/player-swap)
       new-board))  

(defn game-loop
  ([]
   (game-loop (board/render-empty-board)))
  ([board]
   (if-not (rules/game-over? board)
      (do (let [new-board (continue-game board)]
        (recur new-board)))
      (do (let [results (rules/assess-win board)]
            (end-printer/print-game-over results))))))

