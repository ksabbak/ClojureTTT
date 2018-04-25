(ns clojure-tictactoe.game.game-play-test
  (:require [clojure.test :refer :all]
            [clojure.string :as string]
            [clojure-tictactoe.helpers :as helper]
            [clojure-tictactoe.game.game-play :refer :all]
            [clojure-tictactoe.game.computer-opponent :as ai]
            [clojure-tictactoe.cli.input.input-getter :as input-getter]))

(deftest player-move-test-good-argument
  (testing "Accepts good player move"
    (is (= (player-move (fn [& args] 8) [0 1 2 3 4 5 6 7 8] "x") [0 1 2 3 4 5 6 7 "x"]))))

(testing "Board Size"
  (testing "get-board-size"
    (deftest render-empty-board-test
      (testing "returns an empty board of appropriate size"
        (is (= 9 (get-board-size "3x3")))
        (is (= 16 (get-board-size "4x4")))))))
