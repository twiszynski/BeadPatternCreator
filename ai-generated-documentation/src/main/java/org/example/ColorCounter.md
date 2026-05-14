![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Make it real.

---
## ColorCounter Code Documentation

**1. Overview:**

This Java program counts the number of unique colors present in a given image file. It reads the image using the `ImageIO` library, iterates through each pixel, extracts its RGB color value, and stores it in a `HashSet` to ensure uniqueness. Finally, it returns the size of the set, representing the total count of unique colors.

**2. Package/module name:**

org.example

**3. Class/file name:**

ColorCounter.java

**4. Class diagram name:**

ColorCounter_diagram.svg

**5. Detailed Documentation:**

   - **`main(String[] args)` Method:**
     - **Description:** This is the entry point of the program. It sets the image file name, constructs the full path to the image, calls the `countUniqueColors` method to get the count of unique colors, and prints the result to the console.
     - **Parameters:** 
       - `args`: An array of strings representing command-line arguments (not used in this program).
     - **Return Values:** None.
     - **Important Logic:**  The code assumes that the image file is named "mount_sun_2.png" and located in the "C:\\Users\\Admin\\Desktop\\ImgToExcel\" directory.

   - **`countUniqueColors(String imagePath)` Method:**
     - **Description:** This method takes the path to an image file as input and returns the number of unique colors present in the image.
     - **Parameters:** 
       - `imagePath`: A string representing the full path to the image file.
     - **Return Values:** An integer representing the count of unique colors in the image. Returns -1 if there is an error loading the image.
     - **Important Logic:**
       - The method uses a `try-catch` block to handle potential `IOExceptions` that may occur when reading the image file. 
       - It loads the image using `ImageIO.read(new File(imagePath))`.
       - A `HashSet` called `uniqueColors` is used to store unique RGB color values extracted from each pixel.
       - The code iterates through each pixel of the image, retrieves its RGB value using `image.getRGB(x, y)`, and adds it to the `uniqueColors` set. 
       - Finally, it returns the size of the `uniqueColors` set, which represents the count of unique colors.

**6. Pseudo Code:**


```
// Class: ColorCounter

// Method: main(args)
  1. Set image file name to "mount_sun_2"
  2. Construct full path to image file (e.g., "C:\\Users\\Admin\\Desktop\\ImgToExcel\\mount_sun_2.png")
  3. Call countUniqueColors method with the image path
  4. Print the returned unique color count to console

// Method: countUniqueColors(imagePath)
  1. Try to read the image file using ImageIO.read(new File(imagePath))
    - If successful, proceed to step 2
    - If an error occurs (IOException), print an error message and return -1
  2. Create a HashSet called uniqueColors to store unique RGB color values
  3. Iterate through each pixel of the image:
    - For each pixel (x, y):
      - Get the RGB value of the pixel using image.getRGB(x, y)
      - Add the RGB value to the uniqueColors set
  4. Return the size of the uniqueColors set (representing the count of unique colors)



``` 


**Dependencies and Libraries:**

* **ImageIO:** This Java library is used for reading and writing images. It's part of the standard Java API, so no additional installation is required.

* **File:**  This Java class is used to represent files and directories. It's also part of the standard Java API.



