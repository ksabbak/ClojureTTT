(ns clojure-tictactoe.cli.input.input-checker-test
  (:require [clojure.test :refer :all]
            [clojure.string :as string]
            [clojure-tictactoe.helpers :as helper]
            [clojure-tictactoe.cli.input.input-checker :refer :all]))


(testing "Player moves"

  (testing "valid-numeral?"
    (deftest valid-numeral?-test-not-number
      (is (false? (valid-numeral? :former-string ["x" 1 2 3 4 5 6 7 8]))))

    (deftest valid-numeral?-test-too-large
      (is (false? (valid-numeral? 10 ["x" 1 2 3 4 5 6 7 8]))))

    (deftest valid-numeral?-test-too-small
      (is (false? (valid-numeral? 0 ["x" 1 2 3 4 5 6 7 8]))))

    (deftest valid-numeral?-test-valid
      (is (true? (valid-numeral? 1 ["x" 1 2 3 4 5 6 7 8])))
      (is (true? (valid-numeral? 5 ["x" 1 2 3 4 5 6 7 8])))
      (is (true? (valid-numeral? 9 ["x" 1 2 3 4 5 6 7 8])))))

  (testing "valid-move?"
    (deftest valid-move?-test-taken-space
      (is (false? (valid-move? 1 [0 "x" 2 3 4 5 6 7 8]))))

    (deftest valid-move?-test-open-space
      (is (true? (valid-move? 1 [0 1 2 3 4 5 6 7 8]))))))


(testing "Player Markers"

  (testing "acceptable-marker-option?"

    (deftest acceptable-marker-option?-test-too-long
      (testing "returns false if the potential marker is longer than one char"
        (is (false? (acceptable-marker-option? "Nope")))))

    (deftest acceptable-marker-option?-test-good
      (testing "returns true for markers that are only one char long"
        (is (true? (acceptable-marker-option? "x"))))))

  (testing "distinct-markers?"

    (deftest distinct-markers?-test-redundant-markers
      (testing "Returns false if both markers are the same"
        (is (false? (distinct-markers? '("x" "x"))))))

    (deftest distinct-markers?-test-distinct-markers
      (testing "Returns true if markers are distinct"
        (is (true? (distinct-markers? '("o" "x"))))))))
