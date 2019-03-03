package ViewModel;

import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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
        Parent CardAddViewParent = FXMLLoader.load(getClass().getClassLoader().getResource("View/CardAdd.fxml"));
        Scene CardAddViewScene = new Scene(CardAddViewParent);
        Stage window1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window1.setScene(CardAddViewScene);
        window1.show();
    }

    public void handlerBack(ActionEvent event)throws IOException{
        Parent mainViewParent = FXMLLoader.load(getClass().getClassLoader().getResource("View/MainWindow.fxml"));
        Scene mainViewScene = new Scene(mainViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(mainViewScene);
        window.show();
    }
}
