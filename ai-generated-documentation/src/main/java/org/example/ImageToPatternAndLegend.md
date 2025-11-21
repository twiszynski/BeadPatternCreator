![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Get The Future You Want!

---
## ImageToPatternAndLegend.java Documentation and Pseudocode

**1. Overview:**

This Java program processes an image and generates two Excel files: one containing a pattern representation of the image with color codes and another containing a legend associating each color code with its corresponding bead number, name, quantity, weight, and RGB values. 

The program reads the image using ImageIO, extracts pixel colors, and maps them to unique symbols. It then creates an Excel sheet representing the image pattern, where each cell contains the symbol corresponding to the pixel color. A separate sheet is created for the legend, listing each color code, bead number, name, quantity, weight, and RGB values.

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
        - Populates the pattern sheet with symbols corresponding to each pixel color.
        - Populates the legend sheet with color codes, bead numbers, names, quantities, weights, and RGB values.
        - Saves the Excel file.

* **`loadBeadNumbersFromCsv(String csvPath)`:**
    - **Description:** Reads bead numbers from a CSV file. Each row in the CSV represents a bead with its RGB values and number. The function maps the RGB values to their corresponding bead numbers.
    - **Parameters:** `csvPath`: String representing the path to the CSV file.
    - **Return Values:** A HashMap where keys are RGB values (as strings) and values are bead numbers (as strings).
    - **Important Logic:**
        - Parses the CSV file using the CSVFormat class from Apache Commons CSV library.
        - Iterates through each record in the CSV, extracting RGB values and bead numbers.
        - Stores the mapping between RGB values and bead numbers in a HashMap.

* **`loadBeadNamesFromCsv(String csvPath)`:**
    - **Description:** Reads bead names from a CSV file. Similar to `loadBeadNumbersFromCsv`, but instead of reading bead numbers, it reads bead names associated with each RGB value.
    - **Parameters:** `csvPath`: String representing the path to the CSV file.
    - **Return Values:** A HashMap where keys are RGB values (as strings) and values are bead names (as strings).
    - **Important Logic:**
        - Parses the CSV file using the CSVFormat class from Apache Commons CSV library.
        - Iterates through each record in the CSV, extracting RGB values and bead names.
        - Stores the mapping between RGB values and bead names in a HashMap.

* **`setBorderedAndCenteredStyle(XSSFCellStyle style)`:**
    - **Description:** Sets border styles and alignment for an XSSFCellStyle object. This function ensures that all cells have thin borders, are horizontally and vertically centered.
    - **Parameters:** `style`: An XSSFCellStyle object representing the cell style to be modified.
    - **Return Values:** None.
    - **Important Logic:**
        - Sets top, bottom, left, and right borders to thin lines using BorderStyle.THIN.
        - Sets horizontal alignment to center using HorizontalAlignment.CENTER.
        - Sets vertical alignment to center using VerticalAlignment.CENTER.



**5. Pseudo Code:**

```
// Class: ImageToPatternAndLegend

// Method: main(String[] args)
  1. Get image path, Excel output path, and CSV palette path from configuration files.
  2. Read the image using ImageIO.read(new File(imagePath)).
  3. Create an XSSFWorkbook object to represent the Excel file.
  4. Load bead numbers and names from CSV files using loadBeadNumbersFromCsv() and loadBeadNamesFromCsv().
  5. Iterate through each pixel in the image:
     - Get the RGB values of the current pixel.
     - Calculate a unique symbol for the color based on its RGB values (e.g., assign a letter).
     - Create a new sheet in the Excel workbook to represent the pattern.
     - Add a cell to the sheet representing the current pixel and set its value to the calculated symbol.
  6. Create a legend sheet in the Excel workbook.
  7. Populate the legend sheet with color codes, bead numbers, names, quantities, weights, and RGB values for each unique color found in the image.
  8. Save the Excel file using FileOutputStream.

// Method: loadBeadNumbersFromCsv(String csvPath)
  1. Open the CSV file specified by csvPath.
  2. Read the header row of the CSV file.
  3. Iterate through each row in the CSV file, starting from the second row (data rows):
     - Extract the RGB values and bead number from each row.
     - Store the mapping between RGB values and bead numbers in a HashMap.
  4. Return the HashMap containing the bead number mappings.

// Method: loadBeadNamesFromCsv(String csvPath)
  1. Open the CSV file specified by csvPath.
  2. Read the header row of the CSV file.
  3. Iterate through each row in the CSV file, starting from the second row (data rows):
     - Extract the RGB values and bead name from each row.
     - Store the mapping between RGB values and bead names in a HashMap.
  4. Return the HashMap containing the bead name mappings.

// Method: setBorderedAndCenteredStyle(XSSFCellStyle style)
  1. Set top, bottom, left, and right borders of the cell style to thin lines using BorderStyle.THIN.
  2. Set horizontal alignment of the cell style to center using HorizontalAlignment.CENTER.
  3. Set vertical alignment of the cell style to center using VerticalAlignment.CENTER.



```

**6. Dependencies and Libraries:**


* **Apache POI:** Used for reading and writing Excel files.
* **ImageIO:** Used for reading image files.
* **Apache Commons CSV:** Used for parsing CSV files.




