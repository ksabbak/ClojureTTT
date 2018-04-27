(ns clojure-tictactoe.cli.cli-controller
  (:require [clojure.string :as string]
            [clojure-tictactoe.cli.output.board-printer :as board-printer]
            [clojure-tictactoe.cli.output.end-game-printer :as end-printer]
            [clojure-tictactoe.cli.output.instructions-printer :as instructions-printer]
            [clojure-tictactoe.cli.output.messages :as m]
            [clojure-tictactoe.cli.input.input-getter :as input-getter]))

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

(defn intro-game []
  (instructions-printer/print-game-intro)
  (input-getter/continue-to-game))

(defn restart? []
  (let [choice (input-getter/get-option-choice m/yes-no m/restart)]
    (= "Yes!" choice)))

(defn game-options []
  (let [game-type (input-getter/get-option-choice m/game-options m/game-choice-message)
      board-choice (input-getter/get-option-choice m/board-options m/board-size-message)
      board-size (get-board-size board-choice)
      turn (get-first-turn game-type)
      markers (input-getter/acquire-both-markers m/players)]
  {:game-type game-type, :board-size board-size, :turn turn, :markers markers}))
