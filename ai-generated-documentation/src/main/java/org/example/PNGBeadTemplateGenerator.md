![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Get The Future You Want!

---
## PNGBeadTemplateGenerator.java Documentation

**1. Overview:**

This Java program generates PNG bead templates based on a CSV file containing color palettes and effect configurations. It reads data from the CSV file, applies various effects to a base color, and saves the resulting images as individual PNG files in a designated output directory. 

**2. Package/Module Name:**

org.example

**3. Class/File Name:**

PNGBeadTemplateGenerator.java

**4. Detailed Documentation:**


* **`main(String[] args)`:**
    - **Description:** The entry point of the program. It calls the `generateTemplates()` method to initiate the template generation process.
    - **Parameters:** 
        - `args`: An array of strings representing command-line arguments (not used in this code).
    - **Return Values:** None
    - **Important Logic:** Handles potential exceptions during the template generation process and prints stack traces for debugging.

* **`generateTemplates()` throws IOException:**
    - **Description:** This method reads data from the CSV file, processes each bead template definition, applies effects, and saves the resulting images as PNG files.
    - **Parameters:** None
    - **Return Values:** None
    - **Important Logic:**
        1. Creates the output directory if it doesn't exist.
        2. Reads the CSV file line by line, skipping the header line.
        3. For each bead template definition:
            - Parses the color values (R, G, B) and effect type from the CSV line.
            - Retrieves the corresponding effect layers configuration from a map (`FinishesConfig.EFFECT_CONFIGS`).
            - If no configuration is found for the specified effect type, it uses a default configuration ("Basic").
            - Creates a new BufferedImage to hold the final template image.
            - Iterates through each effect layer:
                - If the layer name starts with "BaseColor", it sets the base color using the parsed RGB values and opacity.
                - Otherwise, it loads the corresponding effect image file, checks its dimensions, and applies opacity to the image.
                - Draws the transparent effect layer onto the final image.
            - Saves the composed image as a PNG file in the output directory with a unique filename based on the bead template number.

* **`applyOpacity(BufferedImage image, int opacityPercent)`:**
    - **Description:** This method applies opacity to an input BufferedImage.
    - **Parameters:**
        - `image`: The input BufferedImage to apply opacity to.
        - `opacityPercent`: The desired opacity level as a percentage (0-100).
    - **Return Values:** A new BufferedImage with the applied opacity.
    - **Important Logic:**
        1. Creates a new BufferedImage with the same dimensions as the input image and ARGB type.
        2. Iterates through each pixel in the input image:
            - Retrieves the RGB color value and its alpha component.
            - Calculates the new alpha value based on the opacity percentage.
            - Creates a new Color object with the modified alpha value and sets it as the pixel color in the output image.



**5. Pseudo Code:**

```
// Class: PNGBeadTemplateGenerator

// Method: main(String[] args)
  1. Try to execute generateTemplates() method
  2. If an exception occurs, print stack trace and exit program

// Method: generateTemplates() throws IOException
  1. Create output directory if it doesn't exist
  2. Open CSV file for reading
  3. Read each line from the CSV file
    - Skip the header line
    - Parse bead template data (number, color values, effect type)
    - Retrieve effect layers configuration based on effect type
      - If no configuration found, use default ("Basic") configuration
    4. Create a new BufferedImage for the template image
    5. Iterate through each effect layer:
      - If layer name starts with "BaseColor":
        - Set base color using parsed RGB values and opacity
      - Otherwise:
        - Load corresponding effect image file
        - Check if image dimensions are correct (WIDTH x HEIGHT)
        - Apply opacity to the effect image
        - Draw the transparent effect layer onto the template image
    6. Save the composed image as a PNG file in the output directory
  7. Close CSV file

// Method: applyOpacity(BufferedImage image, int opacityPercent)
  1. Create a new BufferedImage with ARGB type and same dimensions as input image
  2. Iterate through each pixel in the input image
    - Get RGB color value and alpha component
    - Calculate new alpha value based on opacity percentage
    - Set the new Color object with modified alpha to the corresponding pixel in the output image
  3. Return the new BufferedImage with applied opacity



```

**Dependencies and Libraries:**


* **javax.imageio:** This library is used for reading and writing images (ImageIO). It's a standard Java library.
* **java.awt:** Provides classes for graphics and user interface elements (Graphics2D, Color, etc.). Also a standard Java library.
* **java.io:** Used for file input/output operations (FileReader, BufferedReader, FileOutputStream, etc.). Standard Java library.
* **java.nio.file:** For working with files and directories (Files, Paths). Standard Java library.



