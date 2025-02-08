package org.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class ImageToExcelWithLegend {

    public static void main(String[] args) {
        String imageName = "RW";
        String inputFormat = ".png";
        String imagePath = "C:\\Users\\Admin\\Desktop\\ImgToExcel\\" + imageName + inputFormat;
        String excelPath = "C:\\Users\\Admin\\Desktop\\ImgToExcel\\" + imageName + ".xlsx";

        try {
            BufferedImage image = ImageIO.read(new File(imagePath));
            Workbook workbook = new XSSFWorkbook();

            // Tworzenie arkusza dla obrazu
            Sheet imageSheet = workbook.createSheet(imageName);

            // Tworzenie mapy kolorów do liter oraz zliczanie wystąpień kolorów
            Map<Color, String> colorLegend = new HashMap<>();
            Map<Color, Integer> colorCount = new HashMap<>();
            char legendChar = 'A'; // Startujemy od litery A

            for (int row = 0; row < image.getHeight(); row++) {
                Row excelRow = imageSheet.createRow(row);
                for (int col = 0; col < image.getWidth(); col++) {
                    Color pixelColor = new Color(image.getRGB(col, row));
                    XSSFColor xssfColor = new XSSFColor(pixelColor, null);

                    // Dodaj kolor do legendy, jeśli jeszcze go tam nie ma
                    if (!colorLegend.containsKey(pixelColor)) {
                        colorLegend.put(pixelColor, String.valueOf(legendChar));
                        legendChar++;
                    }

                    // Zlicz wystąpienia kolorów
                    colorCount.put(pixelColor, colorCount.getOrDefault(pixelColor, 0) + 1);

                    // Utwórz komórkę
                    Cell cell = excelRow.createCell(col);
                    XSSFCellStyle style = (XSSFCellStyle) workbook.createCellStyle();
                    style.setFillForegroundColor(xssfColor);
                    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    cell.setCellStyle(style);

                    // Ustaw literę reprezentującą kolor w komórce
                    cell.setCellValue(colorLegend.get(pixelColor));
                }
            }

            // Dodanie arkusza z legendą
            Sheet legendSheet = workbook.createSheet("Legenda");

            // Posortowanie legendy według liter
            List<Map.Entry<Color, String>> sortedLegend = new ArrayList<>(colorLegend.entrySet());
            sortedLegend.sort(Map.Entry.comparingByValue());

            // Nagłówki w legendzie
            Row headerRow = legendSheet.createRow(0);
            headerRow.createCell(0).setCellValue("Litera");
            headerRow.createCell(1).setCellValue("Kolor");
            headerRow.createCell(2).setCellValue("Liczba wystąpień");
            headerRow.createCell(3).setCellValue("R");
            headerRow.createCell(4).setCellValue("G");
            headerRow.createCell(5).setCellValue("B");

            // Generowanie legendy w nowym arkuszu
            int rowIndex = 1;
            for (Map.Entry<Color, String> entry : sortedLegend) {
                Row row = legendSheet.createRow(rowIndex++);
                Color color = entry.getKey();

                // Kolumna z literą
                Cell letterCell = row.createCell(0);
                letterCell.setCellValue(entry.getValue());

                // Kolumna z kolorem
                Cell colorCell = row.createCell(1);
                XSSFCellStyle style = (XSSFCellStyle) workbook.createCellStyle();
                XSSFColor xssfColor = new XSSFColor(color, null);
                style.setFillForegroundColor(xssfColor);
                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                colorCell.setCellStyle(style);

                // Kolumna z liczbą wystąpień
                Cell countCell = row.createCell(2);
                countCell.setCellValue(colorCount.get(color));

                // Kolumny z wartościami RGB
                Cell redCell = row.createCell(3);
                redCell.setCellValue(color.getRed());

                Cell greenCell = row.createCell(4);
                greenCell.setCellValue(color.getGreen());

                Cell blueCell = row.createCell(5);
                blueCell.setCellValue(color.getBlue());
            }

            // Zapisanie pliku Excela
            try (FileOutputStream fileOut = new FileOutputStream(excelPath)) {
                workbook.write(fileOut);
            }

            workbook.close();
            System.out.println("Obraz z legendą został pomyślnie zapisany w pliku Excel!");

        } catch (IOException e) {
            System.err.println("Błąd podczas przetwarzania obrazu: " + e.getMessage());
        }
    }
}
