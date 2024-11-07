package org.example.helpers;

import org.example.model.GraphicsCard;
import org.example.model.Registry;
import org.example.model.User;
import org.example.services.Service;
import org.example.repository.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AppHelperRegistryTest {

    private AppHelperRegistry appHelperRegistry;
    private Service<User> mockUserService;
    private Service<GraphicsCard> mockGraphicsCardService;
    private Scanner scanner;

    @BeforeEach
    void setUp() {
        mockUserService = mock(Service.class);
        mockGraphicsCardService = mock(Service.class);
        scanner = mock(Scanner.class);

        appHelperRegistry = new AppHelperRegistry(scanner, mockUserService, mockGraphicsCardService);
    }

    @Test
    void testCreateRegistrySuccess() {
        User testUser = new User();
        testUser.setFirstName("John");
        testUser.setLastName("Doe");
        testUser.setEmail("john.doe@example.com");

        GraphicsCard testGraphicsCard = new GraphicsCard();
        testGraphicsCard.setName("GTX 1080");

        List<User> users = new ArrayList<>();
        users.add(testUser);

        List<GraphicsCard> graphicsCards = new ArrayList<>();
        graphicsCards.add(testGraphicsCard);

        when(mockUserService.print()).thenReturn(true);
        when(mockUserService.getRepository().load()).thenReturn(users);
        when(mockGraphicsCardService.print()).thenReturn(true);
        when(mockGraphicsCardService.getRepository().load()).thenReturn(graphicsCards);

        when(scanner.nextLine()).thenReturn("1");
        when(scanner.nextLine()).thenReturn("1");

        Registry result = appHelperRegistry.create();

        assertNotNull(result, "Registry should be created successfully.");
        assertEquals(testUser, result.getUser(), "User should be correctly assigned.");
        assertEquals(testGraphicsCard, result.getGraphicsCard(), "GraphicsCard should be correctly assigned.");
        assertEquals(LocalDate.now(), result.getSellDate(), "Sell date should be set to current date.");
    }

    @Test
    void testCreateRegistryUserNotFound() {
        when(mockUserService.print()).thenReturn(false);

        Registry result = appHelperRegistry.create();

        assertNull(result, "Registry should not be created if no users are found.");
    }

    @Test
    void testCreateRegistryGraphicsCardNotFound() {
        User testUser = new User();
        testUser.setFirstName("John");
        testUser.setLastName("Doe");
        testUser.setEmail("john.doe@example.com");

        List<User> users = new ArrayList<>();
        users.add(testUser);

        when(mockUserService.print()).thenReturn(true);
        when(mockUserService.getRepository().load()).thenReturn(users);
        when(mockGraphicsCardService.print()).thenReturn(false);

        Registry result = appHelperRegistry.create();

        assertNull(result, "Registry should not be created if no graphics cards are found.");
    }

    @Test
    void testCreateRegistryExceptionHandling() {
        when(mockUserService.print()).thenReturn(true);
        when(mockGraphicsCardService.print()).thenReturn(true);
        when(mockUserService.getRepository().load()).thenReturn(new ArrayList<>());
        when(mockGraphicsCardService.getRepository().load()).thenReturn(new ArrayList<>());

        Registry result = appHelperRegistry.create();

        assertNull(result, "Registry creation should fail due to exception handling.");
    }
}