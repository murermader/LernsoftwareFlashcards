import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
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
  public Label FragenLabel = new Label();
  public Label AntwortLabel = new Label();
  public Label noDeck = new Label();
  public Label easyTime = new Label();
  public Label okTime = new Label();
  public Label hardTime = new Label();

  public Button easy = new Button();
  public Button ok = new Button();
  public Button hard = new Button();
  public Button Show = new Button();

  private Data data = new Data();
  private Helper helper = new Helper();
  private int currentcardIndex = 0;
  private Flashcard currentFlashcard;

  @FXML
  public void initialize() {
    //TODO: Timer starten (Lernzeit). Soll so lange laufen wie die Practice-Ansicht offen ist
    try {
        if (Data.getCurrentDeckName() != null && data.getCurrentDeck() != null) {
          noDeck.setVisible(false);
          data.getCurrentDeck().ready();
          currentFlashcard = data.getCurrentDeck().getCards().get(currentcardIndex);
          LogHelper.writeToLog(Level.INFO, "Aktuelles Deck: " + Data.getCurrentDeckName() + " ready!");
          FragenLabel.setText(currentFlashcard.getFront());
        }
        else {
          easy.setDisable(true);
          ok.setDisable(true);
          hard.setDisable(true);
          Show.setDisable(true);
          noDeck.setVisible(true);
          LogHelper.writeToLog(Level.INFO, "Kein Deck ausgewählt / Das Deck ist leer.");
        }
    } catch (Exception ex) {
      LogHelper.writeToLog(Level.INFO, "Fehler beim Initialisieren des \"Üben\"-Windows: " + ex);
    }
  }

  //Eventhandling
  public void handlerBack(ActionEvent event) throws IOException {
    try {
      if(Data.getCurrentDeckName() != null){
        helper.saveDeckToFile(data.getCurrentDeck(), Data.getCurrentDeckName());
      }
    } catch (Exception ex) {
      LogHelper.writeToLog(Level.INFO, "Fehler beim Speichern des aktuellen Decks.");
    }
    Parent mainViewParent = FXMLLoader.load(getClass().getResource("GUI/MainWindow.fxml"));
    Scene mainViewScene = new Scene(mainViewParent);
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
    AntwortLabel.setText(currentFlashcard.getBack());
    easy.setDisable(false);
    ok.setDisable(false);
    hard.setDisable(false);
    //Nachdem die Rückseite angezeigt wird, sollen die Abfragezeiten über den Buttons angezeigt werden
    //TODO: 3 Labels für 3 Buttons erstellen.
    //TODO: Neue Abfragezeit .setText();
    easyTime.setText(showTimeToNextRepetition(currentFlashcard.getLevel() +2));
    okTime.setText(showTimeToNextRepetition(currentFlashcard.getLevel() +1));
    hardTime.setText(showTimeToNextRepetition(currentFlashcard.getLevel()));
  }

  private String showTimeToNextRepetition(int level){

    long timeInMillis = currentFlashcard.returnTimeInterval(level);
    String timeString = "";
    if(currentFlashcard.getLevel() < 3){ //Als Minuten anzeigen
      timeString = TimeUnit.MILLISECONDS.toMinutes(timeInMillis) + "min";
    } else if(currentFlashcard.getLevel() < 5){ //Als Stunden anzeigen
      timeString = TimeUnit.MILLISECONDS.toHours(timeInMillis) + "h";
    } else{ //Als Tage anzeigen
      timeString = TimeUnit.MILLISECONDS.toDays(timeInMillis) + "d";
    }
    return timeString;
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
    AntwortLabel.setText("");
    currentcardIndex++;
    currentFlashcard = data.getCurrentDeck().getCards().get(currentcardIndex);
    FragenLabel.setText(currentFlashcard.getFront());
  }
}
