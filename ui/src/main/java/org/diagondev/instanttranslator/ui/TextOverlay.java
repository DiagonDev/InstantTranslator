package org.diagondev.instanttranslator.ui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TextOverlay {

    private final Stage stage;
    private final Label label;

    public TextOverlay() {
        stage = new Stage(StageStyle.TRANSPARENT);
        stage.setAlwaysOnTop(true);
        stage.setResizable(false);

        label = new Label();
        label.setTextFill(Color.WHITE);
        label.setStyle("""
            -fx-background-color: rgba(0,0,0,0.6);
            -fx-font-size: 18px;
            -fx-font-weight: bold;
            -fx-wrap-text: true;
        """);
        label.setPadding(new Insets(10));

        StackPane root = new StackPane(label);
        root.setMouseTransparent(true);
        root.setStyle("-fx-background-color: transparent;");

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);

        stage.setScene(scene);
    }

    public void show() {
        if (!stage.isShowing()) {
            stage.show();
        }
    }

    public void hide() {
        stage.hide();
    }

    public void close() {
        stage.close();
    }

    public void updateText(String text) {
        label.setText(text);
    }

    public void setBounds(double x, double y, double w, double h) {
        stage.setX(x);
        stage.setY(y);
        stage.setWidth(w);
        stage.setHeight(h);
    }
}
