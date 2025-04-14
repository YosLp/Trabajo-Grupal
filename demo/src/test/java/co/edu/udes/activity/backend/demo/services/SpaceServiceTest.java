package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.models.Space;
import co.edu.udes.activity.backend.demo.repositories.SpaceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SpaceServiceTest {

    private SpaceRepository spaceRepository;
    private SpaceService spaceService;

    @BeforeEach
    void setUp() {
        spaceRepository = mock(SpaceRepository.class);
        spaceService = new SpaceService();
        spaceService.spaceRepository = spaceRepository;
    }

    @Test
    void testGetAllSpaces() {
        Space espacio1 = new Space();
        espacio1.setId(1L);
        espacio1.setName("Salón 101");
        espacio1.setType("Aula");
        espacio1.setCapacity(30);
        espacio1.setAvailable(true);

        Space espacio2 = new Space();
        espacio2.setId(2L);
        espacio2.setName("Laboratorio 1");
        espacio2.setType("Laboratorio");
        espacio2.setCapacity(25);
        espacio2.setAvailable(false);

        List<Space> espacios = Arrays.asList(espacio1, espacio2);
        when(spaceRepository.findAll()).thenReturn(espacios);

        List<Space> resultado = spaceService.getAllSpaces();

        assertEquals(2, resultado.size());
        assertEquals("Salón 101", resultado.get(0).getName());
        verify(spaceRepository, times(1)).findAll();
    }

    @Test
    void testGetSpaceByIdExistente() {
        Space espacio = new Space();
        espacio.setId(1L);
        espacio.setName("Auditorio");
        espacio.setType("Sala");
        espacio.setCapacity(100);
        espacio.setAvailable(true);

        when(spaceRepository.findById(1L)).thenReturn(Optional.of(espacio));

        Optional<Space> resultado = spaceService.getSpaceById(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Auditorio", resultado.get().getName());
        verify(spaceRepository).findById(1L);
    }

    @Test
    void testGetSpaceByIdInexistente() {
        when(spaceRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<Space> resultado = spaceService.getSpaceById(99L);

        assertFalse(resultado.isPresent());
        verify(spaceRepository).findById(99L);
    }

    @Test
    void testSaveSpace() {
        Space nuevo = new Space();
        nuevo.setName("Biblioteca");
        nuevo.setType("Sala");
        nuevo.setCapacity(50);
        nuevo.setAvailable(true);

        when(spaceRepository.save(nuevo)).thenReturn(nuevo);

        Space resultado = spaceService.saveSpace(nuevo);

        assertNotNull(resultado);
        assertEquals("Biblioteca", resultado.getName());
        verify(spaceRepository).save(nuevo);
    }

    @Test
    void testUpdateSpaceExistente() {
        Space existente = new Space();
        existente.setId(1L);
        existente.setName("Salón Antiguo");
        existente.setType("Aula");
        existente.setCapacity(20);
        existente.setAvailable(false);

        Space actualizado = new Space();
        actualizado.setName("Salón Moderno");
        actualizado.setType("Auditorio");
        actualizado.setCapacity(40);
        actualizado.setAvailable(true);

        when(spaceRepository.findById(1L)).thenReturn(Optional.of(existente));
        when(spaceRepository.save(any(Space.class))).thenReturn(existente);

        Space resultado = spaceService.updateSpace(1L, actualizado);

        assertNotNull(resultado);
        assertEquals("Salón Moderno", resultado.getName());
        verify(spaceRepository).findById(1L);
        verify(spaceRepository).save(existente);
    }

    @Test
    void testUpdateSpaceInexistente() {
        Space actualizado = new Space();
        actualizado.setName("Nuevo nombre");

        when(spaceRepository.findById(100L)).thenReturn(Optional.empty());

        Space resultado = spaceService.updateSpace(100L, actualizado);

        assertNull(resultado);
        verify(spaceRepository).findById(100L);
        verify(spaceRepository, never()).save(any());
    }

    @Test
    void testDeleteSpaceExistente() {
        when(spaceRepository.existsById(1L)).thenReturn(true);

        boolean resultado = spaceService.deleteSpace(1L);

        assertTrue(resultado);
        verify(spaceRepository).deleteById(1L);
    }

    @Test
    void testDeleteSpaceInexistente() {
        when(spaceRepository.existsById(99L)).thenReturn(false);

        boolean resultado = spaceService.deleteSpace(99L);

        assertFalse(resultado);
        verify(spaceRepository, never()).deleteById(any());
    }
}
