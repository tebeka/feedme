; FIXME: Rome latest version is 1.0, however the maven repo has on 0.9 (from
; 2006). Currently we host rome-1.0.jar locally in lib and require jdom. Find a
; way to get the right Rome jar (ask comp.lang.clojure)

(defproject feedme "1.0.0-SNAPSHOT"
  :description "Easy access to RSS/Atom Feeds"
  :repositories [["java.net" "http://download.java.net/maven/2/"]]
  :dependencies [[org.clojure/clojure "1.1.0"]
                 [org.clojure/clojure-contrib "1.1.0"]
                 [rome "1.0"]])
