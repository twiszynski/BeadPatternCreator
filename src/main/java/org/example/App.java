package org.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class App
{
    public static void main(String[] args) {

        String imageName = "zuraw";
        // Ścieżka do obrazu wejściowego
        String imagePath = "C:\\Users\\Admin\\Desktop\\ImgToExcel\\" + imageName + ".png"; // Zmień na rzeczywistą ścieżkę do obrazu
        // Ścieżka do pliku wynikowego Excel
        String excelPath = "C:\\Users\\Admin\\Desktop\\ImgToExcel\\" + imageName + ".xlsx"; // Ścieżka, gdzie zostanie zapisany plik

        try {
            // Wczytaj obraz
            BufferedImage image = ImageIO.read(new File(imagePath));

            // Utwórz nowy arkusz Excela
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("imageName");



            // Iteracja przez piksele obrazu
            for (int row = 0; row < image.getHeight(); row++) {
                Row excelRow = sheet.createRow(row);
                for (int col = 0; col < image.getWidth(); col++) {
                    // Pobierz kolor piksela
                    Color pixelColor = new Color(image.getRGB(col, row));
                    XSSFColor xssfColor = new XSSFColor(pixelColor, null);

                    // Utwórz komórkę
                    Cell cell = excelRow.createCell(col);

                    sheet.setColumnWidth(col, 256 * 3); // Szerokość kolumny

                    // Ustaw styl komórki z kolorem tła
                    XSSFCellStyle style = (XSSFCellStyle) workbook.createCellStyle();
                    style.setFillForegroundColor(xssfColor);
                    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    cell.setCellStyle(style);
                }
            }

            // Ustaw wysokość wierszy (na końcu)
            sheet.setDefaultRowHeight((short) 300); // Wysokość wiersza

            // Zapisz plik Excela
            FileOutputStream fileOut = new FileOutputStream(excelPath);
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();

            System.out.println("Obraz został pomyślnie przeniesiony do Excela!");

        } catch (IOException e) {
            System.err.println("Błąd podczas przetwarzania obrazu: " + e.getMessage());
        }
    }
}
