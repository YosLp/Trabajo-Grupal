package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.models.Space;
import co.edu.udes.activity.backend.demo.models.User;
import co.edu.udes.activity.backend.demo.repositories.ReservationRepository;
import co.edu.udes.activity.backend.demo.models.Reservation;
import co.edu.udes.activity.backend.demo.repositories.SpaceRepository;
import co.edu.udes.activity.backend.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private SpaceRepository spaceRepository;

    @Autowired
    private UserRepository userRepository;

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

    public Reservation makeReservation(Long userId, Long spaceId, LocalDate date, LocalTime startTime, LocalTime endTime) {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<Space> spaceOpt = spaceRepository.findById(spaceId);

        if (userOpt.isPresent() && spaceOpt.isPresent()) {
            Space space = spaceOpt.get();
            if (!space.isAvailable()) return null;

            Reservation reservation = new Reservation();
            reservation.setUser(userOpt.get());
            reservation.setSpace(space);
            reservation.setDate(date);
            reservation.setStarTime(startTime);
            reservation.setEndTime(endTime);
            reservation.setStatus(true);

            space.setAvailable(false);
            spaceRepository.save(space);

            return reservationRepository.save(reservation);
        }
        return null;
    }

    public boolean cancelReservation(Long reservationId) {
        Optional<Reservation> reservationOpt = reservationRepository.findById(reservationId);
        if (reservationOpt.isPresent()) {
            Reservation reservation = reservationOpt.get();
            reservation.setStatus(false);
            reservation.getSpace().setAvailable(true);
            reservationRepository.save(reservation);
            spaceRepository.save(reservation.getSpace());
            return true;
        }
        return false;
    }
    public List<Reservation> getReservationsByUser(Long userId) {
        return reservationRepository.findByUserId(userId);
    }
}