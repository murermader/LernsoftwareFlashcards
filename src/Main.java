import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

  @Override
  public void start(Stage stage) {
    try {
      Helper helper = new Helper();
      helper.createDirectories();
      Parent root = FXMLLoader.load(getClass().getResource("GUI/MainWindow.fxml"));
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.show();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {

    // In der Main Methode rufen wir launch auf, welches unser Fenster startet
    launch(args);
  }
}