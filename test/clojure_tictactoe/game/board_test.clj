(ns clojure-tictactoe.game.board-test
  (:require [clojure.test :refer :all]
            [clojure.string :as string]
            [clojure-tictactoe.helpers :as helper]
            [clojure-tictactoe.game.board :refer :all]))



(def empty-board
  [0 1 2 3 4 5 6 7 8])

(def x-on-one-board
  [0 "x" 2 3 4 5 6 7 8])

(testing "Board"

  (testing "space is open"
    
    (deftest open?-test-is-open
      (testing "open? returns true when no marker on space"
        (is (true? (open? 1 empty-board)))))
    
    (deftest open?-test-is-not-open
      (testing "open? returns false when marker is on space"
        (is (false? (open? 1 x-on-one-board)))))

   (deftest open?-test-integer-string
    (testing "open? returns false when marker is on a space and marker is a string of a number"
     (is (false? (open? 1 [0 "1" 2 3 4 5 6 7 8]))))))
  
  (testing "mark space"

    (deftest mark-space-test-open
      (testing "Successfully marks an open space"
        (is (=(mark-space 1 "x" empty-board) x-on-one-board))))
    (deftest mark-space-test-taken
      (testing "Doesn't mark a taken space. Returns nil"
        (is (nil? (mark-space 1 "x" x-on-one-board))))))) 
   
