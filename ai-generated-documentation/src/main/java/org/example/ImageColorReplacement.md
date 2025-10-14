![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Get The Future You Want!

---
## ImageColorReplacement.java Documentation

**1. Overview:**

This Java program replaces colors in an input image with colors from a predefined palette specified in a CSV file. It reads the palette from the CSV, loads the input image, iterates through each pixel, finds the closest matching color from the palette, and replaces the original pixel color with the chosen palette color. The processed image is then saved as a new PNG file.

**2. Package/Module Name:**

org.example

**3. Class/File Name:**

ImageColorReplacement.java

**4. Detailed Documentation:**


* **`main(String[] args)`:**
    - **Description:** This is the entry point of the program. It orchestrates the entire process of loading the palette, reading the input image, replacing colors, and saving the processed image.
    - **Parameters:** `args`: An array of strings representing command-line arguments (not used in this code).
    - **Return Values:** None.
    - **Important Logic:** 
        - Retrieves paths to the CSV palette file, input image, and output image from configuration settings (`Config` class).
        - Calls `loadPaletteFromCsv()` to read the color palette from the CSV file.
        - Uses `ImageIO.read()` to load the input image.
        - Calls `replaceColors()` to perform the color replacement operation.
        - Saves the processed image using `ImageIO.write()`.

* **`PaletteColor`:**
    - **Description:** A class representing a single color entry from the palette. It stores RGB values, type information, and comments.
    - **Parameters:** (Constructor) `number`, `r`, `g`, `b`, `type`, `comments` - String for number, integers for RGB values, string for type and comments.
    - **Return Values:** None.
    - **Important Logic:**
        - Provides methods to calculate the distance between this color and another given color using both RGB and LAB color spaces (using `compareByRGB()` and `compareByLAB()`).
        - Converts the PaletteColor object to a standard Java `Color` object using `toColor()`.

* **`loadPaletteFromCsv(String csvFilePath)`:**
    - **Description:** Reads the color palette from a CSV file.
    - **Parameters:** `csvFilePath`: String representing the path to the CSV file.
    - **Return Values:** A list of `PaletteColor` objects representing the colors in the palette.
    - **Important Logic:**
        - Uses Apache Commons CSV library to parse the CSV file.
        - Iterates through each record and creates a `PaletteColor` object based on the data in the record.

* **`replaceColors(BufferedImage image, List<PaletteColor> palette)`:**
    - **Description:** Replaces colors in the input image with colors from the provided palette.
    - **Parameters:** `image`: The input BufferedImage to process. `palette`: A list of `PaletteColor` objects representing the color palette.
    - **Return Values:** A new BufferedImage with replaced colors.
    - **Important Logic:**
        - Iterates through each pixel in the image.
        - For each pixel, it finds the closest matching color from the palette using `findClosestPaletteColor()`.
        - Sets the pixel's color to the chosen palette color.

* **`findClosestPaletteColor(Color color, List<PaletteColor> palette)`:**
    - **Description:** Finds the closest matching color in the palette to a given color.
    - **Parameters:** `color`: The target color to find a match for. `palette`: A list of `PaletteColor` objects representing the color palette.
    - **Return Values:** The `PaletteColor` object from the palette that is closest to the input color.
    - **Important Logic:**
        - Iterates through each color in the palette.
        - Calculates the distance between the target color and each palette color using either RGB or LAB color space (based on the configuration setting).
        - Returns the `PaletteColor` with the smallest distance.



**5. Pseudo Code:**

```
// Class: ImageColorReplacement

// Method: main(String[] args)
  1. Get paths to CSV palette file, input image, and output image from configuration settings.
  2. Load color palette from CSV file using loadPaletteFromCsv().
  3. Read input image using ImageIO.read().
  4. Replace colors in the image using replaceColors() with the loaded palette.
  5. Save processed image as PNG file using ImageIO.write().

// Method: loadPaletteFromCsv(String csvFilePath)
  1. Open CSV file for reading.
  2. Parse CSV file using Apache Commons CSV library.
  3. For each record in the CSV:
    - Extract color information (number, R, G, B, type, comments).
    - Create a PaletteColor object with extracted data.
    - Add PaletteColor object to the palette list.
  4. Return the list of PaletteColor objects.

// Method: replaceColors(BufferedImage image, List<PaletteColor> palette)
  1. Get image width and height.
  2. Create a new BufferedImage with same dimensions as input image.
  3. Iterate through each pixel in the input image:
    - Get the color of the current pixel.
    - Find the closest matching PaletteColor from the palette using findClosestPaletteColor().
    - Set the color of the corresponding pixel in the new image to the found PaletteColor's color.
  4. Return the new BufferedImage with replaced colors.

// Method: findClosestPaletteColor(Color color, List<PaletteColor> palette)
  1. Initialize closestPaletteColor to null and minimum distance to infinity.
  2. Iterate through each PaletteColor in the palette:
    - Calculate the distance between the input color and the current PaletteColor using either RGB or LAB color space (based on configuration).
    - If the calculated distance is less than the current minimum distance:
      - Update closestPaletteColor to the current PaletteColor.
      - Update minimum distance to the calculated distance.
  3. Return the closestPaletteColor found.



```

**6. Dependencies and Libraries:**


* **Apache Commons CSV:** Used for parsing the CSV file containing the color palette. 
* **javax.imageio:** Java API for reading and writing images.

**7. Security Considerations:**

- No sensitive information is handled in this code. However, it's important to note that any user-supplied input (e.g., image paths) should be validated to prevent potential security vulnerabilities like directory traversal attacks or file injection.



