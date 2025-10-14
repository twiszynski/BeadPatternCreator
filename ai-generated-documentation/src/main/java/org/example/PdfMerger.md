![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Get The Future You Want!

---
## PdfMerger.java Documentation

**1. Overview:**

This Java program utilizes the `pdfbox` library to merge two PDF files: a "title" PDF and a "pattern" PDF. The merged result is saved as a new PDF file in a specified output directory. 

**2. Package/Module Name:**

org.example

**3. Class/File Name:**

PdfMerger.java

**4. Detailed Documentation:**


   - **`main(String[] args)` Method:**
     - **Description:** The entry point of the program. It sets up the paths for the input PDFs and the output PDF, then calls the `mergePdfs()` method to perform the merging operation.
     - **Parameters:** 
       - `args`: An array of strings representing command-line arguments (not used in this code).
     - **Return Values:** None.
     - **Important Logic:**  The method retrieves file paths from a configuration object (`Config`) and calls `mergePdfs()` to handle the merging process.

   - **`mergePdfs(String introPdfPath, String patternPdfPath, String outputPdfPath)` Method:**
     - **Description:** Merges two PDF files specified by `introPdfPath` and `patternPdfPath`, and saves the merged result to `outputPdfPath`.
     - **Parameters:**
       - `introPdfPath`: The path to the "title" PDF file.
       - `patternPdfPath`: The path to the "pattern" PDF file.
       - `outputPdfPath`: The path to save the merged PDF file.
     - **Return Values:** None.
     - **Important Logic:**
       - Checks if both input files exist. If not, prints an error message and returns.
       - Creates a `PDFMergerUtility` object from the `pdfbox` library.
       - Adds both input PDFs to the merger using `addSource()`.
       - Sets the output file name using `setDestinationFileName()`.
       - Merges the documents using `mergeDocuments(null)`.
       - Prints a success message if merging is successful, or an error message with stack trace if an exception occurs.

**5. Pseudo Code:**



```
// Class: PdfMerger

// Method: main(String[] args)
  1. Get paths for title PDF, pattern PDF, and output PDF from Config object.
  2. Call mergePdfs() method with the obtained file paths.

// Method: mergePdfs(String introPdfPath, String patternPdfPath, String outputPdfPath)
  1. Check if "introPdfPath" exists:
    - If not, print error message and return.
  2. Check if "patternPdfPath" exists:
    - If not, print error message and return.
  3. Create a PDFMergerUtility object.
  4. Add "introPdfPath" to the merger using addSource().
  5. Add "patternPdfPath" to the merger using addSource().
  6. Set the output file name using setDestinationFileName(outputPdfPath).
  7. Merge the documents using mergeDocuments(null):
    - If successful, print success message.
    - If an exception occurs:
      - Print error message with stack trace.

```



**Dependencies and Libraries:**


* **pdfbox:** This Java library is used for manipulating PDF files. 
   -  Equivalent libraries in other languages:
     - Python: PyPDF2, ReportLab
     - C++: poppler
     - JavaScript: pdfjs-dist




