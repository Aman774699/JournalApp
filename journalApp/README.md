# Journal App

Welcome to **Journal App**, a secure and feature-rich application designed to help you document your daily life while keeping you updated with the latest weather conditions. Built with **Spring Boot** and **Java**, this application focuses on performance, security, and user experience.

## 🚀 Features

### 📝 Journal Management
- **Create, Read, Update, Delete (CRUD)**: Easily manage your journal entries.
- **User-Specific Data**: Each user has their own private collection of journal entries.
- **Secure Access**: Protected endpoints ensure only authenticated users can access their data.

### 👥 User Management
- **Authentication**: Secure login and registration using **Spring Security** and **JWT**.
- **Role-Based Access**:
    - **User**: Standard access to manage own journals.
    - **Admin**: Administrative privileges to manage users.

### ⛅ Weather Integration
- **Real-Time Weather**: Get current weather updates for different cities.
- **High Performance**: Utilizes **Redis Caching** to store weather data for 5 minutes (300 seconds), reducing API calls and improving response times.

## 🛠️ Tech Stack

- **Language**: Java 17
- **Framework**: Spring Boot 3.3.3
- **Database**: MySQL 8.0
- **Caching**: Redis (with Spring Data Redis)
- **Security**: Spring Security, JWT (JSON Web Tokens)
- **Tools**: Maven, Lombok, Postman (for testing)

## ⚙️ Configuration

The application is configured using `application.properties`. ensure you have the following services running and configured:

### Database (MySQL)
Ensure your MySQL instance is running and update the connection details:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/journaldb
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
```

### Redis
Ensure your Redis server is running on the default port `6379`.
```properties
spring.redis.host=localhost
spring.redis.port=6379
```

### External APIs
Configure your Weather API key (likely OpenWeatherMap or similar) in the properties file:
```properties
API.weather.key=YOUR_API_KEY
```

## 🏃‍♂️ How to Run

1.  **Clone the repository**:
    ```bash
    git clone https://github.com/your-username/journalApp.git
    cd journalApp
    ```

2.  **Build the project**:
    ```bash
    mvn clean install
    ```

3.  **Run the application**:
    ```bash
    mvn spring-boot:run
    ```

The application will start on `http://localhost:8080` (or your configured server port).

## 🔌 API Endpoints

### Public
- `POST /public/create-user`: Register a new user.
- `GET /public/health-check`: Check system status.

### Journal (`/journal`)
- `GET /journal/getall`: Get all entries for the logged-in user.
- `GET /journal/get/{id}`: Get a specific entry by ID.
- `POST /journal/save`: Create a new journal entry.
- `PUT /journal/update/{id}`: Update an existing entry.
- `DELETE /journal/delete/{id}`: Delete an entry.

### Admin (`/admin`)
- `GET /admin/get-all-user`: Retrieve a list of all users.
- `POST /admin/New-Admin`: Create a new administrative user.

## 🤝 Contributing
Contributions, issues, and feature requests are welcome! Feel free to check the [issues page](issues).

## 📄 License
[MIT](LICENSE)
