cd inventoryservice 
mvn install
cd ..
cd paymentservice 
mvn install
cd ..
cd purchaseservice 
mvn install
cd ..
docker-compose up --build