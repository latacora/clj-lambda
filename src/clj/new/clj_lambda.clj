(ns clj.new.clj-lambda
  (:require [clj.new.templates :refer [renderer project-data ->files]]))

(defn clj-lambda
  "Generate a clj-lambda project with clj new"
  [name]
  (let [render (renderer "clj-lambda")
        data   (project-data name)]
    (println "Generating fresh 'clj new' clj-lambda project.")
    (->files data
             ["deps.edn" (render "deps.edn" {})]
             ["build.clj" (render "build.clj" data)]
             ["build_shared.clj" (render "build_shared.clj" data)]
             ["bb.edn" (render "bb.edn" {})]
             ["script/lambda.clj" (render "lambda.clj" data)]
             ["src/{{nested-dirs}}.clj" (render "core.clj" data)]
             ["test/{{nested-dirs}}_test.clj" (render "core_test.clj" data)])))
