package org.ciaa.mealplanner;

import ch.qos.logback.classic.Level;
import org.ciaa.mealplanner.utilities.ApiHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

/**
 * The class containing the main method responsible for running the application.
 */
@SpringBootApplication
public class CiaaApplication
{
    /**
     * The logger for the application.
     */
    public static final Logger LOGGER = LoggerFactory.getLogger(CiaaApplication.class);

    /**
     * The main method of the application.
     * @param args an array of arguments passed to the application.
     */
    public static void main(String[] args) {
        LOGGER.info("Starting CIAA application...");
        if (Arrays.asList(args).contains("--debug")) ((ch.qos.logback.classic.Logger) LOGGER).setLevel(Level.DEBUG);
        ApiHandler.finishSetup();
        SpringApplication.run(CiaaApplication.class, args);
    }
}
