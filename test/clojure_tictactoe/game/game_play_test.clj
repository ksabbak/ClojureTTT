(ns clojure-tictactoe.game.game-play-test
  (:require [clojure.test :refer :all]
            [clojure.string :as string]
            [clojure-tictactoe.helpers :as helper]
            [clojure-tictactoe.game.game-play :refer :all]))
(def empty-board
  [0 1 2 3 4 5 6 7 8])

(def board-x-at-8
  [0 1 2 3 4 5 6 7 "x"])

(defn move-function-mock
  []
   (first (take 1 [8 0])))


(deftest player-move-test-good-argument
  (testing "Accepts good player move"
    (is (= (player-move (fn [] 8) empty-board) board-x-at-8))))


; (deftest player-move-test-good-argument
;   (testing "Doesn't accept taken space player move"
;     (is (string/includes? (with-out-str (player-move move-function-mock board-x-at-8) "that looks taken")))))
    ; (is (= (with-out-str (player-move (fn [] (take 1 [8 0])) board-x-at-8) ["x" 1 2 3 4 5 6 7 "x"])))))
