package ViewModel;

import Model.*;
import Model.Flashcard;
import Model.Helper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import java.util.logging.Level;

public class CardOverviewController {

    public Data data = new Data();
    public Helper helper = new Helper();
    public ListView list = new ListView();
    public HBox statusbar = new HBox();
    public Label statusbarLabel1 = new Label();

    private Deck currentDeck;
    private ObservableList<String> cardNames = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        try {
            statusbar.setBackground(new Background(new BackgroundFill(Color.rgb(212, 212, 212), CornerRadii.EMPTY, Insets.EMPTY)));

            if (Data.getCurrentUser() != null) {

                currentDeck = data.getCurrentDeck();

                if (currentDeck != null) {

                    for (Flashcard card : currentDeck.getCards()) {
                        cardNames.add(card.getFront());
                    }
                    //noinspection unchecked
                    list.setItems(cardNames);
                }
            }
        } catch (Exception ex) {
            LogHelper.writeToLog(Level.INFO, "Fehler beim Initialisieren des CardOverviewControllers: " + ex);
        }
    }

    @FXML
    public void handlerBack(ActionEvent event) {
        Data.setCurrentDeckName(null);
        helper.switchScene(event, "DeckOverview.fxml");
    }

    @FXML
    public void handlerCardAdd(ActionEvent event) {
        helper.switchScene(event, "CardAdd.fxml");
    }

    @FXML
    public void handlerCardEdit(ActionEvent event) {

        String selectedItem = (String) list.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {

            if (currentDeck.getCardByName(selectedItem) != null) {
                Data.setCurrentFlashcard(currentDeck.getCardByName(selectedItem));
                helper.switchScene(event, "CardEdit.fxml");
            }
        } else {
            statusbarLabel1.setText("Keine Karte zum Editieren ausgewählt.");
        }
    }

    @FXML
    public void handlerCardDelete() {

        Deck currentDeck = data.getCurrentDeck();

        final int selectedIdx = list.getSelectionModel().getSelectedIndex();
        if (selectedIdx != -1) {
            String itemToRemove = list.getSelectionModel().getSelectedItem().toString();

            final int newSelectedIdx = (selectedIdx == list.getItems().size() - 1)
                    ? selectedIdx - 1
                    : selectedIdx;

            list.getItems().remove(selectedIdx);
            list.getSelectionModel().select(newSelectedIdx);
            //removes the player for the array
            System.out.println("selectIdx: " + selectedIdx);
            System.out.println("item: " + itemToRemove);
            System.out.println(data.getCurrentDeck());

            //Karte löschen
            currentDeck.removeCard(currentDeck.getCardByName(itemToRemove));
            helper.saveDeckToFile(currentDeck);
            statusbarLabel1.setText("Karte gelöscht!");
        } else {
            statusbarLabel1.setText("Keine Karte zum Löschen ausgewählt.");
        }
    }
}
