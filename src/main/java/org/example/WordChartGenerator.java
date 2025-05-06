package org.example;

import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class WordChartGenerator {

    static {
        ZipSecureFile.setMinInflateRatio(0.003); // Ustawienie niższego limitu
    }

    public static void main(String[] args) {
        // Podaj ścieżkę do pliku Excela
        String filePath = Config.getXlsxPatternFilePath();

        // Generuj diagram słowny na podstawie pliku Excela
        generateWordChartFromFile(filePath);
    }

    public static void generateWordChartFromFile(String filePath) {
        try (FileInputStream fis = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            int patternWidth = 0;
            int patternHeight = 0;

            //odczytaj wymiary schematu
            Sheet sizeSheet = workbook.getSheet(Config.getSizeSheetName());
            if (sizeSheet != null) {
                Row row = sizeSheet.getRow(1); // Dane są w drugim wierszu (indeks 1)
                if (row != null) {
                    Cell widthCell = row.getCell(0);
                    Cell heightCell = row.getCell(1);

                    patternWidth = (int) widthCell.getNumericCellValue();
                    patternHeight = (int) heightCell.getNumericCellValue();
                }
            } else {
                System.out.println("Brak arkusza 'Size'");
            }

            // Znajdź arkusz o nazwie "Pattern"
            XSSFSheet patternSheet = workbook.getSheet(Config.getPatternSheetName());
            if (patternSheet == null) {
                throw new IllegalArgumentException("Nie znaleziono arkusza o nazwie: Pattern");
            }

            // Wczytaj schemat z arkusza "Pattern"
            String[][] schema = readSchemaFromSheet(patternSheet, patternWidth, patternHeight);

            // Usunięcie istniejącego arkusza "Word_Chart" (jeśli istnieje)
            int wchartSheetIndex = workbook.getSheetIndex(Config.getWordChartSheetName());
            if (wchartSheetIndex != -1) {
                workbook.removeSheetAt(wchartSheetIndex);
            }

            // Utwórz nowy arkusz na diagram słowny
            XSSFSheet wordChartSheet = workbook.createSheet(Config.getWordChartSheetName());

            // Nagłówki
            Row headerRow = wordChartSheet.createRow(0);
            headerRow.createCell(0).setCellValue("Row No.");
            headerRow.createCell(1).setCellValue("Word Chart");

            // Czcionka naglowkow
            Font headerFont = workbook.createFont();
            headerFont.setFontName("Montserrat SemiBold");
            headerFont.setFontHeightInPoints((short) 10); // ustawiamy rozmiar czcionki
            headerFont.setBold(false); // ustawiamy, żeby czcionka była pogrubiona

            XSSFCellStyle headerCellStyle = workbook.createCellStyle();
            setBorderedAndCenteredStyle(headerCellStyle);
            headerCellStyle.setFont(headerFont);

            headerRow.getCell(0).setCellStyle(headerCellStyle);
            headerRow.getCell(1).setCellStyle(headerCellStyle);

            // Generowanie diagramów dla każdego wiersza
            for (int i = 0; i < schema.length; i++) {
                String wordChart = generateRowChart(schema[i]); // Diagram dla wiersza
                Row row = wordChartSheet.createRow(i + 1); // Tworzenie nowego wiersza w arkuszu
                row.createCell(0).setCellValue("Row #" + (i + 1)); // Numer wiersza
                row.createCell(1).setCellValue(wordChart.substring(0, wordChart.length()-1)); // Diagram
            }

            // Czcionka diagramu
            Font mainFont = workbook.createFont();
            mainFont.setFontName("Montserrat");
            mainFont.setFontHeightInPoints((short) 10); // ustawiamy rozmiar czcionki
            mainFont.setBold(false); // ustawiamy, żeby czcionka była pogrubiona

            // Tworzymy styl
            XSSFCellStyle wchartCellStyle = workbook.createCellStyle();
            setBorderedAndLeftStyle(wchartCellStyle);
            wchartCellStyle.setFont(mainFont); // przypisujemy czcionkę do stylu
            wchartCellStyle.setWrapText(true);

            // Iteracja po wierszach w wchartSheet
            for (int i = 1; i < wordChartSheet.getPhysicalNumberOfRows(); i++) {
                Row row = wordChartSheet.getRow(i);
                if (row != null) {  // Sprawdzamy, czy wiersz nie jest pusty
                    for (Cell cell : row) {
                        cell.setCellStyle(wchartCellStyle);
                    }
                }
            }

            // Dopasowanie szerokości kolumn do zawartości
            wordChartSheet.autoSizeColumn(0);
            wordChartSheet.setColumnWidth(1, 256*75);

            // Zapisz zmiany w pliku
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                workbook.write(fos);
            }

            System.out.println("Diagram słowny został dodany do pliku: " + filePath);

        } catch (IOException e) {
            System.err.println("Błąd podczas przetwarzania pliku: " + e.getMessage());
        }
    }

    // Metoda do odczytania schematu z arkusza
    private static String[][] readSchemaFromSheet(Sheet sheet, int patternWidth, int patternHeight) {
        String[][] schema = new String[patternHeight][]; // Macierz schematu

        for (int i = 0; i < patternHeight; i++) {
            Row row = sheet.getRow(i+1);
            if (row == null) continue;

            schema[i] = new String[patternWidth];

            for (int j = 0; j < patternWidth; j++) {
                Cell cell = row.getCell(j+1);
                if (cell != null && cell.getCellType() == CellType.STRING) {
                    schema[i][j] = cell.getStringCellValue();
                } else {
                    schema[i][j] = null; // Puste komórki
                }
            }
        }

        return schema;
    }

    // Metoda do generowania diagramu dla jednego wiersza
    private static String generateRowChart(String[] row) {
        if (row == null || row.length == 0) {
            return ""; // Pusty wiersz
        }

        StringBuilder result = new StringBuilder();
        int count = 1;

        for (int i = 1; i <= row.length; i++) {
            if (row[i - 1] == null) break; // Koniec schematu w wierszu

            // Jeśli zmiana litery lub koniec wiersza
            if (i == row.length || row[i] == null || !row[i].equals(row[i - 1])) {
                result.append(row[i - 1]);
                if (count > 1) {
                    result.append("(").append(count).append(")");
                }
                result.append(", "); // Separator
                count = 1;
            } else {
                count++;
            }
        }

        return result.toString().trim(); // Usuwanie nadmiarowych spacji
    }

    static void setBorderedAndCenteredStyle(XSSFCellStyle style) {
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
    }

    static void setBorderedAndLeftStyle(XSSFCellStyle style) {
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
    }

}

