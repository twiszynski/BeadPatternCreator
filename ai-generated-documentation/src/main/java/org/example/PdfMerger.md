![Capgemini Logo](https://www.capgemini.com/wp-content/themes/capgemini2020/assets/images/logo.svg)

### Make it real.

---
## PdfMerger.java Documentation & Pseudocode

**1. Overview:**

This Java program utilizes the Apache PDFBox library to merge two PDF files: a title page (introPdf) and a pattern document (patternPdf). The merged result is saved as a new PDF file (outputPdfPath). 

**2. Package/module name:** org.example

**3. Class/file name:** PdfMerger.java

**4. Detailed Documentation:**

   - **`main(String[] args)`:**
     - **Description:** This is the entry point of the program. It sets up example file paths for the title PDF, pattern PDF, and output merged PDF based on configurations defined in a `Config` class (not shown). Then it calls the `mergePdfs()` method to perform the merging operation.
     - **Parameters:** 
       - `args`: An array of strings representing command-line arguments (unused in this code).
     - **Return Values:** None
     - **Important Logic:**  The function sets up file paths based on configurations and calls the `mergePdfs()` method to handle the merging process.

   - **`mergePdfs(String introPdfPath, String patternPdfPath, String outputPdfPath)`:**
     - **Description:** This method merges two PDF files specified by their paths. It checks if both input files exist and throws an error if either is missing. Then it uses the `PDFMergerUtility` class to add the input PDFs and specify the output file path. Finally, it calls `mergeDocuments()` to perform the merging operation and prints a success message or an error message based on the outcome.
     - **Parameters:** 
       - `introPdfPath`: The path to the title PDF file.
       - `patternPdfPath`: The path to the pattern PDF file.
       - `outputPdfPath`: The path to save the merged PDF file.
     - **Return Values:** None
     - **Important Logic:** 
        - Checks for existence of input files and throws an error if missing.
        - Uses `PDFMergerUtility` to add source PDFs and set the output file path.
        - Calls `mergeDocuments()` to perform the merging operation.
        - Handles potential `IOException` during merging and prints appropriate messages.

**5. Pseudocode:**


```
// Class: PdfMerger

// Method: main(String[] args)
  1. Get title PDF path from configuration (Config.getMergeDirPath() + Config.getTitlePdfName()).
  2. Get pattern PDF path from configuration (Config.getMergeDirPath() + Config.getPatternPdfName()).
  3. Get output merged PDF path from configuration (Config.getPublishDirPath()+Config.getPublishPdfName()).
  4. Call mergePdfs(titlePdfPath, patternPdfPath, outputPdfPath).

// Method: mergePdfs(String introPdfPath, String patternPdfPath, String outputPdfPath)
  1. Check if the title PDF file exists (introPdfPath):
    - If not found, print an error message and return.
  2. Check if the pattern PDF file exists (patternPdfPath):
    - If not found, print an error message and return.
  3. Create a PDFMergerUtility object.
  4. Add the title PDF to the merger using addSource(introFile).
  5. Add the pattern PDF to the merger using addSource(patternFile).
  6. Set the destination file name for the merged PDF using setDestinationFileName(outputPdfPath).
  7. Merge the documents using mergeDocuments(null):
    - If successful, print a success message with the output path.
    - If an IOException occurs:
      - Print an error message with the exception details.
      - Rethrow the exception to allow for higher-level handling. 

```



**Dependencies and Libraries:**

* **Apache PDFBox:** This library is essential for merging PDFs in Java. It provides classes like `PDFMergerUtility` for manipulating PDF files.


Let me know if you have any other questions or need further clarification on the code documentation or pseudocode!