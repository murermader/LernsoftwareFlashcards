import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
    private Data data = new Data();
    private Deck deck;
    private Deck deckReadyToLearn;
    private int currentcardIndex = 0;

    @FXML
    public void initialize(){
        try{
            if(Data.getCurrentDeckName().isEmpty()){
                //Empty == "";
            }
            Date date = new Date();
            date.getTime();

            Data.setCurrentDeckName("test1");
            deck = data.getCurrentDeck();
            deck.ready(); //Entfernt alle noch nicht lernbereite Karten
            if(deck == null){
                LogHelper.writeToLog(Level.INFO, "Entweder wurde kein Deck ausgewählt, oder das ausgewählte Deck konnte nicht gefunden werden.");
            }
            else{
                FragenLabel.setText(deck.getCards().get(currentcardIndex).getFront());
            }
        }
        catch(Exception ex){
            LogHelper.writeToLog(Level.INFO,"Fehler beim Initialisieren des \"Üben\"-Windows: "+ ex);
        }
    }

    //Eventhandling
    public void handlerBack(ActionEvent event)throws IOException {
        Parent mainViewParent = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
        Scene mainViewScene = new Scene(mainViewParent);
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainViewScene);
        window.show();
    }
    public void handlerEasy(ActionEvent event)throws IOException{
        finishUpCard(1);
    }
    public void handlerOk(ActionEvent event)throws IOException{
        finishUpCard(2);
    }
    public void handlerHard(ActionEvent event)throws IOException{
        finishUpCard(3);
    }
    public void handlerShowBack(ActionEvent event)throws IOException{
        AntwortLabel.setText(deck.getCards().get(currentcardIndex).getBack());
        easy.setDisable(false);
        ok.setDisable(false);
        hard.setDisable(false);
    }
    private void finishUpCard(int difficulty){
        easy.setDisable(true);
        ok.setDisable(true);
        hard.setDisable(true);
        deck.getCards().get(currentcardIndex).setDifficulty(difficulty);
        deck.getCards().get(currentcardIndex).updateInterval();
        AntwortLabel.setText("");
        currentcardIndex++;
        FragenLabel.setText(deck.getCards().get(currentcardIndex).getFront());
    }
}
