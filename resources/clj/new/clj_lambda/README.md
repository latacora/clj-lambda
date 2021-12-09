## Description

TODO

## Prerequisites

Be sure to have installed:

* Clojure and [Clojure CLI](https://clojure.org/releases/tools) >= 1.10.3.1040
* [Babashka](https://github.com/babashka/babashka) >= 0.6.8
* [AWS CLI v2](https://github.com/aws/aws-cli/tree/v2)

## Usage

To build your project's uberjar: `bb uber`

### Create lambda

To uberjar and create a lambda fn: `bb lambda-create`. This is a basic lambda with permissions to write to CloudWatch. To do more interesting things with your lambda you'll probably want to use terraform.

### Update lambda

If you used `bb lambda-create` to create your lambda, you can update your fn with a local version of your code with `bb lambda-update-code`

### Invoke lambda

Invoke your lambda with the payload as an EDN string e.g. `bb lambda-invoke '{:foo :bar}'`.

## Development

These are common commands to run during development. They are also required to pass on CI.

```bash
# Run tests
clojure -X:test

# Run style linter
clojure -M:cljfmt check

# Run code linter
clojure -M:clj-kondo

# Check dependencies are up to date
clojure -M:outdated
```
