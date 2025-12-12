package org.diagondev.instanttranslator.ocr;


import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.diagondev.instanttranslator.screencapture.ScreenCapture;

import java.awt.*;

@Deprecated
public class Main {
    public static void main(String[] args) {
        ITesseract instance = new Tesseract();
        instance.setDatapath("C:/Program Files/Tesseract-OCR/tessdata");
        instance.setLanguage("ita"); // o "eng"

        try {
            ScreenCapture screenCapture = new ScreenCapture();
            Rectangle area = new Rectangle(100, 100, 300, 300);
            String result = instance.doOCR(screenCapture.captureArea(area));
            System.out.println(result);
        }catch (TesseractException e ){
            System.err.println(e.getMessage());
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }
}