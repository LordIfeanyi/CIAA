package org.ciaa.mealplanner;

import org.ciaa.mealplanner.utilities.TextFileHandler;

/**
 * A class containing static methods responsible for performing the fundamental
 * functions of the application. This includes adding new users and handling
 * login authentication.
 */
public class Control {

    /**
     * A User object representing the user currently using the application.
     */
    private static User currentUser;

    /**
     * Uses "TextFileHandler.java" to add a new User to the text file.
     * 
     * @param user the new User to be added.
     */
    public static void addNewUser(User user) {
        TextFileHandler.addUser(user);
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
     * Sets the passed User as the currentUser field.
     * 
     * @param user the User to be set as the currentUser.
     */
    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    /**
     * Creates a new User object with attributes from the passed line of the text
     * file, and sets the 'currentUser' field of this class to that new User object.
     * 
     * @param textFileLine the text file line whose information will be used to
     *                     create the new current user.
     */
    public static void setCurrentUser(String textFileLine) {

        currentUser = new User();
        currentUser.setId(TextFileHandler.getId(textFileLine));
        currentUser.setFirstName(TextFileHandler.getFirstName(textFileLine));
        currentUser.setLastName(TextFileHandler.getLastName(textFileLine));
        currentUser.setEmail(TextFileHandler.getEmail(textFileLine));
        currentUser.setUsername(TextFileHandler.getUsername(textFileLine));
        currentUser.setPassword(TextFileHandler.getPassword(textFileLine));
        currentUser.setIntolerances(TextFileHandler.getIntolerances(textFileLine));
        currentUser.setMeals(TextFileHandler.getMeals(textFileLine));
    }

    /**
     * Checks if the passed UserSignIn object corresponds to a User in the text
     * file. If it does, uses 'setCurrentUser()' to make a new User object with the
     * text file line of the user whose information matched the passed UserSignIn
     * object, sets that new User object as the 'currentUser', and returns true.
     * 
     * @param userSignIn the UserSignIn object to be checked against the user data
     *                   in the text file.
     * @return true if the passed userSignIn corresponds to a User in the system,
     *         false otherwise.
     */
    public static boolean authenticateUser(UserSignIn userSignIn) {

        String textFileLine = TextFileHandler.getLine(userSignIn.getUsername(), userSignIn.getPassword());

        if (textFileLine != null) {
            setCurrentUser(textFileLine);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Updates the fields of the current User with the information of the passed
     * UpdateUserInfo object. Also updates the text file by editing the text file
     * line which corresponds to the current user.
     * 
     * If any fields of the UpdateUserInfo object are null, the user's initial
     * values for those fields are preserved.
     * 
     * @param updatedInfo the object containing the updated user information.
     */
    public static void updateUserInfo(UpdateUserInfo updatedInfo) {

        // profile info
        if (updatedInfo.getFirstName() != "" && updatedInfo.getFirstName() != null) {
            currentUser.setFirstName(updatedInfo.getFirstName());
        }
        if (updatedInfo.getLastName() != "" && updatedInfo.getLastName() != null) {
            currentUser.setLastName(updatedInfo.getLastName());
        }
        if (updatedInfo.getEmail() != "" && updatedInfo.getEmail() != null) {
            currentUser.setEmail(updatedInfo.getEmail());
        }
        if (updatedInfo.getUsername() != "" && updatedInfo.getUsername() != null) {
            currentUser.setUsername(updatedInfo.getUsername());
        }
        if (updatedInfo.getPassword() != "" && updatedInfo.getPassword() != null) {
            currentUser.setPassword(updatedInfo.getPassword());
        }

        // intolerances
        if (updatedInfo.getNewIntolerance() != "" && updatedInfo.getNewIntolerance() != null) {
            currentUser.addIntolerance(updatedInfo.getNewIntolerance());
        }
        if (updatedInfo.getRemoveIntolerance() != "" && updatedInfo.getRemoveIntolerance() != null) {
            currentUser.removeIntolerance(updatedInfo.getRemoveIntolerance());
        }
        if (updatedInfo.getClearIntolerances() == true) {
            currentUser.clearIntolerances();
        }

        // meals
        if (updatedInfo.getNewMeal() != "" && updatedInfo.getNewMeal() != null) {
            currentUser.addMeal(updatedInfo.getNewMeal());
        }
        if (updatedInfo.getRemoveMeal() != "" && updatedInfo.getRemoveMeal() != null) {
            currentUser.removeMeal(updatedInfo.getRemoveMeal());
        }
        if (updatedInfo.getClearMeals() == true) {
            currentUser.clearMeals();
        }

        // update the text file
        TextFileHandler.editLine(currentUser);
    }
}
