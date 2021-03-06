# HP Developer Day 2015 - Łódź

## Java Backend

### Prerequisites

- JDK 8
- Maven 3.3

### Developer environment setup

Import to your IDE as a Maven project.
Import Maven dependencies.

#### Configure:

Edit [application.properties](src/main/resources/application.properties)

### Build

#### Build deployable war:

```bash
mvn clean package
```

#### Run on embedded web server:

From Maven:

```bash
mvn spring-boot:run
```

From your IDE:

Execute `PietrynaApp`

### Test:

You will need a REST client:

* Postman extension for Chrome
* IntelliJ IDEA: Tools > Test RESTful Web Service
* Other client of your choice

Url: [http://localhost:9000/pietryna/api/users/me](http://localhost:9000/pietryna/api/users/me)

HTTP Headers:

* Content-Type: application/json
* Authorization: Basic `base64_encoded_token`

where `base64_encoded_token` is a string: `username:password` encoded with Base64

For example, for username test1@test.com with an empty password you should encode a string `test1@test.com:` : dGVzdDFAdGVzdC5jb206

To encode a string you can use:

* [http://duckduckgo.com](http://duckduckgo.com) - search phrase: base64 user:password
* [https://www.base64decode.org](https://www.base64decode.org)

In response, you should get the JSON describing a user identified with a username passed in Authorization header.