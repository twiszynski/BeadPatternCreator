![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Get The Future You Want!

---
## PngToExcel Build Script Documentation

**1. Overview:**

This Maven build script defines the structure and dependencies for the "PngToExcel" project. It specifies how to compile, package, and run the application. The script also includes plugins for JavaFX integration and compilation. 

**2. Build Tool:** Maven

**3. Script/File Name:** pom.xml

**4. Detailed Documentation:**

   - **Project Definition:**
     - Description: Defines basic project information like group ID, artifact ID, version, packaging type, name, and URL.
     - Parameters: 
       - `groupId`: Unique identifier for the project's group (org.example).
       - `artifactId`: Unique identifier for the project within its group (PngToExcel).
       - `version`: Current version of the project (1.0-SNAPSHOT).
       - `packaging`: Type of artifact to create (jar).
     - Important Logic: None

   - **Properties:**
     - Description: Defines project-wide properties used throughout the build process.
     - Parameters: 
       - `project.build.sourceEncoding`: Source code encoding (UTF-8).
       - `itext.version`: Version of the iText library (9.1.0).
     - Important Logic: None

   - **Dependencies:**
     - Description: Lists external libraries required by the project. Each dependency includes its group ID, artifact ID, version, and scope (compile or test).
     - Parameters: 
       - `groupId`: Unique identifier for the library's group.
       - `artifactId`: Unique identifier for the library within its group.
       - `version`: Version of the library.
       - `scope`: Defines the dependency's usage (compile, test, etc.).
     - Important Logic: None

   - **Build Plugins:**
     - Description: Configures plugins to perform specific tasks during the build process.
     - Parameters: 
       - `groupId`: Unique identifier for the plugin group.
       - `artifactId`: Unique identifier for the plugin within its group.
       - `version`: Version of the plugin.
       - `configuration`: Plugin-specific configuration parameters.
     - Important Logic:
       - **maven-compiler-plugin:** Compiles Java source code using the specified source and target versions (21).
       - **javafx-maven-plugin:** Integrates JavaFX into the project, specifying the main class for execution (BeadLoomVisualizer).

**5. Language Version:** Java 21

**6. Dependency Versions:**

   - iText: 9.1.0
   - Apache POI: 5.2.3
   - OpenJFX: 21
   - Log4j: 2.17.2
   - Commons CSV: 1.13.0
   - Commons IO: 2.18.0
   - Bouncy Castle Adapter: 9.1.0 (bundled with iText)
   - Apache PDFBox: 2.0.30

**7. Pseudo Code:**

```
// Project Setup Phase
1. Define project properties: groupId, artifactId, version, packaging, name, URL
2. Set source encoding to UTF-8
3. Define iText library version (9.1.0)

// Dependency Management Phase
1. Include JUnit for testing (version 3.8.1, scope: test)
2. Include Apache POI libraries (poi and poi-ooxml, version 5.2.3)
3. Include OpenJFX libraries (javafx-controls and javafx-graphics, version 21)
4. Include Log4j core and API libraries (version 2.17.2)
5. Include Commons CSV library (version 1.13.0)
6. Include Commons IO library (version 2.18.0)
7. Include iText core and bouncy-castle adapter (version 9.1.0)
8. Include Apache PDFBox libraries (pdfbox and pdfbox-tools, version 2.0.30)

// Build Configuration Phase
1. Configure Maven Compiler Plugin:
   - Set source and target Java versions to 21
2. Configure JavaFX Maven Plugin:
   - Specify the main class for execution (BeadLoomVisualizer)


```



**8. Dependencies and Plugins Equivalents:**

* **Maven:** Gradle, npm (for JavaScript projects)
* **maven-compiler-plugin:**  `java` plugin in Gradle
* **javafx-maven-plugin:** `javafx` plugin in Gradle 




