(ns clojure-tictactoe.cli.input.input-translator
  (:require [clojure.string :as string]))

(defn get-board-size [choice]
  (let [side-length (read-string (str (first choice)))]
        (* side-length side-length)))

(defn get-first-turn [game-type]
  (let [turns-start-at-one 1
        turns-start-at-zero 0
        players (string/split game-type #" ")]
    (if (= "Computer" (first players))
      turns-start-at-one
      turns-start-at-zero)))
