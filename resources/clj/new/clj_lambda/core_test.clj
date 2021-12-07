(ns {{namespace}}-test
  (:require [clojure.test :refer [deftest is]]
            [cheshire.core :as json]
            [com.latacora.baz :as baz])
  (:import [java.io StringWriter StringReader]))

(deftest handle-request-test
  (let [out (StringWriter.)
        mock-event {:Records []}]
    (baz/-handleRequest nil (StringReader. (json/generate-string mock-event)) out nil)
    (is (= {"hello" "world"}
           (json/parse-string (str out))))))
