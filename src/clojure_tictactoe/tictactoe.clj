(ns clojure-tictactoe.tictactoe
  (:gen-class))


(defn welcome []
  "Welcome to the game of TicTacToe! \n")


(defn instructions []
 "Instructions: 2 players take turns placing markers, try to get three of your markers in a row to win! \n

Examples: 

  Vertical      Horizontal      Diagonal

   | x |     |    |   |     |  x |   |     
===+===+===  | ===+===+===  | ===+===+===  
   | x |     |  x | x | x   |    | x |     
===+===+===  | ===+===+===  | ===+===+===  
   | x |     |    |   |     |    |   | x   ")
