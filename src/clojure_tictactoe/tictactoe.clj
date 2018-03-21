(ns clojure-tictactoe.tictactoe
  (:gen-class)
  (:require [clojure.string :as string]
            [clojure-tictactoe.cli.output.board-printer :as board-printer]
            [clojure-tictactoe.cli.input.input-getter :as input-getter]))

(def player-atom (atom "x"))

(defn switch-player [current-player]
  (if (= current-player "x")
    "o"
    "x"))

(defn parse-move-input
  ([input]
   (let [formatted-input (read-string input)]
     (when (and
             (number? formatted-input)
             (< formatted-input 9))
       formatted-input))))

(defn is-over
  [taken-spaces]
  (if (= 1 (count (set (vals taken-spaces))))
    true
    false
    )
  )

(defn game-loop
  ([]
  (let [player-choice (input-getter/get-player-choice)]
    (println (board-printer/render-board (board-printer/render-board-spaces player-choice)))
    (swap! player-atom switch-player)
    (game-loop player-choice)))
  ([choices]
   (let [player-choice (input-getter/get-player-choice choices)]
     (println (board-printer/render-board (board-printer/render-board-spaces player-choice)))
     (swap! player-atom switch-player)
     (recur player-choice))))

