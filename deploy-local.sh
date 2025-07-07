#!/bin/bash

set -e

echo "🔧 Setting Docker env to Minikube..."
eval $(minikube docker-env)

echo "🐳 Building Docker image into Minikube Docker..."
docker build -t order-service:latest .

echo "🚀 Restarting Kubernetes deployment..."
kubectl rollout restart deployment order-service

echo "✅ Done. Check status with: kubectl get pods"
