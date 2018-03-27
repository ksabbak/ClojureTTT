(ns clojure-tictactoe.game.board-test
  (:require [clojure.test :refer :all]
            [clojure.string :as string]
            [clojure-tictactoe.helpers :as helper]
            [clojure-tictactoe.game.board :refer :all]))


(def x-on-one-board
  [0 "x" 2 3 4 5 6 7 8])

(testing "Board"

  (testing "render-empty-board"

    (deftest render-empty-board-test
      (testing "renders an empty board"
        (is (= (render-empty-board) [0 1 2 3 4 5 6 7 8])))))

  (testing "space is open"

    (deftest space-is-open?-test-is-open
      (testing "space-is-open? returns true when no marker on space"
        (is (true? (space-is-open? 1 (render-empty-board))))))

    (deftest space-is-open?-test-is-not-open
      (testing "space-is-open? returns false when marker is on space"
        (is (false? (space-is-open? 1 x-on-one-board)))))

    (deftest space-is-open?-test-integer-string
      (testing "space-is-open? returns false when marker is on a space and marker is a string of a number"
        (is (false? (space-is-open? 1 [0 "1" 2 3 4 5 6 7 8]))))))


  (testing "mark space"

    (deftest mark-space-test-open
      (testing "Successfully marks an open space"
        (is (=(mark-space 1 "x" (render-empty-board)) x-on-one-board))))
    (deftest mark-space-test-taken
      (testing "Doesn't mark a taken space. Returns nil"
        (is (nil? (mark-space 1 "x" x-on-one-board)))))))

