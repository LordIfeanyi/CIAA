package org.ciaa.mealplanner;

import ch.qos.logback.classic.Level;
import org.ciaa.mealplanner.utilities.ApiHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

/**
 * The class containing the main method responsible for running the application.
 *
 * @author C. Becerra
 * <p>
 * Created on 2024-02-20
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
     *
     * @param args an array of arguments passed to the application.
     */
    public static void main(String[] args) {
        LOGGER.info("Starting CIAA application...");
        if (Arrays.asList(args).contains("--debug")) ((ch.qos.logback.classic.Logger) LOGGER).setLevel(Level.DEBUG);
        SpringApplication.run(CiaaApplication.class, args);
    }


    @Bean
    public ApiHandler apiHandler() {
        String[] tokens = {"9fd619b00abb41b5ae7305ea3b06d2f3", "4dc6eb4bd1ec453dbba335faac5055d8"};
        return new ApiHandler(tokens[(int) (Math.random() * tokens.length)]);
    }
}
