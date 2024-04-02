package org.ciaa.mealplanner;

import org.ciaa.mealplanner.utilities.ApiHandler;
import org.ciaa.mealplanner.utilities.TextFileHandler;

// import java.util.ArrayList;
// import java.util.List;

/**
 * Currently a set of static methods and an attribute responsible for saving
 * user information.
 */
public class Control {

    /**
     * The User object representing the user currently using the application.
     */
    private static User currentUser;

    /**
     * Uses TextFileHandler to add a new User to the text file.
     * 
     * @param user the new User to be added.
     */
    public static void addNewUser(User user) {
        TextFileHandler.addUser(user);
    }

    /**
     * Sets the passed User as the currentUser field.
     * 
     * @param user the User to be set as the currentUser.
     */
    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    /**
     * Returns the static current user field.
     * 
     * @return the current User.
     */
    public static User getCurrentUser() {
        return currentUser;
    }

    /**
     * Creates a new User object with attributes from the passed line of the text
     * file.
     * 
     * @param textFileLine a line from the text file.
     */
    public static void setCurrentUser(String textFileLine) {

        currentUser = new User();
        currentUser.setId(TextFileHandler.getId(textFileLine));
        currentUser.setFirstName(TextFileHandler.getFirstName(textFileLine));
        currentUser.setLastName(TextFileHandler.getLastName(textFileLine));
        currentUser.setEmail(TextFileHandler.getEmail(textFileLine));
        currentUser.setUsername(TextFileHandler.getUsername(textFileLine));
        currentUser.setPassword(TextFileHandler.getPassword(textFileLine));
    }

    /**
     * Checks if the userSignIn corresponds to a User in the text file. If it does,
     * makes a new User with that line's attributes via Control.setCurrentUser(),
     * sets that as the currentUser, and returns true.
     * 
     * @param userSignIn the UserSignIn to be checked against the user data in the
     *                   text file.
     * @return method returns true if the userSignIn corresponds to a User in the
     *         text file, false otherwise.
     */
    public static boolean authenticateUser(UserSignIn userSignIn) {

        String textFileLine = TextFileHandler.checkForMatch(userSignIn.getUsername(), userSignIn.getPassword());

        if (textFileLine != null) {
            setCurrentUser(textFileLine);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Uses ApiHandler to make a request using the intolerances of the current User.
     */
    public static void getSuggestions() {

        ApiHandler.setUri(currentUser);
        try {
            ApiHandler.makeGetRequest();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
