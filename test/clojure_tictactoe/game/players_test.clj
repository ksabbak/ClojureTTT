(ns clojure-tictactoe.game.players-test
  (:require [clojure.test :refer :all]
            [clojure.string :as string]
            [clojure-tictactoe.helpers :as helper]
            [clojure-tictactoe.game.players :refer :all]))


(testing "Players"
  (testing "switch-players"
    (deftest switch-player-test-when-x
      (testing "Returns o when x is passed in"
        (is (= "o" (switch-player "x")))))

    (deftest switch-player-test-when-o
      (testing "Returns x when o is passed in"
        (is (= "x" (switch-player "o")))))))

