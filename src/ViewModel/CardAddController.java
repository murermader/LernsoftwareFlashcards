package ViewModel;

import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.logging.Level;

public class CardAddController {

    public HBox statusbar = new HBox();
    public Label statusbarLabel1 = new Label();
    public TextField questionTextField = new TextField();
    public TextField answerTextField = new TextField();
    public Button addButton = new Button();

    private Helper helper = new Helper();
    private Data data = new Data();
    private Deck deck;


    @FXML
    public void initialize() {
        try{

            statusbar.setBackground(new Background(new BackgroundFill(Color.rgb(212, 212, 212), CornerRadii.EMPTY, Insets.EMPTY)));

            if(!Data.getCurrentUser().isEmpty()){
                deck = data.getCurrentDeck();
                statusbarLabel1.setText("Aktuelles Deck: " + deck.getName());

            } else {
                statusbarLabel1.setText("Aufgrund eines Fehlers konnte der jetzige Benutzer nicht ermittelt werden");
                addButton.setDisable(true);
            }
        } catch (Exception ex){
            LogHelper.writeToLog(Level.INFO, "Beim Initialisieren des CardAddControllers kam es zu einem Fehler: " +ex);
        }
    }

    @FXML
    public void handlerBack(ActionEvent event) throws IOException {
        helper.switchScene(event, "CardOverview.fxml");
    }

    @FXML
    public void handlerAdd(ActionEvent event) throws IOException {

        if(!questionTextField.getText().isEmpty() && !answerTextField.getText().isEmpty()){

            Flashcard flashcard = new Flashcard(questionTextField.getText(), answerTextField.getText());
            deck.addCard(flashcard);
            helper.saveDeckToFile(deck);
            statusbarLabel1.setText("Karte hinzugefügt.");
        } else {
            statusbarLabel1.setText("Karte konnte nicht hinzugefügt werden. (Leere Felder?)");
        }
    }
}
