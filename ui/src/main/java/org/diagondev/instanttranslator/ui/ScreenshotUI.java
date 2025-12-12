package org.diagondev.instanttranslator.ui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ScreenshotUI {

    public void start(Stage stage) {

        Button selectAreaBtn = new Button("Seleziona area schermo");
        selectAreaBtn.setOnAction(e -> {
            AreaSelectorOverlay selector = new AreaSelectorOverlay();
            selector.startSelection(rect -> {
                System.out.println("Area selezionata: " + rect);
                // Qui potrai chiamare il modulo screen-capture
            });
        });

        VBox root = new VBox(20, selectAreaBtn);
        root.setPadding(new Insets(20));

        stage.setScene(new Scene(root, 300, 200));
        stage.setTitle("InstantTranslator - UI");
        stage.show();
    }
}
