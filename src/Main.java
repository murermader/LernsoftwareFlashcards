import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;


public class Main extends Application {

    @Override
    public void start(Stage stage){
        try {
            //Auf false setzen, falls du den Code "ausschalten" willst!

            if(true){
                Helper helper = new Helper();
                List<String> deckNames = helper.getDeckNames();
                if(deckNames == null){
                    LogHelper.writeToLog(Level.INFO, "Es konnnten keine Decks gefunden werden.");
                }
                else{

                    //Um Flashkarten in Apdata\local\flashcards zu erstellen
                    helper.createSampleDecks();

                    List<Deck> allDecks = new ArrayList<>();

                    //Für jeden Stapel Flashcards ein Deck erstellen.
                    for (String name: deckNames) {
                        List<Flashcard> cards;
                        cards = helper.FlashcardListFromFile(name);
                        Deck deck = new Deck(name, cards);
                        allDecks.add(deck);
                    }
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