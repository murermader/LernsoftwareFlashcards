package ViewModel;

import Model.*;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class DeckAddController {

    public TextField StapelText = new TextField();
    private Helper helper = new Helper();

    public void handlerConfirm(ActionEvent event) {

        String deckName = StapelText.getText();

        try {

            List<Flashcard> list = new ArrayList<>();
            Deck deck = new Deck(deckName, list, Data.getCurrentUser());
            helper.saveDeckToFile(deck);

        } catch (Exception ex) {
            LogHelper.writeToLog(Level.INFO, "Fehler beim Erstellen des Stapels "+deckName + ex);
        }
        //Verweis um den Stapel zu befüllen
        helper.switchScene(event,"DeckOverview.fxml");
    }

    public void handlerBack(ActionEvent event) {
        helper.switchScene(event,"MainWindow.fxml");
    }
}
