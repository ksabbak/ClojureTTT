(ns clojure-tictactoe.cli.output.instructions-printer-test
  (:require [clojure.test :refer :all]
            [clojure.string :as string]
            [clojure-tictactoe.helpers :as helper]
            [clojure-tictactoe.cli.output.messages :as m]
            [clojure-tictactoe.cli.output.instructions-printer :refer :all]))

(testing "Game start"
  (testing "print-game-intro"
    (deftest intro-game-message-test
      (testing "Intro messages"
        (is (string/includes? (with-out-str (print-game-intro)) m/welcome-message))
        (is (string/includes? (with-out-str (print-game-intro)) m/instructions-message))))))

(testing "Markers"
  (testing "print-marker-instructions"
    (deftest print-marker-instructions-test
      (testing "displays passed param"
        (is (string/includes? (with-out-str (print-marker-instructions "test")) "test?"))))))

(testing "Ordered list options"
  (testing "print-ordered-list-options"

    (deftest print-ordered-list-options-test-one
      (testing "Returns one option for a one item vector"
        (is (= "\n1. one\n\n" (with-out-str (print-ordered-list-options ["one"]))))))

    (deftest print-ordered-list-options-test-multiple
      (testing "Works on a vector with more than on item"
        (is (= "\n1. one\n2. two\n3. three\n\n" (with-out-str (print-ordered-list-options ["one" "two" "three"]))))))))
