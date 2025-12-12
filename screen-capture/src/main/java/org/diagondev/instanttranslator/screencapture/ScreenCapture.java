package org.diagondev.instanttranslator.screencapture;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ScreenCapture {

    public BufferedImage captureFullScreen() throws AWTException {
        Robot robot = new Robot();
        Rectangle screenBounds = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        return robot.createScreenCapture(screenBounds);
    }

    public BufferedImage captureArea(Rectangle area) throws AWTException {
        Robot robot = new Robot();
        return robot.createScreenCapture(area);
    }
}
