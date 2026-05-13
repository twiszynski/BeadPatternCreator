![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Make it real.

---
## PNGBeadTemplateGenerator Documentation

**1. Overview:**

The `PNGBeadTemplateGenerator` class generates PNG bead templates based on a CSV file containing color palettes and finish effects. It reads the CSV data, processes each entry, creates a layered image with base colors and overlay effects, and saves the resulting PNG files in a designated output directory. 

**2. Package/module name:** `org.example`

**3. Class/file name:** `PNGBeadTemplateGenerator.java`

**4. Detailed Documentation:**

* **Class: `PNGBeadTemplateGenerator`**
    -  **Purpose:** Generates PNG bead templates based on a CSV file containing color palettes and finish effects. 
    - **Dependencies:** Requires the `javax.imageio` library for image processing, `java.io` for file handling, and `java.nio.file` for directory operations.

* **Method: `main(String[] args)`**
    -  **Description:** Entry point of the application. Calls the `generateTemplates()` method to initiate template generation.
    -  **Parameters:** 
        - `args`: Command line arguments (not used in this code).
    -  **Return Values:** None.
    - **Important Logic:** Handles potential `IOException` during file operations and prints stack traces for debugging.

* **Method: `generateTemplates()` throws IOException**
    -  **Description:** Generates bead templates based on the CSV data. Creates output directory, reads CSV file line by line, processes each entry, generates the image, and saves it as a PNG file.
    -  **Parameters:** None.
    -  **Return Values:** None.
    - **Important Logic:** 
        - Skips the header line in the CSV file.
        - Parses each line into color values (R, G, B) and effect type.
        - Retrieves layer configurations for the specified effect type from `FinishesConfig`.
        - Creates a new BufferedImage with dimensions defined by constants (`WIDTH` and `HEIGHT`).
        - Iterates through each layer configuration:
            - If it's a "BaseColor" layer, sets the base color using the parsed RGB values.
            - Otherwise, loads the overlay image from the specified path, applies opacity based on the layer configuration, and draws it onto the BufferedImage.
        - Saves the generated image as a PNG file in the output directory with a unique filename based on the CSV entry number.

* **Method: `applyOpacity(BufferedImage image, int opacityPercent)`**
    -  **Description:** Applies opacity to an input image. Creates a new BufferedImage with the same dimensions and iterates through each pixel, modifying its alpha value based on the provided opacity percentage.
    -  **Parameters:** 
        - `image`: The input BufferedImage.
        - `opacityPercent`: The desired opacity as a percentage (0-100).
    -  **Return Values:** A new BufferedImage with applied opacity.

* **Variables and Data Structures:**
    - `WIDTH`, `HEIGHT`: Constants defining the dimensions of the generated bead templates.
    - `CSV_PATH`: Path to the CSV file containing color palettes and finish effects.
    - `OUTPUT_DIR`: Directory path for saving the generated PNG files.
    - `EFFECTS_DIR_PATH`: Path to the directory containing overlay effect images.
    - `FinishesConfig`: A class (not shown in the provided code) likely holding configurations for different finish types and their corresponding layer settings.



**5. Pseudo Code:**

```
// Class: PNGBeadTemplateGenerator

// Method: main(String[] args)
  1. Try to execute generateTemplates() method
  2. If an IOException occurs, print the stack trace and exit

// Method: generateTemplates() throws IOException
  1. Create output directory if it doesn't exist
  2. Open CSV file for reading
  3. Read each line from the CSV file
    - Skip the first line (header)
    - Parse the line into color values (R, G, B) and effect type
    - Retrieve layer configurations for the specified effect type from FinishesConfig
    - Create a new BufferedImage with dimensions WIDTH x HEIGHT
    - For each layer configuration:
      - If it's a "BaseColor" layer:
        - Set the base color using the parsed RGB values
      - Otherwise:
        - Load the overlay image from the specified path
        - Apply opacity to the overlay image based on the layer configuration
        - Draw the transparent overlay onto the BufferedImage
    - Save the generated image as a PNG file in the output directory with a unique filename

// Method: applyOpacity(BufferedImage image, int opacityPercent)
  1. Create a new BufferedImage with the same dimensions as the input image
  2. For each pixel in the input image:
    - Calculate the new alpha value based on the opacity percentage
    - Set the new alpha value for the corresponding pixel in the output image



```

**6. Dependencies and Libraries:**


* **javax.imageio:** Image I/O library for reading and writing images. 
* **java.io:** Input/Output library for file handling.
* **java.nio.file:**  Provides classes for working with files and directories.

**Equivalent Libraries in Other Languages:**

* **Java:** javax.imageio, java.io, java.nio.file (already used)
* **Python:** Pillow (PIL), os, pathlib
* **C++:** OpenCV, Boost.Filesystem
* **JavaScript:** ImageMagick, fs



