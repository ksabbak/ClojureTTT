(ns clojure-tictactoe.cli.output.board-printer-test
  (:require [clojure.test :refer :all]
            [clojure.string :as string]
            [clojure-tictactoe.helpers :as helper]
            [clojure-tictactoe.cli.output.board-printer :refer :all]))

(testing "Rendering board"
  (testing "render-board"
    (deftest render-board-test
      (testing "Displays a simple blank board"
        (is (= " 1 | 2 | 3 \n===+===+===\n 4 | 5 | 6 \n===+===+===\n 7 | 8 | 9" (render-board [0 1 2 3 4 5 6 7 8])))
        (is (= "  1 |  2 |  3 |  4 \n====+====+====+====\n  5 |  6 |  7 |  8 \n====+====+====+====\n  9 | 10 | 11 | 12 \n====+====+====+====\n 13 | 14 | 15 | 16" (render-board [0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15])))))))

(testing "Printing board"
  (testing "print-board"

    (deftest print-board-test-empty
      (testing "Prints a simple blank board"
        (is (= " 1 | 2 | 3 \n===+===+===\n 4 | 5 | 6 \n===+===+===\n 7 | 8 | 9 \n\n" (with-out-str (print-board [0 1 2 3 4 5 6 7 8]))))
        (is (= "  1 |  2 |  3 |  4 \n====+====+====+====\n  5 |  6 |  7 |  8 \n====+====+====+====\n  9 | 10 | 11 | 12 \n====+====+====+====\n 13 | 14 | 15 | 16 \n\n" (with-out-str (print-board [0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15]))))))

    (deftest print-board-test-markers
      (testing "Prints a board with blank spaces and markers"
        (is (= " 1 | x | 3 \n===+===+===\n 4 | 5 | 6 \n===+===+===\n 7 | 8 | o \n\n" (with-out-str (print-board [0 "x" 2 3 4 5 6 7 "o"]))))
        (is (= "  1 |  2 |  3 |  x \n====+====+====+====\n  o |  o |  7 |  8 \n====+====+====+====\n  9 | 10 | 11 | 12 \n====+====+====+====\n 13 | 14 |  x | 16 \n\n" (with-out-str (print-board [0 1 2 "x" "o" "o" 6 7 8 9 10 11 12 13 "x" 15]))))))))

(testing "Board building helpers"
  (testing "empty-space-incrementor"
    (deftest empty-space-incrementor-test-empty-space
      (testing "Increments the number of an empty space"
        (is (= 1 (empty-space-incrementor 0)))))

    (deftest empty-space-incrementor-test-taken-space
      (testing "Returns an unchanged taken space"
        (is (= "x" (empty-space-incrementor "x")))
        (is (= "0" (empty-space-incrementor "0"))))))

  (testing "space-content-renderer"
    (deftest space-content-renderer-test-marker
      (testing "returns the marker with a space in front"
        (is (= " x" (space-content-renderer "x")))
        (is (= " ~" (space-content-renderer "~")))))

    (deftest space-content-renderer-test-one-digit
      (testing "returns a string of the next digit with a space in front"
        (is (= " 1" (space-content-renderer 0)))
        (is (= " 9" (space-content-renderer 8)))))

    (deftest space-content-renderer-test-double-digit
      (testing "returns a string of the incremented digit"
        (is (= "10" (space-content-renderer 9)))
        (is (= "16" (space-content-renderer 15))))))

  (testing "separate-spaces"
    (deftest separate-spaces-test
      (testing "separates the spaces with ' | ', does not append that to the last space"
        (is (= '("x" " | " 4 " | " "o") (separate-spaces ["x" 4 "o"]))))))

  (testing "row-separator"
    (deftest row-separator-test
      (testing "returns the proper separator"
        (is (= " \n===+===+===\n " (row-separator [0 1 2 3 4 5 6 7 8])))
        (is (= " \n====+====+====+====\n " (row-separator [0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15]))))))

  (testing "content-modifier"
    (deftest content-modifier-standard-board
      (testing "returns empty-space-incrementor for a 3x3 board"
        (is (= empty-space-incrementor (content-modifier [0 1 2 3 4 5 6 7 8])))))

    (deftest content-modifier-large-board
      (testing "returns empty-space-incrementor for a 4x4 board"
        (is (= space-content-renderer (content-modifier [0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15])))))))

