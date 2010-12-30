======
FeedMe
======
Miki Tebeka <miki.tebeka@gmail.com>

Description
===========

An RSS/Atom parser for Clojure

Usage
=====
Add the following dependency to `project.clj`::
    
    [feedme "0.0.3"]

Then in your code::

    (require 'feedme)
    
    (let [feed (feedme/parse "http://rss.slashdot.org/Slashdot/slashdot")
          entry (nth (:entries feed) 0)]
        (println (format "%s [%s]" (:title entry) (:published entry))))

Fields
======

feed
----
:: 

    :author
    :description
    :language
    :link
    :type
    :published
    :title
    :entries

entry
-----
:: 

    :author
    :categories
    :content-type
    :content
    :id
    :link
    :published
    :title
    :updated


License
=======
MIT (see LICENSE.txt)
