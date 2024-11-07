package org.example.helpers;

import org.example.model.GraphicsCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class AppHelperGraphicsCardTest {

    private AppHelperGraphicsCard appHelperGraphicsCard;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void testCreateValidInput() {
        String simulatedInput = "\nNVIDIA RTX 3090\n1500\nNVIDIA\n24\n2021\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        Scanner scanner = new Scanner(inputStream);

        appHelperGraphicsCard = new AppHelperGraphicsCard(scanner);

        GraphicsCard result = appHelperGraphicsCard.create();

        assertNotNull(result, "The graphics card should be created successfully.");
        assertEquals("NVIDIA RTX 3090", result.getName());
        assertEquals(1500, result.getPrice());
        assertEquals("NVIDIA", result.getManufacturer());
        assertEquals(24, result.getMemorySize());
        assertEquals(2021, result.getReleaseYear());
    }

    @Test
    void testCreateInvalidInput() {
        String simulatedInput = "\nInvalid Card\nnot_a_number\nAMD\nnot_a_number\n2020\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        Scanner scanner = new Scanner(inputStream);

        appHelperGraphicsCard = new AppHelperGraphicsCard(scanner);

        GraphicsCard result = appHelperGraphicsCard.create();

        assertNull(result, "The method should return null due to invalid input.");

        assertTrue(outputStream.toString().contains("Error:"), "Error message should be printed for invalid input.");
    }

    @Test
    void testPrintListWithGraphicsCards() {
        List<GraphicsCard> graphicsCards = List.of(
                new GraphicsCard("AMD", 1000, "Radeon RX 6900 XT", 16, 2020)
        );

        appHelperGraphicsCard = new AppHelperGraphicsCard(new Scanner(System.in));

        boolean result = appHelperGraphicsCard.printList(graphicsCards);

        assertTrue(result, "printList should return true for a non-empty list.");

        String output = outputStream.toString();
        assertTrue(output.contains("AMD"), "Output should contain the manufacturer.");
        assertTrue(output.contains("1000"), "Output should contain the price.");
        assertTrue(output.contains("Radeon RX 6900 XT"), "Output should contain the card name.");
        assertTrue(output.contains("16 GB"), "Output should contain the memory size.");
        assertTrue(output.contains("2020"), "Output should contain the release year.");
    }

    @Test
    void testPrintListEmpty() {
        appHelperGraphicsCard = new AppHelperGraphicsCard(new Scanner(System.in));

        boolean result = appHelperGraphicsCard.printList(List.of());

        assertFalse(result, "printList should return false for an empty list.");

        assertTrue(outputStream.toString().contains(" --- List is empty --- "),
                "Output should indicate that the list is empty.");
    }
}
