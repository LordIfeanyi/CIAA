package org.ciaa.mealplanner;

/**
 * A data class representing an attempt to update a user's information used for
 * serializing html input. Fields exists for every fields of the User class, and
 * each has getters and setters.
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

    private String newMeal;
    private String removeMeal;
    private boolean clearMeals;

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

    public String getNewMeal() {
        return newMeal;
    }

    public void setNewMeal(String newMeal) {
        this.newMeal = newMeal;
    }

    public String getRemoveMeal() {
        return removeMeal;
    }

    public void setRemoveMeal(String removeMeal) {
        this.removeMeal = removeMeal;
    }

    public boolean getClearMeals() {
        return clearMeals;
    }

    public void setClearMeals(boolean clearMeals) {
        this.clearMeals = clearMeals;
    }
}
