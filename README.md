## Description

This template generates a project to easily build and deploy a Clojure based AWS
Lambda. The generated project contains a minimal deps.edn, clojure aliases for
enforcing clojure best practices and babashka tasks to quickly test a lambda.

## Usage

```bash
# one-off to install clj-new as a tool:
clojure -Ttools install com.github.seancorfield/clj-new '{:git/tag "v1.2.362"}' :as clj-new

# Generate a project. Quoting inside template string is necessary
clojure -Tclj-new create :template '"https://github.com/latacora/clj-lambda@LATEST_SHA"' :name com.example/bar
```
## Development

Create a project from local version of template:

```bash
clojure -X:new :name com.example/myproject
```

Build a deployable jar of this template:

    $ clojure -T:build ci

This will produce an updated `pom.xml` file with synchronized dependencies inside the `META-INF`
directory inside `target/classes` and the JAR in `target`. You can update the version (and SCM tag)
information in generated `pom.xml` by updating `build.clj`.

Install it locally (requires the `ci` task be run first):

    $ clojure -T:build install

Deploy it to Clojars -- needs `CLOJARS_USERNAME` and `CLOJARS_PASSWORD` environment
variables (requires the `ci` task be run first):

    $ clojure -T:build deploy

Your template will be deployed to com.latacora/clj-lambda on clojars.org by default.

## Alternatives

There are other Clojure libraries and templates that aid with Lambda
development. They each have different and overlapping goals but none quite
provide what this template does. They are worth checking out:

* https://github.com/tokenmill/clojure-graalvm-aws-lambda-template
* https://github.com/paulbutcher/lein-lambda
* https://github.com/nervous-systems/cljs-lambda
* https://github.com/FieryCod/holy-lambda
* https://github.com/uswitch/lambada
* https://github.com/latacora/lamed-sample-app

## License

Copyright © 2021 Latacora

Distributed under the Eclipse Public License version 1.0.
