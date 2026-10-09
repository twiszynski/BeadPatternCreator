package org.example;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class PNGBeadTemplateGenerator {

    private static final int WIDTH = 53;
    private static final int HEIGHT = 66;
    private static final String CSV_PATH = Config.getAbsoluteCsvPalettePath();
    private static final String OUTPUT_DIR = Config.getLibraryBaseDirectoryPath() + "BeadTemplates";
    private static final String EFFECTS_DIR_PATH = Config.getFinishTypesDirPathWithSeparator();

    public static void main(String[] args) {
        try {
            generateTemplates();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generateTemplates() throws IOException {
        Files.createDirectories(Paths.get(OUTPUT_DIR));

        BufferedImage edgeMask = createSubtleEdgeMask(WIDTH, HEIGHT);

        try (BufferedReader br = new BufferedReader(new FileReader(CSV_PATH))) {
            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; // Pomijamy nagłówek CSV
                    continue;
                }

                String[] tokens = line.split(",", -1);
                if (tokens.length < 6) continue;

                String number = tokens[0].trim();
                int r = Integer.parseInt(tokens[1].trim());
                int g = Integer.parseInt(tokens[2].trim());
                int b = Integer.parseInt(tokens[3].trim());
                String effectType = tokens[7].trim();

                // Pobieramy konfigurację warstw z Twojego finishes.json / FinishesConfig
                Map<String, Integer> effectLayers = FinishesConfig.EFFECT_CONFIGS.get(effectType);
                if (effectLayers == null) {
                    effectLayers = FinishesConfig.EFFECT_CONFIGS.get("Basic");
                }

                // Obliczamy jasność perceptualną koloru bazowego (Luminance)
                double baseLuminance = (0.299 * r + 0.587 * g + 0.114 * b) / 255.0;

                BufferedImage canvas = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2d = canvas.createGraphics();

                // Domyślnie wypełniamy tło pełną barwą bazową
                g2d.setColor(new Color(r, g, b, 255));
                g2d.fillRect(0, 0, WIDTH, HEIGHT);

                // Iterujemy po Twoich warstwach
                for (Map.Entry<String, Integer> layer : effectLayers.entrySet()) {
                    String layerName = layer.getKey();
                    int opacityPercent = layer.getValue();

                    if (layerName.startsWith("BaseColor")) {
                        // Warstwa nakładkowa koloru bazowego (często używana u Ciebie z opacities 20-50%)
                        Color tint = new Color(r, g, b, (int) (255 * opacityPercent / 100.0));
                        g2d.setColor(tint);
                        g2d.fillRect(0, 0, WIDTH, HEIGHT);
                    } else {
                        File effectFile = new File(EFFECTS_DIR_PATH + layerName);
                        if (!effectFile.exists()) {
                            System.out.println("Nie znaleziono pliku tekstury: " + effectFile.getAbsolutePath());
                            continue;
                        }

                        BufferedImage overlay = ImageIO.read(effectFile);
                        if (overlay.getWidth() != WIDTH || overlay.getHeight() != HEIGHT) {
                            continue;
                        }

                        // Modyfikacja krycia dla ciemnych koralików, zapobiegająca tworzeniu szarej mgiełki
                        float adjustedOpacity = opacityPercent / 100.0f;
                        if (baseLuminance < 0.25 && layerName.toLowerCase().contains("opaque")) {
                            // Ciemne koraliki pod podkładem Opaque zachowują swoją głębię
                            adjustedOpacity *= (0.4f + baseLuminance * 2.0f);
                        }

                        BufferedImage transparentOverlay = applyOpacity(overlay, adjustedOpacity);
                        g2d.drawImage(transparentOverlay, 0, 0, null);
                    }
                }

                g2d.dispose();

                // Zaokrąglamy delikatnie same narożniki zewnętrzne koralika
                canvas = applyAlphaMask(canvas, edgeMask);

                File outputFile = new File(OUTPUT_DIR + File.separator + number + ".png");
                ImageIO.write(canvas, "PNG", outputFile);
                System.out.println("Wygenerowano z wykorzystaniem tekstur autorskich: " + outputFile.getName());
            }
        }
    }

    private static BufferedImage applyOpacity(BufferedImage image, float opacityFactor) {
        BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int rgba = image.getRGB(x, y);
                Color color = new Color(rgba, true);
                int alpha = (int) (color.getAlpha() * opacityFactor);
                Color newColor = new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
                result.setRGB(x, y, newColor.getRGB());
            }
        }
        return result;
    }

    /**
     * Maska zaokrąglająca jedynie zewnętrzne rogowe piksele prostokąta, nie dotykająca wnętrza koralika
     */
    private static BufferedImage createSubtleEdgeMask(int width, int height) {
        BufferedImage mask = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = mask.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.BLACK);
        g2.fillRoundRect(0, 0, width, height, 6, 6);
        g2.dispose();
        return mask;
    }

    private static BufferedImage applyAlphaMask(BufferedImage source, BufferedImage mask) {
        BufferedImage result = new BufferedImage(source.getWidth(), source.getHeight(), BufferedImage.TYPE_INT_ARGB);
        for (int y = 0; y < source.getHeight(); y++) {
            for (int x = 0; x < source.getWidth(); x++) {
                int maskAlpha = (mask.getRGB(x, y) >> 24) & 0xFF;
                int srcRgb = source.getRGB(x, y);
                int finalAlpha = (int) (((srcRgb >> 24) & 0xFF) * (maskAlpha / 255.0f));
                result.setRGB(x, y, (finalAlpha << 24) | (srcRgb & 0x00FFFFFF));
            }
        }
        return result;
    }
}