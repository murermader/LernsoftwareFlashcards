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
  public Label FragenLabel = new Label();
  public Label AntwortLabel = new Label();
  public Button easy = new Button();
  public Button ok = new Button();
  public Button hard = new Button();
  public Button Show = new Button();

  private Data data = new Data();
  private Helper helper = new Helper();
  private int currentcardIndex = 0;

  @FXML
  public void initialize() {
    try {
        if (Data.getCurrentDeckName() != null) {
          data.getCurrentDeck().ready();
          LogHelper.writeToLog(Level.INFO, "Aktuelles Deck: " + Data.getCurrentDeckName() + " ready!");
          FragenLabel.setText(data.getCurrentDeck().getCards().get(currentcardIndex).getFront());
        }
        else {
          easy.setDisable(true);
          ok.setDisable(true);
          hard.setDisable(true);
          Show.setDisable(true);
          LogHelper.writeToLog(Level.INFO, "Kein Deck ausgewählt.");
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
    Parent mainViewParent = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
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
    AntwortLabel.setText(data.getCurrentDeck().getCards().get(currentcardIndex).getBack());
    easy.setDisable(false);
    ok.setDisable(false);
    hard.setDisable(false);
  }

  private void finishUpCard(int difficulty) {
    easy.setDisable(true);
    ok.setDisable(true);
    hard.setDisable(true);
    data.getCurrentDeck().getCards().get(currentcardIndex).setDifficulty(difficulty);
    data.getCurrentDeck().getCards().get(currentcardIndex).updateInterval();
    //deck.getCards().remove(currentcardIndex);
    AntwortLabel.setText("");
    currentcardIndex++;
    FragenLabel.setText(data.getCurrentDeck().getCards().get(currentcardIndex).getFront());
  }
}
