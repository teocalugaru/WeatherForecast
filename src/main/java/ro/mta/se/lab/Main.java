package ro.mta.se.lab;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ro.mta.se.lab.Classes.Server;

/**
 * JavaFX App
 */
public class Main{

    /*@Override
    public void start(Stage stage) {
        stage.setScene(new Scene(new Pane(), 800, 600));
        stage.show();
    }*/
    public static void main(String[] args) throws  Exception {
        Server S=new Server();
        S.read_config("config_file.txt");
        //launch();
    }

}