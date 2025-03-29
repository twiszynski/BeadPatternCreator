package org.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class WordChartGenerator {

    public static void main(String[] args) {
        // Podaj ścieżkę do pliku Excela
        String imagePatternFileName = "tulip_Pattern";
        String filePath = "C:\\Users\\Admin\\Desktop\\ImgToExcel\\" + imagePatternFileName + ".xlsx";

        // Generuj diagram słowny na podstawie pliku Excela
        generateWordChartFromFile(filePath);
    }

    public static void generateWordChartFromFile(String filePath) {
        try (FileInputStream fis = new FileInputStream(new File(filePath));
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            // Znajdź arkusz o nazwie "Pattern"
            XSSFSheet patternSheet = workbook.getSheet("Pattern");
            if (patternSheet == null) {
                throw new IllegalArgumentException("Nie znaleziono arkusza o nazwie: Pattern");
            }

            // Wczytaj schemat z arkusza "Pattern"
            String[][] schema = readSchemaFromSheet(patternSheet);

            // Utwórz nowy arkusz na diagram słowny
            XSSFSheet wordChartSheet = workbook.createSheet("Word Chart");

            // Nagłówki
            Row headerRow = wordChartSheet.createRow(0);
            headerRow.createCell(0).setCellValue("Row No.");
            headerRow.createCell(1).setCellValue("Word Chart");

            // Generowanie diagramów dla każdego wiersza
            for (int i = 0; i < schema.length; i++) {
                String wordChart = generateRowChart(schema[i]); // Diagram dla wiersza
                Row row = wordChartSheet.createRow(i + 1); // Tworzenie nowego wiersza w arkuszu
                row.createCell(0).setCellValue("Row #" + (i + 1)); // Numer wiersza
                row.createCell(1).setCellValue(wordChart); // Diagram
            }

            // Tworzymy obiekt czcionki
            Font font = workbook.createFont();
            font.setFontName("Calibri");
            font.setFontHeightInPoints((short) 10); // ustawiamy rozmiar czcionki
            font.setBold(false); // ustawiamy, żeby czcionka była pogrubiona

            // Tworzymy styl
            CellStyle style = workbook.createCellStyle();
            style.setFont(font); // przypisujemy czcionkę do stylu

            // Iteracja po wierszach w imageSheet
            for (int i = 0; i < wordChartSheet.getPhysicalNumberOfRows(); i++) {
                Row row = wordChartSheet.getRow(i);
                if (row != null) {  // Sprawdzamy, czy wiersz nie jest pusty
                    for (Cell cell : row) {
                        cell.setCellStyle(style);
                    }
                }
            }

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
    private static String[][] readSchemaFromSheet(Sheet sheet) {
        int rowCount = sheet.getLastRowNum() + 1; // Liczba wierszy w arkuszu
        String[][] schema = new String[rowCount][]; // Macierz schematu

        for (int i = 0; i < rowCount; i++) {
            Row row = sheet.getRow(i);
            if (row == null) continue;

            int colCount = row.getLastCellNum(); // Liczba kolumn w danym wierszu
            schema[i] = new String[colCount];

            for (int j = 0; j < colCount; j++) {
                Cell cell = row.getCell(j);
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

}

