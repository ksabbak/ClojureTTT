(ns clojure-tictactoe.tictactoe
  (:gen-class)
  (:require [clojure.string :as string]))

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
   (into [] (map (fn [space] (or (taken-spaces space)
                                 space))
                 (render-board-spaces)))))

(defn parse-move-input
  ([input]
   (let [formatted-input (read-string input)]
     (when (and
             (number? formatted-input)
             (< formatted-input 9))
       formatted-input))))

(defn get-player-choice
  ([]
   (println (str "Which space would you like to mark, Player " @player-atom "?"))
   (if-let [choice (parse-move-input (get-user-input))]
     {choice @player-atom}
     (do (println "Sorry, looks like that's not possible, try again?")
         (recur))))
  ([choices]
   (let [new_choices (conj choices (get-player-choice))]
     (if (= (count choices) (count new_choices))
       (do (println "Sorry, that's already been taken")
           (recur choices))
       new_choices))))

(defn is-over
  [taken-spaces]
  (if (= 1 (count (set (vals taken-spaces))))
    true
    false
    )
  )

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

