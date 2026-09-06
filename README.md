# Modern Android Todo Application

[![Android CI](https://github.com/owner/repo/actions/workflows/ci.yml/badge.svg)](https://github.com/owner/repo/actions/workflows/ci.yml)
![Kotlin](https://img.shields.io/badge/Kotlin-2.0.21-purple.svg)
![Android SDK](https://img.shields.io/badge/API-24%2B-green.svg)
![Architecture](https://img.shields.io/badge/Architecture-MVVM%20%2B%20Repository-blue.svg)

A modern, offline-first Android application for managing to-do items, built with **Jetpack Compose**, **Kotlin Coroutines / Flow**, **Room Local Database**, and **Retrofit REST Client**.

---

## 📱 Features & Highlights

- **Offline-First Synchronization**: Persists to-do items locally using Room DAO and syncs with remote JSONPlaceholder REST API endpoints via Retrofit.
- **Modern UI Stack**: User interface fully built with Jetpack Compose and Material 3 components.
- **Declarative Navigation**: Screen transitions managed cleanly with `navigation-compose`.
- **Reactive State Management**: Uses Jetpack ViewModels paired with Kotlin StateFlow for reactive UI state updates.
- **Clean Architecture**: Clear layer separation following MVVM + Repository patterns.

---

## 🏗️ Architecture Overview

The app follows recommended Android Architecture Guidelines using clean architecture principles:

```
┌────────────────────────────────────────────────────────┐
│                        UI Layer                        │
│   TodoListScreen / TodoDetailScreen / Jetpack Compose  │
└───────────────────────────┬────────────────────────────┘
                            │ State & Events
┌───────────────────────────▼────────────────────────────┐
│                      ViewModel Layer                   │
│         TodoListViewModel / TodoDetailViewModel         │
└───────────────────────────┬────────────────────────────┘
                            │ Reactive Streams (Flow)
┌───────────────────────────▼────────────────────────────┐
│                      Repository Layer                  │
│                        TodoRepository                  │
└─────────────────────┬───────────────────┬──────────────┘
                      │                   │
         ┌────────────▼───────┐   ┌───────▼────────────┐
         │   Local Data       │   │   Remote Data      │
         │ (Room DB / SQLite) │   │ (Retrofit REST API)│
         └────────────────────┘   └────────────────────┘
```

---

## 🛠️ Technology Stack

- **Language**: Kotlin 2.0
- **UI Framework**: Jetpack Compose (Material 3)
- **Asynchronous / Concurrency**: Kotlin Coroutines & Flow
- **Local Database**: Room Database (SQLite)
- **Networking**: Retrofit 2 & OkHttp 3 & Gson
- **Dependency Injection**: Manual Repository Injection
- **Build Tool**: Gradle (Kotlin DSL)

---

## 📂 Project Structure

```
.
├── .github/
│   └── workflows/
│       └── ci.yml          # GitHub Actions Continuous Integration pipeline
└── todoapp/                # Root Android project directory
    ├── app/
    │   ├── src/
    │   │   ├── main/
    │   │   │   ├── java/com/example/todoapp/
    │   │   │   │   ├── data/
    │   │   │   │   │   ├── local/       # Room Entities, DAO, Database
    │   │   │   │   │   ├── remote/      # Retrofit API interface & DTOs
    │   │   │   │   │   └── TodoRepository.kt
    │   │   │   │   ├── model/           # UI Domain models
    │   │   │   │   ├── ui/              # Compose screens & Navigation graph
    │   │   │   │   │   └── theme/       # Material 3 Theme definition
    │   │   │   │   └── viewmodel/       # ViewModels
    │   │   │   └── AndroidManifest.xml
    │   │   └── test/                    # Unit tests
    │   └── build.gradle.kts
    ├── build.gradle.kts
    └── gradlew
```

---

## 🚀 Getting Started

### Prerequisites

- **Android Studio**: Ladybug / Koala or newer recommended.
- **JDK**: Java Development Kit 17 or higher.
- **Android SDK**: API Level 34 (Target/Compile) and Minimum API Level 24.

### Building & Running Locally

1. **Clone the repository**:
   ```bash
   git clone <repository-url>
   cd <repository-directory>/todoapp
   ```

2. **Grant execution permissions to Gradle wrapper**:
   ```bash
   chmod +x gradlew
   ```

3. **Build the Debug APK**:
   ```bash
   ./gradlew assembleDebug
   ```
   The generated APK will be located at `app/build/outputs/apk/debug/app-debug.apk`.

4. **Run Unit Tests**:
   ```bash
   ./gradlew test
   ```

---

## 🔄 CI/CD Pipeline

Automated build and test validations run on every push and pull request via GitHub Actions (`.github/workflows/ci.yml`). The workflow:

1. Sets up JDK 17 environment.
2. Caches Gradle dependencies for optimized build times.
3. Executes unit tests (`./gradlew test`).
4. Assembles the debug APK (`./gradlew assembleDebug`).

---

## 📄 License

This repository is maintained for portfolio demonstration purposes.
