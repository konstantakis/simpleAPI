package gkonstan.api.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import gkonstan.api.server.database.utils.InitialData;
import gkonstan.api.server.model.*;

@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Starting Server!" );
        InitialData.InsertFakeData();
        SpringApplication.run(App.class, args);
    }
}
