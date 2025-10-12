package hotelmanagement.HotelManagementDemo;

import hotelmanagement.HotelManagementDemo.exception.ResourceNotFoundException;
import hotelmanagement.HotelManagementDemo.model.Amenity;
import hotelmanagement.HotelManagementDemo.model.Hotel;
import hotelmanagement.HotelManagementDemo.repository.HotelRepository;
import hotelmanagement.HotelManagementDemo.service.HotelService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HotelServiceTest {

    @InjectMocks
    private HotelService hotelService;

    @Mock
    private HotelRepository hotelRepository;

    private Hotel sampleHotel;
    private Amenity sampleAmenity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        sampleAmenity = new Amenity();
        sampleAmenity.setAmenityId(1);
        sampleAmenity.setName("WiFi");

        sampleHotel = new Hotel();
        sampleHotel.setHotelId(1);
        sampleHotel.setName("Grand Palace");
        sampleHotel.setLocation("Chennai");
        sampleHotel.setDescription("Luxury stay");
        sampleHotel.setAmenities(Set.of(sampleAmenity));
    }

   @Test
    void testCreateHotel_Success() {
        when(hotelRepository.existsByName("Grand Palace")).thenReturn(false);
        when(hotelRepository.save(sampleHotel)).thenReturn(sampleHotel);

        Hotel result = hotelService.createHotel(sampleHotel);
        assertEquals("Grand Palace", result.getName());
    }

    @Test
    void testCreateHotel_AlreadyExists() {
        when(hotelRepository.existsByName("Grand Palace")).thenReturn(true);
        assertThrows(ResourceNotFoundException.class, () -> hotelService.createHotel(sampleHotel));
    }

    @Test
    void testGetHotelById_Success() {
        when(hotelRepository.findById(1)).thenReturn(Optional.of(sampleHotel));
        Hotel result = hotelService.getHotelById(1);
        assertEquals("Chennai", result.getLocation());
    }

    @Test
    void testGetHotelById_NotFound() {
        when(hotelRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> hotelService.getHotelById(1));
    }

    @Test
    void testUpdateHotel_Success() {
        Hotel updatedHotel = new Hotel();
        updatedHotel.setName("Ocean View");
        updatedHotel.setLocation("Goa");
        updatedHotel.setDescription("Beachside resort");

        when(hotelRepository.findById(1)).thenReturn(Optional.of(sampleHotel));
        when(hotelRepository.save(any(Hotel.class))).thenReturn(updatedHotel);

        Hotel result = hotelService.updateHotel(1, updatedHotel);
        assertEquals("Ocean View", result.getName());
        assertEquals("Goa", result.getLocation());
    }

    @Test
    void testDeleteHotel_Success() {
        when(hotelRepository.findById(1)).thenReturn(Optional.of(sampleHotel));
        doNothing().when(hotelRepository).delete(sampleHotel);

        assertDoesNotThrow(() -> hotelService.deleteHotel(1));
        verify(hotelRepository, times(1)).delete(sampleHotel);
    }

    @Test
    void testGetAmenitiesByHotelId_Success() {
        when(hotelRepository.findById(1)).thenReturn(Optional.of(sampleHotel));
        List<Amenity> result = hotelService.getAmenitiesByHotelId(1);
        assertEquals(1, result.size());
        assertEquals("WiFi", result.get(0).getName());
    }
}