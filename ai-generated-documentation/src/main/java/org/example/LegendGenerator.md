![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Make it real.

---
## LegendGenerator.java Documentation & Pseudocode

**1. Overview:**

The `LegendGenerator` class generates a legend for a pattern image based on its color palette. It reads a CSV file containing bead numbers associated with specific RGB colors, then analyzes an Excel spreadsheet containing the pattern image. The generated legend lists each unique color found in the pattern, along with its corresponding symbol, bead number (if available), count of occurrences, and RGB values.

**2. Package/module name:** `org.example`

**3. Class/file name:** `LegendGenerator.java`

**4. Detailed Documentation:**

* **`main(String[] args)`:**
    - **Description:** Entry point for the program. It sets up input and output paths, loads bead numbers from a CSV file, and calls the `generateLegend()` method to create the legend.
    - **Parameters:** 
        - `args`: Command line arguments (not used in this code).
    - **Return Values:** None.
    - **Important Logic:**
        - Handles potential exceptions during file reading and processing.

* **`generateLegend(String excelFilePath, Map<String, String> beadNumbers)`:**
    - **Description:** Generates the legend based on the provided Excel spreadsheet and bead number mapping.
    - **Parameters:**
        - `excelFilePath`: Path to the Excel file containing the pattern image.
        - `beadNumbers`: A map associating RGB color values with corresponding bead numbers.
    - **Return Values:** None.
    - **Important Logic:**
        - Reads the Excel spreadsheet and extracts color information from cells.
        - Creates a legend sheet in the Excel file, listing each unique color along with its symbol, bead number (if available), count, and RGB values.
        - Sorts the legend alphabetically by symbol.
        - Saves the updated Excel file.

* **`loadBeadNumbersFromCsv(String csvPath)`:**
    - **Description:** Reads bead numbers from a CSV file and returns them as a map associating RGB color values with bead numbers.
    - **Parameters:**
        - `csvPath`: Path to the CSV file containing bead number information.
    - **Return Values:** A map of `String` (RGB color) to `String` (bead number).
    - **Important Logic:**
        - Parses the CSV file using Apache Commons CSV library.
        - Extracts RGB values and corresponding bead numbers from each record.

**5. Pseudocode:**


```
// Class: LegendGenerator

// Method: main(args)
  1. Get input paths for image pattern Excel file and bead number CSV file.
  2. Load bead numbers from CSV file into a map (beadNumbers).
  3. Call generateLegend() method with the Excel file path and beadNumbers map.

// Method: generateLegend(excelFilePath, beadNumbers)
  1. Open the Excel file specified by excelFilePath.
  2. Get the "Pattern" sheet from the workbook.
    - If the sheet doesn't exist, throw an exception.
  3. Remove any existing "Legend" sheet if it exists.
  4. Create a new sheet named "Legend".
  5. Initialize two maps: 
    - colorLegend: Maps colors to their corresponding symbols in the pattern.
    - colorCount: Stores the count of each unique color found.
  6. Iterate through each row and cell in the "Pattern" sheet.
    - If a cell contains a symbol (non-empty string):
      1. Get the cell's fill color.
      2. Add the color to the colorLegend map, associating it with the symbol.
      3. Update the count for that color in the colorCount map.
  7. Create header row in the "Legend" sheet: Symbol, Color, Bead Number, QTY, R, G, B.
  8. Sort the entries in colorLegend alphabetically by symbol.
  9. Iterate through each sorted entry (color, symbol) in colorLegend:
    - Create a new row in the "Legend" sheet for each color.
    - Set the cell values for Symbol, Color (using the color's fill style), Bead Number (from beadNumbers map), QTY (count from colorCount map), and RGB values.
  10. Save the updated Excel file.



```

**6. Dependencies and Libraries:**


* **Apache Commons CSV:** Used for parsing the CSV file containing bead number information.
* **Apache POI:** Used for reading and writing Excel files. 




