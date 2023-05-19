## Step by step

1. Execute gradle build for customer-service, orders-service & product-service.
2. In ~/IdeaProjects/webbshop-services-repo (IntelliJ Git Bash terminal window). Run: docker-compose up -d
3. Wait a few minutes for the mysql-container to start up. It is ready when the log outputs /.../ready for connections/.../ . The services will keep restarting until they succeed to connect to mySql.
4. Try it out via http://localhost:8080/product/all & http://localhost:8081/order/all & http://localhost:8082/customer/all