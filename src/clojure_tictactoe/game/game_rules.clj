(ns clojure-tictactoe.game.game-rules
  (:require [clojure-tictactoe.game.board :as board]))

(defn horizontal-win-options [board]
  (partition (board/side-length board) (range (count board))))

(defn vertical-win-options [board]
  (apply map list (horizontal-win-options board)))

(defn diagonal-win-options [board]
  (let [side-length (board/side-length board)
        top-left-to-bottom-right-diagonal (take side-length (range 0 (count board) (+ 1 side-length)))
        bottom-left-to-top-right-diagonal (take side-length (range (- side-length 1) (count board) (- side-length 1)))]
    (list top-left-to-bottom-right-diagonal bottom-left-to-top-right-diagonal)))

(defn all-win-options [board]
  (concat (diagonal-win-options board) (vertical-win-options board) (horizontal-win-options board)))

(defn potential-wins [board]
  (map #(map board %) (all-win-options board)))

(defn winner? [board]
  (some? (some #(apply = %) (potential-wins board))))

(defn assess-winner [board]
  (-> #(when (apply = %) %)
      (some (potential-wins board))
      (first)))

(defn game-over? [board]
  (or
    (board/board-full? board)
    (winner? board)))
