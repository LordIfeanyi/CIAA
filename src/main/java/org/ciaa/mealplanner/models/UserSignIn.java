package org.ciaa.mealplanner.models;

/**
 * A class representing a user log-in attempt. When a user attempts to log in, a
 * new instance of this class is created in "LoginController.java", and is then
 * used in the checkIfExists() method of "Control.java" for user verification.
 */
public class UserSignIn {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserSignIn [username=" + username + ", password=" + password + "]";
    }

}
