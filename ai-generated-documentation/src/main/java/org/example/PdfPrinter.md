![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Get The Future You Want!

---
## PdfPrinter.java Documentation and Pseudocode

**1. Overview:**

The `PdfPrinter` class is responsible for generating PDF files from Excel spreadsheets. It takes an Excel file path as input and generates a corresponding PDF file in the specified output directory. The class also sets up printing configurations for different sheets within the Excel file, including margins, headers, footers, and scaling. 

**2. Package/module name:** `org.example`

**3. Class/file name:** `PdfPrinter.java`

**4. Detailed Documentation:**

**Class: PdfPrinter**

* **Method: main(String[] args)**
    * **Description:** The entry point of the program. It calls the `printSetup()` and `printToPdf()` methods to configure printing settings and generate the PDF file.
    * **Parameters:** 
        * `args`: An array of strings containing command-line arguments (not used in this code).
    * **Return Values:** None.
    * **Important Logic:**  
        * Calls `printSetup()` to configure printing settings for different sheets within the Excel file.
        * Calls `printToPdf()` to generate the PDF file from the configured Excel file.

* **Method: printSetup(String excelFilePath)**
    * **Description:** Configures printing settings for various sheets within the provided Excel file. This includes setting margins, headers, footers, orientation, and scaling. 
    * **Parameters:**
        * `excelFilePath`: The path to the input Excel file.
    * **Return Values:** None.
    * **Important Logic:**
        * Opens the Excel file using `FileInputStream` and `XSSFWorkbook`.
        * Iterates through each sheet (Pattern, Legend, PageMap, Word_Chart) based on predefined sheet names from `Config` class.
        * For each sheet:
            * Sets margins, header, footer, orientation, paper size, and scaling according to the sheet type.
            * Uses `XSSFPrintSetup` to configure printing settings for each sheet.
            * Hides the "Size" sheet if it exists.
            * Orders the sheets based on desired print order.
        * Saves the modified Excel file back to disk.

* **Method: printToPdf(String excelFilePath, String pdfOutputPath)**
    * **Description:** Generates a PDF file from the provided Excel file using LibreOffice's `soffice` command-line tool. 
    * **Parameters:**
        * `excelFilePath`: The path to the input Excel file.
        * `pdfOutputPath`: The output directory where the PDF file will be saved.
    * **Return Values:** None.
    * **Important Logic:**
        * Constructs a command string using `soffice` with options for headless execution, conversion to PDF, and specifying the input and output paths.
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
  1. Open the Excel file using FileInputStream and XSSFWorkbook.
  2. Get sheet names from Config class (Pattern, Legend, PageMap, Word_Chart).
  3. For each sheet name:
    - Get the corresponding sheet object from the workbook.
    - If sheet exists:
      - Set margins for the sheet based on sheet type.
      - Set header and footer text for the sheet based on sheet type.
      - Set orientation, paper size, and scaling for the sheet.
      - Hide the "Size" sheet if it exists.
      - Order the sheets based on desired print order.
  4. Save the modified Excel file back to disk.

// Method: printToPdf(String excelFilePath, String pdfOutputPath)
  1. Construct a command string using soffice with options for headless execution, conversion to PDF, and specifying input and output paths.
  2. Execute the command using Runtime.getRuntime().exec().
  3. Wait for the process to complete using process.waitFor().
  4. Print a success message indicating the completion of the PDF generation. 



```

**Dependencies and Libraries:**


* **Apache POI:** This library is used for reading and writing Excel files in Java. It provides classes for working with different Excel file formats, including XLSX.
* **LibreOffice:** The `soffice` command-line tool from LibreOffice is used to convert the Excel file to PDF format.

**Edge Cases and Error Handling:**


* The code handles potential `IOException` during file operations. 
* It also uses `process.waitFor()` to wait for the conversion process to complete, which can help handle cases where the conversion fails.



