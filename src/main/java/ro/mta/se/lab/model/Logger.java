package ro.mta.se.lab.model;

import ro.mta.se.lab.controller.Server;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
/**
 * This class is used for the logs//it was good a timestamp for logs too
 * */
public class Logger {
    private static Logger instance = null;
    private static String filename;

    static public Logger getInstance()
    {
        if(instance == null){
            instance = new Logger();
            start();
        }
        return instance;
    }
    /**
     * This function helps us to get the first log(when application starts)
     * */
    public static void start(){
        try {
            filename=Variables.log_file;
            String to_write="Application started...Log file was created successfully!\n";
            Files.write(Paths.get(filename), to_write.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    /**
     * This function is used for logging the user choice of country and city
     * @param state,city for the country and the city chosen by user
     * */
    public void log_choice(String state,String city) throws IOException{
        try{
            String to_write="User chose the city "+city+" from "+state+"\n";
            Files.write(Paths.get(filename), to_write.getBytes(), StandardOpenOption.APPEND);

        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    /**
     * This function is used for logging the parameters for the weather
     * @param temperature,real_feel,humidity,wind,pressure,weather the parameters
     * */
    public void log_information(String temperature,String real_feel,String humidity,String wind,String pressure,String weather){
        try{
            String to_write="The result of the search:Weather "+weather+", Temperature: "+temperature+" Celsius "+", Feels like: "+real_feel+" Celsius"+"\n";
            Files.write(Paths.get(filename), to_write.getBytes(), StandardOpenOption.APPEND);
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}