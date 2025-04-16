package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.models.Reservation;
import co.edu.udes.activity.backend.demo.models.Space;
import co.edu.udes.activity.backend.demo.models.User;
import co.edu.udes.activity.backend.demo.repositories.ReservationRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationService reservationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllReservations() {
        Reservation reserva1 = new Reservation();
        reserva1.setId(1L);
        reserva1.setStatus(true);

        Reservation reserva2 = new Reservation();
        reserva2.setId(2L);
        reserva2.setStatus(false);

        List<Reservation> reservas = Arrays.asList(reserva1, reserva2);
        when(reservationRepository.findAll()).thenReturn(reservas);

        List<Reservation> result = reservationService.getAllReservations();

        assertEquals(2, result.size());
        verify(reservationRepository, times(1)).findAll();
    }

    @Test
    public void testGetReservationById() {
        Reservation reserva = new Reservation();
        reserva.setId(1L);

        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reserva));

        Optional<Reservation> result = reservationService.getReservationById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        verify(reservationRepository, times(1)).findById(1L);
    }

    @Test
    public void testSaveReservation() {
        Reservation reserva = new Reservation();
        reserva.setId(1L);
        reserva.setStatus(true);

        when(reservationRepository.save(reserva)).thenReturn(reserva);

        Reservation result = reservationService.saveReservation(reserva);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(reservationRepository, times(1)).save(reserva);
    }

    @Test
    public void testUpdateReservation() {
        Reservation reservaExistente = new Reservation();
        reservaExistente.setId(1L);
        reservaExistente.setStatus(false);

        Reservation reservaActualizada = new Reservation();
        reservaActualizada.setStarTime(LocalTime.of(9, 0));
        reservaActualizada.setEndTime(LocalTime.of(11, 0));
        reservaActualizada.setDate(LocalDate.of(2025, 4, 10));
        reservaActualizada.setStatus(true);

        User usuario = new User();
        usuario.setId(1L);
        usuario.setFirstName("Camilo");
        reservaActualizada.setUser(usuario);

        Space espacio = new Space();
        espacio.setId(1L);
        espacio.setName("Aula 101");
        reservaActualizada.setSpace(espacio);

        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservaExistente));
        when(reservationRepository.save(any(Reservation.class))).thenAnswer(i -> i.getArgument(0));

        Reservation result = reservationService.updateReservation(1L, reservaActualizada);

        assertNotNull(result);
        assertEquals(LocalTime.of(9, 0), result.getStarTime());
        assertEquals("Camilo", result.getUser().getFirstName());
        verify(reservationRepository, times(1)).save(reservaExistente);
    }

    @Test
    public void testDeleteReservation() {
        when(reservationRepository.existsById(1L)).thenReturn(true);

        boolean result = reservationService.deleteReservation(1L);

        assertTrue(result);
        verify(reservationRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteReservation_NotFound() {
        when(reservationRepository.existsById(99L)).thenReturn(false);

        boolean result = reservationService.deleteReservation(99L);

        assertFalse(result);
        verify(reservationRepository, never()).deleteById(anyLong());
    }
}
