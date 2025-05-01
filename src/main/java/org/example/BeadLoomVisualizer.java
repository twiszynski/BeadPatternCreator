package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BeadLoomVisualizer extends Application {
    private static final double BEAD_WIDTH = 16.0;  // Szerokość koralika w pikselach
    private static final double BEAD_HEIGHT = 13.0; // Wysokość koralika w pikselach
    private static String[][] pattern;              // Schemat z Excela
    private static Map<String, int[]> legend;       // Legenda: symbol -> RGB

    public static void main(String[] args) {

        String imageFileName = "lady2_M";
        String excelFilePath = "C:\\Users\\Admin\\Desktop\\ImgToExcel\\" + imageFileName + "_Pattern.xlsx";

        try (FileInputStream fis = new FileInputStream(excelFilePath)) {
            // Wczytanie pliku Excel
            Workbook workbook = new XSSFWorkbook(fis);

            // Wczytaj dane przed uruchomieniem aplikacji
            legend = loadBeadLegend(workbook);
            pattern = loadPattern(workbook);
            if (legend != null && pattern != null) {
                launch(args);
            } else {
                System.out.println("Błąd wczytywania danych z pliku Excel.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Wczytanie legendy z arkusza Excel
    private static Map<String, int[]> loadBeadLegend(Workbook workbook) {
        Map<String, int[]> colorMap = new HashMap<>();
        Sheet legendSheet = workbook.getSheet("Legend");

        boolean isFirstRow = true;
        for (Row row : legendSheet) {
            if (row == null) continue;

            if (isFirstRow) {
                isFirstRow = false; // Pominięcie pierwszego wiersza
                continue;
            }

            // Symbol w pierwszej kolumnie
            Cell symbolCell = row.getCell(0);
            // Wartości RGB w kolumnach 4, 5, 6
            Cell redCell = row.getCell(4);
            Cell greenCell = row.getCell(5);
            Cell blueCell = row.getCell(6);

            if (symbolCell != null && redCell != null && greenCell != null && blueCell != null) {
                String symbol = symbolCell.getStringCellValue();
                int red = (int) redCell.getNumericCellValue();
                int green = (int) greenCell.getNumericCellValue();
                int blue = (int) blueCell.getNumericCellValue();

                // Dodanie symbolu i kolorów RGB do mapy
                colorMap.put(symbol, new int[]{red, green, blue});
            }
        }
        return colorMap;
    }

    // Wczytanie schematu z arkusza Excel
    private static String[][] loadPattern(Workbook workbook) {
        Sheet sheet = workbook.getSheet("Pattern");
        int rows = sheet.getLastRowNum() + 1;
        int cols = sheet.getRow(0).getLastCellNum();
        String[][] pattern = new String[rows][cols];
        for (int i = 0; i < rows; i++) {
            Row row = sheet.getRow(i);
            for (int j = 0; j < cols; j++) {
                pattern[i][j] = row.getCell(j).getStringCellValue();
            }
        }
        return pattern;
    }

    @Override
    public void start(Stage stage) {
        Pane pane = new Pane();
        drawBeadPattern(pane);

        Scene scene = new Scene(pane, pattern[0].length * BEAD_WIDTH, pattern.length * BEAD_HEIGHT);
        stage.setTitle("Bead Loom Visualization");
        stage.setScene(scene);
        stage.show();
    }

    // Rysowanie schematu koralików
    private void drawBeadPattern(Pane pane) {
        for (int row = 0; row < pattern.length; row++) {
            for (int col = 0; col < pattern[row].length; col++) {
                String symbol = pattern[row][col];
                int[] rgb = legend.get(symbol);
                if (rgb != null) {
                    Rectangle bead = createBead(col * BEAD_WIDTH, row * BEAD_HEIGHT, rgb);
                    pane.getChildren().add(bead);
                }
            }
        }
    }

    // Tworzenie pojedynczego koralika
    private Rectangle createBead(double x, double y, int[] rgb) {
        Rectangle bead = new Rectangle(x, y, BEAD_WIDTH, BEAD_HEIGHT);
        bead.setArcWidth(4);  // Zaokrąglenie rogów
        bead.setArcHeight(4); // Zaokrąglenie rogów

        // Gradient liniowy od góry do dołu
        LinearGradient gradient = new LinearGradient(
                0, 0,              // x1, y1 - początek gradientu (góra)
                0, 1,              // x2, y2 - koniec gradientu (dół)
                true,              // proporcjonalne współrzędne (względem koralika)
                null,              // CycleMethod (domyślnie NO_CYCLE)
                new Stop(0, Color.rgb(rgb[0], rgb[1], rgb[2], 1.0)),           // Jasny kolor na górze
                new Stop(1, Color.rgb(rgb[0] / 2, rgb[1] / 2, rgb[2] / 2, 0.8)) // Ciemniejszy na dole
        );
        bead.setFill(gradient);

        // Obramowanie i cień
        bead.setStroke(Color.BLACK);
        bead.setStrokeWidth(0.3);
        bead.setEffect(new DropShadow(2, 1, 1, Color.GRAY));
        return bead;
    }


}