package org.ciaa.mealplanner.models;

/**
 * A class representing a registered user of the system. When a new user
 * registers, a new instance of this class is created in
 * "RegisterController.java", and is added to the list of User objects, "users",
 * in "Control.java".
 */
public class User {

    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;

    private String id;

    // Constructor
    public User() {
        id = username + System.currentTimeMillis();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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
        return "User [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", username=" + username
                + ", password=" + password + "]";
    }

}
