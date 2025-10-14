![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Get The Future You Want!

---
## ExcelPatternToImage.java Documentation

**1. Overview:**

This Java program converts a pattern defined in an Excel spreadsheet into an image file (PNG format). The Excel file contains two sheets: "Legend" and "Pattern". The "Legend" sheet maps symbols to RGB color values, while the "Pattern" sheet defines the arrangement of symbols. 

The program reads the data from these sheets, generates a BufferedImage based on the pattern dimensions and symbol colors, and finally saves the image to the specified output path.

**2. Package/Module Name:** org.example

**3. Class/File Name:** ExcelPatternToImage.java

**4. Detailed Documentation:**

   - **`main(String[] args)`:**
     - **Description:** The entry point of the program. It reads configuration values for the input Excel file path and output image path, then calls the `convertExcelToImage` function to perform the conversion.
     - **Parameters:** 
       - `args`: String array containing command-line arguments (not used in this code).
     - **Return Values:** None.

   - **`convertExcelToImage(String excelFilePath, String outputImagePath)`:**
     - **Description:** This function reads the Excel file, extracts the pattern data and color map, generates the image, and saves it to the specified path.
     - **Parameters:**
       - `excelFilePath`: Path to the input Excel file.
       - `outputImagePath`: Path to save the output image file.
     - **Return Values:** None.
     - **Important Logic:**
       - Opens the Excel workbook using FileInputStream and XSSFWorkbook.
       - Reads the "Legend" sheet to create a map of symbols to RGB color values.
       - Reads the "Size" sheet to determine the pattern dimensions (rows and columns).
       - Reads the "Pattern" sheet to extract the symbol arrangement.
       - Creates a BufferedImage with the calculated dimensions.
       - Iterates through each cell in the "Pattern" sheet, sets the pixel color based on the symbol's RGB value from the color map, and draws a 1x1 rectangle for each cell.
       - Saves the generated image as a PNG file using ImageIO.write().

   - **`readLegendColors(Workbook workbook)`:**
     - **Description:** This function reads the "Legend" sheet from the Excel workbook and creates a map of symbols to RGB color values.
     - **Parameters:**
       - `workbook`: The open Workbook object.
     - **Return Values:** A HashMap where keys are symbols (strings) and values are int arrays representing RGB color values.
     - **Important Logic:**
       - Iterates through each row in the "Legend" sheet, skipping the header row.
       - Extracts the symbol from the first column and RGB values from columns 4, 5, and 6.
       - Adds the symbol and its corresponding RGB values to the color map.



**5. Pseudo Code:**

```
// Class: ExcelPatternToImage

// Method: main(String[] args)
  1. Get input Excel file path and output image path from configuration (Config class).
  2. Call convertExcelToImage function with the provided paths.

// Method: convertExcelToImage(String excelFilePath, String outputImagePath)
  1. Open the Excel workbook using FileInputStream and XSSFWorkbook.
  2. Read the "Legend" sheet to create a map of symbols to RGB color values (readLegendColors function).
  3. Read the "Size" sheet to get pattern dimensions (rows and columns).
  4. Read the "Pattern" sheet to extract symbol arrangement.
  5. Create a BufferedImage with calculated dimensions using TYPE_INT_RGB.
  6. Iterate through each row in the "Pattern" sheet:
    - For each cell in the row:
      - Get the symbol from the cell.
      - Retrieve the corresponding RGB color values from the color map.
      - Set the pixel color of the BufferedImage using the retrieved RGB values.
      - Draw a 1x1 rectangle at the current cell position.
  7. Save the generated image as PNG to the specified output path using ImageIO.write().

// Method: readLegendColors(Workbook workbook)
  1. Get the "Legend" sheet from the workbook.
  2. Iterate through each row in the "Legend" sheet, skipping the header row:
    - Extract the symbol from the first column.
    - Extract RGB values from columns 4, 5, and 6.
    - Add the symbol and its corresponding RGB values to a HashMap.



```

**Dependencies and Libraries:**

* **Apache POI:** Used for reading and writing Excel files. Equivalent libraries in other languages include:
    * Python: `openpyxl`, `xlrd`, `xlsxwriter`
    * C++: `libxl`
    * Java: `jxl`, `poi-ooxml`



