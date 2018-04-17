(ns clojure-tictactoe.cli.input.input-checker
  (:require [clojure.string :as string]
            [clojure-tictactoe.game.board :as board]))


(defn valid-numeral? [user-input choices]
  (let [choice-limit (+ (count choices) 1)]
    (and
      (number? user-input)
      (< user-input choice-limit)
      (> user-input 0))))

(defn valid-move? [user-input board]
  (board/space-is-open? user-input board))

(defn acceptable-marker-option? [potential-marker]
  (-> potential-marker
      (count)
      (= 1)))

(defn distinct-markers? [markers]
  (->> markers
       (apply =)
       (not)))
