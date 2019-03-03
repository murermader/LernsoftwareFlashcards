package ViewModel;

import Model.Data;
import Model.Helper;
import Model.LogHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;


public class MainWindowController {

    //: Todo View ViewModel anpassen an Java 11 und FXML Dateien neu Schreiben
    // :Todo Scenen Wechsel

    //FXML Elemente
    public ComboBox userComboBox = new ComboBox();
    public Button selectUserButton = new Button();
    public Button editUserButton = new Button();
    public Button decksButton = new Button();
    public Button statsButton = new Button();
    public HBox statusbar = new HBox();
    public Label statusbarLabel1 = new Label();
    private Data data = new Data();
    private Helper helper = new Helper();

    @FXML
    public void initialize() {

        try {
            ObservableList<String> usersCollection = FXCollections.observableArrayList();
            List<String> users = Data.getAllUsers();
            statusbar.setBackground(new Background(new BackgroundFill(Color.rgb(212, 212, 212), CornerRadii.EMPTY, Insets.EMPTY)));

            if (Data.getCurrentUser() == null) {
                decksButton.setDisable(true);
                statsButton.setDisable(true);
                statusbarLabel1.setText("Kein Benutzer ausgewählt. Zum Fortfahren einen Benutzer auswählen.");
            } else {
                statusbarLabel1.setText("Aktuell angemeldet als: " + Data.getCurrentUser());
            }
            if (Data.getAllUsers().isEmpty()) {
                selectUserButton.setDisable(true);
                decksButton.setDisable(true);
                statsButton.setDisable(true);
                statusbarLabel1.setText("Keine Benutzer vorhanden. Zum Fortfahren neue Benutzer erstellen.");
            }
            if (users != null && users.size() > 0) {
                usersCollection.addAll(users);
                userComboBox.setItems(usersCollection);
                userComboBox.getSelectionModel().selectFirst();
            }

        } catch (Exception ex) {
            LogHelper.writeToLog(Level.INFO, "Fehler beim Initialisieren des MainWindowControllers " + ex);
        }
    }

    @FXML
    public void handlerSelectUser() {

        Object item = userComboBox.getSelectionModel().getSelectedItem();
        if (item != null) {
            Data.setCurrentUser(item.toString());
            decksButton.setDisable(false);
            statsButton.setDisable(false);
            statusbarLabel1.setText("Benutzer " + item.toString() + " ausgewählt. ");
        }
    }

    @FXML
    public void handlerEditUser(ActionEvent event) {
        helper.switchScene(event, "UserEdit.fxml");
    }

    @FXML
    public void handlerDeckIndex(ActionEvent event) {
        helper.switchScene(event, "DeckOverview.fxml");
    }

    @FXML
    public void handlerStats(ActionEvent event) {
        helper.switchScene(event, "StatsWindow.fxml");
    }

    @FXML
    public void handlerQuit() {
        System.exit(0);
    }
}
