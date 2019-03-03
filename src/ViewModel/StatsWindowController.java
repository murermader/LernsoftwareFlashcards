package ViewModel;

import Model.*;
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
    public Label nameLabel = new Label();
    public Label deckCount = new Label();
    public Label cardCount = new Label();
    public Label cardLearned = new Label();
    public Label timeSpent = new Label();

    private int lenght;

    public Button resetTime= new Button();
    public Helper helper = new Helper();
    public Data data = new Data();
    public User user = new User(Data.getCurrentUser());

    @FXML
    public void initialize() {
        user.setName(Data.getCurrentUser());
        user.setNumberOfDecks(data.getListOfDecks().size());

        for (Deck deck : data.getListOfDecks()) {
            int l = deck.getLength();
            this.lenght += l;
        }
        user.setNumberOfCards(lenght);


        nameLabel.setText(user.getName());
        deckCount.setText("" + user.getNumberOfDecks());
        cardCount.setText("" + user.getNumberOfCards());
        cardLearned.setText("" + user.getCardsLearned());
        timeSpent.setText("" + user.getTimeSpentLearning());
    }

    public void handlerReset(ActionEvent event) throws IOException {
        user.resetTime();
        Parent StatsWindowParent = FXMLLoader.load(getClass().getClassLoader().getResource("View/StatsWindow.fxml"));
        Scene StatsWindowScene = new Scene(StatsWindowParent);
        Stage window3 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window3.setScene(StatsWindowScene);
        window3.show();
    }


    public void handlerBack(ActionEvent event) throws IOException {
        Parent mainViewParent = FXMLLoader.load(getClass().getClassLoader().getResource("View/MainWindow.fxml"));
        Scene mainViewScene = new Scene(mainViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(mainViewScene);
        window.show();
    }
}
