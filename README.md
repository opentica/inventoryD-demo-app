
# Download Java App Agent
Download AppServerAgen.zip file from the controller getting started Screen 

# Build and start the application


```
docker-compose up --build
```



# Test App Rest APIs

Get all products : GET method wtih URL http://localhost:8080/api/product/getProducts

Add Product: POST method with http://localhost:8080/api/product/addProduct
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
Delete Specific Product: DELETE Method with http://localhost:8080/api/product/deleteProduct
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

Delete All Products: Delete method with URL http://localhost:8080/api/product/deleteAll


# To Build the project from source file
- Import maven project in the eclipse
- Change the database configuration as per your need in the application.properties
- Build the project using maven/eclipse
