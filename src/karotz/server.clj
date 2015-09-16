(ns karotz.server
  (:use
   compojure.core
   ring.middleware.params
   ring.middleware.keyword-params
   [org.httpkit.server :only [run-server]])
  (:require [compojure.route :as route]
            [compojure.handler :as handler]))

(defn wrap-dir-index [handler]
  (fn [req]
    (handler
      (update-in req [:uri] #(if (= "/" %) "/index.html" %)))))

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



