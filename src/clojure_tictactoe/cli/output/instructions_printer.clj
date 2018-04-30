(ns clojure-tictactoe.cli.output.instructions-printer
  (:require [clojure-tictactoe.cli.output.messages :as m]))

(defn print-game-intro []
  (println m/welcome-message)
  (m/print-new-line)
  (m/print-new-line)
  (println m/instructions-message)
  (m/print-new-line)
  (m/print-new-line))

(defn print-marker-instructions [player]
  (m/print-new-line)
  (println (str m/instructions-what-marker player "?")))

(defn print-stringified-options [options]
  (m/print-new-line)
  (->> options
       (map-indexed #(str (+ %1 1) ". " %2))
       (map println)
       (dorun))
  (m/print-new-line))

