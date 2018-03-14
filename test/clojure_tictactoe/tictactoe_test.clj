(ns clojure-tictactoe.tictactoe-test
  (:require [clojure.test :refer :all]
            [clojure-tictactoe.tictactoe :refer :all]))

(deftest welcome-test
  (testing "Welcome message"
    (is (= "Welcome to the game of TicTacToe! \n" (welcome)))))

(deftest instructions-test
  (testing "Instructions"
    (is (= "Instructions: 2 players take turns placing markers, try to get three of your markers in a row to win! 


Examples: 

  Vertical      Horizontal      Diagonal

   | x |     |    |   |     |  x |   |     
===+===+===  | ===+===+===  | ===+===+===  
   | x |     |  x | x | x   |    | x |     
===+===+===  | ===+===+===  | ===+===+===  
   | x |     |    |   |     |    |   | x   "))))



