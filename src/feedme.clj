(ns feedme
  (:import java.net.URL 
           com.sun.syndication.feed.synd.SyndFeed
           com.sun.syndication.io.SyndFeedInput
           com.sun.syndication.io.XmlReader))

(defn- entry [e]
  (let [content (or (.getDescription e) (nth (.getContents e) 0))]
    {
     :author (.getAuthor e)
     :categories (into [] (.getCategories e))
     :content-type (.getMode content)
     :contents (.getValue content)
     :link (.getLink e)
     :published (.getPublishedDate e)
     :title (.getTitle e)
     :updated (.getUpdatedDate e)}))

(defn parse [url]
  (let [url (new URL url)
        input (new SyndFeedInput)
        feed (.build input (new XmlReader url))]
    {
     ; FIXME: This can be probably done nicer with a macro
     :author (.getAuthor feed)
     :description (.getDescription feed)
     :language (.getLanguage feed)
     :link (.getLink feed)
     :type (.getFeedType feed)
     :published (.getPublishedDate feed)
     :title (.getTitle feed)
     :entries (map entry (.getEntries feed))
     }))
