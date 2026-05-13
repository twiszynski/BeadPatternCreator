![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Make it real.

---
## PdfLinker Code Documentation and Pseudocode

**1. Overview:**

The `PdfLinker` class is a Java program designed to automatically link pages within a PDF document based on a predefined mapping. It reads a specific page ("map of pages") in the PDF, extracts logical page numbers from text annotations, and then creates hyperlinks connecting these annotations to their corresponding physical pages within the document.

**2. Package/module name:** `org.example`

**3. Class/file name:** `PdfLinker.java`

**4. Detailed Documentation:**

* **Class: `PdfLinker`**
    -  **Purpose:** This class is responsible for linking pages in a PDF document based on a predefined mapping. It reads the "map of pages" and creates hyperlinks connecting annotations to their corresponding physical pages.

* **Method: `main(String[] args)`**
    - **Description:** The entry point of the program. Loads the published PDF, links pages based on the provided parameters, and saves the linked output as "linked_output.pdf".
    - **Parameters:** 
        - `args`: Command line arguments (not used in this code).
    - **Return Values:** None.
    - **Important Logic:**
        1. Loads the published PDF document using `PDDocument.load()`.
        2. Calls the `linkPages()` method to perform the page linking process.
        3. Saves the modified PDF document as "linked_output.pdf".

* **Method: `linkPages(PDDocument doc, int mapPageIndex, int start, int end)`**
    - **Description:** Links pages within a PDF document based on a mapping defined on a specific page ("map of pages"). 
    - **Parameters:**
        - `doc`: The loaded PDF document.
        - `mapPageIndex`: The index (starting from 0) of the page containing the page map.
        - `start`: The starting logical page number to link.
        - `end`: The ending logical page number to link.
    - **Return Values:** None.
    - **Important Logic:**
        1. **Step 1: Find target pages:** Extracts logical page numbers from the "map of pages" text and maps them to physical page indices using a regular expression and a HashMap.
        2. **Step 2: Locate annotations:** Uses `TextLocator` to find text hits matching the logical page numbers on the map page.
        3. **Step 3: Create links:** For each found annotation, creates a hyperlink (`PDAnnotationLink`) pointing to the corresponding physical page using `PDActionGoTo` and `PDPageFitDestination`. Adds the link annotation to the map page.

* **Method: `getPageText(PDDocument doc, int pageIndex)`**
    - **Description:** Extracts text content from a specific page in the PDF document.
    - **Parameters:**
        - `doc`: The loaded PDF document.
        - `pageIndex`: The index (starting from 0) of the page to extract text from.
    - **Return Values:** A string containing the extracted text from the specified page.

* **Method: `addLinkAnnotation(PDDocument doc, PDPage page, Rectangle2D.Float bounds, int targetPageIndex)`**
    - **Description:** Adds a hyperlink annotation to a specific page in the PDF document.
    - **Parameters:**
        - `doc`: The loaded PDF document.
        - `page`: The page to add the link annotation to.
        - `bounds`: A rectangle defining the area on the page where the link will be placed.
        - `targetPageIndex`: The index (starting from 0) of the target page for the hyperlink.
    - **Return Values:** None.

* **Class: `TextLocator`**
    - **Purpose:** Extends `PDFTextStripper` to locate specific text hits within a PDF document and store their bounding rectangles.


**5. Pseudo Code:**



```
// Class: PdfLinker

main()
  1. Get published PDF file path from Config.getPublishPdfFilePath()
  2. Load the PDF document using PDDocument.load(publishedPdf)
  3. Set `layoutPageIndex` to 4 (index of the "map of pages" page)
  4. Call `linkPages()` with the loaded document, layoutPageIndex, start page number, and end page number
  5. Save the modified PDF document as "linked_output.pdf"

linkPages(PDDocument doc, int mapPageIndex, int start, int end)
  1. Create a HashMap to store logical page numbers and their corresponding physical page indices
  2. Iterate through each page in the PDF document
    - Extract text content from the page using getPageText()
    - Use a regular expression to find occurrences of "- [number] -" within the text
      - If found, extract the number and add it as a key to the HashMap with its corresponding page index as the value
  3. Create a TextLocator object to locate specific text hits on the map page
  4. Iterate through logical page numbers from start to end
    - Use the TextLocator to find text hits matching each logical page number on the map page
      - If a match is found:
        - Retrieve the corresponding physical page index from the HashMap
        - Create a PDAnnotationLink object with an action pointing to the target page using PDActionGoTo and PDPageFitDestination
        - Add the link annotation to the map page

getPageText(PDDocument doc, int pageIndex)
  1. Use PDFTextStripper to extract text content from the specified page in the document

addLinkAnnotation(PDDocument doc, PDPage page, Rectangle2D.Float bounds, int targetPageIndex)
  1. Create a PDAnnotationLink object with an action pointing to the target page using PDActionGoTo and PDPageFitDestination
  2. Set the link's rectangle to the specified bounds
  3. Add the link annotation to the given page



```

**6. Dependencies and Libraries:**


* **Apache PDFBox:** This library is used for reading, manipulating, and writing PDF documents in Java. It provides classes for accessing document metadata, text content, annotations, and other features. 

   - **Equivalent libraries in other languages:**
      - Python: PyPDF2, ReportLab
      - C++: poppler
      - JavaScript: pdfjs



