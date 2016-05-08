(ns clojure-algo.geometry-test
  (:require [clojure.test :refer :all]
            [clojure-algo.core :refer :all]
            [clojure-algo.geometry :refer :all])
  (:import  [clojure_algo.geometry Point]))

(deftest with-some-points
  (testing "Testing with several points"
    (let [c (center-gravity [{:x 1 :y 5} (Point. 3 1)])]
      (is (= (Point. 2 3) c)))))
 
(deftest with-no-points
  (testing "Testing without any inputs")
  (let [c (center-gravity [])]
    (is (= (Point. 0 0) c))))

(run-tests)
