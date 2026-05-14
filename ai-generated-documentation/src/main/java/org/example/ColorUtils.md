![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Make it real.

---
## ColorUtils Documentation

**1. Overview:**

The `ColorUtils` class provides a set of functions to convert between RGB color space and CIELAB color space, as well as calculate the CIEDE2000 color difference between two colors. This is useful for tasks like comparing colors visually, adjusting colors based on perceived differences, or working with color spaces that are more perceptually uniform than RGB.

**2. Package/module name:** org.example

**3. Class/file name:** ColorUtils.java

**4. Class diagram name:** ColorUtils_diagram.svg 

**5. Detailed Documentation:**

   - **`rgbToLab(int r, int g, int b)`:**
     - **Description:** Converts an RGB color (represented as integers for red, green, and blue values) to its equivalent CIELAB representation.
     - **Parameters:**
       - `r`: Integer representing the red component of the RGB color (0-255).
       - `g`: Integer representing the green component of the RGB color (0-255).
       - `b`: Integer representing the blue component of the RGB color (0-255).
     - **Return Values:** A double array containing three values: L (lightness), a (red-green axis), and b (blue-yellow axis) in CIELAB space.
     - **Important Logic:** 
       1. Converts RGB to XYZ using the `rgbToXyz` function.
       2. Converts XYZ to CIELAB using the `xyzToLab` function.

   - **`rgbToXyz(int r, int g, int b)`:**
     - **Description:** Converts an RGB color to its equivalent XYZ representation.
     - **Parameters:**
       - `r`: Integer representing the red component of the RGB color (0-255).
       - `g`: Integer representing the green component of the RGB color (0-255).
       - `b`: Integer representing the blue component of the RGB color (0-255).
     - **Return Values:** A double array containing three values: x, y, and z in XYZ space.
     - **Important Logic:** 
       1. Normalizes RGB values to the range [0, 1].
       2. Applies the CIE XYZ color matching functions to calculate x, y, and z values based on the normalized RGB components.

   - **`xyzToLab(double x, double y, double z)`:**
     - **Description:** Converts an XYZ color to its equivalent CIELAB representation.
     - **Parameters:**
       - `x`: Double representing the x component of the XYZ color.
       - `y`: Double representing the y component of the XYZ color.
       - `z`: Double representing the z component of the XYZ color.
     - **Return Values:** A double array containing three values: L (lightness), a (red-green axis), and b (blue-yellow axis) in CIELAB space.
     - **Important Logic:** 
       1. Normalizes XYZ values to the range [0, 1] using reference values for D65 illuminant.
       2. Applies the CIE L*a*b* transformation equations to calculate L*, a*, and b* values.

   - **`pivotRgb(double n)`:**
     - **Description:**  Applies the gamma correction function used in the conversion from RGB to XYZ.
     - **Parameters:**
       - `n`: Double representing the normalized RGB value (0-1).
     - **Return Values:** A double representing the corrected value after applying the gamma function.
     - **Important Logic:** 
       1. Uses a piecewise function based on the input value to apply different correction formulas.

   - **`pivotXyz(double n)`:**
     - **Description:** Applies the inverse of the gamma correction function used in the conversion from XYZ to RGB.
     - **Parameters:**
       - `n`: Double representing the normalized XYZ value (0-1).
     - **Return Values:** A double representing the corrected value after applying the inverse gamma function.
     - **Important Logic:** 
       1. Uses a piecewise function based on the input value to apply different correction formulas.

   - **`calculateCIEDE2000(double[] lab1, double[] lab2)`:**
     - **Description:** Calculates the CIEDE2000 color difference between two colors represented in CIELAB space.
     - **Parameters:**
       - `lab1`: A double array containing the L*, a*, and b* values of the first color.
       - `lab2`: A double array containing the L*, a*, and b* values of the second color.
     - **Return Values:** A double representing the CIEDE2000 color difference between the two colors.
     - **Important Logic:** 
       1. Calculates various intermediate values like average lightness, chroma, hue angles, etc.
       2. Applies the CIEDE2000 formula to compute the final color difference based on the calculated values.

**6. Pseudo Code:**


```
// Class: ColorUtils

// Method: rgbToLab(r: int, g: int, b: int)
  1. Convert RGB to XYZ using rgbToXyz(r, g, b)
  2. Convert XYZ to CIELAB using xyzToLab(x, y, z)
  3. Return the resulting CIELAB values as a double array

// Method: rgbToXyz(r: int, g: int, b: int)
  1. Normalize RGB values (0-255) to range (0-1)
  2. Calculate x, y, and z using CIE XYZ color matching functions based on normalized RGB values
  3. Return the calculated XYZ values as a double array

// Method: xyzToLab(x: double, y: double, z: double)
  1. Normalize XYZ values (0-1) using reference values for D65 illuminant
  2. Apply CIE L*a*b* transformation equations to calculate L*, a*, and b* values
  3. Return the calculated CIELAB values as a double array

// Method: pivotRgb(n: double)
  1. If n > 0.04045, apply formula (n + 0.055) / 1.055)^2.4
  2. Otherwise, apply formula n / 12.92
  3. Return the calculated value

// Method: pivotXyz(n: double)
  1. If n > 0.008856, apply formula Math.cbrt(n)
  2. Otherwise, apply formula 7.787 * n + 16.0 / 116.0
  3. Return the calculated value

// Method: calculateCIEDE2000(lab1: double[], lab2: double[])
  1. Extract L*, a*, and b* values from both input arrays
  2. Calculate average lightness, chroma, hue angles, etc.
  3. Apply CIEDE2000 formula to compute the final color difference based on calculated values
  4. Return the calculated CIEDE2000 color difference as a double



```

**7. Dependencies and Libraries:**


- The code does not explicitly rely on any external libraries. 
- It uses standard mathematical functions available in most programming languages.




