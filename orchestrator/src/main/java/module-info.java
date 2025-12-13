module orchestrator {
    requires java.desktop;
    requires screen.capture;
    requires translator;
    requires ocr;
    opens org.diagondev.instanttranslator.orchestrator to org.diagondev.instanttranslator.ui ;
    exports org.diagondev.instanttranslator.orchestrator;
}