(ns clojure-tictactoe.cli.output.messages)

(defn print-new-line [] (println ""))

(defn clear-screen []
  (print (str (char 27) "[2J"))
  (print (str (char 27) "[;H")))

(def welcome-message
  "Welcome to the game of TicTacToe!")

(def instructions-message
  "Instructions: 2 players take turns placing markers,\ntry to get three of your markers in a row to win! \n

Examples:

  Vertical      Horizontal      Diagonal

   | x |     |    |   |     |  x |   |
===+===+===  | ===+===+===  | ===+===+===
   | x |     |  x | x | x   |    | x |
===+===+===  | ===+===+===  | ===+===+===
   | x |     |    |   |     |    |   | x   \n\n


Type 'quit' at any point to quit the game.\n")

(def instructions-what-marker
  "What marker would you like for ")

(def game-choice-message
  "What kind of game would you like to play?")

(def board-size-message
  "What size board would you like to play on?")

(def input-choice-request
  "Please enter the number that corresponds to your selection:")

(def press-enter
  "Press enter key to continue")

(def quit "quit")

(def which-space "Which space would you like to mark?")

(def sorry-token-char
  "Sorry, your token can only be one character long, try again.")

(def sorry-token-match
  "Sorry, the tokens can't match. Try again.")

(def sorry-space-invalid
  "Sorry, that's not a valid space, try again.")

(def sorry-space-taken
  "Sorry, that looks taken, try again.")

(def sorry-choice-invalid
  "That's not a valid choice, try again.")

(def end-tie
  "Game over! It's a tie")

(def end-congrats
  "Congratulations to player ")

(def end-won
  "! You won!")

(def restart
  "Would you like to play again?")

(def game-options ["Human vs. Human" "Human vs. Computer" "Computer vs. Human"])

(def board-options ["3x3" "4x4"])

(def players ["player 1" "player 2"])

(def yes-no ["Yes!" "No"])

