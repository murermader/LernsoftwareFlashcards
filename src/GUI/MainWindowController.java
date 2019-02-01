package GUI;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class MainWindowController {


    //: Todo GUI Controller anpassen an Java 11 und FXML Dateien neu Schreiben
    // :Todo Scenen Wechsel

    @FXML
    public void handlerUeben(ActionEvent event)throws IOException{
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("UebenWindow.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    public  void handlerStapelanzeige(){


    }

    @FXML
    public void handlerKartehinzufuegen(){

    }

    @FXML
    public void handlerBeenden(){

    }
}
