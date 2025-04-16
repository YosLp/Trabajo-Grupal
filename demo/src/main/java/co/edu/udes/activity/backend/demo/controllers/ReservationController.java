package co.edu.udes.activity.backend.demo.controllers;

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
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/{id}")
    public Optional<Reservation> getReservationById(@PathVariable Long id) {
        return reservationService.getReservationById(id);
    }

    @PostMapping
    public Reservation createReservation(@RequestBody Reservation reservation) {
        return reservationService.saveReservation(reservation);
    }

    @PutMapping("/{id}")
    public Reservation updateReservation(@PathVariable Long id, @RequestBody Reservation updatedReservation) {
        return reservationService.updateReservation(id, updatedReservation);
    }

    @DeleteMapping("/{id}")
    public String deleteReservation(@PathVariable Long id) {
        boolean deleted = reservationService.deleteReservation(id);
        return deleted ? "Reservación eliminada correctamente" : "No se encontró la reservación con ID: " + id;
    }

    @PostMapping("/make")
    public ResponseEntity<Reservation> makeReservation(@RequestParam Long userId,
                                                       @RequestParam Long spaceId,
                                                       @RequestParam String date,
                                                       @RequestParam String startTime,
                                                       @RequestParam String endTime) {
        LocalDate localDate = LocalDate.parse(date);
        LocalTime localStart = LocalTime.parse(startTime);
        LocalTime localEnd = LocalTime.parse(endTime);

        Reservation reservation = reservationService.makeReservation(userId, spaceId, localDate, localStart, localEnd);
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
    public ResponseEntity<List<Reservation>> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(reservationService.getReservationsByUser(userId));
    }



}