module screen.capture {
    requires java.desktop;
    opens org.diagondev.instanttranslator.screencapture to org.diagondev.instanttranslator.ui ;
    exports org.diagondev.instanttranslator.screencapture;
}