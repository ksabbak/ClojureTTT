(ns clojure-tictactoe.tictactoe
  (:gen-class)
  (:require [clojure.string :as string]))


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

(defn get-user-input []
  (let [user-input (string/trim (read-line))]
    (if (= user-input "quit")
      (. System exit 0)
      user-input)))

(def player-atom (atom "x"))

(defn switch-player [current-player]
  (if (= current-player "x")
    "o"
    "x"))

(defn continue-to-game []
  (println "Press enter key to continue")
  (get-user-input)
  nil)

(defn render-board-spaces
  ([]
   (into [] (take 9 (range))))
  ([taken-spaces]
   (into [] (map (fn [space] (or (taken-spaces space) space)) (render-board-spaces)))))

(defn parse-move-input
  ([input]
    (let [formated-input (read-string input)]
      (when (and (number? formated-input) (< formated-input 9))  formated-input))))

(defn get-player-choice
  ([]
   (println (str "Which space would you like to mark, Player " @player-atom "?"))
   (let [choice (parse-move-input (get-user-input))]
   (if choice
     {choice @player-atom}
    (do (println "Sorry, looks like that's not possible, try again?")
      (recur)))))
  ([choices]
   (let [new_choices (conj choices (get-player-choice))]
     (if (= (count choices) (count new_choices))
       (do (println "Sorry, that's already been taken")
           (recur choices))
       new_choices))))


(defn game-loop
  ([]
  (let [player-choice (get-player-choice)]
    (println (render-board (render-board-spaces player-choice)))
    (swap! player-atom switch-player)
    (game-loop player-choice)))
  ([choices]
   (let [player-choice (get-player-choice choices)]
     (println (render-board (render-board-spaces player-choice)))
     (swap! player-atom switch-player)
     (recur player-choice))))

