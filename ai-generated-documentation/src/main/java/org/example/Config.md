![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Make it real.

---
## Config Class Documentation

**1. Overview:**

The `Config` class serves as a central repository for configuration settings and constants used throughout the application. It defines various properties related to image paths, file names, dimensions, color mappings, fonts, and other essential parameters required for generating bead patterns and visualizations.

**2. Package/module name:**

org.example

**3. Class/file name:**

Config.java

**4. Class diagram name:**

Config_diagram.svg

**5. Detailed Documentation:**


* **`getImgName()`**:
    - **Description**: Returns the base image name used for bead patterns.
    - **Parameters**: None
    - **Return Values**: String - The image name (e.g., "humm").
* **`getDesignNoAndName()`**:
    - **Description**: Returns a string containing the design number and title concatenated together.
    - **Parameters**: None
    - **Return Values**: String - Design number followed by a hyphen and the design title (e.g., "BV004 - Hummingbird").
* **`getTitlePdfName()`**:
    - **Description**: Generates the filename for the PDF document containing the bead pattern title.
    - **Parameters**: None
    - **Return Values**: String - The PDF filename (e.g., "BV004_Hummingbird_Title.pdf").
* **`getPatternPdfName()`**:
    - **Description**: Generates the filename for the PDF document containing the bead pattern itself.
    - **Parameters**: None
    - **Return Values**: String - The PDF filename (e.g., "BV004_Hummingbird_Pattern.pdf").
* **`getPublishPdfName()`**:
    - **Description**: Generates the filename for the final published PDF document containing all information.
    - **Parameters**: None
    - **Return Values**: String - The PDF filename (e.g., "BV004 Hummingbird.pdf").
* **`getColorMappingMode()`**:
    - **Description**: Returns the color mapping mode used for bead patterns.
    - **Parameters**: None
    - **Return Values**: ColorMappingMode - An enum representing the color mapping mode (e.g., RGB).
* **`getImgFormatSuffix()`**:
    - **Description**: Returns the image file format suffix (e.g., ".png").
    - **Parameters**: None
    - **Return Values**: String - The image format suffix.
* **`getLibraryBaseDirectoryPath()`**:
    - **Description**: Returns the base directory path for bead pattern libraries.
    - **Parameters**: None
    - **Return Values**: String - The library base directory path (e.g., "C:\\Users\\Admin\\Desktop\\Beadventure\\").
* **`getMergeDirPath()`**:
    - **Description**: Returns the directory path for merging bead pattern files.
    - **Parameters**: None
    - **Return Values**: String - The merge directory path (e.g., "C:\\Users\\Admin\\Desktop\\Beadventure\\ReadyToMerge\\").
* **`getPublishDirPath()`**:
    - **Description**: Returns the directory path for publishing final bead pattern files.
    - **Parameters**: None
    - **Return Values**: String - The publish directory path (e.g., "C:\\Users\\Admin\\Desktop\\Beadventure\\ReadyToPublish\\").
* **`getPublishPdfFilePath()`**:
    - **Description**: Returns the full file path for the published PDF document.
    - **Parameters**: None
    - **Return Values**: String - The full publish PDF file path (e.g., "C:\\Users\\Admin\\Desktop\\Beadventure\\ReadyToPublish\\BV004 Hummingbird.pdf").
* **`getXlsxPatternFileName()`**:
    - **Description**: Generates the filename for the XLSX pattern file.
    - **Parameters**: None
    - **Return Values**: String - The XLSX pattern filename (e.g., "BV004_Hummingbird_Pattern").
* **`getBaseColorsImgPath()`**:
    - **Description**: Returns the path to the base colors image file.
    - **Parameters**: None
    - **Return Values**: String - The base colors image path (e.g., "C:\\Users\\Admin\\Desktop\\Beadventure\\humm.png").
* **`getMiyukiColorsImgPath()`**:
    - **Description**: Returns the path to the Miyuki color mapped image file.
    - **Parameters**: None
    - **Return Values**: String - The Miyuki colors image path (e.g., "C:\\Users\\Admin\\Desktop\\Beadventure\\humm_M.png").
* **`getAdjustedImgPath()`**:
    - **Description**: Returns the path to the adjusted bead pattern image file.
    - **Parameters**: None
    - **Return Values**: String - The adjusted image path (e.g., "C:\\Users\\Admin\\Desktop\\Beadventure\\humm_M_adj.png").
* **`getAbsoluteCsvPalettePath()`**:
    - **Description**: Returns the absolute path to the CSV palette file.
    - **Parameters**: None
    - **Return Values**: String - The absolute path to the CSV palette file (e.g., "src/main/resources/Palettes/MiyukiFullCSV.csv").
* **`getXlsxPatternFilePath()`**:
    - **Description**: Returns the full file path for the XLSX pattern file.
    - **Parameters**: None
    - **Return Values**: String - The XLSX pattern file path (e.g., "C:\\Users\\Admin\\Desktop\\Beadventure\\BV004_Hummingbird_Pattern.xlsx").
* **`getBeadTemplateDirPathWithSeparator()`**:
    - **Description**: Returns the directory path for bead templates with a separator at the end.
    - **Parameters**: None
    - **Return Values**: String - The template directory path with separator (e.g., "src\\main\\resources\\MiyukiPNG\\").
* **`getFinishTypesDirPathWithSeparator()`**:
    - **Description**: Returns the directory path for finish types with a separator at the end.
    - **Parameters**: None
    - **Return Values**: String - The finish types directory path with separator (e.g., "src\\main\\resources\\FinishTypesPNG\\").
* **`getDefaultTemplateImgName()`**:
    - **Description**: Returns the name of the default template image file.
    - **Parameters**: None
    - **Return Values**: String - The default template image name (e.g., "NoMatch.png").
* **`getDefaultTemplateImgNameAndFormat()`**:
    - **Description**: Returns the default template image name with its format suffix.
    - **Parameters**: None
    - **Return Values**: String - The default template image name and format (e.g., "NoMatch.png").
* **`getVisualisationImgOutputPath()`**:
    - **Description**: Returns the output path for the visualization image file.
    - **Parameters**: None
    - **Return Values**: String - The visualization image file path (e.g., "C:\\Users\\Admin\\Desktop\\Beadventure\\BV004_Hummingbird_visual.png").
* **`getBeadImgPxWidth()`**:
    - **Description**: Returns the pixel width of each bead image.
    - **Parameters**: None
    - **Return Values**: int - The bead image width (e.g., 53).
* **`getBeadImgPxHeight()`**:
    - **Description**: Returns the pixel height of each bead image.
    - **Parameters**: None
    - **Return Values**: int - The bead image height (e.g., 66).
* **`getHorizontalPxSpacing()`**:
    - **Description**: Returns the horizontal spacing between beads in pixels.
    - **Parameters**: None
    - **Return Values**: int - The horizontal spacing (e.g., 1).
* **`getVerticalPxSpacing()`**:
    - **Description**: Returns the vertical spacing between beads in pixels.
    - **Parameters**: None
    - **Return Values**: int - The vertical spacing (e.g., 2).
* **`getCornerArcPxWidth()`**:
    - **Description**: Returns the width of the corner arc in pixels.
    - **Parameters**: None
    - **Return Values**: int - The corner arc width (e.g., 25).
* **`getCornerArcPxHeight()`**:
    - **Description**: Returns the height of the corner arc in pixels.
    - **Parameters**: None
    - **Return Values**: int - The corner arc height (e.g., 25).
* **`getPatternSheetName()`**:
    - **Description**: Returns the name of the sheet containing the bead pattern in the XLSX file.
    - **Parameters**: None
    - **Return Values**: String - The pattern sheet name (e.g., "Bead Pattern").
* **`getLegendSheetName()`**:
    - **Description**: Returns the name of the sheet containing the bead legend in the XLSX file.
    - **Parameters**: None
    - **Return Values**: String - The legend sheet name (e.g., "Bead Legend").
* **`getWordChartSheetName()`**:
    - **Description**: Returns the name of the sheet containing the word chart in the XLSX file.
    - **Parameters**: None
    - **Return Values**: String - The word chart sheet name (e.g., "Word Chart").
* **`getPageMapSheetName()`**:
    - **Description**: Returns the name of the sheet containing the page map in the XLSX file.
    - **Parameters**: None
    - **Return Values**: String - The page map sheet name (e.g., "Pattern Page Layout").
* **`getSizeSheetName()`**:
    - **Description**: Returns the name of the sheet containing bead sizes in the XLSX file.
    - **Parameters**: None
    - **Return Values**: String - The size sheet name (e.g., "Size").
* **`getCopywriteNote()`**:
    - **Description**: Returns the copyright note string for the bead pattern.
    - **Parameters**: None
    - **Return Values**: String - The copyright note (e.g., "©2025 Beadventure. This pattern is for personal use only.\nYou may not copy, share, modify, or resell this file in any form without written permission.").
* **`getColsPerPage()`**:
    - **Description**: Returns the number of columns per page in the bead pattern layout.
    - **Parameters**: None
    - **Return Values**: int - The number of columns (e.g., 30).
* **`getRowsPerPage()`**:
    - **Description**: Returns the number of rows per page in the bead pattern layout.
    - **Parameters**: None
    - **Return Values**: int - The number of rows (e.g., 36).
* **`getHeaderFont()`**:
    - **Description**: Returns the font name used for headers in the bead pattern document.
    - **Parameters**: None
    - **Return Values**: String - The header font name (e.g., "Montserrat SemiBold").
* **`getHeaderFontSize()`**:
    - **Description**: Returns the font size used for headers in the bead pattern document.
    - **Parameters**: None
    - **Return Values**: int - The header font size (e.g., 10).
* **`getBaseFont()`**:
    - **Description**: Returns the font name used for the main body text in the bead pattern document.
    - **Parameters**: None
    - **Return Values**: String - The base font name (e.g., "Montserrat").
* **`getBaseFontSize()`**:
    - **Description**: Returns the font size used for the main body text in the bead pattern document.
    - **Parameters**: None
    - **Return Values**: int - The base font size (e.g., 10).
* **`getColsRowsNumberingFont()`**:
    - **Description**: Returns the font name used for column and row numbering in the bead pattern document.
    - **Parameters**: None
    - **Return Values**: String - The column/row numbering font name (e.g., "Montserrat").
* **`getColsRowsNumberingFontSize()`**:
    - **Description**: Returns the font size used for column and row numbering in the bead pattern document.
    - **Parameters**: None
    - **Return Values**: int - The column/row numbering font size (e.g., 9).



**6. Pseudo Code:**

The `Config` class does not contain any methods that directly execute code logic. It primarily serves as a repository for configuration settings and constants. Therefore, there is no specific pseudocode to describe its execution flow.


