package GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;

public class MehrereScenenFX extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage meineStage) throws Exception {
        // Erstellen der Komponenten und des Layouts
        meineStage.setTitle("erste Szene");
        // Button fuer erste Szene
        final Button startButton = new Button();
        startButton.setText("LOS!");
        // Layout fuer erste Szene
        BorderPane firstRoot = new BorderPane();
        firstRoot.setCenter(startButton);
        Scene firstScene = new Scene(firstRoot, 100, 60);
        // Elemente fuer zweite Szene
        final TextField meinTextField = new TextField("Name");
        final ToggleButton ersterToggleButton = new ToggleButton("Morgen");
        final ToggleButton zweiterToggleButton = new ToggleButton("Anrede");
        final Button meinButton = new Button("START");
        final Label meinLabel = new Label("");
        // Ausrichtung der Komponenten
        BorderPane.setAlignment(meinLabel, Pos.CENTER);
        BorderPane.setAlignment(ersterToggleButton, Pos.CENTER);
        BorderPane.setAlignment(zweiterToggleButton, Pos.CENTER);
        // Layout fuer zweite Szene
        final BorderPane secondRoot = new BorderPane();
        secondRoot.setCenter(meinButton);
        secondRoot.setTop(meinTextField);
        secondRoot.setLeft(ersterToggleButton);
        secondRoot.setRight(zweiterToggleButton);
        secondRoot.setBottom(meinLabel);
        final Scene secondScene = new Scene(secondRoot, 200, 100);
        // Zuerst wird erste Szene angezeigt
        meineStage.setScene(firstScene);
        meineStage.show();
        // Ereignissteuerung
        startButton.addEventHandler(ActionEvent.ACTION,
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent meinEvent) {
                        meineStage.setScene(secondScene);
                        meineStage.setTitle("zweite Szene");
                    }
                });
        meinButton.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent myEvent) {
                if (ersterToggleButton.isSelected()) {
                    meinLabel.setText("Guten Morgen");
                } else {
                    meinLabel.setText("Guten Tag");
                }
                if (zweiterToggleButton.isSelected()) {
                    meinLabel.setText(meinLabel.getText() + " " + meinTextField.getText());
                }
                meinTextField.setText("");
            }
        });
    }
}