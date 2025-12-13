package org.diagondev.instanttranslator.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import org.diagondev.instanttranslator.screencapture.ScreenCapture;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ScreenshotController {

    @FXML
    private Button button_screenshot;

    @FXML
    private Label label_hotkey;

    @FXML
    private TextField text_hotkey;

    @FXML
    private void initialize() {
    }

    public void registerMacro(KeyEvent keyEvent) {
        String hotkey = keyEvent.getCharacter();
        System.out.println("hotkey: " + hotkey);
        text_hotkey.setText(hotkey);
    }

    public void screenshot(ActionEvent actionEvent) {
        AreaSelectorOverlay selectorOverlay = new AreaSelectorOverlay();
        selectorOverlay.startSelection(area -> {
            // Thread separato per non bloccare la UI
            new Thread(() -> {
                ScreenCapture screenCapture = new ScreenCapture();
                try {
                    BufferedImage img = screenCapture.captureArea(area);
                    File outputFile = new File("screenshot.png");
                    ImageIO.write(img, "png", outputFile);
                    System.out.println("Screenshot salvato in: " + outputFile.getAbsolutePath());
                } catch (AWTException | IOException e) {
                    e.printStackTrace();
                }
            }).start();
        });
    }


}
