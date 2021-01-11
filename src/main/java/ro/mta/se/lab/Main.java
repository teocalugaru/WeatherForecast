package ro.mta.se.lab;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        stage.setScene(new Scene(new Pane(), 800, 600));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}