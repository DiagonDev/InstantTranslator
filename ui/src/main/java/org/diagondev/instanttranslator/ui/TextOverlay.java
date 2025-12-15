package org.diagondev.instanttranslator.ui;

import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TextOverlay {

    private final Stage stage;
    private final Label label;

    private double dragOffsetX;
    private double dragOffsetY;

    public TextOverlay() {
        stage = new Stage(StageStyle.TRANSPARENT);
        stage.setAlwaysOnTop(true);
        stage.setResizable(true);

        label = new Label();
        label.setTextFill(Color.WHITE);
        label.setWrapText(true);
        label.setStyle("""
            -fx-background-color: rgba(0,0,0,0.65);
            -fx-font-size: 18px;
            -fx-font-weight: bold;
        """);
        label.setPadding(new Insets(10));

        StackPane root = new StackPane(label);
        root.setStyle("-fx-background-color: transparent;");
        root.setPickOnBounds(false);

        enableDrag(root);

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);

        stage.setScene(scene);

        // Dimensioni iniziali sane
        stage.setWidth(400);
        stage.setHeight(200);

        // Posizione iniziale: alto a sinistra
        moveToTopLeft();
    }

    private void enableDrag(StackPane root) {
        root.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
            dragOffsetX = e.getScreenX() - stage.getX();
            dragOffsetY = e.getScreenY() - stage.getY();
        });

        root.addEventFilter(MouseEvent.MOUSE_DRAGGED, e -> {
            stage.setX(e.getScreenX() - dragOffsetX);
            stage.setY(e.getScreenY() - dragOffsetY);
        });
    }

    public void moveToTopLeft() {
        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        stage.setX(bounds.getMinX() + 20);
        stage.setY(bounds.getMinY() + 40);
    }

    public void show() {
       /* stage.setX(area.x);
        stage.setY(area.y);
        stage.setWidth(area.width);
        stage.setHeight(area.height);*/

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
}
