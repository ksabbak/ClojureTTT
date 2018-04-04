(ns clojure-tictactoe.game.game-rules
  (:require [clojure-tictactoe.game.board :as board]))


(defn assess-win
  [board]
  (let [top-row (= (board 0) (board 1) (board 2))
        middle-row (= (board 3) (board 4) (board 5))
        bottom-row (= (board 6) (board 7) (board 8))
        left-column (= (board 0) (board 3) (board 6))
        middle-column (= (board 1) (board 4) (board 7))
        right-column (= (board 2) (board 5) (board 8))
        top-left-to-bottom-right-diagonal (= (board 0) (board 4) (board 8))
        bottom-left-to-top-right-diagonal (= (board 2) (board 4) (board 6))
        top-left-corner(board 0)
        top-middle-space (board 1)
        top-right-corner (board 2)
        left-middle-space (board 3)
        bottom-left-corner (board 6)]
  (cond
    top-row top-left-corner
    middle-row left-middle-space
    bottom-row bottom-left-corner
    left-column top-left-corner
    middle-column top-middle-space
    right-column top-right-corner
    top-left-to-bottom-right-diagonal top-left-corner
    bottom-left-to-top-right-diagonal top-right-corner)))

(defn game-over?
  [board]
  (not (not (or
              (board/board-full? board)
              (assess-win board)))))
