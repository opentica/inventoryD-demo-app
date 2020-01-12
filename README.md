
# Download Java App Agent
Download AppServerAgen.zip file from the controller getting started Screen 

Copy this AppServerAgen.zip in inventoryservice, paymentservice & purchaseservice

# Build and start the application


```
./build_deploy.sh
```

Above command will build all applications and deploy containers


# Application: inventoryservice
- This application is to maintain the inventory of the application using CRUD operations

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
Puchase a Specific Product: POST Method with http://localhost:8080/api/product/purchase
```
{
	"productId": "1",
	"customerId": "C1",
	"price": 1200.5
}
```
Pay for a specific Product: POST Method with http://localhost:8080/api/product/payment
```
{
	"productId": "1",
	"price": 1200.5
}
```
Delete All Products: Delete method with URL http://localhost:8080/api/product/deleteAll

# Application: purchaseservice
- This application is to purchase the product
- This service internally consumes 'paymentservice'

Purchase Product: POST method with http://localhost:50822/purchaseservice/purchase
Request Body:
```
{
	"productId": "A123",
	"payment": 1000
}
```

# Application: purchaseservice
- This application is to purchase the product
- This service internally consumes 'paymentservice'

Pay for Product: POST method with http://localhost:50821/payservice/pay
Request Body:
```
{
	"productId": "A125",
	"payment": 1500
}
```

# To Build the project from source file
- Import maven project in the eclipse
- Change the database configuration as per your need in the application.properties
- Build the project using maven/eclipse

Note:
- Copy this AppServerAgen.zip in inventoryservice, paymentservice & purchaseservice
- If there is issue around docker build on Windows platform change the file formatting for docker-compose.yml from dos2unix utility