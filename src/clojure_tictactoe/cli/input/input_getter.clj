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

(defn parse-move-input [input choice-limit]
  (let [formatted-input (read-string input)
        valid-numeral (and
                        (number? formatted-input)
                        (< formatted-input choice-limit))]
    (when valid-numeral
      formatted-input)))

(defn get-player-marker [player]
  (instructions-printer/print-marker-instructions player)
  (get-user-input))

(defn get-player-choice [board-length]
  (println (str "Which space would you like to mark?"))
  (if-let [choice (parse-move-input (get-user-input) board-length)]
    choice
    (do (println "Sorry, looks like that's not possible, try again?")
        (recur board-length))))

(defn get-player-move [board]
  (let [move (get-player-choice (count board))]
    (if (board/space-is-open? move board)
      move
      (do (println "Sorry, that looks taken, try again")
          (recur board)))))

(defn get-game-type [options]
  (println instructions-printer/game-choice-message)
  (instructions-printer/print-stringified-options options)
  (loop []
    (println instructions-printer/game-choice-request)
    (let [choice-limit (+ (count options) 1)]
      (if-let [choice (parse-move-input (get-user-input) choice-limit)]
        (options (- choice 1))
        (recur)))))
