package org.ciaa.mealplanner.types;

/**
 * A data class representing an attempt to update
 * a user's information used for serializing html input.
 * Fields exist for every field of the User class, and
 * each has getters and setters.
 *
 * @author Andrew Mazlumyan
 * <p>
 * Created on 2024-04-09
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

    @SuppressWarnings("unused")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @SuppressWarnings("unused")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    @SuppressWarnings("unused")
    public void setEmail(String email) {
        this.email = email;
    }

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

    public String getNewIntolerance() {
        return newIntolerance;
    }

    @SuppressWarnings("unused")
    public void setNewIntolerance(String newIntolerance) {
        this.newIntolerance = newIntolerance;
    }

    public String getRemoveIntolerance() {
        return removeIntolerance;
    }

    @SuppressWarnings("unused")
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

    @SuppressWarnings("unused")
    public void setClearMeals(boolean clearMeals) {
        this.clearMeals = clearMeals;
    }
}
