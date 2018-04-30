(ns clojure-tictactoe.cli.output.board-printer
  (:require [clojure-tictactoe.game.board :as board]))

(defn empty-space-incrementor [space]
  (if (int? space)
    (+ space 1)
    space))

(defn space-content-renderer [space]
  (let [space (str (empty-space-incrementor space))]
    (if (< 1 (count space))
      space
      (str " " space))))

(defn separate-spaces [row]
  (interpose " | " row))

(defn row-separator [board]
  (let [length (count board)
        side-length (board/side-length board)
        separator (->> "="
                       (repeat length)
                       (partition side-length)
                       (interpose \+)
                       (flatten)
                       (apply str))]
    (str " \n"separator "\n ")))


(defn content-modifier [board]
  (if (>= 9 (count board))
    empty-space-incrementor
    space-content-renderer))

(defn render-board [board]
  (let [content-modifier (content-modifier board)
        board (map content-modifier board)]
    (->> board
         (partition (board/side-length board))
         (map separate-spaces)
         (interpose (row-separator board))
         (flatten)
         (apply str)
         (str " "))))

(defn print-board [spaces]
  (println (render-board spaces) "\n"))
