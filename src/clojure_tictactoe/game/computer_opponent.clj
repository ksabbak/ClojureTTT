(ns clojure-tictactoe.game.computer-opponent
  (:require [clojure-tictactoe.game.board :as board]
            [clojure-tictactoe.game.game-rules :as rules]))

(defn assign-opponent-marker [self-marker]
  (if (= "x" self-marker)
    "o"
    "x"))

(defn deduce-opponent-marker [board self-marker]
  (->> board
    (remove number?)
    (remove #(= self-marker %))))

(defn get-opponent-marker [board self-marker]
  (if-let [opponent-marker (first (deduce-opponent-marker board self-marker))]
    opponent-marker
    (assign-opponent-marker self-marker)))

(defn deduce-turn [board]
  (count (remove number? board)))

(defn get-turn-determiner [board]
  (if (even? (deduce-turn board))
    even?
    odd?))

(defn is-current-player? [board player-turn?]
  (let [turn (deduce-turn board)]
        (player-turn? turn)))

(defn swap-markers [markers]
    {:current-marker (:next-marker markers) :next-marker (:current-marker markers)})

(defn win-points [board player-turn?]
  (let [turn (deduce-turn board)
        length (count board)
        points (- (+ length 1) turn)]
    (if (is-current-player? board player-turn?)
      points
      (* points -1))))

(defn choose-best-score [scores board player-turn?]
  (if (is-current-player? board player-turn?)
    (apply max scores)
    (apply min scores)))

(declare mini-max memominimax)

(defn score-turn [board space markers player-turn?]
  (let [turn-marker (:current-marker markers)
        turn (deduce-turn board)
        too-deep (+ (board/side-length board) (- (board/side-length board) 1))
        new-board (board/mark-space space turn-marker board)]
        (cond
          (rules/winner? new-board) (win-points board player-turn?)
          (or (board/board-full? new-board) (> turn too-deep)) 0
          :else (memominimax new-board (swap-markers markers) player-turn?))))

(defn calculate-scores [board markers player-turn?]
  (map #(score-turn board % markers player-turn?) (board/open-spaces board)))

(defn mini-max
  ([board markers player-turn?]
    (let [scores  (calculate-scores board markers player-turn?)]
      (choose-best-score scores board player-turn?))))

(def memominimax (memoize mini-max))

(defn best-move [board markers player-turn?]
  (let [scores (calculate-scores board markers player-turn?)
          scored-spaces (zipmap (board/open-spaces board) scores)
          choice (key (apply max-key val scored-spaces))]
          choice))

(defn get-move [board marker]
  (let [opponent-marker (get-opponent-marker board marker)
        markers {:current-marker marker, :next-marker opponent-marker}
        player-turn? (get-turn-determiner board)]
        (best-move board markers player-turn?)))


