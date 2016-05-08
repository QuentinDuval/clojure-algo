(ns clojure-algo.collision
  (:gen-class))


(defn asteroid
  [name]
  {:name name :category ::asteroid})

(defn rocket
  [matricule]
  {:matricule matricule :category ::rocket})


(defmulti collide
  (fn ([lhs rhs]
    [(:category lhs) (:category rhs)])))

(defmethod collide
  [::asteroid ::asteroid] [lhs rhs]
  (println (str "Astroid collision: " (:name lhs) " " (:name rhs))))

(defmethod collide
  [::rocket ::rocket] [lhs rhs]
  (println (str "That should be impossible! Where is the PM?")))

(defmethod collide
  [::asteroid ::rocket] [lhs rhs]
  (println (str "Rocket " (:matricule rhs)
                " has been blowed by asteroid "
                (:name lhs))))

(defmethod collide
  [::rocket ::asteroid] [lhs rhs]
  (collide rhs lhs))

(defn test-collision
  []
  (let [a1 (asteroid "a1")
        a2 (asteroid "a2")
        r1 (rocket "R2D2")
        r2 (rocket "C6PO")]
    (collide a1 a2)
    (collide r1 r2)
    (collide a1 r1)
    (collide r1 a1)
  ))




