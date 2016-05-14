(defproject clojure-algo "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [
	     [org.clojure/clojure "1.8.0"]
	     [javax.servlet/servlet-api "2.5"] ; Needed for "ring"
	     [ring "1.4.0"] ; HTTP server
	     [compojure "1.5.0"] ; HTTP rounting
     ]
  :aot [clojure-algo.core clojure-algo.geometry]
  :main clojure-algo.core)
