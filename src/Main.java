import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main extends Application {

    @Override
    public void start(Stage stage){
        try {

            //Auf false setzen, falls du den Code "ausschalten" willst!
            boolean TESTDATEN = true;

            if(TESTDATEN){

                Helper helper = new Helper();
                List<String> deckNames = helper.getDeckNames();
                //Um Flashkarten in Apdata\local\flashcards zu erstellen
                //Methoden noch nicht gründlich getestet!
                helper.createSampleDecks();

                //Liste enhält Decks: Jedes Deck enhält eine Liste von Flashkarten und einen Namen
                List<Deck> allDecks = new ArrayList<>();

                for (String name: deckNames) {
                    List<Flashcard> cards;
                    cards = helper.FlashcardListFromFile(name);
                    Deck deck = new Deck(name, cards);
                    allDecks.add(deck);
                }

                //Namen aller Decks für die Stapelanzeige
                for (String string: Deck.nameList ) {
                    System.out.println(string);
                }
            }

            Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        // In der Main Methode rufen wir launch auf, welches unser Fenster startet
        launch(args);
    }
}