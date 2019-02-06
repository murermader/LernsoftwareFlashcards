import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PracticeWindowController implements Initializable {

    private Data data = new Data();
    private List<Deck> decks =  data.getDecks();

    public void handlerBack(ActionEvent event)throws IOException {
        Parent mainViewParent = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
        Scene mainViewScene = new Scene(mainViewParent);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(mainViewScene);
        window.show();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("INITIALIZED!!!!");
        List<Flashcard> flashcards = decks.get(0).getCards();
        System.out.println(flashcards.get(5).getFront());
    }

    public void handlerEasy(ActionEvent event)throws IOException{

    }
    public void handlerOk(ActionEvent event)throws IOException{

    }
    public void handlerHard(ActionEvent event)throws IOException{

    }
    public void handlerRepeat(ActionEvent event)throws IOException{

    }

}
