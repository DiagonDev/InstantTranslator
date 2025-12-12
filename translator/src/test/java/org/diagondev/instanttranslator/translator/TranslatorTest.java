package org.diagondev.instanttranslator.translator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TranslatorTest {

    static Translator translator;

    final String textIta = "Il veloce gatto marrone salta sopra il cane pigro.\n" +
            "questa frase contiene tutte le lettere dell’alfabeto italiano.\n" +
            "Il software OCR dovrebbe riuscire a riconoscere correttamente questo testo.";

    final String textEng = "The quick brown fox jumps over the lazy dog.\n" +
            "This sentence contains every letter of the English alphabet.\n" +
            "OCR software should be able to recognize this text clearly.";

    @BeforeAll
    static void setup() throws Exception {
        translator = new Translator(); // Assicurati che .env contenga DEEPL_API_KEY
    }

    @Test
    void testTranslateString() throws Exception {

            String result = translator.translateString(textEng, "EN", "IT");

        assertNotNull(result, "La traduzione non dovrebbe essere null");
        assertFalse(result.isEmpty(), "La traduzione non dovrebbe essere vuota");
        System.out.println("Traduzione da Inglese a italiano: " + result);

        result = translator.translateString(textIta, "IT", "EN-US");
        assertNotNull(result, "La traduzione non dovrebbe essere null");
        assertFalse(result.isEmpty(), "La traduzione non dovrebbe essere vuota");
        System.out.println("Traduzione da italiano a inglese: " + result);
    }

    /*
    @Test
    void testTranslateList() throws Exception {
        List<String> input = List.of("Good morning", "How are you?");
        List<String> results = translator.translateList(input, "en", "it");

        assertNotNull(results, "La lista dei risultati non dovrebbe essere null");
        assertEquals(input.size(), results.size(), "La lista dei risultati deve avere la stessa dimensione dell'input");

        for (String t : results) {
            assertNotNull(t, "Ogni traduzione non dovrebbe essere null");
            assertFalse(t.isEmpty(), "Ogni traduzione non dovrebbe essere vuota");
            System.out.println("Traduzione lista: " + t);
        }
    }
    */
}
