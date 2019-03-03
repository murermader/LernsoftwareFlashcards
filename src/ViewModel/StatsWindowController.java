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
    public UserStats userStats = new UserStats(Data.getCurrentUser());

    @FXML
    public void initialize() {
        userStats.setName(Data.getCurrentUser());
        userStats.setNumberOfDecks(data.getListOfDecks().size());

        for (Deck deck : data.getListOfDecks()) {
            int l = deck.getLength();
            this.lenght += l;
            LogHelper.writeToLog(Level.INFO, "Anzahl Karten: " + l);
        }
        LogHelper.writeToLog(Level.INFO, "Zeit: " + userStats.getTimeSpentLearning());


        userStats.setNumberOfCards(lenght);

        nameLabel.setText(userStats.getName());
        deckCount.setText("" + userStats.getNumberOfDecks());
        cardCount.setText("" + userStats.getNumberOfCards());
        cardLearned.setText("" + userStats.getCardsLearned());
        timeSpent.setText("" + userStats.getTimeSpentLearning());
    }

    public void handlerReset(ActionEvent event) throws IOException {
        userStats.resetTime();
        helper.switchScene(event,"StatsWindow.fxml");
    }


    public void handlerBack(ActionEvent event) throws IOException {
        helper.switchScene(event,"MainWindow.fxml");
    }
}
