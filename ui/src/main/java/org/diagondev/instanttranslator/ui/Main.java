package org.diagondev.instanttranslator.ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class  Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/ui/main.fxml")
        );

        Scene scene = new Scene(loader.load());
        stage.setTitle("InstantTranslator");
        stage.setScene(scene);
        stage.show();
        ScreenshotController controller = loader.getController();

        stage.setOnCloseRequest(event -> {
            controller.shutdown();
            Platform.exit();
            System.exit(0);
        });
    }

    public static void main(String[] args) {
        launch();
    }
}