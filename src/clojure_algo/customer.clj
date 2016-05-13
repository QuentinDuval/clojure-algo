(ns clojure-algo.customer
  (:gen-class))


(defn make-customer
  "Creates a customer as a simple map"
  [name age address & others]
  {:name name
   :age age
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
  "Group the customer by name"
  [customers]
  (group-by :name customers))

(defn group-by-info
  "Group by several information provided by the user"
  [inputs & keys]
  (let [keys (fn [c] (into [] ((apply juxt keys) c)))
        grouper (fn [group c] (assoc-in group (keys c) c))]
    (reduce grouper {} inputs)
  ))


; For tests only

(defn run-test
  []
  (defn register-some-customers
	  []
    (register-customer "Kakrafoon" 35 "Moon")
    (register-customer "Odile Deray" 30 "Nice")
    (register-customer "John Doe" 0 "???" {:note "Not sure about his name"}))
  (reset! customers [])
	(register-some-customers)
	(dorun (map println @customers))
	(group-by-name @customers)
  (group-by-info @customers :name :address)
)

(run-test)