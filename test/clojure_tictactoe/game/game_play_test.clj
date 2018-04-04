(ns clojure-tictactoe.game.game-play-test
  (:require [clojure.test :refer :all]
            [clojure.string :as string]
            [clojure-tictactoe.helpers :as helper]
            [clojure-tictactoe.game.game-play :refer :all]))

(deftest player-move-test-good-argument
  (testing "Accepts good player move"
    (is (= (player-move (fn [] 8) [0 1 2 3 4 5 6 7 8]) [0 1 2 3 4 5 6 7 "x"]))))



