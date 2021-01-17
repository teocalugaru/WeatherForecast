package ro.mta.se.lab;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import ro.mta.se.lab.model.*;
import javafx.fxml.FXMLLoader;
import ro.mta.se.lab.controller.Server;
import java.io.IOException;

/**
 * JavaFX App
 * Main Class
 */
public class Main extends Application{
    private static Scene scene;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        try {
            Logger log=Logger.getInstance();
            Server server = Server.getInstance();
            server.read_config(Variables.config_file);
            loader.setLocation(this.getClass().getResource("weather.fxml"));
            this.scene=new Scene(loader.load());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws  Exception {
        launch(args);
    }

}