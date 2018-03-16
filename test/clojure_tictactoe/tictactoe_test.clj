(ns clojure-tictactoe.tictactoe-test
  (:require [clojure.test :refer :all]
            [clojure.string :as string]
            [clojure-tictactoe.helpers :as helper]
            [clojure-tictactoe.tictactoe :refer :all]))

(deftest intro-game-message-test 
  (testing "Intro messages"
    (is (string/includes? (with-out-str (print-game-intro)) "TicTacToe"))))

(deftest continue-to-game-message-test
  (testing "The message displays properly"
    (is (string/includes? (with-out-str (with-in-str "\n" (continue-to-game))) "continue"))))

(deftest continue-to-game-input-test
    (testing "The function returns nil once newline has been entered"
          (is (= (with-in-str "\n" (continue-to-game)) nil ))))

(deftest board-test
  (testing "Displays a simple blank board"
    (is (= " 0 | 1 | 2 \n===+===+===\n 3 | 4 | 5 \n===+===+===\n 6 | 7 | 8 \n" (board [0 1 2 3 4 5 6 7 8])))))

(deftest make-board-filler-with-no-arguments-test
  (testing "make-board-filler with no arguments returns spaces that haven't been marked"
    (is (= (make-board-filler) [0 1 2 3 4 5 6 7 8]))))

(deftest make-board-filler-with-a-move-on-space-0-test
  (testing "Returns spaces where only the first space has been marked"
    (is (= (make-board-filler {0 "x"}) ["x" 1 2 3 4 5 6 7 8]))))

(deftest make-board-filler-with-a-move-on-multiple-spaces-test
   (testing "Returns spaces where the appropriate space is marked"
     (is (= (make-board-filler {3 "x", 7 "o", 2 "x"}) [0 1 "x" "x" 4 5 6 "o" 8]))))

(deftest get-player-choice-no-arguments
  (testing "No arguments returns map with player input and x"
    (is (= (with-in-str "0" (get-player-choice)) {0 "x"}))))

(deftest get-player-choice-no-arguments-bad-input
  (testing "Doesn't accept non-numeric input"
    (is (= (with-in-str (helper/make-input ["Alphabet" "8"]) (get-player-choice)) {8 "x"}))))

(deftest get-player-choice-with-arguments-good-input
  (testing "Get player choice with map argument returns that map plus the new choice"
    (is (= (with-in-str "0" (get-player-choice {8 "x"})) {0 "x", 8 "x"}))))

(deftest get-player-choice-with-arguments-bad-input
  (testing "Doesn't accept non-numeric or too-high number input"
    (is (= (with-in-str (helper/make-input ["Alphabet" "270" "8"]) (get-player-choice {0 "x"})) {0 "x", 8 "x"}))))

(deftest get-player-choice-with-arguments-repeat-input
  (testing "Doesn't accept a space that's already taken"
    (is (= (with-in-str (helper/make-input ["0" "8"]) (get-player-choice {0 "x"})) {0 "x", 8 "x"}))))

(deftest parse-move-input-test-with-number-input
  (testing "Returns the int value of a numerical input"
    (is (= (parse-move-input "2") 2))))

(deftest parse-move-input-test-with-non-number-input
  (testing "Returns nil if the input value is not a number"
    (is (= (parse-move-input "Hello!") nil))))

(deftest parse-move-input-test-with-too-high-number-input
  (testing "Returns nil if the input value is greater than board size"
    (is (= (parse-move-input "100") nil))))

(deftest switch-player-test-when-x
  (testing "Returns o when x is passed in"
    (is (= "o" (switch-player "x")))))

(deftest switch-player-test-when-o
  (testing "Returns x when o is passed in"
    (is (= "x" (switch-player "o")))))

;(deftest game-loop-prints-board
;  (testing "Game loop can print the board"
;    (is (string/includes? (with-in-str (helper/make-input ["1" "quit"]) (with-out-str (game-loop))) "0 | x | 2 \n"))))

