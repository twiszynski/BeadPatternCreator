![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Make it real.

---
## WordChartGenerator.java Documentation & Pseudocode

**1. Overview:**

This Java program generates a word chart from an Excel file. It reads a predefined schema from a sheet named "Pattern" and uses it to generate a visual representation of words grouped by their frequency within the data. The output is written back into the same Excel file, adding a new sheet named "Word_Chart".

**2. Package/module name:** org.example

**3. Class/file name:** WordChartGenerator.java

**4. Detailed Documentation:**


* **Class: `WordChartGenerator`**
    *  **Method: `main(String[] args)`**
        *   **Description:** Entry point of the program. It reads the Excel file path from a configuration file (`Config.getXlsxPatternFilePath()`) and calls the `generateWordChartFromFile()` method to process the file.
        *   **Parameters:** 
            *   `args`: String array containing command-line arguments (not used in this code).
        *   **Return Values:** None
        *   **Important Logic:**  Sets up the program execution by reading the Excel file path and initiating the chart generation process.

    * **Method: `generateWordChartFromFile(String filePath)`**
        *   **Description:** Reads an Excel file, extracts the schema from a "Pattern" sheet, generates word charts based on the schema and data in other sheets, and writes the results back to the same Excel file.
        *   **Parameters:** 
            *   `filePath`: String representing the path to the input Excel file.
        *   **Return Values:** None
        *   **Important Logic:**
            *   Reads the dimensions of the word chart from a "Size" sheet.
            *   Reads the schema from the "Pattern" sheet, which defines how words are grouped and displayed in the chart.
            *   Iterates through each row of data in the Excel file (excluding the header row).
            *   Generates a word chart for each row using the `generateRowChart()` method.
            *   Writes the generated word charts to a new sheet named "Word_Chart" in the Excel file.

    * **Method: `readSchemaFromSheet(Sheet sheet, int patternWidth, int patternHeight)`**
        *   **Description:** Reads the schema from the specified Excel sheet and returns it as a 2D string array.
        *   **Parameters:** 
            *   `sheet`: The Excel sheet object containing the schema data.
            *   `patternWidth`: Integer representing the width of the schema.
            *   `patternHeight`: Integer representing the height of the schema.
        *   **Return Values:** A 2D string array representing the schema.
        *   **Important Logic:** Iterates through each row and cell in the sheet, extracting the data and storing it in the schema array.

    * **Method: `generateRowChart(String[] row)`**
        *   **Description:** Generates a word chart for a single row of data based on the provided schema.
        *   **Parameters:** 
            *   `row`: A string array representing a single row of data.
        *   **Return Values:** A string containing the generated word chart representation.
        *   **Important Logic:** Iterates through the data in the row, grouping consecutive identical words and appending them to the output string with their frequency count.

    * **Static Methods: `setBorderedAndCenteredStyle(XSSFCellStyle style)` and `setBorderedAndLeftStyle(XSSFCellStyle style)`**
        *   **Description:** These methods set specific border styles and alignment properties for cells in the Excel sheet.


**5. Pseudo Code:**



```
// Class: WordChartGenerator

// Method: main(String[] args)
  1. Get Excel file path from configuration (Config.getXlsxPatternFilePath()).
  2. Call generateWordChartFromFile() method with the file path.

// Method: generateWordChartFromFile(String filePath)
  1. Open the Excel file using FileInputStream and XSSFWorkbook.
  2. Read dimensions of word chart from "Size" sheet.
  3. Read schema from "Pattern" sheet.
  4. Create a new sheet named "Word_Chart".
  5. Iterate through each row in the data sheets (excluding header).
    - Call generateRowChart() method to create word chart for current row.
    - Write the generated word chart to the "Word_Chart" sheet.
  6. Save changes to the Excel file using FileOutputStream and XSSFWorkbook.write().

// Method: readSchemaFromSheet(Sheet sheet, int patternWidth, int patternHeight)
  1. Create a 2D string array (schema) of size patternHeight x patternWidth.
  2. Iterate through each row in the sheet.
    - For each cell:
      - If cell is not null and contains text data:
        - Store the cell value in the schema array at corresponding position.
      - Otherwise, store null in the schema array.

// Method: generateRowChart(String[] row)
  1. Initialize variables: count (for consecutive word occurrences), result string.
  2. Iterate through each element in the row.
    - If current element is different from previous or it's the last element:
      - Append the current element and its count to the result string.
      - Reset count to 1.
    - Otherwise, increment count.
  3. Return the generated word chart string.



```

**6. Dependencies and Libraries:**


* **Apache POI:** This library is used for reading and writing Excel files in Java. It provides classes for working with different Excel file formats (e.g., .xlsx). 
    * **Equivalent libraries in other languages:**
        * Python: `openpyxl`, `xlrd`, `xlwt`
        * C++: `libxl`, `poppler-utils`
        * JavaScript: `xlsx`, `sheetjs`



