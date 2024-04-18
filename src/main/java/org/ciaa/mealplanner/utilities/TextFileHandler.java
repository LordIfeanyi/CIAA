package org.ciaa.mealplanner.utilities;

import java.io.*;
import java.util.ArrayList;

import org.ciaa.mealplanner.UpdateUserInfo;
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
     * @return method returns the line which the match was found on, or null.
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
     * 
     * @param curLine
     * @return
     */
    public static ArrayList<String> getIntolerances(String curLine) {

        ArrayList<String> intolerances = new ArrayList<String>();

        int index = curLine.indexOf("intolerances=");
        if (index == -1) {
            return null;
        }
        int beginIndex = index + 13;
        int endIndex = beginIndex;

        // find index of where the password ends, delineated by a forward dash "/" in
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
     * 
     * @param curLine
     * @return
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
     * Edits a line of the text file. Each line in the file corresponds to a User.
     * Edits are made to users' fields according to the information of the passed
     * UpdateUserInfo object. The line to be edited is found by the passed User id.
     * 
     * @param user
     * @param updatedInfo the object containing the new information that the
     *                    user's fields will be changed to.
     */
    public static void editLine(User user, UpdateUserInfo updatedInfo) {

        try {

            // read the entire text file into a temporary stringbuffer
            BufferedReader reader = new BufferedReader(new FileReader("TextFile.txt"));
            StringBuffer inputBuffer = new StringBuffer();
            String curLine = reader.readLine();
            while (curLine != null) {

                if (getId(curLine).equals(user.getId())) { // this is the line that needs to be edited

                    // rewrite the line according to the fields of updatedInfo; if values in
                    // updatedInfo are null, maintain the preexisting values

                    // save values from the line (in case updatedInfo fields are null)
                    // String id = getId(curLine);
                    // String firstName = getFirstName(curLine);
                    // String lastName = getLastName(curLine);
                    // String email = getEmail(curLine);
                    // String username = getUsername(curLine);
                    // String password = getPassword(curLine);

                    // // rebuild the line
                    // curLine = "id=" + id + "/";
                    // if (updatedInfo.getFirstName() != "") {
                    // curLine += "firstName=" + updatedInfo.getFirstName() + "/";
                    // } else {
                    // curLine += "firstName=" + firstName + "/";
                    // }
                    // if (updatedInfo.getLastName() != "") {
                    // curLine += "lastName=" + updatedInfo.getLastName() + "/";
                    // } else {
                    // curLine += "lastName=" + lastName + "/";
                    // }
                    // if (updatedInfo.getEmail() != "") {
                    // curLine += "email=" + updatedInfo.getEmail() + "/";
                    // } else {
                    // curLine += "email=" + email + "/";
                    // }
                    // if (updatedInfo.getUsername() != "") {
                    // curLine += "username=" + updatedInfo.getUsername() + "/";
                    // } else {
                    // curLine += "username=" + username + "/";
                    // }
                    // if (updatedInfo.getPassword() != "") {
                    // curLine += "password=" + updatedInfo.getPassword() + "/";
                    // } else {
                    // curLine += "password=" + password + "/";
                    // }
                    // curLine += "intolerances=";
                    // for (String intolerance : user.getIntolerances()) {
                    // curLine += intolerance + ",";
                    // }
                    // curLine += "/";
                    // curLine += "meals=";
                    // for (String meal : user.getMeals()) {
                    // curLine += meal + ",";
                    // }
                    // curLine += "/";

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

            // rewrite the stringbuffer with a newly edited line back to the file
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
