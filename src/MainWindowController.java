import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
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
    public HBox statusbar = new HBox();
    public Label statusbarLabel1 = new Label();
    private Data data = new Data();

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
            if(Data.getAllUsers().isEmpty()){
                selectUserButton.setDisable(true);
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

    public void handlerManageUser(ActionEvent event) throws IOException {

        Parent manageUserView = FXMLLoader.load(getClass().getResource("GUI/manageUser.fxml"));
        Scene practiceViewScene = new Scene(manageUserView);
        Stage window1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window1.setScene(practiceViewScene);
        window1.show();
    }

    @FXML
    public void handlerSelectUser(ActionEvent event) throws IOException {

        Object item = userComboBox.getSelectionModel().getSelectedItem();
        if (item != null) {
            Data.setCurrentUser(item.toString());
            decksButton.setDisable(false);
            statsButton.setDisable(false);
            statusbarLabel1.setText("Benutzer "+ item.toString() + " ausgewählt. ");
        }
    }

    @FXML
    public void handlerDeckIndex(ActionEvent event) throws IOException {

        Parent DeckIndexParent = FXMLLoader.load(getClass().getResource("GUI/DeckOverview.fxml"));
        Scene DeckIndexScene = new Scene(DeckIndexParent);
        Stage window2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window2.setScene(DeckIndexScene);
        window2.show();

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
