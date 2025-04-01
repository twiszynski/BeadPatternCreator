package org.example;

import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFPrintSetup;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class PdfPrinter {

    static {
        ZipSecureFile.setMinInflateRatio(0.005); // Ustawienie niższego limitu
    }
    private static final String COPYWRITE_NOTE = "©2025 Beadventure. This pattern is for personal use only." +
            "\nYou may not copy, share, modify, or resell this file in any form without written permission.";

    private final static String IMAGE_PATTERN_FILE_NAME = "fox2_M_Pattern";
    private final static String EXCEL_FILE = "C:\\Users\\Admin\\Desktop\\ImgToExcel\\" + IMAGE_PATTERN_FILE_NAME + ".xlsx";
    private final static String OUTPUT_PDF = "C:\\Users\\Admin\\Desktop\\ImgToExcel\\";


    public static void main(String[] args) {
        printSetup(EXCEL_FILE);
        printToPdf(EXCEL_FILE, OUTPUT_PDF);
    }

    public static void printSetup(String excelFilePath) {
        final double bottomMarginDefault = 0.75;
        final double topMarginDefault = 0.75;
        final double leftMarginDefault = 0.5;
        final double rightMarginDefault = 0.5;

        try (FileInputStream fis = new FileInputStream(new File(excelFilePath));
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            //Setup for 'Pattern' sheet
            XSSFSheet patternSheet = workbook.getSheet("Pattern");
            XSSFPrintSetup patternPS = patternSheet.getPrintSetup();
            // margins
            patternSheet.setMargin(PageMargin.BOTTOM, bottomMarginDefault);
            patternSheet.setMargin(PageMargin.TOP, topMarginDefault);
            patternSheet.setMargin(PageMargin.LEFT, leftMarginDefault);
            patternSheet.setMargin(PageMargin.RIGHT, rightMarginDefault);

            // Ustawienie stopki
            Footer footer = patternSheet.getFooter();
            footer.setLeft(COPYWRITE_NOTE);
            footer.setCenter("");
            footer.setRight("Bead Pattern - &P/&N");

            // Orientacja pozioma
            patternPS.setLandscape(false);
            // Format papieru
            patternPS.setPaperSize(PaperSize.A4_PAPER);
            // Centrowanie wydruku
            patternSheet.setHorizontallyCenter(true);

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
            System.out.println("Konwersja zakończona!");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

