package gkonstan.api.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Starting Server!" );
        SpringApplication.run(App.class, args);

    }
}
