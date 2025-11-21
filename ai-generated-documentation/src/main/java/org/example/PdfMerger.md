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


* **`main(String[] args)` Method:**
    - **Description:** This is the entry point of the program. It sets up the paths for the input PDF files and the output merged PDF file based on configurations defined in a `Config` class (not shown in the provided code). Then, it calls the `mergePdfs()` method to perform the merging operation.
    - **Parameters:** 
        - `args`: String array containing command-line arguments (not used in this code).
    - **Return Values:** None.
    - **Important Logic:**  The paths for the input and output PDF files are constructed using information from the `Config` class.

* **`mergePdfs(String introPdfPath, String patternPdfPath, String outputPdfPath)` Method:**
    - **Description:** This method merges two PDF files specified by their paths. It uses the `PDFMergerUtility` class from the `pdfbox` library to handle the merging process.
    - **Parameters:**
        - `introPdfPath`: Path to the "title" PDF file.
        - `patternPdfPath`: Path to the "pattern" PDF file.
        - `outputPdfPath`: Path to the output merged PDF file.
    - **Return Values:** None.
    - **Important Logic:**
        1. Checks if both input PDF files exist. If not, it prints an error message and returns.
        2. Creates a `PDFMergerUtility` object.
        3. Adds both input PDFs to the merger using `addSource()`.
        4. Sets the destination file name for the merged PDF using `setDestinationFileName()`.
        5. Merges the documents using `mergeDocuments(null)`.
        6. Prints a success message if merging is successful.
        7. Handles potential `IOException` during the merging process, printing an error message and stack trace.

**5. Dependencies and Libraries:**


* **pdfbox:** This Java library is used for manipulating PDF files. It provides classes for reading, writing, and merging PDFs. 

   - **Equivalent Libraries in Other Languages:**
      - **Python:** PyPDF2, ReportLab
      - **C++:** poppler-utils
      - **JavaScript (Node.js):** pdfkit


* **Config Class:** This class is assumed to exist and provide configuration settings for the program, such as paths to input and output files and directory names.

**6. Pseudo Code:**



```
// Class: PdfMerger

// Method: main(String[] args)
  1. Get paths for title PDF, pattern PDF, and output PDF from Config class.
  2. Call mergePdfs() method with the obtained paths.

// Method: mergePdfs(introPdfPath, patternPdfPath, outputPdfPath)
  1. Check if introPdfPath exists:
    - If not, print error message and return.
  2. Check if patternPdfPath exists:
    - If not, print error message and return.
  3. Create a PDFMergerUtility object.
  4. Add introPdfPath to the merger using addSource().
  5. Add patternPdfPath to the merger using addSource().
  6. Set the output PDF path using setDestinationFileName(outputPdfPath).
  7. Merge the PDFs using mergeDocuments(null).
  8. Print success message if merging is successful.
  9. Handle potential IOException during merging:
    - Print error message and stack trace.



```

**Note:** This documentation assumes that the `Config` class exists and provides the necessary configuration values for the program to function correctly. 


