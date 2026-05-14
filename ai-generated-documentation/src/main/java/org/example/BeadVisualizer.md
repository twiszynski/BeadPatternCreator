![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Make it real.

---
## BeadVisualizer Code Documentation

**1. Overview:**

The `BeadVisualizer` Java application generates a visual representation of a bead pattern defined in an Excel spreadsheet. It reads the pattern from a designated sheet, maps symbols to corresponding bead images, and assembles them into a final image file. 

**2. Package/module name:**

org.example

**3. Class/file name:**

BeadVisualizer.java

**4. Class diagram name:**

BeadVisualizer_diagram.svg (This would be an external SVG file containing the class diagram)

**5. Detailed Documentation:**


* **`main(String[] args)`:**
    - **Description:** The entry point of the application. It reads configuration settings, loads the bead pattern from Excel, generates the visualization, and saves it to a specified output path.
    - **Parameters:** `args`: Command-line arguments (not used in this code).
    - **Return Values:** None.
    - **Important Logic:** 
        - Calls `loadBeadNumbersFromLegendSheet` to map symbols to bead numbers from the legend sheet.
        - Calls `loadPattern` to read the pattern data from the designated sheet.
        - Calls `generateVisualization` to create the final image based on the loaded pattern and bead images.

* **`loadBeadNumbersFromLegendSheet(String excelFilePath)`:**
    - **Description:** Reads a legend sheet from an Excel file and maps symbols to corresponding bead numbers.
    - **Parameters:** `excelFilePath`: Path to the Excel file containing the legend.
    - **Return Values:** A `Map<String, String>` where keys are symbols and values are bead numbers.
    - **Important Logic:**
        - Iterates through rows of the "Legend" sheet (assuming this is the sheet name).
        - Extracts symbol and number from each row and stores them in the map.

* **`loadPattern(String excelFilePath, Map<String, String> symbolToBeadMap)`:**
    - **Description:** Reads a bead pattern from an Excel file and maps symbols to corresponding bead images based on the provided mapping.
    - **Parameters:** 
        - `excelFilePath`: Path to the Excel file containing the pattern.
        - `symbolToBeadMap`: A map of symbols to bead numbers obtained from `loadBeadNumbersFromLegendSheet`.
    - **Return Values:** A 2D string array representing the bead pattern.
    - **Important Logic:**
        - Reads dimensions of the pattern from a "Size" sheet (assuming this is the sheet name).
        - Iterates through rows and columns of the designated pattern sheet.
        - Retrieves the corresponding bead number from `symbolToBeadMap` based on the symbol in each cell.

* **`generateVisualization(String[][] beadPattern, String outputImagePath)`:**
    - **Description:** Creates a visual representation of the bead pattern by assembling individual bead images into a final image file.
    - **Parameters:** 
        - `beadPattern`: A 2D string array representing the bead pattern.
        - `outputImagePath`: Path to save the generated visualization image.
    - **Return Values:** None.
    - **Important Logic:**
        - Calculates tile width and height based on bead image size and spacing.
        - Iterates through rows and columns of the `beadPattern`.
        - Loads each bead image using `loadBeadImage` based on the corresponding bead number.
        - Rounds the corners of each bead image using `roundCorners`.
        - Assembles the individual bead images into a final image using `BufferedImage` and `Graphics2D`.
        - Saves the generated image to the specified output path.

* **`loadBeadImage(String beadNumber)`:**
    - **Description:** Loads a bead image based on its number from a designated directory.
    - **Parameters:** `beadNumber`: The number identifying the bead image.
    - **Return Values:** A `BufferedImage` representing the loaded bead image, or null if the image is not found.
    - **Important Logic:**
        - Constructs the file path for the bead image based on the `beadNumber`.
        - Uses `ImageIO.read` to load the image from the specified path.

* **`roundCorners(BufferedImage image, int arcWidth, int arcHeight)`:**
    - **Description:** Rounds the corners of a given image using a specified arc width and height.
    - **Parameters:** 
        - `image`: The input image to be modified.
        - `arcWidth`: Width of the rounded corner arcs.
        - `arcHeight`: Height of the rounded corner arcs.
    - **Return Values:** A new `BufferedImage` with rounded corners.
    - **Important Logic:**
        - Creates a new `BufferedImage` with the same dimensions as the input image.
        - Fills the new image with a transparent background.
        - Draws a filled rectangle with rounded corners using `fillRoundRect`.
        - Overlays the original image onto the rounded rectangle using `drawImage`.



**6. Pseudo Code:**

```
// Class: BeadVisualizer

main()
  1. Get excel file path and output image path from configuration settings.
  2. Load bead number mapping from legend sheet in Excel.
  3. Load bead pattern from designated sheet in Excel.
  4. Generate visualization based on loaded pattern and bead images.
  5. Save generated visualization to specified output path.

loadBeadNumbersFromLegendSheet(excelFilePath)
  1. Open the Excel file.
  2. Get the "Legend" sheet.
  3. Iterate through each row in the sheet, starting from the second row (assuming headers are in the first row).
  4. Extract symbol and number values from each row.
  5. Store the mapping of symbols to numbers in a map.

loadPattern(excelFilePath, symbolToBeadMap)
  1. Open the Excel file.
  2. Get the "Size" sheet.
  3. Read pattern dimensions (width and height) from the "Size" sheet.
  4. Get the designated pattern sheet.
  5. Iterate through each row and column of the pattern sheet.
  6. For each cell, retrieve the corresponding bead number from the `symbolToBeadMap`.
  7. Store the bead numbers in a 2D array representing the pattern.

generateVisualization(beadPattern, outputImagePath)
  1. Calculate tile width and height based on bead image size and spacing.
  2. Create a new BufferedImage with dimensions equal to the total pattern size.
  3. Iterate through each row and column of the `beadPattern`.
  4. For each bead number:
    - Load the corresponding bead image using `loadBeadImage`.
    - Round the corners of the loaded image using `roundCorners`.
    - Draw the rounded bead image onto the final BufferedImage at the appropriate position.
  5. Save the generated BufferedImage to the specified output path.

loadBeadImage(beadNumber)
  1. Construct the file path for the bead image based on the `beadNumber`.
  2. Try to read the image from the file path using `ImageIO.read`.
  3. If successful, return the loaded image. Otherwise, return null.

roundCorners(image, arcWidth, arcHeight)
  1. Create a new BufferedImage with the same dimensions as the input image.
  2. Fill the new image with a transparent background.
  3. Draw a filled rectangle with rounded corners using `fillRoundRect`.
  4. Overlay the original image onto the rounded rectangle using `drawImage`.
  5. Return the modified image with rounded corners.



```

**Dependencies and Libraries:**

- **Apache POI:** Used for reading and writing Excel files.
- **javax.imageio:** Used for loading and saving images.
- **java.awt:** Provides classes for graphics and image manipulation.


