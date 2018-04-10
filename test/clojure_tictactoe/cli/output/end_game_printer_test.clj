(ns clojure-tictactoe.cli.output.end-game-printer-test
  (:require [clojure.test :refer :all]
            [clojure.string :as string]
            [clojure-tictactoe.helpers :as helper]
            [clojure-tictactoe.cli.output.end-game-printer :refer :all]))

(testing "End game messaging"

  (testing "game-won-message"
    (deftest game-won-message-test
      (testing "Prints the appropriate message"
        (is (string/includes? (game-won-message "x") "x!"))))))

