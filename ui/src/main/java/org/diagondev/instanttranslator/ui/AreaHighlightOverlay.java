package org.diagondev.instanttranslator.ui;

import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AreaHighlightOverlay {

    private final Stage stage;
    private final Rectangle highlight;

    public AreaHighlightOverlay() {
        stage = new Stage(StageStyle.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setAlwaysOnTop(true);

        Pane root = new Pane();
        root.setMouseTransparent(true);
        root.setStyle("-fx-background-color: transparent;");

        highlight = new Rectangle();
        highlight.setStroke(Color.RED);
        highlight.setStrokeWidth(1);
        highlight.setFill(Color.TRANSPARENT);

        root.getChildren().add(highlight);

        Rectangle2D bounds = Screen.getPrimary().getBounds();
        Scene scene = new Scene(root, bounds.getWidth(), bounds.getHeight(), Color.TRANSPARENT);

        stage.setScene(scene);
    }

    public void show(java.awt.Rectangle area) {
        highlight.setX(area.x);
        highlight.setY(area.y);
        highlight.setWidth(area.width);
        highlight.setHeight(area.height);

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
}
