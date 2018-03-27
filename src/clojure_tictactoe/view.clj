(ns clojure-tictactoe.view)

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

(defn render-board [spaces]
 (str  " " (spaces 0) " | " (spaces 1) " | " (spaces 2) " \n===+===+===\n " (spaces 3) " | " (spaces 4) " | " (spaces 5) " \n===+===+===\n " (spaces 6) " | " (spaces 7) " | " (spaces 8) " \n"))

(defn render-board-spaces
  ([]
   (into [] (take 9 (range))))
  ([taken-spaces]
   (into [] (map (fn [space] (or (taken-spaces space)
                                 space))
                 (render-board-spaces)))))
