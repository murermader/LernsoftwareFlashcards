import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CardAddController {
    public void handlerBack(ActionEvent event)throws IOException {

        Parent mainViewParent = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
        Scene mainViewScene = new Scene(mainViewParent);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(mainViewScene);
        window.show();
    }

    public void handlerAdd(ActionEvent event)throws IOException{

    }
}
