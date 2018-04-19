(ns clojure-tictactoe.game.players
  (:require [clojure.string :as string]
            [clojure-tictactoe.game.computer-opponent :as ai]
            [clojure-tictactoe.cli.input.input-getter :as input-getter]))

(defn current-player [turn]
  (if (even? turn)
    (let [first-player 0]
      first-player)
    (let [second-player 1]
      second-player)))

(defn choose-player-function [game-type turn]
  (if (and
        (= 1 (current-player turn))
        (string/includes? game-type "Computer"))
    ai/get-move
    input-getter/get-player-move))
