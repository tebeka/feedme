(ns feedme-test
  (:use [feedme] :reload-all)
  (:use [clojure.test])
  (:import java.io.File))

(defn- resource [name]
  (let [testdir (.getParent (new File (.getAbsolutePath (new File *file*))))]
    (str "file://" testdir "/test/" name)))


(deftest slashdot
  (let [uri (resource "slashdot.rss")
        feed (parse uri)]
    (is (count (feed :entries)) 15)))

(deftest github
  (let [uri (resource "github.atom")
        feed (parse uri)]
    (is (count (feed :entries)) 35)
    (is (:title feed) "Private Feed for mikitebeka")
    (is (:title (nth (:entries feed) 0)) 
        "sah pushed to master at saucelabs/sauce")))
