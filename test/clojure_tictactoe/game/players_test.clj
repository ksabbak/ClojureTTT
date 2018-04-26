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
        (is (= 1 (current-player 7))))))

  (testing "player move logic"
    (deftest choose-player-function-test-player-one
      (testing "Returns the human-move function for player one"
        (is (= input-getter/get-player-move (choose-player-function "Computer" 0)))
        (is (= input-getter/get-player-move (choose-player-function "Human" 0)))))

    (deftest choose-player-function-test-player-two
      (testing "Returns the ai-move function for player two in a vs. computer game"
        (is (= ai/get-move (choose-player-function "Computer" 1)))))

    (deftest choose-player-function-test-player-two
      (testing "Returns the human-move function for player two in a vs. human game"
        (is (= input-getter/get-player-move (choose-player-function "Human vs. Human" 1)))))))

