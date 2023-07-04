## Introduction

This project is a backend application of built for Rova java developer assessment.

It helps to manage customer account creation and profile mgt

## Setup

Make sure to follow all these steps exactly as explained below. Do not miss any steps or you won't be able to run this application.

### Start the Server with Docker
Run the following Docker commands in the project root directory to run the application
- `docker build -t account-service . `
-
then run

- `docker run -p 9093:9093 account-service `
  NB: you can bind to any other free port incase 9093 is already in use on your machine

### CREDENTIALS
- DB username is empty
- DB password is empty
- Basic auth username is 'RVA_rova_test'
- Basic auth password is '123456'

### Documentation
- Swagger doc = http://localhost:9093/swagger-ui/index.html
- Database tables = http://localhost:9093/h2-console/


### DATABASE
- DB = H2 in memory
- migration = Liquibase

### NB
When customer creates an account from the signup API, an access toke is generated and stored
in the response header.

The jwt token is required every other user resource

Some resource access require basic auth
`username=RVA_rova_test`
`password=123456`



