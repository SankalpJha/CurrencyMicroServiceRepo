# Currency Micro Services Project

This project is about how spring boot handles the configuration of different micro services deployed over the cloud platform. In this proejct we will you GitHub as the repo which holds the configuration and spring cloud service which talks other micro service and provide them their configurations. This way all the deployed micro service communicates with a centralized cloud server for configurations.

## Prerequsites

Prior having a look at this project one needs to have basic knowledge of spring, RESTful API's, java, hibernate, GitHub and POJO.

### Features

This project has implementation of below features:
* Spring-boot
* RESTful API
* H2-JPA Implementation
* Exception Handling
* Basic Authentication or Spring Security
* HATEOAS Implementation
* Swagger Documentation

### Required Tools
* Java 1.8+
* Apache Maven
* Spring tool suite

### Installation
* Clone the repository.
* Import project into STS workspace as MAVEN project.
* Right click on the project -> Run -> **Spring boot app**
* Use Postman to hit APIs

### Future Enhancement
* Logging Mechanism
* JWT Implementation

### Description

We have simple rest application in which `currency-calculation-service` service gives call to `currency-exchange-service` for conversion value. `currency-exchange-service` returns the conversion value from database. Then `currency-calculation-service` makes calculation for designated currency. In this we can have multiple instances of `currency-exchange-service` running over cloud and `currency-calculation-service` communicates with different instances.

