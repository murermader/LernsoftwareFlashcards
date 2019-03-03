package ViewModel;

import Model.Data;
import Model.Deck;
import Model.Helper;
import Model.LogHelper;
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

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.logging.Level;

public class DeckOverviewController {

    public HBox statusbar = new HBox();
    public Label statusbarLabel1 = new Label();
    public ListView list = new ListView<String>();
    private Helper helper = new Helper();
    private Data data = new Data();
    private ObservableList<String> deckNames = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        try{
            statusbar.setBackground(new Background(new BackgroundFill(Color.rgb(212, 212, 212), CornerRadii.EMPTY, Insets.EMPTY)));
            if (!data.isEmpty) {

                for (Deck deck : data.getListOfDecks()) {
                    deckNames.add(deck.getName() + " (" + deck.getOwner() + ")");
                }
                //noinspection unchecked
                list.setItems(deckNames);
            } else {
                statusbarLabel1.setText("Es sind momentan noch keine Daten vorhanden.");
            }
        } catch(Exception ex) {
            LogHelper.writeToLog(Level.INFO, "Fehler WIRD NCOH");
        }
    }

    public void handlerDeckSelect(ActionEvent event) {
        String selectedItem = (String) list.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            for (Deck deck : data.getListOfDecks()) {
                if (selectedItem.contains(deck.getName()) && selectedItem.contains(deck.getOwner())) {
                    Data.setCurrentDeckName(deck.getName());
                    LogHelper.writeToLog(Level.INFO, "setCurrentDeckname: " + selectedItem);
                }
            }
        }
        helper.switchScene(event,"PracticeWindow.fxml");
    }

    public void handlerBack(ActionEvent event) {
        list.getItems().clear();
        deckNames.clear();
        helper.switchScene(event,"MainWindow.fxml");
    }

    public void handlerDeckAdd(ActionEvent event) {
        helper.switchScene(event,"DeckAdd.fxml");
    }

    //Kartenansicht
    public void handlerOverview(ActionEvent event) {

        String selectedItem = (String) list.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            for (Deck deck : data.getListOfDecks()) {
                if (selectedItem.contains(deck.getName()) && selectedItem.contains(deck.getOwner())) {
                    Data.setCurrentDeckName(deck.getName());
                    LogHelper.writeToLog(Level.INFO, "setCurrentDeckname: " + deck.getName());
                }
            }
            //Szene wechseln
            helper.switchScene(event,"CardOverview.fxml");
        }
        statusbarLabel1.setText("Es wurde kein Deck ausgewählt! - Bitte zuerst ein Deck auswählen.");
        //selectedItem == Null --> Statusbar
    }

    public void handlerDeleteDeck(ActionEvent event) throws IOException {

        String selectedItem = (String) list.getSelectionModel().getSelectedItem();

        //Deck auswählen
        if (selectedItem != null) {
            for (Deck deck : data.getListOfDecks()) {
                if (selectedItem.contains(deck.getName()) && selectedItem.contains(deck.getOwner())) {
                    Data.setCurrentDeckName(deck.getName());
                    LogHelper.writeToLog(Level.INFO, "setCurrentDeckname: " + selectedItem);
                }
            }
        }
        System.out.println(Data.getCurrentDeckName());


        //Pfad des Decks finden
        File deck = new File(Paths.get(System.getenv("LOCALAPPDATA"), "flashcards", Data.getCurrentDeckName()).toString() + ".txt");
        System.out.println("Attempting to delete " + deck.getAbsolutePath());

        if (!deck.exists()) {
            statusbarLabel1.setText("Stapel existiert nicht");

        } else if (!deck.canWrite()) {
            statusbarLabel1.setText("Keine Berechtigung um diesen Stapel zu löschen");

        } else {
            deck = deck.getCanonicalFile();
            boolean Isremoved = deck.delete();

            if (Isremoved) {

                statusbarLabel1.setText("Stapel gelöscht");
                helper.switchScene(event,"DeckOverview.fxml");

            } else {
                statusbarLabel1.setText("Stapel konnte nicht gelöscht werden");

            }
        }
    }
}
