(ns clojure-tictactoe.cli.output.board-printer
  (:require [clojure-tictactoe.game.board :as board]))

(defn space-renderer [space]
  (if (int? space)
    (+ space 1)
    space))

(defn render-board [spaces]
  (str  " " (space-renderer (spaces 0)) " | "
       (space-renderer (spaces 1)) " | "
       (space-renderer (spaces 2))
       " \n===+===+===\n "
       (space-renderer (spaces 3)) " | "
       (space-renderer (spaces 4)) " | "
       (space-renderer (spaces 5))
       " \n===+===+===\n "
       (space-renderer (spaces 6)) " | "
       (space-renderer (spaces 7)) " | "
       (space-renderer (spaces 8)) " \n"))


(defn print-board [spaces]
  (println (render-board spaces)))
