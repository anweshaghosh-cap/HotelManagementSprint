package hotelmanagement.HotelManagementDemo.service;

import hotelmanagement.HotelManagementDemo.exception.ResourceNotFoundException;
import hotelmanagement.HotelManagementDemo.model.Payment;
import hotelmanagement.HotelManagementDemo.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment createPayment(Payment payment) {
        //validatePaymentDetails(payment);
        return paymentRepository.save(payment);
    }

    public List<Payment> getAllPayments() {
        List<Payment> payments = paymentRepository.findAll();
        if (payments.isEmpty()) {
            throw new ResourceNotFoundException("GETALLFAILS", "No payment records found.");
        }
        return payments;
    }

    public Payment getPaymentById(int id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("GETFAILS", "Payment with ID " + id + " not found."));
    }

    public void deletePayment(int id) {
        Payment payment = getPaymentById(id); // reuse logic
        paymentRepository.delete(payment);
    }

    public List<Payment> getPaymentsByStatus(String status) {
        List<Payment> payments = paymentRepository.findByPaymentStatus(status);
        if (payments.isEmpty()) {
            throw new ResourceNotFoundException("GETALLFAILS", "No payments found with status: " + status);
        }
        return payments;
    }

    public Double getTotalRevenue() {
        Double revenue = paymentRepository.getTotalRevenue();
        if (revenue == null || revenue <= 0) {
            throw new ResourceNotFoundException("GETFAILS", "Revenue data is unavailable or zero.");
        }
        return revenue;
    }

    // 🔍 Optional enhancement: validate payment before saving
   
    }
