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
        computer-second (m/game-options 2)]
    (if (= computer-second game-type)
      turns-start-at-one
      turns-start-at-zero)))

(defn parse-game-choice [game-type]
  (let [humans-only (m/game-options 0)]
    (if-not (= humans-only game-type)
      [:human :computer]
      [:human :human])))

(defn intro-game []
  (m/clear-screen)
  (instructions-printer/print-game-intro)
  (input-getter/continue-to-game))

(defn restart? []
  (let [choice (input-getter/get-option-choice m/yes-no m/restart)
        yes (first m/yes-no)]
    (= yes choice)))

(defn print-board [board]
  (m/clear-screen)
  (board-printer/print-board board))

(defn get-player-move [board _]
  (input-getter/get-player-move board _))

(defn win [winner]
  (end-printer/end-game-printer (end-printer/game-won-message winner)))

(defn tie []
  (end-printer/end-game-printer m/end-tie))

(defn game-options []
  (let [game-choice (input-getter/get-option-choice m/game-options m/game-choice-message)
        players (parse-game-choice game-choice)
        board-choice (input-getter/get-option-choice m/board-options m/board-size-message)
        board-size (get-board-size board-choice)
        turn (get-first-turn game-choice)
        markers (input-getter/acquire-both-markers m/players)]
    {:players players, :board-size board-size, :turn turn, :markers markers}))
