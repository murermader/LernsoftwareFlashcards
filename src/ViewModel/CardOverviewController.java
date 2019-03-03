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
    private Deck deck;


    private ObservableList<String> cardNames = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        //Karten für das ListView holen
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
        helper.switchScene(event,"DeckOverview.fxml"); //Zur vorherigen Seite wechseln
    }

    @FXML
    public void handlerCardAdd(ActionEvent event) throws IOException {
        helper.switchScene(event,"CardAdd.fxml"); //Zum CardAdd wechseln
    }

    @FXML
    public void handlerCardEdit(ActionEvent event) throws IOException {
        helper.switchScene(event,"CardEdit.fxml"); //Zum CardEdit wechseln
    }

    @FXML
    public void handlerCardDelete(ActionEvent event) throws IOException {


        String selectedItem = (String) list.getSelectionModel().getSelectedItem();

        Model.Deck  currentDeck = data.getCurrentDeck();


        // Zeile auswählen
        final int selectedId = list.getSelectionModel().getSelectedIndex();
        if (selectedId != -1) {
            String itemToRemove = list.getSelectionModel().getSelectedItem().toString();

            final int newSelectedIdx =
                    (selectedId == list.getItems().size() - 1)
                            ? selectedId - 1
                            : selectedId;

            list.getItems().remove(selectedId);
            list.getSelectionModel().select(newSelectedIdx);
            //removes the player for the array
            System.out.println("selectId: " + selectedId); //ZeilenId
            System.out.println("item: " + itemToRemove); //Name der Zeile





            currentDeck.removeCard(currentDeck.getCardbyName(itemToRemove)); //Karte löschen
            helper.saveDeckToFile(currentDeck); // Zustand der Karten des Decks überschreiben

        }
        }
    }
