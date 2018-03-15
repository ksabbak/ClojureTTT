(ns clojure-tictactoe.tictactoe-test
  (:require [clojure.test :refer :all]
            [clojure.string :as string]
            [clojure-tictactoe.tictactoe :refer :all]))

(deftest intro-game-message-test 
  (testing "Intro messages"
    (is (string/includes? (with-out-str (print-game-intro)) "TicTacToe"))))

(deftest board-test
  (testing "Displays a simple blank board"
    (is (= " 0 | 1 | 2 \n===+===+===\n 3 | 4 | 5 \n===+===+===\n 6 | 7 | 8 \n" (board [0 1 2 3 4 5 6 7 8])))))

(deftest make-board-filler-with-no-arguments-test
  (testing "make-board-filler with no arguments returns spaces that haven't been marked"
    (is (= (make-board-filler) [0 1 2 3 4 5 6 7 8]))))
