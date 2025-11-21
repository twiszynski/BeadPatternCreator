![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Get The Future You Want!

---
## PdfLinker Code Documentation

**1. Overview:**

The `PdfLinker` Java program automates the process of linking pages within a PDF document based on a predefined mapping. It reads a specific page (the "map page") containing numbered links to other pages in the document. The program then extracts these links, identifies the corresponding target pages using a pattern-matching approach, and creates clickable annotations that link from the map page to the target pages.

**2. Package/module name:** `org.example`

**3. Class/file name:** `PdfLinker.java`

**4. Detailed Documentation:**

   - **`main(String[] args)`:**
     - **Description:** The entry point of the program. It loads a published PDF document, links pages based on a map page and specified range, and saves the linked output as "linked_output.pdf".
     - **Parameters:** `args`: Command-line arguments (not used in this code).
     - **Return Values:** None.
     - **Important Logic:** 
       - Loads the PDF document from the path specified by `Config.getPublishPdfFilePath()`.
       - Calls the `linkPages` method to perform the page linking operation.
       - Saves the modified PDF document as "linked_output.pdf".

   - **`linkPages(PDDocument doc, int mapPageIndex, int start, int end)`:**
     - **Description:** Links pages within a PDF document based on a map page and a specified range of links.
     - **Parameters:** 
       - `doc`: The loaded PDF document.
       - `mapPageIndex`: Index of the page containing the link mapping (zero-based).
       - `start`: Starting logical number of the link to process.
       - `end`: Ending logical number of the link to process.
     - **Return Values:** None.
     - **Important Logic:** 
       - Creates a map (`numberToPhysicalPage`) that maps logical page numbers (e.g., "- 3 -") to their corresponding physical page indices in the document.
       - Locates text on the map page using `TextLocator` and extracts the positions of links based on patterns like "1.", "2.", etc.
       - Iterates through the specified range of links, retrieves the target logical number from each link, and uses the `numberToPhysicalPage` map to find the corresponding physical page index.
       - Adds a clickable annotation (link) to the map page, linking it to the target page using `addLinkAnnotation`.

   - **`getPageText(PDDocument doc, int pageIndex)`:**
     - **Description:** Extracts text content from a specific page in a PDF document.
     - **Parameters:** 
       - `doc`: The loaded PDF document.
       - `pageIndex`: Index of the page to extract text from (zero-based).
     - **Return Values:** A string containing the extracted text from the specified page.
     - **Important Logic:** Uses `PDFTextStripper` to extract text content from a single page.

   - **`addLinkAnnotation(PDDocument doc, PDPage page, Rectangle2D.Float bounds, int targetPageIndex)`:**
     - **Description:** Adds a clickable annotation (link) to a specific page in a PDF document.
     - **Parameters:** 
       - `doc`: The loaded PDF document.
       - `page`: The page to add the link to.
       - `bounds`: Rectangle defining the position and size of the link on the page.
       - `targetPageIndex`: Index of the target page (zero-based) that the link should point to.
     - **Return Values:** None.
     - **Important Logic:** Creates a `PDAnnotationLink` object, sets its action to navigate to the target page using `PDActionGoTo`, and adds it to the specified page's annotations.

   - **`TextLocator` Class:**
     - **Description:** A custom class extending `PDFTextStripper` that locates specific text strings on a PDF page and stores their positions as `TextHit` objects.
     - **Parameters:** None (constructor takes no parameters).
     - **Return Values:** Returns a list of `TextHit` objects containing the located text and its bounding rectangle.

**5. Pseudo Code:**


```
// Class: PdfLinker

// Method: main(String[] args)
  1. Get published PDF file path from Config.getPublishPdfFilePath()
  2. Load PDF document using PDDocument.load()
  3. Set `layoutPageIndex` to 4 (map page index)
  4. Call linkPages() with loaded document, map page index, start link number, and end link number
  5. Save linked PDF document as "linked_output.pdf"

// Method: linkPages(PDDocument doc, int mapPageIndex, int start, int end)
  1. Create a HashMap `numberToPhysicalPage` to store logical page numbers and their corresponding physical page indices
  2. Iterate through each page in the PDF document
    - Extract text content from the page using getPageText()
    - Use a pattern matcher to find occurrences of "- N -" (where N is a number) in the extracted text
    - Store the matched number as the logical page number and its corresponding page index in `numberToPhysicalPage`
  3. Get the map page using doc.getPage(mapPageIndex)
  4. Locate text on the map page using TextLocator.locateText()
  5. Iterate through the specified range of links (start to end)
    - Extract the target logical number from each link on the map page
    - Retrieve the corresponding physical page index from `numberToPhysicalPage`
    - If a valid physical page index is found:
      - Add a clickable annotation to the map page using addLinkAnnotation() linking it to the target page
      - Print a message indicating successful link creation
    - If no valid physical page index is found:
      - Print a message indicating that the link could not be created

// Method: getPageText(PDDocument doc, int pageIndex)
  1. Create a PDFTextStripper object
  2. Set the start and end pages to the specified pageIndex
  3. Extract text from the document using getText()
  4. Return the extracted text as a string

// Method: addLinkAnnotation(PDDocument doc, PDPage page, Rectangle2D.Float bounds, int targetPageIndex)
  1. Create a PDAnnotationLink object
  2. Set the action to navigate to the target page using PDActionGoTo()
  3. Add the link annotation to the specified page

// Class: TextLocator
  1. Extend PDFTextStripper and override writeString() method
  2. In writeString(), check if the string matches a pattern like "N." (where N is a number)
  3. If it matches, create a TextHit object with the text and its bounding rectangle using Rectangle2D.Float
  4. Add the TextHit object to the hits list



```

**6. Dependencies and Libraries:**


- **Apache PDFBox:** This library is used for reading, manipulating, and writing PDF documents in Java. It provides classes for working with pages, annotations, actions, and other PDF elements. 

   - **Equivalent libraries in other languages:**
      - Python: PyPDF2, ReportLab
      - C++: poppler-cpp
      - JavaScript: pdfjs-dist



