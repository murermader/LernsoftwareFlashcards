package GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import GUI.Main;

import java.io.IOException;

public class Controller {
  //View
    public Button handlerUeben;
    public Button handlerAnsehen;
    public Button handlerHinzufuegen;
    public Button handlerBeenden;

    private Main main;

    public void setMain(Main main){
      this.main = main;
    }
    @FXML
    public void handlerUeben()throws Exception{

      FXMLLoader loader = new FXMLLoader(Main.class.getResource("UebenWindow.fxml"));
      AnchorPane pane = loader.load();

      Scene scene = new Scene(pane);


      Stage primaryStage = new Stage();
      primaryStage.setTitle("Flashcards");
      primaryStage.setScene(scene);
      primaryStage.show();
    }

    @FXML
    public  void handlerAnsehen()throws Exception{
      System.out.println("Handler Ansehen");
      FXMLLoader loader = new FXMLLoader(Main.class.getResource("UebenWindow.fxml"));
      AnchorPane pane = loader.load();

      Scene scene = new Scene(pane);


      Stage thridStage = new Stage();
      thridStage.setTitle("Flashcards");
      thridStage.setScene(scene);
      thridStage.show();
    }

    @FXML
    public  void handlerHinzufuegen()throws Exception{
      System.out.println("Handler Hinzufügen");
      FXMLLoader loader = new FXMLLoader(Main.class.getResource("UebenWindow.fxml"));
      AnchorPane pane = loader.load();

      Scene scene = new Scene(pane);


      Stage fourthStage = new Stage();
      fourthStage.setTitle("Flashcards");
      fourthStage.setScene(scene);
      fourthStage.show();
    }

    @FXML
    public void handlerBeenden()throws Exception{
      System.out.println("Handler Beenden");
      FXMLLoader loader = new FXMLLoader(Main.class.getResource("UebenWindow.fxml"));
      AnchorPane pane = loader.load();

      Scene scene = new Scene(pane);


      Stage fifthStage = new Stage();
      fifthStage.setTitle("Flashcards");
      fifthStage.setScene(scene);
      fifthStage.show();
    }
}
