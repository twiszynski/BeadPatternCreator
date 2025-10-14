![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Get The Future You Want!

---
## ImageToPatternAndLegend.java Documentation and Pseudocode

**1. Overview:**

This Java program processes an image and generates two Excel files: one containing a pattern representation of the image with color codes and another containing a legend associating each color code with its corresponding bead number, name, quantity, weight, and RGB values. 

The program reads the image using ImageIO, extracts pixel colors, and maps them to unique symbols. It then creates an Excel sheet representing the image pattern, where each cell contains the symbol corresponding to the pixel color. A separate sheet is created for the legend, listing each color code, its bead number, name, quantity, weight, and RGB values.

**2. Package/module name:** org.example

**3. Class/file name:** ImageToPatternAndLegend.java

**4. Detailed Documentation:**


* **`main(String[] args)`:**
    - **Description:** This is the entry point of the program. It reads the image path, Excel output path, and CSV palette path from configuration files. Then it processes the image, generates the pattern and legend sheets in Excel, and saves the files.
    - **Parameters:** `args`: An array of strings containing command-line arguments (not used in this code).
    - **Return Values:** None.
    - **Important Logic:**
        - Reads image using ImageIO.
        - Creates an XSSFWorkbook object to represent the Excel file.
        - Loads bead numbers and names from a CSV file.
        - Iterates through each pixel in the image, assigning a unique symbol based on its color.
        - Creates two sheets: one for the pattern and another for the legend.
        - Populates the sheets with data extracted from the image and CSV file.
        - Saves the Excel workbook to the specified path.

* **`loadBeadNumbersFromCsv(String csvPath)`:**
    - **Description:** Reads bead numbers from a CSV file, mapping each RGB color combination to its corresponding bead number.
    - **Parameters:** `csvPath`: The path to the CSV file containing bead information.
    - **Return Values:** A HashMap where keys are RGB color combinations (as strings) and values are bead numbers (as strings).
    - **Important Logic:**
        - Parses the CSV file using the CSVFormat class from Apache Commons CSV library.
        - Iterates through each record in the CSV, extracting the RGB values and bead number.
        - Stores the mapping between RGB color combinations and bead numbers in a HashMap.

* **`loadBeadNamesFromCsv(String csvPath)`:**
    - **Description:** Reads bead names from a CSV file, mapping each RGB color combination to its corresponding bead name.
    - **Parameters:** `csvPath`: The path to the CSV file containing bead information.
    - **Return Values:** A HashMap where keys are RGB color combinations (as strings) and values are bead names (as strings).
    - **Important Logic:**
        - Parses the CSV file using the CSVFormat class from Apache Commons CSV library.
        - Iterates through each record in the CSV, extracting the RGB values and bead name.
        - Stores the mapping between RGB color combinations and bead names in a HashMap.

* **`setBorderedAndCenteredStyle(XSSFCellStyle style)`:**
    - **Description:** Sets border styles and alignment for an XSSFCellStyle object.
    - **Parameters:** `style`: The XSSFCellStyle object to modify.
    - **Return Values:** None.
    - **Important Logic:**
        - Sets thin borders on all sides of the cell.
        - Centers both horizontally and vertically.



**5. Pseudo Code:**

```
// Class: ImageToPatternAndLegend

// Method: main(String[] args)
  1. Get image path, Excel output path, and CSV palette path from configuration files.
  2. Read the image using ImageIO.
  3. Create an XSSFWorkbook object to represent the Excel file.
  4. Load bead numbers and names from the CSV file using loadBeadNumbersFromCsv() and loadBeadNamesFromCsv().
  5. Iterate through each pixel in the image:
    - Get the RGB values of the pixel.
    - Create a unique symbol for the color based on its RGB values.
    - Add the symbol to the pattern sheet in Excel.
  6. Create a legend sheet in Excel.
  7. Populate the legend sheet with bead numbers, names, quantities, weights, and RGB values for each color.
  8. Save the Excel workbook to the specified path.

// Method: loadBeadNumbersFromCsv(String csvPath)
  1. Open the CSV file using a FileReader.
  2. Parse the CSV file using Apache Commons CSV library.
  3. Iterate through each record in the CSV:
    - Extract RGB values and bead number from the record.
    - Store the mapping between RGB color combination and bead number in a HashMap.
  4. Return the HashMap containing the bead number mappings.

// Method: loadBeadNamesFromCsv(String csvPath)
  1. Open the CSV file using a FileReader.
  2. Parse the CSV file using Apache Commons CSV library.
  3. Iterate through each record in the CSV:
    - Extract RGB values and bead name from the record.
    - Store the mapping between RGB color combination and bead name in a HashMap.
  4. Return the HashMap containing the bead name mappings.

// Method: setBorderedAndCenteredStyle(XSSFCellStyle style)
  1. Set thin borders on all sides of the cell using BorderStyle.THIN.
  2. Center the content horizontally and vertically using HorizontalAlignment.CENTER and VerticalAlignment.CENTER.



```


