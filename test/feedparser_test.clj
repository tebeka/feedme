(import 'java.io.File)
(require 'feedme)

(defn file? [f]
  (.isFile f))

(defn abs-path [f]
  (.getAbsolutePath f))

(defn file-list [root]
  (map abs-path (filter file? (file-seq (new File root)))))

(defn xml-files [root]
  (filter #(.endsWith % ".xml") (file-list root)))

(defn passes? [path]
  (try
    (feedme/parse (str "file://" path))
    (catch Exception e nil)))


(defn check []
  (let [files (xml-files "test/feedparser/tests/wellformed/")]
    (when-not (empty? files)
      (let [num-files (count files)
            num-ok (count (filter passes? files))
            prec (* (/ num-ok num-files) 100)]
        (println (format "[wellformed] Passing %d of %d (%.2f%%)" 
                         num-ok num-files (float prec)))))))

(check)
