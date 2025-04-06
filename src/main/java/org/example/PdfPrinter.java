package org.example;

import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFPrintSetup;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class PdfPrinter {

    static {
        ZipSecureFile.setMinInflateRatio(0.005); // Ustawienie niższego limitu
    }
    private static final String COPYWRITE_NOTE = "©2025 Beadventure. This pattern is for personal use only.\n" +
            "You may not copy, share, modify, or resell this file in any form without written permission.";

    private final static String IMAGE_PATTERN_FILE_NAME = "ms3_M_Pattern";
    private final static String EXCEL_FILE = "C:\\Users\\Admin\\Desktop\\ImgToExcel\\" + IMAGE_PATTERN_FILE_NAME + ".xlsx";
    private final static String OUTPUT_PDF = "C:\\Users\\Admin\\Desktop\\ImgToExcel\\";


    public static void main(String[] args) {
        printSetup(EXCEL_FILE);
        printToPdf(EXCEL_FILE, OUTPUT_PDF);
    }

    public static void printSetup(String excelFilePath) {
        final double bottomMarginDefault = 1.0;
        final double topMarginDefault = 0.75;
        final double leftMarginDefault = 0.5;
        final double rightMarginDefault = 0.5;

        try (FileInputStream fis = new FileInputStream(new File(excelFilePath));
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            //Setup for 'Pattern' sheet
            XSSFSheet patternSheet = workbook.getSheet("Pattern");
            if (patternSheet != null) {
                XSSFPrintSetup patternPS = patternSheet.getPrintSetup();
                // margins
                patternSheet.setMargin(PageMargin.BOTTOM, bottomMarginDefault);
                patternSheet.setMargin(PageMargin.TOP, topMarginDefault);
                patternSheet.setMargin(PageMargin.LEFT, leftMarginDefault);
                patternSheet.setMargin(PageMargin.RIGHT, rightMarginDefault);
                patternSheet.setRepeatingRows(CellRangeAddress.valueOf("1:1"));
                patternSheet.setRepeatingColumns(CellRangeAddress.valueOf("A:A"));

                // Header
                Header header = patternSheet.getHeader();
                header.setLeft("");
                header.setCenter("BEAD PATTERN - PAGE &P");
                header.setRight("");

                // Footer
                Footer footer = patternSheet.getFooter();
                footer.setLeft("");
                footer.setCenter(COPYWRITE_NOTE);
                footer.setRight("");

                // Orientacja pozioma
                patternPS.setLandscape(false);
                // Format papieru
                patternPS.setPaperSize(PaperSize.A4_PAPER);
                // Centrowanie wydruku
                patternSheet.setHorizontallyCenter(true);
            }

            //Setup for 'Legend' sheet
            XSSFSheet legendSheet = workbook.getSheet("Legend");
            if (legendSheet != null) {
                XSSFPrintSetup legendPS = legendSheet.getPrintSetup();
                // margins
                legendSheet.setMargin(PageMargin.BOTTOM, bottomMarginDefault);
                legendSheet.setMargin(PageMargin.TOP, topMarginDefault);
                legendSheet.setMargin(PageMargin.LEFT, leftMarginDefault);
                legendSheet.setMargin(PageMargin.RIGHT, rightMarginDefault);

                // Header
                Header header = legendSheet.getHeader();
                header.setLeft("");
                header.setCenter("BEAD LEGEND");
                header.setRight("");

                // Footer
                Footer footer = legendSheet.getFooter();
                footer.setLeft("");
                footer.setCenter(COPYWRITE_NOTE);
                footer.setRight("");

                // Orientacja pozioma
                legendPS.setLandscape(false);
                // Format papieru
                legendPS.setPaperSize(PaperSize.A4_PAPER);
                // Centrowanie wydruku
                legendSheet.setHorizontallyCenter(true);
            }


            //Setup for 'Word_Chart' sheet
            XSSFSheet wchartSheet = workbook.getSheet("Word_Chart");
            if (wchartSheet != null) {
                XSSFPrintSetup wchartPS = wchartSheet.getPrintSetup();
                // margins
                wchartSheet.setMargin(PageMargin.BOTTOM, bottomMarginDefault);
                wchartSheet.setMargin(PageMargin.TOP, topMarginDefault);
                wchartSheet.setMargin(PageMargin.LEFT, leftMarginDefault);
                wchartSheet.setMargin(PageMargin.RIGHT, rightMarginDefault);

                // Header
                Header header = wchartSheet.getHeader();
                header.setLeft("");
                header.setCenter("WORD CHART - PAGE &P");
                header.setRight("");

                // Footer
                Footer footer = wchartSheet.getFooter();
                footer.setLeft("");
                footer.setCenter(COPYWRITE_NOTE);
                footer.setRight("");

                // Orientacja pozioma
                wchartPS.setLandscape(false);
                // Format papieru
                wchartPS.setPaperSize(PaperSize.A4_PAPER);
                // Centrowanie wydruku
                wchartSheet.setHorizontallyCenter(true);
            }

            // Skalowanie do jednej strony
//            patternPS.setFitWidth((short) 1);
//            patternPS.setFitHeight((short) 1);


            // Zapisanie pliku XLSX
            try (FileOutputStream fileOut = new FileOutputStream(excelFilePath)) {
                workbook.write(fileOut);
            }
            workbook.close();

            System.out.println("Ustawienia wydruku zostały zapisane.");

        } catch (IOException e) {
            System.err.println("Błąd podczas przetwarzania pliku: " + e.getMessage());
        }
    }

    public static void printToPdf(String excelFilePath, String pdfOutputPath) {
        try {
            String command = "soffice --headless --convert-to pdf " + excelFilePath + " --outdir " + pdfOutputPath;
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();
            System.out.println("Wydruk zakończony! " + "Plik: " + IMAGE_PATTERN_FILE_NAME + ".pdf");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

