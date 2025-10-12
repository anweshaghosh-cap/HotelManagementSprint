package hotelmanagement.HotelManagementDemo;

import hotelmanagement.HotelManagementDemo.exception.ResourceNotFoundException;
import hotelmanagement.HotelManagementDemo.model.Amenity;
import hotelmanagement.HotelManagementDemo.repository.AmenityRepository;
import hotelmanagement.HotelManagementDemo.service.AmenityService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AmenityServiceTest {

    @InjectMocks
    private AmenityService amenityService;

    @Mock
    private AmenityRepository amenityRepository;

    private Amenity sampleAmenity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sampleAmenity = new Amenity();
       // sampleAmenity.setId(1);
        sampleAmenity.setName("WiFi");
        sampleAmenity.setDescription("High-speed internet");
    }

    @Test
    void testCreateAmenity_Success() {
        when(amenityRepository.existsByName("WiFi")).thenReturn(false);
        when(amenityRepository.save(sampleAmenity)).thenReturn(sampleAmenity);

        Amenity result = amenityService.createAmenity(sampleAmenity);
        assertEquals("WiFi", result.getName());
    }

    @Test
    void testCreateAmenity_AlreadyExists() {
        when(amenityRepository.existsByName("WiFi")).thenReturn(true);

        assertThrows(ResourceNotFoundException.class, () -> amenityService.createAmenity(sampleAmenity));
    }

    @Test
    void testGetAllAmenities_Success() {
        when(amenityRepository.findAll()).thenReturn(List.of(sampleAmenity));

        List<Amenity> result = amenityService.getAllAmenities();
        assertEquals(1, result.size());
    }

    @Test
    void testGetAllAmenities_Empty() {
        when(amenityRepository.findAll()).thenReturn(Collections.emptyList());

        assertThrows(ResourceNotFoundException.class, () -> amenityService.getAllAmenities());
    }

    @Test
    void testGetAmenityById_Success() {
        when(amenityRepository.findById(1)).thenReturn(Optional.of(sampleAmenity));

        Amenity result = amenityService.getAmenityById(1);
        assertEquals("WiFi", result.getName());
    }

    @Test
    void testGetAmenityById_NotFound() {
        when(amenityRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> amenityService.getAmenityById(1));
    }

    @Test
    void testUpdateAmenity_Success() {
        Amenity updatedAmenity = new Amenity();
        updatedAmenity.setName("Pool");
        updatedAmenity.setDescription("Outdoor swimming pool");

        when(amenityRepository.findById(1)).thenReturn(Optional.of(sampleAmenity));
        when(amenityRepository.save(any(Amenity.class))).thenReturn(updatedAmenity);

        Amenity result = amenityService.updateAmenity(1, updatedAmenity);
        assertEquals("Pool", result.getName());
    }

    @Test
    void testDeleteAmenity_Success() {
        when(amenityRepository.findById(1)).thenReturn(Optional.of(sampleAmenity));
        doNothing().when(amenityRepository).delete(sampleAmenity);

        assertDoesNotThrow(() -> amenityService.deleteAmenity(1));
        verify(amenityRepository, times(1)).delete(sampleAmenity);
    }
}