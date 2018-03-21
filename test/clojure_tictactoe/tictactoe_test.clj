(ns clojure-tictactoe.tictactoe-test
  (:require [clojure.test :refer :all]
            [clojure.string :as string]
            [clojure-tictactoe.helpers :as helper]
            [clojure-tictactoe.tictactoe :refer :all]))

(testing "Rendering board"
  (testing "render-board"
    (deftest render-board-test
      (testing "Displays a simple blank board"
        (is (= " 0 | 1 | 2 \n===+===+===\n 3 | 4 | 5 \n===+===+===\n 6 | 7 | 8 \n" (render-board [0 1 2 3 4 5 6 7 8]))))))

  (testing "render-board-spaces"
    (deftest render-board-spaces-with-no-arguments-test
      (testing "render-board-spaces with no arguments returns spaces that haven't been marked"
        (is (= (render-board-spaces) [0 1 2 3 4 5 6 7 8]))))

  (deftest render-board-spaces-with-a-move-on-space-0-test
    (testing "Returns spaces where only the first space has been marked"
      (is (= (render-board-spaces {0 "x"}) ["x" 1 2 3 4 5 6 7 8]))))

  (deftest render-board-spaces-with-a-move-on-multiple-spaces-test
    (testing "Returns spaces where the appropriate space is marked"
      (is (= (render-board-spaces {3 "x", 7 "o", 2 "x"}) [0 1 "x" "x" 4 5 6 "o" 8]))))))

(testing "Player moves"
  (testing "get-player-choice"
    (deftest get-player-choice-no-arguments
      (testing "No arguments returns map with player input and x"
        (is (= (with-in-str "0" (get-player-choice)) {0 "x"}))))

    (deftest get-player-choice-no-arguments-bad-input
      (testing "Doesn't accept non-numeric input"
        (is (= (with-in-str (helper/make-input ["Alphabet" "8"]) (get-player-choice)) {8 "x"}))))

    (deftest get-player-choice-with-arguments-good-input
      (testing "Get player choice with map argument returns that map plus the new choice"
        (is (= (with-in-str "0" (get-player-choice {8 "x"})) {0 "x", 8 "x"}))))

    (deftest get-player-choice-with-arguments-bad-input
      (testing "Doesn't accept non-numeric or too-high number input"
        (is (= (with-in-str (helper/make-input ["Alphabet" "270" "8"]) (get-player-choice {0 "x"})) {0 "x", 8 "x"}))))

    (deftest get-player-choice-with-arguments-repeat-input
      (testing "Doesn't accept a space that's already taken"
        (is (= (with-in-str (helper/make-input ["0" "8"]) (get-player-choice {0 "x"})) {0 "x", 8 "x"})))))

  (testing "parse-move-input"
    (deftest parse-move-input-test-with-number-input
      (testing "Returns the int value of a numerical input"
        (is (= (parse-move-input "2") 2))))

    (deftest parse-move-input-test-with-non-number-input
      (testing "Returns nil if the input value is not a number"
        (is (= (parse-move-input "Hello!") nil))))

    (deftest parse-move-input-test-with-too-high-number-input
      (testing "Returns nil if the input value is greater than board size"
        (is (= (parse-move-input "100") nil))))))

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

