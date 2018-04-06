(ns clojure-tictactoe.game.players-test
  (:require [clojure.test :refer :all]
            [clojure.string :as string]
            [clojure-tictactoe.helpers :as helper]
            [clojure-tictactoe.game.players :refer :all]
            [clojure-tictactoe.game.computer-opponent :as ai]))


(testing "Players"
  (testing "switch-players"
    (deftest switch-player-test-when-x
      (testing "Returns o when x is passed in"
        (is (= "o" (switch-player "x")))))

    (deftest switch-player-test-when-o
      (testing "Returns x when o is passed in"
        (is (= "x" (switch-player "o"))))))

  (testing "human-player-move"
    (deftest human-player-move-test
      (testing "Doesn't accept taken input, does accept open space"
        (is (= (with-in-str (helper/make-input ["0" "8"]) (human-player-move ["x" 1 2 3 4 5 6 7 8])) 8 )))))

  (testing "player move logic"
    (deftest choose-player-function-test-x
      (testing "Returns the human-move function for player x"
        (is (= human-player-move (choose-player-function "x")))))

    (deftest choose-player-function-test-x
      (testing "Returns the human-move function for player o"
        (is (= ai/make-move (choose-player-function "o")))))))

