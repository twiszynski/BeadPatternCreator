package org.example;

import java.io.*;

public class ExcelToPdfConverter {

    public static void convertExcelToPdf(String excelFilePath, String pdfOutputPath) {
        try {
            String command = "soffice --headless --convert-to pdf " + excelFilePath + " --outdir " + pdfOutputPath;
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();
            System.out.println("Konwersja zakończona!");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String imagePatternFileName = "couple_M_Pattern";
        String excelFile = "C:\\Users\\Admin\\Desktop\\ImgToExcel\\" + imagePatternFileName + ".xlsx";
        String outputPdf = "C:\\Users\\Admin\\Desktop\\ImgToExcel\\";
        convertExcelToPdf(excelFile, outputPdf);
    }
}

