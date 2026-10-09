package org.example;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class PhotographicBeadGenerator {

    private static final int WIDTH = 53;
    private static final int HEIGHT = 66;
    private static final String CSV_PATH = Config.getAbsoluteCsvPalettePath();
    private static final String OUTPUT_DIR = Config.getLibraryBaseDirectoryPath() + "BeadTemplates";
    private static final String MASKS_DIR = Config.getLibraryBaseDirectoryPath() + "MasterMasks";

    // Pamięć podręczna na autorskie tekstury PNG
    private static final Map<String, BufferedImage> textureCache = new HashMap<>();

    public static void main(String[] args) {
        try {
            generateAllPhotographicSprites();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generateAllPhotographicSprites() throws IOException {
        Files.createDirectories(Paths.get(OUTPUT_DIR));
        Files.createDirectories(Paths.get(MASKS_DIR));

        // Maska cienia walca i przycięcia krawędzi
        BufferedImage cylinderShapeMask = createCylinderShapeMask(WIDTH, HEIGHT);
        BufferedImage cylinderShadow = createCylinderFormShadow(WIDTH, HEIGHT);

        int generatedCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(CSV_PATH))) {
            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; // Pomijamy nagłówek CSV
                    continue;
                }

                String[] tokens = line.split(",", -1);
                if (tokens.length < 8) continue;

                String number = tokens[0].trim();
                int csvR = Integer.parseInt(tokens[1].trim());
                int csvG = Integer.parseInt(tokens[2].trim());
                int csvB = Integer.parseInt(tokens[3].trim());
                String effectType = tokens[7].trim(); // Pobrana nazwa wykończenia z CSV

                // 1. Obliczamy zbalansowaną barwę podłoża (renderRgb)
                Color renderColor = calculateRenderBaseColor(csvR, csvG, csvB, effectType);

                // 2. Tworzymy kanwę z pigmentem z CSV
                BufferedImage canvas = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2d = canvas.createGraphics();
                g2d.setColor(renderColor);
                g2d.fillRect(0, 0, WIDTH, HEIGHT);
                g2d.dispose();

                // 3. Cieniowanie obłości poziomego walca (MULTIPLY)
                blendImages(canvas, cylinderShadow, 0.85f, BlendMode.MULTIPLY);

                // 4. Nakładanie masek tekstur według typu z CSV
                renderEffectLayers(canvas, effectType);

                // 5. Przycięcie do wygładzonego kształtu rurki Delica
                canvas = applyAlphaMask(canvas, cylinderShapeMask);

                // 6. Zapis gotowego sprajta PNG
                File outputFile = new File(OUTPUT_DIR + File.separator + number + ".png");
                ImageIO.write(canvas, "PNG", outputFile);
                generatedCount++;
            }
        }

        System.out.println("Pomyślnie wygenerowano " + generatedCount + " fotograficznych sprajtów w " + OUTPUT_DIR);
    }

    /**
     * Dobiera i sekwencyjnie nakłada właściwe autorskie tekstury PNG na podstawie ciągu znaków z CSV.
     */
    private static void renderEffectLayers(BufferedImage canvas, String effectType) {
        if (effectType == null || effectType.isEmpty()) effectType = "Basic";
        String lower = effectType.toLowerCase();

        boolean hasAB = lower.contains("ab");
        boolean hasMetallic = lower.contains("metallic") || lower.contains("galvanized") || lower.contains("ceylon");
        boolean hasLined = lower.contains("lined");
        boolean hasSatin = lower.contains("satin");
        boolean hasMatte = lower.contains("matte") || lower.contains("frosted");
        boolean hasTransparent = lower.contains("transparent") || lower.contains("crystal");

        // --- WARSTWA 1: Główny refleks/blik lub srebrny rdzeń ---
        if (hasLined) {
            BufferedImage linedMask = getTexture("Lined_strong.png");
            if (linedMask != null) {
                blendImages(canvas, linedMask, 0.85f, BlendMode.SCREEN);
            }
        } else if (hasMetallic) {
            BufferedImage metallicMask = getTexture("Metallic.png");
            if (metallicMask != null) {
                float opacity = hasAB ? 0.65f : 0.75f;
                blendImages(canvas, metallicMask, opacity, BlendMode.SCREEN);
            }
        } else if (hasSatin) {
            BufferedImage satinMask = getTexture("Satin.png");
            if (satinMask != null) {
                blendImages(canvas, satinMask, 0.70f, BlendMode.SCREEN);
            }
        } else if (hasTransparent) {
            BufferedImage transMask = getTexture("Transparent.png");
            if (transMask != null) {
                blendImages(canvas, transMask, 0.60f, BlendMode.SCREEN);
            }
        } else { // Opaque / Basic
            BufferedImage opaqueMask = getTexture("Opaque.png");
            if (opaqueMask != null) {
                blendImages(canvas, opaqueMask, 0.50f, BlendMode.SCREEN);
            }
        }

        // --- WARSTWA 2: Tęczowa iryzacja / Aurora Borealis (AB) ---
        if (hasAB) {
            BufferedImage abMask = getTexture("AB.png");
            if (abMask != null) {
                blendImages(canvas, abMask, 0.65f, BlendMode.OVERLAY);
            }
        }

        // --- WARSTWA 3: Matowienie powierzchni ---
        if (hasMatte) {
            BufferedImage matteMask = getTexture("Matte.png");
            if (matteMask != null) {
                blendImages(canvas, matteMask, 0.45f, BlendMode.MULTIPLY);
            }
        }
    }

    private static Color calculateRenderBaseColor(int r, int g, int b, String effectType) {
        String lower = (effectType != null) ? effectType.toLowerCase() : "";
        float factor = 0.95f;

        if (lower.contains("metallic") || lower.contains("galvanized")) {
            factor = 0.88f;
        } else if (lower.contains("lined")) {
            factor = 0.90f;
        } else if (lower.contains("matte")) {
            factor = 0.98f;
        }

        int outR = Math.max(0, Math.min(255, (int) (r * factor)));
        int outG = Math.max(0, Math.min(255, (int) (g * factor)));
        int outB = Math.max(0, Math.min(255, (int) (b * factor)));
        return new Color(outR, outG, outB);
    }

    // --- SILNIK BLENDA I MASKUJĄCY ---

    public enum BlendMode { MULTIPLY, SCREEN, OVERLAY, NORMAL }

    private static void blendImages(BufferedImage target, BufferedImage overlay, float opacity, BlendMode mode) {
        int width = target.getWidth();
        int height = target.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int tRgb = target.getRGB(x, y);
                int oRgb = overlay.getRGB(x, y);

                int oA = (oRgb >> 24) & 0xFF;
                if (oA == 0) continue;

                int tA = (tRgb >> 24) & 0xFF;
                if (tA == 0) continue;

                int tR = (tRgb >> 16) & 0xFF, tG = (tRgb >> 8) & 0xFF, tB = tRgb & 0xFF;
                int oR = (oRgb >> 16) & 0xFF, oG = (oRgb >> 8) & 0xFF, oB = oRgb & 0xFF;

                float effectiveAlpha = (oA / 255.0f) * opacity;
                int outR = tR, outG = tG, outB = tB;

                switch (mode) {
                    case SCREEN:
                        int screenR = 255 - ((255 - tR) * (255 - oR)) / 255;
                        int screenG = 255 - ((255 - tG) * (255 - oG)) / 255;
                        int screenB = 255 - ((255 - tB) * (255 - oB)) / 255;

                        outR = (int) (tR * (1.0f - effectiveAlpha) + screenR * effectiveAlpha);
                        outG = (int) (tG * (1.0f - effectiveAlpha) + screenG * effectiveAlpha);
                        outB = (int) (tB * (1.0f - effectiveAlpha) + screenB * effectiveAlpha);
                        break;

                    case MULTIPLY:
                        int multR = (tR * oR) / 255;
                        int multG = (tG * oG) / 255;
                        int multB = (tB * oB) / 255;

                        outR = (int) (tR * (1.0f - effectiveAlpha) + multR * effectiveAlpha);
                        outG = (int) (tG * (1.0f - effectiveAlpha) + multG * effectiveAlpha);
                        outB = (int) (tB * (1.0f - effectiveAlpha) + multB * effectiveAlpha);
                        break;

                    case OVERLAY:
                        outR = blendOverlayChannel(tR, oR, effectiveAlpha);
                        outG = blendOverlayChannel(tG, oG, effectiveAlpha);
                        outB = blendOverlayChannel(tB, oB, effectiveAlpha);
                        break;

                    case NORMAL:
                        outR = (int) (tR * (1.0f - effectiveAlpha) + oR * effectiveAlpha);
                        outG = (int) (tG * (1.0f - effectiveAlpha) + oG * effectiveAlpha);
                        outB = (int) (tB * (1.0f - effectiveAlpha) + oB * effectiveAlpha);
                        break;
                }

                target.setRGB(x, y, (tA << 24) | (clamp(outR) << 16) | (clamp(outG) << 8) | clamp(outB));
            }
        }
    }

    private static int blendOverlayChannel(int base, int overlay, float alpha) {
        float b = base / 255.0f;
        float o = overlay / 255.0f;
        float res = (b < 0.5f) ? (2.0f * b * o) : (1.0f - 2.0f * (1.0f - b) * (1.0f - o));
        int blended = (int) (res * 255.0f);
        return (int) (base * (1.0f - alpha) + blended * alpha);
    }

    // --- ŁADOWANIE MASK Z AUTORSKICH PLIKÓW PNG ---

    private static BufferedImage getTexture(String fileName) {
        if (textureCache.containsKey(fileName)) {
            return textureCache.get(fileName);
        }

        File maskFile = new File(MASKS_DIR + File.separator + fileName);
        if (!maskFile.exists()) {
            maskFile = new File(Config.getFinishTypesDirPathWithSeparator() + fileName);
        }

        if (maskFile.exists()) {
            try {
                BufferedImage img = ImageIO.read(maskFile);
                textureCache.put(fileName, img);
                return img;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Nie znaleziono pliku tekstury: " + fileName);
        }
        return null;
    }

    // --- GEOMETRIA WALCA I MASKOWANIE KRAWĘDZI ---

    private static BufferedImage createCylinderFormShadow(int w, int h) {
        BufferedImage shadow = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        for (int y = 0; y < h; y++) {
            double ny = (y - (h / 2.0)) / (h / 2.0);
            double form = Math.cos(ny * (Math.PI / 2.15));
            form = Math.max(0.35, Math.min(1.0, form));

            for (int x = 0; x < w; x++) {
                double edgeFactor = 1.0;
                if (x < 6) edgeFactor = 0.4 + 0.6 * (x / 6.0);
                if (x > w - 7) edgeFactor = 0.4 + 0.6 * ((w - 1 - x) / 6.0);

                int shade = clamp((int) (form * edgeFactor * 255));
                shadow.setRGB(x, y, (255 << 24) | (shade << 16) | (shade << 8) | shade);
            }
        }
        return shadow;
    }

    private static BufferedImage createCylinderShapeMask(int w, int h) {
        BufferedImage mask = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = mask.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Tworzymy czysty kształt rurki z zaokrąglonymi narożnikami
        g2.setColor(Color.BLACK);
        g2.fillRoundRect(0, 0, w, h, 12, 12);
        g2.dispose();
        return mask;
    }

    private static BufferedImage applyAlphaMask(BufferedImage source, BufferedImage mask) {
        BufferedImage result = new BufferedImage(source.getWidth(), source.getHeight(), BufferedImage.TYPE_INT_ARGB);
        for (int y = 0; y < source.getHeight(); y++) {
            for (int x = 0; x < source.getWidth(); x++) {
                int maskAlpha = (mask.getRGB(x, y) >> 24) & 0xFF;

                if (maskAlpha == 0) {
                    // Czysta przezroczystość poza krawędzią koralika (brak białego tła)
                    result.setRGB(x, y, 0x00000000);
                } else {
                    int srcRgb = source.getRGB(x, y);
                    int finalAlpha = (int) (((srcRgb >> 24) & 0xFF) * (maskAlpha / 255.0f));
                    result.setRGB(x, y, (finalAlpha << 24) | (srcRgb & 0x00FFFFFF));
                }
            }
        }
        return result;
    }

    private static int clamp(int val) {
        return Math.max(0, Math.min(255, val));
    }
}