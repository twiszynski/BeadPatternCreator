![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Make it real.

---
## PngToExcel Build Script Documentation

**1. Overview:**

This Maven build script defines the structure and dependencies for the "PngToExcel" project. It specifies how to compile, package, and run the application. The script also includes configurations for JavaFX integration and logging. 

**2. Build Tool:** Maven

**3. Script/File Name:** pom.xml

**4. Detailed Documentation:**

   - **Project Information:**
     -  Description: Defines basic project information like group ID, artifact ID, version, packaging type, name, and URL.
     -  Parameters: None
     -  Important Logic: Sets up the foundation for the project within Maven's ecosystem.

   - **Properties:**
     - Description: Defines properties used throughout the build process. 
     - Parameters: `project.build.sourceEncoding`: Specifies the source code encoding (UTF-8). `itext.version`:  Defines the version of the iText library to be used (9.1.0).
     - Important Logic: Allows for customization and reusability of settings across different sections of the build script.

   - **Dependencies:**
     - Description: Lists all external libraries required by the project. 
     - Parameters: Each dependency has `groupId`, `artifactId`, `version`, and optional `scope` attributes.
     - Important Logic: Ensures that the necessary libraries are downloaded and included in the final build artifact.

   - **Plugins:**
     -  **maven-compiler-plugin:**
        - Description: Configures the Java compiler for the project.
        - Parameters: `source`: Specifies the source code compatibility level (21). `target`: Specifies the target bytecode version (21).
        - Important Logic: Compiles Java source code into executable bytecode.

     - **javafx-maven-plugin:**
        - Description:  Handles JavaFX project-specific tasks like packaging and resource management.
        - Parameters: `mainClass`: Specifies the main class for the application (BeadLoomVisualizer).
        - Important Logic: Integrates JavaFX libraries and sets up the entry point for the application.

**5. Language Version:** Java 21

**6. Dependency Versions:**

   - iText: 9.1.0
   - Apache POI: 5.2.3
   - OpenJFX: 21
   - Log4j: 2.17.2
   - Commons CSV: 1.13.0
   - Commons IO: 2.18.0
   - PDFBox: 2.0.30

**7. Pseudo Code:**

```
// Initialize Maven project settings from pom.xml
// Define project properties like encoding and iText version

// Download and install required dependencies (listed in pom.xml)

// Configure Java compiler using maven-compiler-plugin
    - Set source and target versions to 21
    - Compile all Java source code files

// Configure JavaFX integration using javafx-maven-plugin
    - Specify the main class for the application (BeadLoomVisualizer)
    - Package resources and dependencies required by the application

// Build the final JAR file containing compiled code, libraries, and resources


```

**8. Dependencies and Plugins Equivalents:**

* **Maven:** 
   -  `maven-compiler-plugin`: Gradle equivalent: `javaCompile` block
   -  `javafx-maven-plugin`: Gradle equivalent: `application` plugin with JavaFX configuration
   -  `org.apache.commons:commons-csv`: npm equivalent: `csv-parser` or `csv`

* **Gradle:** 
   -  `javaCompile`: Compiles Java source code
   -  `application`: Plugin for defining application entry points and packaging
   -  `com.itextpdf:itext-core`: Gradle dependency declaration


