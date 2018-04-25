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

(defn turn-marker [is-current-player markers]
  (if is-current-player
    (:self markers)
    (:opponent markers)))

(defn win-points [is-current-player board]
  (let [points (- (+ (count board) 1) (deduce-turn board))]
    (if is-current-player
      points
      (* points -1))))

(defn choose-best-score [is-current-player scores]
  (if is-current-player
    (apply max scores)
    (apply min scores)))

(declare score-turn, mini-max)

(defn score-turn [board space markers player-turn?]
  (let [turn (deduce-turn board)
        is-current-player (player-turn? turn)
        turn-marker (turn-marker is-current-player markers)
        new-board (board/mark-space space turn-marker board) ]
        (cond
          (rules/winner? new-board) (win-points is-current-player board)
          (board/board-full? new-board) 0
          :else (mini-max new-board markers player-turn?))))

(defn mini-max
  ([board markers player-turn?]
    (let [scores  (map #(score-turn board % markers player-turn?) (board/open-spaces board))
        turn (deduce-turn board)
        is-current-player (player-turn? turn)]
      (choose-best-score is-current-player scores)))
  ([board markers player-turn? fix-this]
    (let [scores (map #(score-turn board % markers player-turn?) (board/open-spaces board))
          scored-spaces (zipmap (board/open-spaces board) scores)
          choice (key (apply max-key val scored-spaces))]
          choice)))


(defn get-move [board marker]
  (let [opponent-marker (get-opponent-marker board marker)
        markers {:self marker, :opponent opponent-marker}
        player-turn? (get-turn-determiner board)]
        (mini-max board markers player-turn? true)))

; (def memominimax (memoize mini-max))

