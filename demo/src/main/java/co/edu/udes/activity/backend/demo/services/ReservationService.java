package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.dto.ReservationDTO;
import co.edu.udes.activity.backend.demo.dto.ReservationRequestDTO;
import co.edu.udes.activity.backend.demo.models.Space;
import co.edu.udes.activity.backend.demo.models.User;
import co.edu.udes.activity.backend.demo.repositories.ReservationRepository;
import co.edu.udes.activity.backend.demo.models.Reservation;
import co.edu.udes.activity.backend.demo.repositories.SpaceRepository;
import co.edu.udes.activity.backend.demo.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private SpaceRepository spaceRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<ReservationDTO> getAllReservations() {
        return reservationRepository.findAll()
                .stream()
                .map(reservation -> modelMapper.map(reservation, ReservationDTO.class))
                .collect(Collectors.toList());
    }

    public Optional<ReservationDTO> getReservationById(Long id) {
        return reservationRepository.findById(id)
                .map(reservation -> modelMapper.map(reservation, ReservationDTO.class));
    }

    public ReservationDTO saveReservation(ReservationRequestDTO dto) {
        Reservation reservation = new Reservation();
        reservation.setStarTime(dto.getStarTime());
        reservation.setEndTime(dto.getEndTime());
        reservation.setDate(dto.getDate());
        reservation.setStatus(true);
        reservation.setSpace(dto.getSpace());

        userRepository.findById(dto.getUserid()).ifPresent(reservation::setUser);

        return modelMapper.map(reservationRepository.save(reservation), ReservationDTO.class);
    }

    public ReservationDTO updateReservation(Long id, ReservationRequestDTO dto) {
        return reservationRepository.findById(id).map(reservation -> {
            reservation.setStarTime(dto.getStarTime());
            reservation.setEndTime(dto.getEndTime());
            reservation.setDate(dto.getDate());
            reservation.setSpace(dto.getSpace());
            reservation.setStatus(true);
            userRepository.findById(dto.getUserid()).ifPresent(reservation::setUser);

            return modelMapper.map(reservationRepository.save(reservation), ReservationDTO.class);
        }).orElse(null);
    }

    public boolean deleteReservation(Long id) {
        if (reservationRepository.existsById(id)) {
            reservationRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public ReservationDTO makeReservation(Long userId, Long spaceId, LocalDate date, LocalTime startTime, LocalTime endTime) {
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

            return modelMapper.map(reservationRepository.save(reservation), ReservationDTO.class);
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

    public List<ReservationDTO> getReservationsByUser(Long userId) {
        return reservationRepository.findByUserId(userId)
                .stream()
                .map(reservation -> modelMapper.map(reservation, ReservationDTO.class))
                .collect(Collectors.toList());
    }
}
