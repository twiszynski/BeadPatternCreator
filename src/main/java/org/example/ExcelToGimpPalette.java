package org.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Iterator;

public class ExcelToGimpPalette {

    public static void main(String[] args) {
        String fileName = "PaletaMiyuki";
        String excelFilePath = "C:\\Users\\Admin\\Desktop\\PaletyMiyuki\\" + fileName + ".xlsx";; // Ścieżka do pliku Excel
        String gimpPalettePath = "C:\\Users\\Admin\\Desktop\\PaletyMiyuki\\" + fileName + ".gpl";; // Ścieżka wyjściowa dla pliku .gpl

        try {
            // Wczytanie pliku Excel
            FileInputStream fileInputStream = new FileInputStream(excelFilePath);
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheetAt(0); // Pierwszy arkusz w pliku

            // Przygotowanie pliku do zapisania palety GIMP
            BufferedWriter writer = new BufferedWriter(new FileWriter(gimpPalettePath));

            // Napisz nagłówki palety GIMP
            writer.write("GIMP Palette\n");
            writer.write("Name: My Miyuki Palette\n");
            writer.write("Columns: 0\n");
            writer.write("#\n");

            // Iteracja przez wiersze w arkuszu Excel
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                // Odczyt danych z wiersza (zakładając, że dane zaczynają się od wiersza 1)
                Cell cellNumber = row.getCell(0); // Numer koralika (kolumna A)
                Cell cellR = row.getCell(1); // Wartość R (kolumna B)
                Cell cellG = row.getCell(2); // Wartość G (kolumna C)
                Cell cellB = row.getCell(3); // Wartość B (kolumna D)

                // Sprawdzamy, czy wszystkie komórki zawierają dane
                if (cellNumber != null && cellR != null && cellG != null && cellB != null) {
                    int r = (int) cellR.getNumericCellValue();
                    int g = (int) cellG.getNumericCellValue();
                    int b = (int) cellB.getNumericCellValue();

                    // Formatowanie zapisu w formacie GIMP: "R G B" (gdzie R, G, B to wartości RGB)
                    writer.write(String.format("%d %d %d %s\n", r, g, b, cellNumber.getStringCellValue()));
                }
            }

            // Zamknięcie pliku
            writer.close();
            workbook.close();
            System.out.println("Paleta GIMP została utworzona!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
