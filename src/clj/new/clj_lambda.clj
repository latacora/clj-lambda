(ns clj.new.clj-lambda
  (:require [clj.new.templates :refer [renderer project-data ->files]]))

(defn clj-lambda
  "FIXME: write documentation"
  [name]
  (let [render (renderer "clj-lambda")
        data   (project-data name)]
    (println "Generating fresh 'clj new' clj-lambda project.")
    (->files data
             ["deps.edn" (render "deps.edn" data)]
             ["src/{{nested-dirs}}/foo.clj" (render "foo.clj" data)])))
