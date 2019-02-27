import java.io.IOException;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class ManageUserController {

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

    public void handlerAdd(ActionEvent event) throws IOException {

        Parent userAddView = FXMLLoader.load(getClass().getResource("GUI/UserAdd.fxml"));
        Scene practiceViewScene = new Scene(userAddView);
        Stage window1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window1.setScene(practiceViewScene);
        window1.show();
    }

    public void handlerRemove(ActionEvent event) throws IOException {

        String selectedItem = (String) list.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            List<String> allUsersNew = Data.getAllUsers();
            List<String> toRemove = new ArrayList<>();

            for (String user : allUsersNew) {
                if (selectedItem.equals(user)) {
                    toRemove.add(user);
                    LogHelper.writeToLog(Level.INFO, "User " + user + " entfernt");
                }
            }
            allUsersNew.removeAll(toRemove);
            Data.setAllUsers(allUsersNew);

            helper.saveUsersToFile(allUsersNew);
            updateListView();
        }
    }

    public void handlerSelect(ActionEvent event) throws IOException {

        String selectedUser = (String) list.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            Data.setCurrentUser(selectedUser);
        }
    }

    public void handlerGoBack(ActionEvent event) throws IOException {

        Parent mainWindowView = FXMLLoader.load(getClass().getResource("GUI/MainWindow.fxml"));
        Scene practiceViewScene = new Scene(mainWindowView);
        //This line gets the Stage information
        Stage window1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window1.setScene(practiceViewScene);
        window1.show();
    }

    private void updateListView() {

        list.getItems().clear();
        List<String> users = Data.getAllUsers();
        if (users != null && users.size() > 0) {
            usersCollection.addAll(users);
            list.setItems(usersCollection);
        }
    }
}
