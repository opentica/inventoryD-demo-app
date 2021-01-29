kubectl apply -f productservice-deployment.yaml
kubectl apply -f productservice-service.yaml
kubectl apply -f customerservice-deployment.yaml
kubectl apply -f customerservice-service.yaml
kubectl apply -f purchaseservice-deployment.yaml
kubectl apply -f purchaseservice-service.yaml
kubectl apply -f paymentservice-deployment.yaml
kubectl apply -f paymentservice-service.yaml
kubectl apply -f inventoryservice-deployment.yaml
kubectl apply -f inventoryservice-service.yaml
echo "Waiting for few min so that all services start properly..."
sleep 3m
kubectl apply -f inventory-ingress.yaml
