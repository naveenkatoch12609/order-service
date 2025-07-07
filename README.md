# 🛒 Order Service - Spring Boot Microservice

A simple microservice for order management, built using **Spring Boot**, containerized with **Docker**, deployed on **Kubernetes** using **Helm**, and automated via **GitHub Actions** & **ArgoCD** for GitOps.

---

## 📌 Features

- REST API for order service (Spring Boot)
- PostgreSQL as the backend DB
- Dockerized application
- Kubernetes deployment using YAML & Helm
- GitHub Actions CI/CD pipeline
- GitOps deployment with ArgoCD

---

## 🧰 Tech Stack

| Tech             | Role                              |
|------------------|-----------------------------------|
| Java 17          | Application runtime               |
| Spring Boot      | RESTful API microservice          |
| PostgreSQL       | Database                          |
| Docker           | Containerization                  |
| Minikube         | Local Kubernetes cluster          |
| Kubernetes (k8s) | Container orchestration           |
| Helm             | Kubernetes package manager        |
| GitHub Actions   | Continuous integration & delivery |
| ArgoCD           | GitOps-based CD (optional)        |



---

## 🛠️ Setup Instructions

### 📁 Clone the repository

```bash
git clone https://github.com/naveenkatoch61/order-service.git
cd order-service

🧹 Cleanup
helm uninstall order-service
kubectl delete -f k8s/
minikube delete

👨‍💻 Author
Naveen K