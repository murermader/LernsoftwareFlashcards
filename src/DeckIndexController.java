import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
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
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DeckIndexController {
    public ListView list = new ListView<String>();
    private Data data = new Data();
    private ObservableList<String> deckNames = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        //Funktioniert nicht!
        //data =
        list.getItems().clear();
        list.getSelectionModel().clearSelection();
        deckNames.clear();

        if (!data.isEmpty) {

            for (Deck deck : data.getListOfDecks()) {
                deckNames.add(deck.getName());
                System.out.println(deck.getName());
            }
            //Noch keine Ahnung wie ich diese Warnung wegbekomme, voerst erstmal ignorieren
            list.refresh();
            //noinspection unchecked
            list.setItems(deckNames);
        }
    }

    public void handlerDeckSelect(ActionEvent event) throws IOException {
        String selectedItem = (String) list.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            for (Deck deck : data.getListOfDecks()) {
                if (selectedItem.equals(deck.getName())) {
                    Data.setCurrentDeckName(selectedItem);
                    LogHelper.writeToLog(Level.INFO, "setCurrentDeckname: " + selectedItem);
                }
            }
        }
        Parent practiceViewParent = FXMLLoader.load(getClass().getResource("GUI/PracticeWindow.fxml"));
        Scene practiceViewScene = new Scene(practiceViewParent);

        //This line gets the Stage information
        Stage window1 = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window1.setScene(practiceViewScene);
        window1.show();
    }

    public void handlerBack(ActionEvent event) throws IOException {
        list.getItems().clear();
        deckNames.clear();
        Parent mainViewParent = FXMLLoader.load(getClass().getResource("GUI/MainWindow.fxml"));
        Scene mainViewScene = new Scene(mainViewParent);
        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(mainViewScene);
        window.show();
    }

    public void handlerDeckAdd(ActionEvent event) throws IOException {
        Parent CardAddViewParent = FXMLLoader.load(getClass().getResource("GUI/DeckAdd.fxml"));
        Scene CardAddViewScene = new Scene(CardAddViewParent);

        //This line gets the Stage information
        Stage window1 = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window1.setScene(CardAddViewScene);
        window1.show();
        //Deck hinzufügen
        //Fenster popup: Textfeld für Namen
    }

    public void handlerCardAdd(ActionEvent event) throws IOException {
        Parent CardAddViewParent = FXMLLoader.load(getClass().getResource("GUI/CardAdd.fxml"));
        Scene CardAddViewScene = new Scene(CardAddViewParent);

        //This line gets the Stage information
        Stage window1 = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window1.setScene(CardAddViewScene);
        window1.show();
    }

    public void handlerDeleteDeck(ActionEvent event) throws IOException {
        String selectedItem = (String) list.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            for (Deck deck : data.getListOfDecks()) {
                if (selectedItem.equals(deck.getName())) {
                    Data.setCurrentDeckName(selectedItem);
                    LogHelper.writeToLog(Level.INFO, "setCurrentDeckname: " + selectedItem);
                }
            }
        }
        System.out.println(Data.getCurrentDeckName());

        File deck = new File(Paths.get(System.getenv("LOCALAPPDATA"), "flashcards", Data.getCurrentDeckName()).toString() + ".txt");
        System.out.println(deck);
        System.out.println("Attempting to delete " + deck.getAbsolutePath());

        if (!deck.exists()) {
            System.out.println("  Doesn't exist");

        } else if (!deck.canWrite()) {
            System.out.println("  No write permission");

        } else {
            deck = deck.getCanonicalFile();
            boolean Isremoved = deck.delete();

            if (Isremoved == true) {
                System.out.println("  Deleted!");
                Parent CardAddViewParent = FXMLLoader.load(getClass().getResource("GUI/DeckIndex.fxml"));
                Scene CardAddViewScene = new Scene(CardAddViewParent);

                //This line gets the Stage information
                Stage window1 = (Stage) ((Node) event.getSource()).getScene().getWindow();

                window1.setScene(CardAddViewScene);
                window1.show();


            } else {
                System.out.println("  Delete failed - reason unknown");

            }
        }
    }
}
