module translator {
    requires deepl.java;
    requires java.dotenv;
    opens org.diagondev.instanttranslator.translator to org.diagondev.instanttranslator.orchestrator;
    exports org.diagondev.instanttranslator.translator;
}