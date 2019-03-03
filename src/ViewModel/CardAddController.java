package ViewModel;

import Model.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class CardAddController {


    public ListView CardList = new ListView();
    private static final Path appDirectoryLog = Paths.get(System.getenv("LOCALAPPDATA"), "flashcards", "Log");
    private static final Path appDirectoryRoot = Paths.get(System.getenv("LOCALAPPDATA"), "flashcards");
    private Path osXDirectory = Paths.get(System.getenv("user.home"), "Library", "Application Support", "flashcards");
    Helper helper = new Helper();
    Data data = new Data();


    public void initialize(String deckName) {


    }


    public void handlerBack(ActionEvent event) throws IOException {

        Parent mainViewParent = FXMLLoader.load(getClass().getClassLoader().getResource("View/MainWindow.fxml"));
        Scene mainViewScene = new Scene(mainViewParent);
        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(mainViewScene);
        window.show();
    }

    public void handlerAdd(ActionEvent event) throws IOException {

        System.out.println(Data.getCurrentDeckName());


        //Karte hinzufügen zu dem jeweiligen stapen
    }

}
