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
        - Calculates tile width and height based on bead image size and spacing.
        - Creates a new BufferedImage with dimensions based on the pattern size and tile dimensions.
        - Iterates through the bead pattern, loading each bead image using `loadBeadImage` and placing it in the final image at the corresponding position.

* **`loadBeadImage(String beadNumber)`:**
    - **Description:** Loads a bead image from disk based on its number. Uses a default template if no specific image is found.
    - **Parameters:** `beadNumber`: The number identifying the bead image.
    - **Return Values:** A `BufferedImage` representing the loaded bead image, or null if the image could not be found.

* **`roundCorners(BufferedImage image, int arcWidth, int arcHeight)`:**
    - **Description:** Rounds the corners of a given image using a specified arc width and height.
    - **Parameters:** `image`: The input BufferedImage, `arcWidth`: Width of the rounded corner arcs, `arcHeight`: Height of the rounded corner arcs.
    - **Return Values:** A new BufferedImage with rounded corners.

**5. Pseudo Code:**


```
// Class: BeadVisualizer

main(String[] args)
  1. Get Excel file path from Config (e.g., "beads_pattern.xlsx").
  2. Get output image path from Config (e.g., "visualization.png").
  3. Call loadBeadNumbersFromLegendSheet to get a map of color symbols to bead numbers.
  4. Call loadPattern to read the pattern from the Excel file, using the symbol-to-bead map.
  5. Call generateVisualization to create the image based on the loaded pattern and output path.

loadBeadNumbersFromLegendSheet(String excelFilePath)
  1. Open the Excel file.
  2. Get the "Legend" sheet.
  3. Iterate through rows of the sheet, starting from the second row (header).
    - Extract the symbol and number from each row.
    - Add the symbol-number pair to a map.

loadPattern(String excelFilePath, Map<String, String> symbolToBeadMap)
  1. Open the Excel file.
  2. Get the "Size" sheet.
    - Read the pattern width and height from the sheet.
  3. Get the "Pattern" sheet.
  4. Iterate through rows and columns of the pattern sheet, starting from the second row (header).
    - Extract the cell value.
    - Use the symbolToBeadMap to get the corresponding bead number.
    - Store the bead number in a 2D array representing the pattern.

generateVisualization(String[][] beadPattern, String outputImagePath)
  1. Calculate tile width and height based on bead image size and spacing.
  2. Create a new BufferedImage with dimensions based on the pattern size and tile dimensions.
  3. Iterate through the bead pattern array.
    - For each bead number:
      - Load the corresponding bead image using loadBeadImage.
      - Round the corners of the loaded image using roundCorners.
      - Draw the rounded bead image onto the final BufferedImage at the correct position.
  4. Save the final BufferedImage to the specified output path.

loadBeadImage(String beadNumber)
  1. Construct the full file path for the bead image based on the bead number and template directory.
  2. Try to load the image from disk using ImageIO.read.
  3. If the image is not found, use a default template image.
  4. Return the loaded image.

roundCorners(BufferedImage image, int arcWidth, int arcHeight)
  1. Create a new BufferedImage with the same dimensions as the input image.
  2. Draw the input image onto the new BufferedImage using a rounded rectangle shape defined by arcWidth and arcHeight.
  3. Return the new BufferedImage with rounded corners.



```

**6. Dependencies and Libraries:**

* **Apache POI:** Used for reading and writing Excel files.
* **Java Image I/O (ImageIO):** Used for loading and manipulating images.


