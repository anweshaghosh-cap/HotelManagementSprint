package hotelmanagement.HotelManagementDemo.controller;

import hotelmanagement.HotelManagementDemo.model.Payment;
import hotelmanagement.HotelManagementDemo.payload.SuccessResponse;
import hotelmanagement.HotelManagementDemo.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/post")
    public ResponseEntity<SuccessResponse> registerPayment(@RequestBody Payment paymentDetails) {
        paymentService.createPayment(paymentDetails);
        return ResponseEntity.ok(new SuccessResponse("POSTSUCCESS", "Payment successfully recorded."));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Payment>> fetchAllPayments() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }
    // to get all the list of payments that have been done 
// to get the payment with specific id 
    @GetMapping("/{paymentId}")
    public ResponseEntity<Payment> fetchPaymentById(@PathVariable int paymentId) {
        return ResponseEntity.ok(paymentService.getPaymentById(paymentId));
    }
    /*Optional<Payment> optionalPayment = paymentRepository.findById(id);
    if (optionalPayment.isPresent()) {
        Payment payment = optionalPayment.get();
        payment.setAmount(paymentDetails.getAmount());
        payment.setPaymentDate(paymentDetails.getPaymentDate());
        payment.setPaymentStatus(paymentDetails.getPaymentStatus());
        payment.setReservation(paymentDetails.getReservation());
        return paymentRepository.save(payment);
    }*/


    @DeleteMapping("/{paymentId}")
    public ResponseEntity<SuccessResponse> removePayment(@PathVariable int paymentId) {
        paymentService.deletePayment(paymentId);
        return ResponseEntity.ok(new SuccessResponse("DELETESUCCESS", "Payment successfully removed."));
    }
    // whether the payment has been paid or is it due

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Payment>> fetchPaymentsByStatus(@PathVariable String status) {
        return ResponseEntity.ok(paymentService.getPaymentsByStatus(status));
    }
    // total revenue that is generated 

    @GetMapping("/total-revenue")
    public ResponseEntity<Double> calculateTotalRevenue() {
        return ResponseEntity.ok(paymentService.getTotalRevenue());
    }
}