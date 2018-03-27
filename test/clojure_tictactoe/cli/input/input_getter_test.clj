(ns clojure-tictactoe.cli.input.input-getter-test
  (:require [clojure.test :refer :all]
            [clojure.string :as string]
            [clojure-tictactoe.helpers :as helper]
            [clojure-tictactoe.cli.input.input-getter :refer :all]))

(testing "Game start"
  (testing "continue-to-game"
    (deftest continue-to-game-message-test
      (testing "The message displays properly"
        (is (string/includes? (with-out-str (with-in-str "\n" (continue-to-game))) "continue"))))

    (deftest continue-to-game-input-test
      (testing "The function returns nil once newline has been entered"
        (is (= (with-in-str "\n" (continue-to-game)) nil ))))))

(testing "Player moves"
  (testing "get-player-choice"
    (deftest get-player-choice-test
      (testing "Good move input returns that input as an integer"
        (is (= (with-in-str "0" (get-player-choice)) 0))))

    (deftest get-player-choice-test-bad-arguments
      (testing "Doesn't accept non-numeric input"
        (is (= (with-in-str (helper/make-input ["Alphabet" "8"]) (get-player-choice)) 8 )))))

 (testing "parse-move-input"
    (deftest parse-move-input-test-with-number-input
      (testing "Returns the int value of a numerical input"
        (is (= (parse-move-input "2") 2))))

    (deftest parse-move-input-test-with-non-number-input
      (testing "Returns nil if the input value is not a number"
        (is (= (parse-move-input "Hello!") nil))))

    (deftest parse-move-input-test-with-too-high-number-input
      (testing "Returns nil if the input value is greater than board size"
        (is (= (parse-move-input "100") nil))))))
