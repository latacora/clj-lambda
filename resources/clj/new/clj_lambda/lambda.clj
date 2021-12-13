(ns lambda
  "Helper fns for aws lambda"
  (:require [cheshire.core :as json]
            [again.core :as again]
            [clojure.edn :as edn]
            [babashka.tasks :refer [shell]]))

(def role-name "{{name}}")
(def fn-name "{{name}}")
(def handler-name "{{namespace}}.handler")

(defn- create-fn
  [role-arn jar-file]
  ;; Retries because role needs time to create. When role isn't created
  ;; we see the error 'The role defined for the function cannot be
  ;; assumed by Lambda.'
  (again/with-retries
    {::again/callback (fn [attempt]
                        (case (::again/status attempt)
                          :retry (println "Retrying...")
                          :success (println "Success!")
                          nil))
     ::again/strategy [5000 10000]}
    (shell (format
            ;; These options are known to work but they should be changed to fit
            ;; your lambda's needs
            "aws lambda create-function --function-name %s --zip-file fileb://%s --role %s --runtime java11 --memory-size 256 --handler %s"
            fn-name
            jar-file
            role-arn
            handler-name))))

(defn create
  "Creates a lambda fn and execution role. The role also has permissions to
  upload logs CloudWatch. This lambda is meant for development and a production
  one should be managed by a more appropriate tool like terraform"
  [jar-file]
  (let [policy {"Version" "2012-10-17"
                "Statement" [{"Effect" "Allow" "Principal" {"Service" "lambda.amazonaws.com"} "Action" "sts:AssumeRole"}]}
        role-arn (-> (shell {:out :string}
                            "aws iam create-role --role-name"
                            role-name
                            "--assume-role-policy-document"
                            (json/generate-string policy))
                     :out
                     json/parse-string
                     (get-in ["Role" "Arn"]))]
    (shell "aws iam attach-role-policy --role-name"
           role-name
           "--policy-arn"
           "arn:aws:iam::aws:policy/service-role/AWSLambdaBasicExecutionRole")
    (create-fn role-arn jar-file)))

(defn update-code
  "Updates lambda with the latest local code"
  [jar-file]
  (shell "aws lambda update-function-code --function-name"
         fn-name
         "--zip-file"
         (str "fileb://" jar-file)))

(defn invoke
  "Invokes lambda with given event as an EDN string"
  [args]
  (shell "aws lambda invoke --cli-binary-format raw-in-base64-out --function-name"
         fn-name
         "--payload"
         (json/generate-string (edn/read-string (first args)))
         "/dev/stdout"))
