# Language Learning Web App

This project is a language learning web app built with **Spring Boot**. It offers lessons and exercises to users, tracks progress, and provides scoring based on user answers.

## Features
- **User Authentication**: Register, login, and JWT-based authorization.
- **Lessons**: Users can access lessons with exercises.
- **Exercises**: Multiple-choice exercises for vocabulary, grammar, pronunciation, etc.
- **Progress Tracking**: Track user progress in each lesson with score and time spent.
- **Admin Features**: Admins can manage lessons, exercises, and user progress.

## Technologies
- **Backend**: Spring Boot, Spring Security (JWT)
- **Database**: MySQL (or any relational DB)
- **Frontend**: (Optional for now)

## Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/roxasender/mindspring.git
   ```

2. Configure `application.properties` for your database.

3. Build and run the project:
   ```bash
   ./mvnw spring-boot:run
   ```

4. Access the app via `http://localhost:8080`.

## License
This project is licensed under the MIT License.
