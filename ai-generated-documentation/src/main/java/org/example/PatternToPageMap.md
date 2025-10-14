![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Get The Future You Want!

---
## PatternToPageMap.java Documentation and Pseudocode

**1. Overview:**

This Java program processes an Excel spreadsheet containing a pattern of symbols and colors. It generates a new sheet within the same workbook called "PageMap" which visualizes this pattern as a grid, scaled to represent multiple pages. Each page is outlined with borders, and a page number is displayed in the top-left corner.

**2. Package/module name:** org.example

**3. Class/file name:** PatternToPageMap.java

**4. Detailed Documentation:**


* **`main(String[] args)`:**
    * **Description:** The entry point of the program. It reads the Excel file path from a configuration, sets up page dimensions, and calls `convertExcelToPageMap` to generate the "PageMap" sheet.
    * **Parameters:** 
        - `args`: Command line arguments (not used in this code).
    * **Return Values:** None.
    * **Important Logic:** Retrieves configuration values for the number of columns and rows per page, then initiates the conversion process.

* **`convertExcelToPageMap(String excelFilePath, int rowsPerPage, int colsPerPage)`:**
    * **Description:**  Reads the input Excel file, extracts pattern data, and generates the "PageMap" sheet with scaled symbols and borders.
    * **Parameters:**
        - `excelFilePath`: Path to the input Excel file.
        - `rowsPerPage`: Number of rows per page in the output.
        - `colsPerPage`: Number of columns per page in the output.
    * **Return Values:** None.
    * **Important Logic:**
        - Reads the "Size" sheet to determine the total pattern size (rows and columns).
        - Calculates a scaling factor based on the pattern size and page dimensions.
        - Reads the "Legend" sheet to map symbols to RGB colors.
        - Iterates through the pattern, creating cells in the "PageMap" sheet with corresponding colors.
        - Adds borders around each page section using `addSelectiveBorders`.
        - Sets page numbers within each section.

* **`readLegendColors(Workbook workbook)`:**
    * **Description:** Reads the "Legend" sheet and creates a map associating symbols with their RGB color values.
    * **Parameters:** 
        - `workbook`: The Excel workbook object.
    * **Return Values:** A `Map<String, int[]>` where keys are symbols and values are RGB color arrays (red, green, blue).
    * **Important Logic:** Iterates through the "Legend" sheet rows, extracting symbol and color data to populate the map.

* **`addSelectiveBorders(Sheet sheet, int startRow, int endRow, int startCol, int endCol, short borderColor)`:**
    * **Description:** Adds borders only to the outer edges of a specified range on the "PageMap" sheet.
    * **Parameters:**
        - `sheet`: The "PageMap" sheet object.
        - `startRow`, `endRow`, `startCol`, `endCol`: Define the range of cells to border.
        - `borderColor`: Short value representing the border color index.
    * **Return Values:** None.
    * **Important Logic:** Iterates through the specified range, setting appropriate border styles and colors for top, bottom, left, and right edges.

**5. Pseudo Code:**



```
// Class: PatternToPageMap

// Method: main(String[] args)
  1. Get Excel file path from configuration (Config.getXlsxPatternFilePath()).
  2. Set page dimensions (rowsPerPage, colsPerPage) from configuration (Config.getRowsPerPage(), Config.getColsPerPage()).
  3. Call convertExcelToPageMap() with the file path and page dimensions.

// Method: convertExcelToPageMap(String excelFilePath, int rowsPerPage, int colsPerPage)
  1. Open the Excel file using FileInputStream.
  2. Create a Workbook object from the file stream.
  3. Read the "Legend" sheet to create a map of symbols and their RGB colors (readLegendColors()).
  4. Get the "Size" sheet to determine total pattern size (patternRows, patternColumns).
  5. Calculate scaling factor based on page dimensions and pattern size.
  6. Create a new sheet named "PageMap".
  7. Iterate through each cell in the pattern sheet:
     - Read symbol from current cell.
     - Get corresponding RGB color from the legend map.
     - Create a new cell in the "PageMap" sheet with the same position and set its background color to the retrieved RGB value.
  8. Add borders around each page section using addSelectiveBorders() function, specifying start and end rows/columns for each section.
  9. Set page numbers within each section by creating cells in the top-left corner of each section.
  10. Save changes to the Excel file using FileOutputStream.

// Method: readLegendColors(Workbook workbook)
  1. Get the "Legend" sheet from the workbook.
  2. Create a map to store symbol-color pairs.
  3. Iterate through each row in the "Legend" sheet (excluding the header row):
     - Read symbol, red, green, and blue values from corresponding cells.
     - Add a new entry to the map with the symbol as key and an array of RGB values as value.

// Method: addSelectiveBorders(Sheet sheet, int startRow, int endRow, int startCol, int endCol, short borderColor)
  1. Iterate through each cell within the specified range (startRow, endRow, startCol, endCol).
  2. Set top border style to DOUBLE and top border color to borderColor for cells on the first row.
  3. Set bottom border style to THICK and bottom border color to borderColor for cells on the last row.
  4. Set left border style to THICK and left border color to borderColor for cells on the first column.
  5. Set right border style to THICK and right border color to borderColor for cells on the last column.



```

**6. Dependencies and Libraries:**


* **Apache POI:** This library is used for reading and writing Excel files. 
    - Equivalent libraries in other languages:
        - Python: `openpyxl`, `xlrd`, `xlsxwriter`
        - C++: `libxl`, `poppler`
        - Java:  (already being used)



