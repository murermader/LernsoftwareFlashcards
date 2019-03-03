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

    public Helper helper = new Helper();
    public Data data = new Data();
    private UserStats userStats = new UserStats(Data.getCurrentUser());

    @FXML
    public void initialize() {

        try{
            userStats.setName(Data.getCurrentUser());
            userStats.setNumberOfDecks(data.getListOfDecks().size());
            userStats.setNumberOfCards();

            nameLabel.setText(userStats.getName());
            deckCount.setText("" + userStats.getNumberOfDecks());
            cardCount.setText("" + userStats.getNumberOfCards());
        } catch(Exception ex){
            LogHelper.writeToLog(Level.INFO, "Fehler beim Initialiseren des StatsWindows " +ex);
        }
    }

    @FXML
    public void handlerBack(ActionEvent event) {
        helper.switchScene(event,"MainWindow.fxml");
    }
}
