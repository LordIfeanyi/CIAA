package org.ciaa.mealplanner.utilities;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import org.ciaa.mealplanner.User;

public class ApiHandler {

    private static String uri = "https://api.spoonacular.com/recipes/complexSearch?apiKey=4dc6eb4bd1ec453dbba335faac5055d8";

    /**
     * * Sets the uri field according to the intolerances of the passed User.
     * 
     * @param user the User whose intolerances will be incporated into the generated
     *             uri.
     */
    public static void setUri(User user) {

        String nextParam;

        for (String intolerance : user.getIntolerances()) {
            nextParam = "&" + intolerance; // * debug */ System.out.println("nextParam = " + nextParam);
            uri += nextParam;
        }
    }

    public static void resetUri() {
        uri = "https://api.spoonacular.com/recipes/complexSearch?apiKey=4dc6eb4bd1ec453dbba335faac5055d8";
    }

    /**
     * Makes a get request to the meal API with the current uri field and prints the
     * response.
     * 
     * @throws Exception
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
