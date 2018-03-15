(ns clojure-tictactoe.tictactoe-test
  (:require [clojure.test :refer :all]
            [clojure.string :as string]
            [clojure-tictactoe.tictactoe :refer :all]))

(deftest intro-game-message-test 
  (testing "Intro messages"
    (is (string/includes? (with-out-str (intro-game)) "TicTacToe"))))

(deftest board-test
  (testing "Displays a simple blank board"
    (is (= " 0 | 1 | 2 \n===+===+===\n 3 | 4 | 5 \n===+===+===\n 6 | 7 | 8 \n" (board [0 1 2 3 4 5 6 7 8])))))
