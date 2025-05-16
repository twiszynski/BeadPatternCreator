package org.example;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PNGBeadTemplateGenerator {

    private static final int WIDTH = 53;
    private static final int HEIGHT = 66;
    private static final String GLOSS_PATH = Config.getLibraryBaseDirectoryPath()+"BeadTemplates\\gloss2.png";
    private static final String CSV_PATH = Config.getAbsoluteCsvPalettePath();
    private static final String OUTPUT_DIR = Config.getLibraryBaseDirectoryPath()+"BeadTemplates";

    public static void main(String[] args) {
        try {
            generateTemplates();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generateTemplates() throws IOException {
        BufferedImage glossImage = ImageIO.read(new File(GLOSS_PATH));

        if (glossImage.getWidth() != WIDTH || glossImage.getHeight() != HEIGHT) {
            throw new IllegalArgumentException("Gloss image must be exactly " + WIDTH + "x" + HEIGHT + " pixels.");
        }

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
                if (tokens.length < 5) continue;

                String number = tokens[0].trim();
                int r = Integer.parseInt(tokens[1].trim());
                int g = Integer.parseInt(tokens[2].trim());
                int b = Integer.parseInt(tokens[3].trim());

                BufferedImage baseImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2d = baseImage.createGraphics();

                // Fill base color
                g2d.setColor(new Color(r, g, b));
                g2d.fillRect(0, 0, WIDTH, HEIGHT);

                // Overlay gloss
                g2d.drawImage(glossImage, 0, 0, null);
                g2d.dispose();

                // Save output image
                File outputFile = new File(OUTPUT_DIR + File.separator + number + ".png");
                ImageIO.write(baseImage, "PNG", outputFile);

                System.out.println("Generated: " + outputFile.getName());
            }
        }
    }
}

