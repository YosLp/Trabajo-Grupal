package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.models.Material;
import co.edu.udes.activity.backend.demo.repositories.MaterialRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MaterialServiceTest {

    @Mock
    private MaterialRepository materialRepository;

    @InjectMocks
    private MaterialService materialService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllMaterials() {
        Material material1 = new Material();
        material1.setId(1L);
        material1.setName("Proyector");
        material1.setType("Electrónico");
        material1.setAmount(2);
        material1.setAvailable(true);

        Material material2 = new Material();
        material2.setId(2L);
        material2.setName("Silla");
        material2.setType("Mobiliario");
        material2.setAmount(10);
        material2.setAvailable(true);

        List<Material> listaMateriales = Arrays.asList(material1, material2);
        when(materialRepository.findAll()).thenReturn(listaMateriales);

        List<Material> resultado = materialService.getAllMaterials();

        assertEquals(2, resultado.size());
        verify(materialRepository, times(1)).findAll();
    }

    @Test
    void testGetMaterialById() {
        Material material = new Material();
        material.setId(1L);
        material.setName("Proyector");

        when(materialRepository.findById(1L)).thenReturn(Optional.of(material));

        Optional<Material> resultado = materialService.getMaterialById(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Proyector", resultado.get().getName());
    }

    @Test
    void testSaveMaterial() {
        Material material = new Material();
        material.setName("Computador");

        when(materialRepository.save(material)).thenReturn(material);

        Material resultado = materialService.saveMaterial(material);

        assertNotNull(resultado);
        assertEquals("Computador", resultado.getName());
    }

    @Test
    void testUpdateMaterial() {
        Material materialExistente = new Material();
        materialExistente.setId(1L);
        materialExistente.setName("Computador");
        materialExistente.setType("Electrónico");
        materialExistente.setAmount(5);
        materialExistente.setAvailable(true);

        Material materialActualizado = new Material();
        materialActualizado.setName("Computador Portátil");
        materialActualizado.setType("Electrónico");
        materialActualizado.setAmount(3);
        materialActualizado.setAvailable(false);

        when(materialRepository.findById(1L)).thenReturn(Optional.of(materialExistente));
        when(materialRepository.save(any(Material.class))).thenReturn(materialExistente);

        Material resultado = materialService.updateMaterial(1L, materialActualizado);

        assertNotNull(resultado);
        assertEquals("Computador Portátil", resultado.getName());
        assertEquals(3, resultado.getAmount());
        assertFalse(resultado.isAvailable());
    }

    @Test
    void testDeleteMaterial() {
        when(materialRepository.existsById(1L)).thenReturn(true);
        doNothing().when(materialRepository).deleteById(1L);

        boolean resultado = materialService.deleteMaterial(1L);

        assertTrue(resultado);
        verify(materialRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteMaterialNotFound() {
        when(materialRepository.existsById(99L)).thenReturn(false);

        boolean resultado = materialService.deleteMaterial(99L);

        assertFalse(resultado);
        verify(materialRepository, never()).deleteById(99L);
    }
}