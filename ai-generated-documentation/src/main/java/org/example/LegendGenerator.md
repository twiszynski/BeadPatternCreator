![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Get The Future You Want!

---
## LegendGenerator.java Documentation and Pseudocode

**1. Overview:**

The `LegendGenerator` Java program generates a legend for a pattern image based on its color palette. It reads an Excel file containing the pattern, extracts the colors used, and creates a new sheet in the same Excel file with a legend listing each unique color, its corresponding symbol (if available), bead number, count of occurrences, and RGB values.

**2. Package/module name:** `org.example`

**3. Class/file name:** `LegendGenerator.java`

**4. Detailed Documentation:**

   - **`main(String[] args)`:**
     - **Description:** Entry point for the program. It reads the input parameters, loads bead numbers from a CSV file, and calls the `generateLegend()` function to create the legend.
     - **Parameters:** 
       - `args`: String array containing command-line arguments (not used in this code).
     - **Return Values:** None.
     - **Important Logic:**
       - Sets up paths for input files and output Excel file.
       - Loads bead numbers from a CSV file using `loadBeadNumbersFromCsv()`.
       - Calls `generateLegend()` to create the legend in the specified Excel file.

   - **`generateLegend(String excelFilePath, Map<String, String> beadNumbers)`:**
     - **Description:** Generates the legend sheet within the provided Excel file.
     - **Parameters:** 
       - `excelFilePath`: Path to the input Excel file.
       - `beadNumbers`: A map containing RGB color values as keys and corresponding bead numbers as values.
     - **Return Values:** None.
     - **Important Logic:**
       - Opens the Excel workbook for reading and writing.
       - Retrieves the "Pattern" sheet from the workbook.
       - Removes any existing "Legend" sheet.
       - Iterates through each cell in the "Pattern" sheet, extracting color information and symbols.
       - Creates a new "Legend" sheet with headers for Symbol, Color, Bead Number, QTY, R, G, B.
       - Populates the legend sheet with color information, bead numbers, and RGB values.
       - Saves the updated Excel file.

   - **`loadBeadNumbersFromCsv(String csvPath)`:**
     - **Description:** Reads bead number information from a CSV file and returns it as a map.
     - **Parameters:** 
       - `csvPath`: Path to the input CSV file.
     - **Return Values:** A map where keys are RGB color values (e.g., "255_0_0") and values are corresponding bead numbers.
     - **Important Logic:**
       - Reads the CSV file using Apache Commons CSV library.
       - Parses each record, extracting RGB values and bead numbers.
       - Stores the information in a map for later use.

**5. Pseudo Code:**


```
// Class: LegendGenerator

// Method: main(String[] args)
  1. Get input file paths from command line arguments or predefined constants.
  2. Load bead numbers from CSV file using loadBeadNumbersFromCsv() function.
  3. Call generateLegend() function with Excel file path and loaded bead numbers.

// Method: generateLegend(String excelFilePath, Map<String, String> beadNumbers)
  1. Open the Excel workbook for reading and writing.
  2. Get the "Pattern" sheet from the workbook.
    - If no "Pattern" sheet exists, throw an exception.
  3. Remove any existing "Legend" sheet.
  4. Create a new "Legend" sheet in the workbook.
  5. Iterate through each cell in the "Pattern" sheet:
    - Get the cell's color information (fill color).
    - Extract the symbol associated with the cell (if available).
    - Update the legend map with the color, symbol, and count of occurrences.
  6. Populate the "Legend" sheet with headers for Symbol, Color, Bead Number, QTY, R, G, B.
  7. Iterate through the legend map:
    - For each entry (color, symbol, bead number):
      - Create a new row in the "Legend" sheet.
      - Fill in the cell values with the corresponding information.
  8. Save the updated Excel file.

// Method: loadBeadNumbersFromCsv(String csvPath)
  1. Open the CSV file for reading.
  2. Read each record from the CSV file using Apache Commons CSV library.
  3. Extract RGB color values and bead numbers from each record.
  4. Store the information in a map where keys are RGB color values and values are bead numbers.
  5. Return the map of bead numbers.



```

**6. Dependencies and Libraries:**


- **Apache Commons CSV:** Used for parsing the CSV file containing bead number information. 
- **Apache POI:** Used for reading and writing Excel files.
- **Java AWT (Abstract Window Toolkit):** Used for color handling.




