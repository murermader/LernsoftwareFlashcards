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

public class PracticeWindowController {

    //FXML Elemente
    public Label questionLabel = new Label();
    public Label answerLabel = new Label();
    public Label infoLabel = new Label();
    public Label easyTime = new Label();
    public Label okTime = new Label();
    public Label hardTime = new Label();
    public Label currentUserLabel = new Label();

    public Button easy = new Button();
    public Button ok = new Button();
    public Button hard = new Button();
    public Button show = new Button();

    //Globale Variablen
    private Data data = new Data();
    private Helper helper = new Helper();
    private User user = new User(Data.getCurrentUser());
    private int currentcardIndex = 0;
    private int cardIndexMax;
    private Flashcard currentFlashcard;
    private Deck deckReady;
    private long countTime;

    @FXML
    public void initialize() {

        //TODO: Timer starten (Lernzeit). Soll so lange laufen wie die Practice-Ansicht offen ist
        try {
            countTime = System.currentTimeMillis();
            if (Data.getCurrentDeckName() != null) {

                deckReady = data.getCurrentDeck();
                deckReady.ready();

                if(Data.getCurrentUser() != null){
                    currentUserLabel.setText("Aktuell angemeldet als: " + Data.getCurrentUser());
                }

                if (deckReady.getLength() > 0) {

                    deckReady.sort();
                    infoLabel.setVisible(false);
                    cardIndexMax = deckReady.getLength();
                    currentFlashcard = deckReady.getCards().get(currentcardIndex);
                    LogHelper.writeToLog(Level.INFO, "Aktuelles Deck: " + deckReady.getName() + " ready mit " + deckReady.getLength() + " Karten");
                    questionLabel.setText(currentFlashcard.getFront());

                } else {

                    infoLabel.setText("Deck "+ Data.getCurrentDeckName() +" noch nicht lernbereit!");
                    disableControls();
                    LogHelper.writeToLog(Level.INFO, "Aktuelles Deck enthält keine Karten die gelernt werden können.");
                }

            } else {

                infoLabel.setText("Es wurde kein Deck ausgewählt!");
                disableControls();
                LogHelper.writeToLog(Level.INFO, "Kein Deck ausgewählt.");
            }

        } catch (Exception ex) {
            LogHelper.writeToLog(Level.INFO, "Fehler beim Initialisieren des \"Üben\"-Windows: " + ex);
        }
    }

    //Eventhandling
    public void handlerBack(ActionEvent event) throws IOException {

        try {
            if (Data.getCurrentDeckName() != null) {
                helper.saveDeckToFile(data.getCurrentDeck());
            }
        } catch (Exception ex) {
            LogHelper.writeToLog(Level.INFO, "Fehler beim Speichern des aktuellen Decks.");
        }
        Parent deckIndexController = FXMLLoader.load(getClass().getResource("GUI/DeckIndex.fxml"));
        Scene mainViewScene = new Scene(deckIndexController);
        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(mainViewScene);
        window.show();
    }

    public void handlerEasy(ActionEvent event) {
        finishUpCard(1);
    }

    public void handlerOk(ActionEvent event) {
        finishUpCard(2);
    }

    public void handlerHard(ActionEvent event) {
        finishUpCard(3);
    }

    public void handlerShowBack(ActionEvent event) {

        answerLabel.setText(currentFlashcard.getBack());
        easy.setDisable(false);
        ok.setDisable(false);
        hard.setDisable(false);
        //Nachdem die Rückseite angezeigt wird, sollen die Abfragezeiten über den Buttons angezeigt werden
        easyTime.setText(currentFlashcard.returnTimeIntervalAsString(currentFlashcard.getLevel() + 2));
        okTime.setText(currentFlashcard.returnTimeIntervalAsString(currentFlashcard.getLevel() + 1));
        hardTime.setText(currentFlashcard.returnTimeIntervalAsString(currentFlashcard.getLevel()));
    }

    private void finishUpCard(int difficulty) {

        //TODO: Aufzeichnen dass die Karte gelernt wurde.
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
            //TODO: Hier ist man fertig mit dem Lernen!
            //TODO: Stats: Timer hier stoppen und speichern?
            questionLabel.setText("");
            infoLabel.setText("Der Stapel ist komplett gelernt!");
            infoLabel.setVisible(true);
            show.setDisable(true);

            countTime = System.currentTimeMillis()-countTime;
            user.setTimeSpentLearning(countTime);
            LogHelper.writeToLog(Level.INFO, "Zeit insgesamt " + user.getTimeSpentLearning());
        }
    }

    private void disableControls(){
        easy.setDisable(true);
        ok.setDisable(true);
        hard.setDisable(true);
        show.setDisable(true);
        infoLabel.setVisible(true);
    }
}
