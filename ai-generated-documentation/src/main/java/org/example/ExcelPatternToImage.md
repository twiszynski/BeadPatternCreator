![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Make it real.

---
## ExcelPatternToImage Code Documentation

**1. Overview:**

This Java program converts a pattern defined in an Excel spreadsheet into an image file. The Excel file contains two sheets: "Legend" which maps symbols to RGB color values, and "Pattern" which defines the arrangement of symbols. The program reads these sheets, generates a BufferedImage based on the pattern definition, and saves it as a PNG image file.

**2. Package/module name:** `org.example`

**3. Class/file name:** `ExcelPatternToImage.java`

**4. Class diagram name:** `ExcelPatternToImage_diagram.svg`

**5. Detailed Documentation:**

   - **Class: `ExcelPatternToImage`**
     -  **Method: `main(String[] args)`**
        - Description: Entry point of the program. Reads configuration values for input Excel file path and output image path, then calls `convertExcelToImage()` to perform the conversion.
        - Parameters: 
          - `args`: String array containing command line arguments (not used in this code).
        - Return Values: None
        - Important Logic:
          - Retrieves configuration values for input Excel file path and output image path using `Config` class methods.
          - Calls the `convertExcelToImage()` method to initiate the conversion process.

     - **Method: `convertExcelToImage(String excelFilePath, String outputImagePath)`**
        - Description: Reads the Excel file, extracts pattern data, generates an image based on the pattern, and saves it to the specified output path.
        - Parameters: 
          - `excelFilePath`: Path to the input Excel file.
          - `outputImagePath`: Path to save the generated image file.
        - Return Values: None
        - Important Logic:
          - Opens the Excel file using a FileInputStream and creates a Workbook object.
          - Reads the "Legend" sheet to create a map of symbols to RGB color values (`colorMap`).
          - Reads the "Size" sheet to determine the dimensions of the pattern image.
          - Reads the "Pattern" sheet to extract the symbol arrangement data.
          - Creates a BufferedImage object with the determined dimensions.
          - Iterates through each cell in the "Pattern" sheet and sets the corresponding pixel color based on the symbol and its associated RGB value from `colorMap`.
          - Saves the generated image as a PNG file using ImageIO.write().

     - **Method: `readLegendColors(Workbook workbook)`**
        - Description: Reads the "Legend" sheet from the Excel workbook and creates a map of symbols to their corresponding RGB color values.
        - Parameters: 
          - `workbook`: The Workbook object representing the Excel file.
        - Return Values: A Map<String, int[]> where keys are symbols and values are arrays of three integers representing red, green, and blue color components.
        - Important Logic:
          - Iterates through each row in the "Legend" sheet, skipping the first row (header).
          - Extracts the symbol from the first column and RGB values from columns 4, 5, and 6.
          - Stores the symbol and its RGB value as a key-value pair in the `colorMap`.



**6. Pseudo Code:**

```
// Class: ExcelPatternToImage

// Method: main(String[] args)
  1. Get input Excel file path from configuration (Config.getXlsxPatternFilePath()).
  2. Get output image path from configuration (Config.getAdjustedImgPath()).
  3. Call convertExcelToImage() function with the provided paths.

// Method: convertExcelToImage(String excelFilePath, String outputImagePath)
  1. Open the Excel file using FileInputStream and create Workbook object.
  2. Read "Legend" sheet to create a map of symbols to RGB color values (colorMap).
  3. Read "Size" sheet to get pattern image dimensions (patternRows, patternColumns).
  4. Read "Pattern" sheet to extract symbol arrangement data.
  5. Create a BufferedImage object with dimensions (patternColumns, patternRows) and TYPE_INT_RGB type.
  6. Iterate through each cell in the "Pattern" sheet:
    - Get the symbol from the cell value.
    - Retrieve corresponding RGB color values from the `colorMap`.
    - Set the pixel color at the current cell's coordinates using the retrieved RGB values.
  7. Save the generated BufferedImage as a PNG file to the specified outputImagePath.

// Method: readLegendColors(Workbook workbook)
  1. Get the "Legend" sheet from the Workbook object.
  2. Create an empty HashMap to store symbol-color mappings (colorMap).
  3. Iterate through each row in the "Legend" sheet, skipping the first row (header):
    - Extract the symbol from the first column cell value.
    - Extract RGB values from columns 4, 5, and 6.
    - Store the symbol as key and its RGB values as a value in the `colorMap`.
  4. Return the populated `colorMap`.



```

**7. Dependencies and Libraries:**

* **Apache POI:** Used for reading and writing Excel files. Equivalent libraries in other languages include:
    * Python: openpyxl, xlrd, xlsxwriter
    * C++: libxl, poixml
    * Java: JXL



