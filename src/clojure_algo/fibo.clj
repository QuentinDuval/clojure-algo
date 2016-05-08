(ns clojure-algo.fibo)

(defn fib-iterate
  "Fibonacci based on iterate"
  [n]
  (let [iter (fn [[a b]] [b (+ a b)])
        init [(bigdec 0) (bigdec 1)]
        seq (iterate iter init)]
    (first (first (drop n seq)))))

(defn fib-loop
  "Fibonacci based on a loop"
  [n]
  (loop [a (bigdec 0) b (bigdec 1) i ^int n]
    (if (pos? i)
      (recur b (+ a b) (dec i))
      a)))

(defn fib-seq
  "Fibonacci based on a lazy sequence"
  [n]
  (defn fibs [a b]
    (lazy-seq (cons a (fibs b (+ a b)))))
  (nth (fibs (bigdec 0) (bigdec 1)) n))

(defn fib-tests
  []
  (let [n 2000]
    (time (fib-iterate n))
    (time (fib-loop n))
    (time (fib-seq n))
    nil
  ))

(fib-tests)
