![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Get The Future You Want!

---
## WordChartGenerator.java Documentation and Pseudocode

**1. Overview:**

This Java program generates a word chart from an Excel spreadsheet. It reads a predefined schema from a sheet named "Pattern" and uses it to analyze data in another sheet, typically named "Size". The program then creates a new sheet named "Word_Chart" within the same Excel file, displaying the generated word charts for each row of data.

**2. Package/module name:** org.example

**3. Class/file name:** WordChartGenerator.java

**4. Detailed Documentation:**

* **`main(String[] args)`:**
    - **Description:** The main method of the program. It reads the path to the Excel file from a configuration file (likely `Config.java`), calls the `generateWordChartFromFile()` method to process the file, and prints a success message upon completion.
    - **Parameters:** 
        - `args`: An array of strings representing command-line arguments (not used in this code).
    - **Return Values:** None

* **`generateWordChartFromFile(String filePath)`:**
    - **Description:** This method reads the Excel file specified by `filePath`, processes its contents, and generates the word charts. It handles reading the schema from the "Pattern" sheet, data from the "Size" sheet, and writing the final word chart to a new sheet named "Word_Chart".
    - **Parameters:** 
        - `filePath`: A string representing the path to the Excel file.
    - **Return Values:** None

* **`readSchemaFromSheet(Sheet sheet, int patternWidth, int patternHeight)`:**
    - **Description:** Reads the schema from the specified "Pattern" sheet. It iterates through each row and column of the sheet, extracting the values (strings) from each cell and storing them in a 2D array (`schema`).
    - **Parameters:** 
        - `sheet`: The "Pattern" sheet object from the Excel workbook.
        - `patternWidth`: An integer representing the width of the schema.
        - `patternHeight`: An integer representing the height of the schema.
    - **Return Values:** A 2D array (`schema`) containing the extracted values from the "Pattern" sheet.

* **`generateRowChart(String[] row)`:**
    - **Description:** Generates a word chart for a single row of data based on the provided `row` array and the schema defined in `readSchemaFromSheet()`. It analyzes the sequence of characters in the row, groups consecutive identical characters, and formats them into a string representation.
    - **Parameters:** 
        - `row`: A string array representing a single row of data.
    - **Return Values:** A string containing the generated word chart for the given row.

* **`setBorderedAndCenteredStyle(XSSFCellStyle style)`:**
    - **Description:** Sets border styles and alignment for a cell style object. It applies thin borders to all sides of the cell and centers both horizontally and vertically.
    - **Parameters:** 
        - `style`: An XSSFCellStyle object representing the cell style.
    - **Return Values:** None

* **`setBorderedAndLeftStyle(XSSFCellStyle style)`:**
    - **Description:** Sets border styles and alignment for a cell style object. It applies thin borders to all sides of the cell and aligns text to the left while centering vertically.
    - **Parameters:** 
        - `style`: An XSSFCellStyle object representing the cell style.
    - **Return Values:** None

**5. Pseudo Code:**



```
// Class: WordChartGenerator

// Method: main(String[] args)
  1. Get Excel file path from configuration (Config.getXlsxPatternFilePath()).
  2. Call generateWordChartFromFile() with the file path.
  3. Print success message.

// Method: generateWordChartFromFile(String filePath)
  1. Open the Excel file using FileInputStream and XSSFWorkbook.
  2. Get the "Size" sheet to read dimensions of the schema.
    - If "Size" sheet is not found, print an error message and exit.
  3. Read width and height from the "Size" sheet (second row).
  4. Get the "Pattern" sheet to read the schema.
    - If "Pattern" sheet is not found, throw an IllegalArgumentException.
  5. Read the schema using readSchemaFromSheet() function.
  6. Remove existing "Word_Chart" sheet if it exists.
  7. Create a new sheet named "Word_Chart".
  8. Set up header row with "Row No." and "Word Chart" columns.
    - Apply centered style to header cells.
  9. Generate word charts for each row in the data using generateRowChart() function.
    - Write the generated word chart to the corresponding cell in the "Word_Chart" sheet.
    - Apply left aligned style with wrap text to word chart cells.
  10. Adjust column widths for proper display.
  11. Save changes to the Excel file using FileOutputStream.
  12. Print success message.

// Method: readSchemaFromSheet(Sheet sheet, int patternWidth, int patternHeight)
  1. Iterate through each row of the "Pattern" sheet (starting from row 1).
    - For each row:
      1. Create a new string array to store values for this row.
      2. Iterate through each column of the current row (starting from column 1).
        - Get the cell value and check if it's a string.
        - If it's a string, add it to the current row array.
        - Otherwise, add null to indicate an empty cell.
      3. Add the completed row array to the schema array.

// Method: generateRowChart(String[] row)
  1. Initialize count variable to 1.
  2. Iterate through each element in the `row` array.
    - If current element is null or different from the previous element, append the previous element with its count (if greater than 1) and a separator (" - ") to the result string.
      - Reset count to 1.
    - Otherwise, increment the count.
  3. Return the generated word chart string.



```

**6. Dependencies and Libraries:**

* **Apache POI:** This library is used for reading and writing Excel files in Java. It provides classes for working with different Excel file formats (like .xlsx).


