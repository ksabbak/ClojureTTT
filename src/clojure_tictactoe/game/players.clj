(ns clojure-tictactoe.game.players
  (:require [clojure-tictactoe.game.computer-opponent :as ai]
            [clojure-tictactoe.cli.input.input-getter :as input-getter]))

(def player-atom (atom "x"))

(defn switch-player [current-player]
  (if (= current-player "x")
    "o"
    "x"))

(defn player-swap []
  (swap! player-atom switch-player))

(defn human-player-move [board]
  (input-getter/get-player-move board))

(defn choose-player-function [player]
  (if (= player "x")
    human-player-move
    ai/make-move))
