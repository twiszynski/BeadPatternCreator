![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Make it real.

---
## PdfPrinter.java Documentation & Pseudocode

**1. Overview:**

The `PdfPrinter` class is responsible for generating PDF files from Excel spreadsheets. It reads an Excel file specified by the user, sets up printing configurations for different sheets within the file (Pattern, Legend, PageMap, Word Chart), and then converts the Excel file to a PDF using LibreOffice's command-line interface (`soffice`).

**2. Package/module name:** `org.example`

**3. Class/file name:** `PdfPrinter.java`

**4. Detailed Documentation:**

**Class: PdfPrinter**

* **Method: main(String[] args)**
    * **Description:** Entry point for the application. Calls `printSetup()` to configure printing settings and `printToPdf()` to generate the PDF file.
    * **Parameters:** 
        - `args`: String array containing command-line arguments (not used in this code).
    * **Return Values:** None
    * **Important Logic:**  Sets up the paths for the Excel file and output directory based on configuration settings (`Config` class).

* **Method: printSetup(String excelFilePath)**
    * **Description:** Configures printing settings for different sheets within the specified Excel file. 
    * **Parameters:**
        - `excelFilePath`: Path to the input Excel file.
    * **Return Values:** None
    * **Important Logic:**
        - Opens the Excel file using Apache POI.
        - Iterates through each sheet (Pattern, Legend, PageMap, Word Chart) and sets margins, headers, footers, orientation, paper size, and scaling based on predefined configurations (`Config` class).
        - Sets repeating rows and columns for specific sheets.
        - Adjusts font sizes based on the scale factor to ensure readability.
        - Hides the "Size" sheet from being printed.
        - Orders the sheets in a specific sequence for printing.
        - Saves the modified Excel file with updated settings.

* **Method: printToPdf(String excelFilePath, String pdfOutputPath)**
    * **Description:** Converts the configured Excel file to a PDF using LibreOffice's command-line interface (`soffice`).
    * **Parameters:**
        - `excelFilePath`: Path to the input Excel file.
        - `pdfOutputPath`: Directory path where the output PDF file will be saved.
    * **Return Values:** None
    * **Important Logic:**
        - Constructs the command string for LibreOffice, specifying the input Excel file and output directory.
        - Executes the command using `Runtime.getRuntime().exec()`.
        - Waits for the process to complete.
        - Prints a message indicating successful PDF generation.

**5. Pseudo Code:**


```
// Class: PdfPrinter

// Method: main(String[] args)
  1. Get Excel file path from configuration (Config.getXlsxPatternFilePath()).
  2. Get output directory path from configuration (Config.getLibraryBaseDirectoryPath()).
  3. Call printSetup() to configure printing settings for the Excel file.
  4. Call printToPdf() to generate the PDF file.

// Method: printSetup(String excelFilePath)
  1. Open the Excel file using Apache POI.
  2. Iterate through each sheet (Pattern, Legend, PageMap, Word Chart):
    - Get sheet object for current sheet.
    - Set margins (top, bottom, left, right) based on configuration.
    - Set header and footer content based on configuration.
    - Set orientation (landscape or portrait).
    - Set paper size (A4).
    - Set scaling factor based on sheet dimensions and configuration.
    - Adjust font sizes for readability based on scale factor.
    - Set repeating rows and columns if required.
  3. Hide the "Size" sheet from printing.
  4. Order the sheets in a specific sequence for printing.
  5. Save the modified Excel file with updated settings.

// Method: printToPdf(String excelFilePath, String pdfOutputPath)
  1. Construct command string for LibreOffice to convert Excel to PDF:
    - Include input Excel file path.
    - Include output directory path.
  2. Execute the command using `Runtime.getRuntime().exec()`.
  3. Wait for the process to complete.
  4. Print a message indicating successful PDF generation. 



```

**Dependencies and Libraries:**


* **Apache POI:** Used for reading and writing Excel files.
* **LibreOffice:** Used for converting Excel files to PDF format via its command-line interface (`soffice`).

**Note:** This documentation assumes the existence of a `Config` class that holds configuration settings like file paths, sheet names, and printing parameters.



