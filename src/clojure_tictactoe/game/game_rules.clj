(ns clojure-tictactoe.game.game-rules
  (:require [clojure-tictactoe.game.board :as board]))


(defn assess-win [board]
  (cond
    (= (board 0) (board 1) (board 2)) (board 0)
    (= (board 3) (board 4) (board 5)) (board 3)
    (= (board 6) (board 7) (board 8)) (board 6)
    (= (board 0) (board 3) (board 6)) (board 0)
    (= (board 1) (board 4) (board 7)) (board 1)
    (= (board 2) (board 5) (board 8)) (board 2)
    (= (board 0) (board 4) (board 8)) (board 0)
    (= (board 2) (board 4) (board 6)) (board 2)))

(defn game-over? [board]
  (not (not (or
              (board/board-full? board)
              (assess-win board)))))
