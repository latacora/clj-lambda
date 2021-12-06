(ns {{namespace}}
  (:require [clojure.java.io :as io]
            [cheshire.core :as json])
  (:import [com.amazonaws.services.lambda.runtime RequestStreamHandler])
  (:gen-class
   :name {{namespace}}.handler
   :implements [com.amazonaws.services.lambda.runtime.RequestStreamHandler]))

(defn -handleRequest
  [_ in out _context]
  (with-open [writer (io/writer out)
              reader (io/reader in)]
    (let [events (json/parse-stream reader keyword)]
      (println "Events:" events)
      (json/generate-stream {:hello "world"} writer))))
