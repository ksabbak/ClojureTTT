(ns clojure-tictactoe.game.computer-opponent-test
  (:require [clojure.test :refer :all]
            [clojure.string :as string]
            [clojure-tictactoe.helpers :as helper]
            [clojure-tictactoe.game.computer-opponent :refer :all]))

(testing "Computer opponent"

  (testing "get-move"

    (deftest get-move-test-open-board
      (testing "get-move will return a valid board space on an open board"
        (is (contains? #{0 1 2 3 4 5 6 7 8} (get-move [0 1 2 3 4 5 6 7 8] "o")))))

    (deftest get-move-test-returns-empty-space-only-one
      (testing "get-move will return the only empty space on a board that has one open space"
        (is (= 8 (get-move ["x" "o" "x" "o" "x" "o" "o" "x" 8] "o")))))

    (deftest get-move-test-many-options
      (testing "get-move will return one of any of the open spaces on a board with some taken spaces"
        (is (contains? #{0 1} (get-move [0 1 "x" "o" "x" "o" "o" "x" "o"] "o")))))))

(testing "Help with markers"

  (testing "assign-opponent-marker"

    (deftest assign-opponent-marker-test-not-same
      (testing "Assign opponent marker doesn't assign the same marker as the ai"
        (is (not (= "x" (assign-opponent-marker "x"))))
        (is (not (= "X" (assign-opponent-marker "X"))))
        (is (not (= "1" (assign-opponent-marker "1"))))
        (is (not (= ">" (assign-opponent-marker ">"))))))

    (deftest assign-opponent-marker-test-plus-one
          (testing "Assign opponent marker is always one char ahead of the ai marker (to avoid collision)"
        (is (= "y" (assign-opponent-marker "x")))
        (is (= "Y" (assign-opponent-marker "X")))
        (is (= "2" (assign-opponent-marker "1")))
        (is (= "?" (assign-opponent-marker ">"))))))

  (testing "deduce-opponent-marker"

    (deftest deduce-opponent-marker-test-with-moves
      (testing "Returns a list with only the opponent's marker"
        (is (= (count (set (deduce-opponent-marker [0 1 "x" "o" "x" "o" "o" "x" "o"] "o"))) 1))
        (is (true? (some #(= "x" %) (deduce-opponent-marker [0 1 "x" "o" "x" "o" "o" "x" "o"] "o"))))
        (is (= (count (set (deduce-opponent-marker [0 "x" 2 3 4 5 6 7 8] "o"))) 1))
        (is (true? (some #(= "x" %) (deduce-opponent-marker [0 "x" 2 3 4 5 6 7 8] "o")))))
      (testing "Does not contain the ai marker in the returned list"
        (is (nil? (some #(= "o" %) (deduce-opponent-marker [0 1 "x" "o" "x" "o" "o" "x" "o"] "o"))))))

    (deftest deduce-opponent-marker-test-no-opponent
      (testing "Returns empty list if the opponent hasn't made a move yet"
        (is (= (deduce-opponent-marker [0 1 2 3 4 5 6 7 8] "o") '()))
        (is (= (deduce-opponent-marker [0 "o" 2 3 4 5 6 7 8] "o") '())))))

    (testing "get-opponent-marker"

      (deftest get-opponent-marker-test-no-play
        (testing "Assigns appropriate marker to opponent when no moves have been made"
          (is (= (get-opponent-marker [0 1 2 3 4 5 6 7 8] "o") "p"))))

      (deftest get-opponent-marker-test-one-move
        (testing "Assigns appropriate marker to opponent when one move has been made"
          (is (= (get-opponent-marker [0 "x" 2 3 4 5 6 7 8] "o") "x"))
          (is (= (get-opponent-marker [0 "o" 2 3 4 5 6 7 8] "o") "p"))))

      (deftest get-opponent-marker-test-multiple
        (testing "Assigns appropriate marker when many moves have been made"
          (is (= (get-opponent-marker [0 1 "x" "o" "x" "o" "o" "x" "o"] "o") "x"))))))

(testing "Help with turns"
  (testing "deduce-turn"
    (deftest deduce-turn-test-empty-board
      (testing "Returns 0 when there're no moves on the board"
        (is (= (deduce-turn [0 1 2 3 4 5 6 7 8]) 0))))
    (deftest deduce-turn-test-turns-played
      (testing "Returns the right turn when moves have been made"
        (is (= (deduce-turn [0 "x" 2 3 4 5 6 7 8]) 1))
        (is (= (deduce-turn [0 1 "x" "o" "x" "o" "o" "x" "o"]) 7))))
    ))
