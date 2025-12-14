package org.diagondev.instanttranslator.translator;

public interface TranslationCallback {
    void onSuccess(String translatedText);
    void onError(Throwable error);
}
