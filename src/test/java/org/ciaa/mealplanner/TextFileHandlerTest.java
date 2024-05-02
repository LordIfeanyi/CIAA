package org.ciaa.mealplanner;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.ciaa.mealplanner.utilities.TextFileHandler;
import java.util.ArrayList;

class TextFileHandlerTest {

    @Test
    void testGetUsername() {
        assertEquals("testUsername", TextFileHandler.getUsername(
                "id=1714680880437/firstName=John/lastName=Smith/email=jm@gmail.com/username=testUsername/password=testPassword/intolerances=Dairy,Egg,Gluten,Grain,/meals=660405,649817,636669,/"),
                "must be: testUsername");
    }

    @Test
    void testGetIntolerances() {

        ArrayList<String> expectedArray = new ArrayList<String>();
        expectedArray.add("Dairy");
        expectedArray.add("Egg");
        expectedArray.add("Gluten");
        expectedArray.add("Grain");

        assertEquals(expectedArray, TextFileHandler.getIntolerances(
                "id=1714680880437/firstName=John/lastName=Smith/email=jm@gmail.com/username=testUsername/password=testPassword/intolerances=Dairy,Egg,Gluten,Grain,/meals=660405,649817,636669,/"),
                "must be of type ArrayList<String> with contents: " + expectedArray.toString());
    }

    @Test
    void testGetUsernameAllPaths() {

        // Test for independent path: 1 - 2 - 3
        // This path occurs when the tag 'username=' is not found in the passed string.
        assertEquals(null, TextFileHandler.getUsername(
                "id=1714680880437/firstName=John/lastName=Smith/email=jm@gmail.com/password=testPassword/intolerances=Dairy,Egg,Gluten,Grain,/meals=660405,649817,636669,/"),
                "must be: null");

        // Test for independent path: 1 - 2 - 4 - 5 - 6 - 7 - 5 - ... - 8
        // This path represents the expected behavior of the method.
        assertEquals("testUsername", TextFileHandler.getUsername(
                "id=1714680880437/firstName=John/lastName=Smith/email=jm@gmail.com/username=testUsername/password=testPassword/intolerances=Dairy,Egg,Gluten,Grain,/meals=660405,649817,636669,/"),
                "must be: testUsername");
    }

    @Test
    void testGetIntolerancesAllPaths() {

        // Test for independent path: 1 - 2 - 3
        // This path occurs when the tag 'intolerances=' is not found in the passed string.
        assertEquals(null, TextFileHandler.getIntolerances(
                "id=1714680880437/firstName=John/lastName=Smith/email=jm@gmail.com/username=testUsername/password=testPassword/meals=660405,649817,636669,/"),
                "must be: null");

        // Test for independent path: 1 - 2 - 4 - 5 - 6 - 7 - 8 - 5 - ... - 9 and 1 - 2 - 4 - 5 - 6 - 8 - 5 - ... - 9
        // Both these paths represent the expected behavior of the method.
        ArrayList<String> expectedArray = new ArrayList<String>();
        expectedArray.add("Dairy");
        expectedArray.add("Egg");
        expectedArray.add("Gluten");
        expectedArray.add("Grain");
        assertEquals(expectedArray, TextFileHandler.getIntolerances(
                "id=1714680880437/firstName=John/lastName=Smith/email=jm@gmail.com/username=testUsername/password=testPassword/intolerances=Dairy,Egg,Gluten,Grain,/meals=660405,649817,636669,/"),
                "must be of type ArrayList<String> with contents: " + expectedArray.toString());

    }
}
