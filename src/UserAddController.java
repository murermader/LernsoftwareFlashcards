import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class UserAddController {

    public TextField textField = new TextField();
    private Helper helper = new Helper();


    public void handlerConfirm(ActionEvent event) throws IOException {

        String newUser = textField.getText();
        List<String> allUsers = Data.getAllUsers();

        if(newUser != null){
            allUsers.add(newUser);
        }
        helper.saveUsersToFile(allUsers);

        Parent manageUserView = FXMLLoader.load(getClass().getResource("GUI/manageUser.fxml"));
        Scene practiceViewScene = new Scene(manageUserView);
        //This line gets the Stage information
        Stage window1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window1.setScene(practiceViewScene);
        window1.show();
    }

    public void handlerBack(ActionEvent event) throws IOException {
        Parent manageUserView = FXMLLoader.load(getClass().getResource("GUI/manageUser.fxml"));
        Scene practiceViewScene = new Scene(manageUserView);
        //This line gets the Stage information
        Stage window1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window1.setScene(practiceViewScene);
        window1.show();
    }
}
