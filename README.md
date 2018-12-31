How to run the project?
- Import maven project in the eclipse
- Change the database configuration as per your need in the application.properties

To tese API's we can use postman:

Get all products : GET method wtih URL http://localhost:8080/api/products

Add Product: POST method with http://localhost:8080/api/addProduct
Request Body:

{
"productName":"IPhone",
"productDescription":"Phone",
"manufacturerName":"Apple",
"manufacturerId":2,
"sellerName":"DigitalShopee",
"sellerId":2
}

Delete Specific Product: 
{
"productId": 4,
"productName":"IPhone",
"productDescription":"Phone",
"manufacturerName":"Apple",
"manufacturerId":2,
"sellerName":"DigitalShopee",
"sellerId":2
}

Delete All Products: Delete method with URL http://localhost:8080/api/deleteAll