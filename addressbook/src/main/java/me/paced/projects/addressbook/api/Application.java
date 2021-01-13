package me.paced.projects.addressbook.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Runner class for the RESTful API.
 */
@SpringBootApplication
public class Application {

    /**
     * Start the {@link SpringApplication} to run the API.
     *
     * @param args the command line arguments
     */
    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
