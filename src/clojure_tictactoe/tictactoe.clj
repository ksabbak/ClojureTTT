(ns clojure-tictactoe.tictactoe
  (:gen-class)
  (:require [clojure.string :as string]
            [clojure-tictactoe.cli.output.board-printer :as board-printer]))

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
    (println (board-printer/render-board (board-printer/render-board-spaces player-choice)))
    (swap! player-atom switch-player)
    (game-loop player-choice)))
  ([choices]
   (let [player-choice (get-player-choice choices)]
     (println (board-printer/render-board (board-printer/render-board-spaces player-choice)))
     (swap! player-atom switch-player)
     (recur player-choice))))

