## Step by step

1. Execute gradle build for customer-service, orders-service & product-service. (1)
2. In ~/IdeaProjects/webbshop-services-repo (IntelliJ Git Bash terminal window). Run: docker-compose up -d
3. Wait a few minutes for the mysql-container to start up. It is ready when the log outputs /.../ready for connections/.../ . The services will keep restarting until they succeed to connect to mySql.
4. Try it out via http://localhost:8080/product/all & http://localhost:8081/order/all & http://localhost:8082/customer/all


(1): För mig (K) bråkar gradle build. Men det lirar om jag kör genom GUI:n.
![gradle build gui](https://files.catbox.moe/mnt7vq.png)



## Run in Postman links

### Customer testing
[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/27678778-a9193de4-78c0-44f1-82fc-012cef547b5f?action=collection%2Ffork&source=rip_markdown&collection-url=entityId%3D27678778-a9193de4-78c0-44f1-82fc-012cef547b5f%26entityType%3Dcollection%26workspaceId%3Dc15fe9c9-550c-478a-b18a-823e8a9ae1c8)

### Order testing
[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/27678778-0813b98e-c25d-40f2-b784-d3f9741965a7?action=collection%2Ffork&source=rip_markdown&collection-url=entityId%3D27678778-0813b98e-c25d-40f2-b784-d3f9741965a7%26entityType%3Dcollection%26workspaceId%3Dc15fe9c9-550c-478a-b18a-823e8a9ae1c8)

### Product testing
[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/27678778-f362a5ee-adf4-4462-a66b-3b965dea171f?action=collection%2Ffork&source=rip_markdown&collection-url=entityId%3D27678778-f362a5ee-adf4-4462-a66b-3b965dea171f%26entityType%3Dcollection%26workspaceId%3Dc15fe9c9-550c-478a-b18a-823e8a9ae1c8)