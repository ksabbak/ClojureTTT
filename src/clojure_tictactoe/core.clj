(ns clojure-tictactoe.core
  (:require [clojure-tictactoe.tictactoe :as ttt]))

(defn -main
  [& args]
  (ttt/intro-game))
