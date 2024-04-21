package org.ciaa.mealplanner;

import ch.qos.logback.classic.Level;
import org.ciaa.mealplanner.utilities.ApiHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class CiaaApplication
{
    public static final Logger LOGGER = LoggerFactory.getLogger(CiaaApplication.class);

    public static void main(String[] args) {
        LOGGER.info("Starting CIAA application...");
        if (Arrays.asList(args).contains("--debug")) ((ch.qos.logback.classic.Logger) LOGGER).setLevel(Level.DEBUG);
        LOGGER.debug("Starting " + ApiHandler.class.getName() + "...");
        // ApiHandler.suggestMeals(null);
        SpringApplication.run(CiaaApplication.class, args);
    }
}
