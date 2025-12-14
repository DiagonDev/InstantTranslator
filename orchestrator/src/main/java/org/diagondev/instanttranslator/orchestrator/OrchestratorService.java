package org.diagondev.instanttranslator.orchestrator;

import org.diagondev.instanttranslator.ocr.Ocr;
import org.diagondev.instanttranslator.screencapture.ScreenCapture;
import org.diagondev.instanttranslator.translator.TranslationCallback;
import org.diagondev.instanttranslator.translator.TranslatorClient;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class OrchestratorService {
    ScreenCapture screenCapture;
    Ocr ocr;

    TranslatorClient translatorClient;

    private OrchestratorListener uiListener;

    private ScheduledExecutorService executor;

    public OrchestratorService() {
        this.screenCapture = new ScreenCapture();
        this.ocr = new Ocr();
        try {
            this.translatorClient = new TranslatorClient();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void setListener(OrchestratorListener listener) {
        this.uiListener = listener;
    }

    public void translateOnce(Rectangle rectangle, String sourceLanguage, String targetLanguage) {
        executor = Executors.newSingleThreadScheduledExecutor();
        executor.execute(
                () -> translateAreaAsync(rectangle, sourceLanguage, targetLanguage)
        );
    }

    public void startContinuousTranslation(Rectangle area, String src, String tgt) {
        stop();
        executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(
                () -> translateAreaAsync(area, src, tgt),
                0,
                1,
                TimeUnit.SECONDS
        );
    }

    public void stop() {
        if (executor != null) {
            executor.shutdownNow();
        }
    }

    public void translateAreaAsync(
            Rectangle area,
            String sourceLang,
            String targetLang
    ) {
        new Thread(() -> {
            try {
                BufferedImage image = screenCapture.captureArea(area);
                String text = ocr.textWithOcr(image, "eng");

                translatorClient.translateAsync(
                        text,
                        sourceLang,
                        targetLang,
                        new TranslationCallback() {

                            @Override
                            public void onSuccess(String translatedText) {
                                if (uiListener != null) {
                                    uiListener.onNewTranslation(translatedText);
                                }
                            }

                            @Override
                            public void onError(Throwable error) {
                                if (uiListener != null) {
                                    uiListener.onError(error.getMessage());
                                }
                            }
                        }
                );

            } catch (Exception e) {
                if (uiListener != null) {
                    uiListener.onError(e.getMessage());
                }
            }
        }).start();
    }

}
