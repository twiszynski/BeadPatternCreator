![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Make it real.

---
## ColorUtils.java Documentation and Pseudocode

**1. Overview:**

The `ColorUtils` class provides utility functions for converting between RGB color space and LAB color space. It also implements the CIEDE2000 formula to calculate the color difference (ΔE) between two colors in LAB space. This is useful for tasks like comparing colors, detecting subtle differences, and ensuring consistent color representation across different devices.

**2. Package/module name:**

org.example

**3. Class/file name:**

ColorUtils.java

**4. Detailed Documentation:**

   - **`rgbToLab(int r, int g, int b)`:**
     - **Description:** Converts an RGB color (represented as integers for red, green, and blue values) to its equivalent LAB representation. 
     - **Parameters:**
       - `r`: Integer representing the red component of the RGB color (0-255).
       - `g`: Integer representing the green component of the RGB color (0-255).
       - `b`: Integer representing the blue component of the RGB color (0-255).
     - **Return Values:** A double array containing three values: L (lightness), a (green-red axis), and b (blue-yellow axis) in LAB space.
     - **Important Logic:** This function first converts the RGB values to XYZ using the `rgbToXyz` method. Then, it converts the XYZ values to LAB using the `xyzToLab` method.

   - **`rgbToXyz(int r, int g, int b)`:**
     - **Description:** Converts an RGB color to its equivalent XYZ representation. 
     - **Parameters:** Same as `rgbToLab`.
     - **Return Values:** A double array containing three values: X, Y, and Z in XYZ space.
     - **Important Logic:** This function applies the standard CIE XYZ color matching functions to the normalized RGB values (0-1).

   - **`xyzToLab(double x, double y, double z)`:**
     - **Description:** Converts an XYZ color to its equivalent LAB representation. 
     - **Parameters:**
       - `x`: Double representing the X component of the XYZ color.
       - `y`: Double representing the Y component of the XYZ color.
       - `z`: Double representing the Z component of the XYZ color.
     - **Return Values:** A double array containing three values: L (lightness), a (green-red axis), and b (blue-yellow axis) in LAB space.
     - **Important Logic:** This function uses the standard CIE 1931 color space transformations to convert from XYZ to LAB, taking into account reference white points.

   - **`pivotRgb(double n)`:**
     - **Description:** Applies a gamma correction function to an RGB value (0-1).
     - **Parameters:**
       - `n`: Double representing the normalized RGB value (0-1).
     - **Return Values:** A double representing the corrected RGB value.
     - **Important Logic:** This function implements the standard gamma correction formula used in display devices to ensure accurate color representation.

   - **`pivotXyz(double n)`:**
     - **Description:** Applies a specific transformation function to an XYZ value (0-1).
     - **Parameters:**
       - `n`: Double representing the normalized XYZ value (0-1).
     - **Return Values:** A double representing the transformed XYZ value.
     - **Important Logic:** This function implements a specific mathematical transformation used in the CIE 1976 L*a*b* color space definition.

   - **`calculateCIEDE2000(double[] lab1, double[] lab2)`:**
     - **Description:** Calculates the CIEDE2000 color difference (ΔE) between two colors represented in LAB space. 
     - **Parameters:**
       - `lab1`: A double array representing the first color in LAB space (L, a, b).
       - `lab2`: A double array representing the second color in LAB space (L, a, b).
     - **Return Values:** A double value representing the ΔE between the two colors.
     - **Important Logic:** This function implements the CIEDE2000 formula, which considers various factors like lightness, chroma, and hue to provide a more perceptually uniform color difference metric.

**5. Pseudo Code:**


```
// Class: ColorUtils

// Method: rgbToLab(r, g, b)
  1. Convert RGB (r, g, b) to XYZ using rgbToXyz(r, g, b).
     - Store the result in xyzArray.
  2. Convert XYZ (xyzArray[0], xyzArray[1], xyzArray[2]) to LAB using xyzToLab(xyzArray[0], xyzArray[1], xyzArray[2]).
     - Store the result in labArray.
  3. Return labArray.

// Method: rgbToXyz(r, g, b)
  1. Normalize RGB values (r, g, b) to range 0-1.
  2. Apply pivotRgb function to each normalized value.
     - Store the results in rr, gg, bb.
  3. Calculate X, Y, and Z using the CIE XYZ color matching functions with rr, gg, and bb.
  4. Return an array containing X, Y, and Z.

// Method: xyzToLab(x, y, z)
  1. Normalize XYZ values (x, y, z) to range 0-1.
  2. Apply pivotXyz function to each normalized value.
     - Store the results in xp, yp, zp.
  3. Calculate L using the formula: 116 * yp - 16.
  4. Calculate a using the formula: 500 * (xp - yp).
  5. Calculate b using the formula: 200 * (yp - zp).
  6. Return an array containing L, a, and b.

// Method: pivotRgb(n)
  1. If n > 0.04045, apply the gamma correction formula: ((n + 0.055) / 1.055)^2.4.
  2. Otherwise, apply the linear transformation formula: n / 12.92.

// Method: pivotXyz(n)
  1. If n > 0.008856, apply the cube root function: Math.cbrt(n).
  2. Otherwise, apply the linear transformation formula: 7.787 * n + 16.0 / 116.0.

// Method: calculateCIEDE2000(lab1, lab2)
  1. Extract L, a, and b values from lab1 and lab2 arrays.
  2. Calculate average lightness (avgLp), chroma (avgC), and hue angle (avgHp).
  3. Apply various formulas to calculate deltaL, deltaC, deltaH, and other terms based on CIEDE2000 formula.
  4. Calculate the final ΔE value using the combined terms.



```

**6. Dependencies and Libraries:**


- The `ColorUtils` class does not explicitly rely on any external libraries. 
- It utilizes standard mathematical functions and algorithms for color space conversions and calculations.

- **Equivalent Libraries in Other Languages:**
    - Python: `colormath`, `Pillow`
    - Java: `javax.swing.Color`, `java.awt.Color`
    - C++: `boost::numeric::ublas`, `Eigen`



