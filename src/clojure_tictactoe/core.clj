(ns clojure-tictactoe.core
  (:require [clojure-tictactoe.game.game-play :as game-play]))

(defn -main
  [& args]
  (game-play/game-loop))
