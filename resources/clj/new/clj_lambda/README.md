## Description

TODO

## Prerequisites

Be sure to have installed and setup:

* Clojure and [Clojure CLI](https://clojure.org/releases/tools) >= 1.10.3.1040
* [Babashka](https://github.com/babashka/babashka) >= 0.6.8
* [AWS CLI v2](https://github.com/aws/aws-cli/tree/v2)
* AWS credentials. Recommend using
  [aws-vault](https://github.com/99designs/aws-vault). Most commands below
  would then be prefixed with `aws-vault exec my-profile -- ...`

## Usage

### Create lambda

To uberjar and create a lambda fn: `bb lambda-create`. This is a basic lambda
with permissions to write to CloudWatch. To do more interesting things with
lambda consider using terraform.

### Update lambda

If lambda was created with `bb lambda-create`, update fn with local code using
`bb lambda-update-code`.

### Invoke lambda

Invoke lambda with the payload as an EDN string e.g. `bb lambda-invoke '{:foo
:bar}'`.

### Build uberjar

To build the uberjar: `bb uber`.

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
