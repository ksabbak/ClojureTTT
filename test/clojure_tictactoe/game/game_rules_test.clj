(ns clojure-tictactoe.game.game-rules-test
  (:require [clojure.test :refer :all]
            [clojure.string :as string]
            [clojure-tictactoe.helpers :as helper]
            [clojure-tictactoe.game.game-rules :refer :all]))

(testing "win-logic"

  (testing "winner?"

    (deftest winner?-test-no-win
      (testing "winner? knows there's no win on an empty board"
        (is (false? (winner? [0 1 2 3 4 5 6 7 8])))))

    (deftest winner?-test-top-row
      (testing "winner? returns the winner on a game with a top row win"
        (is (true? (winner? ["x" "x" "x" "o" "o" 5 6 7 8])))))

    (deftest winner?-test-middle-row
      (testing "winner? returns the winner on a game with a middle row win"
        (is (true? (winner? ["x" "x" 2 "o" "o" "o" 6 7 "x"])))))

    (deftest winner?-test-bottom-row
      (testing "winner? returns the winner on a game with a bottom-row win"
        (is (true? (winner? [0 1 2 "o" "o" 5 "x" "x" "x"])))))

    (deftest winner?-test-left-column
      (testing "winner? returns the winner on a game with a left-column win"
        (is (true? (winner? ["o" "x" 2 "o" "x" "x" "o" 7 8])))))

    (deftest winner?-test-middle-column
      (testing "winner? returns the winner on a game with a middle column win"
        (is (true? (winner? ["o" "x" 2 "o" "x" 5 6 "x" 8])))))

    (deftest winner?-test-right-column
      (testing "winner? returns the winner on a game with a right-column win"
        (is (true? (winner? ["x" "x" "o" 3 4 "o" "x" 7 "o"])))))

    (deftest winner?-test-diagonal-1
      (testing "winner? returns the winner on a game with a win from the upper left corner to the lower right one"
        (is (true? (winner? ["x" "o" "o" 3 "x" 5 6 7 "x"])))))

    (deftest winner?-test-diagonal-2
      (testing "winner? returns the winner on a game with a win from the bottom left corner to the upper right one"
        (is (true? (winner? ["x" 1 "o" 3 "o" "x" "o" "x" 8]))))))

  (testing "assess-winner"

    (deftest assess-winner-test-no-win
      (testing "assess-winner knows there's no win on an empty board"
        (is (nil? (assess-winner [0 1 2 3 4 5 6 7 8])))))

    (deftest assess-winner-test-top-row
      (testing "assess-winner returns the winner on a game with a top row win"
        (is (= "x" (assess-winner ["x" "x" "x" "o" "o" 5 6 7 8])))))

    (deftest assess-winner-test-middle-row
      (testing "assess-winner returns the winner on a game with a middle row win"
        (is (= "o" (assess-winner ["x" "x" 2 "o" "o" "o" 6 7 "x"])))))

    (deftest assess-winner-test-bottom-row
      (testing "assess-winner returns the winner on a game with a bottom-row win"
        (is (= "x" (assess-winner [0 1 2 "o" "o" 5 "x" "x" "x"])))))

    (deftest assess-winner-test-left-column
      (testing "assess-winner returns the winner on a game with a left-column win"
        (is (= "o" (assess-winner ["o" "x" 2 "o" "x" "x" "o" 7 8])))))

    (deftest assess-winner-test-middle-column
      (testing "assess-winner returns the winner on a game with a middle column win"
        (is (= "x" (assess-winner ["o" "x" 2 "o" "x" 5 6 "x" 8])))))

    (deftest assess-winner-test-right-column
      (testing "assess-winner returns the winner on a game with a right-column win"
        (is (= "o" (assess-winner ["x" "x" "o" 3 4 "o" "x" 7 "o"])))))

    (deftest assess-winner-test-diagonal-1
      (testing "assess-winner returns the winner on a game with a win from the upper left corner to the lower right one"
        (is (= "x" (assess-winner ["x" "o" "o" 3 "x" 5 6 7 "x"])))))

    (deftest assess-winner-test-diagonal-2
      (testing "assess-winner returns the winner on a game with a win from the bottom left corner to the upper right one"
        (is (= "o" (assess-winner ["x" 1 "o" 3 "o" "x" "o" "x" 8])))))))

(testing "Win options"

  (deftest horizontal-win-options-test
    (testing "returns the list of horizontal win options for that board"
      (is (= '((0 1 2) (3 4 5) (6 7 8)) (horizontal-win-options [0 1 2 3 4 5 6 7 8])))))

  (deftest vertical-win-options-test
    (testing "returns the list of vertical win options for that board"
      (is (= '((0 3 6) (1 4 7) (2 5 8)) (vertical-win-options [0 1 2 3 4 5 6 7 8])))))

   (deftest diagonal-win-options-test
    (testing "returns the list of diagonal win options for that board"
      (is (= '((0 4 8) (2 4 6)) (diagonal-win-options [0 1 2 3 4 5 6 7 8])))))

   (deftest all-win-options-test
    (testing "returns the list of all win options for that board"
      (is (= (set '((0 1 2) (3 4 5) (6 7 8) (0 3 6) (1 4 7) (2 5 8) (0 4 8) (2 4 6))) (set (all-win-options [0 1 2 3 4 5 6 7 8]))))))


  )

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
