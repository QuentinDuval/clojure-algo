(ns clojure-algo.customer
  (:gen-class))


(defn make-customer
  "Creates a customer as a simple map"
  [name address & others]
  {:name name
   :address address
   :other-info others})

(def customers
  "Emulates a DB containing all of our customers"
  (atom []))

(defn register-customer
  "Add a new customer to our data-base"
  [& args]
  (swap! customers conj (apply make-customer args)))

(defn group-by-name
  [customers]
  (group-by :name customers))


; For tests only

(comment
	(defn register-some-customers
	  []
	  (register-customer "Kakrafoon" "Moon")
	  (register-customer "Odile Deray" "Nice")
	  (register-customer "John Doe" "???" {:note "Not sure about his name"}))
	
	(register-some-customers)
	(dorun (map println @customers))
	(group-by-name @customers)
)