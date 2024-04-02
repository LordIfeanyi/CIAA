package org.ciaa.mealplanner.utilities;

import java.io.*;

import org.ciaa.mealplanner.User;

public class TextFileHandler {

    /**
     * Adds a new User to the text file. Writes all of this user's attributes
     * to a single line in the file. Attributes are organized by a tag, followed by
     * an equals sign, the value, and finally ended with a forward slash.
     * Ex:
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

            writer.write("\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Searches each line of the text file for a username and password that matches
     * the passed parameters. Returns the line that the match was found on.
     * 
     * @param username the username being searched for in the text file.
     * @param password the password being searched for in the text file.
     * @return method returns true the line which the match was found on, or null.
     */
    public static String checkForMatch(String username, String password) {

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
        int beginIndex = index + 9;
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
}
