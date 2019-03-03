import java.io.IOException;
import Model.Helper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    private Helper helper = new Helper();

    @Override
    public void start(Stage stage) {

        try {
            helper.createDirectories();

            //Nur Temporär, damit auf allen Geräten testbar
//            helper.createSampleDeck("test1",1, "Rafael");
//            helper.createSampleDeck("test2",10, "Silas");
//            helper.createSampleDeck("test3",20, "Kai");
//            helper.createSampleDeck("test1",100, "Rafael");
//            helper.createSampleDeck("test2",100, "Silas");
//            helper.createSampleDeck("test3",100, "Kai");
//            helper.createSampleDeck("test4",2, "Beispieldeck");
//            helper.createSampleDeck("test4",2, "Beispieldeck");

            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("View/MainWindow.fxml"));
            Scene scene = new Scene(root);
            stage.getIcons().add(new Image("View/icon.png"));
            stage.setTitle("Flashcards - Projekt von Rafael, Kai & Silas");
            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        // In der Main Methode rufen wir launch auf, welches unser Fenster startet
        launch(args);
    }
}