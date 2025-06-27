package org.example;

import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.interactive.annotation.*;
import org.apache.pdfbox.pdmodel.interactive.action.*;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDPageDestination;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDPageFitDestination;
import org.apache.pdfbox.text.*;

import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PdfLinker {

    public static void main(String[] args) throws IOException {
        File publishedPdf = new File(Config.getPublishPdfFilePath());
        try (PDDocument doc = PDDocument.load(publishedPdf)) {
            int layoutPageIndex = 4; // np. strona z "mapą stron" (licząc od zera)
            linkPages(doc, layoutPageIndex, 1, 3); // zakładając, że mamy 1. do x. na mapie stron
            doc.save("linked_output.pdf");
        }
    }

    public static void linkPages(PDDocument doc, int mapPageIndex, int start, int end) throws IOException {
        // Krok 1: znajdź stronę docelową dla każdego logicznego numeru (- N -)
        Map<Integer, Integer> numberToPhysicalPage = new HashMap<>();
        Pattern pattern = Pattern.compile("-\\s+(\\d+)\\s+-");

        for (int i = 0; i < doc.getNumberOfPages(); i++) {
            String text = getPageText(doc, i);
            Matcher matcher = pattern.matcher(text);
            while (matcher.find()) {
                int number = Integer.parseInt(matcher.group(1));
                numberToPhysicalPage.put(number, i);
            }
        }

        // Krok 2: przeszukaj stronę mapy i znajdź pozycje tekstu "1.", "2." itd.
        PDPage mapPage = doc.getPage(mapPageIndex);
        TextLocator locator = new TextLocator();
        List<TextLocator.TextHit> hits = locator.locateText(doc, mapPageIndex);

        for (int i = start; i <= end; i++) {
            String targetText = i + ".";
            Optional<TextLocator.TextHit> match = hits.stream()
                    .filter(h -> h.text.equals(targetText))
                    .findFirst();

            if (match.isPresent()) {
                int targetLogicalNumber = i + 2; // np. "1." -> "- 3 -"
                Integer targetPageIndex = numberToPhysicalPage.get(targetLogicalNumber);

                if (targetPageIndex != null) {
                    addLinkAnnotation(doc, mapPage, match.get().bounds, targetPageIndex);
                    System.out.println("Dodano link z " + targetText + " do strony " + targetLogicalNumber);
                } else {
                    System.out.println("Nie znaleziono strony dla - " + targetLogicalNumber + " -");
                }
            } else {
                System.out.println("Nie znaleziono wystąpienia tekstu: " + targetText);
            }
        }
    }

    private static String getPageText(PDDocument doc, int pageIndex) throws IOException {
        PDFTextStripper stripper = new PDFTextStripper();
        stripper.setStartPage(pageIndex + 1);
        stripper.setEndPage(pageIndex + 1);
        return stripper.getText(doc);
    }

    private static void addLinkAnnotation(PDDocument doc, PDPage page, Rectangle2D.Float bounds, int targetPageIndex) {
        PDAnnotationLink link = new PDAnnotationLink();
        PDActionGoTo action = new PDActionGoTo();
        PDPageDestination dest = new PDPageFitDestination();
        dest.setPage(doc.getPage(targetPageIndex));
        action.setDestination(dest);
        link.setAction(action);

        PDRectangle rect = new PDRectangle(bounds.x, bounds.y, bounds.width, bounds.height);
        link.setRectangle(rect);

        try {
            page.getAnnotations().add(link);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class TextLocator extends PDFTextStripper {
        List<TextHit> hits = new ArrayList<>();

        public TextLocator() throws IOException {
            setSortByPosition(true);
        }

        public List<TextHit> locateText(PDDocument doc, int pageIndex) throws IOException {
            this.hits.clear();
            this.setStartPage(pageIndex + 1);
            this.setEndPage(pageIndex + 1);
            this.getText(doc);
            return hits;
        }

        @Override
        protected void writeString(String string, List<TextPosition> textPositions) throws IOException {
            if (string.matches("\\d+\\.")) {
                TextPosition first = textPositions.get(0);
                float x = first.getXDirAdj();
                float y = first.getYDirAdj();
                float w = (float) textPositions.stream().mapToDouble(TextPosition::getWidth).sum();
                float h = first.getHeightDir();
                Rectangle2D.Float bounds = new Rectangle2D.Float(x, y, w, h);
                hits.add(new TextHit(string, bounds));
            }
        }

        static class TextHit {
            String text;
            Rectangle2D.Float bounds;

            public TextHit(String text, Rectangle2D.Float bounds) {
                this.text = text;
                this.bounds = bounds;
            }
        }
    }
}

