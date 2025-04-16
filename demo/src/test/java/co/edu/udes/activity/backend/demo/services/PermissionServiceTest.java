package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.models.Permission;
import co.edu.udes.activity.backend.demo.repositories.PermissionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PermissionServiceTest {

    private PermissionRepository permissionRepository;
    private PermissionService permissionService;

    @BeforeEach
    public void setUp() {
        permissionRepository = mock(PermissionRepository.class);
        permissionService = new PermissionService();
        permissionService.permissionRepository = permissionRepository;
    }

    @Test
    public void testGetAllPermissions() {
        Permission permiso = new Permission();
        permiso.setId(1L);
        permiso.setName("Crear usuario");
        permiso.setDescription("Permite crear usuarios");

        when(permissionRepository.findAll()).thenReturn(List.of(permiso));

        List<Permission> resultado = permissionService.getAllPermissions();

        assertEquals(1, resultado.size());
        assertEquals("Crear usuario", resultado.get(0).getName());
        verify(permissionRepository).findAll();
    }

    @Test
    public void testGetPermissionById() {
        Permission permiso = new Permission();
        permiso.setId(2L);
        permiso.setName("Editar perfil");

        when(permissionRepository.findById(2L)).thenReturn(Optional.of(permiso));

        Optional<Permission> resultado = permissionService.getPermissionById(2L);

        assertTrue(resultado.isPresent());
        assertEquals("Editar perfil", resultado.get().getName());
        verify(permissionRepository).findById(2L);
    }

    @Test
    public void testSavePermission() {
        Permission permiso = new Permission();
        permiso.setName("Ver reportes");

        when(permissionRepository.save(permiso)).thenReturn(permiso);

        Permission resultado = permissionService.savePermission(permiso);

        assertNotNull(resultado);
        assertEquals("Ver reportes", resultado.getName());
        verify(permissionRepository).save(permiso);
    }

    @Test
    public void testUpdatePermission() {
        Permission permisoExistente = new Permission();
        permisoExistente.setId(3L);
        permisoExistente.setName("Antiguo nombre");

        Permission permisoActualizado = new Permission();
        permisoActualizado.setName("Nuevo nombre");
        permisoActualizado.setDescription("Descripción actualizada");

        when(permissionRepository.findById(3L)).thenReturn(Optional.of(permisoExistente));
        when(permissionRepository.save(permisoExistente)).thenReturn(permisoExistente);

        Permission resultado = permissionService.updatePermission(3L, permisoActualizado);

        assertNotNull(resultado);
        assertEquals("Nuevo nombre", resultado.getName());
        assertEquals("Descripción actualizada", resultado.getDescription());
        verify(permissionRepository).save(permisoExistente);
    }

    @Test
    public void testDeletePermissionExistente() {
        when(permissionRepository.existsById(4L)).thenReturn(true);

        boolean eliminado = permissionService.deletePermission(4L);

        assertTrue(eliminado);
        verify(permissionRepository).deleteById(4L);
    }

    @Test
    public void testDeletePermissionInexistente() {
        when(permissionRepository.existsById(5L)).thenReturn(false);

        boolean eliminado = permissionService.deletePermission(5L);

        assertFalse(eliminado);
        verify(permissionRepository, never()).deleteById(anyLong());
    }
}
