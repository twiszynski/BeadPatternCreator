package org.example;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class ColorCounter {

    public static void main(String[] args) {

        String imageName = "mount_sun_2";
        // Ścieżka do obrazu wejściowego
        String imagePath = "C:\\Users\\Admin\\Desktop\\ImgToExcel\\" + imageName + ".png";

        int uniqueColorsCount = countUniqueColors(imagePath);

        System.out.println(uniqueColorsCount);
    }

    public static int countUniqueColors(String imagePath) {
        try {
            // Załaduj obraz z podanej ścieżki
            BufferedImage image = ImageIO.read(new File(imagePath));

            // Zbiór do przechowywania unikalnych kolorów
            Set<Integer> uniqueColors = new HashSet<>();

            // Przejdź przez piksele obrazu
            for (int y = 0; y < image.getHeight(); y++) {
                for (int x = 0; x < image.getWidth(); x++) {
                    int rgb = image.getRGB(x, y); // Pobierz wartość koloru (ARGB w formacie int)
                    uniqueColors.add(rgb); // Dodaj kolor do zbioru
                }
            }

            // Zwróć liczbę unikalnych kolorów
            return uniqueColors.size();
        } catch (IOException e) {
            System.err.println("Nie można załadować obrazu: " + e.getMessage());
            return -1; // Zwróć -1 w przypadku błędu
        }
    }
}
