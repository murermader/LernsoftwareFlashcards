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
import javafx.stage.Stage;


public class MainWindowController {

    //: Todo GUI Controller anpassen an Java 11 und FXML Dateien neu Schreiben
    // :Todo Scenen Wechsel

    //FXML Elemente
    public ComboBox userComboBox = new ComboBox();
    public Button selectUserButton = new Button();
    public Button manageUserButton = new Button();
    public Button decksButton = new Button();
    public Button statsButton = new Button();
    public Button exitButton = new Button();

    private Data data = new Data();

    @FXML
    public void initialize() {

        try {
            ObservableList<String> usersCollection = FXCollections.observableArrayList();
            List<String> users = Data.getAllUsers();

            if (Data.getCurrentUser() == null) {
                decksButton.setDisable(true);
                statsButton.setDisable(true);
                exitButton.setDisable(true);
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

    public void handlerManageUser(ActionEvent event) throws IOException {
        Parent manageUserView = FXMLLoader.load(getClass().getResource("GUI/manageUser.fxml"));
        Scene practiceViewScene = new Scene(manageUserView);
        //This line gets the Stage information
        Stage window1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window1.setScene(practiceViewScene);
        window1.show();
    }

    @FXML
    public void handlerSelectUser(ActionEvent event) throws IOException {
        if (userComboBox.getSelectionModel().getSelectedItem().toString() != null) {
            Data.setCurrentUser(userComboBox.getSelectionModel().getSelectedItem().toString());
            decksButton.setDisable(false);
            statsButton.setDisable(false);
            exitButton.setDisable(false);
        }
    }

    @FXML
    public void handlerPractice(ActionEvent event) throws IOException {

        Parent practiceViewParent = FXMLLoader.load(getClass().getResource("GUI/PracticeWindow.fxml"));
        Scene practiceViewScene = new Scene(practiceViewParent);
        //This line gets the Stage information
        Stage window1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window1.setScene(practiceViewScene);
        window1.show();
    }

    @FXML
    public void handlerDeckIndex(ActionEvent event) throws IOException {

        Parent DeckIndexParent = FXMLLoader.load(getClass().getResource("GUI/DeckIndex.fxml"));
        Scene DeckIndexScene = new Scene(DeckIndexParent);
        Stage window2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window2.setScene(DeckIndexScene);
        window2.show();

    }

    @FXML
    public void handlerCardAdd(ActionEvent event) throws IOException {

        Parent CardAddParent = FXMLLoader.load(getClass().getResource("GUI/CardAdd.fxml"));
        Scene CardAddScene = new Scene(CardAddParent);
        Stage window3 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window3.setScene(CardAddScene);
        window3.show();
    }

    @FXML
    public void handlerStats(ActionEvent event) throws IOException {

        Parent StatsWindowParent = FXMLLoader.load(getClass().getResource("GUI/StatsWindow.fxml"));
        Scene StatsWindowScene = new Scene(StatsWindowParent);
        Stage window3 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window3.setScene(StatsWindowScene);
        window3.show();
    }

    @FXML
    public void handlerQuit() {

        System.exit(0);
    }
}
