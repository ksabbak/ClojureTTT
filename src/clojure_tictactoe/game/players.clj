(ns clojure-tictactoe.game.players
  (:require [clojure.string :as string]
            [clojure-tictactoe.game.computer-opponent :as ai]
            [clojure-tictactoe.cli.input.input-getter :as input-getter]))

(defn acceptable-marker-option? [potential-marker]
  (-> potential-marker
      (count)
      (= 1)))

(defn distinct-markers? [markers]
  (->> markers
       (apply =)
       (not)))

(defn acquire-one-marker [player]
  (let [marker (input-getter/get-player-marker player)]
    (if (acceptable-marker-option? marker)
      marker
      (recur player))))

(defn acquire-both-markers [players]
  (let [markers (map acquire-one-marker players)]
    (if (distinct-markers? markers)
      (into [] markers)
      (recur players))))

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
    ai/make-move
    input-getter/get-player-move))
