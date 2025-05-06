package org.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExcelPatternToImage {

    public static void main(String[] args) {

        String excelFilePath = Config.getXlsxPatternFilePath();
        String outputImagePath = Config.getAdjustedImgPath();

        convertExcelToImage(excelFilePath, outputImagePath);
    }


    public static void convertExcelToImage(String excelFilePath, String outputImagePath) {
        try (FileInputStream fis = new FileInputStream(excelFilePath)) {
            // Wczytanie pliku Excel
            Workbook workbook = new XSSFWorkbook(fis);

            // Wczytanie mapy symboli i kolorów RGB z arkusza "Legend"
            Map<String, int[]> colorMap = readLegendColors(workbook);

            int patternRows = 0;
            int patternColumns = 0;

            //Pobranie liczby wierszy i kolumn
            Sheet sizeSheet = workbook.getSheet(Config.getSizeSheetName());
            if (sizeSheet != null) {
                Row row = sizeSheet.getRow(1); // Dane są w drugim wierszu (indeks 1)
                if (row != null) {
                    Cell widthCell = row.getCell(0);
                    Cell heightCell = row.getCell(1);

                    patternColumns = (int) widthCell.getNumericCellValue();
                    patternRows = (int) heightCell.getNumericCellValue();
                }
            } else {
                System.out.println("Brak arkusza 'Size'");
            }

            // Pobranie arkusza "Pattern" (schemat)
            Sheet patternSheet = workbook.getSheet(Config.getPatternSheetName());


            // Tworzenie obrazu o wymiarach na podstawie schematu
            BufferedImage image = new BufferedImage(patternColumns, patternRows, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = image.createGraphics();

            for (int r = 0; r < patternRows; r++) {
                Row row = patternSheet.getRow(r+1);
                if (row == null) continue; // Pomijanie pustych wierszy

                for (int c = 0; c < patternColumns; c++) {
                    Cell cell = row.getCell(c+1);

                    // Odczytanie symbolu z komórki
                    String symbol = (cell != null) ? cell.getStringCellValue().toUpperCase() : "";
                    int[] rgb = colorMap.getOrDefault(symbol, new int[]{255, 255, 255}); // Domyślnie biały kolor

                    // Ustawienie koloru piksela
                    Color color = new Color(rgb[0], rgb[1], rgb[2]);
                    graphics.setColor(color);
                    graphics.fillRect(c, r, 1, 1); // Każda komórka to 1x1 piksel
                }
            }

            graphics.dispose();

            // Zapisanie obrazu do pliku .png
            ImageIO.write(image, "png", new File(outputImagePath));
            System.out.println("Obraz został zapisany jako: " + outputImagePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Funkcja do odczytania mapy symboli i kolorów RGB z arkusza "Legend".
     */
    private static Map<String, int[]> readLegendColors(Workbook workbook) {
        Map<String, int[]> colorMap = new HashMap<>();
        Sheet legendSheet = workbook.getSheet(Config.getLegendSheetName());


        boolean isFirstRow = true;
        for (Row row : legendSheet) {
            if (row == null) continue;

            if (isFirstRow) {
                isFirstRow = false; // Pominięcie pierwszego wiersza
                continue;
            }

            // Symbol w pierwszej kolumnie
            Cell symbolCell = row.getCell(0);
            // Wartości RGB w kolumnach 4, 5, 6
            Cell redCell = row.getCell(6);
            Cell greenCell = row.getCell(7);
            Cell blueCell = row.getCell(8);

            if (symbolCell != null && redCell != null && greenCell != null && blueCell != null) {
                String symbol = symbolCell.getStringCellValue();
                int red = (int) redCell.getNumericCellValue();
                int green = (int) greenCell.getNumericCellValue();
                int blue = (int) blueCell.getNumericCellValue();

                // Dodanie symbolu i kolorów RGB do mapy
                colorMap.put(symbol, new int[]{red, green, blue});
            }
        }
        return colorMap;
    }
}
