package GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Controller {
  //View
    public Button ueben;
    public Button ansehen;
    public   Button hinzufuegen;
    public Button beenden;

    @FXML
    public void handlerUeben(){
      System.out.println("Handler Üben");
    }

    @FXML
    public  void handlerAnsehen(){
      System.out.println("Handler Ansehen");
    }

    @FXML
    public  void handlerHinzufuegen(){
      System.out.println("Handler Hinzufügen");
    }

    @FXML
    public void handlerBeenden(){
      System.out.println("Handler Beenden");
    }
}
