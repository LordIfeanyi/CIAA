package org.ciaa.mealplanner;

/**
 * A class representing an attempt to update the user's information. When a user
 * updates their information in the UserInfo html page, a new instance of this
 * class is created automatically in the UserInfoController class.
 */
public class UpdateUserInfo {

    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;

    private String newIntolerance;
    private String removeIntolerance;
    private boolean clearIntolerances;

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

    public String getNewIntolerance() {
        return newIntolerance;
    }

    public void setNewIntolerance(String newIntolerance) {
        this.newIntolerance = newIntolerance;
    }

    public String getRemoveIntolerance() {
        return removeIntolerance;
    }

    public void setRemoveIntolerance(String removeIntolerance) {
        this.removeIntolerance = removeIntolerance;
    }

    public boolean getClearIntolerances() {
        return clearIntolerances;
    }

    public void setClearIntolerances(boolean clearIntolerances) {
        this.clearIntolerances = clearIntolerances;
    }
}
