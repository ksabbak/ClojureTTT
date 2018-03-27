(ns clojure-tictactoe.cli.output.instructions-printer-test
  (:require [clojure.test :refer :all]
            [clojure.string :as string]
            [clojure-tictactoe.helpers :as helper]
            [clojure-tictactoe.cli.output.instructions-printer :refer :all]))

(testing "Game start"
  (testing "print-game-intro"
    (deftest intro-game-message-test
      (testing "Intro messages"
       (is (string/includes? (with-out-str (print-game-intro)) welcome-message))
       (is (string/includes? (with-out-str (print-game-intro)) instructions-message))))))
