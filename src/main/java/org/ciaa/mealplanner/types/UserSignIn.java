package org.ciaa.mealplanner.types;

/**
 * A data class representing a user sign-in attempt used for serializing html input.
 * Consists of a username and password along with getters and setters.
 *
 * @author Andrew Mazlumyan
 * <p>
 * Created on 2024-03-19
 */
public class UserSignIn {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    @SuppressWarnings("unused")
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    @SuppressWarnings("unused")
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserSignIn [username=" + username + ", password=" + password + "]";
    }
}
