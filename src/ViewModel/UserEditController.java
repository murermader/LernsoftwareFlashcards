package ViewModel;

import Model.Data;
import Model.Helper;
import Model.LogHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class UserEditController {

    public ListView list = new ListView();

    private Data data = new Data();
    private Helper helper = new Helper();
    private ObservableList<String> usersCollection = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        try {
            updateListView();

        } catch (Exception ex) {
            LogHelper.writeToLog(Level.INFO, "Fehler beim Initialisieren des MainWindowControllers " + ex);
        }
    }

    @FXML
    public void handlerAdd(ActionEvent event) {
        helper.switchScene(event, "UserAdd.fxml");
    }

    @FXML
    public void handlerRemove() {

        try {
            String selectedItem = (String) list.getSelectionModel().getSelectedItem();

            if (selectedItem != null) {
                List<String> allUsersNew = Data.getAllUsers();
                List<String> toRemove = new ArrayList<>();

                for (String user : allUsersNew) {
                    if (selectedItem.equals(user)) {
                        toRemove.add(user);
                        LogHelper.writeToLog(Level.INFO, "Model.User " + user + " entfernt");
                    }
                }
                allUsersNew.removeAll(toRemove);
                Data.setAllUsers(allUsersNew);

                helper.saveUsersToFile(allUsersNew);
                updateListView();
            }
        } catch (Exception ex) {
            LogHelper.writeToLog(Level.INFO, "Fehler beim Entfernen eines Benutzers: " + ex);
        }
    }

    @FXML
    public void handlerSelect(ActionEvent event) {

        try {
            String selectedUser = (String) list.getSelectionModel().getSelectedItem();
            if (selectedUser != null) {
                Data.setCurrentUser(selectedUser);
                helper.switchScene(event, "MainWindow.fxml");
            }
        } catch (Exception ex) {
            LogHelper.writeToLog(Level.INFO, "Fehler bei der Auswahl des Benutzers: " + ex);
        }

    }

    @FXML
    public void handlerGoBack(ActionEvent event) {
        helper.switchScene(event, "MainWindow.fxml");
    }

    @FXML
    private void updateListView() {
        try {
            list.getItems().clear();
            List<String> users = Data.getAllUsers();
            if (users != null && users.size() > 0) {
                usersCollection.addAll(users);
                list.setItems(usersCollection);
            }
        } catch (Exception ex) {
            LogHelper.writeToLog(Level.INFO, "Fehler beim Updaten der Userliste: " + ex);
        }
    }
}
