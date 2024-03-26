package org.ciaa.mealplanner;

import java.util.ArrayList;
import java.util.List;

import org.ciaa.mealplanner.models.User;
import org.ciaa.mealplanner.models.UserSignIn;

public class Control {

    private static List<User> users = new ArrayList<User>();

    public static void addUser(User user) {
        users.add(user);
    }

    /**
     * If the passed UserSignIn corresponds to a User in users, return that user's
     * id, otherwise return an empty string.
     * 
     * @param userSignIn
     * @return
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

    public static void printUsers() {
        System.out.println("USERS:\n==========");
        for (User user : users) {
            System.out.println(user.toString() + ", \n");
        }
        System.out.println("\n==========");
    }
}
