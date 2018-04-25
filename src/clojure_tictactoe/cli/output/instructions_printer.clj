(ns clojure-tictactoe.cli.output.instructions-printer)

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

(defn print-marker-instructions [player]
  (println (str "What marker would you like for " player "?")))

(defn print-stringified-options [options]
  (->> options
       (map-indexed #(str (+ %1 1) ". " %2))
       (map println)
       (dorun)))

(def game-choice-message
  "What kind of game would you like to play?\n")

(def board-size-message
  "What size board would you like to play on?\n")

(def input-choice-request
  "\nPlease enter the number that corresponds to your selection:")

