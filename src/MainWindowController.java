import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainWindowController {

  //: Todo GUI Controller anpassen an Java 11 und FXML Dateien neu Schreiben
  // :Todo Scenen Wechsel

  @FXML
  public void handlerPractice(ActionEvent event) throws IOException {
    Parent practiceViewParent = FXMLLoader.load(getClass().getResource("GUI/PracticeWindow.fxml"));
    Scene practiceViewScene = new Scene(practiceViewParent);

    //This line gets the Stage information
    Stage window1 = (Stage) ((Node) event.getSource()).getScene().getWindow();

    window1.setScene(practiceViewScene);
    window1.show();
  }


  @FXML
  public void handlerDeckIndex(ActionEvent event) throws IOException {
    Parent DeckIndexParent = FXMLLoader.load(getClass().getResource("GUI/DeckIndex.fxml"));
    Scene DeckIndexScene = new Scene(DeckIndexParent);

    Stage window2 = (Stage) ((Node) event.getSource()).getScene().getWindow();

    window2.setScene(DeckIndexScene);
    window2.show();

  }

  @FXML
  public void handlerCardAdd(ActionEvent event) throws IOException {
    Parent CardAddParent = FXMLLoader.load(getClass().getResource("GUI/CardAdd.fxml"));
    Scene CardAddScene = new Scene(CardAddParent);

    Stage window3 = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window3.setScene(CardAddScene);
    window3.show();
  }

  @FXML
  public void handlerStats(ActionEvent event)throws IOException{
    Parent StatsWindowParent = FXMLLoader.load(getClass().getResource("GUI/StatsWindow.fxml"));
    Scene StatsWindowScene = new Scene(StatsWindowParent);

    Stage window3 = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window3.setScene(StatsWindowScene);
    window3.show();
  }

  @FXML
  public void handlerQuit() {
    System.exit(0);

  }
}
