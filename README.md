# Microservices
- Microservice is a design pattern in which a large application is broken into smaller individual self independent applications.
- These applications runs on seperate servers in most cases in production environment and talk with each other using REST APIs.
- Traditionally, application is made using monolith architecture where all features and services are wrapped inside a single application while in microservice, they are seperated and runs on different server systems.
- For example: Consider an e-Commere application where we have different modules like product search, order management, cart management, payment management etc. We can have microservices for each of these services.


## Why Microservice
- Seperate independent deployed business logic
- easy to scale up only specific part of whole application instead of scaling up whole application (biggest problem with monoliths)
- diff microservice can use different tools and technology for implementation since microservice communicate over REST calls which are independent of underlying tech used.
- Easy debugging and maintenance, crash/failure of one microservice doesn't breaks down whole system.
- faster application development because of seperated individual applications


## Microservice Basic execution flow
![microservice-execution-flow](https://github.com/akgarg0472/MicroServices/blob/main/Microservice-1.png?raw=true")
- Client makes a request to specific URL (assuming /payment to API gateway). API gateway scans request and checks if there exists microservice for requested url. If exists then redirect request to the microservice otherwise throws error.
- API gateway is a server that know where does a microservice is running. It is also known as Discovery server. It's like an entry point into the application that keeps track of each microservice registered with it.
- Each microservice register itself on Discovery server.


## Microservices with Spring Boot
- Spring is one of the most used and feature rich framework when it comes to the microservice development. 
- Spring provides hell lot of features and libraries to work with microservices development.
- Each microservice is a complete spring boot in itself.
- For API gateway, we have Eureka Server library by netflix. Actually API gateway is a concept which is implemented by the Eurea Server library.
- To make API discoverable on Eureka Server, we have to make our microservices Eureka Client using Eureka Client library and give eureka server url to its properties.
- To make REST calls, spring provides [RestTemplate](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/client/RestTemplate.html) class which consists of methods to create different types of Http requests.
- If we want to make asynchronous API calls as well as synchronous API calls, we can use Spring reactive web programming using [WebClients](https://docs.spring.io/spring-boot/docs/2.0.3.RELEASE/reference/html/boot-features-webclient.html).


## Fault tolerance
- We have circuit breaker mechanism to temporary suspend calls to the microservice that is not performing well or it is down due to any reason. 
- Spring provides Spring Cloud Circuit Breaker that includes the implementations of some of popular circuit breakers such as Hystrix by netflix, Resilience4J etc. 
- Configure classes and methods to handle faults. [Official docs](https://spring.io/projects/spring-cloud-circuitbreaker)


### Reference links
- [Spring Microservice blog](https://spring.io/blog/2015/07/14/microservices-with-spring)
- [Spring Cloud](https://spring.io/projects/spring-cloud)
- [Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html)
- [Securing REST API](https://dzone.com/articles/build-secure-microservices-in-your-spring-rest-api)
- [Securing REST APIs using JWT](https://medium.com/@mool.smreeti/microservices-with-spring-boot-authentication-with-jwt-and-spring-security-6e10155d9db0)
