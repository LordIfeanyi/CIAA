package org.ciaa.mealplanner.utilities;

import java.io.*;
import java.util.ArrayList;
import org.ciaa.mealplanner.User;

/**
 * A class containing static methods responsible for creating, reading, updating
 * and deleting lines of the text file, "TextFile.txt".
 * 
 * Each line of the text file corresponds to a distinct user registered in the
 * system, and contains their data organized by tags and separated by dividers.
 */
public class TextFileHandler {

    /**
     * Adds a new User to the text file. Writes all of this user's attributes
     * to a single line in the file. Attributes are organized by a tag, followed by
     * an equals sign, the value, and finally ended with a forward slash.
     * Ex: id=12345/firstName=Bob/lastName=Smith/email= ...
     * 
     * @param user the User whose information will be written to the next line of
     *             the text file.
     */
    public static void addUser(User user) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("TextFile.txt", true));

            writer.write("id=" + user.getId() + "/");
            writer.write("firstName=" + user.getFirstName() + "/");
            writer.write("lastName=" + user.getLastName() + "/");
            writer.write("email=" + user.getEmail() + "/");
            writer.write("username=" + user.getUsername() + "/");
            writer.write("password=" + user.getPassword() + "/");
            writer.write("intolerances=");
            for (String intolerance : user.getIntolerances()) {
                writer.write(intolerance + ",");
            }
            writer.write("/");
            writer.write("meals=");
            for (String meal : user.getMeals()) {
                writer.write(meal + ",");
            }
            writer.write("/");

            writer.write("\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Searches each line of the text file for a username and password that matches
     * the passed parameters. Returns the entire line that the match was found on,
     * or null.
     * 
     * @param username the username being searched for in the text file.
     * @param password the password being searched for in the text file.
     * @return the line of the text file which the match was found on, or null.
     */
    public static String getLine(String username, String password) {

        try {
            BufferedReader reader = new BufferedReader(new FileReader("TextFile.txt"));
            String curLine = reader.readLine();
            while (curLine != null) {

                String returnedUserName = getUsername(curLine);
                String returnedPassword = getPassword(curLine);

                if (returnedUserName.equals(username) && returnedPassword.equals(password)) {
                    reader.close();
                    return curLine;
                }
                curLine = reader.readLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Parses the passed line of the text file for its id. The id is
     * located between the "id=" tag and the following forward slash "/".
     * 
     * @param curLine the line of the text file to be parsed.
     * @return the id, or null if it wasn't found.
     */
    public static String getId(String curLine) {
        int index = curLine.indexOf("id=");
        if (index == -1) {
            return null;
        }
        int beginIndex = index + 3;
        int endIndex = beginIndex;

        // find index of where the id ends, delineated by a forward dash "/" in
        // the text file.
        char curChar = curLine.charAt(beginIndex);
        while (curChar != '/') {
            endIndex++;
            curChar = curLine.charAt(endIndex);
        }
        return curLine.substring(beginIndex, endIndex);
    }

    /**
     * Parses the passed line of the text file for its first name. The first name is
     * located between the "firstName=" tag and the following forward slash "/".
     * 
     * @param curLine the line of the text file to be parsed.
     * @return the firstName, or null if it wasn't found.
     */
    public static String getFirstName(String curLine) {
        int index = curLine.indexOf("firstName=");
        if (index == -1) {
            return null;
        }
        int beginIndex = index + 10;
        int endIndex = beginIndex;

        // find index of where the firstName ends, delineated by a forward dash "/" in
        // the text file.
        char curChar = curLine.charAt(beginIndex);
        while (curChar != '/') {
            endIndex++;
            curChar = curLine.charAt(endIndex);
        }
        return curLine.substring(beginIndex, endIndex);
    }

    /**
     * Parses the passed line of the text file for its last name. The last name is
     * located between the "lastName=" tag and the following forward slash "/".
     * 
     * @param curLine the line of the text file to be parsed.
     * @return the lastName, or null if it wasn't found.
     */
    public static String getLastName(String curLine) {
        int index = curLine.indexOf("lastName=");
        if (index == -1) {
            return null;
        }
        int beginIndex = index + 9;
        int endIndex = beginIndex;

        // find index of where the lastName ends, delineated by a forward dash "/" in
        // the text file.
        char curChar = curLine.charAt(beginIndex);
        while (curChar != '/') {
            endIndex++;
            curChar = curLine.charAt(endIndex);
        }
        return curLine.substring(beginIndex, endIndex);
    }

    /**
     * Parses the passed line of the text file for its email. The email is
     * located between the "email=" tag and the following forward slash "/".
     * 
     * @param curLine the line of the text file to be parsed.
     * @return the email, or null if it wasn't found.
     */
    public static String getEmail(String curLine) {
        int index = curLine.indexOf("email=");
        if (index == -1) {
            return null;
        }
        int beginIndex = index + 6;
        int endIndex = beginIndex;

        // find index of where the email ends, delineated by a forward dash "/" in
        // the text file.
        char curChar = curLine.charAt(beginIndex);
        while (curChar != '/') {
            endIndex++;
            curChar = curLine.charAt(endIndex);
        }
        return curLine.substring(beginIndex, endIndex);
    }

    /**
     * Parses the passed line of the text file for its username. The username is
     * located between the "username=" tag and the following forward slash "/".
     * 
     * @param curLine the line of the text file to be parsed.
     * @return the username, or null if it wasn't found.
     */
    public static String getUsername(String curLine) {
        int index = curLine.indexOf("username=");
        if (index == -1) {
            return null;
        }
        int beginIndex = index + 9;
        int endIndex = beginIndex;

        // find index of where the username ends, delineated by a forward dash "/" in
        // the text file.
        char curChar = curLine.charAt(beginIndex);
        while (curChar != '/') {
            endIndex++;
            curChar = curLine.charAt(endIndex);
        }
        return curLine.substring(beginIndex, endIndex);
    }

    /**
     * Parses the passed line of the text file for its password. The password is
     * located between the "password=" tag and the following forward slash "/".
     * 
     * @param curLine the line of the text file to be parsed.
     * @return the password, or null if it wasn't found.
     */
    public static String getPassword(String curLine) {
        int index = curLine.indexOf("password=");
        if (index == -1) {
            return null;
        }
        int beginIndex = index + 9;
        int endIndex = beginIndex;

        // find index of where the password ends, delineated by a forward dash "/" in
        // the text file.
        char curChar = curLine.charAt(beginIndex);
        while (curChar != '/') {
            endIndex++;
            curChar = curLine.charAt(endIndex);
        }
        return curLine.substring(beginIndex, endIndex);
    }

    /**
     * Parses the passed line of the text file for its intolerances. The
     * intolerances are a comma separated list located between the "intolerances="
     * tag and the following forward slash "/". This comma separated list is then
     * parsed and its individual elements are added to the list being returned.
     * 
     * @param curLine the line of the text file to be parsed.
     * @return a list of the intolerances.
     */
    public static ArrayList<String> getIntolerances(String curLine) {

        ArrayList<String> intolerances = new ArrayList<String>();

        int index = curLine.indexOf("intolerances=");
        if (index == -1) {
            return null;
        }
        int beginIndex = index + 13;
        int endIndex = beginIndex;

        // find index of where the intolerances ends, delineated by a forward dash "/"
        // in
        // the text file.
        char curChar = curLine.charAt(beginIndex);
        while (curChar != '/') {

            if (curChar == ',') {
                intolerances.add(curLine.substring(beginIndex, endIndex));
                beginIndex = endIndex + 1;
            }
            endIndex++;
            curChar = curLine.charAt(endIndex);
        }
        return intolerances;
    }

    /**
     * Parses the passed line of the text file for its saved meals. The meals are a
     * comma separated list located between the "meals=" tag and the following
     * forward slash "/". This comma separated list is then parsed and its
     * individual elements are added to the list being returned.
     * 
     * @param curLine the line of the text file to be parsed.
     * @return a list of the meals
     */
    public static ArrayList<String> getMeals(String curLine) {

        ArrayList<String> meals = new ArrayList<String>();

        int index = curLine.indexOf("meals=");
        if (index == -1) {
            return null;
        }
        int beginIndex = index + 6;
        int endIndex = beginIndex;

        // find index of where the password ends, delineated by a forward dash "/" in
        // the text file.
        char curChar = curLine.charAt(beginIndex);
        while (curChar != '/') {

            if (curChar == ',') {
                meals.add(curLine.substring(beginIndex, endIndex));
                beginIndex = endIndex + 1;
            }
            endIndex++;
            curChar = curLine.charAt(endIndex);
        }
        return meals;
    }

    /**
     * Edits the line of the text file corresponding to the passed User according to
     * its current data.
     * 
     * @param user the whose corresponding text file line will be edited.
     */
    public static void editLine(User user) {

        try {

            // read the entire text file into a temporary stringbuffer line by line
            BufferedReader reader = new BufferedReader(new FileReader("TextFile.txt"));
            StringBuffer inputBuffer = new StringBuffer();
            String curLine = reader.readLine();
            while (curLine != null) {

                // if this is the line that needs to be edited
                if (getId(curLine).equals(user.getId())) {

                    // rewrite the line according to the current state of the passed User's fields
                    curLine = "id=" + user.getId() + "/";
                    curLine += "firstName=" + user.getFirstName() + "/";
                    curLine += "lastName=" + user.getLastName() + "/";
                    curLine += "email=" + user.getEmail() + "/";
                    curLine += "username=" + user.getUsername() + "/";
                    curLine += "password=" + user.getPassword() + "/";
                    curLine += "intolerances=";
                    for (String intolerance : user.getIntolerances()) {
                        curLine += intolerance + ",";
                    }
                    curLine += "/";
                    curLine += "meals=";
                    for (String meal : user.getMeals()) {
                        curLine += meal + ",";
                    }
                    curLine += "/";
                }
                inputBuffer.append(curLine);
                inputBuffer.append('\n');

                curLine = reader.readLine();
            }
            reader.close();

            // rewrite the stringbuffer with the newly edited line back to the file
            FileOutputStream fileOut = new FileOutputStream("TextFile.txt");
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
