(ns clojure-tictactoe.cli.input.input-getter
  (:require [clojure.string :as string]
            [clojure-tictactoe.game.board :as board]
            [clojure-tictactoe.cli.output.instructions-printer :as instructions-printer]))

(defn get-user-input []
  (let [user-input (string/trim (read-line))]
    (if (= user-input "quit")
      (. System exit 0)
      user-input)))

(defn continue-to-game []
  (println "Press enter key to continue")
  (get-user-input)
  nil)

(defn parse-move-input [input]
  (let [formatted-input (read-string input)
        valid-numeral (and
                          (number? formatted-input)
                          (< formatted-input 9))]
      (when valid-numeral
        formatted-input)))

(defn get-player-marker [player]
  (instructions-printer/print-marker-instructions player)
  (get-user-input)
  )

(defn get-player-choice []
  (println (str "Which space would you like to mark?"))
  (if-let [choice (parse-move-input (get-user-input))]
    choice
    (do (println "Sorry, looks like that's not possible, try again?")
        (recur))))

(defn get-player-move [board]
  (loop []
    (let [move (get-player-choice)]
      (if (board/space-is-open? move board)
        move
        (do (println "Sorry, that looks taken, try again")
            (recur))))))
