(ns clojure-tictactoe.tictactoe-test
  (:require [clojure.test :refer :all]
            [clojure.string :as string]
            [clojure-tictactoe.helpers :as helper]
            [clojure-tictactoe.tictactoe :refer :all]))

(testing "Players"
  (testing "switch-players"
    (deftest switch-player-test-when-x
      (testing "Returns o when x is passed in"
        (is (= "o" (switch-player "x")))))

    (deftest switch-player-test-when-o
      (testing "Returns x when o is passed in"
        (is (= "x" (switch-player "o")))))))

(testing "End game"
  (testing "is-over"
    (deftest is-over-test-true-when-horizontal-win
      (testing "Returns true when top row is taken by the same marker"
        (is (= true (is-over {0 "x", 1 "x", 2 "x"}))))
      (testing "Returns true when the middle row is taken by the same marker"
        (is (= true (is-over {3 "x", 4 "x", 5 "x"}))))
      (testing "Returns true when the last row is taken by the same marker"
        (is (= true (is-over {6 "x", 7 "x", 8 "x"})))))
    (deftest is-over-test-false-when-mismatching-tokens-on-row
      (testing "Returns false when top row is full but has different markers"
        (is (= false (is-over {0 "x", 1 "o", 2 "x"})))))
    (def is-over-test-false-when-no-win-and-board-is-not-full
      (testing "Returns false when only two markers are on the board"
        (is (= false (is-over {0 "x", 1 "x"})))))))

