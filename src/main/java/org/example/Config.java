package org.example;

import java.io.File;

public class Config {

    private static final String IMG_NAME = "bird";

    private static final String IMG_INPUT_FORMAT = ".png";
    private static final String MAPPED_COLORS_IMG_SUFFIX = "_M";
    private static final String ADJUSTED_IMG_SUFFIX = "_adj";
    private static final String LIB_DIR_PATH = "C:\\Users\\Admin\\Desktop\\ImgToExcel\\";
    private static final String PATTERN_FILE_SUFFIX = "_Pattern";
    private static final String PATTERN_FILE_FORMAT = ".xlsx";
    private static final String PALETTE_PATH = "src/main/resources/Palettes/MiyukiFullCSV.csv";



    public static String getImgName() {
        return IMG_NAME;
    }

    public static String getXlsxPatternFileName() {
        return IMG_NAME + MAPPED_COLORS_IMG_SUFFIX + PATTERN_FILE_SUFFIX;
    }

    public static String getBaseColorsImgPath() {
        return LIB_DIR_PATH + IMG_NAME + IMG_INPUT_FORMAT;
    }

    public static String getMiyukiColorsImgPath() {
        return LIB_DIR_PATH + IMG_NAME + MAPPED_COLORS_IMG_SUFFIX + IMG_INPUT_FORMAT;
    }

    public static String getAdjustedImgPath() {
        return LIB_DIR_PATH + IMG_NAME + MAPPED_COLORS_IMG_SUFFIX + ADJUSTED_IMG_SUFFIX + IMG_INPUT_FORMAT;
    }

    public static String getAbsoluteCsvPalettePath() {
        return new File(PALETTE_PATH).getAbsolutePath();
    }

    public static String getXlsxPatternFilePath() {
        return LIB_DIR_PATH + IMG_NAME + MAPPED_COLORS_IMG_SUFFIX + PATTERN_FILE_SUFFIX + PATTERN_FILE_FORMAT;
    }


}
