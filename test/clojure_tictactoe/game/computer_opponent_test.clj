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
        (is (contains? #{0 1} (get-move [0 1 "x" "o" "x" "o" "o" "x" "o"] "o")))))

    (deftest get-move-test-takes-immediate-win
      (testing "will take the winning move"
        (is (= 2 (get-move ["x" "x" 2 3 "o" 5 6 "o" 8] "x")))
        (is (= 8 (get-move ["x" 1 2 "o" "x" 5 6 "o" 8] "x")))
        (is (= 6 (get-move ["x" 1 "o" "x" 4 5 6 "o" 8] "x")))
        (is (= 3 (get-move ["x" "x" "x" 3 "o" "o" 6 7 "o" 9 10 11 12 13 14 15] "x")))
        (is (= 12 (get-move ["x" 1 "o" 3 "x" "o" 6 "o" "x" 9 10 11 12 13 14 15] "x")))
        (is (= 10 (get-move ["x" 1 "o" 3 "o" "x" 6 "o" 8 9 10 11 12 13 14 "x"] "x")))
        ))

    (deftest get-move-test-stops-immediate-loss
      (testing "will stop the opponent from winning"
        (is (= 2 (get-move ["x" "x" 2 3 "o" 5 6 "o" 8] "o")))
        (is (= 8 (get-move ["x" 1 2 "o" "x" 5 6 "o" 8] "o")))
        (is (= 6 (get-move ["x" 1 "o" "x" 4 5 6 "o" 8] "o")))
        (is (= 3 (get-move ["x" "x" "x" 3 "o" "o" 6 7 "o" 9 10 11 12 13 14 15] "o")))
        (is (= 12 (get-move ["x" 1 "o" 3 "x" "o" 6 "o" "x" 9 10 11 12 13 14 15] "o")))
        (is (= 10 (get-move ["x" 1 "o" 3 "o" "x" 6 "o" 8 9 10 11 12 13 14 "x"] "o")))
        (is (= 3 (get-move [0 1 2 3 "o" "x" "x" "x" "o" "x" "o" 11 "x" 13 "o" 15] "o")))
        ))

    (deftest get-move-test-forks
      (testing "will stop forks"
        (is (some #(= (get-move ["x" 1 2 3 "o" 5 6 7 "x"] "o") %) [1 3 5 7]))
        (is (not (some #(= (get-move ["x" 1 2 3 "o" 5 6 "x" 8] "o") %) [1 2])))
        (is (some #(= (get-move ["x" 1 2 3 "x" 5 6 7 "o"] "o") %) [2 6]))
        (is (some #(= (let [move (get-move [0 1 2 "o" 4 "x" "o" "o" 8 9 "x" "x" "o" "x" 14 15] "o")] move) %) [1 8 9]))))))

(testing "Help with markers"

  (testing "assign-opponent-marker"

    (deftest assign-opponent-marker-test-not-same
      (testing "Assign opponent marker doesn't assign the same marker as the ai"
        (is (not (= "x" (assign-opponent-marker "x"))))
        (is (not (= "X" (assign-opponent-marker "X"))))
        (is (not (= "1" (assign-opponent-marker "1"))))
        (is (not (= ">" (assign-opponent-marker ">"))))))

    (deftest assign-opponent-marker-test-plus-one
          (testing "Assign opponent marker is always x unless the computer is x, in which case it's o"
        (is (= "o" (assign-opponent-marker "x")))
        (is (= "x" (assign-opponent-marker "X")))
        (is (= "x" (assign-opponent-marker "1")))
        (is (= "x" (assign-opponent-marker ">"))))))

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
          (is (= (get-opponent-marker [0 1 2 3 4 5 6 7 8] "o") "x"))))

      (deftest get-opponent-marker-test-one-move
        (testing "Assigns appropriate marker to opponent when one move has been made"
          (is (= (get-opponent-marker [0 "x" 2 3 4 5 6 7 8] "o") "x"))
          (is (= (get-opponent-marker [0 "o" 2 3 4 5 6 7 8] "o") "x"))))

      (deftest get-opponent-marker-test-multiple
        (testing "Assigns appropriate marker when many moves have been made"
          (is (= (get-opponent-marker [0 1 "x" "o" "x" "o" "o" "x" "o"] "o") "x")))))

    (testing "turn-marker"
      (deftest turn-marker-test-self
        (testing "Returns the 'self' marker if the ai is the current player"
          (is (= {:current-marker "-", :next-marker "+"} (swap-markers {:current-marker "+" :next-marker "-"})))))))

(testing "Help with turns"
  (testing "deduce-turn"
    (deftest deduce-turn-test-empty-board
      (testing "Returns 0 when there're no moves on the board"
        (is (= (deduce-turn [0 1 2 3 4 5 6 7 8]) 0))))
    (deftest deduce-turn-test-turns-played
      (testing "Returns the right turn when moves have been made"
        (is (= (deduce-turn [0 "x" 2 3 4 5 6 7 8]) 1))
        (is (= (deduce-turn [0 1 "x" "o" "x" "o" "o" "x" "o"]) 7)))))

  (testing "get-turn-determiner"
    (deftest get-turn-determiner-ai-first
      (testing "returns even? if ai is player one"
        (is (= (get-turn-determiner [0 1 2 3 4 5 6 7 8]) even?))
        (is (= (get-turn-determiner ["x" 1 "x" "o" "x" "o" "o" "x" "o"]) even?))))
    (deftest get-turn-determiner-ai-first
      (testing "returns odd? if ai is player two"
        (is (= (get-turn-determiner ["x" 1 2 3 4 5 6 7 8]) odd?))
        (is (= (get-turn-determiner [0 1 "x" "o" "x" "o" "o" "x" "o"]) odd?))))))

(testing "Minimax calculations"
  (testing "win-points"

    (deftest win-points-test-ai-won
      (testing "returns positive points if AI turn"
        (is (pos? (win-points ["x" "o" 2 "o" "x" 5 6 7 8] even?)))))

    (deftest win-points-test-opponent-won
      (testing "returns negative points if opponent turn"
        (is (neg? (win-points ["x" "o" 2 "o" "x" 5 6 7 8] odd?))))))

  (testing "choose-best-score"

      (deftest choose-best-score-test-self

        (testing "Returns the highest score if the current player is the AI"
          (is (= 10 (choose-best-score [10 0 -10 -10 -10 0] [0 1 2 3 4 5 6 7 8] even?)))
          (is (= 0 (choose-best-score [-10 -10 -10 0] [0 1 2 3 4 5 6 "x" 8] odd?)))
          (is (= -10 (choose-best-score [-10 -10 -10]["o" 1 "x" 3 4 5 6 7 "x"] odd?))))

        (testing "Returns the lowest score if the current player is not the AI"
          (is (= -10 (choose-best-score [10 0 -10 -10 -10 0] [0 1 2 3 4 5 6 7 8] odd?)))
          (is (= 0 (choose-best-score [10 10 10 0] [0 1 2 3 4 5 6 "x" 8] even?)))
          (is (= 10 (choose-best-score [10 10 10] ["o" 1 "x" 3 4 5 6 7 "x"] even?)))))))
