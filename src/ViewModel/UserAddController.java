package ViewModel;

import Model.Data;
import Model.Helper;
import Model.LogHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

public class UserAddController {

    public TextField textField = new TextField();
    public HBox statusbar = new HBox();
    public Label statusbarLabel1 = new Label();
    private Helper helper = new Helper();

    @FXML
    public void initialize() {

        try {
            statusbar.setBackground(new Background(new BackgroundFill(Color.rgb(212, 212, 212), CornerRadii.EMPTY, Insets.EMPTY)));

        } catch (Exception ex) {
            LogHelper.writeToLog(Level.INFO, "Fehler beim Initialisieren des MainWindowControllers " + ex);
        }
    }

    public void handlerConfirm(ActionEvent event) throws IOException {

        try {
            String newUser = textField.getText();
            List<String> allUsers = Data.getAllUsers();
            boolean userNameIsUnique = true;

            if (newUser != null) {

                for (String user : allUsers) {
                    if (newUser.equals(user)) {
                        userNameIsUnique = false;
                        LogHelper.writeToLog(Level.INFO, "Username konnte nicht angenommen werden, da der Name " + user + " schon existiert.");
                    }
                }
                if (newUser.length() < 30 && newUser.length() > 2 && userNameIsUnique) {
                    allUsers.add(newUser);
                    helper.saveUsersToFile(allUsers);
                    switchToManageUserView(event);
                } else {
                    if(!userNameIsUnique){
                        statusbarLabel1.setText("Info: Der Benutzername muss einzigartig. Es kann jeden Namen nur einmal geben.");
                    } else {
                        statusbarLabel1.setText("Info: Der Name muss länger als 2 und kürzer als 30 Zeichen sein.");
                    }
                }
            }
        } catch (Exception ex) {
            LogHelper.writeToLog(Level.INFO, "Fehler beim User hinzufügen " + ex);
        }
    }

    @FXML
    public void handlerBack(ActionEvent event) throws IOException {
        switchToManageUserView(event);
    }

    @FXML
    private void switchToManageUserView(ActionEvent event) {
        helper.switchScene(event, "UserEdit.fxml");
    }
}
