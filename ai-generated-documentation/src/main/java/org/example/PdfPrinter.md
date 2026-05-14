![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Make it real.

---
## PdfPrinter Code Documentation

**1. Overview:**

The `PdfPrinter` class is responsible for setting up print configurations for various Excel sheets within a workbook and converting the workbook to a PDF file. It handles tasks like defining margins, headers, footers, page orientation, and scaling for different sheets, ensuring consistent formatting across all printed documents. 

**2. Package/module name:** `org.example`

**3. Class/file name:** `PdfPrinter.java`

**4. Class diagram name:** `PdfPrinter_diagram.svg` (Please provide the actual SVG file for this diagram)

**5. Detailed Documentation:**

**Class: PdfPrinter**

* **Static Method: `main(String[] args)`**
    * **Description:** Entry point of the application. Calls `printSetup()` to configure print settings and `printToPdf()` to convert the Excel workbook to PDF.
    * **Parameters:** 
        - `args`: Command line arguments (not used in this code).
    * **Return Values:** None

* **Static Method: `printSetup(String excelFilePath)`**
    * **Description:** Configures print settings for different sheets within the specified Excel workbook. It sets margins, headers, footers, page orientation, and scaling based on predefined constants and sheet names. 
    * **Parameters:**
        - `excelFilePath`: Path to the input Excel file.
    * **Return Values:** None

* **Static Method: `printToPdf(String excelFilePath, String pdfOutputPath)`**
    * **Description:** Converts the Excel workbook to a PDF file using LibreOffice's command line interface (`soffice`). 
    * **Parameters:**
        - `excelFilePath`: Path to the input Excel file.
        - `pdfOutputPath`: Directory path where the PDF file will be saved.
    * **Return Values:** None

**Variables:**

* **`bottomMarginDefault`, `topMarginDefault`, `leftMarginDefault`, `rightMarginDefault`**: Constants defining default margins for all sheets in inches.


**6. Pseudo Code:**



```
// Class: PdfPrinter

// Method: main(String[] args)
  1. Call printSetup() with the path to the Excel file.
  2. Call printToPdf() with the Excel file path and desired PDF output directory.

// Method: printSetup(String excelFilePath)
  1. Open the Excel workbook using the provided file path.
  2. Iterate through each sheet in the workbook based on predefined sheet names (Pattern, Legend, PageMap, Word_Chart).
     - For each sheet:
       - Set margins according to `bottomMarginDefault`, `topMarginDefault`, `leftMarginDefault`, and `rightMarginDefault`.
       - Configure header with design name, sheet name, and "BEAD PATTERN" for the Pattern sheet.
       - Configure footer with copyright note and logo placeholder for all sheets.
       - Set page orientation to landscape (false) or portrait (true).
       - Set paper size to A4.
       - Center the content horizontally.
       - Adjust scaling factors based on sheet dimensions and predefined constants.
       - Hide the Size sheet if it exists.
       - Reorder sheets according to a specific sequence.
  3. Save the modified workbook to the original file path.

// Method: printToPdf(String excelFilePath, String pdfOutputPath)
  1. Construct the command string to convert Excel to PDF using LibreOffice's command line interface.
  2. Execute the command using `Runtime.getRuntime().exec()`.
  3. Wait for the process to complete.
  4. Print a success message indicating the PDF file has been generated.



```

**Dependencies and Libraries:**

* **Apache POI:** Used for reading and writing Excel files.
* **LibreOffice:** Required for converting the Excel workbook to PDF using its command line interface (`soffice`).


