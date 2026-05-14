![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Make it real.

---
## PNGBeadTemplateGenerator Documentation

**1. Overview:**

The `PNGBeadTemplateGenerator` class generates PNG bead templates based on data provided in a CSV file. It reads color information, effect types, and layer configurations from the CSV and combines them to create unique bead designs. Each template is saved as a separate PNG file in the specified output directory.

**2. Package/module name:** `org.example`

**3. Class/file name:** `PNGBeadTemplateGenerator.java`

**4. Class diagram name:** `PNGBeadTemplateGenerator_diagram.svg`

**5. Detailed Documentation:**

   - **`main(String[] args)`:**
     - **Description:** The entry point of the application. It calls the `generateTemplates()` method to initiate the template generation process.
     - **Parameters:** 
       - `args`: An array of strings representing command-line arguments (not used in this code).
     - **Return Values:** None
     - **Important Logic:** Handles potential `IOException` during template generation and prints the stack trace if an error occurs.

   - **`generateTemplates()` throws IOException:**
     - **Description:** Reads data from the CSV file, processes each bead definition, generates the corresponding image, and saves it as a PNG file.
     - **Parameters:** None
     - **Return Values:** None
     - **Important Logic:**
       - Creates the output directory if it doesn't exist.
       - Reads the CSV file line by line, skipping the header line.
       - For each bead definition:
         - Parses color and effect type information from the CSV line.
         - Retrieves layer configurations based on the effect type from a predefined map (`FinishesConfig.EFFECT_CONFIGS`).
         - Creates a new BufferedImage for the bead template.
         - Iterates through each layer configuration, applying opacity and drawing the corresponding image or color onto the canvas.
         - Saves the generated image as a PNG file in the output directory.

   - **`applyOpacity(BufferedImage image, int opacityPercent)`:**
     - **Description:** Applies opacity to an input image by modifying its alpha channel.
     - **Parameters:**
       - `image`: The input BufferedImage.
       - `opacityPercent`: The desired opacity level as a percentage (0-100).
     - **Return Values:** A new BufferedImage with the applied opacity.
     - **Important Logic:** Iterates through each pixel of the input image, calculates the new alpha value based on the opacity percentage, and creates a new color with the adjusted alpha.

**6. Pseudo Code:**


```
// Class: PNGBeadTemplateGenerator

// Method: main(args)
  1. Call generateTemplates() 
  2. Handle potential IOException during template generation
    - If an error occurs, print the stack trace and exit

// Method: generateTemplates() throws IOException
  1. Create output directory if it doesn't exist
  2. Open CSV file for reading
  3. Read each line from the CSV file
    - Skip the header line
    - Parse color information (R, G, B) and effect type from the current line
    - Retrieve layer configurations based on the effect type from FinishesConfig.EFFECT_CONFIGS
      - If no configuration is found for the effect type, use the default "Basic" configuration
    4. Create a new BufferedImage for the bead template
    5. For each layer configuration:
      - Get the layer name and opacity value
      - If the layer name starts with "BaseColor":
        - Set the base color of the image using the parsed R, G, B values and the opacity
      - Otherwise:
        - Load the corresponding effect image from the EFFECTS_DIR_PATH
        - Check if the loaded image has the correct dimensions (WIDTH x HEIGHT)
        - Apply opacity to the loaded image using applyOpacity() function
        - Draw the transparent layer onto the bead template image
    6. Save the generated image as a PNG file in the output directory

// Method: applyOpacity(image, opacityPercent)
  1. Create a new BufferedImage with the same dimensions as the input image
  2. Calculate the alpha factor based on the opacity percentage
  3. Iterate through each pixel of the input image
    - Get the current color and its alpha value
    - Calculate the new alpha value by multiplying the original alpha with the alpha factor
    - Create a new Color object with the original RGB values and the calculated new alpha
    - Set the new color for the corresponding pixel in the output image
  4. Return the modified BufferedImage



```

**7. Dependencies and Libraries:**


* **ImageIO:** Used for reading and writing images (PNG format). Equivalent libraries in other languages include:
    * Java: javax.imageio.*
    * Python: Pillow (PIL)
    * C++: OpenCV, stb_image
* **Files:** Used for file system operations like creating directories. Equivalent libraries in other languages include:
    * Java: java.nio.file.*
    * Python: os, pathlib
    * C++: filesystem library

**Note:** This documentation assumes that `Config` and `FinishesConfig` classes are defined elsewhere and provide the necessary configuration data for the application.



