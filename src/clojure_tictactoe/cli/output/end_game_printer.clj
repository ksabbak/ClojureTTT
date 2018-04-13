(ns clojure-tictactoe.cli.output.end-game-printer)

(def game-tie-message "Game over! It's a tie.")

(defn game-won-message [winner]
  (str "Congratulations to player " winner "! You won!"))

(defn end-game-printer [message]
  (println message))
