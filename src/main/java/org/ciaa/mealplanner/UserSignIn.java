package org.ciaa.mealplanner;

/**
 * A data class representing a user sign in attempt used for serializing html
 * input. Consists of a username and password along with getters & setters.
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
