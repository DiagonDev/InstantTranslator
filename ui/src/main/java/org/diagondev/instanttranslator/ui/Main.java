package org.diagondev.instanttranslator.ui;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        ScreenshotUI ui = new ScreenshotUI();
        ui.start(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
