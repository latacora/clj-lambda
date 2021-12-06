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
             ["build.clj" (render "build.clj" data)]
             ["build_shared.clj" (render "build_shared.clj" data)]
             ["bb.edn" (render "bb.edn" data)]
             ["src/{{nested-dirs}}.clj" (render "core.clj" data)])))
