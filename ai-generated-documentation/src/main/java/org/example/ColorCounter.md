![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Make it real.

---
## ColorCounter.java Documentation

**1. Overview:**

This Java program analyzes an image file and counts the number of unique colors present within it. It reads the image using the `ImageIO` library, iterates through each pixel, extracts its RGB color value, and stores it in a `HashSet` to ensure uniqueness. Finally, it returns the size of the set, representing the total count of unique colors.

**2. Package/module name:** org.example

**3. Class/file name:** ColorCounter.java

**4. Detailed Documentation:**

   - **Function/Method: `main(String[] args)`**
     - **Description:** The entry point of the program. It sets the image filename, constructs the full image path, calls the `countUniqueColors` function to get the unique color count, and prints the result to the console.
     - **Parameters:** 
       - `args`: An array of strings representing command-line arguments (not used in this program).
     - **Return Values:** None
     - **Important Logic:**  The code assumes that an image file with the specified name exists in the designated directory. It also handles potential errors during image loading and prints an error message if the image cannot be loaded.

   - **Function/Method: `countUniqueColors(String imagePath)`**
     - **Description:** This function takes the path to an image file as input and returns the count of unique colors present in the image.
     - **Parameters:** 
       - `imagePath`: A string representing the full path to the image file.
     - **Return Values:** An integer representing the number of unique colors found in the image. Returns -1 if an error occurs during image loading.
     - **Important Logic:**  The function uses a `try-catch` block to handle potential `IOExceptions` that may occur when reading the image file. It iterates through each pixel of the image, extracts its RGB color value, and adds it to a `HashSet`. The size of the `HashSet` represents the number of unique colors found in the image.

**5. Pseudo Code:**


```
// Class: ColorCounter

// Method: main(String[] args)
  1. Set imageName variable to "mount_sun_2"
  2. Construct imagePath by combining the base directory and imageName with ".png" extension
  3. Call countUniqueColors function with imagePath as argument, store result in uniqueColorsCount variable
  4. Print uniqueColorsCount to console

// Method: countUniqueColors(String imagePath)
  1. Try:
    - Read image from imagePath using ImageIO.read()
    - Create a HashSet called uniqueColors to store unique color values
    - Iterate through each pixel of the image:
      - For each row (y):
        - For each column (x):
          - Get RGB value of pixel at (x, y) using image.getRGB(x, y)
          - Add RGB value to uniqueColors HashSet
    - Return size of uniqueColors HashSet 
  2. Catch IOException:
    - Print error message indicating inability to load the image
    - Return -1

```



**Dependencies and Libraries:**

* **javax.imageio:** This package provides functionality for reading and writing images in various formats. It's part of the Java standard library.


Let me know if you have any other questions or need further clarification on any aspect of this documentation!