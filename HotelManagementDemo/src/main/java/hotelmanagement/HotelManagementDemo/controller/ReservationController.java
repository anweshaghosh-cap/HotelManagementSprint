package hotelmanagement.HotelManagementDemo.controller;

import hotelmanagement.HotelManagementDemo.model.Reservation;
import hotelmanagement.HotelManagementDemo.payload.SuccessResponse;
import hotelmanagement.HotelManagementDemo.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.swing.text.html.parser.Entity;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;
   /* public ResponseEntity<Review> createReview(@RequestBody Map<String, Object> payload) {
        Integer reservationId = (Integer) payload.get("reservationId");
        Integer rating = (Integer) payload.get("rating");
        String comment = (String) payload.get("comment");
        LocalDate reviewDate = LocalDate.parse((String) payload.get("reviewDate"));

        Reservation reservation = reservationRepository.findById(reservationId)
            .orElseThrow(() -> new RuntimeException("Reservation not found"));

        Review review = new Review(reservation, rating, comment, reviewDate);
        return ResponseEntity.ok(reviewService.createReview(review));
    }*/


    @PostMapping("/post")
    public ResponseEntity<SuccessResponse> createReservation(@RequestBody Reservation reservation) {
        reservationService.createReservation(reservation);
        return ResponseEntity.ok(new SuccessResponse("POSTSUCCESS", "Reservation created successfully."));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Reservation>> getAllReservations() {
        return ResponseEntity.ok(reservationService.getAllReservations());
    }

    @GetMapping("/{reservationId}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable int reservationId) {
        return ResponseEntity.ok(reservationService.getReservationById(reservationId));
    }

    @PutMapping("/update/{reservationId}")
    public ResponseEntity<SuccessResponse> updateReservation(@PathVariable int reservationId,
                                                              @RequestBody Reservation reservation) {
        reservationService.updateReservation(reservationId, reservation);
        return ResponseEntity.ok(new SuccessResponse("UPDATESUCCESS", "Reservation updated successfully."));
    }
//  to get the start  date and end date of the reservation
   /* @PutMapping("/update/{reviewId}")
    public ResponseEntity<Review> updateReview(@PathVariable Integer reviewId, @RequestBody Review review) {
        Review updated = reviewService.updateReview(reviewId, review);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }*/

     // deleting a reservation id 
    @DeleteMapping("delete/{reservationId}")
    public ResponseEntity<SuccessResponse> deleteReservation(@PathVariable int reservationId) {
        reservationService.deleteReservation(reservationId);
        return ResponseEntity.ok(new SuccessResponse("DELETESUCCESS", "Reservation deleted successfully."));
    }
    @GetMapping("/date-range")
    public ResponseEntity<List<Reservation>> getReservationsInDateRange(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        return ResponseEntity.ok(reservationService.getReservationsInDateRange(startDate, endDate));
    }
    
/*
    @GetMapping("/date-range")
    public ResponseEntity<List<Reservation>> getReservationsInDateRange(
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        return ResponseEntity.ok(reservationService.getReservationsInDateRange(startDate, endDate));
    }*/
}