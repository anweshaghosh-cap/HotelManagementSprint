package hotelmanagement.HotelManagementDemo.repository;

import hotelmanagement.HotelManagementDemo.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    @Query("SELECT r FROM Reservation r WHERE r.checkInDate >= :startDate AND r.checkOutDate <= :endDate")
    List<Reservation> findReservationsInDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    boolean existsByGuestEmailAndCheckInDateAndRoom(String guestEmail, Date checkInDate, hotelmanagement.HotelManagementDemo.model.Room room);
}