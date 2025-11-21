![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Get The Future You Want!

---
## PatternToPageMap.java Documentation and Pseudocode

**1. Overview:**

This Java program processes an Excel file containing a pattern of symbols and colors. It generates a new sheet in the same Excel file called "PageMap" which visualizes the pattern as a grid, dividing it into pages based on user-defined parameters (rows per page and columns per page). Each page is represented by a distinct color and bordered to clearly distinguish them.

**2. Package/module name:** org.example

**3. Class/file name:** PatternToPageMap.java

**4. Detailed Documentation:**

* **`main(String[] args)`:**
    - **Description:** This is the entry point of the program. It reads the Excel file path from a configuration file, sets up page size parameters, and calls the `convertExcelToPageMap` function to perform the conversion.
    - **Parameters:** 
        - `args`: String array containing command line arguments (not used in this code).
    - **Return Values:** None.
    - **Important Logic:**
        - Retrieves the Excel file path from a configuration file (`Config`).
        - Reads the number of columns and rows per page from the configuration file (`Config`).
        - Calls `convertExcelToPageMap` to process the Excel file.

* **`convertExcelToPageMap(String excelFilePath, int rowsPerPage, int colsPerPage)`:**
    - **Description:** This function reads the input Excel file, extracts the pattern data, and generates the "PageMap" sheet with page divisions and borders.
    - **Parameters:**
        - `excelFilePath`: String representing the path to the input Excel file.
        - `rowsPerPage`: Integer specifying the number of rows per page.
        - `colsPerPage`: Integer specifying the number of columns per page.
    - **Return Values:** None.
    - **Important Logic:**
        - Reads the "Size" sheet to determine the total pattern size (rows and columns).
        - Calculates a scaling factor based on the pattern size and page size parameters.
        - Reads the "Legend" sheet to map symbols to RGB color values.
        - Creates the "PageMap" sheet and fills it with colors based on the symbol mapping.
        - Adds borders around each page division using `addSelectiveBorders`.
        - Sets column widths and row heights for the "PageMap" sheet.
        - Saves the changes back to the original Excel file.

* **`readLegendColors(Workbook workbook)`:**
    - **Description:** This function reads the "Legend" sheet from the Excel file and creates a map of symbols to their corresponding RGB color values.
    - **Parameters:**
        - `workbook`: Workbook object representing the open Excel file.
    - **Return Values:** A HashMap where keys are symbols (strings) and values are integer arrays representing RGB color values.
    - **Important Logic:**
        - Iterates through rows in the "Legend" sheet, skipping the header row.
        - Extracts symbol, red, green, and blue values from each row.
        - Stores the symbol and its corresponding RGB values in the HashMap.

* **`addSelectiveBorders(Sheet sheet, int startRow, int endRow, int startCol, int endCol, short borderColor)`:**
    - **Description:** This function adds borders to a specific range of cells on the "PageMap" sheet, only applying borders to the outer edges of the defined range.
    - **Parameters:**
        - `sheet`: Sheet object representing the "PageMap" sheet.
        - `startRow`, `endRow`: Integers defining the starting and ending rows of the border range.
        - `startCol`, `endCol`: Integers defining the starting and ending columns of the border range.
        - `borderColor`: Short value representing the color index for the borders.
    - **Return Values:** None.
    - **Important Logic:**
        - Iterates through each cell within the specified range.
        - Applies appropriate border styles (top, bottom, left, right) based on the cell's position within the range.


**5. Pseudo Code:**

```
// Class: PatternToPageMap

main()
  1. Get Excel file path from configuration file.
  2. Get rows per page and columns per page from configuration file.
  3. Call convertExcelToPageMap function with file path, rows per page, and columns per page.

convertExcelToPageMap(excelFilePath, rowsPerPage, colsPerPage)
  1. Open the Excel file using FileInputStream.
  2. Read "Size" sheet to get total pattern size (rows and columns).
  3. Calculate scaling factor based on pattern size and page size parameters.
  4. Read "Legend" sheet to create a map of symbols to RGB color values.
  5. Create "PageMap" sheet in the Excel file.
  6. Fill "PageMap" sheet with colors based on symbol mapping from step 4.
  7. Add borders around each page division using addSelectiveBorders function.
  8. Set column widths and row heights for "PageMap" sheet.
  9. Save changes to the Excel file using FileOutputStream.

readLegendColors(workbook)
  1. Iterate through rows in "Legend" sheet, skipping header row.
  2. For each row:
    - Extract symbol, red, green, and blue values.
    - Store symbol and RGB color values in a HashMap.
  3. Return the HashMap of symbols to RGB colors.

addSelectiveBorders(sheet, startRow, endRow, startCol, endCol, borderColor)
  1. Iterate through each cell within the specified range (startRow, endRow, startCol, endCol).
  2. For each cell:
    - If cell is on top row, apply top border with specified color.
    - If cell is on bottom row, apply bottom border with specified color.
    - If cell is on left column, apply left border with specified color.
    - If cell is on right column, apply right border with specified color.

```



