(defproject lt.inventi/rabirologist "1.0.0"
  :description "Karotz rabbit numerologist"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [compojure  "1.4.0"]
                 [http-kit  "2.1.18"]
                 [javax.servlet/servlet-api  "2.5"]]
  :jvm-opts ["-Dfile.encoding=UTF-8" "-Dconsole.encoding=utf-8"]
  :min-lein-version "2.0.0"
  :deploy-repositories [["releases" "http://nexus.inventi.corp/content/repositories/releases/"]
                        ["snapshots" "http://nexus.inventi.corp/content/repositories/snapshots/"]])
