(ns karotz.server
  (:use
   compojure.core
   ring.middleware.params
   ring.middleware.keyword-params
   [org.httpkit.server :only [run-server]])
  (:require [compojure.route :as route]
            [compojure.handler :as handler]
            [ring.util.response :as resp]
            [karotz.api :as api]
            [karotz.wisdom :as wisdom]))

(defn wrap-dir-index [handler]
  (fn [req]
    (handler
      (update-in req [:uri] #(if (= "/" %) "/index.html" %)))))

(defn tell [ip text]
  (api/leds ip "ff0000" 700)
  (api/ears ip 1 1)
  (Thread/sleep 2000)
  (api/leds ip "fff200" 500)
  (Thread/sleep 2000)
  (api/leds ip "00b7ff" 300)
  (Thread/sleep 2000)
  (api/leds ip "00ff09" 0)
  (api/say ip text))

(def api-routes
  (->
    (routes
      (GET "/tellme" [name]
           (do
             (tell "10.50.0.160" (wisdom/predict name))
             (resp/redirect "/index.html")))
      (route/resources "/"))
    (wrap-dir-index)))

(def api (handler/api #'api-routes))

(defn- run [port]
  (run-server api {:port port}))

(defn -main [port]
  (do
    (run (Integer/valueOf port))
    (println "Rabbitz server started")))

