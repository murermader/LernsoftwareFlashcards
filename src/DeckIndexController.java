import java.io.IOException;
import java.util.logging.Level;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class DeckIndexController {
  public ListView list = new ListView<String>();
  private Data data = new Data();
  ObservableList<String> deckNames = FXCollections.observableArrayList();

  public void handlerBack(ActionEvent event) throws IOException {
    deckNames.clear();
    Parent mainViewParent = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
    Scene mainViewScene = new Scene(mainViewParent);
    //This line gets the Stage information
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(mainViewScene);
    window.show();
  }

  @FXML
  public void initialize() {
    deckNames.clear();
      if(!data.isEmpty){

        deckNames.clear();
        for (Deck deck: data.getListOfDecks()) {
          deckNames.add(deck.getName());
        }
        //Noch keine Ahnung wie ich diese Warnung wegbekomme, voerst erstmal ignorieren
        //noinspection unchecked
        list.setItems(deckNames);
      }
  }


  public void handlerDeckSelect(ActionEvent event) throws IOException {
    String selectedItem = (String)list.getSelectionModel().getSelectedItem();

    if(selectedItem != null){
      for (Deck deck: data.getListOfDecks()) {
        if(selectedItem.equals(deck.getName())){
          Data.setCurrentDeckName(selectedItem);
          LogHelper.writeToLog(Level.INFO, "setCurrentDeckname: " + selectedItem);
        }
      }
    }
  }

  public void handlerDeckAdd(ActionEvent event) throws IOException {

  }
}
