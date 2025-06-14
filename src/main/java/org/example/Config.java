package org.example;

import java.io.File;

public class Config {
    //General Image and paths properties
    private static final String IMG_NAME = "bird3";

    private static final ColorMappingMode COLOR_MAPPING_MODE = ColorMappingMode.LAB;

    private static final String IMG_FORMAT_SUFFIX = ".png";
    private static final String MAPPED_COLORS_IMG_SUFFIX = "_M";
    private static final String ADJUSTED_IMG_SUFFIX = "_adj";
    private static final String LIB_DIR_PATH = "C:\\Users\\Admin\\Desktop\\ImgToExcel\\";
    private static final String PATTERN_FILE_SUFFIX = "_Pattern";
    private static final String PATTERN_FILE_FORMAT = ".xlsx";
    private static final String PALETTE_PATH = "src/main/resources/Palettes/MiyukiFullCSV.csv";

    //BeadVisualizer properties
    private static final String TEMPLATE_FOLDER_PATH = "src\\main\\resources\\MiyukiPNG";
    private static final String FINISH_TYPES_DIR_PATH = "src\\main\\resources\\FinishTypesPNG";
    private static final String DEFAULT_IMAGE_NAME = "NoMatch.png";
    private static final String VISUAL_IMG_SUFFIX = "_visual";
    private static final int BEAD_IMG_PX_WIDTH = 53;
    private static final int BEAD_IMG_PX_HEIGHT = 66;
    private static final int HORIZONTAL_PX_SPACING = 2;
    private static final int VERTICAL_PX_SPACING = 1;
    private static final int CORNER_ARC_PX_WIDTH = 25;
    private static final int CORNER_ARC_PX_HEIGHT = 25;

    //XLSX Pattern file properties
    private static final String PATTERN_SHEET_NAME = "Pattern";
    private static final String LEGEND_SHEET_NAME = "Legend";
    private static final String WORD_CHART_SHEET_NAME = "Word_Chart";
    private static final String SIZE_SHEET_NAME = "Size";
    private static final String PAGE_MAP_SHEET_NAME = "PageMap";

    // Print properties
    private static final String COPYWRITE_NOTE = "©2025 Beadventure. This pattern is for personal use only.\n" +
            "You may not copy, share, modify, or resell this file in any form without written permission.";

    //PageMap division settings
    private static final int COLS_PER_PAGE = 30;
    private static final int ROWS_PER_PAGE = 37;

    //Fonts properties
    private static final String HEADER_FONT = "Montserrat SemiBold";
    private static final int HEADER_FONT_SIZE = 10;
    private static final String BASE_FONT = "Montserrat";
    private static final int BASE_FONT_SIZE = 10;
    private static final String COLS_ROWS_NUMBERING_FONT = "Montserrat";
    private static final int COLS_ROWS_NUMBERING_FONT_SIZE = 9;


    public static String getImgName() {
        return IMG_NAME;
    }

    public static ColorMappingMode getColorMappingMode() {
        return COLOR_MAPPING_MODE;
    }

    public static String getImgFormatSuffix() {
        return IMG_FORMAT_SUFFIX;
    }

    public static String getLibraryBaseDirectoryPath() {
        return LIB_DIR_PATH;
    }

    public static String getXlsxPatternFileName() {
        return IMG_NAME + MAPPED_COLORS_IMG_SUFFIX + PATTERN_FILE_SUFFIX;
    }

    public static String getBaseColorsImgPath() {
        return LIB_DIR_PATH + IMG_NAME + IMG_FORMAT_SUFFIX;
    }

    public static String getMiyukiColorsImgPath() {
        return LIB_DIR_PATH + IMG_NAME + MAPPED_COLORS_IMG_SUFFIX + IMG_FORMAT_SUFFIX;
    }

    public static String getAdjustedImgPath() {
        return LIB_DIR_PATH + IMG_NAME + MAPPED_COLORS_IMG_SUFFIX + ADJUSTED_IMG_SUFFIX + IMG_FORMAT_SUFFIX;
    }

    public static String getAbsoluteCsvPalettePath() {
        return new File(PALETTE_PATH).getAbsolutePath();
    }

    public static String getXlsxPatternFilePath() {
        return LIB_DIR_PATH + IMG_NAME + MAPPED_COLORS_IMG_SUFFIX + PATTERN_FILE_SUFFIX + PATTERN_FILE_FORMAT;
    }

    public static String getBeadTemplateDirPathWithSeparator() {
        return new File(TEMPLATE_FOLDER_PATH).getAbsolutePath() + File.separator;
    }

    public static String getFinishTypesDirPathWithSeparator() {
        return new File(FINISH_TYPES_DIR_PATH).getAbsolutePath() + File.separator;
    }

    public static String getDefaultTemplateImgName() {
        return DEFAULT_IMAGE_NAME;
    }

    public static String getDefaultTemplateImgNameAndFormat() {
        return DEFAULT_IMAGE_NAME + IMG_FORMAT_SUFFIX;
    }

    public static String getVisualisationImgOutputPath() {
        return LIB_DIR_PATH + IMG_NAME + MAPPED_COLORS_IMG_SUFFIX + VISUAL_IMG_SUFFIX + IMG_FORMAT_SUFFIX;
    }

    public static int getBeadImgPxWidth() {
        return BEAD_IMG_PX_WIDTH;
    }

    public static int getBeadImgPxHeight() {
        return BEAD_IMG_PX_HEIGHT;
    }

    public static int getHorizontalPxSpacing() {
        return HORIZONTAL_PX_SPACING;
    }

    public static int getVerticalPxSpacing() {
        return VERTICAL_PX_SPACING;
    }

    public static int getCornerArcPxWidth() {
        return CORNER_ARC_PX_WIDTH;
    }

    public static int getCornerArcPxHeight() {
        return CORNER_ARC_PX_HEIGHT;
    }

    public static String getPatternSheetName() {
        return PATTERN_SHEET_NAME;
    }

    public static String getLegendSheetName() {
        return LEGEND_SHEET_NAME;
    }

    public static String getWordChartSheetName() {
        return WORD_CHART_SHEET_NAME;
    }

    public static String getPageMapSheetName() {
        return PAGE_MAP_SHEET_NAME;
    }

    public static String getSizeSheetName() {
        return SIZE_SHEET_NAME;
    }

    public static String getCopywriteNote() {
        return COPYWRITE_NOTE;
    }

    public static int getColsPerPage() {
        return COLS_PER_PAGE;
    }

    public static int getRowsPerPage() {
        return ROWS_PER_PAGE;
    }

    public static String getHeaderFont() {
        return HEADER_FONT;
    }

    public static int getHeaderFontSize() {
        return HEADER_FONT_SIZE;
    }

    public static String getBaseFont() {
        return BASE_FONT;
    }

    public static int getBaseFontSize() {
        return BASE_FONT_SIZE;
    }

    public static String getColsRowsNumberingFont() {
        return COLS_ROWS_NUMBERING_FONT;
    }

    public static int getColsRowsNumberingFontSize() {
        return COLS_ROWS_NUMBERING_FONT_SIZE;
    }
}
