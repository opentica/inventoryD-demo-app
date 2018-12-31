# Build the project
- Import maven project in the eclipse
- Change the database configuration as per your need in the application.properties
- Build the project using maven/eclipse

# MySQL Docker Image

## Download MySQL Server Docker Image 
```
docker pull mysql/mysql-server:5.7
```

## Start a new docker container
```
docker run --name=mysql-standalone -p 3306:3306 -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=inventoryDB -e MYSQL_USER=opentica -e MYSQL_PASSWORD=password -d mysql/mysql-server:5.7
```
# spring-boot Docker Image

## Build spring-boot Docker Image

```
docker build . -t opentica-spring-boot-demo
```

## Start a sprint-boot container 
```
docker run -p 8080:8080 --name opentica-spring-boot-demo --link mysql-standalone -d opentica-spring-boot-demo
```



# Test App Rest APIs

Get all products : GET method wtih URL http://localhost:8080/api/products

Add Product: POST method with http://localhost:8080/api/addProduct
Request Body:
```
{
"productName":"IPhone",
"productDescription":"Phone",
"manufacturerName":"Apple",
"manufacturerId":2,
"sellerName":"DigitalShopee",
"sellerId":2
}
```
Delete Specific Product:
```
{
"productId": 4,
"productName":"IPhone",
"productDescription":"Phone",
"manufacturerName":"Apple",
"manufacturerId":2,
"sellerName":"DigitalShopee",
"sellerId":2
}
```

Delete All Products: Delete method with URL http://localhost:8080/api/deleteAll
