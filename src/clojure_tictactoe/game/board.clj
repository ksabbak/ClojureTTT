(ns clojure-tictactoe.game.board)

(defn space-is-open? [space board]
  (int? (board space)))

(defn open-spaces [board]
  (keep #(if (space-is-open? % board) %) (range (count board)))
  )

(defn mark-space [space marker board]
  (when (space-is-open? space board)
    (assoc board space marker)))

(defn render-empty-board [length]
  (into [] (take length (range))))

(defn board-full?  [board]
  (= 0 (count (open-spaces board))))
