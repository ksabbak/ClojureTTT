(ns clojure-tictactoe.game.players
  (:require [clojure.string :as string]
            [clojure-tictactoe.game.computer-opponent :as ai]
            [clojure-tictactoe.cli.input.input-getter :as input-getter]))

(defn current-player [turn]
  (if (even? turn)
    (let [even-player 0]
      even-player)
    (let [odd-player 1]
      odd-player)))

