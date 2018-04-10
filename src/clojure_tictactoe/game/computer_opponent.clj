(ns clojure-tictactoe.game.computer-opponent
  (:require [clojure-tictactoe.game.board :as board]))

(defn make-move [board]
  (rand-nth (board/open-spaces board)))
