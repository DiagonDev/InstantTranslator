module org.diagondev.instanttranslator.ui {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.desktop;
    requires screen.capture;

    // In sostanza apro il modulo a javafx.fxml per permettergli di vedere le classi
    opens org.diagondev.instanttranslator.ui to javafx.fxml;
    exports org.diagondev.instanttranslator.ui;
}
