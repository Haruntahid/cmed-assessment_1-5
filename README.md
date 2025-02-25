# CMED ASSESSMENT 01-05

## Project Overview
This repository contains five separate folders (**CMED ASSESSMENT 01-05**), each representing the same project with different datasets or implementations.

Each folder contains two main components:
- **Frontend** (React.js)
- **Backend** (Spring Boot with Gradle)

---

## Technologies Used
### Frontend:
- React.js
- Tailwind CSS

### Backend:
- Spring Boot
- Gradle (Build System)
- Swagger (API Documentation)
- H2 Database

---

## Installation and Setup
### Prerequisites
- Node.js (Recommended: v18+)
- Java (JDK 17+)
- Gradle (Ensure it's installed and configured)
- H2 Console (As per backend configuration)

### Running the Frontend
1. Navigate to the **frontend** directory inside a specific project folder (e.g., `CMED ASSESSMENT 01/frontend`).
2. Install dependencies:
   ```sh
   npm install
   ```
3. Start the development server:
   ```sh
   npm run dev
   ```
4. The React app will be available at: **http://localhost:5173**

### Running the Backend
1. Navigate to the **backend** directory inside a specific project folder (e.g., `CMED ASSESSMENT 01/backend`).
2. Build the project using Gradle:
   ```sh
   ./gradlew build
   ```
3. Run the Spring Boot application:
   ```sh
   ./gradlew bootRun
   ```
4. The backend server will be available at: **http://localhost:8080**

---

## API Documentation
- Swagger is available for API testing and documentation.
- Once the backend is running, open your browser and visit:
  ```
  http://localhost:8080/swagger-ui.html
  ```

---

## Project Structure
Each folder follows this structure:
```
CMED ASSESSMENT 01-05/
│── frontend/        # React.js Frontend Code
│── backend/         # Spring Boot Backend Code
│── README.md        # Documentation File
```

---

## Notes
- Ensure that the database credentials are correctly set in the `application.properties` file inside the backend.

