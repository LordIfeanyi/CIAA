package org.ciaa.mealplanner.utilities;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import org.ciaa.mealplanner.User;

/**
 * A class containing static methods responsible for making http requests to the
 * meal api, spoonacular.
 * 
 * NOTE: This class's methods are currently not in use; spoonacular requests are
 * being made on the front end and this needs to be changed.
 */
public class ApiHandler {

    /**
     * The base url used to make api requests onto which more specifying tags are
     * appended.
     */
    private static String uri = "https://api.spoonacular.com/recipes/complexSearch?apiKey=4dc6eb4bd1ec453dbba335faac5055d8";

    /**
     * Appends the intolerances of the passed user to the uri according to the
     * spoonacular APIs convention.
     * 
     * @param user the User whose intolerances are appended to the uri
     */
    public static void setUri(User user) {
        String nextParam;
        for (String intolerance : user.getIntolerances()) {
            nextParam = "&" + intolerance;
            uri += nextParam;
        }
    }

    /**
     * Resets the uri back to the base.
     */
    public static void resetUri() {
        uri = "https://api.spoonacular.com/recipes/complexSearch?apiKey=4dc6eb4bd1ec453dbba335faac5055d8";
    }

    /**
     * Makes a GET request to the spoonacular api with the current 'uri' and prints
     * the response.
     * 
     * @throws Exception error fetching the data
     */
    public static void makeGetRequest() throws Exception {

        // make GET request
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI(uri))
                .GET()
                .build();

        // make client
        HttpClient httpClient = HttpClient.newHttpClient();

        // have client send the GET request and save the response
        HttpResponse<String> postResponse = httpClient.send(getRequest, BodyHandlers.ofString());

        // print the response
        System.out.println("postResponse = " + postResponse.body());
    }
}
