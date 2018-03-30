(ns clojure-tictactoe.game.board)

(defn space-is-open?
  [space board]
  (int? (board space)))

(defn mark-space
  [space marker board]
  (when (space-is-open? space board)
    (assoc board space marker)))

(defn render-empty-board []
  (into [] (take 9 (range))))

(defn board-full?
  [board]
  (not (some #(space-is-open? %  board) (take (count board) (range)))))
