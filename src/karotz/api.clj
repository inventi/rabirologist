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

(defn leds
  ([karotz-ip rgb speed]
   (let [pulse (if (zero? speed) 0 1)]
     (karotz-request karotz-ip
                     (str "leds?pulse=" pulse "&speed=" speed "&color=" rgb))))
  ([karotz-ip rgb]
   (leds karotz-ip rgb 1 700)))

(defn ears
  ([karotz-ip]
   (ears karotz-ip 5 3))

  ([karotz-ip left right]
   (let [request-url (str "ears?left=" left "&right=" right)]
       (karotz-request karotz-ip request-url))))

(defn- escape [text]
  (-> text
      (st/replace #"'" "%27")
      (st/replace #" " "%20")))

(defn say [karotz-ip text]
  (karotz-request karotz-ip
                  (str "tts?voice=graham&text=" (escape text))))
