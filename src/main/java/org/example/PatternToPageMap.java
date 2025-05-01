package org.example;

import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFColor;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PatternToPageMap {

    static {
        ZipSecureFile.setMinInflateRatio(0.003); // Ustawienie niższego limitu
    }

    public static void main(String[] args) {
        String fileName = "bird";
        String excelFilePath = "C:\\Users\\Admin\\Desktop\\ImgToExcel\\" + fileName + "_M_Pattern.xlsx";

        // **Podaj liczbę wierszy i kolumn na pojedynczej stronie wydruku**
        int colsPerPage = 30;  // Liczba kolumn na stronę (sprawdzone po wydruku)
        int rowsPerPage = 37;  // Liczba wierszy na stronę (sprawdzone po wydruku)


        convertExcelToPageMap(excelFilePath, rowsPerPage, colsPerPage);
    }

    public static void convertExcelToPageMap(String excelFilePath, int rowsPerPage, int colsPerPage) {
        try (FileInputStream fis = new FileInputStream(excelFilePath)) {
            Workbook workbook = new XSSFWorkbook(fis);

            // **Wczytanie mapy kolorów**
            Map<String, int[]> colorMap = readLegendColors(workbook);

            int patternRows = 0;
            int patternColumns = 0;

            // **Odczytanie liczby wierszy i kolumn z arkusza Size**
            Sheet sizeSheet = workbook.getSheet("Size");
            if (sizeSheet != null) {
                Row row = sizeSheet.getRow(1);
                if (row != null) {
                    patternColumns = (int) row.getCell(0).getNumericCellValue();
                    patternRows = (int) row.getCell(1).getNumericCellValue();
                }
            } else {
                System.out.println("Brak arkusza 'Size'");
                return;
            }


            // Indeks wybranego koloru z predefiniowanej palety kolorow
        short borderColorIndex = IndexedColors.RED.getIndex();


            // **Usunięcie istniejącego arkusza "PageMap", jeśli istnieje**
            int pageMapIndex = workbook.getSheetIndex("PageMap");
            if (pageMapIndex != -1) {
                workbook.removeSheetAt(pageMapIndex);
            }

            // **Tworzenie nowego arkusza "PageMap"**
            Sheet pageMapSheet = workbook.createSheet("PageMap");
            Sheet patternSheet = workbook.getSheet("Pattern");

            // **Wypełnianie arkusza "PageMap" danymi kolorystycznymi**
            for (int r = 0; r < patternRows; r++) {
                Row row = patternSheet.getRow(r + 1);
                Row newRow = pageMapSheet.createRow(r);

                if (row == null) continue;

                for (int c = 0; c < patternColumns; c++) {
                    Cell cell = row.getCell(c + 1);
                    Cell newCell = newRow.createCell(c);

                    String symbol = (cell != null) ? cell.getStringCellValue().toUpperCase() : "";
                    int[] rgb = colorMap.getOrDefault(symbol, new int[]{255, 255, 255});

                    // **Ustawienie koloru tła komórki**
                    CellStyle style = workbook.createCellStyle();
                    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    style.setFillForegroundColor(new XSSFColor(new java.awt.Color(rgb[0], rgb[1], rgb[2]), null));
                    newCell.setCellStyle(style);
                }
            }

            // **Dodanie obramowań zakresów stron**
            int pageNumber = 1;
            for (int c = 0; c < patternColumns; c += colsPerPage) { // Przechodzenie w "kolumnach" stron
                for (int r = 0; r < patternRows; r += rowsPerPage) { // Przechodzenie od góry do dołu
                    int startRow = r;
                    int endRow = Math.min(r + rowsPerPage - 1, patternRows - 1);
                    int startCol = c;
                    int endCol = Math.min(c + colsPerPage - 1, patternColumns - 1);

                    // **Dodanie wyraźnego obramowania wokół zakresu**
                    addSelectiveBorders(pageMapSheet, startRow, endRow, startCol, endCol, borderColorIndex);

                    // **Dodanie numeru strony w lewym górnym rogu zakresu**
                    Row row = pageMapSheet.getRow(startRow);
                    Cell cell = row.createCell(startCol);
                    cell.setCellValue("" + pageNumber);
                    pageNumber++;
                }
            }

            // Ustawienia szerokości kolumn i wysokości wierszy
            for (int col = 0; col < patternColumns; col++) {
                pageMapSheet.setColumnWidth(col, 256 * 3);
            }
            pageMapSheet.setDefaultRowHeight((short) 370);

            // **Zapisanie zmian w tym samym pliku**
            try (FileOutputStream fos = new FileOutputStream(excelFilePath)) {
                workbook.write(fos);
            }

            System.out.println("Mapa stron z poprawnymi obramowaniami została wygenerowana w arkuszu 'PageMap'.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Funkcja do odczytania mapy symboli i kolorów RGB z arkusza "Legend".
     */
    private static Map<String, int[]> readLegendColors(Workbook workbook) {
        Map<String, int[]> colorMap = new HashMap<>();
        Sheet legendSheet = workbook.getSheet("Legend");

        boolean isFirstRow = true;
        for (Row row : legendSheet) {
            if (row == null) continue;

            if (isFirstRow) {
                isFirstRow = false;
                continue;
            }

            Cell symbolCell = row.getCell(0);
            Cell redCell = row.getCell(6);
            Cell greenCell = row.getCell(7);
            Cell blueCell = row.getCell(8);

            if (symbolCell != null && redCell != null && greenCell != null && blueCell != null) {
                String symbol = symbolCell.getStringCellValue();
                int red = (int) redCell.getNumericCellValue();
                int green = (int) greenCell.getNumericCellValue();
                int blue = (int) blueCell.getNumericCellValue();
                colorMap.put(symbol, new int[]{red, green, blue});
            }
        }
        return colorMap;
    }

    /**
     * Dodaje odpowiednie obramowanie tylko do zewnętrznych krawędzi zakresu strony.
     */
    private static void addSelectiveBorders(Sheet sheet, int startRow, int endRow, int startCol, int endCol, short borderColor) {

        for (int r = startRow; r <= endRow; r++) {
            Row row = sheet.getRow(r);
            if (row == null) row = sheet.createRow(r);

            for (int c = startCol; c <= endCol; c++) {
                Cell cell = row.getCell(c);
                if (cell == null) cell = row.createCell(c);


                // Ustawienie obramowań tylko dla krawędzi
                if (r == startRow) {
                    cell.getCellStyle().setBorderTop(BorderStyle.DOUBLE); // Góra
                    cell.getCellStyle().setTopBorderColor(borderColor); // Góra
                }
                if (r == endRow) {
                    cell.getCellStyle().setBorderBottom(BorderStyle.THICK);
                    cell.getCellStyle().setBottomBorderColor(borderColor);// Dół
                }
                if (c == startCol) {
                    cell.getCellStyle().setBorderLeft(BorderStyle.THICK);
                    cell.getCellStyle().setLeftBorderColor(borderColor);// Lewa
                }
                if (c == endCol) {
                    cell.getCellStyle().setBorderRight(BorderStyle.THICK);
                    cell.getCellStyle().setRightBorderColor(borderColor);// Prawa
                }

            }
        }
    }
}
