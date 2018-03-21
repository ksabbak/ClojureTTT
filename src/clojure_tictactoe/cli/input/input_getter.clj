(ns clojure-tictactoe.cli.input.input-getter
  (:require [clojure.string :as string]
            [clojure-tictactoe.game.players :as players]))

(defn get-user-input []
  (let [user-input (string/trim (read-line))]
    (if (= user-input "quit")
      (. System exit 0)
      user-input)))

(defn continue-to-game []
  (println "Press enter key to continue")
  (get-user-input)
  nil)

(defn parse-move-input
  ([input]
   (let [formatted-input (read-string input)]
     (when (and
             (number? formatted-input)
             (< formatted-input 9))
       formatted-input))))

(defn get-player-choice
  ([]
   (println (str "Which space would you like to mark, Player " @players/player-atom "?"))
   (if-let [choice (parse-move-input (get-user-input))]
     {choice @players/player-atom}
     (do (println "Sorry, looks like that's not possible, try again?")
         (recur))))
  ([choices]
   (let [new_choices (conj choices (get-player-choice))]
     (if (= (count choices) (count new_choices))
       (do (println "Sorry, that's already been taken")
           (recur choices))
       new_choices))))
