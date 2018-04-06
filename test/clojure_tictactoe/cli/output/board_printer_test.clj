(ns clojure-tictactoe.cli.output.board-printer-test
  (:require [clojure.test :refer :all]
            [clojure.string :as string]
            [clojure-tictactoe.helpers :as helper]
            [clojure-tictactoe.cli.output.board-printer :refer :all]))

(testing "Rendering board"
  (testing "render-board"
    (deftest render-board-test
      (testing "Displays a simple blank board"
        (is (= " 0 | 1 | 2 \n===+===+===\n 3 | 4 | 5 \n===+===+===\n 6 | 7 | 8 \n" (render-board [0 1 2 3 4 5 6 7 8])))))))

(testing "Printing board"
  (testing "print-board"

    (deftest print-board-test-empty
      (testing "Prints a simple blank board"
        (is (= " 0 | 1 | 2 \n===+===+===\n 3 | 4 | 5 \n===+===+===\n 6 | 7 | 8 \n\n" (with-out-str (print-board [0 1 2 3 4 5 6 7 8]))))))

    (deftest print-board-test-markers
      (testing "Prints a board with blank spaces and markers"
        (is (= " 0 | x | 2 \n===+===+===\n 3 | 4 | 5 \n===+===+===\n 6 | 7 | o \n\n" (with-out-str (print-board [0 "x" 2 3 4 5 6 7 "o"]))))))))

