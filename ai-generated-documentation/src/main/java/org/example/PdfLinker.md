![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Make it real.

---
## PdfLinker Code Documentation

**1. Overview:**

The `PdfLinker` class is designed to link pages within a PDF document based on a predefined mapping. It reads a specific page ("map page") containing numbered links to other pages in the document. The code then extracts the text from each linked entry and uses it to locate corresponding annotations on the map page. Finally, it creates hyperlinks connecting these annotations to the target pages.

**2. Package/module name:** `org.example`

**3. Class/file name:** `PdfLinker.java`

**4. Class diagram name:** `PdfLinker_diagram.svg` (provided in your request)

**5. Detailed Documentation:**

   - **`main(String[] args)`:**
     - **Description:** The entry point of the program. It loads a PDF document, links pages based on the mapping defined in the "map page", and saves the modified document.
     - **Parameters:** `args`: An array of strings containing command-line arguments (not used in this code).
     - **Return Values:** None.
     - **Important Logic:**
       - Loads the PDF document specified by `Config.getPublishPdfFilePath()`.
       - Calls `linkPages()` to perform the page linking operation.
       - Saves the modified PDF document as "linked_output.pdf".

   - **`linkPages(PDDocument doc, int mapPageIndex, int start, int end)`:**
     - **Description:** Links pages within a PDF document based on a mapping defined in a specific page.
     - **Parameters:**
       - `doc`: The loaded PDF document.
       - `mapPageIndex`: The index of the page containing the page mapping (starting from 0).
       - `start`: The starting logical page number to link.
       - `end`: The ending logical page number to link.
     - **Return Values:** None.
     - **Important Logic:**
       - Creates a map (`numberToPhysicalPage`) to store the mapping between logical page numbers and their corresponding physical page indices.
       - Extracts text from each page of the document and uses a regular expression to identify logical page numbers.
       - Locates the "map page" and extracts text hits (text positions) using `TextLocator`.
       - Iterates through the specified range of logical page numbers (`start` to `end`).
       - For each logical page number, it searches for the corresponding text hit on the map page.
       - If a match is found, it retrieves the physical page index from the `numberToPhysicalPage` map and adds a hyperlink annotation connecting the text hit to the target page.

   - **`getPageText(PDDocument doc, int pageIndex)`:**
     - **Description:** Extracts the text content from a specific page of a PDF document.
     - **Parameters:**
       - `doc`: The loaded PDF document.
       - `pageIndex`: The index of the page to extract text from (starting from 0).
     - **Return Values:** A string containing the extracted text from the specified page.
     - **Important Logic:** Uses `PDFTextStripper` to extract text from a single page.

   - **`addLinkAnnotation(PDDocument doc, PDPage page, Rectangle2D.Float bounds, int targetPageIndex)`:**
     - **Description:** Adds a hyperlink annotation to a specific page of a PDF document.
     - **Parameters:**
       - `doc`: The loaded PDF document.
       - `page`: The page to add the annotation to.
       - `bounds`: A rectangle defining the area on the page where the annotation should be placed.
       - `targetPageIndex`: The index of the target page to link to (starting from 0).
     - **Return Values:** None.
     - **Important Logic:** Creates a `PDAnnotationLink` object, sets its action to a `PDActionGoTo` pointing to the target page, and adds it to the specified page's annotations.

   - **`TextLocator` Class:**
     - **Description:** A custom class extending `PDFTextStripper` that extracts text hits (text positions) from a specific page of a PDF document.
     - **Important Logic:** Overrides the `writeString()` method to capture text positions and store them in a list (`hits`).

**6. Pseudo Code:**


```
// Class: PdfLinker

main()
  1. Load PDF document using provided file path.
  2. Get map page index from configuration.
  3. Define start and end logical page numbers to link.
  4. Call linkPages() function with loaded document, map page index, start, and end values.
  5. Save modified PDF document as "linked_output.pdf".

linkPages(PDDocument doc, int mapPageIndex, int start, int end)
  1. Create a mapping between logical page numbers and physical page indices (numberToPhysicalPage).
  2. Iterate through each page of the document:
    - Extract text from the page.
    - Use regular expression to identify logical page numbers in the extracted text.
    - Store the identified logical page number and its corresponding physical page index in the mapping.
  3. Locate the map page using the provided mapPageIndex.
  4. Extract text hits (text positions) from the map page using TextLocator class.
  5. Iterate through the specified range of logical page numbers (start to end):
    - For each logical page number:
      - Search for the corresponding text hit on the map page.
      - If a match is found:
        - Retrieve the physical page index from the mapping.
        - Add a hyperlink annotation connecting the text hit to the target page using addLinkAnnotation() function.
      - If no match is found, log an error message indicating that the logical page number was not found on the map page.

getPageText(PDDocument doc, int pageIndex)
  1. Use PDFTextStripper class to extract text from the specified page of the document.
  2. Return the extracted text as a string.

addLinkAnnotation(PDDocument doc, PDPage page, Rectangle2D.Float bounds, int targetPageIndex)
  1. Create a PDAnnotationLink object.
  2. Set the action of the annotation to a PDActionGoTo pointing to the target page.
  3. Add the annotation to the specified page using its addAnnotations() method.

TextLocator class
  1. Override writeString() method:
    - Capture text positions and store them in a list (hits).



```


**7. Dependencies and Libraries:**

* **Apache PDFBox:** This library is used for reading, manipulating, and writing PDF documents. It provides classes for accessing document metadata, extracting text, adding annotations, and more. 

   - **Equivalent libraries in other languages:**
      - Java: Apache PDFBox (already used)
      - Python: PyPDF2, pdfminer.six
      - C++: poppler-cpp



