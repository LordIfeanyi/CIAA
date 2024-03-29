package org.ciaa.mealplanner;

import java.util.ArrayList;
import java.util.List;

import org.ciaa.mealplanner.models.User;
import org.ciaa.mealplanner.models.UserSignIn;

/**
 * Currently a set of static methods and an attribute responsible for saving
 * user information.
 */
public class Control {

    /**
     * An ArrayList of User objects representing the users currently registered in
     * the system. This is a temporary implementation! We need to implement user
     * data management externally (via a text file) so that it is saved indepdent
     * of deployments.
     */
    private static List<User> users = new ArrayList<User>();

    /**
     * Adds a User to the ArrayList of Users, "users".
     * 
     * @param user the new User to be added.
     */
    public static void addUser(User user) {
        users.add(user);
    }

    /**
     * Verifies that the credentials of the passed UserSignIn object are consistent
     * with a User in the "users" ArrayList. If there exists a corresponding User in
     * users, this method returns that User, otherwise returns null.
     * 
     * @param userSignIn the UserSignIn object whose credentials will be compared
     *                   against all User objects in "users" for a match.
     * @return the User in "users" whose credentials correspond to the passsed
     *         UserSignIn object, or null
     */
    public static User checkIfExists(UserSignIn userSignIn) {

        for (User user : users) {
            if (user.getUsername().equals(userSignIn.getUsername())
                    && user.getPassword().equals(userSignIn.getPassword())) {
                return user;
            }
        }
        return null;
    }

    /**
     * Prints the user array; used for troubleshooting.
     */
    public static void printUsers() {
        System.out.println("USERS:\n==========");
        for (User user : users) {
            System.out.println(user.toString() + ", \n");
        }
        System.out.println("\n==========");
    }
}
