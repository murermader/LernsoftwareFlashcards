package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {

    // Hier legen wir einen Button an
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        mainWindow();
    }

    public  void  mainWindow(){
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("MainWindow.fxml"));
            AnchorPane pane = loader.load();

            primaryStage.setMinHeight(500.00);
            primaryStage.setMinHeight(500.00);

            MainWindowController mainWindowController = loader.getController();
            mainWindowController.setMain(this);

            Scene scene = new Scene(pane);
            primaryStage.setScene(scene);
            primaryStage.show();

        }catch (IOException e){
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {

        // In der Main Methode rufen wir launch auf, welches unser Fenster startet
        launch(args);
    }
}