import java.io.IOException;
import java.util.logging.Level;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class StatsWindowController {

    //FXML Elemente
    public Label nameLable = new Label();
    public Label cardCount = new Label();
    public Label deckCount = new Label();

    public Button allUserSwitch = new Button();
    public Button timeSwitch = new Button();

    public Helper helper = new Helper();
    public Data data = new Data();
    public User user = new User(data.getCurrentUser());

    //Globale Variabeln
    private String name;
    private int numberOfDecks;
    private int numberOfCards;
    private int cardsLearned;
    private long timeSpentLearning;

    @FXML
    public void initialize() {
        user.setName(data.getCurrentUser());
        user.setNumberOfDecks(data.getListOfDecks().size());
    //    user.setNumberOfCards();
    //    user.setCardsLearned();
    //    user.setTimeSpentLearning();

        name = user.getName();
        numberOfDecks = user.getNumberOfDecks();

        nameLable.setText(name);
        deckCount.setText("" + user.getNumberOfDecks());
    }

    public void handlerReset(ActionEvent event) throws IOException {


    }


    public void handlerBack(ActionEvent event) throws IOException {

        Parent mainViewParent = FXMLLoader.load(getClass().getResource("GUI/MainWindow.fxml"));
        Scene mainViewScene = new Scene(mainViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(mainViewScene);
        window.show();
    }
}
