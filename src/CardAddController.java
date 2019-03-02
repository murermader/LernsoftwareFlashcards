import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.logging.Level;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class CardAddController {


    public ListView CardList = new ListView();
    private static final Path appDirectoryLog = Paths.get(System.getenv("LOCALAPPDATA"), "flashcards", "Log");
    private static final Path appDirectoryRoot = Paths.get(System.getenv("LOCALAPPDATA"), "flashcards");
    private Path osXDirectory = Paths.get(System.getenv("user.home"), "Library", "Application Support", "flashcards");
    Helper helper = new Helper();
    Data data = new Data();


    public void initialize(String deckName) {

        List<Flashcard> CardList = new ArrayList<>();
        Deck deck = new Deck(deckName, CardList, Data.getCurrentUser());

        try {

            FileInputStream fileStreamIn = new FileInputStream(
                    Paths.get(appDirectoryRoot.toString(), deckName).toString());
            ObjectInputStream objectStream = new ObjectInputStream(fileStreamIn);
            CardList = (List<Flashcard>) objectStream.readObject();

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

        System.out.println(Data.getCurrentDeckName());


        //Karte hinzufügen zu dem jeweiligen stapen
    }

    public void handlerCardEdit(ActionEvent event) throws IOException {
        //Ausgewählte Karte editiere

    }

    public void handlerCardDelete(ActionEvent event) throws IOException {
        //Ausgewählte Karte löschen

    }

}
