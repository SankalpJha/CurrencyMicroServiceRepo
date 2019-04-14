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
* HATEOAS Implementation
* Swagger Documentation
* Feign REST Client
* Eureka Naming Server Implementaiton
* Zuul API Gateway Implementaion
* Load Distribution through Ribbon
* Health monitoring through Actuator
* Tracing a request through distributed Zipkin server
* Logging Filter through API Gateway
* Rabbit MQ with Zipkin

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
When we call a resouce through `currency-calculation-service` the request will go through API Gateway for this `netflix-zuul-zpi-gateway` server is implemented, this API Gateway has Logging filter, we can have many things which we want to before the request gives call to `currency-exchange-service` for e.g. setting up security for authenticated calls.
Once the call is made it is assigned a unique id or request number via `Zipkin` then the `currency-calculation-service` gives call to `currency-exchange-service` to get the conversion multiple. Based on the number of instances UP for `currency-exchange-service` on different ports, `Ribbon` distributes those request to proper instances to get the response. `Ribbion` picks the `currency-exchange-service` name from the `netflix-eureka-naming-server` as all the micro services are registered on `netflix-eureka-naming-server` with the application name. `netflix-eureka-naming-server` shows the list services which are UP and running with all their instances and port numbers. We do have some logging in controller of each micro service which help `Zipkin` to trace a request. Once the response is sent back to calling service the result is in plain JSON format. All the logs are sent on `Rabbit MQ` and via `Zipkin` we can see the how request went from one service to another in `Zipkin` UI.


### Ports

| Application          | Port |
|:---------------------|:------|
| Limit Service | 8080,8081...|
| Spring Cloud Config Sever| 8888|
| Currency Exchange Service | 8000,8001...|
| Currency Conversion Serice | 8100,8101...|
| Netflix Eureka Naming Sever | 8761 |
| Netflix Zuul API Gateway | 8765 |
| Zipkin Distributed Tracing Server | 9411 |

### Ports

| Application          | URL |
|:---------------------|:------|
| Limit Service | http://localhost:8080/limits POST -> http://localhost:8080/actuator/refresh |
| Spring Cloud Config Sever| http://localhost:8888/limits-service/default http://localhost:8888/limits-service/dev |
| Currency Converter Service - Direct Call | http://localhost:8100/currency-converter/from/USD/to/INR/quantity/10 |
| Currency Converter Service - Feign | http://localhost:8100/currency-converter-feign/from/EUR/to/INR/quantity/10000 |
| Currency Exchange Service | http://localhost:8000/currency-exchange/from/EUR/to/INR http://localhost:8001/currency-exchange/from/USD/to/INR |
| Eureka | http://localhost:8761/ |
| Zuul - Currency Exchange & Exchange Services | http://localhost:8765/currency-exchange-service/currency-exchange/from/EUR/to/INR http://localhost:8765/currency-conversion-service/currency-converter-feign/from/USD/to/INR/quantity/10 |
| Zipkin | http://localhost:9411/zipkin/ |
| Spring Cloud Bus Refresh | http://localhost:8080/bus/refresh |

