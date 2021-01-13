package ro.mta.se.lab.Classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
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
            myReader.close();
            for(State i: states){
                //System.out.println(i.getCode());
                String output="Statul cu codul "+i.getCode()+" are urmatoarele orase:";
                System.out.println(output);
                for(City j: i.getCities()) {
                    System.out.println(j.getName());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
