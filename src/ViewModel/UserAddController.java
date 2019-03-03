package ViewModel;

import Model.Data;
import Model.Helper;
import Model.LogHelper;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

public class UserAddController {

    public TextField textField = new TextField();
    public Label statusbarLabel1 = new Label();
    private Helper helper = new Helper();

    public void handlerConfirm(ActionEvent event) throws IOException {

        String newUser = textField.getText();
        List<String> allUsers = Data.getAllUsers();
        boolean userNameIsUnique = true;

        if (newUser != null) {

            for (String user : allUsers) {
                if(newUser.equals(user)){
                    userNameIsUnique = false;
                    LogHelper.writeToLog(Level.INFO, "Username konnte nicht angenommen werden, da der Name " + user + " schon existiert.");
                }
            }
            if (!newUser.equals("Beispieldeck") && newUser.length() < 30 && newUser.length() > 2 && userNameIsUnique) {
                allUsers.add(newUser);
                helper.saveUsersToFile(allUsers);
                switchToManageUserView(event);
            } else {
                statusbarLabel1.setText("Info: Der Name muss länger als 2 und kürzer als 30 Zeichen sein.");
            }
        }
    }

    public void handlerBack(ActionEvent event) throws IOException {
        switchToManageUserView(event);
    }

    private void switchToManageUserView(ActionEvent event) throws IOException {
        helper.switchScene(event,"manageUser.fxml");
    }
}
