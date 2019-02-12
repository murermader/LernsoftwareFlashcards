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
  private ObservableList<String> deckNames = FXCollections.observableArrayList();

  @FXML
  public void initialize() {
    //Funktioniert nicht!
    list.getItems().clear();
    list.getSelectionModel().clearSelection();
    deckNames.clear();

      if(!data.isEmpty){

        for (Deck deck: data.getListOfDecks()) {
          deckNames.add(deck.getName());
          System.out.println(deck.getName());
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

  public void handlerBack(ActionEvent event) throws IOException {
    list.getItems().clear();
    deckNames.clear();
    Parent mainViewParent = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
    Scene mainViewScene = new Scene(mainViewParent);
    //This line gets the Stage information
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(mainViewScene);
    window.show();
  }

  public void handlerDeckAdd(ActionEvent event) throws IOException {

  }
}
