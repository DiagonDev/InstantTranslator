package org.diagondev.instanttranslator.screencapture;

import org.junit.jupiter.api.Test;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ScreenCaptureTest {

    @Test
    public void testFullScreenCapture() throws Exception {
        ScreenCapture sc = new ScreenCapture();
        BufferedImage img = sc.captureFullScreen();

        assertNotNull(img);

        ImageIO.write(img, "png", new File("test-fullscreen.png"));
        System.out.println("Screenshot salvato in test-fullscreen.png");
    }

    @Test
    public void testAreaCapture() throws Exception {
        ScreenCapture sc = new ScreenCapture();
        Rectangle area = new Rectangle(100, 100, 300, 300);
        BufferedImage img = sc.captureArea(area);

        assertNotNull(img);

        ImageIO.write(img, "png", new File("test-area.png"));
        System.out.println("Screenshot area salvato in test-area.png");
    }
}
