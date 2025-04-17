package co.edu.udes.activity.backend.demo.controllers;

import co.edu.udes.activity.backend.demo.dto.ReservationDTO;
import co.edu.udes.activity.backend.demo.dto.ReservationRequestDTO;
import co.edu.udes.activity.backend.demo.services.ReservationService;
import co.edu.udes.activity.backend.demo.models.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/all")
    public List<ReservationDTO> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDTO> getReservationById(@PathVariable Long id) {
        return reservationService.getReservationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ReservationDTO createReservation(@RequestBody ReservationRequestDTO dto) {
        return reservationService.saveReservation(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationDTO> updateReservation(@PathVariable Long id, @RequestBody ReservationRequestDTO dto) {
        ReservationDTO updated = reservationService.updateReservation(id, dto);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReservation(@PathVariable Long id) {
        boolean deleted = reservationService.deleteReservation(id);
        return deleted ? ResponseEntity.ok("Reservación eliminada correctamente") :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró la reservación con ID: " + id);
    }

    @PostMapping("/make")
    public ResponseEntity<ReservationDTO> makeReservation(@RequestParam Long userId,
                                                          @RequestParam Long spaceId,
                                                          @RequestParam String date,
                                                          @RequestParam String startTime,
                                                          @RequestParam String endTime) {
        LocalDate localDate = LocalDate.parse(date);
        LocalTime localStart = LocalTime.parse(startTime);
        LocalTime localEnd = LocalTime.parse(endTime);

        ReservationDTO reservation = reservationService.makeReservation(userId, spaceId, localDate, localStart, localEnd);
        return reservation != null ?
                ResponseEntity.ok(reservation) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/cancel/{id}")
    public ResponseEntity<String> cancelReservation(@PathVariable Long id) {
        boolean cancelled = reservationService.cancelReservation(id);
        return cancelled ?
                ResponseEntity.ok("Reservación cancelada") :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reservación no encontrada");
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReservationDTO>> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(reservationService.getReservationsByUser(userId));
    }
}
