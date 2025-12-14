package org.diagondev.instanttranslator.ui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import org.diagondev.instanttranslator.orchestrator.OrchestratorListener;
import org.diagondev.instanttranslator.orchestrator.OrchestratorService;
import java.awt.*;


public class ScreenshotController implements OrchestratorListener {

    public Label label_from;
    public Label label_to;
    @FXML
    private Button button_screenshot;
    @FXML
    private Button button_area;

    @FXML
    private Label label_hotkey;

    @FXML
    private TextField text_hotkey;

    @FXML
    private ComboBox<String> combo_from;

    @FXML
    private ComboBox<String> combo_to;


    @FXML
    private TextArea text_translated;


    private AreaSelectorOverlay selectorOverlay;

    private OrchestratorService orchestratorService;

    @FXML
    private void initialize() {
        combo_from.getItems().addAll("EN", "IT");
        combo_to.getItems().addAll("EN-US", "IT");

        combo_from.setValue("EN");
        combo_to.setValue("IT");
        selectorOverlay = new AreaSelectorOverlay();
        orchestratorService = new OrchestratorService();
        orchestratorService.setListener(this);

    }

    public void registerMacro(KeyEvent keyEvent) {
        String hotkey = keyEvent.getCharacter();
        System.out.println("hotkey: " + hotkey);
        text_hotkey.setText(hotkey);
    }

    public void screenshot(ActionEvent actionEvent) {
        selectorOverlay.startSelection(area -> {
            Rectangle awtRect = toAwtRect(area);
            orchestratorService.translateOnce(awtRect, combo_from.getValue(),  combo_to.getValue());
        });
    }
    public void captureArea(ActionEvent actionEvent) {
        selectorOverlay.startSelection(area -> {
            Rectangle awtRect = toAwtRect(area);
            orchestratorService.startContinuousTranslation(awtRect, combo_from.getValue(),  combo_to.getValue());
        });

    }

    @Override
    public void onNewTranslation(String translatedText) {
        Platform.runLater(() ->
                text_translated.setText(translatedText)
        );
    }

    @Override
    public void onError(String message) {
        Platform.runLater(() ->
                text_translated.setText("Errore: " + message)
        );
    }

    public Rectangle toAwtRect(javafx.scene.shape.Rectangle area) {
        return new Rectangle(
                (int) area.getX(),
                (int) area.getY(),
                (int) area.getWidth(),
                (int) area.getHeight());
    }
}
