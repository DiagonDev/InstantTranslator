package org.diagondev.instanttranslator.ocr;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Ocr {
    /**
     * Uses tess4j's OCR to extract a text with from image
     * @param image the {@code BufferedImage} containing the image from which you want to extract the string
     * @param language the {@code String} used to set the language of the tesseract
     * @return result the {@code String} extracted with ocr from image
     */
    public String textWithOcr(BufferedImage image, String language) {
        ITesseract instance = new Tesseract();
        instance.setDatapath("C:/Program Files/Tesseract-OCR/tessdata");
        instance.setLanguage(language); // "ita" or "eng"
        String result = "";
        try {
            result = instance.doOCR(image);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
        return result;
    }
}
