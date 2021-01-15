package ro.mta.se.lab;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.json.JSONObject;
import ro.mta.se.lab.Classes.*;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.util.ArrayList;

/**
 * JavaFX App
 */
public class Main extends Application{
    private static Scene scene;
    private static Server server;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        try {
            server=new Server();
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