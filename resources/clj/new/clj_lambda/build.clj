(ns build
  (:refer-clojure :exclude [test])
  (:require [org.corfield.build :as bb]
            [build-shared :as bs]))

(def main '{{namespace}})

(defn uber "Build the uberjar." [opts]
  (-> opts
      (assoc :lib bs/lib :version bs/version :main main)
      (bb/uber)))
