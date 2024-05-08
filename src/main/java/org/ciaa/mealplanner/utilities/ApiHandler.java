package org.ciaa.mealplanner.utilities;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.ciaa.mealplanner.types.*;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The class responsible for handling API requests to the Spoonacular API.
 * This uses OkHttp to make these requests.
 *
 * @author C. Becerra
 * <p>
 * Created on 2024-04-12
 */
public class ApiHandler
{
    private static final String API_URL = "https://api.spoonacular.com/";
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiHandler.class);
    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();

    private final String apiKey;


    /**
     * Creates a new ApiHandler object with the passed API key.
     *
     * @param apiKey The API key to use for the API calls
     */
    public ApiHandler(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * Handles the random recipe suggestion API call for the passed user.
     *
     * @param user        The user for whom to suggest meals
     * @param includeTags The tags to include in the suggestion
     * @return A ListResponse object containing the suggested meals
     */
    public ListResponse<Recipe> suggestMeals(User user, String includeTags) {
        StringBuilder builder = new StringBuilder(API_URL + "recipes/random?apiKey=" + apiKey);
        builder.append("&number=").append(10);
        if (includeTags != null && !includeTags.isEmpty()) {
            builder.append("&include-tags=").append(includeTags);
        }
        ArrayList<String> userIntolerances = user.getIntolerances();
        if (!userIntolerances.isEmpty()) {
            builder.append("&exclude-tags=");
            userIntolerances.forEach(intolerance -> builder.append(intolerance).append(','));
            builder.deleteCharAt(builder.length() - 1);
        }
        String url = builder.toString();

        TypeToken<?> typeToken = TypeToken.getParameterized(ListResponse.class, Recipe.class);
        //noinspection unchecked
        return (ListResponse<Recipe>) getResponse(url, typeToken);
    }

    public ListResponse<SimpleRecipe> searchMeals(SearchMealsRequest request) {
        StringBuilder builder = new StringBuilder(API_URL + "recipes/complexSearch?apiKey=" + apiKey);
        builder.append("&number=").append(10);
        builder.append("&query=").append(request.query());
        if (request.cuisine() != null && !request.cuisine().isEmpty()) {
            builder.append("&cuisine=").append(request.cuisine());
        }
        if (request.diet() != null && !request.diet().isEmpty()) {
            builder.append("&diet=").append(request.diet());
        }
        if (request.mealType() != null && !request.mealType().isEmpty()) {
            builder.append("&type=").append(request.mealType());
        }
        if (request.intolerances() != null && !request.intolerances().isEmpty()) {
            builder.append("&intolerances=");
            request.intolerances().forEach(intolerance -> builder.append(intolerance).append(','));
            builder.deleteCharAt(builder.length() - 1);
        }
        String url = builder.toString();

        TypeToken<?> typeToken = TypeToken.getParameterized(ListResponse.class, SimpleRecipe.class);
        //noinspection unchecked
        return (ListResponse<SimpleRecipe>) getResponse(url, typeToken);
    }

    public Recipe getRecipeInformation(String id) {
        String url = API_URL + "recipes/" + id + "/information?apiKey=" + apiKey;

        TypeToken<?> typeToken = TypeToken.get(Recipe.class);
        return (Recipe) getResponse(url, typeToken);
    }

    @Nullable
    private <T> T getResponse(String url, TypeToken<T> typeToken) {
        Request request = new Request.Builder()
              .url(url)
              .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                LOGGER.error("Unsuccessful HTTP response: {}", response.message());
                return null;
            }

            if (response.body() != null) {
                String string = response.body().string();
                LOGGER.debug("Response from API: {}", string);
                return gson.fromJson(string, typeToken);
            }
        } catch (IOException e) {
            LOGGER.error("Connection problem: {}", e.getMessage());
        }

        LOGGER.error("An unknown error occurred.");
        return null;
    }
}
