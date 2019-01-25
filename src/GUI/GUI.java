package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class GUI extends Application {

    private  Stage S;
    public static void main(String[] arguments) {
        launch();

    }

    @Override
    public void start(Stage S) {
        this.S = S;
        mainWindow();
        /*
        VBox parent2 = new VBox(); //Vertikale anordnung der Nodes
        HBox parent = new HBox(); //Horizontale anordnung der Nodes

        Label label = new Label("Test"); //Label später für die Anzeige der Fragen und Antworten
        parent.getChildren().add(label);

        Button button = new Button("Knopf"); //Buttons zur Anzeige der Schwierigkeit (Text/Zahl und AKTIONEN) Methoden get() / set()
        parent2.getChildren().add(button);

        TextField textField = new TextField("Text"); //Textfelder editierbar (Später um neue Flaschcard zu machen)(Text wird übergeben)
        parent.getChildren().add(textField);

        Slider slider = new Slider(); //Slider (möglich für die Schwierigkeit (Eine Zahl wird übergeben)
        parent.getChildren().add(slider);

        TextArea textArea = new TextArea(); //Ähnlich wie Textfeld nür größer
        parent2.getChildren().add(textArea);



        Scene scene = new Scene(parent);
        Scene scene2 = new Scene(parent2);
        S.setScene(scene);
        S.show();
        System.out.println("Hello World");
        */

    }

    public void mainWindow(){

       try {
           FXMLLoader loader = new FXMLLoader(GUI.class.getResource("GUI/MainWindow.fxml"));
           AnchorPane pane = loader.load();

           S.setMinHeight(400.00);
           S.setMinWidth(500.00);

           MainWindowController mainWindowController = loader.getController();
           mainWindowController.setMain(this);

           Scene scene = new Scene(pane);
           S.setScene(scene);
           S.show();
       }catch (IOException e){
           e.printStackTrace();
       }
    }
}
