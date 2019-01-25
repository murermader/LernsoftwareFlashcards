package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage primaryStage;
    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        mainWindow(primaryStage);
    }

    public void mainWindow(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("GUIWindow.fxml"));
        AnchorPane pane = loader.load();

        Scene scene = new Scene(pane);

        Controller controller = loader.getController();
        controller.setMain(this);

        primaryStage.setTitle("Flashcards");
        primaryStage.setScene(scene);
        primaryStage.show();



    }

    public void UebenWindow(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("UebenWindow.fxml"));
        AnchorPane pane = loader.load();

        Scene scene = new Scene(pane);

        Controller controller = loader.getController();
        controller.setMain(this);

        Stage secondaryStage = new Stage();
        secondaryStage.setTitle("Flashcards");
        secondaryStage.setScene(scene);
        secondaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
