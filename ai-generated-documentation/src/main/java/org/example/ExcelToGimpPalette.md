![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Get The Future You Want!

---
## ExcelToGimpPalette.java Documentation

**1. Overview:**

This Java program reads data from an Excel spreadsheet and generates a GIMP palette file (.gpl). The Excel file is assumed to have columns representing the coral number, red (R), green (G), and blue (B) values for each color. The program writes these values into a .gpl file in the specified format.

**2. Package/module name:** org.example

**3. Class/file name:** ExcelToGimpPalette.java

**4. Detailed Documentation:**

* **main(String[] args):**
    - **Description:** This is the main method of the program. It handles file input, processing, and output generation.
    - **Parameters:** 
        - `args`: String array containing command-line arguments (not used in this code).
    - **Return Values:** None.
    - **Important Logic:**
        1. Defines input and output file paths based on the provided filename.
        2. Opens the Excel workbook using Apache POI library.
        3. Iterates through each row of the first sheet in the workbook.
        4. Reads R, G, B values from specified columns for each row.
        5. Writes the color data to the .gpl file in the correct format.
        6. Closes the input and output files.

**5. Key Variables and Data Structures:**

* `fileName`: String variable storing the base name of the Excel file (e.g., "PaletaMiyuki").
* `excelFilePath`: String variable holding the full path to the input Excel file.
* `gimpPalettePath`: String variable holding the full path to the output .gpl file.
* `workbook`: Workbook object representing the opened Excel file.
* `sheet`: Sheet object representing the first sheet in the workbook.
* `rowIterator`: Iterator object used to iterate through each row in the sheet.
* `row`: Row object representing a single row in the sheet.
* `cellNumber`, `cellR`, `cellG`, `cellB`: Cell objects representing individual cells in the sheet containing coral number, R, G, and B values respectively.

**6. Assumptions and Dependencies:**

* The Excel file has a specific structure with columns for coral number, R, G, and B values starting from row 1.
* The program relies on the Apache POI library for reading and processing Excel files.


**7. Pseudo Code:**

```
// Class: ExcelToGimpPalette

// Method: main(String[] args)
  1. Define input file path (excelFilePath) based on fileName.
  2. Define output file path (gimpPalettePath) based on fileName.
  3. Open the Excel workbook using FileInputStream and XSSFWorkbook.
  4. Get the first sheet from the workbook.
  5. Create a BufferedWriter to write to the .gpl file.
  6. Write GIMP palette header information to the .gpl file:
     - "GIMP Palette"
     - "Name: My Miyuki Palette"
     - "Columns: 0"
     - "#"
  7. Iterate through each row in the sheet using an Iterator:
     - Get cell values for coral number, R, G, and B from corresponding columns.
     - Check if all cells contain valid data.
       - If yes:
         - Read numeric values for R, G, B.
         - Format the color data as "R G B coralNumber" and write it to the .gpl file.
   8. Close the BufferedWriter and Workbook objects.
  9. Print a success message.
 10. Handle any exceptions during file operations or processing:
     - Print the error stack trace using e.printStackTrace().



```

**8. Dependencies and Libraries:**

* **Apache POI:** Used for reading and writing Excel files.


