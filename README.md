# **ARekikGT-JEE_Ateliers**

## **Project Overview**

**ARekikGT-JEE_Ateliers** is a Java-based web application developed using **Spring Boot**, **Spring Security**, and **JWT (JSON Web Token)** for user authentication and authorization. The application demonstrates best practices for implementing RESTful APIs with a secure authentication and role-based access control mechanism.

This project is organized as part of an educational workshop series focusing on Java Enterprise Edition (JEE) application development.

---

## **Features**
- **User Authentication:** Login functionality using JWT for secure authentication.
- **Role-Based Access Control:** Access APIs based on roles (e.g., ADMIN, USER).
- **Secure Token Management:** Implements `JWTAuthenticationFilter` and `JWTAuthorizationFilter` for handling token generation and validation.
- **CRUD Operations:** Basic REST APIs for user management.
- **Database Integration:** Uses MySQL as the database for persisting user and role information.

---

## **Technologies Used**
- **Spring Boot:** Framework for rapid development of Java applications.
- **Spring Security:** Provides authentication and authorization.
- **JWT:** For stateless authentication.
- **MySQL:** Database for persisting user and role data.
- **Maven:** Dependency management and build automation tool.
- **Lombok:** Simplifies Java boilerplate code with annotations.

---

## **Getting Started**

### **Prerequisites**
- Java 17 (or compatible version)
- Maven
- MySQL Server
- Git

### **Setup Instructions**

1. **Clone the Repository**
   ```bash
   git clone https://github.com/yourusername/ARekikGT-JEE_Ateliers.git
   cd ARekikGT-JEE_Ateliers
2. **Clone the Repository**
   - Create a database in MySQL:
   ```bash
   CREATE DATABASE atelier_db;
- Update src/main/resources/application.properties with your database credentials:
   ```bash
   spring.datasource.url=jdbc:mysql://localhost:3306/atelier_db
   spring.datasource.username=YOUR_USERNAME
   spring.datasource.password=YOUR_PASSWORD
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true

3. **Build the Project**
   - Start the application using:
   ```bash
   mvn clean install
4. **Run the Application**
   - Start the application using:
   ```bash
   mvn clean install
5. **Test the Application**
  - Use a tool like Postman to test the following endpoints:
POST /login: Authenticate a user and receive a JWT token.
GET /all: Access user data (requires an ADMIN role and a valid JWT token).


Endpoints
HTTP Method	Endpoint	Description	Role Required
POST	 |/login	|Authenticate a user	    |None
GET	   |/all	  |Get a list of all users	|ADMIN
POST	 |/users	|Create a new user      	|None
POST 	 |/roles	|Create a new role	      |None


## **Known Issues**

1. **JWTAuthenticationFilter.java:** Error related to "The hierarchy of the type JWTAuthenticationFilter is inconsistent".

   - Ensure you are using the correct parent class (`UsernamePasswordAuthenticationFilter`) and method signatures.

2. **JWTAuthorizationFilter.java:** Error related to "The hierarchy of the type JWTAuthorizationFilter is inconsistent".

   - Ensure the filter extends `OncePerRequestFilter` and follows the correct method signature.
  

## **License**
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

## **Contact**
For any questions or support, please contact:
- **Ahmed Rekik**
- Email: [ahmed.rekik@ieee.org](mailto:ahmed.rekik@ieee.org)


## **Directory Structure**
 ```plaintext
src/
├── main/
│   ├── java/
│   │   └── com/
│   │         └── tn/
│   │           └── atelier/
│   │               └── ahmed_rekik/
│   │                   ├── Controller/          # REST Controllers
│   │                   ├── Entity/              # JPA Entities
│   │                   ├── Repository/          # Repositories for database access
│   │                   ├── Security/            # Security filters and configuration
│   │                   └── Service/             # Business logic and services
│   └── resources/
│       └── application.properties               # Spring Boot application properties



   
