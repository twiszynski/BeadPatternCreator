![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Make it real.

---
## ImageColorReplacement.java Documentation & Pseudocode

**1. Overview:**

This Java program processes an image by replacing its colors with closest matches from a predefined color palette defined in a CSV file. The program reads the input image, loads the color palette, iterates through each pixel of the image, finds the closest matching color from the palette, and replaces the original pixel color with the chosen palette color. Finally, it saves the modified image as a PNG file.

**2. Package/module name:** org.example

**3. Class/file name:** ImageColorReplacement.java

**4. Detailed Documentation:**

   - **`main(String[] args)`:**
     - **Description:** The main method of the program, responsible for executing the image processing workflow.
     - **Parameters:** `args`: An array of strings representing command-line arguments (not used in this code).
     - **Return Values:** None.
     - **Important Logic:**
       - Reads the CSV palette file path from a configuration object (`Config`).
       - Reads the input image path from a configuration object (`Config`).
       - Reads the output image path from a configuration object (`Config`).
       - Calls `loadPaletteFromCsv` to load the color palette.
       - Calls `ImageIO.read` to read the input image.
       - Calls `replaceColors` to replace colors in the image using the loaded palette.
       - Calls `ImageIO.write` to save the processed image as a PNG file.
       - Prints a confirmation message indicating the output image path.

   - **`PaletteColor`:**
     - **Description:** A class representing a single color from the palette, containing its RGB values, type, and comments.
     - **Parameters:** `number`, `r`, `g`, `b`, `type`, `comments`: String and integer values defining the color properties.
     - **Return Values:** None.
     - **Important Logic:**
       - Provides methods for calculating distances between colors using RGB (`compareByRGB`) and LAB (`compareByLAB`) color spaces.
       - Provides a method to convert the palette color to a `Color` object (`toColor`).

   - **`loadPaletteFromCsv(String csvFilePath)`:**
     - **Description:** Reads the color palette from a CSV file, parsing each line into a `PaletteColor` object and storing them in a list.
     - **Parameters:** `csvFilePath`: String representing the path to the CSV file containing the palette data.
     - **Return Values:** A list of `PaletteColor` objects representing the loaded palette.
     - **Important Logic:**
       - Uses Apache Commons CSV library to parse the CSV file.
       - Extracts color information (number, R, G, B, type, comments) from each record and creates a `PaletteColor` object for each entry.

   - **`replaceColors(BufferedImage image, List<PaletteColor> palette)`:**
     - **Description:** Iterates through each pixel of the input image, finds the closest matching color from the palette using `findClosestPaletteColor`, and replaces the original pixel color with the chosen palette color.
     - **Parameters:** `image`: A `BufferedImage` object representing the input image. `palette`: A list of `PaletteColor` objects representing the color palette.
     - **Return Values:** A new `BufferedImage` object with replaced colors.
     - **Important Logic:**
       - Creates a new `BufferedImage` object to store the processed image.
       - Iterates through each pixel of the input image using nested loops.
       - Calls `findClosestPaletteColor` to find the closest matching color from the palette for the current pixel.
       - Sets the RGB value of the corresponding pixel in the new image with the chosen palette color.

   - **`findClosestPaletteColor(Color color, List<PaletteColor> palette)`:**
     - **Description:** Finds the closest matching color from the palette to a given `Color` object. Uses either RGB or LAB distance based on the configuration setting (`Config.getColorMappingMode()`).
     - **Parameters:** `color`: A `Color` object representing the pixel color to compare. `palette`: A list of `PaletteColor` objects representing the color palette.
     - **Return Values:** The `PaletteColor` object from the palette that is closest to the given `Color`.
     - **Important Logic:**
       - Iterates through each color in the palette.
       - Calculates the distance between the input `color` and the current palette color using either `compareByRGB` or `compareByLAB` based on the configuration setting.
       - Keeps track of the closest color found so far.

**5. Pseudocode:**


```
// Class: ImageColorReplacement

// Method: main(String[] args)
  1. Get CSV palette file path from Config object.
  2. Get input image path from Config object.
  3. Get output image path from Config object.
  4. Load the color palette from the CSV file using loadPaletteFromCsv function.
  5. Read the input image using ImageIO.read function.
  6. Replace colors in the image using replaceColors function, passing the loaded palette and the input image.
  7. Save the processed image as a PNG file using ImageIO.write function.
  8. Print a confirmation message indicating the output image path.

// Method: loadPaletteFromCsv(String csvFilePath)
  1. Open the CSV file for reading.
  2. Parse the CSV file using Apache Commons CSV library, parsing each line into a PaletteColor object.
  3. Store all parsed PaletteColor objects in a list.
  4. Return the list of PaletteColor objects.

// Method: replaceColors(BufferedImage image, List<PaletteColor> palette)
  1. Create a new BufferedImage object with the same dimensions as the input image.
  2. Iterate through each pixel of the input image using nested loops.
  3. For each pixel:
    - Get the RGB values of the current pixel.
    - Find the closest matching color from the palette using findClosestPaletteColor function, passing the current pixel's color and the palette.
    - Set the RGB value of the corresponding pixel in the new image with the chosen palette color.
  4. Return the new BufferedImage object with replaced colors.

// Method: findClosestPaletteColor(Color color, List<PaletteColor> palette)
  1. Get the current color mapping mode from Config object (RGB or LAB).
  2. Iterate through each PaletteColor in the palette.
  3. Calculate the distance between the input color and the current palette color using either compareByRGB or compareByLAB function based on the mapping mode.
  4. Keep track of the PaletteColor with the smallest distance found so far.
  5. Return the PaletteColor with the smallest distance.



```

**6. Dependencies and Libraries:**

- **Apache Commons CSV:** Used for parsing the CSV file containing the color palette.


