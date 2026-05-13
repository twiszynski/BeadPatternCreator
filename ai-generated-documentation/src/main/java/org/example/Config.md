![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Make it real.

---
## Config.java Documentation and Pseudocode

**1. Overview:**

The `Config` class in this Java project serves as a central repository for configuration settings and constants used throughout the application. It defines various properties related to image paths, file formats, bead visualization parameters, XLSX pattern file details, print configurations, and font styles. These values are accessed globally within the project, ensuring consistency and maintainability.

**2. Package/module name:**

`org.example`

**3. Class/file name:**

`Config.java`

**4. Detailed Documentation:**

* **getImgName():**
    - **Description:** Returns the base image name used for bead patterns.
    - **Parameters:** None
    - **Return Values:** String - The image name (e.g., "humm").
* **getDesignNoAndName():**
    - **Description:** Returns a string combining the design number and title.
    - **Parameters:** None
    - **Return Values:** String - Design number followed by " - " and design title (e.g., "BV004 - Hummingbird").
* **getTitlePdfName():**
    - **Description:** Generates the PDF filename for the pattern title page.
    - **Parameters:** None
    - **Return Values:** String - PDF filename including design number, title without spaces, and "_Title" suffix (e.g., "BV004_Hummingbird_Title.pdf").
* **getPatternPdfName():**
    - **Description:** Generates the PDF filename for the pattern sheet.
    - **Parameters:** None
    - **Return Values:** String - PDF filename including design number, title without spaces, and "_Pattern" suffix (e.g., "BV004_Hummingbird_Pattern.pdf").
* **getPublishPdfName():**
    - **Description:** Generates the PDF filename for the published pattern document.
    - **Parameters:** None
    - **Return Values:** String - PDF filename including design number and title without spaces (e.g., "BV004 Hummingbird.pdf").
* **getColorMappingMode():**
    - **Description:** Returns the color mapping mode used for bead visualization.
    - **Parameters:** None
    - **Return Values:** `ColorMappingMode` - Enum value representing the color mapping mode (e.g., RGB).
* **getImgFormatSuffix():**
    - **Description:** Returns the image file format suffix (e.g., ".png").
    - **Parameters:** None
    - **Return Values:** String - Image file format suffix.
* **getLibraryBaseDirectoryPath():**
    - **Description:** Returns the base directory path for beadventure library files.
    - **Parameters:** None
    - **Return Values:** String - Absolute path to the library directory (e.g., "C:\\Users\\Admin\\Desktop\\Beadventure\\").
* **getMergeDirPath():**
    - **Description:** Returns the directory path for merging bead pattern images.
    - **Parameters:** None
    - **Return Values:** String - Path to the merge directory within the library base directory (e.g., "C:\\Users\\Admin\\Desktop\\Beadventure\\ReadyToMerge\\").
* **getPublishDirPath():**
    - **Description:** Returns the directory path for publishing final bead pattern files.
    - **Parameters:** None
    - **Return Values:** String - Path to the publish directory within the library base directory (e.g., "C:\\Users\\Admin\\Desktop\\Beadventure\\ReadyToPublish\\").
* **getPublishPdfFilePath():**
    - **Description:** Returns the full file path for the published PDF pattern document.
    - **Parameters:** None
    - **Return Values:** String - Full path to the published PDF file within the publish directory.
* **getXlsxPatternFileName():**
    - **Description:** Generates the filename for the XLSX pattern file.
    - **Parameters:** None
    - **Return Values:** String - Filename including design number, title without spaces, and "_Pattern" suffix (e.g., "BV004_Hummingbird_Pattern").
* **getBaseColorsImgPath():**
    - **Description:** Returns the path to the base colors image file.
    - **Parameters:** None
    - **Return Values:** String - Path to the base colors image within the library directory (e.g., "C:\\Users\\Admin\\Desktop\\Beadventure\\humm.png").
* **getMiyukiColorsImgPath():**
    - **Description:** Returns the path to the Miyuki color mapped image file.
    - **Parameters:** None
    - **Return Values:** String - Path to the Miyuki color mapped image within the library directory (e.g., "C:\\Users\\Admin\\Desktop\\Beadventure\\humm_M.png").
* **getAdjustedImgPath():**
    - **Description:** Returns the path to the adjusted bead pattern image file.
    - **Parameters:** None
    - **Return Values:** String - Path to the adjusted bead pattern image within the library directory (e.g., "C:\\Users\\Admin\\Desktop\\Beadventure\\humm_M_adj.png").
* **getAbsoluteCsvPalettePath():**
    - **Description:** Returns the absolute path to the CSV palette file.
    - **Parameters:** None
    - **Return Values:** String - Absolute path to the CSV palette file (e.g., "src/main/resources/Palettes/MiyukiFullCSV.csv").
* **getXlsxPatternFilePath():**
    - **Description:** Returns the full file path for the XLSX pattern file.
    - **Parameters:** None
    - **Return Values:** String - Full path to the XLSX pattern file within the library directory (e.g., "C:\\Users\\Admin\\Desktop\\Beadventure\\BV004_Hummingbird_Pattern.xlsx").
* **getBeadTemplateDirPathWithSeparator():**
    - **Description:** Returns the path to the bead template directory with a separator at the end.
    - **Parameters:** None
    - **Return Values:** String - Path to the bead template directory (e.g., "src\\main\\resources\\MiyukiPNG\\").
* **getFinishTypesDirPathWithSeparator():**
    - **Description:** Returns the path to the finish types directory with a separator at the end.
    - **Parameters:** None
    - **Return Values:** String - Path to the finish types directory (e.g., "src\\main\\resources\\FinishTypesPNG\\").
* **getDefaultTemplateImgName():**
    - **Description:** Returns the name of the default template image.
    - **Parameters:** None
    - **Return Values:** String - Name of the default template image (e.g., "NoMatch.png").
* **getDefaultTemplateImgNameAndFormat():**
    - **Description:** Returns the name of the default template image with its file format.
    - **Parameters:** None
    - **Return Values:** String - Name of the default template image with its file format (e.g., "NoMatch.png").
* **getVisualisationImgOutputPath():**
    - **Description:** Returns the path to the output directory for visualization images.
    - **Parameters:** None
    - **Return Values:** String - Path to the output directory for visualization images (e.g., "C:\\Users\\Admin\\Desktop\\Beadventure\\BV004_Hummingbird_visual.png").
* **getBeadImgPxWidth():**
    - **Description:** Returns the pixel width of each bead image.
    - **Parameters:** None
    - **Return Values:** Integer - Pixel width of each bead image (e.g., 53).
* **getBeadImgPxHeight():**
    - **Description:** Returns the pixel height of each bead image.
    - **Parameters:** None
    - **Return Values:** Integer - Pixel height of each bead image (e.g., 66).
* **getHorizontalPxSpacing():**
    - **Description:** Returns the horizontal spacing between beads in pixels.
    - **Parameters:** None
    - **Return Values:** Integer - Horizontal spacing between beads in pixels (e.g., 1).
* **getVerticalPxSpacing():**
    - **Description:** Returns the vertical spacing between beads in pixels.
    - **Parameters:** None
    - **Return Values:** Integer - Vertical spacing between beads in pixels (e.g., 2).
* **getCornerArcPxWidth():**
    - **Description:** Returns the width of the corner arc in pixels.
    - **Parameters:** None
    - **Return Values:** Integer - Width of the corner arc in pixels (e.g., 25).
* **getCornerArcPxHeight():**
    - **Description:** Returns the height of the corner arc in pixels.
    - **Parameters:** None
    - **Return Values:** Integer - Height of the corner arc in pixels (e.g., 25).
* **getPatternSheetName():**
    - **Description:** Returns the name of the sheet containing the bead pattern in the XLSX file.
    - **Parameters:** None
    - **Return Values:** String - Name of the pattern sheet (e.g., "Bead Pattern").
* **getLegendSheetName():**
    - **Description:** Returns the name of the sheet containing the bead legend in the XLSX file.
    - **Parameters:** None
    - **Return Values:** String - Name of the legend sheet (e.g., "Bead Legend").
* **getWordChartSheetName():**
    - **Description:** Returns the name of the sheet containing the word chart in the XLSX file.
    - **Parameters:** None
    - **Return Values:** String - Name of the word chart sheet (e.g., "Word Chart").
* **getPageMapSheetName():**
    - **Description:** Returns the name of the sheet containing the page map layout in the XLSX file.
    - **Parameters:** None
    - **Return Values:** String - Name of the page map sheet (e.g., "Pattern Page Layout").
* **getSizeSheetName():**
    - **Description:** Returns the name of the sheet containing size information in the XLSX file.
    - **Parameters:** None
    - **Return Values:** String - Name of the size sheet (e.g., "Size").
* **getCopywriteNote():**
    - **Description:** Returns the copyright note string for the pattern.
    - **Parameters:** None
    - **Return Values:** String - Copyright note string.
* **getColsPerPage():**
    - **Description:** Returns the number of columns per page in the pattern layout.
    - **Parameters:** None
    - **Return Values:** Integer - Number of columns per page (e.g., 30).
* **getRowsPerPage():**
    - **Description:** Returns the number of rows per page in the pattern layout.
    - **Parameters:** None
    - **Return Values:** Integer - Number of rows per page (e.g., 36).
* **getHeaderFont():**
    - **Description:** Returns the font name used for headers.
    - **Parameters:** None
    - **Return Values:** String - Font name (e.g., "Montserrat SemiBold").
* **getHeaderFontSize():**
    - **Description:** Returns the font size used for headers.
    - **Parameters:** None
    - **Return Values:** Integer - Font size in points (e.g., 10).
* **getBaseFont():**
    - **Description:** Returns the font name used for base text.
    - **Parameters:** None
    - **Return Values:** String - Font name (e.g., "Montserrat").
* **getBaseFontSize():**
    - **Description:** Returns the font size used for base text.
    - **Parameters:** None
    - **Return Values:** Integer - Font size in points (e.g., 10).
* **getColsRowsNumberingFont():**
    - **Description:** Returns the font name used for column and row numbering.
    - **Parameters:** None
    - **Return Values:** String - Font name (e.g., "Montserrat").
* **getColsRowsNumberingFontSize():**
    - **Description:** Returns the font size used for column and row numbering.
    - **Parameters:** None
    - **Return Values:** Integer - Font size in points (e.g., 9).



**5. Pseudo Code:**

```
// Class: Config

// No specific methods require execution steps as they are primarily data accessors.


```




