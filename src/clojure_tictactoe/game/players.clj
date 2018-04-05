(ns clojure-tictactoe.game.players
  (:require [clojure-tictactoe.game.computer-opponent :as ai]
            [clojure-tictactoe.cli.input.input-getter :as input-getter]
            [clojure-tictactoe.game.board :as board]
    ))

(def player-atom (atom "x"))

(defn switch-player [current-player]
  (if (= current-player "x")
    "o"
    "x"))

(defn player-swap []
  (swap! player-atom switch-player))

(defn human-player-move [board]
  (loop []
    (let [move (input-getter/get-player-choice)]
      (if (board/space-is-open? move board)
          move
          (do (println "Sorry, that looks taken, try again")
              (recur))))))

(defn choose-player-function [player]
  (if (= player "x")
    human-player-move
    ai/make-move
  ))
