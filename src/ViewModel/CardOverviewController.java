package ViewModel;

import Model.Data;
import Model.Deck;
import Model.Flashcard;
import Model.Helper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


public class CardOverviewController {

    private static final Path appDirectoryLog = Paths.get(System.getenv("LOCALAPPDATA"), "flashcards", "Log");
    private static final Path appDirectoryRoot = Paths.get(System.getenv("LOCALAPPDATA"), "flashcards");
    private Path osXDirectory = Paths.get(System.getenv("user.home"), "Library", "Application Support", "flashcards");

    public Data data = new Data();
    public Helper helper = new Helper();
    public ListView list = new ListView();
    //public Deck deck = new Deck();

    private ObservableList<String> cardNames = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        if (Data.getCurrentUser() != null) {

            Deck currentDeck = data.getCurrentDeck();

            if (currentDeck != null) {

                for (Flashcard card : currentDeck.getCards()) {
                    cardNames.add(card.getFront());
                }
                //noinspection unchecked
                list.setItems(cardNames);
            }
        }
    }

    @FXML
    public void handlerBack(ActionEvent event) throws IOException {
        Data.setCurrentDeckName(null);
        helper.switchScene(event,"DeckOverview.fxml");
    }

    @FXML
    public void handlerCardAdd(ActionEvent event) throws IOException {
        helper.switchScene(event,"CardAdd.fxml");
    }

    @FXML
    public void handlerCardEdit(ActionEvent event) throws IOException {
        helper.switchScene(event,"CardEdit.fxml");
    }

    @FXML
    public void handlerCardDelete(ActionEvent event) throws IOException {
        //Ausgewählte Karte löschen

        String selectedItem = (String) list.getSelectionModel().getSelectedItem();

        Model.Deck currentDeck = data.getCurrentDeck();

        if (selectedItem != null) {
            for (Model.Flashcard card: currentDeck.getCards()) {
                if (selectedItem.equals(card.getFront())) {
                    Model.Data.setCurrentDeckName(selectedItem);
                }
            }
        }

        final int selectedIdx = list.getSelectionModel().getSelectedIndex();
        if (selectedIdx != -1) {
            String itemToRemove = list.getSelectionModel().getSelectedItem().toString();

            final int newSelectedIdx =
                    (selectedIdx == list.getItems().size() - 1)
                            ? selectedIdx - 1
                            : selectedIdx;

            list.getItems().remove(selectedIdx);
            list.getSelectionModel().select(newSelectedIdx);
            //removes the player for the array
            System.out.println("selectIdx: " + selectedIdx);
            System.out.println("item: " + itemToRemove);

            if (data.getCurrentDeck() !=null){

                //Deck.getCardbyName
            }

        }
    }
}
