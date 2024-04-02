package org.ciaa.mealplanner;

/**
 * A class representing a registered user of the system.
 */
public class User {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String[] intolerances;

    // Constructor
    public User() {
        id = "" + System.currentTimeMillis();

        // testing
        intolerances = new String[3];
        intolerances[0] = "dairy";
        intolerances[1] = "egg";
        intolerances[2] = "peanut";
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

    public String[] getIntolerances() {
        return intolerances;
    }

    public void setIntolerances(String[] intolerances) {
        this.intolerances = intolerances;
    }
}
