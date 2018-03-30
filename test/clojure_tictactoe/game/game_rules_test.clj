(ns clojure-tictactoe.game.game-rules-test
  (:require [clojure.test :refer :all]
            [clojure.string :as string]
            [clojure-tictactoe.helpers :as helper]
            [clojure-tictactoe.game.game-rules :refer :all]))

(testing "win-logic"

  (testing "assess-win"

    (deftest assess-win-test-no-win
      (testing "assess-win knows there's no win on an empty board"
        (is (nil? (assess-win [0 1 2 3 4 5 6 7 8])))))

    (deftest assess-win-test-top-row
      (testing "assess-win returns the winner on a game with a top row win"
        (is (= "x" (assess-win ["x" "x" "x" "o" "o" 5 6 7 8])))))

    (deftest assess-win-test-middle-row
      (testing "assess-win returns the winner on a game with a middle row win"
        (is (= "o" (assess-win ["x" "x" 2 "o" "o" "o" 6 7 "x"]))))) 

    (deftest assess-win-test-bottom-row
      (testing "assess-win returns the winner on a game with a bottom-row win"
        (is (= "x" (assess-win [0 1 2 "o" "o" 5 "x" "x" "x"])))))

    (deftest assess-win-test-left-column
      (testing "assess-win returns the winner on a game with a left-column win"
        (is (= "o" (assess-win ["o" "x" 2 "o" "x" "x" "o" 7 8])))))

    (deftest assess-win-test-middle-column
      (testing "assess-win returns the winner on a game with a middle column win"
        (is (= "x" (assess-win ["o" "x" 2 "o" "x" 5 6 "x" 8])))))

    (deftest assess-win-test-right-column
      (testing "assess-win returns the winner on a game with a right-column win"
        (is (= "o" (assess-win ["x" "x" "o" 3 4 "o" "x" 7 "o"])))))

    (deftest assess-win-test-diagonal-1
      (testing "assess-win returns the winner on a game with a win from the upper left corner to the lower right one"
        (is (= "x" (assess-win ["x" "o" "o" 3 "x" 5 6 7 "x"])))))

    (deftest assess-win-test-diagonal-2
      (testing "assess-win returns the winner on a game with a win from the bottom left corner to the upper right one"
        (is (= "o" (assess-win ["x" 1 "o" 3 "o" "x" "o" "x" 8])))))))

(testing "Game over"

  (deftest game-over?-test-empty-board
    (testing "game-over? returns false when given an empty board"
      (is (false? (game-over? [0 1 2 3 4 5 6 7 8])))))

  (deftest game-over?-test-mid-game
    (testing "game-over? returns false when there have been plays but the game isn't over"
      (is (false? (game-over? ["x" "o" 2 3 4 5 6 7 8])))))

  (deftest game-over?-test-win
    (testing "game-over? returns true when there's been a win"
      (is (true? (game-over? ["x" "x" "o" 3 4 "o" "x" 7 "o"])))))

  (deftest game-over-test-tie
    (testing "game-over? returns true when there are no moves left and no win"
      (is (true? (game-over? ["x" "o" "x" "x" "o" "o" "o" "x" "x"]))))))
