![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Get The Future You Want!

---
## PdfLinker Code Documentation

**1. Overview:**

The `PdfLinker` class is designed to link pages within a PDF document based on a predefined mapping defined on a specific page (the "map page"). It reads the map page's text, extracts logical page numbers, and creates hyperlinks to corresponding physical pages in the document. 

**2. Package/module name:** `org.example`

**3. Class/file name:** `PdfLinker.java`

**4. Detailed Documentation:**

   - **`main(String[] args)`:**
     - **Description:** The entry point of the application. It loads a published PDF document, links pages based on the provided parameters, and saves the linked output as "linked_output.pdf".
     - **Parameters:** `args`: An array of strings representing command-line arguments (not used in this code).
     - **Return Values:** None.
     - **Important Logic:** 
       - Loads the PDF document specified by `Config.getPublishPdfFilePath()`.
       - Calls the `linkPages` method to perform the page linking operation.
       - Saves the modified PDF document as "linked_output.pdf".

   - **`linkPages(PDDocument doc, int mapPageIndex, int start, int end)`:**
     - **Description:** Links pages within a PDF document based on a mapping defined on a specific page (the "map page"). 
     - **Parameters:**
       - `doc`: The loaded PDF document.
       - `mapPageIndex`: The index of the page containing the page mapping information.
       - `start`: The starting logical page number to link.
       - `end`: The ending logical page number to link.
     - **Return Values:** None.
     - **Important Logic:**
       - Creates a map (`numberToPhysicalPage`) that maps logical page numbers to their corresponding physical page indices within the document. This mapping is extracted from the text content of the map page using regular expressions.
       - Locates the text positions on the map page corresponding to the desired logical page numbers (e.g., "1.", "2.").
       - For each linked page, it retrieves the target physical page index from `numberToPhysicalPage` and adds a hyperlink annotation to the corresponding text position on the map page.

   - **`getPageText(PDDocument doc, int pageIndex)`:**
     - **Description:** Extracts the text content from a specific page in a PDF document.
     - **Parameters:**
       - `doc`: The loaded PDF document.
       - `pageIndex`: The index of the page to extract text from.
     - **Return Values:** A string containing the extracted text from the specified page.
     - **Important Logic:** Uses `PDFTextStripper` to extract the text content from the given page.

   - **`addLinkAnnotation(PDDocument doc, PDPage page, Rectangle2D.Float bounds, int targetPageIndex)`:**
     - **Description:** Adds a hyperlink annotation to a specific page in a PDF document.
     - **Parameters:**
       - `doc`: The loaded PDF document.
       - `page`: The page to add the hyperlink annotation to.
       - `bounds`: A rectangle defining the area on the page where the hyperlink will be placed.
       - `targetPageIndex`: The index of the target page to link to.
     - **Return Values:** None.
     - **Important Logic:** Creates a `PDAnnotationLink` object, sets its action to a `PDActionGoTo` that points to the target page, and adds the annotation to the specified page.

   - **`TextLocator` Class:**
     - **Description:** A custom class extending `PDFTextStripper` used to locate specific text strings within a PDF document. It extracts text positions and stores them in a list of `TextHit` objects.
     - **Important Logic:** Overrides the `writeString` method to capture text strings matching a specific pattern (e.g., "1.", "2.") and store their position information as `TextHit` objects.

**5. Pseudo Code:**


```
// Class: PdfLinker

// Method: main(String[] args)
  1. Get the path to the published PDF file from Config.getPublishPdfFilePath().
  2. Load the PDF document using PDDocument.load(publishedPdf).
  3. Set the layout page index (map page index) to 4.
  4. Call linkPages method with the loaded document, map page index, start page number (1), and end page number (3).
  5. Save the modified PDF document as "linked_output.pdf".

// Method: linkPages(PDDocument doc, int mapPageIndex, int start, int end)
  1. Create a HashMap called `numberToPhysicalPage` to store the mapping between logical and physical page numbers.
  2. Iterate through each page in the document.
    - Extract the text content from the current page using getPageText(doc, i).
    - Use a regular expression pattern (-\\s+(\\d+)\\s+- ) to find occurrences of logical page numbers within the text.
    - For each match, store the logical page number and its corresponding physical page index in `numberToPhysicalPage`.
  3. Get the map page using doc.getPage(mapPageIndex).
  4. Locate all text hits on the map page using TextLocator.locateText(doc, mapPageIndex).
  5. Iterate through the range of logical page numbers from start to end.
    - For each logical page number:
      - Find the corresponding text hit on the map page that matches the logical page number (e.g., "1.", "2.").
      - Retrieve the target physical page index from `numberToPhysicalPage` using the logical page number.
      - If the target physical page index is found, add a hyperlink annotation to the corresponding text hit on the map page using addLinkAnnotation(doc, mapPage, match.get().bounds, targetPageIndex).

// Method: getPageText(PDDocument doc, int pageIndex)
  1. Create a PDFTextStripper object.
  2. Set the start and end pages for extraction to the specified pageIndex.
  3. Call getText(doc) on the stripper to extract the text content from the document.
  4. Return the extracted text as a string.

// Method: addLinkAnnotation(PDDocument doc, PDPage page, Rectangle2D.Float bounds, int targetPageIndex)
  1. Create a PDAnnotationLink object.
  2. Create a PDActionGoTo object and set its destination to a PDPageFitDestination pointing to the target page.
  3. Set the action of the annotation link to the created PDActionGoTo object.
  4. Set the rectangle of the annotation link to the specified bounds.
  5. Add the annotation link to the specified page using page.getAnnotations().add(link).

// Class: TextLocator
  1. Extend PDFTextStripper class.
  2. Override the writeString method to capture text strings matching a specific pattern (e.g., "\\d+\\.") and store their position information as `TextHit` objects in a list called hits.



```


**6. Dependencies and Libraries:**

- **Apache PDFBox:** This library is used for reading, manipulating, and writing PDF documents. It provides classes for working with pages, annotations, actions, and other PDF elements.
- **Java AWT (Abstract Window Toolkit):** Used for basic geometric shapes like `Rectangle2D`.



