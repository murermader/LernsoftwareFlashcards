package ViewModel;

import Model.*;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class DeckAddController {

    public TextField StapelText = new TextField();
    private Helper helper = new Helper();

    public void handlerConfirm(ActionEvent event)throws IOException{

        String deckName = StapelText.getText();
        System.out.println(deckName);

        try {

            List<Flashcard> list = new ArrayList<>();
            Deck deck = new Deck(deckName, list, Data.getCurrentUser());
            helper.saveDeckToFile(deck);

        } catch (Exception ex) {
            LogHelper.writeToLog(Level.INFO, "Fehler beim Erstellen des Stapels "+deckName + ex);
        }

        //Verweis um den Stapel zu befüllen
        helper.switchScene(event,"CardAdd.fxml");
    }

    public void handlerBack(ActionEvent event)throws IOException{
        helper.switchScene(event,"MainWindow.fxml");
    }
}
