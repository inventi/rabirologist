(ns karotz.api
  (:require [clojure.xml :as xml]
            [clojure.string :as st])
  (:import java.io.IOException
           (java.util.logging Logger Level)))

(def log (Logger/getLogger "lt.inventi.karotz.api"))

(defn- karotz-api [host]
  (str "http://" host "/cgi-bin"))

(defn- karotz-request
  ([karot url]
   (let [url  (str (karotz-api karot) "/" url)]
     (future
       (try
         (slurp url)
         (catch Exception e
           (.log log Level/SEVERE "failed to send request to karotz" e))))
     url)))

(defn ears
  ([karotz-ip]
   (ears karotz-ip 5 3))

  ([karotz-ip left right]
   (let [request-url (str "ears?left=" left "&right=" right)]
       (karotz-request karotz-ip request-url))))

(defn say [karotz-ip media-url]
  (let [escaped-url (st/replace (str "sound?url=" media-url) #" " "%20")]
    (karotz-request karotz-ip escaped-url)))
