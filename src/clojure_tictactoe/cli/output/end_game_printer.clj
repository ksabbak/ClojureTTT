(ns clojure-tictactoe.cli.output.end-game-printer)

(def game-tie-message "Game over! It's a tie.")

(defn game-won-message
  [winner]
  (str "Congratulations to player " winner "! You won!"))

(defn print-game-over
  [results]
  (if-let [winner results] 
    (println (game-won-message winner))
    (println game-tie-message)))
