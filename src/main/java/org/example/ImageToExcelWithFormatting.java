package org.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ImageToExcelWithFormatting {

    public static void main(String[] args) {
        String imageName = "zuraw";
        String imagePath = "C:\\Users\\Admin\\Desktop\\ImgToExcel\\" + imageName + ".png";
        String excelPath = "C:\\Users\\Admin\\Desktop\\ImgToExcel\\" + imageName + "3.xlsx";

        try {
            BufferedImage image = ImageIO.read(new File(imagePath));

            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet(imageName);

            Map<Color, String> colorLegend = new HashMap<>();
            char letter = 'A';

            // Iteracja przez piksele obrazu
            for (int row = 0; row < image.getHeight(); row++) {
                Row excelRow = sheet.createRow(row);
                for (int col = 0; col < image.getWidth(); col++) {
                    Color pixelColor = new Color(image.getRGB(col, row));

                    if (!colorLegend.containsKey(pixelColor)) {
                        colorLegend.put(pixelColor, String.valueOf(letter));
                        letter++;
                    }

                    String colorCode = colorLegend.get(pixelColor);

                    Cell cell = excelRow.createCell(col);
                    cell.setCellValue(colorCode);

                    sheet.setColumnWidth(col, 256 * 3);
                }
            }

            sheet.setDefaultRowHeight((short) 300);

            // Dodaj reguły formatowania warunkowego
            XSSFSheetConditionalFormatting conditionalFormatting = sheet.getSheetConditionalFormatting();

            for (Map.Entry<Color, String> entry : colorLegend.entrySet()) {
                Color color = entry.getKey();
                String colorCode = entry.getValue();

                // Tworzenie obiektu XSSFColor przy użyciu tablicy RGB
                byte[] rgb = new byte[]{(byte) color.getRed(), (byte) color.getGreen(), (byte) color.getBlue()};
                XSSFColor xssfColor = new XSSFColor(rgb, new DefaultIndexedColorMap());

                XSSFConditionalFormattingRule rule = conditionalFormatting.createConditionalFormattingRule(
                        String.format("A1=\"%s\"", colorCode));
                PatternFormatting fill = rule.createPatternFormatting();
                fill.setFillForegroundColor(xssfColor);
                fill.setFillPattern(PatternFormatting.SOLID_FOREGROUND);

                CellRangeAddress[] regions = {new CellRangeAddress(0, image.getHeight() - 1, 0, image.getWidth() - 1)};
                conditionalFormatting.addConditionalFormatting(regions, rule);
            }

            try (FileOutputStream fileOut = new FileOutputStream(excelPath)) {
                workbook.write(fileOut);
            }
            workbook.close();

            System.out.println("Obraz został pomyślnie przeniesiony do Excela z formatowaniem warunkowym!");
        } catch (IOException e) {
            System.err.println("Błąd podczas przetwarzania obrazu: " + e.getMessage());
        }
    }
}

