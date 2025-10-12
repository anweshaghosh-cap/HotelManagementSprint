package hotelmanagement.HotelManagementDemo;

import hotelmanagement.HotelManagementDemo.exception.ResourceNotFoundException;
import hotelmanagement.HotelManagementDemo.model.RoomType;
import hotelmanagement.HotelManagementDemo.repository.RoomTypeRepository;
import hotelmanagement.HotelManagementDemo.service.RoomTypeService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RoomTypeServiceTest {

    @InjectMocks
    private RoomTypeService roomTypeService;

    @Mock
    private RoomTypeRepository roomTypeRepository;

    private RoomType sampleRoomType;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sampleRoomType = new RoomType();
        sampleRoomType.setRoomTypeId(1);
        sampleRoomType.setTypeName("Deluxe");
        sampleRoomType.setDescription("Spacious room");
        sampleRoomType.setMaxOccupancy(2);
        sampleRoomType.setPricePerNight(3500.0);
    }

    @Test
    void testCreateRoomType_Success() {
        when(roomTypeRepository.existsByTypeName("Deluxe")).thenReturn(false);
        when(roomTypeRepository.save(sampleRoomType)).thenReturn(sampleRoomType);

        RoomType result = roomTypeService.createRoomType(sampleRoomType);
        assertEquals("Deluxe", result.getTypeName());
    }

    @Test
    void testCreateRoomType_AlreadyExists() {
        when(roomTypeRepository.existsByTypeName("Deluxe")).thenReturn(true);
        assertThrows(ResourceNotFoundException.class, () -> roomTypeService.createRoomType(sampleRoomType));
    }

    @Test
    void testGetRoomTypeById_Success() {
        when(roomTypeRepository.findById(1)).thenReturn(Optional.of(sampleRoomType));
        RoomType result = roomTypeService.getRoomTypeById(1);
        assertEquals(2, result.getMaxOccupancy());
    }

    @Test
    void testUpdateRoomType_Success() {
        RoomType updatedType = new RoomType();
        updatedType.setTypeName("Suite");
        updatedType.setDescription("Luxury suite");
        updatedType.setMaxOccupancy(4);
        updatedType.setPricePerNight(5000.0);

        when(roomTypeRepository.findById(1)).thenReturn(Optional.of(sampleRoomType));
        when(roomTypeRepository.save(any(RoomType.class))).thenReturn(updatedType);

        RoomType result = roomTypeService.updateRoomType(1, updatedType);
        assertEquals("Suite", result.getTypeName());
        assertEquals(4, result.getMaxOccupancy());
    }

    @Test
    void testDeleteRoomType_Success() {
        when(roomTypeRepository.findById(1)).thenReturn(Optional.of(sampleRoomType));
        doNothing().when(roomTypeRepository).delete(sampleRoomType);

        assertDoesNotThrow(() -> roomTypeService.deleteRoomType(1));
        verify(roomTypeRepository, times(1)).delete(sampleRoomType);
    }
}