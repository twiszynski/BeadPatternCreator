![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Make it real.

---
## ImageColorReplacement Code Documentation

**1. Overview:**

The `ImageColorReplacement` Java program processes an input image and replaces its colors with closest matches from a predefined color palette defined in a CSV file. The output is a new image file with the replaced colors. 

**2. Package/module name:**

org.example

**3. Class/file name:**

ImageColorReplacement.java

**4. Class diagram name:**

ImageColorReplacement_diagram.svg

**5. Detailed Documentation:**


   - **`main(String[] args)`:**
     - **Description:** The entry point of the program. It reads configuration values, loads the color palette from a CSV file, reads the input image, replaces colors in the image using the loaded palette, and saves the processed image to a new file.
     - **Parameters:** `args`: An array of strings containing command-line arguments (not used in this code).
     - **Return Values:** None.
     - **Important Logic:** 
       - Retrieves paths for the CSV palette file, input image, and output image from configuration settings (`Config` class).
       - Calls `loadPaletteFromCsv()` to read the color palette from the CSV file.
       - Uses `ImageIO.read()` to load the input image.
       - Calls `replaceColors()` to perform color replacement in the image.
       - Saves the processed image using `ImageIO.write()`.

   - **`loadPaletteFromCsv(String csvFilePath)`:**
     - **Description:** Reads a color palette from a CSV file and returns a list of `PaletteColor` objects.
     - **Parameters:** `csvFilePath`: The path to the CSV file containing the color palette data.
     - **Return Values:** A list of `PaletteColor` objects representing the colors in the palette.
     - **Important Logic:** 
       - Uses Apache Commons CSV library to parse the CSV file.
       - Iterates through each record in the CSV and creates a `PaletteColor` object based on the data in each record.

   - **`replaceColors(BufferedImage image, List<PaletteColor> palette)`:**
     - **Description:** Replaces colors in an input image with closest matches from a given color palette.
     - **Parameters:** 
       - `image`: The input BufferedImage to process.
       - `palette`: A list of `PaletteColor` objects representing the available colors.
     - **Return Values:** A new BufferedImage with replaced colors.
     - **Important Logic:** 
       - Iterates through each pixel in the image.
       - For each pixel, it calls `findClosestPaletteColor()` to determine the closest color from the palette.
       - Sets the pixel's color in the new image to the found closest color.

   - **`findClosestPaletteColor(Color color, List<PaletteColor> palette)`:**
     - **Description:** Finds the closest color from a given palette to a target color using either RGB or LAB distance calculation based on configuration settings.
     - **Parameters:** 
       - `color`: The target color to find a match for.
       - `palette`: A list of `PaletteColor` objects representing the available colors.
     - **Return Values:** The `PaletteColor` object from the palette that is closest to the target color.
     - **Important Logic:** 
       - Iterates through each color in the palette.
       - Calculates the distance between the target color and each palette color using either RGB or LAB distance based on `Config.getColorMappingMode()`.
       - Returns the `PaletteColor` object with the smallest distance.



**6. Pseudo Code:**

```
// Main Program Logic
1. Get input image path, output image path, and CSV palette path from configuration settings.
2. Load color palette from CSV file using loadPaletteFromCsv() function.
3. Read input image using ImageIO.read().
4. Replace colors in the image using replaceColors() function with the loaded palette.
5. Save processed image to output file using ImageIO.write().

// Function: loadPaletteFromCsv(csvFilePath)
1. Open CSV file specified by csvFilePath.
2. Read each line from the CSV file, assuming a header row exists.
3. For each line (representing a color):
   - Extract RGB values, type, and comments from the line.
   - Create a new PaletteColor object with extracted data.
   - Add the PaletteColor object to the palette list.
4. Return the list of PaletteColor objects.

// Function: replaceColors(image, palette)
1. Get image width and height.
2. Create a new BufferedImage with same dimensions as input image.
3. Iterate through each pixel in the input image:
   - Get the RGB values of the current pixel.
   - Find the closest color from the palette using findClosestPaletteColor() function.
   - Set the RGB values of the corresponding pixel in the new image to the closest color's RGB values.
4. Return the new BufferedImage with replaced colors.

// Function: findClosestPaletteColor(color, palette)
1. Get the mapping mode (RGB or LAB) from configuration settings.
2. Iterate through each PaletteColor object in the palette:
   - Calculate the distance between the target color and the current palette color using either RGB or LAB distance calculation based on the mapping mode.
3. Return the PaletteColor object with the smallest calculated distance.

```



**7. Dependencies and Libraries:**


* **Apache Commons CSV:** Used for parsing the CSV file containing the color palette data. Equivalent libraries in other languages include:
    - Python: `csv` module
    - Java: `opencsv` library
    - C++: `libcsv` library

* **javax.imageio:** Used for reading and writing image files (PNG in this case).



