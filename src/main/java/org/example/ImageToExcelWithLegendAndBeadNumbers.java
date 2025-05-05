package org.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
import org.apache.commons.csv.*;

public class ImageToExcelWithLegendAndBeadNumbers {

    public static void main(String[] args) {

        String imagePath = Config.getMiyukiColorsImgPath();
        String excelPath = Config.getXlsxPatternFilePath();
        String paletteCsvPath = Config.getAbsoluteCsvPalettePath(); // Ścieżka do pliku CSV z paletą kolorów

        try {
            BufferedImage image = ImageIO.read(new File(imagePath));
            XSSFWorkbook workbook = new XSSFWorkbook();

            // Wczytanie palety kolorów z CSV
            Map<String, String> beadNumbers = loadBeadNumbersFromCsv(paletteCsvPath);

            // Wczytanie nazw kolorów z CSV
            Map<String, String> beadNames = loadBeadNamesFromCsv(paletteCsvPath);

            // Tworzenie arkusza dla obrazu
            XSSFSheet patternSheet = workbook.createSheet("Pattern");

            // Font for default text
            Font mainFont = workbook.createFont();
            mainFont.setFontName("Montserrat");
            mainFont.setFontHeightInPoints((short) 10); // ustawiamy rozmiar czcionki
            mainFont.setBold(false); // ustawiamy, żeby czcionka nie była pogrubiona

            // Font for headers
            Font headerFont = workbook.createFont();
            headerFont.setFontName("Montserrat SemiBold");
            headerFont.setFontHeightInPoints((short) 10); // ustawiamy rozmiar czcionki
            headerFont.setBold(true); // ustawiamy, żeby czcionka była pogrubiona

            // Mapa kolorów do liter oraz ich liczby wystąpień
            Map<Color, String> colorLegend = new HashMap<>();
            Map<Color, Integer> colorCount = new HashMap<>();
            char legendChar = 'A';

            // Wypełnianie schematu symbolami z uwzględnieniem numeracji
            for (int row = 0; row <= image.getHeight(); row++) {
                Row excelRow = patternSheet.createRow(row);

                for (int col = 0; col <= image.getWidth(); col++) {
                    Cell cell = excelRow.createCell(col);
                    XSSFCellStyle cellStyle = workbook.createCellStyle();
                    setBorderedAndCenteredStyle(cellStyle);

                    if (row == 0 && col == 0) {
                        // Pusta lewa górna komórka
                        cell.setCellValue("");
                    } else if (row == 0) {
                        // Nagłówki kolumn (1, 2, 3, ...)
                        cell.setCellValue(col);
                        cell.setCellStyle(cellStyle);
                    } else if (col == 0) {
                        // Nagłówki wierszy (1, 2, 3, ...)
                        cell.setCellValue(row);
                        cell.setCellStyle(cellStyle);
                    } else {
                        Color pixelColor = new Color(image.getRGB(col - 1, row - 1));

                        if (!colorLegend.containsKey(pixelColor)) {
                            colorLegend.put(pixelColor, String.valueOf(legendChar));
                            legendChar++;
                        }

                        // Zliczanie wystąpień kolorów
                        colorCount.put(pixelColor, colorCount.getOrDefault(pixelColor, 0) + 1);

                        String colorCode = colorLegend.get(pixelColor);
                        cell.setCellValue(colorCode);
                        cell.setCellStyle(cellStyle);
                    }
                }
            }

            // Ustawienia szerokości kolumn i wysokości wierszy
            for (int col = 0; col <= image.getWidth(); col++) {
                patternSheet.setColumnWidth(col, 256 * 3);
            }
            patternSheet.setDefaultRowHeight((short) 370);

            // Dodanie reguł formatowania warunkowego
            XSSFSheetConditionalFormatting conditionalFormatting = patternSheet.getSheetConditionalFormatting();

            for (Map.Entry<Color, String> entry : colorLegend.entrySet()) {
                Color color = entry.getKey();
                String colorCode = entry.getValue();

                byte[] rgb = new byte[]{(byte) color.getRed(), (byte) color.getGreen(), (byte) color.getBlue()};
                XSSFColor xssfColor = new XSSFColor(rgb, new DefaultIndexedColorMap());

                XSSFConditionalFormattingRule rule = conditionalFormatting.createConditionalFormattingRule(
                        String.format("A1=\"%s\"", colorCode));
                PatternFormatting fill = rule.createPatternFormatting();
                fill.setFillForegroundColor(xssfColor);
                fill.setFillPattern(PatternFormatting.SOLID_FOREGROUND);

                CellRangeAddress[] regions = {new CellRangeAddress(0, image.getHeight(), 0, image.getWidth())};
                conditionalFormatting.addConditionalFormatting(regions, rule);
            }

            // Tworzenie arkusza z legendą
            XSSFSheet legendSheet = workbook.createSheet("Legend");

            // Posortowanie legendy alfabetycznie
            List<Map.Entry<Color, String>> sortedLegend = new ArrayList<>(colorLegend.entrySet());
            sortedLegend.sort(Map.Entry.comparingByValue());

            // Nagłówki w legendzie
            Row headerRow = legendSheet.createRow(0);
            headerRow.createCell(0).setCellValue("Symbol");
            headerRow.createCell(1).setCellValue("Color");
            headerRow.createCell(2).setCellValue("Number");
            headerRow.createCell(3).setCellValue("Color Name");
            headerRow.createCell(4).setCellValue("QTY");
            headerRow.createCell(5).setCellValue("Approx. weight [g]");
            headerRow.createCell(6).setCellValue("R");
            headerRow.createCell(7).setCellValue("G");
            headerRow.createCell(8).setCellValue("B");

            XSSFCellStyle headerCellStyle = workbook.createCellStyle();
            setBorderedAndCenteredStyle(headerCellStyle);
            headerCellStyle.setFont(headerFont);

            headerRow.getCell(0).setCellStyle(headerCellStyle);
            headerRow.getCell(1).setCellStyle(headerCellStyle);
            headerRow.getCell(2).setCellStyle(headerCellStyle);
            headerRow.getCell(3).setCellStyle(headerCellStyle);
            headerRow.getCell(4).setCellStyle(headerCellStyle);
            headerRow.getCell(5).setCellStyle(headerCellStyle);

            // Generowanie legendy
            int rowIndex = 1;
            for (Map.Entry<Color, String> entry : sortedLegend) {
                Row row = legendSheet.createRow(rowIndex++);
                Color color = entry.getKey();

                // Kolumna z literą
                Cell letterCell = row.createCell(0);
                letterCell.setCellValue(entry.getValue());
                XSSFCellStyle letterCellStyle = workbook.createCellStyle();
                setBorderedAndCenteredStyle(letterCellStyle);
                letterCell.setCellStyle(letterCellStyle);

                // Kolumna z kolorem
                Cell colorCell = row.createCell(1);
                XSSFCellStyle colorCellStyle = workbook.createCellStyle();
                byte[] rgb = new byte[]{(byte) color.getRed(), (byte) color.getGreen(), (byte) color.getBlue()};
                XSSFColor xssfColor = new XSSFColor(rgb, new DefaultIndexedColorMap());
                colorCellStyle.setFillForegroundColor(xssfColor);
                colorCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                setBorderedAndCenteredStyle(colorCellStyle);
                colorCell.setCellStyle(colorCellStyle);

                // Kolumna z numerem koralika
                Cell beadNumberCell = row.createCell(2);
                String colorRGB = String.format("%s_%s_%s",color.getRed(),color.getGreen(),color.getBlue());
                System.out.println(colorRGB);
                beadNumberCell.setCellValue(beadNumbers.getOrDefault(colorRGB, "Brak numeru"));
                System.out.println(beadNumbers.get(colorRGB));
                XSSFCellStyle beadNumberCellStyle = workbook.createCellStyle();
                setBorderedAndCenteredStyle(beadNumberCellStyle);
                beadNumberCell.setCellStyle(beadNumberCellStyle);

                // Kolumna z nazwa koloru
                Cell colorNameCell = row.createCell(3);
                colorNameCell.setCellValue(beadNames.getOrDefault(colorRGB, "Brak koloru"));
                XSSFCellStyle colorNameCellStyle = workbook.createCellStyle();
                setBorderedAndCenteredStyle(colorNameCellStyle);
                colorNameCell.setCellStyle(colorNameCellStyle);

                // Kolumna z liczbą wystąpień
                Cell countCell = row.createCell(4);
                countCell.setCellValue(colorCount.get(color));
                XSSFCellStyle countCellStyle = workbook.createCellStyle();
                setBorderedAndCenteredStyle(countCellStyle);
                countCell.setCellStyle(countCellStyle);

                // Kolumna z wagą
                Cell weightCell = row.createCell(5);
                Integer colorQty = colorCount.get(color);
                Double weight = (double) (colorQty*0.005);
                DecimalFormat df = new DecimalFormat("#.##");
                String roundedWeight = df.format(weight);
                weightCell.setCellValue(roundedWeight);
                XSSFCellStyle weightCellStyle = workbook.createCellStyle();
                setBorderedAndCenteredStyle(weightCellStyle);
                weightCell.setCellStyle(weightCellStyle);

                // Kolumny z wartościami RGB
                row.createCell(6).setCellValue(color.getRed());
                row.createCell(7).setCellValue(color.getGreen());
                row.createCell(8).setCellValue(color.getBlue());

            }



            // Tworzymy styl
            CellStyle currentStyle;

            // Iteracja po wierszach w patternSheet
            for (int i = 0; i < patternSheet.getPhysicalNumberOfRows(); i++) {
                Row row = patternSheet.getRow(i);
                if (row != null) {  // Sprawdzamy, czy wiersz nie jest pusty
                    for (Cell cell : row) {
                        currentStyle = cell.getCellStyle();
                        currentStyle.setFont(mainFont);
                        cell.setCellStyle(currentStyle);
                    }
                }
            }

            // Iteracja po wierszach w legendSheet
            for (int i = 1; i < legendSheet.getPhysicalNumberOfRows(); i++) {
                Row row = legendSheet.getRow(i);
                if (row != null) {  // Sprawdzamy, czy wiersz nie jest pusty
                    for (Cell cell : row) {
                        currentStyle = cell.getCellStyle();
                        currentStyle.setFont(mainFont);
                        cell.setCellStyle(currentStyle);
                    }
                }
            }

            legendSheet.setColumnHidden(6,true);
            legendSheet.setColumnHidden(7,true);
            legendSheet.setColumnHidden(8,true);
            legendSheet.autoSizeColumn(3);
            legendSheet.autoSizeColumn(5);


            //Dodaj arkusz z wymiarami schematu
            Sheet dimSheet = workbook.createSheet("Size");

            Row rowHeader = dimSheet.createRow(0);
            rowHeader.createCell(0).setCellValue("Width");
            rowHeader.createCell(1).setCellValue("Height");

            Row rowValues = dimSheet.createRow(1);
            rowValues.createCell(0).setCellValue(image.getWidth());
            rowValues.createCell(1).setCellValue(image.getHeight());

            // Zapisanie pliku Excela
            try (FileOutputStream fileOut = new FileOutputStream(excelPath)) {
                workbook.write(fileOut);
            }

            workbook.close();
            System.out.println("Obraz z legendą, numerami koralików i regułami formatowania został zapisany!");
        } catch (IOException e) {
            System.err.println("Błąd podczas przetwarzania obrazu: " + e.getMessage());
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

    private static Map<String, String> loadBeadNamesFromCsv(String csvPath) throws IOException {
        Map<String, String> beadNames = new HashMap<>();
        try (Reader reader = new FileReader(csvPath)) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
            for (CSVRecord record : records) {
                String red = record.get("R");
                String green = record.get("G");
                String blue = record.get("B");
                String rgbKey = red + "_" + green + "_" + blue;
                String colorName = record.get("Name");
                beadNames.put(rgbKey, colorName);
            }
        }
        return beadNames;
    }

    static void setBorderedAndCenteredStyle(XSSFCellStyle style) {
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
    }
}

