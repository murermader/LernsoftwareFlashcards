import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

//Diese Klasse ist eine Schnittstelle zwischen den Decks und der GUI.
class Data {

    private static String currentDeckName = "";
    private List<Deck> allDecks = new ArrayList<>();

    Data(){
        Helper helper = new Helper();
        List<String> deckNames = helper.getDeckNames();
        if(deckNames == null){
            LogHelper.writeToLog(Level.INFO, "Es konnnten keine Decks gefunden werden.");
        }
        else{
            //Um Flashkarten in Apdata\local\flashcards zu erstellen
            helper.createSampleDecks();
            //Für jeden Stapel Flashcards ein Deck erstellen.
            for (String name: deckNames) {
                List<Flashcard> cards;
                cards = helper.FlashcardListFromFile(name);
                Deck deck = new Deck(name, cards);
                allDecks.add(deck);
            }
        }
    }

    //Methoden
    List<Deck> getDecks(){
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
