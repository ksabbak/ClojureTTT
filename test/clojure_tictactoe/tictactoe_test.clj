(ns clojure-tictactoe.tictactoe-test
  (:require [clojure.test :refer :all]
            [clojure.string :as string]
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

(deftest make-board-filler-with-amove-on-multiple-spaces-test
   (testing "Returns spaces where the appropriate space is marked"
     (is (= (make-board-filler {3 "x", 7 "o", 2 "x"}) [0 1 "x" "x" 4 5 6 "o" 8]))))
