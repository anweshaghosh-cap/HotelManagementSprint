package hotelmanagement.HotelManagementDemo.service;

import hotelmanagement.HotelManagementDemo.exception.ResourceNotFoundException;
import hotelmanagement.HotelManagementDemo.model.Reservation;
import hotelmanagement.HotelManagementDemo.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public Reservation createReservation(Reservation reservation) {
        if (reservation.getRoom() == null || reservation.getGuestEmail() == null || reservation.getCheckInDate() == null) {
            throw new IllegalArgumentException("Reservation must include room, guest email, and check-in date.");
        }

        boolean alreadyExists = reservationRepository.existsByGuestEmailAndCheckInDateAndRoom(
                reservation.getGuestEmail(),
                reservation.getCheckInDate(),
                reservation.getRoom()
        );

        if (alreadyExists) {
            throw new ResourceNotFoundException("ADDFAILS", "Reservation already exists for the given details.");
        }

        return reservationRepository.save(reservation);
    }

    public List<Reservation> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        if (reservations.isEmpty()) {
            throw new ResourceNotFoundException("GETALLFAILS", "No reservations found.");
        }
        return reservations;
    }

    public Reservation getReservationById(int id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("GETFAILS", "Reservation with ID " + id + " not found."));
    }

    public Reservation updateReservation(int id, Reservation updatedReservation) {
        Reservation existingReservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UPDTFAILS", "Reservation with ID " + id + " not found."));

        existingReservation.setGuestName(updatedReservation.getGuestName());
        existingReservation.setGuestEmail(updatedReservation.getGuestEmail());
        existingReservation.setGuestPhone(updatedReservation.getGuestPhone());
        existingReservation.setCheckInDate(updatedReservation.getCheckInDate());
        existingReservation.setCheckOutDate(updatedReservation.getCheckOutDate());
        existingReservation.setRoom(updatedReservation.getRoom());

        return reservationRepository.save(existingReservation);
    }

    public void deleteReservation(int id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DLTFAILS", "Reservation with ID " + id + " not found."));
        reservationRepository.delete(reservation);
    }

    public List<Reservation> getReservationsInDateRange(LocalDate startDate, LocalDate endDate) {
        List<Reservation> reservations = reservationRepository.findReservationsInDateRange(startDate, endDate);
        if (reservations.isEmpty()) {
            throw new ResourceNotFoundException("GETALLFAILS", "No reservations found between the specified dates.");
        }
        return reservations;
    }
}