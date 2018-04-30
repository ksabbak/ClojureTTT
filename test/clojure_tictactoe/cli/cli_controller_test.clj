(ns clojure-tictactoe.cli.cli-controller
  (:require [clojure.test :refer :all]
            [clojure.string :as string]
            [clojure-tictactoe.helpers :as helper]
            [clojure-tictactoe.cli.input.input-translator :refer :all]))

(testing "Board Size"
  (testing "get-board-size"
    (deftest render-empty-board-test
      (testing "returns an empty board of appropriate size"
        (is (= 9 (get-board-size "3x3")))
        (is (= 16 (get-board-size "4x4")))))))

(testing "Turns"
  (testing "get-first-turn"
    (deftest get-first-turn-test-ai-first
      (testing "Returns one when the ai goes first"
        (is (= 1 (get-first-turn "Computer vs. Human")))))

    (deftest get-first-turn-test-human-first
      (testing "Returns zero when the human player plays first"
        (is (= 0 (get-first-turn "Human vs. Human")))
        (is (= 0 (get-first-turn "Human vs. Computer")))))))

(testing "Game choice"
  (testing "parse-game-choice"
    (deftest parse-game-choice-test-with-computer
      (testing "Returns [:human :computer] for both human vs. computer types"
        (is (= [:human :computer] (parse-game-choice "Human vs. Computer")))
        (is (= [:human :computer] (parse-game-choice "Computer vs. Human")))))

    (deftest parse-game-choice-test-with-computer
      (testing "Returns [:human :human] human only games"
        (is (= [:human :human] (parse-game-choice "Human vs. Human")))))))

(testing "Restart"
  (testing "restart?"
    (deftest restart?-test-yes
      (is (true? (restart? "Yes!"))))
    (deftest restart?-test-no
      (is (false? (restart? "No"))))))
