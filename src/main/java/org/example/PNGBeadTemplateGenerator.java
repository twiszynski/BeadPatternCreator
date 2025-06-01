package org.example;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class PNGBeadTemplateGenerator {

    private static final int WIDTH = 53;
    private static final int HEIGHT = 66;
    private static final String CSV_PATH = Config.getAbsoluteCsvPalettePath();
    private static final String OUTPUT_DIR = Config.getLibraryBaseDirectoryPath() + "BeadTemplates";
    private static final String EFFECTS_DIR_PATH = Config.getFinishTypesDirPathWithSeparator(); // <- uzupełnij to u siebie

    public static void main(String[] args) {
        try {
            generateTemplates();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generateTemplates() throws IOException {
        Files.createDirectories(Paths.get(OUTPUT_DIR));

        try (BufferedReader br = new BufferedReader(new FileReader(CSV_PATH))) {
            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue; // skip header
                }

                String[] tokens = line.split(",", -1);
                if (tokens.length < 6) continue;

                String number = tokens[0].trim();
                int r = Integer.parseInt(tokens[1].trim());
                int g = Integer.parseInt(tokens[2].trim());
                int b = Integer.parseInt(tokens[3].trim());
                String effectType = tokens[5].trim(); // zakładamy, że kolumna z efektem to 6-ta kolumna (indeks 5)

                // Pobierz mapę warstw i ich przezroczystości dla danego efektu
                Map<String, Integer> effectLayers = FinishesConfig.EFFECT_CONFIGS.get(effectType);
                if (effectLayers == null) {
                    System.out.println("Brak konfiguracji dla efektu: " + effectType + ", pomijam numer: " + number);
                    continue;
                }

                BufferedImage composedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2d = composedImage.createGraphics();

                // Sprawdź, czy efekt zawiera warstwę BaseColor i jaką ma przezroczystość
                Integer baseOpacity = effectLayers.get("BaseColor");
                if (baseOpacity != null && baseOpacity > 0) {
                    Color baseColor = new Color(r, g, b, (int) (255 * baseOpacity / 100.0));
                    g2d.setColor(baseColor);
                    g2d.fillRect(0, 0, WIDTH, HEIGHT);
                }

                for (Map.Entry<String, Integer> layer : effectLayers.entrySet()) {
                    String layerName = layer.getKey();
                    int opacity = layer.getValue();

                    if ("BaseColor".equals(layerName)) continue;

                    File effectFile = new File(EFFECTS_DIR_PATH + layerName);
                    if (!effectFile.exists()) {
                        System.out.println("Nie znaleziono pliku: " + effectFile.getAbsolutePath() + ", pomijam.");
                        continue;
                    }

                    BufferedImage overlay = ImageIO.read(effectFile);
                    if (overlay.getWidth() != WIDTH || overlay.getHeight() != HEIGHT) {
                        System.out.println("Nieprawidłowe wymiary obrazu: " + effectFile.getName() + ", pomijam.");
                        continue;
                    }

                    BufferedImage transparentLayer = applyOpacity(overlay, opacity);
                    g2d.drawImage(transparentLayer, 0, 0, null);
                }

                g2d.dispose();

                File outputFile = new File(OUTPUT_DIR + File.separator + number + ".png");
                ImageIO.write(composedImage, "PNG", outputFile);

                System.out.println("Wygenerowano: " + outputFile.getName());
            }
        }
    }

    private static BufferedImage applyOpacity(BufferedImage image, int opacityPercent) {
        BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        float alphaFactor = opacityPercent / 100.0f;

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int rgba = image.getRGB(x, y);
                Color color = new Color(rgba, true);
                int alpha = (int) (color.getAlpha() * alphaFactor);
                Color newColor = new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
                result.setRGB(x, y, newColor.getRGB());
            }
        }

        return result;
    }
}
