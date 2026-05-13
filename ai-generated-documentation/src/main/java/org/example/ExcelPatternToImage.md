![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Make it real.

---
## ExcelPatternToImage.java Documentation

**1. Overview:**

This Java program converts a pattern defined in an Excel spreadsheet into an image file. The Excel file contains two sheets: "Legend" and "Pattern". The "Legend" sheet maps symbols to RGB color values, while the "Pattern" sheet defines the arrangement of symbols. The program reads these sheets, generates a BufferedImage based on the pattern, and saves it as a PNG image file.

**2. Package/module name:** org.example

**3. Class/file name:** ExcelPatternToImage.java

**4. Detailed Documentation:**

* **Class: `ExcelPatternToImage`**
    * This class contains the main logic for converting the Excel pattern to an image. 

    * **Method: `main(String[] args)`**
        * Description: The entry point of the program. It reads the input Excel file path and output image path from configuration settings, then calls the `convertExcelToImage` method to perform the conversion.
        * Parameters:
            * `args`: String array containing command-line arguments (not used in this code).
        * Return Values: None
        * Important Logic: 
            * Retrieves file paths from configuration settings (`Config`).
            * Calls `convertExcelToImage` to process the Excel file and generate the image.

    * **Method: `convertExcelToImage(String excelFilePath, String outputImagePath)`**
        * Description: This method reads the Excel file, extracts the pattern data, generates the image, and saves it to the specified path.
        * Parameters:
            * `excelFilePath`: Path to the input Excel file.
            * `outputImagePath`: Path to save the generated image file.
        * Return Values: None
        * Important Logic:
            * Opens the Excel file using a FileInputStream and Workbook object.
            * Reads the "Legend" sheet to create a map of symbols to RGB color values (`colorMap`).
            * Reads the "Size" sheet to determine the dimensions of the pattern image.
            * Reads the "Pattern" sheet to extract the symbol arrangement.
            * Creates a BufferedImage with the calculated dimensions and sets up graphics for drawing.
            * Iterates through each cell in the "Pattern" sheet, retrieves the symbol, gets its corresponding color from `colorMap`, and draws a filled rectangle (1x1 pixel) on the image based on the cell's coordinates.
            * Disposes of the graphics object.
            * Saves the generated BufferedImage as a PNG file to the specified output path.
            * Handles potential IOExceptions during file operations.

    * **Method: `readLegendColors(Workbook workbook)`**
        * Description: This method reads the "Legend" sheet from the Excel workbook and creates a map of symbols to RGB color values.
        * Parameters:
            * `workbook`: The Workbook object representing the open Excel file.
        * Return Values: A HashMap where keys are symbols (strings) and values are int arrays representing RGB color values.
        * Important Logic:
            * Iterates through each row in the "Legend" sheet, skipping the first row (header).
            * Extracts the symbol from the first column and RGB values from columns 4, 5, and 6.
            * Adds the symbol-color pair to the `colorMap` HashMap.



**5. Pseudo Code:**

```
// Class: ExcelPatternToImage

// Method: main(String[] args)
  1. Get input Excel file path from configuration settings (Config.getXlsxPatternFilePath()).
  2. Get output image file path from configuration settings (Config.getAdjustedImgPath()).
  3. Call convertExcelToImage method with the obtained file paths.

// Method: convertExcelToImage(String excelFilePath, String outputImagePath)
  1. Open the Excel file using FileInputStream and Workbook object.
  2. Read "Legend" sheet to create a map of symbols to RGB color values (colorMap).
  3. Read "Size" sheet to get pattern dimensions (patternRows, patternColumns).
  4. Read "Pattern" sheet to extract symbol arrangement.
  5. Create a BufferedImage with dimensions (patternColumns, patternRows) and TYPE_INT_RGB.
  6. Get Graphics2D object from the BufferedImage.
  7. Iterate through each row in the "Pattern" sheet:
     - For each cell in the row:
        - Get symbol from the cell.
        - Retrieve corresponding RGB color values from colorMap using the symbol.
        - Set the graphics color to the retrieved RGB value.
        - Draw a filled rectangle (1x1 pixel) at the current cell's coordinates.
  8. Dispose of the Graphics2D object.
  9. Save the BufferedImage as PNG file to the specified outputImagePath.
  10. Handle potential IOException during file operations.

// Method: readLegendColors(Workbook workbook)
  1. Get "Legend" sheet from the Workbook.
  2. Create a HashMap (colorMap) to store symbol-color pairs.
  3. Iterate through each row in the "Legend" sheet, skipping the first row (header):
     - Extract symbol from the first column and RGB values from columns 4, 5, and 6.
     - Add the symbol as key and RGB values as value to the colorMap.
  4. Return the colorMap.



```

**Dependencies and Libraries:**

* **Apache POI:** This library is used for reading and writing Excel files in Java. It provides classes for working with different Excel file formats, including XLSX. 
    * **Equivalent libraries in other languages:**
        * Python: `openpyxl`, `xlrd`, `xlsxwriter`
        * C++: `libxlxs`
        * JavaScript: `xlsxjs`

* **javax.imageio:** This Java API is used for reading and writing images. It provides classes for working with various image formats, including PNG.



