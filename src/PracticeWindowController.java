import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


import javax.xml.stream.Location;

public class PracticeWindowController {
    public Label FragenLabel = new Label();

    @FXML
    public void initialize(){
        //Hier die Methode zur Anzeige der Frage
        System.out.println("Test");
    }

    public void handlerBack(ActionEvent event)throws IOException {
        Parent mainViewParent = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
        Scene mainViewScene = new Scene(mainViewParent);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(mainViewScene);
        window.show();

    }

    public void handlerEasy(ActionEvent event)throws IOException{

    }
    public void handlerOk(ActionEvent event)throws IOException{

    }
    public void handlerHard(ActionEvent event)throws IOException{

    }
    public void handlerRepeat(ActionEvent event)throws IOException{

    }
    public void handlerShowAnswer(ActionEvent event)throws IOException{

    }
    public void handlerStart(ActionEvent event)throws IOException{
        FragenLabel.setText("Test");
        System.out.println("FragenTest");
    }

}
