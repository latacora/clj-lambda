(ns {{namespace}}-test
  (:require [clojure.test :refer [deftest is]]
            [cheshire.core :as json]
            [{{namespace}} :as {{name}}])
  (:import [java.io StringWriter StringReader]))

(deftest handle-request-test
  (let [out (StringWriter.)
        mock-event {:Records []}]
    ({{name}}/-handleRequest nil (StringReader. (json/generate-string mock-event)) out nil)
    (is (= {"hello" "world"}
           (json/parse-string (str out))))))
