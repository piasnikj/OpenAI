# OpenAI JavaFX Application

This is a simple JavaFX application that communicates with OpenAI APIs. The application features a ChatGPT-like interface where users can enter a prompt and receive responses from the API. The results of each query made to the OpenAI API are stored in an SQLite database.

## Features

1. Java 17 with JavaFX for the front-end.
2. Gradle with Kotlin DSL for building the application.
3. Model-View-Controller (MVC) design pattern for each screen.
4. SQLite database for storing API responses.
5. Adherence to design patterns from "Head First Design Patterns" as necessary.

## Prerequisites

- Java 17 JDK
- Gradle

## Setup

1. Clone the repository:
   ```
   git clone https://github.com/piasnikj/OpenAI.git
   cd OpenAI
   ```

2. Set the `JAVA_HOME` environment variable to your Java 17 JDK installation path.

3. Add your OpenAI API key to a configuration file or as an environment variable.

4. Build and run the application:
`gradle run`

#### Launch Configuration
5. Set up the following VM Options:
    ```
    --module-path
    C:\javafx-sdk-17.0.1\lib
    --module-path=C:\javafx-sdk-17.0.1\lib ## System Dependent
    --add-modules=javafx.base
    --add-modules=javafx.controls
    --add-modules=javafx.fxml
    --add-modules=javafx.graphics
    --add-modules=javafx.media
    --add-modules=javafx.swing
    --add-exports=javafx.base/com.sun.javafx.event=ALL-UNNAMED
    ```


## Usage

1. Enter a prompt in the text field at the bottom of the screen.
2. Click the "Send" button or press "Enter" to submit the prompt.
3. The application will display the API response in the scrollable area above the prompt section.
4. Each query made to the OpenAI API will be stored in the SQLite database.

