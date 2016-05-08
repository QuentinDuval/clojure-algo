(ns clojure-algo.geometry
  (:gen-class))

(set! *warn-on-reflection* true)

(defrecord Point
  ^{:doc "Docstring do not work directly in defrecord"}
  ; Type hints here does not help performance"
  [^long x ^long y])

(defn center-gravity
  "Compute the center of gravity of a set of points"
  [points]
  (let [ln (count points)
        mx (transduce (map :x) + 0 points)
        my (transduce (map :y) + 0 points)]
    (if (pos? ln)
      (Point. (/ mx ln) (/ my ln))
      (Point. 0 0))
    ))

(defn center-gravity-test
  "A small test of performance to check it scales okay"
  [n]
  (let [inputs (partition 2 (range (* 2 n)))
        coords (map (fn [[x y]] {:x x :y y}) inputs)
        points (map map->Point coords)]
    (time (dorun points))
    (time (center-gravity coords))
    (time (center-gravity points))
  ))

(center-gravity-test 100000)
