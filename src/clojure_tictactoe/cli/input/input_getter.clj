(ns clojure-tictactoe.cli.input.input-getter
  (:require [clojure.string :as string]
            [clojure-tictactoe.cli.output.instructions-printer :as instructions-printer]
            [clojure-tictactoe.cli.input.input-checker :as input-checker]))

(defn get-user-input []
  (let [user-input (string/trim (read-line))]
    (if (= user-input "quit")
      (. System exit 0)
      user-input)))

(defn continue-to-game []
  (println "Press enter key to continue")
  (get-user-input)
  nil)

(defn format-valid-numeral [number]
  (- number 1))

(defn format-input [input]
  (if-let [unreadable (or
                        (empty? input)
                        (string/includes? input "#"))]
    input
    (read-string input)))

(defn get-player-marker [player]
  (instructions-printer/print-marker-instructions player)
  (let [choice (get-user-input)]
    (if (input-checker/acceptable-marker-option? choice)
      choice
      (do (println "\nSorry, your token can only be one character long, try again.")
          (recur player)))))

(defn acquire-both-markers [players]
  (let [markers (map get-player-marker players)]
    (if (input-checker/distinct-markers? markers)
      (into [] markers)
      (do (println "\nSorry, the tokens can't match. Try again.")
          (recur players)))))

(defn get-player-choice [board]
  (println (str "Which space would you like to mark?"))
  (let [choice (format-input (get-user-input))]
    (if (input-checker/valid-numeral? choice board)
      (format-valid-numeral choice)
      (do (println "\nSorry, that's not a valid space, try again")
          (recur board)))))

(defn get-player-move [board marker]
  (let [move (get-player-choice board)]
    (if (input-checker/valid-move? move board)
      move
      (do (println "\nSorry, that looks taken, try again")
          (recur board marker)))))

(defn get-game-type [options]
  (println instructions-printer/game-choice-message)
  (instructions-printer/print-stringified-options options)
  (loop []
    (println instructions-printer/game-choice-request)
    (let [choice (format-input (get-user-input))]
      (if (input-checker/valid-numeral? choice options)
        (options (format-valid-numeral choice))
        (do (println "\nThat's not a valid game type, try again.")
            (recur))))))
