import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

//Klasse die einen Überblick über alle Decks hat.
class Data {

    private static String currentUser;
    private static String currentDeckName;
    public static List<Deck> currentUserDecks = new ArrayList<>();
    private static List<String> allUsers = new ArrayList<>();
    public boolean isEmpty;

    Data() {

        try {
            Helper helper = new Helper();
            List<String> deckNames = new ArrayList<>();
            deckNames.clear();
            deckNames = helper.getDeckNames();

            //Damit die Users File nicht in den Decks auftaucht.
            List<String> toRemove = new ArrayList<>();
            for (String file : deckNames) {
                if (file.equals("Users.txt")) {
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
                currentUserDecks.clear();
                List<Deck> allDecks = new ArrayList<>();

                for (String name : deckNames) {
                    allDecks.add(helper.getDeckFromFile(name));
                }

                for (Deck deck : allDecks) {
                    if(deck.getOwner().equals(Data.getCurrentUser()) || deck.getOwner().equals("Beispieldeck")){
                        currentUserDecks.add(deck);
                    }
                }
            }

            //Users
            if (helper.getUsersFromFile() != null) {
                allUsers = helper.getUsersFromFile();
            } else {
                LogHelper.writeToLog(Level.INFO, "Keine Usernamen vorhanden. Userliste bleibt leer.");
            }
            if (currentUser != null) {
                LogHelper.writeToLog(Level.INFO, "Aktueller Benutzer: " + currentUser);
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
        LogHelper.writeToLog(Level.INFO, "Current User neu gesetzt als: " + currentUser);
    }

    public static List<String> getAllUsers() {
        return allUsers;
    }

    public static void setAllUsers(List<String> allUsersNew) {
        allUsers = allUsersNew;
    }

    //Methoden
    public List<Deck> getListOfDecks() {
        return currentUserDecks;
    }

    static String getCurrentDeckName() {
        return currentDeckName;
    }

    static void setCurrentDeckName(String currentDeckName) {
        Data.currentDeckName = currentDeckName;
    }

    Deck getCurrentDeck() {

        for (Deck deck : currentUserDecks) {
            if (deck.getName().equals(currentDeckName)) {
                return deck;
            }
        }
        return null;
    }
}
