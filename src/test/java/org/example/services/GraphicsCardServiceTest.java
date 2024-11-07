package org.example.services;

import org.example.helpers.AppHelper;
import org.example.model.GraphicsCard;
import org.example.repository.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GraphicsCardServiceTest {

    private GraphicsCardService graphicsCardService;
    private AppHelper<GraphicsCard> mockAppHelper;
    private Repository<GraphicsCard> mockRepository;

    @BeforeEach
    void setUp() {
        mockAppHelper = mock(AppHelper.class);
        mockRepository = mock(Repository.class);

        graphicsCardService = new GraphicsCardService(mockAppHelper, mockRepository);
    }

    @Test
    void testAddValidGraphicsCard() {
        GraphicsCard testGraphicsCard = new GraphicsCard("NVIDIA", 699, "RTX 3080",  10, 2020);
        when(mockAppHelper.create()).thenReturn(testGraphicsCard); // Simulate successful creation

        boolean result = graphicsCardService.add();

        assertTrue(result, "add() should return true when a valid GraphicsCard is created.");
        verify(mockRepository, times(1)).save(testGraphicsCard); // Verify that the graphics card was saved once
    }

    @Test
    void testAddInvalidGraphicsCard() {
        when(mockAppHelper.create()).thenReturn(null);

        boolean result = graphicsCardService.add();

        assertFalse(result, "add() should return false when GraphicsCard creation fails.");
        verify(mockRepository, never()).save(any(GraphicsCard.class)); // Verify that save() was never called
    }

    @Test
    void testPrintListWithGraphicsCards() {
        GraphicsCard graphicsCard1 = new GraphicsCard("AMD", 579, "RX 6800", 16, 2020);
        GraphicsCard graphicsCard2 = new GraphicsCard("NVIDIA", 229, "GTX 1660", 6, 2019);
        when(mockRepository.load()).thenReturn(List.of(graphicsCard1, graphicsCard2)); // Simulate repository with 2 graphics cards
        when(mockAppHelper.printList(anyList())).thenReturn(true); // Simulate that printList will succeed

        boolean result = graphicsCardService.print();

        assertTrue(result, "print() should return true when the repository has GraphicsCards.");
        verify(mockAppHelper, times(1)).printList(anyList()); // Ensure that printList() was called once
    }

    @Test
    void testPrintListWithNoGraphicsCards() {
        when(mockRepository.load()).thenReturn(List.of());
        when(mockAppHelper.printList(anyList())).thenReturn(false);

        boolean result = graphicsCardService.print();

        assertFalse(result, "print() should return false when the repository is empty.");
        verify(mockAppHelper, times(1)).printList(anyList());
    }
}