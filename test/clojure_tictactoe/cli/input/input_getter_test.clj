(ns clojure-tictactoe.cli.input.input-getter-test
  (:require [clojure.test :refer :all]
            [clojure.string :as string]
            [clojure-tictactoe.helpers :as helper]
            [clojure-tictactoe.cli.input.input-getter :refer :all]))

(testing "Game start"
  (testing "continue-to-game"
    (deftest continue-to-game-message-test
      (testing "The message displays properly"
        (is (string/includes? (with-out-str (with-in-str "\n" (continue-to-game))) "continue"))))

    (deftest continue-to-game-input-test
      (testing "The function returns nil once newline has been entered"
        (is (= (with-in-str "\n" (continue-to-game)) nil ))))))
