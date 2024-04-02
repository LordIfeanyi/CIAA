package org.ciaa.mealplanner;

/**
 * A class representing a user log-in attempt. When a user attempts to log in, a
 * new instance of this class is created automatically in the login controller
 * class.
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
