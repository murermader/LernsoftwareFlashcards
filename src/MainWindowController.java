import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainWindowController {

    @FXML
    public void handlerPractice(ActionEvent event)throws IOException{
        Parent practiceViewParent = FXMLLoader.load(getClass().getResource("PracticeWindow.fxml"));
        Scene practiceViewScene = new Scene(practiceViewParent);

        //This line gets the Stage information
        Stage window1 = (Stage)((Node)event.getSource()).getScene().getWindow();

        window1.setScene(practiceViewScene);
        window1.show();
    }

    @FXML
    public  void handlerDeckIndex(ActionEvent event)throws IOException{
        Parent DeckIndexParent = FXMLLoader.load(getClass().getResource("DeckIndex.fxml"));
        Scene DeckIndexScene= new Scene(DeckIndexParent);

        Stage window2 = (Stage)((Node)event.getSource()).getScene().getWindow();

        window2.setScene(DeckIndexScene);
        window2.show();
    }

    @FXML
    public void handlerCardAdd(ActionEvent event)throws IOException{
        Parent CardAddParent = FXMLLoader.load(getClass().getResource("CardAdd.fxml"));
        Scene CardAddScene = new Scene(CardAddParent);

        Stage window3 = (Stage)((Node)event.getSource()).getScene().getWindow();
        window3.setScene(CardAddScene);
        window3.show();
    }

    @FXML
    public void handlerQuit(){
        System.exit(0);

    }
}
