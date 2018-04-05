(ns clojure-tictactoe.game.game-play-test
  (:require [clojure.test :refer :all]
            [clojure.string :as string]
            [clojure-tictactoe.helpers :as helper]
            [clojure-tictactoe.game.game-play :refer :all]
            [clojure-tictactoe.game.computer-opponent :as ai]
            [clojure-tictactoe.cli.input.input-getter :as input-getter]))

(deftest player-move-test-good-argument
  (testing "Accepts good player move"
    (is (= (player-move (fn [& args] 8) [0 1 2 3 4 5 6 7 8]) [0 1 2 3 4 5 6 7 "x"]))))
