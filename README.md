# Course Planner API

A Java Spring Boot REST API that helps students manage courses, create semester plans, validate prerequisites, calculate total credits, and suggest eligible courses based on completed prerequisites.

This project was built to practice backend development using Java, Spring Boot, REST APIs, JPA repositories, and an H2 in-memory database.

## Features

- Add new courses with course code, title, credits, and prerequisites
- View all saved courses
- Search for a course by course code
- Delete courses
- Create semester plans using selected course codes
- Calculate total credits for a semester plan
- Detect missing prerequisites in a semester plan
- Suggest eligible courses based on completed courses

## Technologies Used

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 Database
- Maven
- Postman
- Eclipse IDE

## Project Structure

```text
src/main/java/com/rafi/courseplanner
├── controller
│   ├── CourseController.java
│   └── PlanController.java
├── dto
│   ├── CourseRequest.java
│   ├── CourseResponse.java
│   ├── PlanRequest.java
│   └── PlanResponse.java
├── model
│   ├── Course.java
│   └── SemesterPlan.java
├── repository
│   ├── CourseRepository.java
│   └── SemesterPlanRepository.java
├── service
│   ├── CourseService.java
│   └── PlanService.java
└── CoursePlannerApiApplication.java
