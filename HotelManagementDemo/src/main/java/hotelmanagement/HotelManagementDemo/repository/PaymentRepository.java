package hotelmanagement.HotelManagementDemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hotelmanagement.HotelManagementDemo.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    List<Payment> findByPaymentStatus(String status);

    @Query("SELECT SUM(p.amount) FROM Payment p")
    Double getTotalRevenue();
}