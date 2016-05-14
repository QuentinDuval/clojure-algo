(ns clojure-algo.core
  (:gen-class)
  (:use [ring.adapter.jetty :only (run-jetty)])
  (:use [ring.middleware.params :only (wrap-params)])
  (:use [compojure.core :only (defroutes GET)])
  (:require [compojure.route :as route]))

(defn -main
  [& args]
  (println (+ 1 2)))




; Playing with a small REST server

(defn echo-handler
  [{:keys [uri params]}]
  {:body
   (format "Path = \"%s\" with params \"%s\"" uri params)})

(defn apply-fn
  [name expr]
  (let [result (eval (read-string expr))]
    {:body
     (str "[" name "] " expr " : " (str result))
    }))

; https://github.com/weavejester/compojure/wiki/Nesting-routes
(defroutes app*
  "Handler that routes requests to other handlers"
  (GET "/" [] "<h1>Route page</h1>")
  (GET "/echo/*" request echo-handler)
  (GET "/fn/:name" [name expr] (apply-fn name expr))
  (route/not-found "<h1>No match</h1>"))

(def app
  "Extract the parameters and forward to handler"
  (wrap-params app*))

;; Test code

(def server (run-jetty #'app {:port 8080 :join? false}))
(.stop server)
