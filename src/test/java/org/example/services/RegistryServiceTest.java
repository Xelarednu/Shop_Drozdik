package org.example.services;

import org.example.helpers.AppHelper;
import org.example.model.Registry;
import org.example.repository.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RegistryServiceTest {

    private RegistryService registryService;
    private AppHelper<Registry> mockAppHelperRegistry;
    private Repository<Registry> mockRepositoryRegistry;

    @BeforeEach
    void setUp() {

        mockAppHelperRegistry = mock(AppHelper.class);
        mockRepositoryRegistry = mock(Repository.class);


        registryService = new RegistryService(mockAppHelperRegistry, mockRepositoryRegistry);
    }

    @Test
    void testAddValidRegistry() {
        Registry testRegistry = new Registry();
        when(mockAppHelperRegistry.create()).thenReturn(testRegistry);
        when(mockRepositoryRegistry.load()).thenReturn(List.of());

        boolean result = registryService.add();

        assertTrue(result, "add() should return true when a valid Registry is created.");
        verify(mockRepositoryRegistry, times(1)).save(testRegistry);
    }

    @Test
    void testAddInvalidRegistry() {
        when(mockAppHelperRegistry.create()).thenReturn(null);

        boolean result = registryService.add();

        assertFalse(result, "add() should return false when registry creation fails.");
        verify(mockRepositoryRegistry, never()).save(any(Registry.class));
    }

    @Test
    void testPrintListWithRegistries() {
        Registry registry1 = new Registry();
        Registry registry2 = new Registry();
        when(mockRepositoryRegistry.load()).thenReturn(List.of(registry1, registry2));
        when(mockAppHelperRegistry.printList(anyList())).thenReturn(true);

        boolean result = registryService.print();

        assertTrue(result, "print() should return true when the repository has Registries.");
        verify(mockAppHelperRegistry, times(1)).printList(anyList());
    }

    @Test
    void testPrintListWithNoRegistries() {
        when(mockRepositoryRegistry.load()).thenReturn(List.of());
        when(mockAppHelperRegistry.printList(anyList())).thenReturn(false);

        boolean result = registryService.print();

        assertFalse(result, "print() should return false when the repository is empty.");
        verify(mockAppHelperRegistry, times(1)).printList(anyList());
    }
}