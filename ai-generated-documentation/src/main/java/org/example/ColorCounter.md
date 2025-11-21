![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Get The Future You Want!

---
## ColorCounter.java Documentation

**1. Overview:**

This Java program analyzes an image file and counts the number of unique colors present in it. It reads the image using the `ImageIO` library, iterates through each pixel, extracts its RGB color value, and stores it in a `HashSet` to ensure uniqueness. Finally, it returns the size of the set, representing the total count of unique colors.

**2. Package/module name:** org.example

**3. Class/file name:** ColorCounter.java

**4. Detailed Documentation:**

   - **Function: main(String[] args)**
     - **Description:** The entry point of the program. It sets the image filename, constructs the full image path, calls the `countUniqueColors` function to get the unique color count, and prints the result to the console.
     - **Parameters:** 
       - `args`: String array containing command-line arguments (not used in this program).
     - **Return Values:** None
     - **Important Logic:**  The main function sets up the image path based on the provided filename and calls the `countUniqueColors` function to perform the color counting.

   - **Function: countUniqueColors(String imagePath)**
     - **Description:** This function reads an image from the specified path, extracts unique colors from it, and returns their count.
     - **Parameters:** 
       - `imagePath`: String representing the full path to the image file.
     - **Return Values:** Integer representing the number of unique colors found in the image. Returns -1 if an error occurs while loading the image.
     - **Important Logic:**
       - The function uses a `try-catch` block to handle potential `IOExceptions` during image loading.
       - It reads the image using `ImageIO.read(new File(imagePath))`.
       - A `HashSet` named `uniqueColors` is used to store unique RGB color values encountered in the image. 
       - The function iterates through each pixel of the image, extracts its RGB value using `image.getRGB(x, y)`, and adds it to the `uniqueColors` set.
       - Finally, it returns the size of the `uniqueColors` set, which represents the count of unique colors.

**5. Pseudo Code:**


```
// Class: ColorCounter

// Method: main(String[] args)
  1. Set imageName variable to "mount_sun_2"
  2. Construct imagePath by combining "C:\\Users\\Admin\\Desktop\\ImgToExcel\\" with imageName and ".png" extension
  3. Call countUniqueColors function with imagePath as argument and store the returned value in uniqueColorsCount variable
  4. Print uniqueColorsCount to console

// Method: countUniqueColors(String imagePath)
  1. Try to read image from imagePath using ImageIO.read()
    - If successful, proceed to step 2
    - If unsuccessful, print an error message and return -1
  2. Create a HashSet named uniqueColors to store unique RGB color values
  3. Iterate through each pixel of the image:
    - For each pixel (x, y):
      - Get the RGB value of the pixel using image.getRGB(x, y)
      - Add the RGB value to the uniqueColors set
  4. Return the size of the uniqueColors set

```



**Dependencies and Libraries:**

* **ImageIO:** This library is part of Java's standard libraries and is used for reading and writing images. 


Let me know if you have any other questions or need further clarification on any aspect of this documentation!