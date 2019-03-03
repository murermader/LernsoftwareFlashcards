package ViewModel;

import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.logging.Level;

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
            LogHelper.writeToLog(Level.INFO, "Anzahl Karten: " + l);
        }
        LogHelper.writeToLog(Level.INFO, "Zeit: " + user.getTimeSpentLearning());


        user.setNumberOfCards(lenght);

        nameLabel.setText(user.getName());
        deckCount.setText("" + user.getNumberOfDecks());
        cardCount.setText("" + user.getNumberOfCards());
        cardLearned.setText("" + user.getCardsLearned());
        timeSpent.setText("" + user.getTimeSpentLearning());
    }

    public void handlerReset(ActionEvent event) throws IOException {
        user.resetTime();
        helper.switchScene(event,"StatsWindow.fxml");
    }


    public void handlerBack(ActionEvent event) throws IOException {
        helper.switchScene(event,"MainWindow.fxml");
    }
}
