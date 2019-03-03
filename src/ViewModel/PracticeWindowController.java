package ViewModel;

import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import java.util.logging.Level;

public class PracticeWindowController {

    //FXML Elemente
    public Label questionLabel = new Label();
    public Label answerLabel = new Label();
    public Label easyTime = new Label();
    public Label okTime = new Label();
    public Label hardTime = new Label();
    public Label statusbarLabel1 = new Label();
    public Button easy = new Button();
    public Button ok = new Button();
    public Button hard = new Button();
    public Button show = new Button();
    public HBox statusbar = new HBox();

    private Data data = new Data();
    private Helper helper = new Helper();
    private UserStats userStats = new UserStats(Data.getCurrentUser());
    private int currentcardIndex = 0;
    private int cardIndexMax;
    private Flashcard currentFlashcard;
    private Deck deckReady;
    private long countTime;

    @FXML
    public void initialize() {
        countTime = System.currentTimeMillis();

        try {
            statusbar.setBackground(new Background(new BackgroundFill(Color.rgb(212, 212, 212), CornerRadii.EMPTY, Insets.EMPTY)));
            if (Data.getCurrentDeckName() != null) {

                deckReady = data.getCurrentDeck();
                deckReady.ready();
                if (Data.getCurrentUser() != null) {

                    statusbarLabel1.setText("Aktuell angemeldet als: " + Data.getCurrentUser());
                }
                if (deckReady.getLength() > 0) {

                    deckReady.sort();
                    cardIndexMax = deckReady.getLength();
                    currentFlashcard = deckReady.getCards().get(currentcardIndex);
                    LogHelper.writeToLog(Level.INFO, "Aktuelles Deck: " + deckReady.getName() + " ready mit " + deckReady.getLength() + " Karten");
                    questionLabel.setText(currentFlashcard.getFront());

                } else {

                    statusbarLabel1.setText(Data.getCurrentDeckName() + " noch nicht lernbereit!");
                    disableControls();
                    LogHelper.writeToLog(Level.INFO, "Aktuelles Model.Deck enthält keine Karten die gelernt werden können.");
                }
            } else {

                statusbarLabel1.setText("Es wurde kein Model.Deck ausgewählt!");
                disableControls();
                LogHelper.writeToLog(Level.INFO, "Kein Model.Deck ausgewählt.");
            }
        } catch (Exception ex) {

            LogHelper.writeToLog(Level.INFO, "Fehler beim Initialisieren des \"Üben\"-Windows: " + ex);
        }
    }

    //Eventhandling
    public void handlerBack(ActionEvent event) {

        try {
            countTime = System.currentTimeMillis() - countTime;
            userStats.setTimeSpentLearning(countTime);

            if (Data.getCurrentDeckName() != null) {

                helper.saveDeckToFile(data.getCurrentDeck());
            }
        } catch (Exception ex) {

            LogHelper.writeToLog(Level.INFO, "Fehler beim Speichern des aktuellen Decks.");
        }
        helper.switchScene(event, "DeckOverview.fxml");
    }

    public void handlerEasy() {
        finishUpCard(1);
    }

    public void handlerOk() {
        finishUpCard(2);
    }

    public void handlerHard() {
        finishUpCard(3);
    }

    public void handlerShowBack() {

        try{
            answerLabel.setText(currentFlashcard.getBack());
            easy.setDisable(false);
            ok.setDisable(false);
            hard.setDisable(false);
            //Nachdem die Rückseite angezeigt wird, sollen die Abfragezeiten über den Buttons angezeigt werden
            easyTime.setText(currentFlashcard.returnTimeIntervalAsString(currentFlashcard.getLevel() + 2));
            okTime.setText(currentFlashcard.returnTimeIntervalAsString(currentFlashcard.getLevel() + 1));
            hardTime.setText(currentFlashcard.returnTimeIntervalAsString(currentFlashcard.getLevel()));

        } catch(Exception ex){
            LogHelper.writeToLog(Level.INFO, "Fehler beim Anzeigen der Rückseite " +ex);
        }
    }

    private void finishUpCard(int difficulty) {

        try {
            easy.setDisable(true);
            ok.setDisable(true);
            hard.setDisable(true);
            easyTime.setText("");
            okTime.setText("");
            hardTime.setText("");
            currentFlashcard.setDifficulty(difficulty);
            currentFlashcard.updateInterval();
            //deck.getCards().remove(currentcardIndex);
            answerLabel.setText("");
            currentcardIndex++;

            if (currentcardIndex < cardIndexMax) {

                currentFlashcard = deckReady.getCards().get(currentcardIndex);
                questionLabel.setText(currentFlashcard.getFront());
            } else {

                questionLabel.setText("");
                statusbarLabel1.setText("Der Stapel ist komplett gelernt!");
                statusbarLabel1.setVisible(true);
                show.setDisable(true);
                //Zeit wird gestoppt
                countTime = System.currentTimeMillis() - countTime;
                userStats.setTimeSpentLearning(countTime);
            }
        } catch (Exception ex) {
            LogHelper.writeToLog(Level.INFO, "Fehler beim Vearbeiten der Karte");
        }
    }

    private void disableControls() {

        try{
            easy.setDisable(true);
            ok.setDisable(true);
            hard.setDisable(true);
            show.setDisable(true);
        } catch(Exception ex){
            LogHelper.writeToLog(Level.INFO, "Fehler beim Deaktivieren der Controls " +ex);
        }
    }
}
