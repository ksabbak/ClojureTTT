(ns clojure-tictactoe.helpers)


(defn make-input [coll]
    (apply str (interleave coll (repeat "\n"))))
