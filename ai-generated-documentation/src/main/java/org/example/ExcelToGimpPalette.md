![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Make it real.

---
## ExcelToGimpPalette.java Documentation

**1. Overview:**

This Java program reads data from an Excel spreadsheet and generates a GIMP palette file (.gpl). The Excel file is assumed to contain RGB color values in columns B, C, and D, starting from row 2.  The program writes the extracted colors into a .gpl file with the same name as the input Excel file.

**2. Package/module name:** org.example

**3. Class/file name:** ExcelToGimpPalette.java

**4. Detailed Documentation:**

* **main(String[] args):**
    - **Description:** This is the main method of the program. It handles reading the input Excel file, processing the data, and writing the output GIMP palette file.
    - **Parameters:** 
        - `args`: String array containing command line arguments (not used in this code).
    - **Return Values:** None
    - **Important Logic:**
        1. Defines input and output file paths based on the provided filename.
        2. Opens the Excel workbook using Apache POI library.
        3. Iterates through each row of the first sheet in the workbook, starting from row 1.
        4. Extracts RGB values from cells B, C, and D for each row.
        5. Writes the extracted RGB values to the GIMP palette file in the specified format.
        6. Handles potential exceptions during file reading, processing, and writing.

**5. Key Variables and Data Structures:**

* `fileName`: String variable storing the base name of the input Excel file (e.g., "PaletaMiyuki").
* `excelFilePath`: String variable holding the full path to the input Excel file.
* `gimpPalettePath`: String variable holding the full path to the output GIMP palette file.
* `workbook`: Workbook object representing the opened Excel file.
* `sheet`: Sheet object representing the first sheet in the workbook.
* `rowIterator`: Iterator object used to iterate through each row in the sheet.
* `row`: Row object representing a single row in the sheet.
* `cellNumber`, `cellR`, `cellG`, `cellB`: Cell objects representing individual cells within a row, holding the coral number and RGB values respectively.

**6. Dependencies:**

* **Apache POI library:** Used for reading and processing Excel files. Equivalent libraries in other languages include:
    - Python: openpyxl, xlrd
    - C++: libxl, poixl
    - Java: JXL


**7. Pseudo Code:**



```
// Class: ExcelToGimpPalette

// Method: main(String[] args)
  1. Define input and output file paths based on the provided filename.
  2. Open the Excel workbook using Apache POI library.
  3. Get the first sheet from the workbook.
  4. Create a BufferedWriter to write data to the GIMP palette file.
  5. Write the header information to the GIMP palette file: "GIMP Palette", "Name: My Miyuki Palette", "Columns: 0", "#".
  6. Iterate through each row in the sheet, starting from row 1:
    - Get the cell objects for coral number (column A), R value (column B), G value (column C), and B value (column D).
    - Check if all four cells contain valid data.
      - If yes:
        - Extract the numeric values for R, G, and B from the respective cells.
        - Format the RGB values and coral number as a string in the format "R G B CoralNumber".
        - Write the formatted string to the GIMP palette file.
    - Handle potential exceptions during file reading, processing, and writing.
  7. Close the BufferedWriter and workbook objects.
  8. Print a success message indicating that the GIMP palette file has been created.



``` 


