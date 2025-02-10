package org.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BeadVisualizer {
    private static final String TEMPLATE_FOLDER = "C:\\Users\\Admin\\Desktop\\ImgToExcel\\DBTemplate\\";
    private static final String DEFAULT_IMAGE = "NoMatch.png";
    private static final int BEAD_IMG_PX_WIDTH = 53;
    private static final int BEAD_IMG_PX_HEIGHT = 66;
    private static final int HORIZONTAL_PX_SPACING = 2;
    private static final int VERTICAL_PX_SPACING = 1;
    private static final int CORNER_ARC_PX_WIDTH = 15;
    private static final int CORNER_ARC_PX_HEIGHT = 15;


    public static void main(String[] args) {
        String imageName = "fox2_M";
        String excelFilePath = "C:\\Users\\Admin\\Desktop\\ImgToExcel\\" + imageName + "_Pattern.xlsx";
        String patternSheetName = "Pattern";
        String legendSheetName = "Legend";
        String outputImagePath = "C:\\Users\\Admin\\Desktop\\ImgToExcel\\" + imageName + "_visual.png";

        try {
            Map<String, String> colorSymbolToBeadMap = loadBeadNumbersFromLegendSheet(excelFilePath, legendSheetName);
            String[][] beadNumbersPattern = loadPattern(excelFilePath, patternSheetName, colorSymbolToBeadMap);
            generateVisualization(beadNumbersPattern, outputImagePath);
            System.out.println("Wizualizacja zapisana jako: " + outputImagePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Wczytuje mapowanie symbol koloru -> numer koralika z istniejacej legendy pliku xlsx - arkusz 'Legend'
     */
    private static Map<String, String> loadBeadNumbersFromLegendSheet(String excelFilePath, String legendSheetName) throws IOException {

        Map<String, String> beadNumbersMap = new HashMap<>();
        try (InputStream excelFile = Files.newInputStream(Paths.get(excelFilePath));
             Workbook workbook = new XSSFWorkbook(excelFile)) {

            Sheet sheet = workbook.getSheet(legendSheetName);
            Iterator<Row> rowIterator = sheet.iterator();
            boolean isFirstRow = true;

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if (isFirstRow) {
                    isFirstRow = false; // pomijamy pierwszy wiersz z nagłówkami
                    continue;
                }
                Cell symbolCell = row.getCell(0); // Kolumna Symbol
                Cell numberCell = row.getCell(2); // Kolumna Number

                if (symbolCell != null && numberCell != null) {
                    String symbol = symbolCell.getStringCellValue();
                    String number = numberCell.getStringCellValue();
                    beadNumbersMap.put(symbol, number);
                }
            }

            // Wyświetlanie wartości mapy
            for (Map.Entry<String, String> entry : beadNumbersMap.entrySet()) {
                System.out.println("Symbol: " + entry.getKey() + ", Number: " + entry.getValue());
            }
        }
        return beadNumbersMap;
    }


    /**
     * Wczytuje schemat z Excela i mapuje symbole na numery koralików.
     */
    private static String[][] loadPattern(String excelFilePath, String sheetName, Map<String, String> symbolToBeadMap) throws IOException {
        try (InputStream excelFile = Files.newInputStream(Paths.get(excelFilePath));
             Workbook workbook = new XSSFWorkbook(excelFile)) {

            Sheet sheet = workbook.getSheet(sheetName);
            int rows = sheet.getPhysicalNumberOfRows();
            int cols = sheet.getRow(0).getPhysicalNumberOfCells();
            String[][] beadPattern = new String[rows][cols];

            for (int i = 0; i < rows; i++) {
                Row row = sheet.getRow(i);
                if (row == null || row.getCell(0) == null) break; // Jeśli pierwszy wiersz pusty -> koniec schematu

                for (int j = 0; j < cols; j++) {
                    Cell cell = row.getCell(j);
                    if (cell == null) break; // Jeśli pusta komórka -> koniec całego wiersza

                    String cellValue = cell.getStringCellValue();
                    beadPattern[i][j] = symbolToBeadMap.getOrDefault(cellValue, "NoMatch");
                }
            }
            return beadPattern;
        }
    }


    /**
     * Generuje obraz wizualizacji, łącząc odpowiednie obrazy koralików.
     */
    private static void generateVisualization(String[][] beadPattern, String outputImagePath) throws IOException {
        int tileWidth = BEAD_IMG_PX_WIDTH + HORIZONTAL_PX_SPACING;
        int tileHeight = BEAD_IMG_PX_HEIGHT + VERTICAL_PX_SPACING;

        int rows = beadPattern.length;
        int cols = beadPattern[0].length;

        BufferedImage outputImage = new BufferedImage(cols * tileWidth, rows * tileHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = outputImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                String beadNumber = beadPattern[i][j];
                BufferedImage beadImage = roundCorners(loadBeadImage(beadNumber), CORNER_ARC_PX_WIDTH, CORNER_ARC_PX_HEIGHT);

                if (beadImage != null) {
                    // Tworzymy przezroczyste tło
                    BufferedImage transparentBackground = new BufferedImage(tileWidth, tileHeight, BufferedImage.TYPE_INT_ARGB);
                    Graphics2D g = transparentBackground.createGraphics();
                    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                    // Wstawiamy koralik w lewym górnym rogu tła
                    g.drawImage(beadImage, 0, 0, null);
                    g.dispose();

                    // Rysujemy gotowy element na finalnym obrazie
                    g2d.drawImage(transparentBackground, j * tileWidth, i * tileHeight, null);
                }
            }
        }

        g2d.dispose();
        ImageIO.write(outputImage, "PNG", new File(outputImagePath));
    }

    /**
     * Wczytuje obraz koralika na podstawie jego numeru lub używa domyślnego.
     */
    private static BufferedImage loadBeadImage(String beadNumber) {
        String imagePath = TEMPLATE_FOLDER + beadNumber + ".png";
        File imageFile = new File(imagePath);

        if (!imageFile.exists()) {
            imagePath = TEMPLATE_FOLDER + DEFAULT_IMAGE; // Użyj NoMatch.png, jeśli brak dopasowania
        }

        try {
            return ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static BufferedImage roundCorners(BufferedImage image, int arcWidth, int arcHeight) {
        BufferedImage rounded = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = rounded.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Tworzymy przezroczystą maskę z zaokrąglonymi rogami
        g2.setComposite(AlphaComposite.Clear);
        g2.fillRect(0, 0, image.getWidth(), image.getHeight());

        g2.setComposite(AlphaComposite.Src);
        g2.setColor(Color.BLACK);
        g2.fillRoundRect(0, 0, image.getWidth(), image.getHeight(), arcWidth, arcHeight);

        g2.setComposite(AlphaComposite.SrcAtop);
        g2.drawImage(image, 0, 0, null);

        g2.dispose();

        return rounded;
    }
}

