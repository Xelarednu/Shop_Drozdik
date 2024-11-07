package org.example.helpers;

import org.example.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class AppHelperUserTest {

    private AppHelperUser appHelperUser;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void testCreateValidInput() {
        String simulatedInput = "\nJohn\nDoe\njohn.doe@example.com\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        Scanner scanner = new Scanner(inputStream);

        appHelperUser = new AppHelperUser(scanner);

        User result = appHelperUser.create();

        assertNotNull(result, "User should be created successfully.");
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        assertEquals("john.doe@example.com", result.getEmail());
    }

    @Test
    void testPrintListWithUsers() {
        List<User> users = List.of(
                new User("Jane", "Doe", "jane.doe@example.com")
        );

        appHelperUser = new AppHelperUser(new Scanner(System.in));

        boolean result = appHelperUser.printList(users);

        assertTrue(result, "printList should return true for a non-empty list.");

        String output = outputStream.toString();
        assertTrue(output.contains("Jane"), "Output should contain the first name.");
        assertTrue(output.contains("Doe"), "Output should contain the last name.");
        assertTrue(output.contains("jane.doe@example.com"), "Output should contain the email.");
    }

    @Test
    void testPrintListEmpty() {
        appHelperUser = new AppHelperUser(new Scanner(System.in));

        boolean result = appHelperUser.printList(List.of());

        assertFalse(result, "printList should return false for an empty list.");

        assertTrue(outputStream.toString().contains(" --- List of customers is empty --- "),
                "Output should indicate that the list is empty.");
    }
}