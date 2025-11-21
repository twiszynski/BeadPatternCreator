![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Get The Future You Want!

---
## ImageColorReplacement.java Documentation

**1. Overview:**

This Java program replaces colors in an input image with colors from a predefined palette specified in a CSV file. It reads the palette from the CSV, loads the input image, iterates through each pixel, finds the closest matching color from the palette, and creates a new image with the replaced colors. Finally, it saves the processed image to a specified output path.

**2. Package/Module Name:**

org.example

**3. Class/File Name:**

ImageColorReplacement.java

**4. Detailed Documentation:**


* **`main(String[] args)`:**
    - **Description:** The main method of the program, responsible for orchestrating the entire process of color replacement.
    - **Parameters:** `args`: An array of strings representing command-line arguments (not used in this code).
    - **Return Values:** None.
    - **Important Logic:**
        1. Retrieves paths to the CSV palette file, input image, and output image from configuration settings (`Config` class).
        2. Loads the color palette from the CSV file using `loadPaletteFromCsv()`.
        3. Reads the input image using `ImageIO.read()`.
        4. Calls `replaceColors()` to perform the color replacement on the image.
        5. Saves the processed image to the specified output path using `ImageIO.write()`.
        6. Prints a confirmation message indicating successful processing and output file path.

* **`PaletteColor`:**
    - **Description:** A class representing a single color entry from the palette, storing its RGB values, type, and comments.
    - **Parameters:** (Constructor) `number`, `r`, `g`, `b`, `type`, `comments`.
    - **Return Values:** None.
    - **Important Logic:**
        1. Stores the color information as attributes.
        2. Provides methods to calculate distances between this color and another given color using both RGB and LAB color spaces (`compareByRGB()`, `compareByLAB()`).
        3. Converts the stored RGB values into a `Color` object using `toColor()`.

* **`loadPaletteFromCsv(String csvFilePath)`:**
    - **Description:** Reads the color palette from a CSV file and returns a list of `PaletteColor` objects.
    - **Parameters:** `csvFilePath`: The path to the CSV file containing the palette data.
    - **Return Values:** A `List<PaletteColor>` representing the loaded palette.
    - **Important Logic:**
        1. Parses the CSV file using Apache Commons CSV library, assuming a specific header structure.
        2. Creates a `PaletteColor` object for each record in the CSV and adds it to the list.

* **`replaceColors(BufferedImage image, List<PaletteColor> palette)`:**
    - **Description:** Replaces the colors in an input image with the closest matching colors from the provided palette.
    - **Parameters:** `image`: The input `BufferedImage` object. `palette`: The list of `PaletteColor` objects representing the color palette.
    - **Return Values:** A new `BufferedImage` object with replaced colors.
    - **Important Logic:**
        1. Iterates through each pixel in the input image.
        2. For each pixel, it finds the closest matching color from the palette using `findClosestPaletteColor()`.
        3. Sets the pixel's color in the new image to the found closest color.

* **`findClosestPaletteColor(Color color, List<PaletteColor> palette)`:**
    - **Description:** Finds the closest matching color from a given palette to a specific input color.
    - **Parameters:** `color`: The input `Color` object. `palette`: The list of `PaletteColor` objects representing the color palette.
    - **Return Values:** A `PaletteColor` object representing the closest match found in the palette.
    - **Important Logic:**
        1. Iterates through each color in the palette.
        2. Calculates the distance between the input color and each palette color using either RGB or LAB color space based on the configuration (`Config.getColorMappingMode()`).
        3. Keeps track of the closest match found so far, updating it if a smaller distance is encountered.



**5. Pseudo Code:**

```
// Class: ImageColorReplacement

// Method: main(String[] args)
  1. Get paths to CSV palette file, input image, and output image from configuration settings.
  2. Load the color palette from the CSV file using loadPaletteFromCsv().
  3. Read the input image using ImageIO.read().
  4. Call replaceColors() to perform the color replacement on the image.
  5. Save the processed image to the specified output path using ImageIO.write().
  6. Print a confirmation message indicating successful processing and output file path.

// Method: loadPaletteFromCsv(String csvFilePath)
  1. Open the CSV file for reading.
  2. Parse the CSV file, assuming a specific header structure.
  3. For each record in the CSV:
    - Create a new PaletteColor object using the data from the record.
    - Add the PaletteColor object to a list.
  4. Return the list of PaletteColor objects.

// Method: replaceColors(BufferedImage image, List<PaletteColor> palette)
  1. Get the width and height of the input image.
  2. Create a new BufferedImage with the same dimensions as the input image.
  3. Iterate through each pixel in the input image:
    - Get the color of the current pixel.
    - Find the closest matching color from the palette using findClosestPaletteColor().
    - Set the color of the corresponding pixel in the new image to the found closest color.
  4. Return the new BufferedImage with replaced colors.

// Method: findClosestPaletteColor(Color color, List<PaletteColor> palette)
  1. Initialize a variable to store the closest PaletteColor object found so far (set to null initially).
  2. Iterate through each PaletteColor object in the palette:
    - Calculate the distance between the input color and the current PaletteColor object using either RGB or LAB color space based on configuration settings.
    - If the calculated distance is smaller than the distance to the previously found closest match:
      - Update the closest PaletteColor object to the current PaletteColor object.
  3. Return the closest PaletteColor object found.



```

**6. Dependencies and Libraries:**


* **Apache Commons CSV:** Used for parsing the CSV palette file. Equivalent libraries in other languages include `csv` (Python), `js-csv` (JavaScript), etc.
* **javax.imageio:** Java API for reading and writing images. No direct equivalent in other languages, but similar functionalities exist in most programming languages.



