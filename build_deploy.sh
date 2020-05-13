cd inventoryservice 
mvn install
cd ..
cd paymentservice 
mvn install
cd ..
cd purchaseservice 
mvn install
cd ..
cd productservice 
mvn install
cd ..
cd customerservice 
mvn install
cd ..

docker-compose up --build
