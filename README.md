# DevOps Project - Spring Boot Backend


## Overview
This project demonstrates a full DevOps workflow using a small Spring Boot backend application. The goal is to practice **end-to-end DevOps concepts** including:

- Backend API development
- CI/CD pipeline (GitHub Actions)
- Docker containerization
- Kubernetes deployment (Kind)
- Observability (metrics, structured logs, tracing)
- Security checks (SAST + DAST)

---

## Table of Contents
1. [Project Structure](#project-structure)
2. [Prerequisites](#prerequisites)
3. [Running Locally](#running-locally)
4. [API Endpoints](#api-endpoints)
5. [Docker](#docker)
6. [Kubernetes Deployment](#kubernetes-deployment)
7. [CI/CD Pipeline](#cicd-pipeline)
8. [Observability](#observability)
9. [Security](#security)


---
## Project Structure
```bash
devops-project/
│
├─ src/main/java/com/enicarthage/devops_project/
│ ├─ controller/ # REST controllers
│ ├─ model/ # Data models
│ ├─ metrics/ # Metrics service
│
├─ Dockerfile # Docker image definition
├─ deployment.yaml # Kubernetes Deployment manifest
├─ service.yaml # Kubernetes Service manifest
├─ ci.yml # GitHub Actions CI/CD workflow
├─ README.md # Project documentation
└─ pom.xml # Maven configuration
```
---

## Prerequisites

- Java 17
- Maven
- Docker Desktop
- Kind (Kubernetes in Docker)
- kubectl CLI
- Git & GitHub account

---

## Running Locally

1. Clone the repository:

```bash
git clone https://github.com/kamkoum-sabrine/devops-project
cd devops-project
```
2. Build and run the backend:

```bash
mvn clean install
java -jar target/app.jar
```

3. Test API endpoints:
```bash
curl http://localhost:8081/health
curl http://localhost:8081/hello
curl -X POST http://localhost:8081/tasks -H "Content-Type: application/json" -d '{"title":"Test Task"}'
curl http://localhost:8081/tasks
curl http://localhost:8081/metrics
```
API Endpoints
| Endpoint   | Method | Description                                     |
| ---------- | ------ | ----------------------------------------------- |
| `/health`  | GET    | Check if service is UP                          |
| `/hello`   | GET    | Test endpoint returning a greeting              |
| `/tasks`   | GET    | List all tasks                                  |
| `/tasks`   | POST   | Create a new task                               |
| `/metrics` | GET    | Returns request count and average response time |

---
## Docker
1. Build Docker image:
   ```bash
   docker build -t devops-backend:latest .
   ```
2. Run container locally:
  ```bash
  docker run -p 8081:8081 devops-backend:latest
  ```
3. Verify container:
   ```bash
   docker ps
    ```
---
## Kubernetes Deployment
1. Load image into Kind cluster:
   ```bash
   kind load docker-image devops-backend:latest --name devops-cluster
   ```
2. Apply manifests:
  ```bash
  kubectl apply -f deployment.yaml
  kubectl apply -f service.yaml
  ```
3. Verify pods:
   ```bash
   kubectl get pods
   kubectl get svc
   ```

4.Access app via NodePort or port-forward:
  ```bash
  kubectl port-forward deployment/devops-backend 8081:8081
  curl http://localhost:8081/health
  curl http://localhost:8081/hello
  curl http://localhost:8081/metrics

  ```

----
## CI/CD Pipeline
GitHub Actions workflow (ci.yml) automatically:
  - Builds Maven project
  - Runs unit tests
  - Uploads test reports
  - Placeholder for Docker build & Kubernetes deployment

# Workflow triggers:
 - Push to  ``` develop ``` or ``` feature/* ``` branches
 - Pull requests targeting ``` develop ```

## Observability
  - Metrics
      - ``` /metrics ``` endpoint exposes:
          - ``` requestCount ```
          - ``` avgResponseTimeMs ```
  - Logs
      - Structured logs for requests and errors
      - ``` traceId ``` for correlation per request
  - Tracing
      - ``` X-Trace-Id ``` header supported
  - Enables monitoring, debugging, and performance insights in Kubernetes

## Security
- ### SAST: GitHub CodeQL scans for static security issues
- ### DAST: Runtime scan placeholders for the deployed API
- Ensures security best practices in code and deployment

## Authors:
- Sabrine KAMKOUM, ENICAR 3rd year
- Hibat Allah BEN AISSA, ENICAR 3rd year 
