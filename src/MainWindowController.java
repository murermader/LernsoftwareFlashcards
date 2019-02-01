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
    public void handlerUeben(ActionEvent event)throws IOException{
        Parent uebenViewParent = FXMLLoader.load(getClass().getResource("UebenWindow.fxml"));
        Scene uebenViewScene = new Scene(uebenViewParent);

        //This line gets the Stage information
        Stage window1 = (Stage)((Node)event.getSource()).getScene().getWindow();

        window1.setScene(uebenViewScene);
        window1.show();
    }


    @FXML
    public  void handlerStapelanzeige(ActionEvent event)throws IOException{
        Parent stapelanzeigeParent = FXMLLoader.load(getClass().getResource("StapelanzeigeWindow.fxml"));
        Scene stapelanzeigeScene= new Scene(stapelanzeigeParent);

        Stage window2 = (Stage)((Node)event.getSource()).getScene().getWindow();

        window2.setScene(stapelanzeigeScene);
        window2.show();

    }

    @FXML
    public void handlerKartehinzufuegen(ActionEvent event)throws IOException{
        Parent kartehinzufuegenParent = FXMLLoader.load(getClass().getResource("KartehinzufuegenWindow.fxml"));
        Scene kartehinzufuegenScene = new Scene(kartehinzufuegenParent);

        Stage window3 = (Stage)((Node)event.getSource()).getScene().getWindow();
        window3.setScene(kartehinzufuegenScene);
        window3.show();
    }

    @FXML
    public void handlerBeenden(){
        System.exit(0);

    }
}
