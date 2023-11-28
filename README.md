# Database Service

This service provides the capability to execute queries to databases using configurations stored in the `ds.yaml` file.

## Getting Started

Follow these steps to run the service:

1. **Clone the repository:**
    ```
   git clone https://github.com/your-username/database-service.git
   cd database-service
   ```

2. **Install dependencies:**
   ```
   mvn clean install
   ```

3. **Run the service:**
   ```
   mvn spring-boot:run
   ```

## Configuration

Database configurations are stored in the `ds.yaml` file.
Configure the database settings according to your requirements.

Usage
The service provides a REST API for executing queries to databases. Example requests:

Get all users:

curl -X GET http://localhost:8080/users
Get user by ID:
```
curl -X GET http://localhost:8080/users/{id}
```
Filter users by Username:
```
curl -X GET http://localhost:8080/users?username={username}
```

Filter users by Surname:
```
curl -X GET http://localhost:8080/users?surname={surname}
```
Filter users by Name:
```
curl -X GET http://localhost:8080/users?name={name}
```
