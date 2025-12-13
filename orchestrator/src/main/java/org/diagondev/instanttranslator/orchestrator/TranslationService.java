package org.diagondev.instanttranslator.orchestrator;

import org.diagondev.instanttranslator.ocr.Ocr;
import org.diagondev.instanttranslator.screencapture.ScreenCapture;
import org.diagondev.instanttranslator.translator.Translator;
import java.awt.*;
import java.awt.image.BufferedImage;

public class TranslationService {
    ScreenCapture screenCapture;
    Ocr ocr;
    Translator translator;

    public TranslationService() {
        this.screenCapture = new ScreenCapture();
        this.ocr = new Ocr();
        try {
            this.translator = new Translator();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public String translateArea(Rectangle rectangle, String sourceLanguage, String targetLanguage) {
        String result = "";
        try {
            BufferedImage image = screenCapture.captureArea(rectangle);
            String ocrResult = ocr.textWithOcr(image, "eng");
            result = translator.translateString(ocrResult, sourceLanguage, targetLanguage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
