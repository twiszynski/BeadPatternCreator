![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Get The Future You Want!

---
## PdfPrinter.java Documentation and Pseudocode

**1. Overview:**

The `PdfPrinter` class is responsible for generating PDF files from Excel spreadsheets. It takes an Excel file path as input and generates a corresponding PDF file in the specified output directory. The class also handles setting up print configurations for different sheets within the Excel file, including margins, headers, footers, and scaling. 

**2. Package/module name:** `org.example`

**3. Class/file name:** `PdfPrinter.java`

**4. Detailed Documentation:**

* **Class: PdfPrinter**
    *  **Description:** This class handles the process of generating PDF files from Excel spreadsheets and setting up print configurations for different sheets within the Excel file. 
    * **Dependencies:** The class relies on external libraries such as Apache POI (for reading and manipulating Excel files) and LibreOffice (via its command-line interface, `soffice`, for converting Excel to PDF).

* **Method: main(String[] args)**
    *  **Description:** This is the entry point of the program. It calls the `printSetup` method to configure print settings for the Excel file and then calls the `printToPdf` method to generate the PDF output.
    * **Parameters:** 
        * `args`: An array of strings containing command-line arguments (not used in this implementation).
    * **Return Values:** None

* **Method: printSetup(String excelFilePath)**
    *  **Description:** This method configures print settings for different sheets within the specified Excel file. It sets margins, headers, footers, and scaling based on predefined configurations stored in the `Config` class. 
    * **Parameters:**
        * `excelFilePath`: The path to the input Excel file.
    * **Return Values:** None
    * **Important Logic:**
        * Iterates through each sheet defined in the `Config` class (Pattern, Legend, PageMap, WordChart).
        * For each sheet:
            * Sets margins based on predefined values and scales them according to the sheet's size.
            * Configures headers and footers with specific text and formatting.
            * Sets orientation, paper size, and horizontal centering.
            * Adjusts zoom factor for proper scaling.
        * Hides the "Size" sheet from being printed.
        * Reorders sheets based on predefined order.
        * Saves the modified Excel file back to disk.

* **Method: printToPdf(String excelFilePath, String pdfOutputPath)**
    *  **Description:** This method converts the configured Excel file into a PDF file using LibreOffice's command-line interface (`soffice`). 
    * **Parameters:**
        * `excelFilePath`: The path to the input Excel file.
        * `pdfOutputPath`: The directory where the output PDF file will be saved.
    * **Return Values:** None
    * **Important Logic:**
        * Constructs a command string using `soffice` with options for headless execution, conversion to PDF format, and specifying the input and output paths.
        * Executes the command using `Runtime.getRuntime().exec()`.
        * Waits for the process to complete using `process.waitFor()`.
        * Prints a success message indicating the completion of the PDF generation.

**5. Pseudo Code:**


```
// Class: PdfPrinter

// Method: main(String[] args)
  1. Call printSetup(excelFilePath) 
  2. Call printToPdf(excelFilePath, pdfOutputPath)

// Method: printSetup(String excelFilePath)
  1. Open the Excel file using FileInputStream and XSSFWorkbook
  2. Iterate through each sheet defined in Config (Pattern, Legend, PageMap, WordChart):
    * Get the sheet object from the workbook
    * Set margins for the sheet based on predefined values and scale them according to the sheet's size
    * Configure headers and footers with specific text and formatting
    * Set orientation, paper size, and horizontal centering
    * Adjust zoom factor for proper scaling
  3. Hide the "Size" sheet from being printed
  4. Reorder sheets based on predefined order
  5. Save the modified Excel file back to disk

// Method: printToPdf(String excelFilePath, String pdfOutputPath)
  1. Construct a command string using soffice with options for headless execution, conversion to PDF format, and specifying the input and output paths
  2. Execute the command using Runtime.getRuntime().exec()
  3. Wait for the process to complete using process.waitFor()
  4. Print a success message indicating the completion of the PDF generation



```

**Edge Cases and Error Handling:**


* The code handles potential `IOException` during file operations (reading/writing Excel files). It prints an error message and terminates if an exception occurs. 
* The `printToPdf` method uses `process.waitFor()` to wait for the LibreOffice conversion process to complete. This helps ensure that the PDF generation is successful before proceeding.

**Dependencies and Libraries:**


* **Apache POI:** Used for reading, writing, and manipulating Excel files (.xlsx).
* **LibreOffice (via soffice):** Used for converting Excel files to PDF format.



