(ns clojure-tictactoe.game.unbeatability-test
  (:require [clojure.test :refer :all]
            [clojure-tictactoe.helpers :as helper]
            [clojure-tictactoe.game.board :as board]
            [clojure-tictactoe.game.game-rules :as game-rules]
            [clojure-tictactoe.game.computer-opponent :as computer-opponent]))

(def starting-board (board/render-empty-board 9))

(def computer-marker "o")
(def human-marker "x")

(defn human-simulator [board]
  (let [spaces (board/open-spaces board)]
    (map #(board/mark-space % human-marker board) spaces)))

(defn computer-simulator [board]
  (let [move (computer-opponent/get-move board computer-marker)]
    (board/mark-space move computer-marker board)))

(defn simulator [board]
  (let [boards (map #(computer-simulator %) (human-simulator board))
        outcomes (map #(= human-marker (game-rules/winner? %)) boards)]
    (if (every? false? outcomes)
      (let [new-boards (filter #(not (game-rules/game-over? %)) boards)]
        (map simulator new-boards)
          true)
      false)))

(deftest computer-is-unbeatable
  (testing "every possible game"
    (is (true? (simulator starting-board)))))

