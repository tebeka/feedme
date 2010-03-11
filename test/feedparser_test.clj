(import 'java.io.File)
(require 'feedme)
(use 'clojure.contrib.duck-streams)

(defn file? [f]
  (.isFile f))

(defn abs-path [f]
  (.getAbsolutePath f))

(defn walk-tree [files]
  (when-first [file files]
    (if (file? file)
      (lazy-cat (list file) (walk-tree (rest files)))
      (lazy-cat (walk-tree (.listFiles file))
                (walk-tree (rest files))))))

(defn file-list [root]
  (map abs-path (filter file? (walk-tree (.listFiles (new File root))))))

(defn xml-files [root]
  (filter #(.endsWith % ".xml") (file-list root)))

(defn check []
  (doseq [file (xml-files "test/feedparser/tests/wellformed/")]
    (try
      (feedme/parse (str "file://" file))
    (catch Exception e (println file)))))

(defn obj-ok []
  (doseq [uri (read-lines "failed.log")]
    (try
      (feedme/make-feed uri)
      (println uri)
    (catch Exception e))))

(obj-ok)
