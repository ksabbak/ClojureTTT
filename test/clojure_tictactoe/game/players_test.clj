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
        (is (= ai/make-move (choose-player-function "Computer" 1)))))

    (deftest choose-player-function-test-player-two
      (testing "Returns the human-move function for player two in a vs. human game"
        (is (= input-getter/get-player-move (choose-player-function "Human vs. Human" 1)))))))

(testing "Player Markers"

  (testing "acceptable-marker-option?"

    (deftest acceptable-marker-option?-test-too-long
      (testing "returns false if the potential marker is longer than one char"
        (is (false? (acceptable-marker-option? "Nope")))))

    (deftest acceptable-marker-option?-test-good
      (testing "returns true for markers that are only one char long"
        (is (true? (acceptable-marker-option? "x"))))))

  (testing "distinct-markers?"

    (deftest distinct-markers?-test-redundant-markers
      (testing "Returns false if both markers are the same"
        (is (false? (distinct-markers? '("x" "x"))))))

    (deftest distinct-markers?-test-distinct-markers
      (testing "Returns true if markers are distinct"
        (is (true? (distinct-markers? '("o" "x")))))))

  (testing "acquire-one-marker"

    (deftest acquire-one-marker-test
      (testing "Only accepts string that is one char"
        (is (= "x" (with-in-str (helper/make-input ["too long" " " "x"]) (acquire-one-marker "Player 1")))))))

  (testing "acquire-both-markers"

    (deftest acquire-both-markers-test
      (testing "Only accepts distinct markers"
        (is (= '("x" "o") (with-in-str (helper/make-input ["x" "x" "x" "o"]) (acquire-both-markers '("Player 1" "Player 2")))))))))
