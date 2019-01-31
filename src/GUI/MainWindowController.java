package GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class MainWindowController {

    public Main main;

    private Button button;
    private Stage primaryStage;

    public void setMain(Main main){
        this.main = main;
    }

    //: Todo GUI Controller anpassen an Java 11 und FXML Dateien neu Schreiben
    // :Todo Scenen Wechsel
    @FXML
    public void handlerUeben(){
        /*try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Stage stage = (Stage) .getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        }catch (IOException io){
            io.printStackTrace();
        }
        */
    }

    @FXML
    public  void handlerStapelanzeige(){

    }

    @FXML
    public void handlerKartehinzufuegen(){

    }

    @FXML
    public void handlerBeenden(){

    }
}
