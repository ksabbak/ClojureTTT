(ns clojure-tictactoe.cli.output.board-printer
  (:require [clojure-tictactoe.game.board :as board]))

(defn space-content-renderer [space]
  (if (int? space)
    (+ space 1)
    space))

(defn render-board [spaces]
  (str  " " (space-content-renderer (spaces 0)) " | "
       (space-content-renderer (spaces 1)) " | "
       (space-content-renderer (spaces 2))
       " \n===+===+===\n "
       (space-content-renderer (spaces 3)) " | "
       (space-content-renderer (spaces 4)) " | "
       (space-content-renderer (spaces 5))
       " \n===+===+===\n "
       (space-content-renderer (spaces 6)) " | "
       (space-content-renderer (spaces 7)) " | "
       (space-content-renderer (spaces 8)) " \n"))


(defn print-board [spaces]
  (println (render-board spaces)))
