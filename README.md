# PerpusHub Bandung

Unified platform for cross-library book search and lending in Bandung.

## Screenshots

<img src="https://github.com/user-attachments/assets/72926197-9123-425c-846a-ef3d9f3d685e" />
<img src="https://github.com/user-attachments/assets/6d5bf31d-8053-404d-ae78-df3d04a862b1" />
<img src="https://github.com/user-attachments/assets/7b697836-738a-44f0-bb73-c0352cec55d7" />
<img src="https://github.com/user-attachments/assets/dab743ca-a470-4ba4-a91a-4a6e0e88992e" />
<img src="https://github.com/user-attachments/assets/e7b4ab95-5bd0-48a1-aca9-d08391f801fb" />

## Technologies Used

**Languages:**

* Kotlin

**Frameworks/Libraries:**

* Compose Multiplatform
* ViewModel
* StateFlow
* Coroutines

**Dependency Injection:**

* Koin

**Services:**

* MapTiler

## How to Run

This frontend depends on a separate backend service. Before running the app:

- Ensure the backend is running: https://github.com/Rubricate12/perpushub-bandung-api
- Add the required values to your `local.properties` file:
  - backend.base.url=your-backend-url
  - maptiler.api.key=your-maptiler-key

Then continue with the setup steps below.

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

### Build and Run Desktop (JVM) Application

To build and run the development version of the desktop app, use the run configuration from the run widget
in your IDE’s toolbar or run it directly from the terminal:
- on macOS/Linux
  ```shell
  ./gradlew :composeApp:run
  ```
- on Windows
  ```shell
  .\gradlew.bat :composeApp:run
  ```

### Build and Run Web Application

To build and run the development version of the web app, use the run configuration from the run widget
in your IDE's toolbar or run it directly from the terminal:
- on macOS/Linux
  ```shell
  ./gradlew :composeApp:wasmJsBrowserDevelopmentRun
  ```
- on Windows
  ```shell
  .\gradlew.bat :composeApp:wasmJsBrowserDevelopmentRun
  ```

### Build and Run iOS Application

To build and run the development version of the iOS app, use the run configuration from the run widget
in your IDE’s toolbar or open the [/iosApp](./iosApp) directory in Xcode and run it from there.
