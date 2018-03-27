(ns clojure-tictactoe.game.players)

(def player-atom (atom "x"))

(defn switch-player [current-player]
  (if (= current-player "x")
    "o"
    "x"))

(defn player-swap []
  (swap! player-atom switch-player))
