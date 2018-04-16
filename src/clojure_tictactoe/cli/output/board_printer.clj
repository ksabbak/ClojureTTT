(ns clojure-tictactoe.cli.output.board-printer
  (:require [clojure-tictactoe.game.board :as board]))

(defn render-board [spaces]
  (str  " " (spaces 0) " | "
       (spaces 1) " | "
       (spaces 2)
       " \n===+===+===\n "
       (spaces 3) " | "
       (spaces 4) " | "
       (spaces 5)
       " \n===+===+===\n "
       (spaces 6) " | "
       (spaces 7) " | "
       (spaces 8) " \n"))


(defn print-board [spaces]
  (println (render-board spaces)))
