import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
public class DeckAddController {

    public TextField StapelText = new TextField();
    Helper x = new Helper();

    public void handlerConfirm(ActionEvent event)throws IOException{
        String deckName = StapelText.getText();
        System.out.println(deckName);
        try {

            List<Flashcard> list = new ArrayList<>();
            Deck deck = new Deck(deckName, list);


            x.saveDeckToFile(deck, deckName);
        } catch (Exception ex) {
            LogHelper.writeToLog(Level.INFO, "Fehler beim Erstellen des Stapels "+deckName + ex);
        }

        //Verweis um den Stapel zu befüllen
        Parent CardAddViewParent = FXMLLoader.load(getClass().getResource("GUI/CardAdd.fxml"));
        Scene CardAddViewScene = new Scene(CardAddViewParent);

        //This line gets the Stage information
        Stage window1 = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window1.setScene(CardAddViewScene);
        window1.show();
    }

    public void handlerBack(ActionEvent event)throws IOException{
        Parent mainViewParent = FXMLLoader.load(getClass().getResource("GUI/MainWindow.fxml"));
        Scene mainViewScene = new Scene(mainViewParent);
        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(mainViewScene);
        window.show();
    }
}
