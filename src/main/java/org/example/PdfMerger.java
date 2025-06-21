package org.example;

import org.apache.pdfbox.multipdf.PDFMergerUtility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PdfMerger {

    public static void main(String[] args) {
        // Przykładowe ścieżki – zmień je według potrzeb
        String titlePdfPath = Config.getMergeDirPath() + Config.getTitlePdfName();
        String patternPdfPath = Config.getMergeDirPath() + Config.getPatternPdfName();
        String mergedPdfPath = Config.getPublishDirPath()+Config.getPublishPdfName();

        mergePdfs(titlePdfPath, patternPdfPath, mergedPdfPath);
    }

    public static void mergePdfs(String introPdfPath, String patternPdfPath, String outputPdfPath) {
        File introFile = new File(introPdfPath);
        File patternFile = new File(patternPdfPath);

        if (!introFile.exists()) {
            System.err.println("Intro file not found: " + introPdfPath);
            return;
        }

        if (!patternFile.exists()) {
            System.err.println("Pattern file not found: " + patternPdfPath);
            return;
        }

        PDFMergerUtility merger = new PDFMergerUtility();
        try {
            merger.addSource(introFile);
            merger.addSource(patternFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        merger.setDestinationFileName(outputPdfPath);

        try {
            merger.mergeDocuments(null);
            System.out.println("PDFs successfully merged into: " + outputPdfPath);
        } catch (IOException e) {
            System.err.println("Error merging PDFs: " + e.getMessage());
            e.printStackTrace();
        }
    }

}

