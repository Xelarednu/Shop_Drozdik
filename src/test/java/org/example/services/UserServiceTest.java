package org.example.services;

import org.example.helpers.AppHelper;
import org.example.model.User;
import org.example.repository.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private UserService userService;
    private AppHelper<User> mockUserAppHelper;
    private Repository<User> mockUserRepository;

    @BeforeEach
    void setUp() {
        mockUserAppHelper = mock(AppHelper.class);
        mockUserRepository = mock(Repository.class);

        userService = new UserService(mockUserAppHelper, mockUserRepository);
    }

    @Test
    void testAddValidUser() {
        User testUser = new User("John", "Doe", "john.doe@example.com");
        when(mockUserAppHelper.create()).thenReturn(testUser);
        when(mockUserRepository.load()).thenReturn(List.of());

        boolean result = userService.add();

        assertTrue(result, "add() should return true when a valid User is created.");
        verify(mockUserRepository, times(1)).save(testUser);
    }

    @Test
    void testAddInvalidUser() {
        when(mockUserAppHelper.create()).thenReturn(null);

        boolean result = userService.add();

        assertFalse(result, "add() should return false when user creation fails.");
        verify(mockUserRepository, never()).save(any(User.class));
    }

    @Test
    void testPrintListWithUsers() {
        User user1 = new User("Jane", "Doe", "jane.doe@example.com");
        User user2 = new User("Alice", "Smith", "alice.smith@example.com");
        when(mockUserRepository.load()).thenReturn(List.of(user1, user2));
        when(mockUserAppHelper.printList(anyList())).thenReturn(true);

        boolean result = userService.print();

        assertTrue(result, "print() should return true when the repository has Users.");
        verify(mockUserAppHelper, times(1)).printList(anyList());
    }

    @Test
    void testPrintListWithNoUsers() {
        when(mockUserRepository.load()).thenReturn(List.of());
        when(mockUserAppHelper.printList(anyList())).thenReturn(false);

        boolean result = userService.print();

        assertFalse(result, "print() should return false when the repository is empty.");
        verify(mockUserAppHelper, times(1)).printList(anyList());
    }
}
