

package hotelmanagement.HotelManagementDemo;

import hotelmanagement.HotelManagementDemo.exception.ResourceNotFoundException;
import hotelmanagement.HotelManagementDemo.model.Payment;
import hotelmanagement.HotelManagementDemo.repository.PaymentRepository;
import hotelmanagement.HotelManagementDemo.service.PaymentService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PaymentServiceTest {

    @InjectMocks
    private PaymentService paymentService;

    @Mock
    private PaymentRepository paymentRepository;

    private Payment samplePayment;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        samplePayment = new Payment();
        //samplePayment.setId(1);
        samplePayment.setAmount(1000.0);
        samplePayment.setPaymentStatus("Completed");
    }

    @Test
    void testCreatePayment() {
        when(paymentRepository.save(samplePayment)).thenReturn(samplePayment);
        Payment result = paymentService.createPayment(samplePayment);
        assertEquals(samplePayment, result);
    }

    @Test
    void testGetAllPayments_Success() {
        List<Payment> payments = List.of(samplePayment);
        when(paymentRepository.findAll()).thenReturn(payments);
        List<Payment> result = paymentService.getAllPayments();
        assertEquals(1, result.size());
    }

    @Test
    void testGetAllPayments_EmptyList() {
        when(paymentRepository.findAll()).thenReturn(Collections.emptyList());
        assertThrows(ResourceNotFoundException.class, () -> paymentService.getAllPayments());
    }

    @Test
    void testGetPaymentById_Success() {
        when(paymentRepository.findById(1)).thenReturn(Optional.of(samplePayment));
        Payment result = paymentService.getPaymentById(1);
        assertEquals(samplePayment, result);
    }

    @Test
    void testGetPaymentById_NotFound() {
        when(paymentRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> paymentService.getPaymentById(1));
    }

    @Test
    void testDeletePayment() {
        when(paymentRepository.findById(1)).thenReturn(Optional.of(samplePayment));
        doNothing().when(paymentRepository).delete(samplePayment);
        assertDoesNotThrow(() -> paymentService.deletePayment(1));
        verify(paymentRepository, times(1)).delete(samplePayment);
    }

    @Test
    void testGetPaymentsByStatus_Success() {
        List<Payment> payments = List.of(samplePayment);
        when(paymentRepository.findByPaymentStatus("Completed")).thenReturn(payments);
        List<Payment> result = paymentService.getPaymentsByStatus("Completed");
        assertEquals(1, result.size());
    }

    @Test
    void testGetPaymentsByStatus_Empty() {
        when(paymentRepository.findByPaymentStatus("Pending")).thenReturn(Collections.emptyList());
        assertThrows(ResourceNotFoundException.class, () -> paymentService.getPaymentsByStatus("Pending"));
    }

    @Test
    void testGetTotalRevenue_Success() {
        when(paymentRepository.getTotalRevenue()).thenReturn(5000.0);
        Double result = paymentService.getTotalRevenue();
        assertEquals(5000.0, result);
    }

    @Test
    void testGetTotalRevenue_ZeroOrNull() {
        when(paymentRepository.getTotalRevenue()).thenReturn(0.0);
        assertThrows(ResourceNotFoundException.class, () -> paymentService.getTotalRevenue());

        when(paymentRepository.getTotalRevenue()).thenReturn(null);
        assertThrows(ResourceNotFoundException.class, () -> paymentService.getTotalRevenue());
    }
}