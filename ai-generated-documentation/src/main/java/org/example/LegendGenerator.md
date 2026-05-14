![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Make it real.

---
## LegendGenerator Code Documentation

**1. Overview:**

The `LegendGenerator` class generates a legend for a pattern image based on a CSV file containing bead color information and corresponding numbers. It reads the pattern image from an Excel file, extracts the colors used in the pattern, and creates a new sheet in the same Excel file containing the legend. The legend lists each unique color used in the pattern, its corresponding symbol (if available), bead number, count of occurrences, and RGB values.

**2. Package/module name:** `org.example`

**3. Class/file name:** `LegendGenerator.java`

**4. Class diagram name:** `LegendGenerator_diagram.svg`

**5. Detailed Documentation:**

   - **`main(String[] args)`:**
     - **Description:** Entry point of the program. It reads the input parameters, loads bead number information from a CSV file, and calls the `generateLegend()` method to create the legend.
     - **Parameters:** 
       - `args`: Command line arguments (not used in this code).
     - **Return Values:** None.
     - **Important Logic:**
       - Sets default paths for input files based on the image pattern file name.
       - Loads bead number information from the CSV file using `loadBeadNumbersFromCsv()`.
       - Calls `generateLegend()` to create the legend in the specified Excel file.

   - **`generateLegend(String excelFilePath, Map<String, String> beadNumbers)`:**
     - **Description:** Generates the legend for the pattern image and saves it to the specified Excel file.
     - **Parameters:** 
       - `excelFilePath`: Path to the Excel file containing the pattern image.
       - `beadNumbers`: A map containing bead numbers corresponding to their RGB values.
     - **Return Values:** None.
     - **Important Logic:**
       - Opens the Excel file for reading and writing.
       - Retrieves the "Pattern" sheet from the workbook.
       - Removes any existing "Legend" sheet.
       - Iterates through each cell in the "Pattern" sheet, extracting color information and symbols.
       - Creates a new sheet named "Legend".
       - Sorts the legend entries alphabetically based on symbol.
       - Generates rows for each unique color, including symbol, color fill, bead number, count, and RGB values.
       - Saves the updated Excel file.

   - **`loadBeadNumbersFromCsv(String csvPath)`:**
     - **Description:** Reads bead number information from a CSV file and returns it as a map.
     - **Parameters:** 
       - `csvPath`: Path to the CSV file containing bead number information.
     - **Return Values:** A map where keys are RGB values (e.g., "255_0_0") and values are corresponding bead numbers.
     - **Important Logic:**
       - Parses the CSV file using Apache Commons CSV library.
       - Extracts "R", "G", "B" columns and combines them to create a unique key for each color.
       - Retrieves the "Number" column value as the corresponding bead number.

**6. Pseudo Code:**


```
// Class: LegendGenerator

// Method: main(String[] args)
  1. Get image pattern file name from command line arguments (or use default).
  2. Construct paths for input files based on image pattern file name.
  3. Load bead number information from CSV file using loadBeadNumbersFromCsv().
  4. Call generateLegend() to create the legend in the specified Excel file.

// Method: generateLegend(String excelFilePath, Map<String, String> beadNumbers)
  1. Open the Excel file for reading and writing.
  2. Get the "Pattern" sheet from the workbook.
    - If no "Pattern" sheet exists, throw an exception.
  3. Remove any existing "Legend" sheet.
  4. Create a new sheet named "Legend".
  5. Iterate through each cell in the "Pattern" sheet:
    - Extract color information (e.g., fill color) from the cell.
    - If color is valid, add it to a map of colors and their corresponding symbols/data.
  6. Sort the legend entries alphabetically based on symbol.
  7. Create rows for each unique color in the "Legend" sheet:
    - Add symbol, color fill, bead number (from beadNumbers map), count, and RGB values.
  8. Save the updated Excel file.

// Method: loadBeadNumbersFromCsv(String csvPath)
  1. Open the CSV file for reading.
  2. Parse the CSV file using Apache Commons CSV library.
  3. Iterate through each row in the CSV file:
    - Extract "R", "G", "B" columns and combine them to create a unique key (RGB value).
    - Retrieve the "Number" column value as the corresponding bead number.
  4. Return a map where keys are RGB values and values are bead numbers.



```

**7. Dependencies and Libraries:**


* **Apache Commons CSV:** Used for parsing the CSV file containing bead number information. 
   - Equivalent libraries in other languages:
      - Python: `csv` module
      - Java: `org.apache.commons.csv` (already used)
      - C++: `boost::spirit` or `libcsv`

* **Apache POI:** Used for reading and writing Excel files. 
   - Equivalent libraries in other languages:
      - Python: `openpyxl`, `xlrd`, `xlsxwriter`
      - Java: `org.apache.poi` (already used)
      - C++: `liboffice` or `ooxml`



