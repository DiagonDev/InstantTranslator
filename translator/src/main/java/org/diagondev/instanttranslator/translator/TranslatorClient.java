package org.diagondev.instanttranslator.translator;

import com.deepl.api.DeepLClient;
import com.deepl.api.TextResult;
import io.github.cdimascio.dotenv.Dotenv;

import java.util.List;

public class TranslatorClient {
    DeepLClient deepLClient;

    public TranslatorClient(){
        Dotenv dotenv = Dotenv.configure()
                .directory("C:/Users/Diagon/Desktop/Progetti/InstantTranslator/translator")
                .filename("api-key.env")
                .load();
        String authKey = dotenv.get("DEEPL_API_KEY");
        if (authKey == null) {
            System.err.println("API KEY NOT FOUND");
            System.exit(-1);
        }
        deepLClient = new DeepLClient(authKey);
    }
    public List<String> translateList(List<String> text, String sourceLang, String targetLang) throws Exception {
        List<TextResult> results = deepLClient.translateText(text, sourceLang, targetLang);
        return results.stream().map(TextResult::getText).toList();
    }
    public String translateString(String text, String sourceLang, String targetLang) throws Exception {
        TextResult result = deepLClient.translateText(text, sourceLang, targetLang);
        return result.getText();
    }

    public void translateAsync(
            String text,
            String sourceLang,
            String targetLang,
            TranslationCallback callback
    ) {
        new Thread(() -> {
            try {
                TextResult result = deepLClient.translateText(text, sourceLang, targetLang);
                callback.onSuccess(result.getText());
            } catch (Exception e) {
                callback.onError(e);
            }
        }).start();
    }

}
