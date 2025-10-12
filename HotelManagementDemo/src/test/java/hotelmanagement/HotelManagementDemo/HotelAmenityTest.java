package hotelmanagement.HotelManagementDemo;

import hotelmanagement.HotelManagementDemo.exception.ResourceNotFoundException;
import hotelmanagement.HotelManagementDemo.model.Amenity;
import hotelmanagement.HotelManagementDemo.model.Hotel;
import hotelmanagement.HotelManagementDemo.model.HotelAmenity;
import hotelmanagement.HotelManagementDemo.repository.AmenityRepository;
import hotelmanagement.HotelManagementDemo.repository.HotelAmenityRepository;
import hotelmanagement.HotelManagementDemo.repository.HotelRepository;
import hotelmanagement.HotelManagementDemo.service.HotelAmenityService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HotelAmenityServiceTest {

    @InjectMocks
    private HotelAmenityService hotelAmenityService;

    @Mock
    private HotelRepository hotelRepository;

    @Mock
    private AmenityRepository amenityRepository;

    @Mock
    private HotelAmenityRepository hotelAmenityRepository;

    private Hotel sampleHotel;
    private Amenity sampleAmenity;
    private HotelAmenity sampleHotelAmenity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        sampleHotel = new Hotel();
        sampleHotel.setHotelId(1);
        sampleHotel.setName("Grand Palace");

        sampleAmenity = new Amenity();
        sampleAmenity.setAmenityId(1);
        sampleAmenity.setName("WiFi");

        sampleHotelAmenity = new HotelAmenity();
        sampleHotelAmenity.setHotel(sampleHotel);
        sampleHotelAmenity.setAmenity(sampleAmenity);
        sampleHotelAmenity.setActive(true);
        sampleHotelAmenity.setAddedDate(new Date());
    }

    @Test
    void testGetAllHotelAmenities() {
        when(hotelAmenityRepository.findAll()).thenReturn(List.of(sampleHotelAmenity));
        List<HotelAmenity> result = hotelAmenityService.getAllHotelAmenities();
        assertEquals(1, result.size());
    }

    @Test
    void testAddHotelAmenity_Success() {
        when(hotelRepository.findById(1)).thenReturn(Optional.of(sampleHotel));
        when(amenityRepository.findById(1)).thenReturn(Optional.of(sampleAmenity));
        when(hotelAmenityRepository.existsByHotelAndAmenity(sampleHotel, sampleAmenity)).thenReturn(false);

        assertDoesNotThrow(() -> hotelAmenityService.addHotelAmenity(1, 1));
        verify(hotelAmenityRepository, times(1)).save(any(HotelAmenity.class));
    }

    @Test
    void testAddHotelAmenity_HotelNotFound() {
        when(hotelRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> hotelAmenityService.addHotelAmenity(1, 1));
    }

    @Test
    void testAddHotelAmenity_AmenityNotFound() {
        when(hotelRepository.findById(1)).thenReturn(Optional.of(sampleHotel));
        when(amenityRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> hotelAmenityService.addHotelAmenity(1, 1));
    }

    @Test
    void testAddHotelAmenity_AlreadyExists() {
        when(hotelRepository.findById(1)).thenReturn(Optional.of(sampleHotel));
        when(amenityRepository.findById(1)).thenReturn(Optional.of(sampleAmenity));
        when(hotelAmenityRepository.existsByHotelAndAmenity(sampleHotel, sampleAmenity)).thenReturn(true);

        assertThrows(ResourceNotFoundException.class, () -> hotelAmenityService.addHotelAmenity(1, 1));
    }
}