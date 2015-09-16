(ns karotz.server
  (:use
   compojure.core
   ring.middleware.params
   ring.middleware.keyword-params
   [org.httpkit.server :only [run-server]])
  (:require [compojure.route :as route]
            [compojure.handler :as handler]
            [karotz.api :as api]))

(defn wrap-dir-index [handler]
  (fn [req]
    (handler
      (update-in req [:uri] #(if (= "/" %) "/index.html" %)))))

(defn thinking [ip]
  (api/leds ip "ff0000" 700)
  (api/ears ip 1 1)
  (Thread/sleep 2000)
  (api/leds ip "fff200" 500)
  (Thread/sleep 2000)
  (api/leds ip "00b7ff" 300)
  (Thread/sleep 2000)
  (api/leds ip "00ff09" 0)
  (api/say ip "Greetings Antanas. Relax, it's going to be OK. Unless you're a software architect, in which case there's no hope for you."))

(def api-routes
  (->
    (routes
      (GET "/tellme" [name] name)
      (route/resources "/"))
    (wrap-dir-index)))

(def api (handler/api #'api-routes))

(defn- run [port]
  (run-server api {:port port}))

(defn -main [port]
  (do
    (run (Integer/valueOf port))
    (println "Rabbitz server started")))



