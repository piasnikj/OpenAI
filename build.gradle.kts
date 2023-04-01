/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Java application project to get you started.
 * For more details take a look at the 'Building Java & JVM projects' chapter in the Gradle
 * User Manual available at https://docs.gradle.org/8.0/userguide/building_java_projects.html
 */

plugins {
    // Apply the application plugin to add support for building a CLI application in Java.
    application

    id("org.openjfx.javafxplugin") version "0.0.9"
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    // Use JUnit test framework.
    testImplementation("junit:junit:4.13.2")

    // This dependency is used by the application.
    implementation("com.google.guava:guava:31.1-jre")

    implementation("org.openjfx:javafx-controls:17.0.0.1")
    implementation("org.openjfx:javafx-fxml:17.0.0.1")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("org.xerial:sqlite-jdbc:3.34.0")

    implementation("com.konghq:unirest-java:3.13.10")
    implementation("org.yaml:snakeyaml:1.29")

    implementation ("com.fasterxml.jackson.core:jackson-core:2.13.0")
    implementation ("com.fasterxml.jackson.core:jackson-databind:2.13.0")

}

javafx {
    version = "17.0.0.1"
    modules("javafx.controls", "javafx.fxml")
}

application {
    // Define the main class for the application.
    mainClass.set("org.pias.openai.OpenAI")
}
