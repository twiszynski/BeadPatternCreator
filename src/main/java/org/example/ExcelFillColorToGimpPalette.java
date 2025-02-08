package org.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ExcelFillColorToGimpPalette {
    public static void main(String[] args) {

        String fileName = "PaletaMiyuki";
        String excelFilePath = "C:\\Users\\Admin\\Desktop\\PaletyMiyuki\\" + fileName + ".xlsx"; // Ścieżka do pliku Excel
        String gimpPalettePath = "C:\\Users\\Admin\\Desktop\\PaletyMiyuki\\" + fileName + ".gpl"; // Ścieżka wyjściowa dla pliku .gpl


        try (Workbook workbook = new XSSFWorkbook(Files.newInputStream(Paths.get(excelFilePath)));
             BufferedWriter writer = new BufferedWriter(new FileWriter(gimpPalettePath))) {

            // Otwórz arkusz Excela
            Sheet sheet = workbook.getSheetAt(0);

            // Napisz nagłówki palety GIMP
            writer.write("GIMP Palette\n");
            writer.write("Name: My Miyuki Palette\n");
            writer.write("Columns: 0\n");
            writer.write("#\n");

            // Przetwarzaj wiersze Excela
            for (Row row : sheet) {
                Cell numberCell = row.getCell(0); // Kolumna z numerem koralików
                Cell colorCell = row.getCell(1);  // Kolumna z wypełnieniem koloru

                if (numberCell == null || colorCell == null) continue;

                String number = numberCell.getStringCellValue();
                Color fillColor = getFillColor(colorCell);

                if (fillColor != null) {
                    // Napisz linię do pliku .gpl w formacie RGB
                    writer.write(String.format("%d %d %d %s\n",
                            fillColor.getRed(),
                            fillColor.getGreen(),
                            fillColor.getBlue(),
                            number));
                }
            }

            System.out.println("Plik palety został zapisany: " + gimpPalettePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Pobiera kolor wypełnienia komórki w formacie RGB.
     */
    private static Color getFillColor(Cell cell) {
        CellStyle style = cell.getCellStyle();
        if (style == null) return null;

        Color color = null;
        if (style.getFillForegroundColorColor() instanceof XSSFColor) {
            XSSFColor xssfColor = (XSSFColor) style.getFillForegroundColorColor();
            if (xssfColor != null) {
                byte[] rgb = xssfColor.getRGB();
                if (rgb != null) {
                    return new Color(rgb[0] & 0xFF, rgb[1] & 0xFF, rgb[2] & 0xFF);
                }
            }
        }
        return null;
    }

    /**
     * Klasa pomocnicza do przechowywania kolorów w formacie RGB.
     */
    private static class Color {
        private final int red;
        private final int green;
        private final int blue;

        public Color(int red, int green, int blue) {
            this.red = red;
            this.green = green;
            this.blue = blue;
        }

        public int getRed() {
            return red;
        }

        public int getGreen() {
            return green;
        }

        public int getBlue() {
            return blue;
        }
    }
}

