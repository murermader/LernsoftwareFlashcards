import javafx.application.Application;
import javafx.stage.Stage;

public class GUI extends Application {

    public static void main(String[] arguments) {
        launch();

    }

    @Override
    public void start(Stage S) {
        S.show();
        System.out.println("Hello World");
    }
}
