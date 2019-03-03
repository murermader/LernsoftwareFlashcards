package ViewModel;

import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.util.logging.Level;

public class StatsWindowController {

    //FXML Elemente
    public Label nameLabel = new Label();
    public Label deckCount = new Label();
    public Label cardCount = new Label();
    public Label cardLearned = new Label();
    public Label timeSpent = new Label();

    private int lenght;
    public Helper helper = new Helper();
    public Data data = new Data();
    private UserStats userStats = new UserStats(Data.getCurrentUser());

    @FXML
    public void initialize() {

        try{
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
            String timeIndication = " Minuten";
            timeSpent.setText(userStats.getTimeSpentLearning() + timeIndication);

        } catch(Exception ex){
            LogHelper.writeToLog(Level.INFO, "Fehler beim Initialiseren des StatsWindows " +ex);
        }
    }

    @FXML
    public void handlerReset(ActionEvent event) {
        userStats.resetTime();
        helper.switchScene(event,"StatsWindow.fxml");
    }

    @FXML
    public void handlerBack(ActionEvent event) {
        helper.switchScene(event,"MainWindow.fxml");
    }
}
