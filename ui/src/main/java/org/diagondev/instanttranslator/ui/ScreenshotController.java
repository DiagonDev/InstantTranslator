package org.diagondev.instanttranslator.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import org.diagondev.instanttranslator.orchestrator.TranslationService;
import java.awt.*;


public class ScreenshotController {

    @FXML
    private Button button_screenshot;

    @FXML
    private Label label_hotkey;

    @FXML
    private TextField text_hotkey;

    @FXML
    private TextField text_from;

    @FXML
    private TextField text_to;

    @FXML
    private TextArea text_translated;

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

            // separated thread for UI
            new Thread(() -> {
                Rectangle awtRect = new Rectangle(
                        (int) area.getX(),
                        (int) area.getY(),
                        (int) area.getWidth(),
                        (int) area.getHeight()
                );
                TranslationService translationService = new TranslationService();
                System.out.println(text_from.getText());
                text_translated.setText(translationService.translateArea(awtRect, text_from.getText(),  text_to.getText()));
            }).start();
        });
    }


}
