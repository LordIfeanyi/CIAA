package org.ciaa.mealplanner;

import org.apache.commons.logging.Log;
import org.ciaa.mealplanner.utilities.ApiHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CiaaApplication
{
    private static final InnerSpringApplication SPRING_APPLICATION = new InnerSpringApplication(CiaaApplication.class);

    public static void main(String[] args) {
        ApiHandler.suggestMeals(null);

        getApplicationLog().info("Starting CIAA application...");
        SPRING_APPLICATION.run(args);
    }

    public static Log getApplicationLog() {
        return SPRING_APPLICATION.getApplicationLog();
    }

    private static class InnerSpringApplication extends SpringApplication
    {
        public InnerSpringApplication(Class<?>... primarySources) {
            super(primarySources);
        }

        @Override
        public Log getApplicationLog() {
            return super.getApplicationLog();
        }
    }
}
