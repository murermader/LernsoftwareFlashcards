import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class PracticeWindowController {
    public Label FragenLabel = new Label();
    public Label AntwortLabel = new Label();
    private Data data = new Data();
    private Deck deck;
    private int currentcardIndex = 0;

    @FXML
    public void initialize(){
        try{
            if(Data.getCurrentDeckName().isEmpty()){
                //Empty == "";
            }

            Data.setCurrentDeckName("test1");
            deck = data.getCurrentDeck();
            if(deck == null){
                LogHelper.writeToLog(Level.INFO, "Entweder wurde kein Deck ausgewählt, oder das ausgewählte Deck konnte nicht gefunden werden.");
            }
            else{
                showFront();
            }
        }
        catch(Exception ex){
            LogHelper.writeToLog(Level.INFO,"Fehler beim Initialisieren des \"Üben\"-Windows: "+ ex);
        }
    }

    private void showFront(){
        FragenLabel.setText(deck.getCards().get(currentcardIndex).getFront());
    }

    private void showBack(){
        AntwortLabel.setText(deck.getCards().get(currentcardIndex).getBack());
        currentcardIndex++;
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
        showBack();
        deck.getCards().get(currentcardIndex).setDifficulty(1);
        deck.getCards().get(currentcardIndex).updateInterval();
    }
    public void handlerOk(ActionEvent event)throws IOException{
        showBack();
        deck.getCards().get(currentcardIndex).setDifficulty(2);
        deck.getCards().get(currentcardIndex).updateInterval();
    }
    public void handlerHard(ActionEvent event)throws IOException{
        showBack();
        deck.getCards().get(currentcardIndex).setDifficulty(3);
        deck.getCards().get(currentcardIndex).updateInterval();
    }
    public void handlerNext(ActionEvent event)throws IOException{
        //Button nächste Karte
        AntwortLabel.setText("");
        FragenLabel.setText(deck.getCards().get(currentcardIndex).getFront());
    }
}
