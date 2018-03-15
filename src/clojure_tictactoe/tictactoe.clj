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
   | x |     |    |   |     |    |   | x   ")


(defn intro-game []
  (println welcome-message)
  (println instructions-message))
