(ns clojure-tictactoe.cli.input.input-translator
  (:require [clojure.string :as string]
            [clojure-tictactoe.cli.output.board-printer :as board-printer]
            [clojure-tictactoe.cli.output.end-game-printer :as end-printer]
            [clojure-tictactoe.cli.output.instructions-printer :as instructions-printer]
            [clojure-tictactoe.cli.input.input-getter :as input-getter]
            [clojure-tictactoe.cli.input.input-translator :as input-translator]))

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
