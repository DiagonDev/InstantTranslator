package org.diagondev.instanttranslator.translator;

import com.deepl.api.DeepLClient;
import com.deepl.api.TextResult;
import io.github.cdimascio.dotenv.Dotenv;

import java.util.List;

public class Translator {
    DeepLClient client;

    public Translator() throws Exception {
        Dotenv dotenv = Dotenv.configure()
                .directory("C:/Users/Diagon/Desktop/Progetti/InstantTranslator/translator")
                .filename("api-key.env")
                .load();
        String authKey = dotenv.get("DEEPL_API_KEY");
        if (authKey == null) {
            System.err.println("API KEY NOT FOUND");
            System.exit(-1);
        }
        client = new DeepLClient(authKey);
    }
    public List<String> translateList(List<String> text, String sourceLang, String targetLang) throws Exception {
        List<TextResult> results = client.translateText(text, sourceLang, targetLang);
        return results.stream().map(TextResult::getText).toList();
    }
    public String translateString(String text, String sourceLang, String targetLang) throws Exception {
        TextResult result = client.translateText(text, sourceLang, targetLang);
        return result.getText();
    }

}
