![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Get The Future You Want!

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
        - Calls `loadPattern` to read the pattern from the Excel file.
        - Calls `generateVisualization` to create the final image based on the loaded pattern.

* **`loadBeadNumbersFromLegendSheet(String excelFilePath)`:**
    - **Description:** Reads a legend sheet from the provided Excel file and maps color symbols to corresponding bead numbers.
    - **Parameters:** `excelFilePath`: The path to the Excel file.
    - **Return Values:** A `Map<String, String>` where keys are color symbols and values are bead numbers.
    - **Important Logic:**
        - Iterates through rows of the legend sheet (skipping the header row).
        - Extracts the symbol and number from each row and stores them in the map.

* **`loadPattern(String excelFilePath, Map<String, String> symbolToBeadMap)`:**
    - **Description:** Reads the bead pattern from a designated sheet in the Excel file and maps symbols to bead numbers using the provided mapping.
    - **Parameters:** `excelFilePath`: The path to the Excel file, `symbolToBeadMap`: A map of color symbols to bead numbers.
    - **Return Values:** A 2D string array representing the bead pattern.
    - **Important Logic:**
        - Reads the dimensions of the pattern from a separate "Size" sheet.
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
    - **Parameters:** `beadNumber`: The number of the bead image to load.
    - **Return Values:** A `BufferedImage` representing the loaded bead image, or null if the image could not be found.

* **`roundCorners(BufferedImage image, int arcWidth, int arcHeight)`:**
    - **Description:** Rounds the corners of a given image using a specified arc width and height.
    - **Parameters:** `image`: The input BufferedImage, `arcWidth`: Width of the rounded corner arcs, `arcHeight`: Height of the rounded corner arcs.
    - **Return Values:** A new BufferedImage with rounded corners.

**5. Pseudo Code:**


```
// Class: BeadVisualizer

main(String[] args)
  1. Get Excel file path and output image path from configuration (Config class).
  2. Load bead number mapping from legend sheet in Excel using loadBeadNumbersFromLegendSheet().
  3. Load bead pattern from designated sheet in Excel using loadPattern() with the mapping.
  4. Generate visualization of the pattern using generateVisualization() with the loaded pattern and output path.

loadBeadNumbersFromLegendSheet(String excelFilePath)
  1. Open Excel file at given path.
  2. Get legend sheet (name defined in Config).
  3. Iterate through rows, skipping header row:
    - Extract symbol and number from corresponding cells.
    - Store mapping in a HashMap with symbol as key and number as value.

loadPattern(String excelFilePath, Map<String, String> symbolToBeadMap)
  1. Open Excel file at given path.
  2. Get size sheet (name defined in Config).
  3. Extract pattern width and height from the size sheet.
  4. Get pattern sheet (name defined in Config).
  5. Iterate through rows and columns of the pattern sheet:
    - Extract cell value representing bead symbol.
    - Use symbolToBeadMap to get corresponding bead number.
    - Store bead number in a 2D array representing the pattern.

generateVisualization(String[][] beadPattern, String outputImagePath)
  1. Calculate tile width and height based on bead image size and spacing (defined in Config).
  2. Create new BufferedImage with dimensions based on pattern size and tile dimensions.
  3. Iterate through beadPattern:
    - For each bead number:
      - Load corresponding bead image using loadBeadImage().
      - Round corners of the loaded image using roundCorners() function.
      - Place the rounded bead image in the final BufferedImage at its correct position based on pattern coordinates.
  4. Save the generated BufferedImage to the specified output path as a PNG file.

loadBeadImage(String beadNumber)
  1. Construct full image path using bead number and template directory (defined in Config).
  2. Check if the image file exists.
    - If yes, load the image using ImageIO.read().
    - If no, use default template image (name defined in Config).
  3. Return loaded image.

roundCorners(BufferedImage image, int arcWidth, int arcHeight)
  1. Create new BufferedImage with same dimensions as input image.
  2. Set rendering hints for antialiasing.
  3. Fill the new image with transparent color using AlphaComposite.Clear.
  4. Draw a rounded rectangle on the new image using fillRoundRect() function, specifying arc width and height.
  5. Use AlphaComposite.SrcAtop to overlay the original image onto the rounded rectangle.
  6. Return the new BufferedImage with rounded corners.



```

**Dependencies and Libraries:**

- **Apache POI:** Used for reading and writing Excel files. Equivalent libraries in other languages include:
    - Java: Apache POI
    - Python: openpyxl, pandas
    - C++: libxl, xlwings
- **Java Image I/O (ImageIO):** Used for loading and saving images.

**Edge Cases and Error Handling:**

- The code handles missing bead images by using a default template image.
- It also checks if the Excel files exist and throws exceptions if they are not found.



