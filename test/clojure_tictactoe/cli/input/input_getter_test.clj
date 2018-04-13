(ns clojure-tictactoe.cli.input.input-getter-test
  (:require [clojure.test :refer :all]
            [clojure.string :as string]
            [clojure-tictactoe.helpers :as helper]
            [clojure-tictactoe.cli.input.input-getter :refer :all]
            [clojure-tictactoe.cli.output.instructions-printer :as instructions-printer]))

(testing "Game start"
  (testing "continue-to-game"
    (deftest continue-to-game-message-test
      (testing "The message displays properly"
        (is (string/includes? (with-out-str (with-in-str "\n" (continue-to-game))) "continue"))))

    (deftest continue-to-game-input-test
      (testing "The function returns nil once newline has been entered"
        (is (nil? (with-in-str "\n" (continue-to-game)))))))

  (testing "get-game-type"

    (deftest get-game-type-test-display
      (testing "Prints proper message"
        (is (string/includes? (with-out-str (with-in-str "1" (get-game-type ["Pie" "Cake" "Ice cream"]))) instructions-printer/game-choice-message))
        (is (string/includes? (with-out-str (with-in-str "1" (get-game-type ["Pie" "Cake" "Ice cream"]))) "1. Pie\n2. Cake\n3. Ice cream"))))

    (deftest get-game-type-test-input
      (testing "Doesn't accept input that isn't 1 or 2"
        (is (= (with-in-str (helper/make-input ["Alpha chars" "3" "2"]) (get-game-type ["Cake" "Pie"])) "Pie"))))))


(testing "Player moves"
  (testing "get-player-choice"
    (deftest get-player-choice-test
      (testing "Good move input returns that input as an integer"
        (is (= (with-in-str "0" (get-player-choice 9)) 0))))

    (deftest get-player-choice-test-bad-arguments
      (testing "Doesn't accept non-numeric input"
        (is (= (with-in-str (helper/make-input ["Alphabet" "8"]) (get-player-choice 9)) 8)))))

  (testing "get-player-move"
    (deftest get-player-move-test
      (testing "Doesn't accept taken input, does accept open space"
        (is (= (with-in-str (helper/make-input ["0" "8"]) (get-player-move ["x" 1 2 3 4 5 6 7 8])) 8 )))))

  (testing "parse-move-input"
    (deftest parse-move-input-test-with-number-input
      (testing "Returns the int value of a numerical input"
        (is (= (parse-move-input "2" 9) 2))))

    (deftest parse-move-input-test-with-non-number-input
      (testing "Returns nil if the input value is not a number"
        (is (nil? (parse-move-input "Hello!" 9)))))

    (deftest parse-move-input-test-with-too-high-number-input
      (testing "Returns nil if the input value is greater than board size"
        (is (nil? (parse-move-input "100" 9)))))))
