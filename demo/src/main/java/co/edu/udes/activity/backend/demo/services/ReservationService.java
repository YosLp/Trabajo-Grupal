package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.repositories.ReservationRepository;
import co.edu.udes.activity.backend.demo.models.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Optional<Reservation> getReservationById(Long id) {
        return reservationRepository.findById(id);
    }

    public Reservation saveReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public Reservation updateReservation(Long id, Reservation updatedReservation) {
        return reservationRepository.findById(id).map(reservation -> {
            reservation.setStarTime(updatedReservation.getStarTime());
            reservation.setEndTime(updatedReservation.getEndTime());
            reservation.setDate(updatedReservation.getDate());
            reservation.setStatus(updatedReservation.isStatus());
            reservation.setUser(updatedReservation.getUser());
            reservation.setSpace(updatedReservation.getSpace());
            return reservationRepository.save(reservation);
        }).orElse(null);
    }

    public boolean deleteReservation(Long id) {
        if (reservationRepository.existsById(id)) {
            reservationRepository.deleteById(id);
            return true;
        }
        return false;
    }
}