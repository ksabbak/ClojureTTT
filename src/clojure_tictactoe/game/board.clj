(ns clojure-tictactoe.game.board)

(defn open?
  [space board]
  (int? (board space)))


(defn mark-space
  [space marker board]
  (when (open? space board)
    (assoc board space marker)))
