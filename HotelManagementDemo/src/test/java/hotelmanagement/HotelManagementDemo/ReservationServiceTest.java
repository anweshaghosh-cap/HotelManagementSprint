package hotelmanagement.HotelManagementDemo;

import hotelmanagement.HotelManagementDemo.controller.ReservationController;
import hotelmanagement.HotelManagementDemo.model.Reservation;
import hotelmanagement.HotelManagementDemo.payload.SuccessResponse;
import hotelmanagement.HotelManagementDemo.service.ReservationService;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReservationControllerTest {

    @Mock
    private ReservationService reservationService;

    @InjectMocks
    private ReservationController reservationController;

    private Reservation sampleReservation;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sampleReservation = new Reservation();
        sampleReservation.setReservationId(1);
        sampleReservation.setGuestName("John Doe");
        sampleReservation.setGuestEmail("john@example.com");
        sampleReservation.setCheckInDate(Date.valueOf("2024-01-01"));
        sampleReservation.setCheckOutDate(Date.valueOf("2024-01-05"));
    }

    @Test
    void shouldCreateReservationSuccessfully() {
        ResponseEntity<SuccessResponse> response = reservationController.createReservation(sampleReservation);
        assertEquals("POSTSUCCESS", response.getBody().getCode());
        assertEquals("Reservation created successfully.", response.getBody().getMessage());
    }

    @Test
    void shouldReturnAllReservations() {
        when(reservationService.getAllReservations()).thenReturn(List.of(sampleReservation));
        ResponseEntity<List<Reservation>> response = reservationController.getAllReservations();
        assertEquals(1, response.getBody().size());
    }

    @Test
    void shouldReturnReservationById() {
        when(reservationService.getReservationById(1)).thenReturn(sampleReservation);
        ResponseEntity<Reservation> response = reservationController.getReservationById(1);
        assertEquals("John Doe", response.getBody().getGuestName());
    }

    @Test
    void shouldDeleteReservationSuccessfully() {
        doNothing().when(reservationService).deleteReservation(1);
        ResponseEntity<SuccessResponse> response = reservationController.deleteReservation(1);
        assertEquals("DELETESUCCESS", response.getBody().getCode());
    }


    
}