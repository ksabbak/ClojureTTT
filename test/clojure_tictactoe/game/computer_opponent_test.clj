(ns clojure-tictactoe.game.computer-opponent-test
  (:require [clojure.test :refer :all]
            [clojure.string :as string]
            [clojure-tictactoe.helpers :as helper]
            [clojure-tictactoe.game.computer-opponent :refer :all]))

(testing "Computer opponent"

  (testing "make-move"

    (deftest make-move-test-open-board
      (testing "make-move will return a valid board space on an open board"
        (is (contains? #{0 1 2 3 4 5 6 7 8} (make-move [0 1 2 3 4 5 6 7 8])))))

    (deftest make-move-test-returns-empty-space-only-one
      (testing "make-move will return the only empty space on a board that has one open space"
        (is (= 8 (make-move ["x" "o" "x" "o" "x" "o" "o" "x" 8])))))

    (deftest make-move-test-many-options
      (testing "make-move will return one of any of the open spaces on a board with some taken spaces"
        (is (contains? #{0 1} (make-move [0 1 "x" "o" "x" "o" "o" "x" "o" "x" "o"])))))))
