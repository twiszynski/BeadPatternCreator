![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Make it real.

---
## BeadVisualizer.java Documentation and Pseudocode

**1. Overview:**

The `BeadVisualizer` class generates a visual representation of a bead pattern defined in an Excel spreadsheet. It reads the pattern from a designated sheet, maps color symbols to bead numbers based on a legend, and then assembles individual bead images into a composite image according to the pattern. 

**2. Package/Module Name:** `org.example`

**3. Class/File Name:** `BeadVisualizer.java`

**4. Detailed Documentation:**

* **`main(String[] args)`:**
    - **Description:** The entry point of the program. It reads the Excel file path, output image path, loads bead numbers from a legend sheet, retrieves the pattern from the designated sheet, and generates the visualization.
    - **Parameters:** `args`: An array of strings containing command-line arguments (not used in this code).
    - **Return Values:** None.
    - **Important Logic:** 
        - Calls `loadBeadNumbersFromLegendSheet` to map color symbols to bead numbers.
        - Calls `loadPattern` to retrieve the pattern from the Excel file.
        - Calls `generateVisualization` to create the final image based on the loaded pattern.

* **`loadBeadNumbersFromLegendSheet(String excelFilePath)`:**
    - **Description:** Reads a legend sheet from the provided Excel file and creates a map of color symbols to corresponding bead numbers.
    - **Parameters:** `excelFilePath`: The path to the Excel file.
    - **Return Values:** A `Map<String, String>` where keys are color symbols and values are bead numbers.
    - **Important Logic:** 
        - Iterates through rows of the legend sheet, extracting symbol and number values from specific cells.
        - Stores the mapping in a HashMap.

* **`loadPattern(String excelFilePath, Map<String, String> symbolToBeadMap)`:**
    - **Description:** Reads the bead pattern from the Excel file and maps color symbols to bead numbers using the provided map.
    - **Parameters:** `excelFilePath`: The path to the Excel file, `symbolToBeadMap`: The mapping of color symbols to bead numbers.
    - **Return Values:** A 2D string array representing the bead pattern.
    - **Important Logic:**
        - Reads the dimensions of the pattern from a separate sheet.
        - Iterates through rows and columns of the pattern sheet, extracting cell values and mapping them to bead numbers using the provided map.

* **`generateVisualization(String[][] beadPattern, String outputImagePath)`:**
    - **Description:** Creates an image visualization of the bead pattern by assembling individual bead images based on the provided pattern data.
    - **Parameters:** `beadPattern`: A 2D string array representing the bead pattern, `outputImagePath`: The path to save the generated image.
    - **Return Values:** None.
    - **Important Logic:**
        - Calculates tile width and height based on individual bead image size and spacing.
        - Creates a new BufferedImage with dimensions based on the pattern size and tile dimensions.
        - Iterates through the bead pattern, loading each bead image using `loadBeadImage` and placing it in the final image according to its position in the pattern.

* **`loadBeadImage(String beadNumber)`:**
    - **Description:** Loads a bead image based on its number. Uses a default template if no specific image is found.
    - **Parameters:** `beadNumber`: The number identifying the bead image.
    - **Return Values:** A `BufferedImage` representing the loaded bead image, or null if the image could not be loaded.

* **`roundCorners(BufferedImage image, int arcWidth, int arcHeight)`:**
    - **Description:** Rounds the corners of a given image using a specified arc width and height.
    - **Parameters:** `image`: The input BufferedImage, `arcWidth`: Width of the rounded corner arcs, `arcHeight`: Height of the rounded corner arcs.
    - **Return Values:** A new BufferedImage with rounded corners.

**5. Pseudo Code:**


```
// Class: BeadVisualizer

// Method: main(String[] args)
  1. Get Excel file path from Config (e.g., "path/to/beads.xlsx").
  2. Get output image path from Config (e.g., "output/visualization.png").
  3. Call loadBeadNumbersFromLegendSheet to create a map of color symbols to bead numbers.
  4. Call loadPattern to retrieve the bead pattern from the Excel file using the symbol-to-number map.
  5. Call generateVisualization to create the image visualization based on the loaded pattern and output path.

// Method: loadBeadNumbersFromLegendSheet(String excelFilePath)
  1. Open the Excel file specified by excelFilePath.
  2. Get the sheet named "Legend" from the workbook.
  3. Iterate through each row in the sheet, starting from the second row (index 1).
    - Extract the symbol and number values from specific cells in each row.
    - Store the symbol as key and the number as value in a HashMap.
  4. Return the HashMap containing the color symbol to bead number mapping.

// Method: loadPattern(String excelFilePath, Map<String, String> symbolToBeadMap)
  1. Open the Excel file specified by excelFilePath.
  2. Get the sheet named "Size" from the workbook.
    - Extract the width and height of the pattern from specific cells in the sheet.
  3. Get the sheet named "Pattern" from the workbook.
  4. Iterate through each row in the pattern sheet, starting from the second row (index 1).
    - For each cell in the row:
      - Extract the cell value.
      - Use the symbolToBeadMap to find the corresponding bead number for the extracted symbol.
      - Store the bead number in a 2D array representing the pattern.
  5. Return the 2D array containing the bead pattern.

// Method: generateVisualization(String[][] beadPattern, String outputImagePath)
  1. Calculate tile width and height based on individual bead image size and spacing.
  2. Create a new BufferedImage with dimensions based on the pattern size and tile dimensions.
  3. Iterate through each element in the beadPattern array.
    - For each element:
      - Call loadBeadImage to get the corresponding bead image using the element value as input.
      - If the image is loaded successfully:
        - Calculate the position of the bead image in the final BufferedImage based on its row and column index.
        - Draw the loaded bead image at the calculated position in the final BufferedImage.
  4. Save the generated BufferedImage to the specified outputImagePath.



```

**6. Dependencies and Libraries:**


* **Apache POI:** Used for reading and writing Excel files. 
    * Equivalent libraries:
        * Java: JXL, OpenOffice API
        * Python: openpyxl, xlrd, xlsxwriter
        * C++: libxl, poixml
* **Java Image I/O (ImageIO):** Used for reading and writing image files. 
    * Equivalent libraries:
        * Python: Pillow (PIL), OpenCV
        * C++: OpenCV

**7. Edge Cases and Error Handling:**


* The code handles missing bead images by using a default template image.
* It also includes basic error handling for file operations and image loading, printing stack traces to the console.



