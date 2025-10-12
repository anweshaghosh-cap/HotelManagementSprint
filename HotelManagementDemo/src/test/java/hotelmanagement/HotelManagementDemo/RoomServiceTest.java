
package hotelmanagement.HotelManagementDemo;

import hotelmanagement.HotelManagementDemo.exception.ResourceNotFoundException;
import hotelmanagement.HotelManagementDemo.model.Room;
import hotelmanagement.HotelManagementDemo.model.RoomType;
import hotelmanagement.HotelManagementDemo.repository.RoomRepository;
import hotelmanagement.HotelManagementDemo.service.RoomService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RoomServiceTest {

    @InjectMocks
    private RoomService roomService;

    @Mock
    private RoomRepository roomRepository;

    private Room sampleRoom;
    private RoomType sampleRoomType;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        sampleRoomType = new RoomType();
        sampleRoomType.setRoomTypeId(1);
        //sampleRoomType.setTypeName("Deluxe");

        sampleRoom = new Room();
        sampleRoom.setRoomId(1);
        sampleRoom.setRoomNumber(101);
        sampleRoom.setAvailable(true);
        sampleRoom.setRoomType(sampleRoomType);
    }

    @Test
    void testSaveRoom() {
        when(roomRepository.save(sampleRoom)).thenReturn(sampleRoom);
        Room result = roomService.saveRoom(sampleRoom);
        assertEquals("101", result.getRoomNumber());
        assertEquals("Deluxe", result.getRoomType().getTypeName());
    }

    @Test
    void testFetchAllRooms_Success() {
        when(roomRepository.findAll()).thenReturn(List.of(sampleRoom));
        List<Room> result = roomService.fetchAllRooms();
        assertEquals(1, result.size());
    }

    @Test
    void testFetchAllRooms_Empty() {
        when(roomRepository.findAll()).thenReturn(Collections.emptyList());
        assertThrows(ResourceNotFoundException.class, () -> roomService.fetchAllRooms());
    }

    @Test
    void testFindRoomById_Success() {
        when(roomRepository.findById(1)).thenReturn(Optional.of(sampleRoom));
        Room result = roomService.findRoomById(1);
        assertEquals(101, result.getRoomNumber());
    }

    @Test
    void testFindRoomById_NotFound() {
        when(roomRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> roomService.findRoomById(1));
    }

    @Test
    void testModifyRoom_Success() {
        RoomType updatedRoomType = new RoomType();
        updatedRoomType.setRoomTypeId(2);
        //updatedRoomType.setTypeName("Suite");

        Room updatedRoom = new Room();
        updatedRoom.setRoomId(102);
        updatedRoom.setAvailable(false);
        updatedRoom.setRoomType(updatedRoomType);

        when(roomRepository.findById(1)).thenReturn(Optional.of(sampleRoom));
        when(roomRepository.save(any(Room.class))).thenReturn(updatedRoom);

        Room result = roomService.modifyRoom(1, updatedRoom);
        assertEquals(102, result.getRoomNumber());
        assertFalse(result.isAvailable());
        assertEquals("Suite", result.getRoomType().getTypeName());
    }

    @Test
    void testRemoveRoom_Success() {
        when(roomRepository.findById(1)).thenReturn(Optional.of(sampleRoom));
        doNothing().when(roomRepository).delete(sampleRoom);

        assertDoesNotThrow(() -> roomService.removeRoom(1));
        verify(roomRepository, times(1)).delete(sampleRoom);
    }

    @Test
    void testFetchAvailableRoomsByType_Success() {
        when(roomRepository.findAvailableRoomsByType(1)).thenReturn(List.of(sampleRoom));
        List<Room> result = roomService.fetchAvailableRoomsByType(1);
        assertEquals(1, result.size());
    }

    @Test
    void testFetchAvailableRoomsByType_Empty() {
        when(roomRepository.findAvailableRoomsByType(1)).thenReturn(Collections.emptyList());
        assertThrows(ResourceNotFoundException.class, () -> roomService.fetchAvailableRoomsByType(1));
    }

    @Test
    void testFetchRoomsByLocation_Success() {
        when(roomRepository.findRoomsByLocation("Chennai")).thenReturn(List.of(sampleRoom));
        List<Room> result = roomService.fetchRoomsByLocation("Chennai");
        assertEquals(1, result.size());
    }

    @Test
    void testFetchRoomsByLocation_Empty() {
        when(roomRepository.findRoomsByLocation("Delhi")).thenReturn(Collections.emptyList());
        assertThrows(ResourceNotFoundException.class, () -> roomService.fetchRoomsByLocation("Delhi"));
    }

    @Test
    void testFetchRoomsByAmenity_Success() {
        when(roomRepository.findRoomsByAmenity(5)).thenReturn(List.of(sampleRoom));
        List<Room> result = roomService.fetchRoomsByAmenity(5);
        assertEquals(1, result.size());
    }

    @Test
    void testFetchRoomsByAmenity_Empty() {
        when(roomRepository.findRoomsByAmenity(99)).thenReturn(Collections.emptyList());
        assertThrows(ResourceNotFoundException.class, () -> roomService.fetchRoomsByAmenity(99));
    }
}
