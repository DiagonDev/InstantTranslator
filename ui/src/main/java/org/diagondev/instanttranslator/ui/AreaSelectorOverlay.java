package org.diagondev.instanttranslator.ui;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;

public class AreaSelectorOverlay {

    private double startX, startY;
    private Rectangle selectionRect;

    public interface AreaSelectedListener {
        void onAreaSelected(Rectangle area);
    }

    public void startSelection(AreaSelectedListener listener) {
        Stage overlay = new Stage();
        overlay.initStyle(StageStyle.TRANSPARENT);
        overlay.setAlwaysOnTop(true);

        Pane root = new Pane();
        root.setStyle("-fx-background-color: rgba(0,0,0,0.15);");

        Scene scene = new Scene(root, Color.TRANSPARENT);

        selectionRect = new Rectangle();
        selectionRect.setFill(Color.color(0, 0.5, 1, 0.3));
        selectionRect.setStroke(Color.BLUE);
        selectionRect.setStrokeWidth(2);

        root.getChildren().add(selectionRect);

        scene.setOnMousePressed(this::handleMousePressed);
        scene.setOnMouseDragged(this::handleMouseDragged);
        scene.setOnMouseReleased(e -> handleMouseReleased(e, overlay, listener));

        // schermo intero
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        overlay.setScene(scene);
        overlay.setX(0);
        overlay.setY(0);
        overlay.setWidth(screen.getWidth());
        overlay.setHeight(screen.getHeight());
        overlay.show();
    }

    private void handleMousePressed(MouseEvent e) {
        startX = e.getSceneX();
        startY = e.getSceneY();
        selectionRect.setX(startX);
        selectionRect.setY(startY);
        selectionRect.setWidth(0);
        selectionRect.setHeight(0);
    }

    private void handleMouseDragged(MouseEvent e) {
        double width = Math.abs(e.getSceneX() - startX);
        double height = Math.abs(e.getSceneY() - startY);

        selectionRect.setX(Math.min(startX, e.getSceneX()));
        selectionRect.setY(Math.min(startY, e.getSceneY()));
        selectionRect.setWidth(width);
        selectionRect.setHeight(height);
    }

    private void handleMouseReleased(MouseEvent e, Stage overlay, AreaSelectedListener listener) {
        overlay.close();

        Rectangle awtRect = new Rectangle(
                (int) selectionRect.getX(),
                (int) selectionRect.getY(),
                (int) selectionRect.getWidth(),
                (int) selectionRect.getHeight()
        );

        listener.onAreaSelected(awtRect);
    }
}
