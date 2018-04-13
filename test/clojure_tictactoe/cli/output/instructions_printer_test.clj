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

(testing "Markers"
  (testing "print-marker-instructions"
    (deftest print-marker-instructions-test
      (testing "displays passed param"
        (is (string/includes? (with-out-str (print-marker-instructions "test")) "test?")))))
  )

(testing "Stringify options"
  (testing "stringify-options"

    (deftest stringify-options-test-one
      (testing "Returns one option for a one item vector"
        (is (= "1. one\n" (with-out-str (stringify-options ["one"]))))))

    (deftest stringify-options-test-multiple
      (testing "Works on a vector with more than on item"
        (is (= "1. one\n2. two\n3. three\n" (with-out-str (stringify-options ["one" "two" "three"]))))))))
