# Reelix

A Kotlin Multiplatform application targeting Android and iOS, designed for browsing and managing movie catalogs. This project demonstrates modern mobile development practices, utilizing a single shared codebase for the UI, business logic, and data layers.

## Features

* **Movie Discovery:** Fetching and displaying real-time trending movies and top-rated films using the TMDB API.
* **Detailed Information:** Comprehensive movie details, including overviews, ratings, cast profiles, and embedded trailers.
* **Search and Filtering:** Finding specific movies by title, genre, or release year.
* **Offline Capabilities:** Local caching and a "Favorites" system that allows users to save and view movies without an internet connection.

## Architecture and Technologies

The application is built with scalability and maintainability in mind, adhering to clean architecture principles and a Unidirectional Data Flow (UDF).

* **Framework:** Kotlin Multiplatform (KMP)
* **UI:** Compose Multiplatform with custom edge-to-edge layouts and glassmorphism effects.
* **Architecture:** Model-View-ViewModel (MVVM) pattern.
* **Concurrency:** Kotlin Coroutines and `StateFlow` for reactive state management.
* **Networking:** Ktor Client and `kotlinx.serialization` for consuming remote REST APIs.
* **Dependency Injection:** Koin for decoupled component management and automated dependency resolution.
* **Persistence:** Local database (Room/SQLDelight) for offline caching.
* **Data Handling:** Repository pattern used as a single source of truth to orchestrate data synchronization between the remote API and the local database.


### Build and Run Android Application

To build and run the development version of the Android app, use the run configuration from the run widget
in your IDE’s toolbar or build it directly from the terminal:
- on macOS/Linux
  ```shell
  ./gradlew :composeApp:assembleDebug
  ```
- on Windows
  ```shell
  .\gradlew.bat :composeApp:assembleDebug
  ```

### Build and Run iOS Application

To build and run the development version of the iOS app, use the run configuration from the run widget
in your IDE’s toolbar or open the [/iosApp](./iosApp) directory in Xcode and run it from there.

---
Architected and developed by [bohDawn](https://github.com/bohDawn)
