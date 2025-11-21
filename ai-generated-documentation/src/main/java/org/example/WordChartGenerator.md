![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Get The Future You Want!

---
## WordChartGenerator.java Documentation and Pseudocode

**1. Overview:**

This Java program generates a word chart diagram from a given Excel file. The program reads a predefined schema from a sheet named "Pattern" and uses it to generate a visual representation of the data in another sheet. 

The output is written back into the same Excel file, adding a new sheet named "Word_Chart" containing the generated diagrams. Each row in the "Word_Chart" sheet represents a row from the input data, with each cell displaying a condensed representation of the corresponding data segment based on the schema.


**2. Package/module name:**

org.example

**3. Class/file name:**

WordChartGenerator.java

**4. Detailed Documentation:**

* **Class: WordChartGenerator**
    * **Method: main(String[] args)**
        * **Description:** Entry point of the program. Reads the Excel file path from a configuration file and calls `generateWordChartFromFile` to process the data.
        * **Parameters:** 
            * `args`: String array containing command-line arguments (not used in this code).
        * **Return Values:** None
        * **Important Logic:**  Retrieves the Excel file path from a configuration file (`Config.getXlsxPatternFilePath()`) and calls the `generateWordChartFromFile` method to process the data.

    * **Method: generateWordChartFromFile(String filePath)**
        * **Description:** Processes the Excel file specified by the given path, reads the schema, generates word charts for each row, and writes the results back to the same file.
        * **Parameters:** 
            * `filePath`: String representing the path to the input Excel file.
        * **Return Values:** None
        * **Important Logic:**
            * Opens the Excel file using a `FileInputStream` and creates an `XSSFWorkbook` object.
            * Reads the dimensions of the schema from a sheet named "Size".
            * Retrieves the schema data from a sheet named "Pattern" using `readSchemaFromSheet`.
            * Removes any existing "Word_Chart" sheet if it exists.
            * Creates a new sheet named "Word_Chart" and adds header rows for "Row No." and "Word Chart".
            * Iterates through each row of the schema, generating a word chart string using `generateRowChart` and writing it to the "Word_Chart" sheet.
            * Sets appropriate styles for cells in the "Word_Chart" sheet.
            * Autosizes columns and writes the changes back to the file using a `FileOutputStream`.

    * **Method: readSchemaFromSheet(Sheet sheet, int patternWidth, int patternHeight)**
        * **Description:** Reads the schema data from the specified Excel sheet.
        * **Parameters:** 
            * `sheet`: The Excel sheet object containing the schema data.
            * `patternWidth`: Integer representing the width of the schema.
            * `patternHeight`: Integer representing the height of the schema.
        * **Return Values:** A 2D String array representing the schema data.
        * **Important Logic:** Iterates through each row and cell in the sheet, extracting the string values and storing them in a 2D array.

    * **Method: generateRowChart(String[] row)**
        * **Description:** Generates a word chart string for a given row of schema data.
        * **Parameters:** 
            * `row`: A String array representing a single row of schema data.
        * **Return Values:** A String containing the generated word chart representation.
        * **Important Logic:** Iterates through the row, identifying changes in values and grouping consecutive identical values together. It then constructs a string representation of the word chart based on these groupings.

    * **Methods: setBorderedAndCenteredStyle(XSSFCellStyle style), setBorderedAndLeftStyle(XSSFCellStyle style)**
        * **Description:** Helper methods to set border styles and alignment for cells in the Excel sheet.


**5. Pseudo Code:**



```
// Class: WordChartGenerator

// Method: main(String[] args)
  1. Get Excel file path from configuration (Config.getXlsxPatternFilePath()).
  2. Call generateWordChartFromFile with the file path.

// Method: generateWordChartFromFile(String filePath)
  1. Open the Excel file using FileInputStream and create XSSFWorkbook object.
  2. Read schema dimensions from "Size" sheet.
  3. Retrieve schema data from "Pattern" sheet using readSchemaFromSheet().
  4. Remove existing "Word_Chart" sheet if it exists.
  5. Create a new sheet named "Word_Chart".
  6. Add header rows for "Row No." and "Word Chart".
  7. Iterate through each row of the schema:
     - Generate word chart string using generateRowChart() for current row.
     - Write the generated word chart to the "Word_Chart" sheet.
  8. Set appropriate styles for cells in the "Word_Chart" sheet.
  9. Autosize columns and write changes back to the file using FileOutputStream.

// Method: readSchemaFromSheet(Sheet sheet, int patternWidth, int patternHeight)
  1. Create a 2D String array (schema) of size patternHeight x patternWidth.
  2. Iterate through each row in the sheet:
     - For each cell in the row:
        - If cell is not null and its type is STRING:
           - Extract string value from the cell and store it in the schema array.
        - Otherwise, set the corresponding cell in the schema array to null.

// Method: generateRowChart(String[] row)
  1. Initialize count variable to 1.
  2. Iterate through each element in the row:
     - If current element is null or different from previous element OR it's the last element in the row:
        - Append the current element and its count (if greater than 1) to the result string.
        - Append a separator (" - ") to the result string.
        - Reset count to 1.
     - Otherwise, increment count.
  3. Return the generated word chart string.



```

**6. Dependencies and Libraries:**

* **Apache POI:** This program relies heavily on the Apache POI library for reading and writing Excel files.


