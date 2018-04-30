(ns clojure-tictactoe.cli.output.end-game-printer
  (:require [clojure-tictactoe.cli.output.messages :as m]))

(defn game-won-message [winner]
  (str m/end-congrats winner m/end-won))

(defn end-game-printer [message]
  (println message))
