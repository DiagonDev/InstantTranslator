package org.diagondev.instanttranslator.translator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TranslatorClientTest {

    static TranslatorClient translatorClient;

    final String textIta = """
            Il veloce gatto marrone salta sopra il cane pigro.
            questa frase contiene tutte le lettere dell’alfabeto italiano.
            Il software OCR dovrebbe riuscire a riconoscere correttamente questo testo.""";

    final String textEng = """
            The quick brown fox jumps over the lazy dog.
            This sentence contains every letter of the English alphabet.
            OCR software should be able to recognize this text clearly.""";

    @BeforeAll
    static void setup() throws Exception {
        translatorClient = new TranslatorClient(); // Assicurati che .env contenga DEEPL_API_KEY
    }

    @Test
    void testTranslateString() throws Exception {

            String result = translatorClient.translateString(textEng, "EN", "IT");

        assertNotNull(result, "La traduzione non dovrebbe essere null");
        assertFalse(result.isEmpty(), "La traduzione non dovrebbe essere vuota");
        System.out.println("Traduzione da Inglese a italiano: " + result);

        result = translatorClient.translateString(textIta, "IT", "EN-US");
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
