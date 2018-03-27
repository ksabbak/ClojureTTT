(ns clojure-tictactoe.cli.output.board-printer)

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

(defn render-board-spaces
  ([]
   (into [] (take 9 (range))))
  ([taken-spaces]
   taken-spaces))
