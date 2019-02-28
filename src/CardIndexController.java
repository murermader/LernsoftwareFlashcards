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

    public ListView CardList = new ListView();
    private static final Path appDirectoryLog = Paths.get(System.getenv("LOCALAPPDATA"), "flashcards", "Log");
    private static final Path appDirectoryRoot = Paths.get(System.getenv("LOCALAPPDATA"), "flashcards");
    private Path osXDirectory = Paths.get(System.getenv("user.home"), "Library", "Application Support", "flashcards");
    public  Data data = new Data();
    @FXML
    public void initialize(){

        System.out.println(data.getCurrentDeck());

        List<Flashcard> CardList = new ArrayList<>();
        Deck deck = new Deck(data.getCurrentDeck().toString(), CardList, Data.getCurrentUser());

        try {

            FileInputStream fileStreamIn = new FileInputStream(
                    Paths.get(appDirectoryRoot.toString(), data.getCurrentDeck().toString()).toString());
            ObjectInputStream objectStream = new ObjectInputStream(fileStreamIn);
            CardList = (List<Flashcard>) objectStream.readObject();
            System.out.println(CardList);

        } catch (Exception ex) {
            LogHelper.writeToLog(Level.INFO, "Fehler beim Einlesen der Speicherdatei: " + ex);
        }
        deck.setCards(CardList);

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
        Parent mainViewParent = FXMLLoader.load(getClass().getResource("GUI/CardEdit.fxml"));
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

    }
}
