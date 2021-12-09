## Description

This template generates a project to easily build and deploy a Clojure based AWS
Lambda. The generated project provides the following:

* A slim deps.edn that only contains dependencies needed to work with AWS Lambda
* Uberjar compilation that relies on [tools.build](https://clojure.org/guides/tools_build)
* Clojure aliases to verify Clojure best practices including testing and linting
* A CI config to enforce Clojure best practices
* Babashka tasks to create an uberjar based lambda and other common lambda tasks

## Usage

```sh
# one-off to install clj-new as a tool:
clojure -Ttools install com.github.seancorfield/clj-new '{:git/tag "v1.2.362"}' :as clj-new

# Generate a project. Quoting inside template string is necessary
clojure -Tclj-new create :template '"https://github.com/latacora/clj-lambda@LATEST_SHA"' :name com.example/bar
```

## Development

Create a project from local version of template:

```sh
clojure -X:new :name com.example/myproject
```

Build a deployable jar of this template:

```sh
clojure -T:build ci
```

This will produce an updated `pom.xml` file with synchronized dependencies inside the `META-INF`
directory inside `target/classes` and the JAR in `target`. You can update the version (and SCM tag)
information in generated `pom.xml` by updating `build.clj`.

Install it locally (requires the `ci` task be run first):

```sh
clojure -T:build install
```

Deploy it to Clojars -- needs `CLOJARS_USERNAME` and `CLOJARS_PASSWORD` environment
variables (requires the `ci` task be run first):

```sh
clojure -T:build deploy
```

Your template will be deployed to com.latacora/clj-lambda on clojars.org by default.

## Tests

CI confirms that the template generates correctly with a passing CI config. Dependencies need to be up to date for both this project and the generated project.

## Alternatives

There are other Clojure libraries and templates that aid with Lambda
development. They have different and overlapping goals but none quite
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
