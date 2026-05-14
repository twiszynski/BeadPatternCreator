![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Make it real.

---
## PatternToPageMap Code Documentation

**1. Overview:**

The `PatternToPageMap` class is responsible for generating a page map from an Excel file containing a pattern design. It reads the pattern design, color legend, and size information from specific sheets within the Excel file. Then, it creates a new sheet called "PageMap" where each cell represents a section of the pattern on a single page. The code also adds borders to define the sections and scales the pattern based on the number of rows and columns per page defined in configuration settings.

**2. Package/module name:** `org.example`

**3. Class/file name:** `PatternToPageMap.java`

**4. Class diagram name:** `PatternToPageMap_diagram.svg` (This should be a separate file containing the class diagram)

**5. Detailed Documentation:**

   - **Class: `PatternToPageMap`**
     - **Fields:**
       - `patternRows`: Integer representing the number of rows in the pattern design.
       - `patternColumns`: Integer representing the number of columns in the pattern design.
     - **Methods:**
       - **`main(String[] args)`**: 
         - Description: Entry point for the program. Reads the Excel file path from configuration, sets up page size parameters, and calls `convertExcelToPageMap()` to generate the page map.
         - Parameters: `args`: String array containing command line arguments (not used in this code).
         - Return Value: None.
       - **`convertExcelToPageMap(String excelFilePath, int rowsPerPage, int colsPerPage)`**: 
         - Description: Reads the Excel file, extracts pattern data, color legend, and size information. Creates a new sheet called "PageMap" and fills it with scaled pattern data based on the specified page size. Adds borders to define page sections and saves the modified Excel file.
         - Parameters: `excelFilePath`: String representing the path to the input Excel file. `rowsPerPage`: Integer representing the number of rows per page. `colsPerPage`: Integer representing the number of columns per page.
         - Return Value: None.
       - **`readLegendColors(Workbook workbook)`**: 
         - Description: Reads the color legend from a specific sheet ("Legend") in the Excel file and returns a map associating each symbol with its corresponding RGB color values.
         - Parameters: `workbook`: Workbook object representing the open Excel file.
         - Return Value: Map<String, int[]> where keys are symbols and values are arrays of three integers representing RGB color values.
       - **`addSelectiveBorders(Sheet sheet, int startRow, int endRow, int startCol, int endCol, short borderColor)`**: 
         - Description: Adds borders to a specific section of the "PageMap" sheet based on the provided coordinates and border color index. Only outer borders are added for each cell within the specified range.
         - Parameters: `sheet`: Sheet object representing the "PageMap" sheet. `startRow`, `endRow`, `startCol`, `endCol`: Integers defining the top-left and bottom-right corners of the section to border. `borderColor`: Short integer representing the index of the border color in the Excel color palette.
         - Return Value: None.

**6. Pseudo Code:**


```
// Class: PatternToPageMap

// Method: main(String[] args)
  1. Get Excel file path from configuration settings.
  2. Set page size parameters (rowsPerPage, colsPerPage) from configuration settings.
  3. Call convertExcelToPageMap() with the file path and page size parameters.

// Method: convertExcelToPageMap(excelFilePath, rowsPerPage, colsPerPage)
  1. Open the Excel file using FileInputStream.
  2. Read the "Size" sheet to get patternRows and patternColumns.
  3. Calculate scale factor based on pattern size and page size.
  4. Read the "Legend" sheet to create a map of symbols and their RGB colors.
  5. Create a new sheet called "PageMap".
  6. Iterate through each cell in the pattern design sheet:
     - Get symbol from current cell.
     - Retrieve corresponding RGB color from the legend map.
     - Set cell background color on the "PageMap" sheet to the retrieved color.
  7. Calculate starting and ending rows/columns for each page section based on scale factor.
  8. Iterate through each page section:
      - Add borders to the outer edges of the section using addSelectiveBorders() function.
      - Merge cells within each section to create a larger cell representing a single page.
      - Set text value in merged cell with page number.
  9. Save the modified Excel file using FileOutputStream.

// Method: readLegendColors(workbook)
  1. Get the "Legend" sheet from the workbook.
  2. Iterate through each row in the "Legend" sheet, starting from the second row (header row).
  3. Extract symbol, red, green, and blue values from corresponding cells.
  4. Create a map entry with the symbol as key and an array of RGB values as value.
  5. Return the map containing all legend entries.

// Method: addSelectiveBorders(sheet, startRow, endRow, startCol, endCol, borderColor)
  1. Iterate through each cell within the specified range (startRow to endRow, startCol to endCol).
  2. Set top border style and color for cells on the top row.
  3. Set bottom border style and color for cells on the bottom row.
  4. Set left border style and color for cells on the left column.
  5. Set right border style and color for cells on the right column.



```

**7. Dependencies and Libraries:**

- **Apache POI:** Used for reading and writing Excel files. 


