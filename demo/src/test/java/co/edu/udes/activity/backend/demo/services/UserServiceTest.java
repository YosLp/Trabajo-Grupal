package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.models.User;
import co.edu.udes.activity.backend.demo.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User usuario1;
    private User usuario2;

    @BeforeEach
    void setUp() {
        usuario1 = new User();
        usuario1.setId(1L);
        usuario1.setFirstName("Juan");
        usuario1.setLastName("Pérez");
        usuario1.setEmail("juan.perez@ejemplo.com");
        usuario1.setPassword("clave123");
        usuario1.setStatus(true);

        usuario2 = new User();
        usuario2.setId(2L);
        usuario2.setFirstName("Diana");
        usuario2.setLastName("Gómez");
        usuario2.setEmail("diana.gomez@ejemplo.com");
        usuario2.setPassword("clave456");
        usuario2.setStatus(false);
    }

    @Test
    void testObtenerTodosLosUsuarios() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(usuario1, usuario2));

        List<User> usuarios = userService.getAllUsers();

        assertEquals(2, usuarios.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testObtenerUsuarioPorIdExistente() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(usuario1));

        Optional<User> resultado = userService.getUserById(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Juan", resultado.get().getFirstName());
    }

    @Test
    void testObtenerUsuarioPorIdInexistente() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<User> resultado = userService.getUserById(99L);

        assertFalse(resultado.isPresent());
    }

    @Test
    void testGuardarUsuario() {
        when(userRepository.save(usuario1)).thenReturn(usuario1);

        User guardado = userService.saveUser(usuario1);

        assertNotNull(guardado);
        assertEquals("juan.perez@ejemplo.com", guardado.getEmail());
        verify(userRepository).save(usuario1);
    }

    @Test
    void testActualizarUserExistente() {

        usuario1 = new User();
        usuario1.setId(1L);
        usuario1.setFirstName("Juan");
        usuario1.setLastName("Pérez");
        usuario1.setEmail("juan.perez@ejemplo.com");
        usuario1.setPassword("clave123");
        usuario1.setStatus(true);


        when(userRepository.findById(1L)).thenReturn(Optional.of(usuario1));

        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));


        User datosActualizados = new User();
        datosActualizados.setFirstName("Juan Camilo");
        datosActualizados.setLastName("Pérez López");
        datosActualizados.setEmail("juan.camilo@udea.edu.co");
        datosActualizados.setPassword("nuevaClave");
        datosActualizados.setStatus(false);


        User resultado = userService.updateUser(1L, datosActualizados);


        assertNotNull(resultado);
        assertEquals("Juan Camilo", resultado.getFirstName());
        assertEquals("juan.camilo@udea.edu.co", resultado.getEmail());
        assertFalse(resultado.getStatus());
        verify(userRepository).save(usuario1);
    }

    @Test
    void testActualizarUserInexistente() {

        when(userRepository.findById(99L)).thenReturn(Optional.empty());


        User datosActualizados = new User();
        datosActualizados.setFirstName("Camila");
        datosActualizados.setLastName("Rodríguez");
        datosActualizados.setEmail("camila.rodriguez@unal.edu.co");
        datosActualizados.setPassword("clave789");
        datosActualizados.setStatus(true);


        User resultado = userService.updateUser(99L, datosActualizados);


        assertNull(resultado);
    }

    @Test
    void testEliminarUsuarioExistente() {
        when(userRepository.existsById(1L)).thenReturn(true);

        boolean resultado = userService.deleteUser(1L);

        assertTrue(resultado);
        verify(userRepository).deleteById(1L);
    }

    @Test
    void testEliminarUsuarioInexistente() {
        when(userRepository.existsById(99L)).thenReturn(false);

        boolean resultado = userService.deleteUser(99L);

        assertFalse(resultado);
        verify(userRepository, never()).deleteById(any());
    }
}