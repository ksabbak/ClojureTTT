(ns clojure-tictactoe.game.game-play
(:require   [clojure-tictactoe.cli.output.board-printer :as board-printer]
            [clojure-tictactoe.cli.input.input-getter :as input-getter]
            [clojure-tictactoe.game.players :as players]
            [clojure-tictactoe.game.board :as board]
            ))

(defn player-move
  [move-function board]
  (loop []
    (println (move-function))
    (if-let [new-board (board/mark-space (move-function) @players/player-atom board)]
      new-board
      (do (println "Sorry, that looks taken, try again") ;;TODO: move this
        (recur)))))

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

