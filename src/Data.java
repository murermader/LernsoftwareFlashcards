import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

//Klasse die einen Überblick über alle Decks hat.
class Data {

    private static String currentUser;
    private static String currentDeckName;
    private static List<Deck> allDecks = new ArrayList<>();
    private static List<String> allUsers = new ArrayList<>();
    public boolean isEmpty;

    Data() {

        try {
            Helper helper = new Helper();
            List<String> deckNames = new ArrayList<>();
            deckNames.clear();
            deckNames = helper.getDeckNames();

            List<String> toRemove = new ArrayList<>();
            for (String file: deckNames) {
                if(file.equals("Users.txt")){
                    toRemove.add(file);
                }
            }
            deckNames.removeAll(toRemove);

            if (deckNames.size() == 0) {
                isEmpty = true;
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

    public static String getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(String currentUser) {
        Data.currentUser = currentUser;
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
