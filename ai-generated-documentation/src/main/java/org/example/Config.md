![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Get The Future You Want!

---
## Config.java Documentation and Pseudocode

**1. Overview:**

This Java class `Config` stores various configuration settings and paths used by a bead pattern generation application. It defines constants for image file names, directory paths, sheet names in Excel files, font properties, and other relevant parameters. 

**2. Package/module name:**

org.example

**3. Class/file name:**

Config.java

**4. Detailed Documentation:**

* **`getImgName()`**:
    - **Description:** Returns the base image file name used for bead patterns.
    - **Parameters:** None
    - **Return Values:** String - The image file name (e.g., "humm").
* **`getDesignNoAndName()`**:
    - **Description:** Returns a string containing the design number and title.
    - **Parameters:** None
    - **Return Values:** String - A combined string of design number and title (e.g., "BV004 - Hummingbird").
* **`getTitlePdfName()`**:
    - **Description:** Generates the PDF file name for the pattern title page.
    - **Parameters:** None
    - **Return Values:** String - The PDF file name including design number and title (e.g., "BV004_Hummingbird_Title.pdf").
* **`getPatternPdfName()`**:
    - **Description:** Generates the PDF file name for the pattern sheet.
    - **Parameters:** None
    - **Return Values:** String - The PDF file name including design number and title (e.g., "BV004_Hummingbird_Pattern.pdf").
* **`getPublishPdfName()`**:
    - **Description:** Generates the PDF file name for the published pattern document.
    - **Parameters:** None
    - **Return Values:** String - The PDF file name including design number and title (e.g., "BV004 Hummingbird.pdf").
* **`getColorMappingMode()`**:
    - **Description:** Returns the color mapping mode used for bead visualization.
    - **Parameters:** None
    - **Return Values:** ColorMappingMode - The current color mapping mode (e.g., RGB).
* **`getImgFormatSuffix()`**:
    - **Description:** Returns the image file format suffix (e.g., ".png").
    - **Parameters:** None
    - **Return Values:** String - The image file format suffix.
* **`getLibraryBaseDirectoryPath()`**:
    - **Description:** Returns the base directory path for beadventure library files.
    - **Parameters:** None
    - **Return Values:** String - The absolute path to the library directory.
* **`getMergeDirPath()`**:
    - **Description:** Returns the directory path for merging files.
    - **Parameters:** None
    - **Return Values:** String - The absolute path to the merge directory.
* **`getPublishDirPath()`**:
    - **Description:** Returns the directory path for publishing files.
    - **Parameters:** None
    - **Return Values:** String - The absolute path to the publish directory.
* **`getPublishPdfFilePath()`**:
    - **Description:** Constructs and returns the full file path for the published PDF document.
    - **Parameters:** None
    - **Return Values:** String - The complete file path for the published PDF.
* **`getXlsxPatternFileName()`**:
    - **Description:** Generates the Excel pattern file name based on design number and title.
    - **Parameters:** None
    - **Return Values:** String - The Excel pattern file name (e.g., "BV004_Hummingbird_Pattern.xlsx").
* **`getBaseColorsImgPath()`**:
    - **Description:** Returns the path to the base colors image file.
    - **Parameters:** None
    - **Return Values:** String - The absolute path to the base colors image file.
* **`getMiyukiColorsImgPath()`**:
    - **Description:** Returns the path to the Miyuki color mapped image file.
    - **Parameters:** None
    - **Return Values:** String - The absolute path to the Miyuki color mapped image file.
* **`getAdjustedImgPath()`**:
    - **Description:** Returns the path to the adjusted image file.
    - **Parameters:** None
    - **Return Values:** String - The absolute path to the adjusted image file.
* **`getAbsoluteCsvPalettePath()`**:
    - **Description:** Returns the absolute path to the CSV palette file.
    - **Parameters:** None
    - **Return Values:** String - The absolute path to the CSV palette file.
* **`getXlsxPatternFilePath()`**:
    - **Description:** Constructs and returns the full file path for the Excel pattern file.
    - **Parameters:** None
    - **Return Values:** String - The complete file path for the Excel pattern file.
* **`getBeadTemplateDirPathWithSeparator()`**:
    - **Description:** Returns the directory path for bead templates with a separator at the end.
    - **Parameters:** None
    - **Return Values:** String - The absolute path to the bead template directory with a separator.
* **`getFinishTypesDirPathWithSeparator()`**:
    - **Description:** Returns the directory path for finish types images with a separator at the end.
    - **Parameters:** None
    - **Return Values:** String - The absolute path to the finish types directory with a separator.
* **`getDefaultTemplateImgName()`**:
    - **Description:** Returns the name of the default template image file.
    - **Parameters:** None
    - **Return Values:** String - The name of the default template image file (e.g., "NoMatch.png").
* **`getDefaultTemplateImgNameAndFormat()`**:
    - **Description:** Returns the name and format of the default template image file.
    - **Parameters:** None
    - **Return Values:** String - The complete name and format of the default template image file (e.g., "NoMatch.png").
* **`getVisualisationImgOutputPath()`**:
    - **Description:** Constructs and returns the output path for the visualization image file.
    - **Parameters:** None
    - **Return Values:** String - The full path to save the visualization image file.
* **`getBeadImgPxWidth()`**:
    - **Description:** Returns the pixel width of each bead image.
    - **Parameters:** None
    - **Return Values:** int - The pixel width of each bead image.
* **`getBeadImgPxHeight()`**:
    - **Description:** Returns the pixel height of each bead image.
    - **Parameters:** None
    - **Return Values:** int - The pixel height of each bead image.
* **`getHorizontalPxSpacing()`**:
    - **Description:** Returns the horizontal spacing between beads in pixels.
    - **Parameters:** None
    - **Return Values:** int - The horizontal spacing between beads in pixels.
* **`getVerticalPxSpacing()`**:
    - **Description:** Returns the vertical spacing between beads in pixels.
    - **Parameters:** None
    - **Return Values:** int - The vertical spacing between beads in pixels.
* **`getCornerArcPxWidth()`**:
    - **Description:** Returns the width of the corner arc in pixels.
    - **Parameters:** None
    - **Return Values:** int - The width of the corner arc in pixels.
* **`getCornerArcPxHeight()`**:
    - **Description:** Returns the height of the corner arc in pixels.
    - **Parameters:** None
    - **Return Values:** int - The height of the corner arc in pixels.
* **`getPatternSheetName()`**:
    - **Description:** Returns the name of the sheet containing the bead pattern.
    - **Parameters:** None
    - **Return Values:** String - The name of the pattern sheet (e.g., "Bead Pattern").
* **`getLegendSheetName()`**:
    - **Description:** Returns the name of the sheet containing the bead legend.
    - **Parameters:** None
    - **Return Values:** String - The name of the legend sheet (e.g., "Bead Legend").
* **`getWordChartSheetName()`**:
    - **Description:** Returns the name of the sheet containing the word chart.
    - **Parameters:** None
    - **Return Values:** String - The name of the word chart sheet (e.g., "Word Chart").
* **`getPageMapSheetName()`**:
    - **Description:** Returns the name of the sheet containing the pattern page layout.
    - **Parameters:** None
    - **Return Values:** String - The name of the page map sheet (e.g., "Pattern Page Layout").
* **`getSizeSheetName()`**:
    - **Description:** Returns the name of the sheet containing bead size information.
    - **Parameters:** None
    - **Return Values:** String - The name of the size sheet (e.g., "Size").
* **`getCopywriteNote()`**:
    - **Description:** Returns the copyright note for the pattern.
    - **Parameters:** None
    - **Return Values:** String - The copyright note string.
* **`getColsPerPage()`**:
    - **Description:** Returns the number of columns per page in the pattern sheet.
    - **Parameters:** None
    - **Return Values:** int - The number of columns per page.
* **`getRowsPerPage()`**:
    - **Description:** Returns the number of rows per page in the pattern sheet.
    - **Parameters:** None
    - **Return Values:** int - The number of rows per page.
* **`getHeaderFont()`**:
    - **Description:** Returns the font name used for headers.
    - **Parameters:** None
    - **Return Values:** String - The header font name (e.g., "Montserrat SemiBold").
* **`getHeaderFontSize()`**:
    - **Description:** Returns the font size used for headers in points.
    - **Parameters:** None
    - **Return Values:** int - The header font size.
* **`getBaseFont()`**:
    - **Description:** Returns the font name used for base text.
    - **Parameters:** None
    - **Return Values:** String - The base font name (e.g., "Montserrat").
* **`getBaseFontSize()`**:
    - **Description:** Returns the font size used for base text in points.
    - **Parameters:** None
    - **Return Values:** int - The base font size.
* **`getColsRowsNumberingFont()`**:
    - **Description:** Returns the font name used for column and row numbering.
    - **Parameters:** None
    - **Return Values:** String - The font name for column and row numbering (e.g., "Montserrat").
* **`getColsRowsNumberingFontSize()`**:
    - **Description:** Returns the font size used for column and row numbering in points.
    - **Parameters:** None
    - **Return Values:** int - The font size for column and row numbering.



**5. Pseudo Code:**

```
// Class: Config

// No specific methods require execution, as this class is purely for configuration storage.


// Assumptions and Dependencies:

// -  The application assumes the existence of a directory structure with paths defined in the Config class. 
// -  Libraries like Apache POI are likely used to interact with Excel files based on sheet names defined in the Config class.
// -  Image processing libraries are assumed for handling image file operations using paths defined in the Config class.

```



