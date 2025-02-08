package org.example;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import java.awt.Color;
import java.io.*;
import java.util.*;

public class LegendGenerator {

    public static void main(String[] args) {

        String imagePatternFileName = "ms3_M_Pattern";
        String filePath = "C:\\Users\\Admin\\Desktop\\ImgToExcel\\" + imagePatternFileName + ".xlsx";
        String paletteCsvPath = "C:\\Users\\Admin\\Desktop\\Palety\\CSV\\MiyukiFullCSV.csv";
        Map<String, String> beadNumbers = null;
        try {
            beadNumbers = loadBeadNumbersFromCsv(paletteCsvPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        generateLegend(filePath, beadNumbers);

    }

    public static void generateLegend(String excelFilePath, Map<String, String> beadNumbers) {
        try (FileInputStream fileInputStream = new FileInputStream(excelFilePath);
             XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream)) {

            // Pobranie arkusza "Pattern"
            XSSFSheet patternSheet = workbook.getSheet("Pattern");
            if (patternSheet == null) {
                throw new IllegalArgumentException("Brak arkusza 'Pattern' w podanym pliku.");
            }

            // Usunięcie istniejącego arkusza "Legend" (jeśli istnieje)
            int legendSheetIndex = workbook.getSheetIndex("Legend");
            if (legendSheetIndex != -1) {
                workbook.removeSheetAt(legendSheetIndex);
            }

            // Przygotowanie struktur do generowania legendy
            Map<Color, String> colorLegend = new HashMap<>();
            Map<Color, Integer> colorCount = new HashMap<>();

            for (Row row : patternSheet) {
                for (Cell cell : row) {
                    String symbol = cell.getStringCellValue();
                    if (symbol != null && !symbol.isEmpty()) {
                        // Odczytanie koloru komórki
                        CellStyle cellStyle = cell.getCellStyle();
                        XSSFColor xssfColor = (XSSFColor) cellStyle.getFillForegroundColorColor();
                        if (xssfColor != null) {
                            Color color = new Color(
                                    xssfColor.getRGB()[0] & 0xFF,
                                    xssfColor.getRGB()[1] & 0xFF,
                                    xssfColor.getRGB()[2] & 0xFF
                            );

                            // Aktualizacja map legendy i liczby wystąpień
                            colorLegend.putIfAbsent(color, symbol);
                            colorCount.put(color, colorCount.getOrDefault(color, 0) + 1);
                        }
                    }
                }
            }

            // Tworzenie nowego arkusza z legendą
            XSSFSheet legendSheet = workbook.createSheet("Legend");

            // Posortowanie legendy alfabetycznie
            List<Map.Entry<Color, String>> sortedLegend = new ArrayList<>(colorLegend.entrySet());
            sortedLegend.sort(Map.Entry.comparingByValue());

            // Nagłówki w legendzie
            Row headerRow = legendSheet.createRow(0);
            headerRow.createCell(0).setCellValue("Symbol");
            headerRow.createCell(1).setCellValue("Color");
            headerRow.createCell(2).setCellValue("Number");
            headerRow.createCell(3).setCellValue("QTY");
            headerRow.createCell(4).setCellValue("R");
            headerRow.createCell(5).setCellValue("G");
            headerRow.createCell(6).setCellValue("B");

            // Generowanie wierszy legendy
            int rowIndex = 1;
            for (Map.Entry<Color, String> entry : sortedLegend) {
                Row row = legendSheet.createRow(rowIndex++);
                Color color = entry.getKey();

                // Kolumna z literą
                Cell letterCell = row.createCell(0);
                letterCell.setCellValue(entry.getValue());

                // Kolumna z kolorem
                Cell colorCell = row.createCell(1);
                XSSFCellStyle style = workbook.createCellStyle();
                XSSFColor xssfColor = new XSSFColor(
                        new byte[]{(byte) color.getRed(), (byte) color.getGreen(), (byte) color.getBlue()},
                        new DefaultIndexedColorMap());
                style.setFillForegroundColor(xssfColor);
                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                colorCell.setCellStyle(style);

                // Kolumna z numerem koralika
                Cell beadNumberCell = row.createCell(2);
                String colorRGB = String.format("%s_%s_%s", color.getRed(), color.getGreen(), color.getBlue());
                beadNumberCell.setCellValue(beadNumbers.getOrDefault(colorRGB, "Brak numeru"));

                // Kolumna z liczbą wystąpień
                Cell countCell = row.createCell(3);
                countCell.setCellValue(colorCount.get(color));

                // Kolumny z wartościami RGB
                row.createCell(4).setCellValue(color.getRed());
                row.createCell(5).setCellValue(color.getGreen());
                row.createCell(6).setCellValue(color.getBlue());
            }

            // Zapisanie pliku
            try (FileOutputStream fileOut = new FileOutputStream(excelFilePath)) {
                workbook.write(fileOut);
            }

            System.out.println("Legenda została wygenerowana i zapisana w pliku.");

        } catch (IOException e) {
            System.err.println("Błąd podczas generowania legendy: " + e.getMessage());
        }
    }

    private static Map<String, String> loadBeadNumbersFromCsv(String csvPath) throws IOException {
        Map<String, String> beadNumbers = new HashMap<>();
        try (Reader reader = new FileReader(csvPath)) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
            for (CSVRecord record : records) {
                String red = record.get("R");
                String green = record.get("G");
                String blue = record.get("B");
                String beadNumber = record.get("Number");
                String rgbKey = red + "_" + green + "_" + blue;
                beadNumbers.put(rgbKey, beadNumber);
            }
        }
        return beadNumbers;
    }
}
