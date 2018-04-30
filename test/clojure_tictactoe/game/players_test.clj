(ns clojure-tictactoe.game.players-test
  (:require [clojure.test :refer :all]
            [clojure.string :as string]
            [clojure-tictactoe.helpers :as helper]
            [clojure-tictactoe.game.players :refer :all]
            [clojure-tictactoe.game.computer-opponent :as ai]
            [clojure-tictactoe.cli.input.input-getter :as input-getter]))

(testing "Players"
  (testing "current-player"
    (deftest current-player-test-first-player
      (testing "Returns 0 when it's the first player's turn"
        (is (= 0 (current-player 0)))
        (is (= 0 (current-player 2)))
        (is (= 0 (current-player 8)))))

    (deftest current-player-test-second-player
      (testing "Returns 1 when it's the second player's turn"
        (is (= 1 (current-player 1)))
        (is (= 1 (current-player 3)))
        (is (= 1 (current-player 7)))))))

