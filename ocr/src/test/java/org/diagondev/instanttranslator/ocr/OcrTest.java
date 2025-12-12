package org.diagondev.instanttranslator.ocr;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

public class OcrTest {

    @Test
    public void testTextWithOcrFromResources() throws IOException {

        File imageFile = new File("C:/Users/Diagon/Desktop/Progetti/InstantTranslator/mock/test-ita.png");
        assertNotNull(imageFile, "Immagine di test non trovata nei resources");
        BufferedImage testIta = ImageIO.read(imageFile);

        Ocr ocr = new Ocr();

        String result = ocr.textWithOcr(testIta, "ita");

        assertNotNull(result);
        assertFalse(result.isEmpty(), "OCR dovrebbe riconoscere qualche testo");
        System.out.println("Test OCR output: " + result);

        imageFile = new File("C:/Users/Diagon/Desktop/Progetti/InstantTranslator/mock/test-eng.png");
        assertNotNull(imageFile, "Immagine di test non trovata nei resources");
        BufferedImage testEng = ImageIO.read(imageFile);
        result = ocr.textWithOcr(testEng, "eng");

        assertNotNull(result);
        assertFalse(result.isEmpty(), "OCR dovrebbe riconoscere qualche testo");
        System.out.println("Test OCR output: " + result);
    }
}
