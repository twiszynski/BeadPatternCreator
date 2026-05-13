![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Make it real.

---
## PatternToPageMap.java Documentation & Pseudocode

**1. Overview:**

This Java program processes an Excel file containing a pattern of symbols and colors. It generates a new sheet in the same Excel file called "PageMap" which visualizes this pattern divided into pages based on user-defined page size (rows and columns per page). Each page is represented by a distinct color combination, reflecting the original pattern's layout.

**2. Package/module name:**

org.example

**3. Class/file name:**

PatternToPageMap.java

**4. Detailed Documentation:**


* **Class: PatternToPageMap**
    *  This class contains the main logic for converting an Excel pattern sheet into a "PageMap" visualization. 

    * **Method: main(String[] args)**
        * **Description:** Entry point of the program. Reads the input Excel file path, page size parameters from configuration (Config), and calls `convertExcelToPageMap` to perform the conversion.
        * **Parameters:**
            * `args`: String array containing command-line arguments (not used in this code).
        * **Return Values:** None
        * **Important Logic:** 
            * Retrieves the Excel file path from configuration (`Config`).
            * Reads page size parameters (rows and columns per page) from configuration.
            * Calls `convertExcelToPageMap` to process the Excel file.

    * **Method: convertExcelToPageMap(String excelFilePath, int rowsPerPage, int colsPerPage)**
        * **Description:**  Reads the input Excel file, extracts pattern data and color mapping, generates the "PageMap" sheet, and saves the modified Excel file.
        * **Parameters:**
            * `excelFilePath`: Path to the input Excel file.
            * `rowsPerPage`: Number of rows per page in the output.
            * `colsPerPage`: Number of columns per page in the output.
        * **Return Values:** None
        * **Important Logic:**
            * Opens the Excel workbook using a FileInputStream.
            * Reads the "Legend" sheet to create a map of symbols and their corresponding RGB color values.
            * Reads the "Size" sheet to determine the total number of rows and columns in the pattern.
            * Creates a new sheet called "PageMap".
            * Iterates through the pattern data, dividing it into pages based on `rowsPerPage` and `colsPerPage`.
            * For each page:
                * Adds borders around the page area.
                * Sets the cell color for each symbol in the page according to the color map.
                * Adds a page number label in the top-left corner of each page.
            * Saves the modified workbook back to the original file path.

    * **Method: readLegendColors(Workbook workbook)**
        * **Description:** Reads the "Legend" sheet from the Excel workbook and creates a map of symbols to their corresponding RGB color values.
        * **Parameters:**
            * `workbook`: The open Workbook object.
        * **Return Values:** A HashMap where keys are symbols (strings) and values are int arrays representing RGB color values.
        * **Important Logic:**
            * Iterates through each row in the "Legend" sheet, skipping the header row.
            * Extracts the symbol, red, green, and blue values from each row.
            * Stores the symbol and its RGB value as a key-value pair in the HashMap.

    * **Method: addSelectiveBorders(Sheet sheet, int startRow, int endRow, int startCol, int endCol, short borderColor)**
        * **Description:** Adds borders to the specified range of cells on the given sheet. Borders are applied only to the top, bottom, left, and right edges of the selected area.
        * **Parameters:**
            * `sheet`: The Sheet object to modify.
            * `startRow`, `endRow`: Row indices defining the top and bottom borders.
            * `startCol`, `endCol`: Column indices defining the left and right borders.
            * `borderColor`: Short value representing the border color index.
        * **Return Values:** None
        * **Important Logic:**
            * Iterates through each cell within the specified range.
            * Sets the appropriate border style (DOUBLE for top, THICK for bottom, left, and right) and border color using `borderColor`.



**5. Pseudo Code:**

```
// Class: PatternToPageMap

// Method: main(String[] args)
  1. Get Excel file path from configuration (Config).
  2. Get rows per page and columns per page from configuration (Config).
  3. Call convertExcelToPageMap with the file path, rows per page, and columns per page.

// Method: convertExcelToPageMap(String excelFilePath, int rowsPerPage, int colsPerPage)
  1. Open the Excel workbook using FileInputStream.
  2. Read the "Legend" sheet to create a map of symbols to RGB colors.
  3. Read the "Size" sheet to get the total pattern size (rows and columns).
  4. Create a new sheet called "PageMap".
  5. Calculate the scale factor based on the page size and pattern size.
  6. Iterate through the pattern data, dividing it into pages:
     - For each page:
       - Add borders around the page area using addSelectiveBorders().
       - Set cell colors for each symbol in the page according to the color map.
       - Add a page number label in the top-left corner of each page.
  7. Save the modified workbook back to the original file path.

// Method: readLegendColors(Workbook workbook)
  1. Iterate through each row in the "Legend" sheet, skipping the header row.
  2. Extract the symbol, red, green, and blue values from each row.
  3. Store the symbol and its RGB value as a key-value pair in a HashMap.

// Method: addSelectiveBorders(Sheet sheet, int startRow, int endRow, int startCol, int endCol, short borderColor)
  1. Iterate through each cell within the specified range.
  2. Set the appropriate border style (DOUBLE for top, THICK for bottom, left, and right) and border color using borderColor.



```

**6. Dependencies and Libraries:**


* **Apache POI:** This library is used to read and write Excel files in Java. 
    * Equivalent libraries in other languages:
        * Python: `openpyxl`, `xlrd`, `xlsxwriter`
        * C++: `libxlsxdom`, `poppler-utils`



