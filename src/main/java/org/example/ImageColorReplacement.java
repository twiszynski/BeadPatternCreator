package org.example;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImageColorReplacement {

    public static void main(String[] args) {

        String csvFilePath = Config.getAbsoluteCsvPalettePath();
        String inputImagePath = Config.getBaseColorsImgPath();
        String outputImagePath = Config.getMiyukiColorsImgPath();

        try {
            // 1. Wczytaj paletę z pliku CSV
            List<PaletteColor> palette = loadPaletteFromCsv(csvFilePath);

            // 2. Wczytaj obraz wejściowy
            BufferedImage image = ImageIO.read(new File(inputImagePath));

            // 3. Podmień kolory obrazu na najbliższe z palety
            BufferedImage processedImage = replaceColors(image, palette);

            // 4. Zapisz przetworzony obraz
            ImageIO.write(processedImage, "png", new File(outputImagePath));

            System.out.println("Obraz został przetworzony i zapisany jako: " + outputImagePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Klasa do przechowywania informacji o kolorach z CSV
    static class PaletteColor {
        String number; // Numer koralika
        int r, g, b;   // Składowe RGB
        String type;   // Typ (np. multi)
        String comments; // Komentarz

        PaletteColor(String number, int r, int g, int b, String type, String comments) {
            this.number = number;
            this.r = r;
            this.g = g;
            this.b = b;
            this.type = type;
            this.comments = comments;
        }

        // Obliczanie odległości między kolorami w przestrzeni RGB
        public double compareByRGB(Color color) {
            return Math.sqrt(Math.pow(this.r - color.getRed(), 2) +
                    Math.pow(this.g - color.getGreen(), 2) +
                    Math.pow(this.b - color.getBlue(), 2));
        }

        // Obliczanie delty między kolorami na podstawie CIEDE2000 i LAB
        public double compareByLAB(Color color) {
            double[] lab1 = ColorUtils.rgbToLab(this.r, this.g, this.b);
            double[] lab2 = ColorUtils.rgbToLab(color.getRed(), color.getGreen(), color.getBlue());
            return ColorUtils.calculateCIEDE2000(lab1, lab2);
        }

        public Color toColor() {
            return new Color(r, g, b);
        }
    }


    // Wczytywanie palety z pliku CSV
    private static List<PaletteColor> loadPaletteFromCsv(String csvFilePath) throws IOException {
        List<PaletteColor> palette = new ArrayList<>();
        try (FileReader reader = new FileReader(csvFilePath)) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withHeader("Number", "R", "G", "B", "Type", "Comments")
                    .withSkipHeaderRecord()
                    .parse(reader);

            for (CSVRecord record : records) {
                String number = record.get("Number");
                int r = Integer.parseInt(record.get("R"));
                int g = Integer.parseInt(record.get("G"));
                int b = Integer.parseInt(record.get("B"));
                String type = record.get("Type");
                String comments = record.get("Comments");

                palette.add(new PaletteColor(number, r, g, b, type, comments));
            }
        }
        return palette;
    }

    // Podmiana kolorów obrazu na najbliższe z palety
    private static BufferedImage replaceColors(BufferedImage image, List<PaletteColor> palette) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color originalColor = new Color(image.getRGB(x, y));
                PaletteColor closestColor = findClosestPaletteColor(originalColor, palette);
                newImage.setRGB(x, y, closestColor.toColor().getRGB());
            }
        }
        return newImage;
    }

    // Znajdowanie najbliższego koloru z palety
    private static PaletteColor findClosestPaletteColor(Color color, List<PaletteColor> palette) {
        PaletteColor closest = null;
        double minDistance = Double.MAX_VALUE;

        for (PaletteColor paletteColor : palette) {
            double distance = Config.getColorMappingMode() == ColorMappingMode.LAB ?
                    paletteColor.compareByLAB(color) : paletteColor.compareByRGB(color);
            if (distance < minDistance) {
                minDistance = distance;
                closest = paletteColor;
            }
        }

        return closest;
    }
}

