(ns clojure-tictactoe.core-test
  (:require [clojure.test :refer :all]
            [clojure-tictactoe.core :refer :all]
	    [clojure-tictactoe.tictactoe :refer :all]))

(deftest a-test
  (testing "I pass."
    (is (= 1 1))))

(deftest start-test
  (testing "can connect to app"
    (is (= 2 (start)))))
