(ns clojure-tictactoe.game.computer-opponent
  (:require [clojure-tictactoe.game.board :as board]))

(defn assign-opponent-marker [self-marker]
  (->> self-marker
    (seq)
    (first)
    (int)
    (+ 1)
    (char)
    (str)))

(defn deduce-opponent-marker [board self-marker]
  (->> board
    (remove number?)
    (remove #(= self-marker %))))

(defn get-opponent-marker [board self-marker]
  (if-let [opponent-marker (first (deduce-opponent-marker board self-marker))]
    opponent-marker
    (assign-opponent-marker self-marker)))

(defn deduce-turn [board]
  (count (remove number? board)))

(defn get-move [board marker]
  (let [opponent-marker (get-opponent-marker board marker)
        markers {:self marker, :opponent opponent-marker}])
  (rand-nth (board/open-spaces board)))

(defn mini-max [board markers]
  (map #(board/mark-space %)))
