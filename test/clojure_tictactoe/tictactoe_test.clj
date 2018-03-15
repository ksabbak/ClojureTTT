(ns clojure-tictactoe.tictactoe-test
  (:require [clojure.test :refer :all]
            [clojure.string :as string]
            [clojure-tictactoe.tictactoe :refer :all]))

(deftest intro-game-message-test 
  (testing "Intro messages"
    (is (string/includes? (with-out-str (intro-game)) "TicTacToe"))))
