import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class CardIndexController {

    private static final Path appDirectoryLog = Paths.get(System.getenv("LOCALAPPDATA"), "flashcards", "Log");
    private static final Path appDirectoryRoot = Paths.get(System.getenv("LOCALAPPDATA"), "flashcards");
    private Path osXDirectory = Paths.get(System.getenv("user.home"), "Library", "Application Support", "flashcards");
    public  Data data = new Data();
    public  Helper helper = new Helper();
    public ListView CardList = new ListView<String>();

    private ObservableList<String> cardNames = FXCollections.observableArrayList();

    @FXML
    public void initialize(){


        Deck currentDeck = data.getCurrentDeck();
        if (currentDeck != null) {

            for (Flashcard card: currentDeck.getCards()) {
                cardNames.add(card.getFront());
            }
            //noinspection unchecked
            CardList.setItems(cardNames);
        }
    }


    public void handlerBack(ActionEvent event) throws IOException {

        Parent mainViewParent = FXMLLoader.load(getClass().getResource("GUI/MainWindow.fxml"));
        Scene mainViewScene = new Scene(mainViewParent);
        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(mainViewScene);
        window.show();
    }

    public void handlerCardAdd(ActionEvent event) throws IOException {
        Parent mainViewParent = FXMLLoader.load(getClass().getResource("GUI/CardAdd.fxml"));
        Scene mainViewScene = new Scene(mainViewParent);
        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(mainViewScene);
        window.show();
    }

    public void handlerCardEdit(ActionEvent event) throws IOException {
        Parent mainViewParent = FXMLLoader.load(getClass().getResource("GUI/CardEdit.fxml"));
        Scene mainViewScene = new Scene(mainViewParent);
        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(mainViewScene);
        window.show();

    }

    public void handlerCardDelete(ActionEvent event) throws IOException {
        //Ausgewählte Karte löschen

        /*String selectedItem = (String) CardList.getSelectionModel().getSelectedItem();

        Deck currentDeck = data.getCurrentDeck();

        if (selectedItem != null) {
            for (Flashcard card: currentDeck.getCards()) {
                if (selectedItem.equals(card.getName())) {
                    Data.setCurrentCardName(selectedItem);
                    LogHelper.writeToLog(Level.INFO, "setCurrentCardname: " + selectedItem);
                }
            }
        }
        System.out.println(Data.getCurrentDeckName());*/

    }
}
