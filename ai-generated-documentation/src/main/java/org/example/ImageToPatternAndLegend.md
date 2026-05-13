![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Make it real.

---
## ImageToPatternAndLegend.java Documentation & Pseudocode

**1. Overview:**

The `ImageToPatternAndLegend.java` program processes an image file and generates two Excel files: one containing a pattern representation of the image based on color distribution, and another containing a legend associating each unique color with its corresponding bead number, name, quantity, weight, and RGB values. 

**2. Package/module name:** `org.example`

**3. Class/file name:** `ImageToPatternAndLegend.java`

**4. Detailed Documentation:**

* **main(String[] args):**
    - **Description:** This is the entry point of the program. It reads an image file, generates the pattern and legend Excel files, and handles any potential errors during the process.
    - **Parameters:** `args`: An array of strings containing command-line arguments (not used in this code).
    - **Return Values:** None.
    - **Important Logic:**
        - Reads image file path from configuration (`Config`).
        - Reads CSV files containing bead numbers and names from configuration (`Config`).
        - Creates a new Excel workbook for the pattern sheet, legend sheet, and dimensions sheet.
        - Loads color information from the CSV files into maps.
        - Iterates through each pixel of the image, assigning a unique symbol based on its color.
        - Writes the pattern data to the pattern sheet in Excel.
        - Generates a legend for each unique color, including bead number, name, quantity, weight, and RGB values.
        - Writes the legend data to the legend sheet in Excel.
        - Writes dimensions of the image (width, height) in both centimeters and inches to the dimensions sheet.
        - Saves the Excel workbook to the specified file path.

* **loadBeadNumbersFromCsv(String csvPath):**
    - **Description:** Reads bead numbers from a CSV file and stores them in a map keyed by RGB values.
    - **Parameters:** `csvPath`: The path to the CSV file containing bead number information.
    - **Return Values:** A map where keys are RGB strings (e.g., "255_0_0") and values are corresponding bead numbers.

* **loadBeadNamesFromCsv(String csvPath):**
    - **Description:** Reads bead names from a CSV file and stores them in a map keyed by RGB values.
    - **Parameters:** `csvPath`: The path to the CSV file containing bead name information.
    - **Return Values:** A map where keys are RGB strings (e.g., "255_0_0") and values are corresponding bead names.

* **setBorderedAndCenteredStyle(XSSFCellStyle style):**
    - **Description:** Sets border styles and alignment for a given cell style.
    - **Parameters:** `style`: The XSSFCellStyle object to modify.


**5. Pseudo Code:**



```
// Class: ImageToPatternAndLegend

// Method: main(String[] args)
  1. Get image file path from configuration (Config).
  2. Get CSV paths for bead numbers and names from configuration (Config).
  3. Create new Excel workbook with sheets for pattern, legend, and dimensions.
  4. Load bead numbers from CSV into a map keyed by RGB values.
  5. Load bead names from CSV into a map keyed by RGB values.
  6. Read image file using ImageIO.read().
  7. Create maps to store color information (legend) and pattern data.
  8. Iterate through each pixel of the image:
      - Get RGB values for the current pixel.
      - If the color is not already in the legend map:
          - Generate a unique symbol for the color.
          - Add the color, symbol, bead number, name, and other relevant information to the legend map.
      - Write the symbol to the pattern sheet in Excel based on pixel coordinates.
  9. Create legend rows in Excel using data from the legend map.
  10. Write dimensions of the image (width, height) in both centimeters and inches to the dimensions sheet.
  11. Save the Excel workbook to the specified file path.

// Method: loadBeadNumbersFromCsv(String csvPath)
  1. Open the CSV file using FileReader.
  2. Parse the CSV data using CSVFormat.DEFAULT with header row.
  3. Iterate through each record in the CSV:
      - Extract RGB values and bead number from the record.
      - Store the bead number in a map keyed by RGB values.
  4. Return the map of bead numbers.

// Method: loadBeadNamesFromCsv(String csvPath)
  1. Open the CSV file using FileReader.
  2. Parse the CSV data using CSVFormat.DEFAULT with header row.
  3. Iterate through each record in the CSV:
      - Extract RGB values and bead name from the record.
      - Store the bead name in a map keyed by RGB values.
  4. Return the map of bead names.

// Method: setBorderedAndCenteredStyle(XSSFCellStyle style)
  1. Set top, bottom, left, and right borders to THIN for the given style.
  2. Align text horizontally to CENTER and vertically to CENTER for the given style.



```


