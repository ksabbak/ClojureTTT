(ns clojure-tictactoe.cli.output.end-game-printer-test
  (:require [clojure.test :refer :all]
            [clojure.string :as string]
            [clojure-tictactoe.helpers :as helper]
            [clojure-tictactoe.cli.output.end-game-printer :refer :all]))

(testing "End game messaging"
  (testing "print-game-over"
    (deftest print-game-over-test-tie
      (testing "Prints tie message when it gets nil as an argument"
        (is (string/includes? (with-out-str (print-game-over nil)) game-tie-message))))
    
    (deftest print-game-over-test-win
      (testing "Prints won message when it gets a truthy argument"
        (is (string/includes? (with-out-str (print-game-over "x")) (game-won-message "x")))))
    )
  
  (testing "game-won-message"
    (deftest game-won-message-test
      (testing "Prints the appropriate message"
        (is (string/includes? (game-won-message "x") "x!")))))
  )
