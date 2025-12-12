package org.diagondev.instanttranslator.screencapture;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Deprecated
public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Test Robot\n");

        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        BufferedImage img;
        try {
            Robot robot = new Robot();
            img = robot.createScreenCapture(screenRect);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ImageIO.write(img, "png", new File("test.png"));
        System.out.println("Screenshot salvato!");
    }
}