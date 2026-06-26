# 🎓 KnowledgeHub

A centralized study management platform built with **Spring Boot** and **Kotlin** that helps university students organize their academic life in one place.

Instead of keeping notes in multiple applications, assignments in calendars, lecture slides in folders, and study resources scattered across different platforms, **KnowledgeHub** provides a single application where students can manage everything related to their university courses.

---

## 📖 Overview

KnowledgeHub is a full-stack project currently under active development.

The backend is built using a layered Spring Boot architecture and exposes a RESTful API for managing students, courses, study notes, assignments, and course progress.

Future versions will include authentication, file uploads, reminders, search functionality, and a modern React frontend.

---

## ✨ Current Features

### 👨‍🎓 Student Management

* Create students
* View student profiles
* Update student information
* Delete students

### 📚 Course Management

* Create courses
* List all courses belonging to a student
* Update course information
* Delete courses

### 📝 Study Notes

Each course contains its own collection of study notes.

Features include:

* Create notes
* View notes
* Update notes
* Delete notes

### 📋 Assignment Management

Assignments belong to a course and support:

* Due dates
* Priority
* Status tracking
* CRUD operations

### 📊 Course Progress

KnowledgeHub automatically calculates course statistics including:

* Number of study notes
* Number of assignments
* Completed assignments
* Overall completion percentage

---

## 🏗️ Architecture

The project follows a layered architecture:

```text
REST API
     │
     ▼
Controllers
     │
     ▼
Services
     │
     ▼
Repositories
     │
     ▼
Database
```

Each module follows the same structure:

```text
Entity
│
├── Repository
├── Service
├── DTOs
└── REST API
```

---

## 📁 Project Structure

```text
student/
course/
studyNote/
assignment/
util/
config/
```

---

## 🚀 Technologies

### Backend

* Kotlin
* Spring Boot
* Spring Data JPA
* Hibernate
* REST API
* Gradle

### Database

* H2 Database (Development)

### Development Tools

* IntelliJ IDEA
* Postman
* Git
* GitHub

---

## 📡 REST API

### Students

```
POST   /api/v1/students
GET    /api/v1/students
GET    /api/v1/students/{id}
PUT    /api/v1/students/{id}
DELETE /api/v1/students/{id}
```

### Courses

```
POST   /api/v1/students/{studentId}/courses
GET    /api/v1/students/{studentId}/courses
GET    /api/v1/students/{studentId}/courses/{id}
PUT    /api/v1/students/{studentId}/courses/{id}
DELETE /api/v1/students/{studentId}/courses/{id}
```

### Study Notes

```
POST   /api/v1/students/{studentId}/courses/{courseId}/study-notes
GET    /api/v1/students/{studentId}/courses/{courseId}/study-notes
GET    /api/v1/students/{studentId}/courses/{courseId}/study-notes/{id}
PUT    /api/v1/students/{studentId}/courses/{courseId}/study-notes/{id}
DELETE /api/v1/students/{studentId}/courses/{courseId}/study-notes/{id}
```

### Assignments

```
POST   /api/v1/students/{studentId}/courses/{courseId}/assignments
GET    /api/v1/students/{studentId}/courses/{courseId}/assignments
GET    /api/v1/students/{studentId}/courses/{courseId}/assignments/{id}
PUT    /api/v1/students/{studentId}/courses/{courseId}/assignments/{id}
DELETE /api/v1/students/{studentId}/courses/{courseId}/assignments/{id}
```

### Course Progress

```
GET /api/v1/students/{studentId}/courses/{courseId}/progress
```

---

## 🔮 Planned Features

* JWT Authentication
* Spring Security
* PostgreSQL
* File Uploads
* Search
* Tags
* Favorites
* Calendar
* Reminders
* Dashboard
* Statistics
* Swagger / OpenAPI Documentation
* Docker
* React Frontend
* Responsive Design

---

## 📷 Screenshots

Screenshots will be added as the frontend is developed.

---

## 👩‍💻 Author

**Layla Abdallah**

Computer Science Student

German Jordanian University (GJU)

Exchange Semester at Technische Hochschule Mittelhessen (THM)

---

## 📄 License

This project is intended for educational and portfolio purposes.
