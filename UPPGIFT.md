# Group Assignment

The goal with this course is to manage microservices that communicates with other services and databases. For that purpose we use container technology by docker and k8s. We also integration-test the software in a microservice setup.

## Part 1

### Week 20 - Create microservices

In backend 1 you started to build a monolith, were all services is build closely coupled. We will now in backend 2 separate (decouple) it to multiple services.

*Simplifications* We aren't required to have 100% feature parity between the systems, so you may loose some features,  compared to your previous solution. You may also use a single database instance running, i.e in docker or installed. In this course we aren't required to cover the frontend, so if it's tricky a API REST call is good enough.

1. Decide 3 services that you will create from your monolith, try to motivate your decision.
    - Is it possible to create good understandable API:s
    - Does the database/Schema make sense?
    - Is it possible to solve business challenges withing the service?

2. Create separate spring project for each service when working in a small team it's good to:
    - Have a standard setup, use the same settings in spring initializer
    - A naming convention
    - Same code structure, folders etc

3. Create a Dockerfile for each service

4. Build and push to ghcr.io

### Week 21 - Test microservices

1. Extend one service of your choice to make use of another service API. I.e fetching data about a customer or a product
2. Deploy the services in a cluster setup manually mapped spring boots with ports, docker compose,  or k8s in minikube
2. Create a integration test that verifies that the services can communicate.

# Part 2 - Security

Week 21 - 22

    No valid password should be committed to git
    One of your services should require a login, with spring security
    Create a integration test that verifies the login

VG

You must complete 2 of 3 VG tasks and the integrations tests.

    Document your services in a professional manner with for example OpenAPI 3 springdoc

    Implement error handling for the service communication, ideas on what to handle:
        Rest client calls that takes to long time may break the system, modify your connect and read timeout.
        If a client call fails, retry it maximal 3 times. Write code or use for example spring-retry
        Write error handling for RestTemplates:
            HttpServerErrorException
            HttpClientErrorException

    Implement Spring Validation for the restcontroller and database.

Mandatory: Show how your code handles the VG tasks by creating integrations tests in for example postman.
Hand-in Instructions

    Download your project as a zip from github and upload it to studentportalen
    Export or Screenshot the tests, compress it as a zip and upload it to studentportalen
    Present your solution on the last course day
