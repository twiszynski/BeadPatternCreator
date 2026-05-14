![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Make it real.

---
## WordChartGenerator Code Documentation

**1. Overview:**

The `WordChartGenerator` class generates a word chart diagram from a predefined Excel template. It reads a pattern schema from a specific sheet in the input Excel file and uses it to create a visual representation of word frequencies within rows. The output is written back to the same Excel file, adding a new sheet containing the generated word chart.

**2. Package/module name:** `org.example`

**3. Class/file name:** `WordChartGenerator.java`

**4. Class diagram name:** `WordChartGenerator_diagram.svg` (This should be provided as an image file)

**5. Detailed Documentation:**

   - **Class: `WordChartGenerator`**
     -  **Static Method: `main(String[] args)`**
        - Description: Entry point for the program. It reads the input Excel file path from a configuration object (`Config`) and calls the `generateWordChartFromFile()` method to process the file.
        - Parameters: 
           - `args`: String array containing command line arguments (not used in this code).
        - Return Value: None
        - Important Logic: Sets up the input file path, handles potential exceptions during file reading and processing.

     - **Static Method: `generateWordChartFromFile(String filePath)`**
        - Description: Generates the word chart diagram from the specified Excel file. 
        - Parameters:
           - `filePath`: String representing the path to the input Excel file.
        - Return Value: None
        - Important Logic:
          - Reads the size of the pattern schema from a dedicated sheet (`Size`).
          - Retrieves the pattern schema from the `Pattern` sheet.
          - Removes any existing `Word_Chart` sheet in the workbook.
          - Creates a new sheet named `Word_Chart`.
          - Generates header row for the word chart.
          - Iterates through each row of the pattern schema and generates a word chart string using `generateRowChart()`.
          - Writes the generated word charts to the `Word_Chart` sheet.
          - Sets appropriate cell styles for headers and word charts.
          - Autosizes columns based on content.
          - Saves the modified workbook back to the original file path.

     - **Private Method: `readSchemaFromSheet(Sheet sheet, int patternWidth, int patternHeight)`**
        - Description: Reads the pattern schema from a given Excel sheet.
        - Parameters:
           - `sheet`: The Excel sheet object containing the schema data.
           - `patternWidth`: Integer representing the width of the schema.
           - `patternHeight`: Integer representing the height of the schema.
        - Return Value: A 2D String array representing the pattern schema.
        - Important Logic: Iterates through each row and cell in the sheet, extracting string values from cells and storing them in a 2D array. Handles empty cells by setting their value to `null`.

     - **Private Method: `generateRowChart(String[] row)`**
        - Description: Generates a word chart string for a single row of the pattern schema.
        - Parameters:
           - `row`: A String array representing a single row of the pattern schema.
        - Return Value: A String containing the generated word chart representation.
        - Important Logic: Iterates through each element in the row, counting consecutive occurrences of the same word and appending them to the result string with their counts enclosed in parentheses.

     - **Private Methods: `setBorderedAndCenteredStyle(XSSFCellStyle style)` and `setBorderedAndLeftStyle(XSSFCellStyle style)`**
        - Description: These methods set specific border styles and alignment properties for cells within the Excel sheet. 
        - Parameters:
           - `style`: An XSSFCellStyle object representing the cell style to be modified.

**6. Pseudo Code:**


```
// Class: WordChartGenerator

// Method: main(String[] args)
  1. Get input file path from Config object.
  2. Call generateWordChartFromFile() method with the file path.

// Method: generateWordChartFromFile(String filePath)
  1. Open the Excel file using FileInputStream.
  2. Read size of pattern schema from "Size" sheet.
  3. Retrieve pattern schema from "Pattern" sheet.
  4. Remove existing "Word_Chart" sheet (if present).
  5. Create new "Word_Chart" sheet.
  6. Set header row for the word chart.
  7. Iterate through each row of the pattern schema:
     - Call generateRowChart() method to get word chart string for current row.
     - Write the generated word chart string to the corresponding cell in "Word_Chart" sheet.
  8. Apply appropriate cell styles (headers and word charts).
  9. Autosize columns based on content.
  10. Save the modified Excel file using FileOutputStream.

// Method: readSchemaFromSheet(Sheet sheet, int patternWidth, int patternHeight)
  1. Create a 2D String array to store the schema (patternWidth x patternHeight).
  2. Iterate through each row of the sheet:
     - For each cell in the row:
        - If cell is not empty and contains string value:
           - Store the string value in the corresponding position in the 2D array.
        - Otherwise, set the cell value to null.
  3. Return the 2D String array representing the schema.

// Method: generateRowChart(String[] row)
  1. Initialize a StringBuilder object to store the word chart string.
  2. Iterate through each element in the row:
     - If current element is different from the previous one or it's the last element:
        - Append the current element and its count (if greater than 1) to the StringBuilder.
        - Add a separator (" - ") after each word chart entry.
  3. Return the generated word chart string.



```

**7. Dependencies and Libraries:**


* **Apache POI:** This library is used for reading and writing Excel files in Java. It provides classes for working with different Excel file formats, including XLSX. 

   - **Equivalent libraries in other languages:**
      - Python: `openpyxl`, `xlrd`, `xlsxwriter`
      - C++: `libxlsxdom`, `poixml`



