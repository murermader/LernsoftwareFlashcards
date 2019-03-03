package ViewModel;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CardEditController {

    public void handlerBack(ActionEvent event)throws IOException{
        Parent mainViewParent = FXMLLoader.load(getClass().getClassLoader().getResource("View/CardAdd.fxml"));
        Scene mainViewScene = new Scene(mainViewParent);
        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(mainViewScene);
        window.show();
    }

    public void handlerEdit(ActionEvent event)throws IOException{

    }
}
