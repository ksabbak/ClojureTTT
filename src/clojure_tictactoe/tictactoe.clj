(ns clojure-tictactoe.tictactoe
  (:gen-class))


(def welcome-message 
  "Welcome to the game of TicTacToe! \n")

(def instructions-message 
 "Instructions: 2 players take turns placing markers, try to get three of your markers in a row to win! \n

Examples: 

  Vertical      Horizontal      Diagonal

   | x |     |    |   |     |  x |   |     
===+===+===  | ===+===+===  | ===+===+===  
   | x |     |  x | x | x   |    | x |     
===+===+===  | ===+===+===  | ===+===+===  
   | x |     |    |   |     |    |   | x  \n\n ")


(defn print-game-intro []
  (println welcome-message)
  (println instructions-message))

(defn board [spaces]
  " 0 | 1 | 2 \n===+===+===\n 3 | 4 | 5 \n===+===+===\n 6 | 7 | 8 \n")

(defn continue-to-game []
  (println "Press enter key to continue")
  (read-line))

(defn make-board-filler 
  ([]
   (into [] (take 9 (range)))))
