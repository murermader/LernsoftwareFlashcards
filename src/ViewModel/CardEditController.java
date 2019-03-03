package ViewModel;

import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import java.util.logging.Level;

public class CardEditController {

    public HBox statusbar = new HBox();
    public Label statusbarLabel1 = new Label();
    public TextField questionTextField = new TextField();
    public TextField answerTextField = new TextField();

    private Helper helper = new Helper();
    private Flashcard flashcard;
    private Data data = new Data();
    private Deck currentDeck;

    @FXML
    public void initialize() {
        try {

            statusbar.setBackground(new Background(new BackgroundFill(Color.rgb(212, 212, 212), CornerRadii.EMPTY, Insets.EMPTY)));

            if (Data.getCurrentFlashcard() != null && Data.getCurrentUser() != null) {
                flashcard = Data.getCurrentFlashcard();
                currentDeck = data.getCurrentDeck();
                questionTextField.setText(flashcard.getFront());
                answerTextField.setText(flashcard.getBack());
            }

        } catch (Exception ex) {
            LogHelper.writeToLog(Level.INFO, "WIRD NOCH" + ex);
        }
    }

    @FXML
    public void handlerBack(ActionEvent event) {
        helper.switchScene(event, "CardOverview.fxml");
    }

    @FXML
    public void handlerEdit(ActionEvent event) {
        try{
            if (!questionTextField.getText().isEmpty() && !answerTextField.getText().isEmpty()) {

                Flashcard flashcardEdited = new Flashcard(flashcard);
                flashcardEdited.setFront(questionTextField.getText());
                flashcardEdited.setBack(answerTextField.getText());

                for (Flashcard card: currentDeck.getCards()) {
                    if(card.getFront().equals(flashcard.getFront()) && card.getBack().equals(flashcard.getBack())){
                        flashcard = card;
                    }
                }

                currentDeck.removeCard(flashcard);
                currentDeck.addCard(flashcardEdited);
                helper.saveDeckToFile(currentDeck);
                helper.switchScene(event, "CardOverview.fxml");
            } else {
                statusbarLabel1.setText("Karte konnte nicht hinzugefügt werden. (Leere Felder?)");
            }

        } catch (Exception ex) {
            LogHelper.writeToLog(Level.INFO, "RIP");
        }
    }
}
