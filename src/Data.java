import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

//Klasse die einen Überblick über alle Decks hat.
class Data {

    private static String currentDeckName = "";
    private static List<Deck> allDecks = new ArrayList<>();

    Data(){
        try{
            Helper helper = new Helper();
            List<String> deckNames = helper.getDeckNames();
            //Es existieren keine Decks: SampleDecks erstellen
            if(deckNames.size() == 0){
                LogHelper.writeToLog(Level.INFO, "Erstelle Sample Decks für Testzwecke");
                //Sample Decks erstellen
                helper.createSampleDeck("FirstDeck", 100);
                helper.createSampleDeck("SecondDeck", 50);
                helper.createSampleDeck("ThirdDeck", 20);
                deckNames = helper.getDeckNames();
            }
            //Für jeden Stapel Flashcards ein Deck erstellen.
            for (String name: deckNames) {
                allDecks.add(helper.getDeckFromFile(name));
            }

        }
        catch(Exception ex){
            LogHelper.writeToLog(Level.INFO, "Fehler beim Initialisieren des Decks: " +ex);
        }
    }

    //Methoden
    List<Deck> getListOfDecks(){
        return allDecks;
    }

    public static String getCurrentDeckName() {
        return currentDeckName;
    }
    public static void setCurrentDeckName(String currentDeckName) {
        Data.currentDeckName = currentDeckName;
    }

    public Deck getCurrentDeck(){

        for (Deck deck: allDecks) {
            if(deck.getName().equals(currentDeckName)){
                return deck;
            }
        }
        return null;
    }

}
