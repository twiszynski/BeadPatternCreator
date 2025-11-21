![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Get The Future You Want!

---
## ExcelPatternToImage.java Documentation

**1. Overview:**

This Java program converts a pattern defined in an Excel spreadsheet into an image file (PNG format). The Excel file contains two sheets: "Legend" and "Pattern". The "Legend" sheet maps symbols to RGB color values, while the "Pattern" sheet defines the arrangement of symbols. 

The program reads the data from these sheets, generates a BufferedImage based on the pattern dimensions and symbol colors, and finally saves the image to the specified output path.

**2. Package/module name:** org.example

**3. Class/file name:** ExcelPatternToImage.java

**4. Detailed Documentation:**

* **`main(String[] args)`:**
    - **Description:** The entry point of the program. It reads configuration values for the input Excel file path and output image path, then calls the `convertExcelToImage()` function to perform the conversion.
    - **Parameters:** 
        - `args`: String array containing command line arguments (not used in this code).
    - **Return Values:** None.

* **`convertExcelToImage(String excelFilePath, String outputImagePath)`:**
    - **Description:** This function reads the Excel file, extracts the pattern data and color map, generates the image, and saves it to the specified path.
    - **Parameters:**
        - `excelFilePath`: Path to the input Excel file.
        - `outputImagePath`: Path to save the output image file.
    - **Return Values:** None.
    - **Important Logic:**
        1. Opens the Excel file using `FileInputStream` and creates a Workbook object.
        2. Reads the "Legend" sheet to create a map (`colorMap`) associating symbols with their corresponding RGB color values.
        3. Retrieves pattern dimensions (rows and columns) from the "Size" sheet.
        4. Reads the "Pattern" sheet and iterates through each cell, using the `colorMap` to set the pixel color based on the symbol in the cell.
        5. Creates a BufferedImage with the specified dimensions and uses Graphics2D to draw the pattern onto it.
        6. Saves the generated image as a PNG file using `ImageIO.write()`.

* **`readLegendColors(Workbook workbook)`:**
    - **Description:** This function reads the "Legend" sheet from the Excel workbook and builds a map (`colorMap`) that maps symbols to their RGB color values.
    - **Parameters:**
        - `workbook`: The Workbook object containing the Excel data.
    - **Return Values:** A HashMap where keys are symbols and values are int arrays representing RGB color values.
    - **Important Logic:**
        1. Iterates through each row in the "Legend" sheet, skipping the header row.
        2. Extracts the symbol from the first column and RGB values (red, green, blue) from subsequent columns.
        3. Adds the symbol-color pair to the `colorMap`.



**5. Pseudo Code:**

```
// Class: ExcelPatternToImage

// Method: main(String[] args)
  1. Get input Excel file path and output image path from configuration (Config class).
  2. Call convertExcelToImage() function with the provided paths.

// Method: convertExcelToImage(String excelFilePath, String outputImagePath)
  1. Open the Excel file using FileInputStream.
  2. Create a Workbook object from the opened file.
  3. Read legend colors from "Legend" sheet and store them in a colorMap (symbol -> RGB values).
  4. Get pattern dimensions (rows and columns) from "Size" sheet.
  5. Read pattern data from "Pattern" sheet.
  6. Create a BufferedImage with the extracted dimensions.
  7. Iterate through each cell in the "Pattern" sheet:
     - Get the symbol from the cell.
     - Retrieve the corresponding RGB color values from the colorMap.
     - Set the pixel color at the current position in the BufferedImage using the retrieved RGB values.
  8. Save the generated BufferedImage as a PNG file to the specified output path.

// Method: readLegendColors(Workbook workbook)
  1. Get the "Legend" sheet from the Workbook.
  2. Iterate through each row in the "Legend" sheet (skip header row):
     - Extract the symbol and RGB values from the corresponding cells.
     - Add the symbol-color pair to a HashMap.
  3. Return the HashMap containing the legend colors.



```

**Dependencies and Libraries:**

* **Apache POI:** This library is used for reading and writing Excel files in Java. 
    * **Equivalent libraries in other languages:**
        * Python: `openpyxl`, `xlrd`, `xlsxwriter`
        * C++: `libxl`, `poppler-utils`


**Edge Cases and Error Handling:**

The code includes basic error handling by catching `IOException` during file operations. It also handles cases where the "Size" or "Pattern" sheets are not found in the Excel file. However, it doesn't handle potential issues like invalid data types in the Excel cells or missing required information.



