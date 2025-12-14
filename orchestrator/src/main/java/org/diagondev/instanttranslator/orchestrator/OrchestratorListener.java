package org.diagondev.instanttranslator.orchestrator;

public interface OrchestratorListener {
    void onNewTranslation(String translatedText);
    void onError(String message);
}
