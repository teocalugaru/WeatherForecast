package ro.mta.se.lab.Classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.net.URL;
import java.net.URLConnection;
import org.json.JSONObject;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
public class Server {
    public void read_config(String file_path){
        ArrayList<State> states=new ArrayList<State>();
        int ok;
        try {

            File myObj = new File(file_path);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] del=data.split("\\s+");
                City new_city=new City(Integer.parseInt(del[0]),del[1],Double.parseDouble(del[2]),Double.parseDouble(del[3]),del[4]);
                ok=0;
                for (State index: states){
                    if(index.getCode().equals(new_city.getCode())){
                        index.getCities().add(new_city);
                        ok=1;
                        break;
                    }
                }
                if(ok==0){
                    State new_state=new State(new ArrayList<>(),new_city.getCode());
                    new_state.getCities().add(new_city);
                    states.add(new_state);
                }
            }
            Variables.statesList=new ArrayList<State>();
            Variables.statesList=states;
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public JSONObject read_from_server(String city){
        JSONObject json = null;
        String URL=Variables.URL;
        String key=Variables.key;
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URL + city + "&appid=" + key))
                    .build();
            HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
            json = new JSONObject(response.body().toString());
        }
        catch (IOException | InterruptedException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return json;
    }
}
