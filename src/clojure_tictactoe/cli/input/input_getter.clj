(ns clojure-tictactoe.cli.input.input-getter
  (:require [clojure.string :as string]
            [clojure-tictactoe.cli.output.instructions-printer :as instructions-printer]
            [clojure-tictactoe.cli.output.messages :as m]
            [clojure-tictactoe.cli.input.input-checker :as input-checker]))

(defn get-user-input []
  (let [user-input (string/trim (read-line))]
    (if (= user-input m/quit)
      (. System exit 0)
      user-input)))

(defn continue-to-game []
  (println m/press-enter)
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
      (do (m/print-new-line)
        (println m/sorry-token-char)
          (recur player)))))

(defn acquire-both-markers [players]
  (let [markers (map get-player-marker players)]
    (if (input-checker/distinct-markers? markers)
      (into [] markers)
      (do (m/print-new-line)
          (println m/sorry-token-match)
          (recur players)))))

(defn get-player-choice [board]
  (println m/which-space)
  (let [choice (format-input (get-user-input))]
    (if (input-checker/valid-numeral? choice board)
      (format-valid-numeral choice)
      (do (m/print-new-line)
          (println m/sorry-space-invalid)
          (recur board)))))

(defn get-player-move [board marker]
  (let [move (get-player-choice board)]
    (if (input-checker/valid-move? move board)
      move
      (do (m/print-new-line)
          (println m/sorry-space-taken)
          (recur board marker)))))

(defn get-option-choice [options intro]
  (m/print-new-line)
  (println intro)
  (instructions-printer/print-stringified-options options)
  (loop []
    (println m/input-choice-request)
    (let [choice (format-input (get-user-input))]
      (if (input-checker/valid-numeral? choice options)
        (options (format-valid-numeral choice))
        (do (m/print-new-line)
            (println m/sorry-choice-invalid)
            (recur))))))


