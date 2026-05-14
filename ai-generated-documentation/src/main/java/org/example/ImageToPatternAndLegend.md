![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Make it real.

---
## ImageToPatternAndLegend Documentation

**1. Overview:**

The `ImageToPatternAndLegend` class processes an image file and generates an Excel spreadsheet containing a pattern representation of the image, a legend with color information, bead numbers, and names, as well as dimensions of the original image. 

**2. Package/module name:**

org.example

**3. Class/file name:**

ImageToPatternAndLegend.java

**4. Class diagram name:**

ImageToPatternAndLegend_diagram.svg (This file should be provided separately)

**5. Detailed Documentation:**


* **`main(String[] args)`:**
    - **Description:** This is the entry point of the program. It reads an image file, generates the pattern and legend data, and saves it to an Excel spreadsheet.
    - **Parameters:** `args`: An array of strings containing command-line arguments (not used in this code).
    - **Return Values:** None.
    - **Important Logic:**
        - Reads image path, excel output path, and CSV palette path from configuration files.
        - Loads the image using ImageIO.
        - Creates an XSSFWorkbook to store the Excel data.
        - Calls helper functions to load bead numbers and names from a CSV file.
        - Generates the pattern sheet with pixel color codes based on the image.
        - Generates the legend sheet with color information, bead numbers, names, quantities, weight, and RGB values.
        - Sets up conditional formatting for highlighting cells based on color in the pattern sheet.
        - Saves the Excel workbook to the specified path.

* **`loadBeadNumbersFromCsv(String csvPath)`:**
    - **Description:** Reads bead numbers from a CSV file and stores them in a map keyed by RGB values.
    - **Parameters:** `csvPath`: The path to the CSV file containing bead number information.
    - **Return Values:** A Map<String, String> where keys are RGB values and values are corresponding bead numbers.
    - **Important Logic:**
        - Parses the CSV file using Apache Commons CSV library.
        - Extracts RGB values and bead numbers from each record.
        - Stores the mapping in a HashMap.

* **`loadBeadNamesFromCsv(String csvPath)`:**
    - **Description:** Reads bead names from a CSV file and stores them in a map keyed by RGB values.
    - **Parameters:** `csvPath`: The path to the CSV file containing bead name information.
    - **Return Values:** A Map<String, String> where keys are RGB values and values are corresponding bead names.
    - **Important Logic:**
        - Parses the CSV file using Apache Commons CSV library.
        - Extracts RGB values and bead names from each record.
        - Stores the mapping in a HashMap.

* **`setBorderedAndCenteredStyle(XSSFCellStyle style)`:**
    - **Description:** Sets border and alignment properties for an XSSFCellStyle object.
    - **Parameters:** `style`: The XSSFCellStyle object to modify.
    - **Return Values:** None.
    - **Important Logic:**
        - Sets thin borders on all sides of the cell.
        - Centers both horizontally and vertically.



**6. Pseudo Code:**

```
// Class: ImageToPatternAndLegend

// Method: main(String[] args)
  1. Get image path, excel output path, and CSV palette path from configuration files.
  2. Load the image using ImageIO.read(imagePath).
  3. Create an XSSFWorkbook to store Excel data.
  4. Call loadBeadNumbersFromCsv() to get bead numbers from CSV.
  5. Call loadBeadNamesFromCsv() to get bead names from CSV.
  6. Create a pattern sheet in the workbook.
    - Iterate over each pixel in the image.
      - Get RGB values of the pixel.
      - Determine corresponding color code and bead information from loaded maps.
      - Add cell to the pattern sheet with color code.
  7. Create a legend sheet in the workbook.
    - Add header row for "Symbol", "Color", "Bead #", "Color Name", "Qty [pcs]", "Weight [g]", "R", "G", "B".
    - Iterate over unique colors in the image.
      - Get RGB values and corresponding bead information from loaded maps.
      - Add a row to the legend sheet with color code, name, number, quantity, weight, and RGB values.
  8. Set conditional formatting for highlighting cells based on color in the pattern sheet.
  9. Save the workbook to the specified excel output path.

// Method: loadBeadNumbersFromCsv(String csvPath)
  1. Open the CSV file using FileReader.
  2. Parse the CSV file using Apache Commons CSV library.
  3. Create a HashMap to store bead numbers keyed by RGB values.
  4. Iterate over each record in the CSV file.
    - Extract RGB values and bead number from the record.
    - Store the mapping in the HashMap.
  5. Return the HashMap.

// Method: loadBeadNamesFromCsv(String csvPath)
  1. Open the CSV file using FileReader.
  2. Parse the CSV file using Apache Commons CSV library.
  3. Create a HashMap to store bead names keyed by RGB values.
  4. Iterate over each record in the CSV file.
    - Extract RGB values and bead name from the record.
    - Store the mapping in the HashMap.
  5. Return the HashMap.

// Method: setBorderedAndCenteredStyle(XSSFCellStyle style)
  1. Set thin borders on all sides of the cell using BorderStyle.THIN.
  2. Center both horizontally and vertically using HorizontalAlignment.CENTER and VerticalAlignment.CENTER.



```


