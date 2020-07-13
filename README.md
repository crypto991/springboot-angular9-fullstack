# Spring Boot API with Angular 9 UI
 
This example app shows how to create a Spring Boot API and display its data with an Angular 9 UI.


**Prerequisites:** [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) and [Node.js](https://nodejs.org/).


## Getting Started

To install this example application, run the following commands:

```bash
git clone https://github.com/crypto991/springboot-angular9-fullstack.git
cd springboot-angular9-fullstack
```


This will get a copy of the project installed locally. To install all of its dependencies and start each app, follow the instructions below.

#### Create MySQL Database : 
```bash
CREATE SCHEMA `testdb`;
```

To run the server, cd into the `server` folder and run:
 
```bash
./mvnw clean install

./mvnw spring-boot:run
```

To run the client, cd into the `client` folder and run:
 
```bash
npm install && npm start
```

Angular UI AWS URL : http://angular-s3-bucket.s3-website.eu-central-1.amazonaws.com/
     
```bash
credentials: 
    username: test@gmail.com
    password: 123456
```

Spring REST API AWS URL : http://restapitest-env.eba-gmpkzu72.eu-central-1.elasticbeanstalk.com/
