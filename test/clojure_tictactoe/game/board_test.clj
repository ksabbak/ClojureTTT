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

  (testing "open spaces"
    
    (deftest open-spaces-test-empty-board
      (testing "open-spaces returns all spaces on an empty board"
        (is (= (render-empty-board) (open-spaces (render-empty-board))))))
    
    (deftest open-spaces-test-mixed-board
      (testing "open-spaces only returns the open spaces on a board with at least one mark"
        (is (= '(0 2 3 4 5 6 7 8) (open-spaces x-on-one-board)))
        (is (= '(0 8) (open-spaces [0 "x" "o" "x" "x" "o" "x" "o" 8])))))
    
    (deftest open-spaces-test-full
      (testing "open-spaces returns empty list on a full board"
        (is (= '() (open-spaces ["o" "x" "o" "x" "x" "o" "x" "o" "x"])))))
    )

  (testing "mark space"

    (deftest mark-space-test-open
      (testing "Successfully marks an open space"
        (is (= (mark-space 1 "x" (render-empty-board)) x-on-one-board))))
    (deftest mark-space-test-taken
      (testing "Doesn't mark a taken space. Returns nil"
        (is (nil? (mark-space 1 "x" x-on-one-board))))))

  (testing "full board"

    (deftest board-full?-test-empty-board
      (testing "board-full? returns false when given an empty board"
        (is (false? (board-full? (render-empty-board))))))
    
    (deftest board-full?-test-partial-board
      (testing "board-full? returns false when given a partially filled board"
        (is (false? (board-full? ["x" "o" "x" 3 4 5 6 7 8])))))

    (deftest board-full?-test-full-board
      (testing "board-full? returns true when given a full board"
        (is (true? (board-full? ["x" "o" "x" "x" "o" "o" "o" "x" "x"])))))))


