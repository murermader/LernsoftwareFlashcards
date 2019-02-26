import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

//Klasse die einen Überblick über alle Decks hat.
class Data {

    private static String currentDeckName;
    private static List<Deck> allDecks = new ArrayList<>();
    private boolean isEmpty;

    //Soll das alles machen was alle Decks beineinflusst.
    Data() {
        try {
            Helper helper = new Helper();
            List<String> deckNames = new ArrayList<>();
            deckNames.clear();
            deckNames = helper.getDeckNames();
            //Es existieren keine Decks: SampleDecks erstellen
            if (deckNames.size() == 0) {
                isEmpty = true;
                //Keine Decks erstellen, sondern
                LogHelper.writeToLog(Level.INFO, "Es sind keine Decks vorhanden.");
            } else {
                isEmpty = false;
                //Für jeden Stapel Flashcards ein Deck erstellen.
                allDecks.clear();
                for (String name : deckNames) {
                    allDecks.add(helper.getDeckFromFile(name));
                }
            }
        } catch (Exception ex) {
            LogHelper.writeToLog(Level.INFO, "Fehler beim Initialisieren des Decks: " + ex);
        }
    }

    //Methoden
    List<Deck> getListOfDecks() {
        return allDecks;
    }

    static String getCurrentDeckName() {
        return currentDeckName;
    }

    static void setCurrentDeckName(String currentDeckName) {
        Data.currentDeckName = currentDeckName;
    }

    Deck getCurrentDeck() {

        for (Deck deck : allDecks) {
            if (deck.getName().equals(currentDeckName)) {
                return deck;
            }
        }
        return null;
    }
}
