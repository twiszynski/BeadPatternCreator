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
        ZipSecureFile.setMinInflateRatio(0.003); // Ustawienie niższego limitu
    }

    public static void main(String[] args) {
        printSetup(Config.getXlsxPatternFilePath());
        printToPdf(Config.getXlsxPatternFilePath(), Config.getLibraryBaseDirectoryPath());
    }

    public static void printSetup(String excelFilePath) {
        final double bottomMarginDefault = 1.25;
        final double topMarginDefault = 0.75;
        final double leftMarginDefault = 0.5;
        final double rightMarginDefault = 0.5;

        try (FileInputStream fis = new FileInputStream(new File(excelFilePath));
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

        //Setup for 'Pattern' sheet ######################################################################
            XSSFSheet patternSheet = workbook.getSheet(Config.getPatternSheetName());
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
                header.setCenter("&\"Montserrat SemiBold\"- &P -");
                header.setLeft("&\"Montserrat SemiBold\""+Config.getDesignNoAndName());
                header.setRight("&\"Montserrat SemiBold\"BEAD PATTERN");

                // Footer
                Footer footer = patternSheet.getFooter();
                footer.setLeft("&9&\"Montserrat\""+Config.getCopywriteNote());
                footer.setCenter("");
                footer.setRight("&14<LOGO>");

                // Orientacja pozioma
                patternPS.setLandscape(false);
                // Format papieru
                patternPS.setPaperSize(PaperSize.A4_PAPER);
                // Centrowanie wydruku
                patternSheet.setHorizontallyCenter(true);
            }

        //Setup for 'Legend' sheet ######################################################################
            XSSFSheet legendSheet = workbook.getSheet(Config.getLegendSheetName());
            if (legendSheet != null) {
                XSSFPrintSetup legendPS = legendSheet.getPrintSetup();
                // margins
                legendSheet.setMargin(PageMargin.BOTTOM, bottomMarginDefault);
                legendSheet.setMargin(PageMargin.TOP, topMarginDefault);
                legendSheet.setMargin(PageMargin.LEFT, leftMarginDefault);
                legendSheet.setMargin(PageMargin.RIGHT, rightMarginDefault);

                // Header
                Header header = legendSheet.getHeader();
                header.setLeft("&\"Montserrat SemiBold\""+Config.getDesignNoAndName());
                header.setCenter("&\"Montserrat SemiBold\"- &P -");
                header.setRight("&\"Montserrat SemiBold\" BEAD LEGEND");

                // Footer
                Footer footer = legendSheet.getFooter();
                footer.setLeft("&9&\"Montserrat\""+Config.getCopywriteNote());
                footer.setCenter("");
                footer.setRight("&14<LOGO>");

                // Orientacja pozioma
                legendPS.setLandscape(false);
                // Format papieru
                legendPS.setPaperSize(PaperSize.A4_PAPER);
                // Centrowanie wydruku
                legendSheet.setHorizontallyCenter(true);
            }


        //Setup for 'PageMap' sheet ######################################################################
            XSSFSheet pmapSheet = workbook.getSheet(Config.getPageMapSheetName());
            if (pmapSheet != null) {
                XSSFPrintSetup pmapPS = pmapSheet.getPrintSetup();

                //Ustalenie wspolczynnika skalowania
                int patternHeight = 0;
                int patternWidth = 0;

                // **Odczytanie liczby wierszy i kolumn z arkusza Size**
                Sheet sizeSheet = workbook.getSheet(Config.getSizeSheetName());
                if (sizeSheet != null) {
                    Row row = sizeSheet.getRow(1);
                    if (row != null) {
                        patternWidth = (int) row.getCell(0).getNumericCellValue();
                        patternHeight = (int) row.getCell(1).getNumericCellValue();
                    }
                } else {
                    System.out.println("Brak arkusza 'Size'");
                    return;
                }

                double scaleWidthFactor = (double) patternWidth / Config.getColsPerPage();
                double scaleHeightFactor = (double) patternHeight / Config.getRowsPerPage();
                double scaleFactor = Math.max(scaleWidthFactor, scaleHeightFactor);
                int baseFontSize = 10;
                int baseFontSizeCN = 9;
                int adjustedFontSize = (int) Math.round(baseFontSize * scaleFactor)+2;
                int adjustedFontSizeCN = (int) Math.round(baseFontSizeCN * scaleFactor)+2;

                // margins
                pmapSheet.setMargin(PageMargin.BOTTOM, bottomMarginDefault*scaleFactor);
                pmapSheet.setMargin(PageMargin.TOP, topMarginDefault*scaleFactor);
                pmapSheet.setMargin(PageMargin.LEFT, leftMarginDefault*scaleFactor);
                pmapSheet.setMargin(PageMargin.RIGHT, rightMarginDefault*scaleFactor);

                // Header
                Header header = pmapSheet.getHeader();
                header.setLeft("&\"Montserrat SemiBold\"&"+adjustedFontSize+Config.getDesignNoAndName());
                header.setCenter("&\"Montserrat SemiBold\"&"+adjustedFontSize+"- &P -");
                header.setRight("&\"Montserrat SemiBold\"&"+adjustedFontSize+"PATTERN PAGE LAYOUT");

                // Footer
                Footer footer = pmapSheet.getFooter();
                String footerNote = "&\"Montserrat\"&"+adjustedFontSizeCN+Config.getCopywriteNote();
                footer.setLeft(footerNote);
                footer.setCenter("");
                footer.setRight("&"+adjustedFontSize+"<LOGO>");

                // Orientacja pozioma
                pmapPS.setLandscape(false);
                // Format papieru
                pmapPS.setPaperSize(PaperSize.A4_PAPER);
                // Centrowanie wydruku
                pmapSheet.setHorizontallyCenter(true);

                // Dopasowanie do strony
                pmapSheet.setFitToPage(true);

                // Skalowanie do jednej strony
                pmapPS.setFitWidth((short) 1);
                pmapPS.setFitHeight((short) 1);

            }

        //Setup for 'Word_Chart' sheet  ######################################################################
            XSSFSheet wchartSheet = workbook.getSheet(Config.getWordChartSheetName());
            if (wchartSheet != null) {
                XSSFPrintSetup wchartPS = wchartSheet.getPrintSetup();
                // margins
                wchartSheet.setMargin(PageMargin.BOTTOM, bottomMarginDefault);
                wchartSheet.setMargin(PageMargin.TOP, topMarginDefault);
                wchartSheet.setMargin(PageMargin.LEFT, leftMarginDefault);
                wchartSheet.setMargin(PageMargin.RIGHT, rightMarginDefault);

                // Header
                Header header = wchartSheet.getHeader();
                header.setLeft("&\"Montserrat SemiBold\""+Config.getDesignNoAndName());
                header.setCenter("&\"Montserrat SemiBold\"- &P -");
                header.setRight("&\"Montserrat SemiBold\"WORD CHART");

                // Footer
                Footer footer = wchartSheet.getFooter();
                footer.setLeft("&9&\"Montserrat\""+Config.getCopywriteNote());
                footer.setCenter("");
                footer.setRight("&14<LOGO>");

                // Orientacja pozioma
                wchartPS.setLandscape(false);
                // Format papieru
                wchartPS.setPaperSize(PaperSize.A4_PAPER);
                // Centrowanie wydruku
                wchartSheet.setHorizontallyCenter(true);

//                wchartSheet.setFitToPage(true);
                wchartPS.setFitWidth((short) 1);
            }



            // Wykluczenie arkuszy roboczych - Size
            workbook.setSheetHidden(workbook.getSheetIndex(Config.getSizeSheetName()),true);


            //Ustawienie kolejności arkuszy - jesli wszystkie istnieja
            if(workbook.getSheetIndex(Config.getPageMapSheetName()) != -1 && workbook.getSheetIndex(Config.getPatternSheetName()) != -1 &&
                    workbook.getSheetIndex(Config.getLegendSheetName()) != -1 && workbook.getSheetIndex(Config.getWordChartSheetName()) != -1)
            {
                workbook.setSheetOrder(Config.getPageMapSheetName(), 0);     // pierwszy do druku
                workbook.setSheetOrder(Config.getLegendSheetName(), 1);      // trzeci
                workbook.setSheetOrder(Config.getPatternSheetName(), 2);     // drugi
                workbook.setSheetOrder(Config.getWordChartSheetName(), 3);  // czwarty
            }


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
            System.out.println("Wydruk zakończony! " + "Plik: " + Config.getXlsxPatternFileName() + ".pdf");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

