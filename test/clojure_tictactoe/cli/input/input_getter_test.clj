(ns clojure-tictactoe.cli.input.input-getter-test
  (:require [clojure.test :refer :all]
            [clojure.string :as string]
            [clojure-tictactoe.helpers :as helper]
            [clojure-tictactoe.cli.input.input-getter :refer :all]
            [clojure-tictactoe.cli.output.instructions-printer :as instructions-printer]))
(testing "General"

  (testing "format-input"

    (deftest format-input-read-string-cannot-handle
      (testing "format-input returns an empty string when given an empty string"
        (is (= "" (format-input ""))))
      (testing "format-input returns the same string if it contains an octothorp"
        (is (= "omg #hashtag" (format-input "omg #hashtag")))))

    (deftest format-input-integer
      (testing "returns an int when given an int string"
        (is (true? (int? (format-input "7"))))))

    (deftest format-input-string-other
      (testing "returns the symbol of any non-empty, non numeric string"
        (is (true? (symbol? (format-input "once this was a string"))))))))



(testing "Game start"

  (testing "continue-to-game"

    (deftest continue-to-game-message-test
      (testing "The message displays properly"
        (is (string/includes? (with-out-str (with-in-str "\n" (continue-to-game))) "continue"))))

    (deftest continue-to-game-input-test
      (testing "The function returns nil once newline has been entered"
        (is (nil? (with-in-str "\n" (continue-to-game)))))))

  (testing "get-game-type"

    (deftest get-game-type-test-display
      (testing "Prints proper message"
        (is (string/includes? (with-out-str (with-in-str "1" (get-game-type ["Pie" "Cake" "Ice cream"]))) instructions-printer/game-choice-message))
        (is (string/includes? (with-out-str (with-in-str "1" (get-game-type ["Pie" "Cake" "Ice cream"]))) "1. Pie\n2. Cake\n3. Ice cream"))))

    (deftest get-game-type-test-input
      (testing "Doesn't accept input that isn't 1 or 2"
        (is (= (with-in-str (helper/make-input ["Alpha chars" "#" "3" "" "2"]) (get-game-type ["Cake" "Pie"])) "Pie"))))))


(testing "Player moves"

  (testing "get-player-choice"

    (deftest get-player-choice-test
      (testing "Good move input returns that input as an integer, but one less"
        (is (= (with-in-str "1" (get-player-choice ["x" 1 2 3 4 5 6 7 8])) 0))))

    (deftest get-player-choice-test-bad-arguments
      (testing "Doesn't accept non-numeric input"
        (is (= (with-in-str (helper/make-input ["Alphabet" "" "8"]) (get-player-choice ["x" 1 2 3 4 5 6 7 8])) 7)))
      (testing "Prints the right error message"
        (is (string/includes?
              (with-out-str (with-in-str (helper/make-input ["string" "9"]) (get-player-choice ["x" 1 2 3 4 5 6 7 8])))
              "Sorry, that's not a valid space, try again\n")))))

  (testing "get-player-move"
    (deftest get-player-move-test
      (testing "Doesn't accept taken input, does accept open space"
        (is (= (with-in-str (helper/make-input ["1" "9"]) (get-player-move ["x" 1 2 3 4 5 6 7 8])) 8 ))))))

(testing "Player Markers"

  (testing "get-player-marker"

    (deftest get-player-marker-test
      (testing "Only accepts string that is one char"
        (is (= "#" (with-in-str (helper/make-input ["too long" " " "#"]) (get-player-marker "Player 1"))))))
    (deftest get-player-marker-test-bad-marker-message
      (testing "Displays right message when too long string is given"
        (is (string/includes?
              (with-out-str (with-in-str (helper/make-input ["too long" " " "x"]) (get-player-marker "Player 1")))
              "Sorry, your token can only be one character long, try again.")))))

  (testing "acquire-both-markers"

    (deftest acquire-both-markers-test
      (testing "Only accepts distinct markers"
        (is (= '("x" "o") (with-in-str (helper/make-input ["x" "x" "x" "o"]) (acquire-both-markers '("Player 1" "Player 2")))))))

    (deftest acquire-both-markers-test-matching-message
      (testing "Displays right message when markers match"
        (is (string/includes?
              (with-out-str (with-in-str (helper/make-input ["x" "x" "x" "o"]) (acquire-both-markers '("Player 1" "Player 2"))))
              "Sorry, the tokens can't match. Try again."))))))
