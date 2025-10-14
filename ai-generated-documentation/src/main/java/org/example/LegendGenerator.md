![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Get The Future You Want!

---
## LegendGenerator.java Documentation and Pseudocode

**1. Overview:**

The `LegendGenerator` Java program generates a legend for a pattern image based on its color palette. It reads an Excel file containing the pattern, extracts the colors used, and creates a new sheet in the same Excel file with a legend listing each unique color, its corresponding symbol (if available), bead number (if mapped), count of occurrences, and RGB values.

**2. Package/module name:** `org.example`

**3. Class/file name:** `LegendGenerator.java`

**4. Detailed Documentation:**

   - **`main(String[] args)`:**
     - **Description:** Entry point for the program. It reads the input parameters, loads bead number mappings from a CSV file, and calls the `generateLegend` function to create the legend.
     - **Parameters:** 
       - `args`: String array containing command-line arguments (not used in this code).
     - **Return Values:** None.
     - **Important Logic:**
       - Sets up paths for input files and output Excel file.
       - Loads bead number mappings from the CSV file using `loadBeadNumbersFromCsv`.
       - Calls `generateLegend` to create the legend in the specified Excel file.

   - **`generateLegend(String excelFilePath, Map<String, String> beadNumbers)`:**
     - **Description:** Generates the legend sheet within the provided Excel file. It reads the pattern sheet, extracts color information, and creates a new sheet with the legend data.
     - **Parameters:** 
       - `excelFilePath`: Path to the input/output Excel file.
       - `beadNumbers`: Map containing bead numbers mapped to RGB color values.
     - **Return Values:** None.
     - **Important Logic:**
       - Opens the Excel workbook and retrieves the "Pattern" sheet.
       - Removes any existing "Legend" sheet.
       - Iterates through each cell in the pattern sheet, extracting color information and symbols.
       - Creates a new sheet named "Legend" and populates it with legend data: symbol, color, bead number, count, and RGB values.
       - Saves the updated Excel file.

   - **`loadBeadNumbersFromCsv(String csvPath)`:**
     - **Description:** Reads bead number mappings from a CSV file and returns them as a map.
     - **Parameters:** 
       - `csvPath`: Path to the CSV file containing bead number mappings.
     - **Return Values:** A `Map<String, String>` where keys are RGB color values and values are corresponding bead numbers.
     - **Important Logic:**
       - Reads the CSV file using Apache Commons CSV library.
       - Parses each record and extracts "R", "G", "B" values and "Number".
       - Creates a map with RGB values as keys and bead numbers as values.

**5. Pseudo Code:**


```
// Class: LegendGenerator

// Method: main(String[] args)
  1. Set input file paths for pattern image, CSV file with bead numbers, and output Excel file.
  2. Try to load bead number mappings from the CSV file using loadBeadNumbersFromCsv function.
    - If successful, store the mapping in a map called "beadNumbers".
    - If an error occurs during loading, throw a RuntimeException.
  3. Call generateLegend function with the output Excel file path and the loaded bead numbers map.

// Method: generateLegend(String excelFilePath, Map<String, String> beadNumbers)
  1. Open the Excel workbook specified by "excelFilePath" using FileInputStream.
  2. Get the "Pattern" sheet from the workbook.
    - If the "Pattern" sheet doesn't exist, throw an IllegalArgumentException.
  3. Remove any existing sheet named "Legend".
  4. Create a new map called "colorLegend" to store color mappings and their symbols.
  5. Create a new map called "colorCount" to store the count of each color occurrence.
  6. Iterate through each row in the "Pattern" sheet.
    - For each cell in the row:
      1. Get the cell value (symbol).
      2. If the symbol is not empty or null:
        - Get the cell's fill color using CellStyle and XSSFColor.
        - Create a Color object from the RGB values of the fill color.
        - Update "colorLegend" with the color as key and symbol as value (if symbol exists).
        - Increment the count for the color in "colorCount".
  7. Create a new sheet named "Legend" in the workbook.
  8. Sort the entries in "colorLegend" alphabetically based on their values (symbols).
  9. Create header row in the "Legend" sheet with columns: Symbol, Color, Bead Number, QTY, R, G, B.
  10. Iterate through the sorted color legend entries.
    - For each entry:
      1. Create a new row in the "Legend" sheet.
      2. Set the symbol value in the first column.
      3. Apply the color as fill to the second column cell using XSSFCellStyle and XSSFColor.
      4. Set the bead number from the "beadNumbers" map based on the RGB values of the color.
      5. Set the count of the color occurrence in the fourth column.
      6. Set the R, G, B values in the fifth to seventh columns.
  10. Save the updated Excel workbook using FileOutputStream.
  11. Print a success message indicating that the legend has been generated and saved.

// Method: loadBeadNumbersFromCsv(String csvPath)
  1. Open the CSV file specified by "csvPath" using FileReader.
  2. Parse the CSV file using Apache Commons CSV library, assuming the first row is the header.
  3. Iterate through each record in the parsed CSV data.
    - Extract the "R", "G", "B" values and "Number" from each record.
    - Create a key-value pair in the map where the key is the concatenated RGB value (e.g., "255_0_0") and the value is the corresponding "Number".
  4. Return the populated map of bead numbers mapped to RGB values.



```

**6. Dependencies and Libraries:**


- **Apache Commons CSV:** Used for parsing the CSV file containing bead number mappings. Equivalent libraries in other languages include:
    - Python: `csv` module
    - Java: `opencsv` library
    - C++: `boost::spirit` or `rapidcsv` library

- **Apache POI:** Used for reading and writing Excel files. Equivalent libraries in other languages include:
    - Python: `openpyxl` or `xlrd/xlwt`
    - Java: `jxl` or `poi` (already used)
    - C++: `libxlsxwriter`



