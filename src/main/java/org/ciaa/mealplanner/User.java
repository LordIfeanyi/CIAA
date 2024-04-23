package org.ciaa.mealplanner;

import java.util.*;

/**
 * A class representing a registered user of the system.
 * Each new User is given a unique 'id' in the constructor according to the current time.
 * All attributes have getters and setters.
 */
public class User {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private ArrayList<String> intolerances;
    private ArrayList<String> meals;

    // Constructor
    public User() {
        id = "" + System.currentTimeMillis();
        intolerances = new ArrayList<>();
        meals = new ArrayList<String>();
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

    public ArrayList<String> getIntolerances() {
        return intolerances;
    }

    public void setIntolerances(ArrayList<String> intolerances) {
        this.intolerances = intolerances;
    }

    public void addIntolerance(String intolerance) {
        intolerances.add(intolerance);
    }

    public void removeIntolerance(String intolerance) {
        intolerances.remove(intolerance);
    }

    public void clearIntolerances() {
        intolerances.clear();
    }

    public ArrayList<String> getMeals() {
        return meals;
    }

    public void setMeals(ArrayList<String> meals) {
        this.meals = meals;
    }

    public void addMeal(String meal) {
        meals.add(meal);
    }

    public void removeMeal(String meal) {
        meals.remove(meal);
    }

    public void clearMeals() {
        meals.clear();
    }
}
