(ns clojure-tictactoe.cli.output.board-printer-test
  (:require [clojure.test :refer :all]
            [clojure.string :as string]
            [clojure-tictactoe.helpers :as helper]
            [clojure-tictactoe.cli.output.board-printer :refer :all]))

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
